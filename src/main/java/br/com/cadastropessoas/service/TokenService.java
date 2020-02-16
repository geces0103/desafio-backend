package br.com.cadastropessoas.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastropessoas.entity.Pessoa;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TokenService {

	private static final String KEY = UUID.randomUUID().toString();
	private static final long EXPIRATION = 900000; // 15 minutos

	public String generateToken(Pessoa pessoa) {
		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(pessoa.getId().toString())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	public boolean validateToken(String token) {
		String hash = StringUtils.replaceOnce(token, "Basic ", StringUtils.EMPTY);
		if (StringUtils.isNotBlank(hash)) {
			try {
				Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash).getBody();
				if (claims.getExpiration().before(Calendar.getInstance().getTime())) {
					log.warn("token is expired");
					return false;
				}
				return true;
			} catch (SignatureException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
		return false;
	}
	
}

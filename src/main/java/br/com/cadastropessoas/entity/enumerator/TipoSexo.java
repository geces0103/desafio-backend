package br.com.cadastropessoas.entity.enumerator;

public enum TipoSexo {
	
	FEMININO("Feminino"),
	MASCULINO("Masculino");
	
	private String label;
	
	private TipoSexo(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}

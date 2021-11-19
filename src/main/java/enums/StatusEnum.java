package enums;

public enum StatusEnum {
	APROVADO(1, "Aprovado"), 
	PENDENTE(2, "Pendente"),
	REPROVADO(3, "Reprovado"),
	ERRO(4, "Erro");
	
	private int aprovado;
	
	private String statusNome;
	
	StatusEnum(int aprovado, String statusNome) {
		this.aprovado = aprovado;
		this.statusNome = statusNome;
	}

	public int getEnumNome() {
		return aprovado;
	}

	public String getStatusNome() {
		return statusNome;
	}
	
	public static String status(int status) {
		for(StatusEnum statusEnum :values()) {
			if(status == statusEnum.getEnumNome()) return statusEnum.getStatusNome();
		}
		
		return StatusEnum.ERRO.getStatusNome();
	}

	public static int status(String statusNome) {
		for(StatusEnum statusEnum :values()) {
			if(statusNome.equalsIgnoreCase(statusEnum.getStatusNome())) return statusEnum.getEnumNome();
		}
		
		return StatusEnum.ERRO.getEnumNome();
	}
	
	
}

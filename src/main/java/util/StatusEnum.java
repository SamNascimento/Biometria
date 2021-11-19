package util;

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

	public int getAprovado() {
		return aprovado;
	}

	public String getStatusNome() {
		return statusNome;
	}
	
	public static String status(int status) {
		for(StatusEnum statusEnum :values()) {
			if(status == statusEnum.getAprovado()) return statusEnum.getStatusNome();
		}
		
		return StatusEnum.ERRO.getStatusNome();
	}

	public static int status(String statusNome) {
		for(StatusEnum statusEnum :values()) {
			if(statusNome.equalsIgnoreCase(statusEnum.getStatusNome())) return statusEnum.getAprovado();
		}
		
		return StatusEnum.ERRO.getAprovado();
	}
	
	
}

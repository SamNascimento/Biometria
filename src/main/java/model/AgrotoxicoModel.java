package model;

public class AgrotoxicoModel {
	
	private int idAgrotoxico;
	
	private String nomeAgrotoxico;
	
	private String nomeProprietario;
	
	private String tipo;
	
	private int status;
	
	private int idProprietario;

	public String getNomeAgrotoxico() {
		return nomeAgrotoxico;
	}

	public void setNomeAgrotoxico(String nomeAgrotoxico) {
		this.nomeAgrotoxico = nomeAgrotoxico;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	public int getIdAgrotoxico() {
		return idAgrotoxico;
	}

	public void setIdAgrotoxico(int idAgrotoxico) {
		this.idAgrotoxico = idAgrotoxico;
	}
}

package model;

public class FuncionarioModel {
	private int idFuncionario;
	
	private String nome;

	private String cpf;

	private byte[] biometriaEntrada;

	private int nivelAcesso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public byte[] getBiometriaEntrada() {
		return biometriaEntrada;
	}

	public void setBiometriaEntrada(byte[] biometriaEntrada) {
		this.biometriaEntrada = biometriaEntrada;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

}

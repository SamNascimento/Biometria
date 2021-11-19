package model;

public class ProprietarioModel {
	
	private int idProprietario;
	
	private String nome;
	
	private String cpf;
	
	private String endereco;
	
	private String quantidadeAgrotoxicosAprovados;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getQuantidadeAgrotoxicosAprovados() {
		return quantidadeAgrotoxicosAprovados;
	}

	public void setQuantidadeAgrotoxicosAprovados(String quantidadeAgrotoxicosAprovados) {
		this.quantidadeAgrotoxicosAprovados = quantidadeAgrotoxicosAprovados;
	}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
}

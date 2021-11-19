package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.NivelAcesso;
import model.AgrotoxicoModel;
import model.ProprietarioModel;

public class ProprietarioService {
	
	public boolean cadastrarProprietario(ProprietarioModel proprietario) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("INSERT INTO dono(nome, cpf, endereco) values(?, ?, ?)");
		prepareStatement.setString(1, proprietario.getNome());
		prepareStatement.setString(2, proprietario.getCpf());
		prepareStatement.setString(3, proprietario.getEndereco());
		
		int status = prepareStatement.executeUpdate();
		
		return 1 == status ? true : false;
	}

	public List<ProprietarioModel> buscarProprietarios() throws Exception {
		List<ProprietarioModel> listaProprietario = new ArrayList<ProprietarioModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * FROM dono");
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		
		while(resultadoQuery.next()) {
			ProprietarioModel proprietario = new ProprietarioModel();
			
			proprietario.setIdProprietario(resultadoQuery.getInt("idDono"));
			proprietario.setNome(resultadoQuery.getString("nome"));
			proprietario.setCpf(resultadoQuery.getString("cpf"));
			proprietario.setEndereco(resultadoQuery.getString("endereco"));
			
			listaProprietario.add(proprietario);
			
		}
		
		return listaProprietario;
	}

	public ProprietarioModel buscarProprietarioPorId(int idProprietario) throws Exception {
		ProprietarioModel proprietario = new ProprietarioModel();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * FROM dono where idDono =" + idProprietario);
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		
		while(resultadoQuery.next()) {
			proprietario.setIdProprietario(resultadoQuery.getInt("idDono"));
			proprietario.setNome(resultadoQuery.getString("nome"));
			proprietario.setCpf(resultadoQuery.getString("cpf"));
			proprietario.setEndereco(resultadoQuery.getString("endereco"));
		}
		
		return proprietario;
	}

	public boolean atualizarPerfil(ProprietarioModel proprietario) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("UPDATE dono set "
				+ " nome='" + proprietario.getNome() + "', cpf='" + proprietario.getCpf()  + "', endereco='" + proprietario.getEndereco()
				+ "' where idDono =" + NivelAcesso.getIdProprietario());
		
		int status = prepareStatement.executeUpdate();
		
		return 1 == status ? true : false;
	}

	public String buscarQuantidadeAgrotoxicoAprovados(int idFuncionario) throws Exception {
		List<AgrotoxicoModel> listaAgrotoxicos = new ArrayList<AgrotoxicoModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT nome FROM agrotoxico WHERE idDono =" + idFuncionario + " AND status = 1;");
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			AgrotoxicoModel agrotoxico = new AgrotoxicoModel();
			
			agrotoxico.setNomeAgrotoxico(resultadoQuery.getString("nome"));
			listaAgrotoxicos.add(agrotoxico);
		}
		
		return String.valueOf(listaAgrotoxicos.size());
	}
	
	
}

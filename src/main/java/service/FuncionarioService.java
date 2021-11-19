package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import model.FuncionarioModel;
import viewmodel.BiometriaViewModel;

public class FuncionarioService {
	
	@Autowired
	HttpSession session;
	
	public boolean cadastrarFuncionario(FuncionarioModel funcionario) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatementFuncionario = conexao.prepareStatement("INSERT INTO funcionario(nome, cpf, nivelAcesso) values(?, ?, ?)");
		PreparedStatement  prepareStatementBiometria = conexao.prepareStatement("INSERT INTO biometria(biometria, idFuncionario) values(?, LAST_INSERT_ID())");
		
		prepareStatementFuncionario.setString(1, funcionario.getNome());
		prepareStatementFuncionario.setString(2, funcionario.getCpf());
		prepareStatementFuncionario.setInt(3, funcionario.getNivelAcesso());
		
		
		int statusFuncionario = prepareStatementFuncionario.executeUpdate();

		prepareStatementBiometria.setBytes(1, funcionario.getBiometriaEntrada());
		
		int statusBiometria = prepareStatementBiometria.executeUpdate();
		
		return 1 == statusFuncionario && 1 == statusBiometria ? true : false;
	}

	public boolean atualizarFuncionario(FuncionarioModel funcionario) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatementFuncionario = conexao.prepareStatement("UPDATE funcionario SET "
				+ "nome='"+ funcionario.getNome()
				+ "', cpf='"+ funcionario.getCpf() 
				+ "', nivelAcesso='"+ funcionario.getNivelAcesso() + "' where idFuncionario = " + funcionario.getIdFuncionario());
		
		int statusFuncionario = prepareStatementFuncionario.executeUpdate();
		
		return 1 == statusFuncionario ? true : false;
	}
	
	public List<FuncionarioModel> buscarFuncionario() throws Exception {
		List<FuncionarioModel> listaFuncionario = new ArrayList<FuncionarioModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * FROM funcionario");
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		
		while(resultadoQuery.next()) {
			FuncionarioModel funcionario = new FuncionarioModel();
			
			funcionario.setIdFuncionario(resultadoQuery.getInt("idFuncionario"));
			funcionario.setNome(resultadoQuery.getString("nome"));
			funcionario.setCpf(resultadoQuery.getString("cpf"));
			funcionario.setNivelAcesso(resultadoQuery.getInt("NivelAcesso"));
			
			listaFuncionario.add(funcionario);
		}
		
		return listaFuncionario;
	}

	public FuncionarioModel buscarFuncionarioPorId(int id) throws Exception {
		FuncionarioModel funcionario = new FuncionarioModel();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * FROM funcionario where idFuncionario=" + id);
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			funcionario.setIdFuncionario(resultadoQuery.getInt("idFuncionario"));
			funcionario.setNome(resultadoQuery.getString("nome"));
			funcionario.setCpf(resultadoQuery.getString("cpf"));
			funcionario.setNivelAcesso(resultadoQuery.getInt("nivelAcesso"));
		}
		
		return funcionario;
	}

	public int buscarAcessoFuncionario(int id) throws Exception {
		FuncionarioModel funcionario = new FuncionarioModel();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT nivelAcesso FROM funcionario where idFuncionario=" + id);
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			funcionario.setNivelAcesso(resultadoQuery.getInt("nivelAcesso"));
		}
		
		return funcionario.getNivelAcesso();
	}

	public BiometriaViewModel buscarBiometriaAcesso() throws Exception {
		BiometriaViewModel biometriaViewModel = new BiometriaViewModel();
		
		List<byte[]> listaBiometria = new ArrayList<byte[]>();
		List<Integer> listaId = new ArrayList<Integer>();
		
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * FROM biometria");
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			byte[] biometriaByte;
			int idFuncionario;
			
			biometriaByte = resultadoQuery.getBytes("biometria");
			idFuncionario = resultadoQuery.getInt("idFuncionario");
			
			listaId.add(idFuncionario);
			listaBiometria.add(biometriaByte);
		}
		
		
		biometriaViewModel.setListaBiometria(listaBiometria);
		biometriaViewModel.setListaIdFuncionario(listaId);
		return biometriaViewModel;
	}
	
}

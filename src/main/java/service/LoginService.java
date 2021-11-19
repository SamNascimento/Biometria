package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.NivelAcesso;
import model.LoginProprietarioModel;
import model.ProprietarioModel;

public class LoginService {

	public boolean cadastrarLoginProprietario(ProprietarioModel proprietario,
			LoginProprietarioModel loginProprietarioModel) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();

		PreparedStatement prepareStatementProprietario = conexao
				.prepareStatement("INSERT INTO dono (nome, cpf, endereco) values(?, ?, ?)");
		PreparedStatement prepareStatementLogin = conexao.prepareStatement(
				"INSERT INTO login (login, senha, idDono) values(?, ?, LAST_INSERT_ID())");

		prepareStatementProprietario.setString(1, proprietario.getNome());
		prepareStatementProprietario.setString(2, proprietario.getCpf());
		prepareStatementProprietario.setString(3, proprietario.getEndereco());

		int statusProprietario = prepareStatementProprietario.executeUpdate();

		prepareStatementLogin.setString(1, loginProprietarioModel.getLogin());
		prepareStatementLogin.setString(2, loginProprietarioModel.getSenha());

		int statusLoginProprietario = prepareStatementLogin.executeUpdate();

		return 1 == statusProprietario && 1 == statusLoginProprietario ? true : false;
	}

	public boolean validarLogin(String login, String senha) throws Exception {
		List<LoginProprietarioModel> listaLoginProprietario = new ArrayList<LoginProprietarioModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();

		PreparedStatement prepareStatement = conexao.prepareStatement("SELECT * FROM login");
		ResultSet resultadoQuery = prepareStatement.executeQuery();

		while (resultadoQuery.next()) {
			LoginProprietarioModel loginModel = new LoginProprietarioModel();
			loginModel.setLogin(resultadoQuery.getString("login"));
			loginModel.setSenha(resultadoQuery.getString("senha"));
			loginModel.setIdProprietario(resultadoQuery.getInt("idDono"));
			listaLoginProprietario.add(loginModel);
		}
		
		for (LoginProprietarioModel loginProprietarioModel : listaLoginProprietario) {
			if(login.equals(loginProprietarioModel.getLogin()) && senha.equals(loginProprietarioModel.getSenha())) {
				NivelAcesso.setIdProprietario(loginProprietarioModel.getIdProprietario());
				return true;
			} 
		}

		return false;
	}

}

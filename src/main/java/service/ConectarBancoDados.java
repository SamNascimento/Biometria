package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarBancoDados {

	public static Connection conectarBanco() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoaps?autoReconnect=true&useSSL=false", "CCAPS", "root");
		return conexao;
	}
}

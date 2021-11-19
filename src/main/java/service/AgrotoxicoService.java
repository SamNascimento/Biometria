package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.NivelAcesso;
import model.AgrotoxicoModel;

public class AgrotoxicoService {
	
	public boolean cadastrarAgrotoxico(AgrotoxicoModel agrotoxico) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("INSERT INTO agrotoxico(nome, tipo, status, idDono) values(?, ?, ?, ?)");
		prepareStatement.setString(1, agrotoxico.getNomeAgrotoxico());
		prepareStatement.setString(2, agrotoxico.getTipo());
		prepareStatement.setInt(3, agrotoxico.getStatus());
		prepareStatement.setInt(4, NivelAcesso.getIdProprietario());
		
		int status = prepareStatement.executeUpdate();
		
		return 1 == status ? true : false;
	}
	
	public List<AgrotoxicoModel> buscarAgrotoxicos() throws Exception {
		List<AgrotoxicoModel> listaAgrotoxicos = new ArrayList<AgrotoxicoModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * from agrotoxico");
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			AgrotoxicoModel agrotoxico = new AgrotoxicoModel();
			
			agrotoxico.setIdAgrotoxico(resultadoQuery.getInt("idAgrotoxico"));
			agrotoxico.setNomeAgrotoxico(resultadoQuery.getString("nome"));
			agrotoxico.setTipo(resultadoQuery.getString("tipo"));
			agrotoxico.setStatus(resultadoQuery.getInt("status"));
			agrotoxico.setIdProprietario(resultadoQuery.getInt("idDono"));
			listaAgrotoxicos.add(agrotoxico);
		}
		
		return listaAgrotoxicos;
	}
	public List<AgrotoxicoModel> buscarAgrotoxicosPorId(int idProprietario) throws Exception {
		 List<AgrotoxicoModel> listaAgrotoxico = new ArrayList<AgrotoxicoModel>();
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("SELECT * from agrotoxico where idDono=" + idProprietario);
		ResultSet resultadoQuery = prepareStatement.executeQuery();
		
		while(resultadoQuery.next()) {
			AgrotoxicoModel agrotoxico =  new AgrotoxicoModel();
			agrotoxico.setIdAgrotoxico(resultadoQuery.getInt("idAgrotoxico"));
			agrotoxico.setNomeAgrotoxico(resultadoQuery.getString("nome"));
			agrotoxico.setTipo(resultadoQuery.getString("tipo"));
			agrotoxico.setStatus(resultadoQuery.getInt("status"));
			agrotoxico.setIdProprietario(resultadoQuery.getInt("idDono"));
			
			listaAgrotoxico.add(agrotoxico);
		}
		
		return listaAgrotoxico;
	}

	public boolean aprovarAgrotoxico(int status, int idAgrotoxico) throws Exception {
		Connection conexao = ConectarBancoDados.conectarBanco();
		
		PreparedStatement  prepareStatement = conexao.prepareStatement("UPDATE agrotoxico SET status =" + status + " where idAgrotoxico = " + idAgrotoxico);
		int statusQuery = prepareStatement.executeUpdate();
		
		return 1 == statusQuery ? true : false;
	}
}

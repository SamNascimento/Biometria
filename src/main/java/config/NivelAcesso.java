package config;

public class NivelAcesso {
	public static int idFuncionario;
	
	public static int idProprietario;

	public static int nivelAcessoFuncionario;
	
	public static String tipoAcesso;

	public static int getIdFuncionario() {
		return idFuncionario;
	}

	public static void setIdFuncionario(int idFuncionario) {
		NivelAcesso.idFuncionario = idFuncionario;
	}

	public static int getIdProprietario() {
		return idProprietario;
	}

	public static void setIdProprietario(int idProprietario) {
		NivelAcesso.idProprietario = idProprietario;
	}

	public static int getNivelAcessoFuncionario() {
		return nivelAcessoFuncionario;
	}

	public static void setNivelAcessoFuncionario(int nivelAcessoFuncionario) {
		NivelAcesso.nivelAcessoFuncionario = nivelAcessoFuncionario;
	}

	public static String getTipoAcesso() {
		return tipoAcesso;
	}

	public static void setTipoAcesso(String tipoAcesso) {
		NivelAcesso.tipoAcesso = tipoAcesso;
	}
}

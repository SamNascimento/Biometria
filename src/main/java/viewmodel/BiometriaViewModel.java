package viewmodel;

import java.util.List;

public class BiometriaViewModel {
	private List<byte[]> listaBiometria;
	
	private List<Integer> listaIdFuncionario;

	public List<byte[]> getListaBiometria() {
		return listaBiometria;
	}
	

	public void setListaBiometria(List<byte[]> listaBiometria) {
		this.listaBiometria = listaBiometria;
	}

	public List<Integer> getListaIdFuncionario() {
		return listaIdFuncionario;
	}

	public void setListaIdFuncionario(List<Integer> listaIdFuncionario) {
		this.listaIdFuncionario = listaIdFuncionario;
	}
}

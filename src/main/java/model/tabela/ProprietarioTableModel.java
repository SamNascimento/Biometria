package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.ProprietarioModel;

public class ProprietarioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1620918827787136402L;
	
	private List<ProprietarioModel> proprietarios;
	private String[] colunas = new String[] { "Nome", "CPF", "Endereço" };

	/** Creates a new instance of DevmediaTableModel */
	    public ProprietarioTableModel(List<ProprietarioModel> proprietarios) {
	        this.proprietarios = proprietarios;
	    }

	public ProprietarioTableModel(){
	     this.proprietarios = new ArrayList<ProprietarioModel>();
	    }

	public int getRowCount() {
		return proprietarios.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(ProprietarioModel aValue, int rowIndex) {
		ProprietarioModel proprietario = proprietarios.get(rowIndex);
		
		proprietario.setNome(aValue.getNome());
		proprietario.setCpf(aValue.getCpf());
		proprietario.setEndereco(aValue.getEndereco());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ProprietarioModel proprietario = proprietarios.get(rowIndex);

		switch (columnIndex) {
		case 0:
			proprietario.setNome(aValue.toString());
		case 1:
			proprietario.setCpf(aValue.toString());
		case 2:
			proprietario.setEndereco(aValue.toString());

		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ProprietarioModel proprietarioSelecionado = proprietarios.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = proprietarioSelecionado.getNome();
			break;
		case 1:
			valueObject = proprietarioSelecionado.getCpf();
			break;
		case 2:
			valueObject = proprietarioSelecionado.getEndereco();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean ProprietarioModel.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public ProprietarioModel getProprietario(int indiceLinha) {
		return proprietarios.get(indiceLinha);
	}

	public void addProprietario(ProprietarioModel u) {
		proprietarios.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProprietario(int indiceLinha) {
		proprietarios.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeProprietarios(List<ProprietarioModel> novosProprietarios) {

		int tamanhoAntigo = getRowCount();
		proprietarios.addAll(novosProprietarios);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		proprietarios.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return proprietarios.isEmpty();
	}

}

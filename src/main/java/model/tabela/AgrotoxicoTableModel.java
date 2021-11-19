package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import enums.StatusEnum;
import model.AgrotoxicoModel;

public class AgrotoxicoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1620918827787136402L;
	
	private List<AgrotoxicoModel> agrotoxicos;
	private String[] colunas = new String[] { "Proprietario", "Nome", "Tipo", "Status" };

	/** Creates a new instance of DevmediaTableModel */
	    public AgrotoxicoTableModel(List<AgrotoxicoModel> agrotoxicos) {
	        this.agrotoxicos = agrotoxicos;
	    }

	public AgrotoxicoTableModel(){
	     this.agrotoxicos = new ArrayList<AgrotoxicoModel>();
	    }

	public int getRowCount() {
		return agrotoxicos.size();
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

	public void setValueAt(AgrotoxicoModel aValue, int rowIndex) {
		AgrotoxicoModel agrotoxico = agrotoxicos.get(rowIndex);
		
		agrotoxico.setNomeProprietario(aValue.getNomeProprietario());
		agrotoxico.setNomeAgrotoxico(aValue.getNomeAgrotoxico());
		agrotoxico.setTipo(aValue.getTipo());
		agrotoxico.setStatus(StatusEnum.status(aValue.getTipo()));
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		AgrotoxicoModel agrotoxico = agrotoxicos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			agrotoxico.setNomeProprietario(aValue.toString());
		case 1:
			agrotoxico.setNomeAgrotoxico(aValue.toString());
		case 2:
			agrotoxico.setTipo(aValue.toString());
		case 3: 
			agrotoxico.setStatus(StatusEnum.status(aValue.toString()));
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		AgrotoxicoModel agroxicoSelecionado = agrotoxicos.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = agroxicoSelecionado.getNomeProprietario();
			break;
		case 1:
			valueObject = agroxicoSelecionado.getNomeAgrotoxico();
			break;
		case 2:
			valueObject = agroxicoSelecionado.getTipo();
			break;
		case 3:
			valueObject = StatusEnum.status(agroxicoSelecionado.getStatus());
		default:
			System.err.println("Índice inválido para propriedade do bean.");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public AgrotoxicoModel getProprietario(int indiceLinha) {
		return agrotoxicos.get(indiceLinha);
	}

	public void addProprietario(AgrotoxicoModel u) {
		agrotoxicos.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProprietario(int indiceLinha) {
		agrotoxicos.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeProprietarios(List<AgrotoxicoModel> novosAgrotoxicos) {

		int tamanhoAntigo = getRowCount();
		agrotoxicos.addAll(novosAgrotoxicos);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		agrotoxicos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return agrotoxicos.isEmpty();
	}
}

package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import viewmodel.AprovarAgrotoxicoViewModel;	

public class AprovarAgrotoxicoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1620918827787136402L;
	
	private List<AprovarAgrotoxicoViewModel> aprovarAgrotoxicos;
	private String[] colunas = new String[] { "Id agrotoxico", "proprietario", "nome", "status" };

	/** Creates a new instance of DevmediaTableModel */
	    public AprovarAgrotoxicoTableModel(List<AprovarAgrotoxicoViewModel> aprovarAgrotoxicos) {
	        this.aprovarAgrotoxicos = aprovarAgrotoxicos;
	    }

	public AprovarAgrotoxicoTableModel(){
	     this.aprovarAgrotoxicos = new ArrayList<AprovarAgrotoxicoViewModel>();
	    }

	public int getRowCount() {
		return aprovarAgrotoxicos.size();
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

	public void setValueAt(AprovarAgrotoxicoViewModel aValue, int rowIndex) {
		AprovarAgrotoxicoViewModel aprovarAgrotoxico = aprovarAgrotoxicos.get(rowIndex);
		
		aprovarAgrotoxico.setIdAgrotoxico(aValue.getIdAgrotoxico());
		aprovarAgrotoxico.setNomeProprietario(aValue.getNomeProprietario());
		aprovarAgrotoxico.setNomeAgrotoxico(aValue.getNomeAgrotoxico());
		aprovarAgrotoxico.setStatus(aValue.getStatus());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		AprovarAgrotoxicoViewModel aprovarArotoxico = aprovarAgrotoxicos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			aprovarArotoxico.setIdAgrotoxico(aValue.toString());
		case 1:
			aprovarArotoxico.setNomeProprietario(aValue.toString());
		case 2:
			aprovarArotoxico.setNomeAgrotoxico(aValue.toString());
		case 3: 
			aprovarArotoxico.setStatus(aValue.toString());
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		AprovarAgrotoxicoViewModel agroxicoSelecionado = aprovarAgrotoxicos.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = agroxicoSelecionado.getIdAgrotoxico();
			break;
		case 1:
			valueObject = agroxicoSelecionado.getNomeProprietario();
			break;
		case 2:
			valueObject = agroxicoSelecionado.getNomeAgrotoxico();
			break;
		case 3:
			valueObject = agroxicoSelecionado.getStatus();
		default:
			System.err.println("Índice inválido para propriedade do bean.");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public AprovarAgrotoxicoViewModel getProprietario(int indiceLinha) {
		return aprovarAgrotoxicos.get(indiceLinha);
	}

	public void addProprietario(AprovarAgrotoxicoViewModel u) {
		aprovarAgrotoxicos.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProprietario(int indiceLinha) {
		aprovarAgrotoxicos.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeProprietarios(List<AprovarAgrotoxicoViewModel> novosAgrotoxicos) {

		int tamanhoAntigo = getRowCount();
		aprovarAgrotoxicos.addAll(novosAgrotoxicos);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		aprovarAgrotoxicos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return aprovarAgrotoxicos.isEmpty();
	}
}

package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.FuncionarioModel;

public class FuncionariosTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1620918827787136402L;
	
	private List<FuncionarioModel> funcionarios;
	private String[] colunas = new String[] { "Nome", "CPF" };

	/** Creates a new instance of DevmediaTableModel */
	    public FuncionariosTableModel(List<FuncionarioModel> funcionario) {
	        this.funcionarios = funcionario;
	    }

	public FuncionariosTableModel(){
	     this.funcionarios = new ArrayList<FuncionarioModel>();
	    }

	public int getRowCount() {
		return funcionarios.size();
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

	public void setValueAt(FuncionarioModel aValue, int rowIndex) {
		FuncionarioModel funcionario = funcionarios.get(rowIndex);
		
		funcionario.setNome(aValue.getNome());
		funcionario.setCpf(aValue.getCpf());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		FuncionarioModel funcionario = funcionarios.get(rowIndex);

		switch (columnIndex) {
		case 0:
			funcionario.setNome(aValue.toString());
		case 1:
			funcionario.setCpf(aValue.toString());

		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		FuncionarioModel funcionarioSelecionado = funcionarios.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = funcionarioSelecionado.getNome();
			break;
		case 1:
			valueObject = funcionarioSelecionado.getCpf();
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

	public FuncionarioModel getProprietario(int indiceLinha) {
		return funcionarios.get(indiceLinha);
	}

	public void addProprietario(FuncionarioModel u) {
		funcionarios.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProprietario(int indiceLinha) {
		funcionarios.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeProprietarios(List<FuncionarioModel> novosFuncionarios) {

		int tamanhoAntigo = getRowCount();
		funcionarios.addAll(novosFuncionarios);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		funcionarios.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return funcionarios.isEmpty();
	}

}

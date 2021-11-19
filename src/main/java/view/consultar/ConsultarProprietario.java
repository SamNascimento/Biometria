package view.consultar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.ProprietarioModel;
import model.tabela.ProprietarioTableModel;
import service.ProprietarioService;
import util.Utils;

public class ConsultarProprietario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	ProprietarioService proprietarioService = new ProprietarioService();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JPanel painelFundo;
	JTable tabela;
	JScrollPane scrollPane;
	JButton voltar;
	JLabel titulo;

	JFrame consultarFuncionarioFrame = new JFrame("Consultar Proprietario");

	public ConsultarProprietario() throws Exception {
		consultarFuncionarioFrame.setBounds(20, 20, 900, 500);
		consultarFuncionarioFrame.setLayout(null);
		consultarFuncionarioFrame.setResizable(false);
		consultarFuncionarioFrame.setLocationRelativeTo(null);
		consultarFuncionarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		painelFundo = new JPanel();
		ProprietarioTableModel proprietarioTabelaModel = new ProprietarioTableModel();
		
		tabela = new JTable();
		tabela.setModel(proprietarioTabelaModel);
		List<ProprietarioModel> listaProprietarios = listaProprietarios();
		proprietarioTabelaModel.addListaDeProprietarios(listaProprietarios);
		
		scrollPane = new JScrollPane(tabela);
		tabela.setFillsViewportHeight(true);
		tabela.setBounds(20, 20, 800, 500);

		painelFundo.add(scrollPane);
		painelFundo.setBounds(20, 120, 850, 300);
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.setForeground(Color.red);

		titulo = new JLabel("Consultar Propietario");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);

		voltar = new JButton("Voltar");
		voltar.setBounds(770, 25, 100, 30);
		voltar.setFont(font2);
		voltar.addActionListener(this);

		consultarFuncionarioFrame.getContentPane().add(titulo);
		consultarFuncionarioFrame.getContentPane().add(painelFundo);
		consultarFuncionarioFrame.getContentPane().add(voltar);
		consultarFuncionarioFrame.setVisible(true);
	}

	private List<ProprietarioModel> listaProprietarios() throws Exception {
		List<ProprietarioModel> listaProprietarios = proprietarioService.buscarProprietarios();
		
		for (int index = 0; index < listaProprietarios.size(); index++) {
			listaProprietarios.get(index).setQuantidadeAgrotoxicosAprovados(proprietarioService.buscarQuantidadeAgrotoxicoAprovados(listaProprietarios.get(index).getIdProprietario()));
		}
		
		return listaProprietarios;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == voltar) {
			Utils.voltarParaMenu(consultarFuncionarioFrame);
		}
	}
}

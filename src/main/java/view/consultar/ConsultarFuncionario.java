package view.consultar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.tabela.FuncionariosTableModel;
import service.FuncionarioService;
import util.Utils;

public class ConsultarFuncionario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;
	
	FuncionarioService funcionarioService = new FuncionarioService();
	
	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JPanel painelFundo;
	JTable tabela;
	JScrollPane scrollPane;
	JButton voltar;
	JLabel titulo;
	
	JFrame consultarFuncionarioFrame = new JFrame("Consultar Funcionários");
	
	public ConsultarFuncionario() throws Exception {
		consultarFuncionarioFrame.setBounds(20, 20, 800, 500);
		consultarFuncionarioFrame.setLayout(null);
		consultarFuncionarioFrame.setResizable(false);
		consultarFuncionarioFrame.setLocationRelativeTo(null);
		consultarFuncionarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		titulo = new JLabel("Consultar Funcionários");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(670, 25, 100, 30);
		voltar.setFont(font2);
		voltar.addActionListener(this);

		painelFundo = new JPanel();
		FuncionariosTableModel funcionarioTableModel = new FuncionariosTableModel();
		
		
		tabela = new JTable(funcionarioTableModel);
		tabela.setModel(funcionarioTableModel);
		funcionarioTableModel.addListaDeProprietarios(funcionarioService.buscarFuncionario());
		
		scrollPane = new JScrollPane(tabela);
		tabela.setFillsViewportHeight(true);	
		tabela.setBounds(20, 20, 800, 500);	
		
		painelFundo.add(scrollPane);
		painelFundo.setBounds(20, 120, 750, 300);
		painelFundo.setLayout( new GridLayout(1, 1));
		painelFundo.setForeground(Color.red);

		consultarFuncionarioFrame.getContentPane().add(titulo);
		consultarFuncionarioFrame.getContentPane().add(painelFundo);
		consultarFuncionarioFrame.getContentPane().add(voltar);
		consultarFuncionarioFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == voltar) {
			Utils.voltarParaMenu(consultarFuncionarioFrame);
		}
	}

}

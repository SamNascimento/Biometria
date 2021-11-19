package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import config.NivelAcesso;
import view.cadastrar.CadastrarAgrotoxico;
import view.cadastrar.CadastrarFuncionario;
import view.consultar.ConsultarAgrotoxico;
import view.consultar.ConsultarFuncionario;
import view.consultar.ConsultarProprietario;
import view.editar.EditarFuncionario;
import view.editar.EditarProprietario;
import view.funcionario.AprovarAgrotoxico;

public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 4521939660076860433L;

	JMenuBar menuBar;
	JMenu inicio, funcionario, proprietario, cadastroFuncionario, cadastroAgrotoxico, opcoes;
	JMenuItem consultarProprietario, consultarAgrotoxicos, consultarFuncionarios, editarPerfil, editarFuncionario;
	JMenuItem cadastrarFuncionario, cadastrarAgrotoxico, aprovarAgrotoxico, sair, desconectar;

	int funcionarioId;

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame menu = new JFrame("Indentificação Biométrica");

	public Menu() {
		menu.setBounds(20, 20, 900, 500);
		menu.setLayout(null);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();

		inicio = new JMenu("Inicio");
		funcionario = new JMenu("Funcionario");
		cadastroFuncionario = new JMenu("Cadatrar");
		proprietario = new JMenu("Proprietario");
		cadastroAgrotoxico = new JMenu("Cadatrar");
		opcoes = new JMenu("Opções");

		consultarFuncionarios = new JMenuItem("Consultar funcionários");
		consultarFuncionarios.addActionListener(this);

		cadastrarFuncionario = new JMenuItem("Cadastrar funcionario");
		cadastrarFuncionario.addActionListener(this);

		consultarAgrotoxicos = new JMenuItem("Consultar agrotoxicos");
		consultarAgrotoxicos.addActionListener(this);

		consultarProprietario = new JMenuItem("Consultar proprietario");
		consultarProprietario.addActionListener(this);

		editarPerfil = new JMenuItem("Editar Perfil");
		editarPerfil.addActionListener(this);

		editarFuncionario = new JMenuItem("Editar funcionário");
		editarFuncionario.addActionListener(this);

		cadastrarAgrotoxico = new JMenuItem("Cadastrar agrotoxico");
		cadastrarAgrotoxico.addActionListener(this);

		aprovarAgrotoxico = new JMenuItem("Aprovar agrotoxico");
		aprovarAgrotoxico.addActionListener(this);

		desconectar = new JMenuItem("Desconectar");
		desconectar.addActionListener(this);

		sair = new JMenuItem("Sair");
		sair.addActionListener(this);

		inicio.add(aprovarAgrotoxico);

		funcionario.add(consultarFuncionarios);
		funcionario.add(editarFuncionario);
		funcionario.add(cadastroFuncionario);

		proprietario.add(consultarProprietario);
		proprietario.add(consultarAgrotoxicos);
		proprietario.add(cadastroAgrotoxico);

		cadastroAgrotoxico.add(cadastrarAgrotoxico);
		cadastroFuncionario.add(cadastrarFuncionario);

		opcoes.add(editarPerfil);
		opcoes.add(desconectar);
		opcoes.add(sair);

		definirAcesso();

		menuBar.add(inicio);
		menuBar.add(funcionario);
		menuBar.add(proprietario);
		menuBar.add(opcoes);

		menu.setJMenuBar(menuBar);
		menu.setVisible(true);
	}

	private void definirAcesso() {
		String tipoAcesso = NivelAcesso.getTipoAcesso();

		if ("Proprietario".equalsIgnoreCase(tipoAcesso)) {
			funcionario.setVisible(false);
			inicio.setVisible(false);
			consultarProprietario.setVisible(false);

		} else if ("Funcionario".equalsIgnoreCase(tipoAcesso)) {
			int nivelAcesso = NivelAcesso.getNivelAcessoFuncionario();
			cadastroAgrotoxico.setVisible(false);
			cadastrarAgrotoxico.setVisible(false);
			editarPerfil.setVisible(false);

			switch (nivelAcesso) {
			case 1:
				cadastroFuncionario.setVisible(false);
				editarFuncionario.setVisible(false);
				inicio.setVisible(false);
				break;
			case 2:
				cadastroFuncionario.setVisible(false);
				editarFuncionario.setVisible(false);
			default:
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == cadastrarFuncionario) {
				new CadastrarFuncionario();
			} else if (event.getSource() == consultarFuncionarios) {
				new ConsultarFuncionario();

			} else if (event.getSource() == consultarAgrotoxicos) {
				new ConsultarAgrotoxico();
			} else if (event.getSource() == consultarProprietario) {
				new ConsultarProprietario();
			} else if (event.getSource() == cadastrarAgrotoxico) {
				new CadastrarAgrotoxico();
			} else if (event.getSource() == aprovarAgrotoxico) {
				new AprovarAgrotoxico();
			} else if (event.getSource() == desconectar) {
				menu.dispose();
				new Inicio();
			} else if (event.getSource() == editarPerfil) {
				new EditarProprietario();
			} else if (event.getSource() == editarFuncionario) {
				new EditarFuncionario();
			} else if (event.getSource() == sair) {
				System.exit(0);
			}
		} catch (Exception e) {

		}
	}
}

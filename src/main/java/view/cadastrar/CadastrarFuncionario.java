package view.cadastrar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.FuncionarioModel;
import service.FuncionarioService;
import util.Utils;
import view.login.LoginFuncionario;

public class CadastrarFuncionario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	FuncionarioService funcionarioService = new FuncionarioService();
	FuncionarioModel funcionarioModel = new FuncionarioModel();
	JFileChooser escolherBiometriaAcesso = new JFileChooser();

	String caminhoImagem;

	JButton cadastrarFuncionario, cancelar, selecionarBiometriaButton;
	JLabel titulo, nomeFuncionarioLabel, cpfFuncionarioLabel, nivelAcessoLabel, selecionarBiometriaLabel;
	JTextField nomeUsuarioInput, cpfUsuarioInput;
	ButtonGroup nivelAcesso;
	JRadioButton nivelAcesso1, nivelAcesso2, nivelAcesso3;


	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame cadastroFuncionarioFrame = new JFrame("Cadastrar funcionário");

	public CadastrarFuncionario() {
		cadastroFuncionarioFrame.setBounds(20, 20, 800, 700);
		cadastroFuncionarioFrame.setLayout(null);
		cadastroFuncionarioFrame.setResizable(false);
		cadastroFuncionarioFrame.setLocationRelativeTo(null);
		cadastroFuncionarioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titulo = new JLabel("Cadastro Funcionário");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);

		nomeFuncionarioLabel = new JLabel("Nome de usuário: ");
		nomeFuncionarioLabel.setBounds(30, 80, 200, 40);
		nomeFuncionarioLabel.setFont(font2);

		nomeUsuarioInput = new JTextField();
		nomeUsuarioInput.setBounds(30, 115, 150, 27);

		cpfFuncionarioLabel = new JLabel("CPF: ");
		cpfFuncionarioLabel.setBounds(200, 80, 200, 40);
		cpfFuncionarioLabel.setFont(font2);

		cpfUsuarioInput = new JTextField();
		cpfUsuarioInput.setBounds(200, 115, 150, 27);

		nivelAcessoLabel = new JLabel("Selecione nivel de acesso deste usuário: ");
		nivelAcessoLabel.setBounds(370, 80, 200, 40);
		nivelAcessoLabel.setFont(font2);

		nivelAcesso1 = new JRadioButton("1");
		nivelAcesso1.setBounds(370, 115, 35, 20);

		nivelAcesso2 = new JRadioButton("2");
		nivelAcesso2.setBounds(405, 115, 35, 20);

		nivelAcesso3 = new JRadioButton("3");
		nivelAcesso3.setBounds(440, 115, 35, 20);

		nivelAcesso = new ButtonGroup();
		nivelAcesso.add(nivelAcesso1);
		nivelAcesso.add(nivelAcesso2);
		nivelAcesso.add(nivelAcesso3);

		selecionarBiometriaButton = new JButton("Selecionar biometria");
		selecionarBiometriaButton.setBounds(30, 170, 200, 30);
		selecionarBiometriaButton.setFont(font2);
		selecionarBiometriaButton.addActionListener(this);

		selecionarBiometriaLabel = new JLabel();
		selecionarBiometriaLabel.setBounds(30, 220, 240, 336);
		selecionarBiometriaLabel.setFont(font2);

		cadastrarFuncionario = new JButton("Cadastrar");
		cadastrarFuncionario.setBounds(30, 580, 100, 30);
		cadastrarFuncionario.setFont(font2);
		cadastrarFuncionario.addActionListener(this);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(150, 580, 100, 30);
		cancelar.setFont(font2);
		cancelar.addActionListener(this);

		cadastroFuncionarioFrame.getContentPane().add(titulo);
		cadastroFuncionarioFrame.getContentPane().add(nomeFuncionarioLabel);
		cadastroFuncionarioFrame.getContentPane().add(nomeUsuarioInput);
		cadastroFuncionarioFrame.getContentPane().add(cpfFuncionarioLabel);
		cadastroFuncionarioFrame.getContentPane().add(cpfUsuarioInput);
		cadastroFuncionarioFrame.getContentPane().add(nivelAcessoLabel);
		cadastroFuncionarioFrame.getContentPane().add(nivelAcesso1);
		cadastroFuncionarioFrame.getContentPane().add(nivelAcesso2);
		cadastroFuncionarioFrame.getContentPane().add(nivelAcesso3);
		cadastroFuncionarioFrame.getContentPane().add(selecionarBiometriaButton);
		cadastroFuncionarioFrame.getContentPane().add(selecionarBiometriaLabel);
		cadastroFuncionarioFrame.getContentPane().add(cadastrarFuncionario);
		cadastroFuncionarioFrame.getContentPane().add(cancelar);
		cadastroFuncionarioFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cadastrarFuncionario) {
			try {
				funcionarioModel.setNome(nomeUsuarioInput.getText());
				funcionarioModel.setCpf(cpfUsuarioInput.getText());

				funcionarioModel.setBiometriaEntrada(Files.readAllBytes(Paths.get(caminhoImagem)));

				for (Enumeration<?> buttons = nivelAcesso.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = (AbstractButton) buttons.nextElement();

					if (button.isSelected()) {
						funcionarioModel.setNivelAcesso(Integer.parseInt(button.getText()));
						break;
					}
				}

				boolean statusCadastro = funcionarioService.cadastrarFuncionario(funcionarioModel);

				if (statusCadastro) {
					Utils.exibirMensagem(cadastroFuncionarioFrame, "Cadastrado com sucesso!", "Sucesso");
				} else {
					Utils.exibirMensagem(cadastroFuncionarioFrame, "Erro na inclusão!", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(cadastroFuncionarioFrame, "Erro na inclusão!", "Erro");
				e.printStackTrace();
			}
		} else if (event.getSource() == selecionarBiometriaButton) {
			escolherBiometriaAcesso.showOpenDialog(null);

			selecionarBiometriaLabel.setIcon(null);

			caminhoImagem = escolherBiometriaAcesso.getCurrentDirectory().getPath().concat("\\")
					.concat(escolherBiometriaAcesso.getSelectedFile().getName());
			selecionarBiometriaLabel.setIcon(new ImageIcon(caminhoImagem));
		} else if (event.getSource() == cancelar) {
			cadastroFuncionarioFrame.dispose();
			new LoginFuncionario();
		}

	}

}

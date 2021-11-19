package view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import config.NivelAcesso;
import model.LoginProprietarioModel;
import model.ProprietarioModel;
import service.LoginService;
import util.Utils;
import view.Inicio;
import view.Menu;

public class LoginProprietario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5390750743168111591L;

	private static final String JA_POSSUI_LOGIN = "Logar-se";

	private LoginService loginService = new LoginService();

	JButton logarBotao, cancelar, cadastrarProprietarioBotao;
	JLabel titulo, loginProprietarioLabel, senhaProprietarioLabel, enderecoProprietarioLabel, nomeProprietarioLabel,
			cpfProprietarioLabel;
	JTextField loginProprietarioInput, enderecoProprietarioInput, nomeProprietarioInput, cpfProprietarioInput;
	
	JPasswordField senhaProprietarioInput;
	
	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Dialog", Font.PLAIN, 15);
	Color transparente = new Color(0, 0, 0, 0);

	JFrame loginProprietarioFrame = new JFrame("Login Proprietario");
	
	public LoginProprietario() {
		SwingUtilities.invokeLater(() -> {
			loginProprietarioFrame.setBounds(25, 20, 500, 500);
			loginProprietarioFrame.setLayout(null);
			loginProprietarioFrame.setResizable(false);
			loginProprietarioFrame.setLocationRelativeTo(null);
			loginProprietarioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			titulo = new JLabel("Login Proprietário Rural");
			titulo.setBounds(80, 10, 400, 50);
			titulo.setFont(font1);

			nomeProprietarioLabel = new JLabel("Nome: ");
			nomeProprietarioLabel.setBounds(30, 80, 200, 40);
			nomeProprietarioLabel.setFont(font2);

			nomeProprietarioInput = new JTextField();
			nomeProprietarioInput.setBounds(30, 115, 430, 25);

			cpfProprietarioLabel = new JLabel("CPF: ");
			cpfProprietarioLabel.setBounds(30, 135, 200, 40);
			cpfProprietarioLabel.setFont(font2);

			cpfProprietarioInput = new JTextField();
			cpfProprietarioInput.setBounds(30, 165, 430, 25);

			loginProprietarioLabel = new JLabel("Login: ");
			loginProprietarioLabel.setBounds(30, 120, 200, 40);
			loginProprietarioLabel.setFont(font2);

			loginProprietarioInput = new JTextField();
			loginProprietarioInput.setBounds(30, 160, 430, 25);

			senhaProprietarioLabel = new JLabel("Senha: ");
			senhaProprietarioLabel.setBounds(30, 180, 200, 40);
			senhaProprietarioLabel.setFont(font2);

			senhaProprietarioInput = new JPasswordField();
			senhaProprietarioInput.setBounds(30, 210, 430, 25);

			enderecoProprietarioLabel = new JLabel("Endereço: ");
			enderecoProprietarioLabel.setBounds(30, 290, 200, 40);
			enderecoProprietarioLabel.setFont(font2);

			enderecoProprietarioInput = new JTextField();
			enderecoProprietarioInput.setBounds(30, 320, 430, 25);

			enderecoProprietarioLabel.setVisible(false);
			enderecoProprietarioInput.setVisible(false);
			nomeProprietarioLabel.setVisible(false);
			nomeProprietarioInput.setVisible(false);
			cpfProprietarioLabel.setVisible(false);
			cpfProprietarioInput.setVisible(false);

			cadastrarProprietarioBotao = new JButton("Cadastrar-se");
			cadastrarProprietarioBotao.setBounds(30, 350, 220, 40);
			cadastrarProprietarioBotao.setFont(font2);
			cadastrarProprietarioBotao.addActionListener(this);

			logarBotao = new JButton("Enviar");
			logarBotao.setBounds(30, 270, 100, 30);
			logarBotao.setFont(font2);
			logarBotao.addActionListener(this);

			cancelar = new JButton("Cancelar");
			cancelar.setBounds(150, 270, 100, 30);
			cancelar.setFont(font2);
			cancelar.addActionListener(this);

			loginProprietarioFrame.getContentPane().add(titulo);
			loginProprietarioFrame.getContentPane().add(nomeProprietarioLabel);
			loginProprietarioFrame.getContentPane().add(nomeProprietarioInput);
			loginProprietarioFrame.getContentPane().add(cpfProprietarioLabel);
			loginProprietarioFrame.getContentPane().add(cpfProprietarioInput);
			loginProprietarioFrame.getContentPane().add(loginProprietarioLabel);
			loginProprietarioFrame.getContentPane().add(loginProprietarioInput);
			loginProprietarioFrame.getContentPane().add(senhaProprietarioLabel);
			loginProprietarioFrame.getContentPane().add(senhaProprietarioInput);
			loginProprietarioFrame.getContentPane().add(cadastrarProprietarioBotao);
			loginProprietarioFrame.getContentPane().add(enderecoProprietarioLabel);
			loginProprietarioFrame.getContentPane().add(enderecoProprietarioInput);
			loginProprietarioFrame.getContentPane().add(logarBotao);
			loginProprietarioFrame.getContentPane().add(cancelar);
			loginProprietarioFrame.setVisible(true);
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cadastrarProprietarioBotao) {
			if (JA_POSSUI_LOGIN.equalsIgnoreCase(cadastrarProprietarioBotao.getText())) {
				titulo.setText("Login Proprietário Rural");
				logarBotao.setText("Logar");

				titulo.setBounds(80, 10, 400, 50);
				loginProprietarioLabel.setBounds(30, 120, 200, 40);
				loginProprietarioInput.setBounds(30, 160, 430, 25);
				senhaProprietarioLabel.setBounds(30, 180, 200, 40);
				senhaProprietarioInput.setBounds(30, 210, 430, 25);
				cadastrarProprietarioBotao.setBounds(30, 350, 220, 40);
				logarBotao.setBounds(30, 270, 100, 30);
				cancelar.setBounds(150, 270, 100, 30);

				enderecoProprietarioLabel.setVisible(false);
				enderecoProprietarioInput.setVisible(false);
				nomeProprietarioLabel.setVisible(false);
				nomeProprietarioInput.setVisible(false);
				cpfProprietarioLabel.setVisible(false);
				cpfProprietarioInput.setVisible(false);

				cadastrarProprietarioBotao.setText("Cadastrar-se");
			} else {
				titulo.setText("Cadastrar Proprietário Rural");
				logarBotao.setText("Cadastrar");

				titulo.setBounds(55, 10, 400, 50);
				loginProprietarioLabel.setBounds(30, 185, 200, 40);
				loginProprietarioInput.setBounds(30, 220, 430, 25);
				senhaProprietarioLabel.setBounds(30, 240, 200, 40);
				senhaProprietarioInput.setBounds(30, 270, 430, 25);
				cadastrarProprietarioBotao.setBounds(340, 400, 100, 40);
				logarBotao.setBounds(30, 380, 100, 30);
				cancelar.setBounds(150, 380, 100, 30);

				enderecoProprietarioLabel.setVisible(true);
				enderecoProprietarioInput.setVisible(true);
				nomeProprietarioLabel.setVisible(true);
				nomeProprietarioInput.setVisible(true);
				cpfProprietarioLabel.setVisible(true);
				cpfProprietarioInput.setVisible(true);

				cadastrarProprietarioBotao.setText(JA_POSSUI_LOGIN);
			}

		}

		if (event.getSource() == logarBotao && "Cadastrar".equalsIgnoreCase(logarBotao.getText())) {
			try {
				ProprietarioModel proprietarioModel = new ProprietarioModel();
				LoginProprietarioModel loginProprietarioModel = new LoginProprietarioModel();

				proprietarioModel.setNome(nomeProprietarioInput.getText());
				proprietarioModel.setCpf(cpfProprietarioInput.getText().toString());
				proprietarioModel.setEndereco(enderecoProprietarioInput.getText());

				loginProprietarioModel.setLogin(loginProprietarioInput.getText());
				loginProprietarioModel.setSenha(senhaProprietarioInput.getText().toString());

				boolean statusCadastro = loginService.cadastrarLoginProprietario(proprietarioModel,
						loginProprietarioModel);

				if (statusCadastro) {
					Utils.exibirMensagem(loginProprietarioFrame, "Cadastro realizado com sucesso!", "Sucesso");
					loginProprietarioFrame.dispose();
					new LoginProprietario();
				} else {
					Utils.exibirMensagem(loginProprietarioFrame, "Erro na inclusão!", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(loginProprietarioFrame, "Erro na inclusão!", "Erro");
			}
		}
		if (event.getSource() == logarBotao && "Enviar".equalsIgnoreCase(logarBotao.getText())) {
			try {
				boolean statusCadastro = loginService.validarLogin(loginProprietarioInput.getText(), senhaProprietarioInput.getText().toString());

				if (statusCadastro) {
					loginProprietarioFrame.dispose();
					NivelAcesso.setTipoAcesso("Proprietario");
					new Menu();
				} else {
					Utils.exibirMensagem(loginProprietarioFrame, "Erro! Login ou senha incorretos", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(loginProprietarioFrame, "Erro! Problema no banco de dados", "Erro");
			}

		} else if(event.getSource() == cancelar) {
			if("Login Proprietario".equalsIgnoreCase(titulo.getText())) {
				loginProprietarioFrame.dispose();				
				new Inicio();
			} else {
				loginProprietarioFrame.dispose();				
				new LoginProprietario();
			}
		}
	}
}

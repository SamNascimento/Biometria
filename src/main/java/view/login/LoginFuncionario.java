package view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import config.NivelAcesso;
import service.FuncionarioService;
import util.Utils;
import view.Inicio;
import view.Menu;
import view.cadastrar.CadastrarFuncionario;
import viewmodel.BiometriaViewModel;

public class LoginFuncionario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;
	private static final String JA_POSSUI_LOGIN = "Já possui login? Logar-se";

	FuncionarioService funcionarioService = new FuncionarioService();

	String caminhoBiometriaValidacao;
	JButton biometriaValidacaoBotao, voltarTipoLogin, cadastrarFuncionarioBotao;
	JLabel titulo;
	JFileChooser escolherBiometria = new JFileChooser();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);
	Color transparente = new Color(0, 0, 0, 0);

	JFrame loginFuncionarioFrame = new JFrame("Identificação Biométrica");

	public LoginFuncionario() {
		loginFuncionarioFrame.setBounds(20, 20, 700, 300);
		loginFuncionarioFrame.setLayout(null);
		loginFuncionarioFrame.setResizable(false);
		loginFuncionarioFrame.setLocationRelativeTo(null);
		loginFuncionarioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titulo = new JLabel("Login Funcionario");
		titulo.setBounds(230, 10, 400, 50);
		titulo.setFont(font1);

		biometriaValidacaoBotao = new JButton("Buscar imagem biometrica para avaliação");
		biometriaValidacaoBotao.setBounds(185, 80, 320, 40);
		biometriaValidacaoBotao.setFont(font2);
		biometriaValidacaoBotao.addActionListener(this);

		cadastrarFuncionarioBotao = new JButton("Cadastro de novo funcionário");
		cadastrarFuncionarioBotao.setBounds(185, 130, 320, 40);
		cadastrarFuncionarioBotao.setFont(font2);
		cadastrarFuncionarioBotao.addActionListener(this);

		voltarTipoLogin = new JButton("Voltar");
		voltarTipoLogin.setBounds(310, 200, 50, 40);
		voltarTipoLogin.setForeground(Color.blue);
		voltarTipoLogin.setBackground(transparente);
		voltarTipoLogin.setBorder(new LineBorder(transparente));
		voltarTipoLogin.setOpaque(false);
		voltarTipoLogin.addActionListener(this);

		loginFuncionarioFrame.getContentPane().add(titulo);
		loginFuncionarioFrame.getContentPane().add(biometriaValidacaoBotao);
		loginFuncionarioFrame.getContentPane().add(cadastrarFuncionarioBotao);
		loginFuncionarioFrame.getContentPane().add(voltarTipoLogin);

		loginFuncionarioFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == biometriaValidacaoBotao) {
			BiometriaViewModel biometria = new BiometriaViewModel();
			escolherBiometria.showOpenDialog(null);
			caminhoBiometriaValidacao = escolherBiometria.getCurrentDirectory().getPath().concat("\\")
					.concat(escolherBiometria.getSelectedFile().getName());
			try {
				double threshold = 80;
				boolean matches = false;
				boolean encontrou = false;
				int index = 0;

				biometria = funcionarioService.buscarBiometriaAcesso();

				FingerprintTemplate biometriaValidacao = new FingerprintTemplate(
						new FingerprintImage(Files.readAllBytes(Paths.get(caminhoBiometriaValidacao)),
								new FingerprintImageOptions().dpi(500)));

				for (byte[] bs : biometria.getListaBiometria()) {
					FingerprintTemplate biometriaAcesso = new FingerprintTemplate(
							new FingerprintImage(bs, new FingerprintImageOptions().dpi(500)));

					double score = new FingerprintMatcher(biometriaValidacao).match(biometriaAcesso);
					matches = score >= threshold;

					if (matches) {

						for (int idFuncionario : biometria.getListaIdFuncionario()) {
							if (biometria.getListaIdFuncionario().get(index) == idFuncionario) {
								NivelAcesso.setIdFuncionario(idFuncionario);
								NivelAcesso.setTipoAcesso("Funcionario");
								NivelAcesso.setNivelAcessoFuncionario(funcionarioService.buscarAcessoFuncionario(idFuncionario));
								encontrou = true;
								loginFuncionarioFrame.dispose();
								new Menu();
								break;
							}
						}
					}
					
					index += 1;
					if(encontrou) {
						break;
					}
					
				}
				if(!encontrou) Utils.exibirMensagem(loginFuncionarioFrame, "Biometria inválida", "Erro");

			} catch (Exception e) {
				Utils.exibirMensagem(loginFuncionarioFrame, "Problemas com banco de dados", "Erro");
				System.out.println(e.getCause());
			}
		} else if (event.getSource() == cadastrarFuncionarioBotao) {
			loginFuncionarioFrame.dispose();
			new CadastrarFuncionario();
		} else if (event.getSource() == voltarTipoLogin) {
			loginFuncionarioFrame.dispose();
			new Inicio();
		}
	}
}

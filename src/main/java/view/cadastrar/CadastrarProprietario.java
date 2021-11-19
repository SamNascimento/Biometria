package view.cadastrar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.ProprietarioModel;
import service.ProprietarioService;
import util.Utils;

public class CadastrarProprietario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	JButton cadastrarPropietario, cancelar;
	JLabel titulo, nomePropietarioLabel, cpfPropietarioLabel, enderecoPropietarioLabel;
	JTextField nomePropietarioInput, cpfPropietarioInput, enderecoPropietarioInput;

	ProprietarioService proprietarioService = new ProprietarioService();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame cadastroFuncionarioFrame = new JFrame("Cadastrar Proprietário");

	public CadastrarProprietario() {
		cadastroFuncionarioFrame.setBounds(20, 20, 800, 300);
		cadastroFuncionarioFrame.setLayout(null);
		cadastroFuncionarioFrame.setResizable(false);
		cadastroFuncionarioFrame.setLocationRelativeTo(null);
		cadastroFuncionarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		titulo = new JLabel("Cadastro Proprietário");
		titulo.setBounds(10, 10, 400, 50);
		titulo.setFont(font1);

		nomePropietarioLabel = new JLabel("Digite nome do proprietario: ");
		nomePropietarioLabel.setBounds(10, 80, 200, 40);
		nomePropietarioLabel.setFont(font2);

		nomePropietarioInput = new JTextField();
		nomePropietarioInput.setBounds(10, 115, 150, 20);

		cpfPropietarioLabel = new JLabel("Digite cpf do proprietario: ");
		cpfPropietarioLabel.setBounds(210, 80, 200, 40);
		cpfPropietarioLabel.setFont(font2);

		cpfPropietarioInput = new JTextField();
		cpfPropietarioInput.setBounds(210, 115, 150, 20);

		enderecoPropietarioLabel = new JLabel("Digite seu endereço: ");
		enderecoPropietarioLabel.setBounds(400, 80, 200, 40);
		enderecoPropietarioLabel.setFont(font2);

		enderecoPropietarioInput = new JTextField();
		enderecoPropietarioInput.setBounds(400, 115, 200, 20);

		cadastrarPropietario = new JButton("Cadastrar");
		cadastrarPropietario.setBounds(30, 190, 100, 30);
		cadastrarPropietario.setFont(font2);
		cadastrarPropietario.addActionListener(this);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(150, 190, 100, 30);
		cancelar.setFont(font2);
		cancelar.addActionListener(this);

		cadastroFuncionarioFrame.getContentPane().add(titulo);
		cadastroFuncionarioFrame.getContentPane().add(nomePropietarioLabel);
		cadastroFuncionarioFrame.getContentPane().add(nomePropietarioInput);
		cadastroFuncionarioFrame.getContentPane().add(cpfPropietarioLabel);
		cadastroFuncionarioFrame.getContentPane().add(cpfPropietarioInput);
		cadastroFuncionarioFrame.getContentPane().add(enderecoPropietarioLabel);
		cadastroFuncionarioFrame.getContentPane().add(enderecoPropietarioInput);
		cadastroFuncionarioFrame.getContentPane().add(cadastrarPropietario);
		cadastroFuncionarioFrame.getContentPane().add(cancelar);
		cadastroFuncionarioFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cadastrarPropietario) {
			ProprietarioModel proprietarioModel = new ProprietarioModel();
			proprietarioModel.setNome(nomePropietarioInput.getText());
			proprietarioModel.setCpf(cpfPropietarioInput.getText());
			proprietarioModel.setEndereco(enderecoPropietarioInput.getText());
			
			try {
				boolean statusCadastro = proprietarioService.cadastrarProprietario(proprietarioModel);
				
				if(statusCadastro) {
					Utils.exibirMensagem(cadastroFuncionarioFrame,"Cadastrado com sucesso!", "Sucesso");
					Utils.limparCampos(cadastroFuncionarioFrame);
				}else {
					Utils.exibirMensagem(cadastroFuncionarioFrame,"Erro na inclusão!", "Erro");
				}
				
			} catch (Exception e) {
				Utils.exibirMensagem(cadastroFuncionarioFrame,"Erro na inclusão!", "Erro");
				e.printStackTrace();
			}
		} else if(event.getSource() == cancelar) {
			Utils.voltarParaMenu(cadastroFuncionarioFrame);
		}
	}

}

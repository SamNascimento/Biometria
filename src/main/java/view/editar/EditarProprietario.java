package view.editar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import config.NivelAcesso;
import model.ProprietarioModel;
import service.ProprietarioService;
import util.Utils;

public class EditarProprietario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	ProprietarioService proprietarioService = new ProprietarioService();

	JButton editarProprietario, cancelar;
	JLabel titulo, nomePropietarioLabel, cpfPropietarioLabel, enderecoPropietarioLabel;
	JTextField nomePropietarioInput, cpfPropietarioInput, enderecoPropietarioInput;

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame editarProprietarioFrame = new JFrame("Editar Perfil");

	public EditarProprietario() throws Exception {
		editarProprietarioFrame.setBounds(20, 20, 800, 300);
		editarProprietarioFrame.setLayout(null);
		editarProprietarioFrame.setResizable(false);
		editarProprietarioFrame.setLocationRelativeTo(null);
		editarProprietarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		titulo = new JLabel("Editar Perfil");
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

		editarProprietario = new JButton("Atualizar");
		editarProprietario.setBounds(10, 200, 100, 30);
		editarProprietario.setFont(font2);
		editarProprietario.addActionListener(this);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(130, 200, 100, 30);
		cancelar.setFont(font2);
		cancelar.addActionListener(this);
		
		setarDadosProprietario();

		editarProprietarioFrame.getContentPane().add(titulo);
		editarProprietarioFrame.getContentPane().add(nomePropietarioLabel);
		editarProprietarioFrame.getContentPane().add(nomePropietarioInput);
		editarProprietarioFrame.getContentPane().add(cpfPropietarioLabel);
		editarProprietarioFrame.getContentPane().add(cpfPropietarioInput);
		editarProprietarioFrame.getContentPane().add(enderecoPropietarioLabel);
		editarProprietarioFrame.getContentPane().add(enderecoPropietarioInput);
		editarProprietarioFrame.getContentPane().add(editarProprietario);
		editarProprietarioFrame.getContentPane().add(cancelar);
		editarProprietarioFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == editarProprietario) {
			ProprietarioModel proprietario = new ProprietarioModel();
			proprietario.setNome(nomePropietarioInput.getText());
			proprietario.setCpf(cpfPropietarioInput.getText());
			proprietario.setEndereco(enderecoPropietarioInput.getText());
			
			try {
				boolean status = proprietarioService.atualizarPerfil(proprietario);
				if(status) {
					Utils.exibirMensagem(editarProprietarioFrame, "Perfil alterado com sucesso!", "Sucesso");
				}else {
					Utils.exibirMensagem(editarProprietarioFrame, "Dados inválidos!", "Erro");
				}
			} catch (Exception e) {
				Utils.exibirMensagem(editarProprietarioFrame, "Problema com banco de dados!", "Erro");
				e.printStackTrace();
			}
			
		} else if (event.getSource() == cancelar) {
			Utils.voltarParaMenu(editarProprietarioFrame);
		}
	}

	private void setarDadosProprietario() throws Exception {
		ProprietarioModel proprietario =  proprietarioService.buscarProprietarioPorId(NivelAcesso.getIdProprietario());
		
		if(proprietario != null) {
			nomePropietarioInput.setText(proprietario.getNome());
			cpfPropietarioInput.setText(proprietario.getCpf());
			enderecoPropietarioInput.setText(proprietario.getEndereco());
		}
	}

}

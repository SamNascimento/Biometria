package view.cadastrar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import enums.StatusEnum;
import model.AgrotoxicoModel;
import service.AgrotoxicoService;
import util.Utils;

public class CadastrarAgrotoxico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	JButton cadastrarAgrotoxico, cancelar;
	JLabel titulo, nomeAgrotoxicoLabel, tipoAgrotoxicoLabel;
	JTextField nomeAgrotoxicoInput, tipoAgrotoxicoInput;

	AgrotoxicoService agrotoxicoService = new AgrotoxicoService();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame cadastroAgrotoxicoFrame = new JFrame("Cadastrar Agrotoxico");

	public CadastrarAgrotoxico() {
		cadastroAgrotoxicoFrame.setBounds(20, 20, 500, 300);
		cadastroAgrotoxicoFrame.setLayout(null);
		cadastroAgrotoxicoFrame.setResizable(false);
		cadastroAgrotoxicoFrame.setLocationRelativeTo(null);
		cadastroAgrotoxicoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		titulo = new JLabel("Cadastrar agrotoxico");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);

		nomeAgrotoxicoLabel = new JLabel("Digite nome do agrotoxico: ");
		nomeAgrotoxicoLabel.setBounds(30, 80, 200, 40);
		nomeAgrotoxicoLabel.setFont(font2);

		nomeAgrotoxicoInput = new JTextField();
		nomeAgrotoxicoInput.setBounds(30, 115, 150, 20);

		tipoAgrotoxicoLabel = new JLabel("Digite tipo agrotoxico: ");
		tipoAgrotoxicoLabel.setBounds(220, 80, 200, 40);
		tipoAgrotoxicoLabel.setFont(font2);

		tipoAgrotoxicoInput = new JTextField();
		tipoAgrotoxicoInput.setBounds(220, 115, 150, 20);

		cadastrarAgrotoxico = new JButton("Cadastrar");
		cadastrarAgrotoxico.setBounds(30, 190, 100, 30);
		cadastrarAgrotoxico.setFont(font2);
		cadastrarAgrotoxico.addActionListener(this);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(150, 190, 100, 30);
		cancelar.setFont(font2);
		cancelar.addActionListener(this);

		cadastroAgrotoxicoFrame.getContentPane().add(titulo);
		cadastroAgrotoxicoFrame.getContentPane().add(nomeAgrotoxicoLabel);
		cadastroAgrotoxicoFrame.getContentPane().add(nomeAgrotoxicoInput);
		cadastroAgrotoxicoFrame.getContentPane().add(tipoAgrotoxicoLabel);
		cadastroAgrotoxicoFrame.getContentPane().add(tipoAgrotoxicoInput);
		cadastroAgrotoxicoFrame.getContentPane().add(cadastrarAgrotoxico);
		cadastroAgrotoxicoFrame.getContentPane().add(cancelar);
		cadastroAgrotoxicoFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cadastrarAgrotoxico) {
			AgrotoxicoModel agrotoxico = new AgrotoxicoModel();
			agrotoxico.setNomeAgrotoxico(nomeAgrotoxicoInput.getText());
			agrotoxico.setStatus(StatusEnum.PENDENTE.getEnumNome());
			agrotoxico.setTipo(tipoAgrotoxicoInput.getText());

			try {
				boolean statusCadastro = agrotoxicoService.cadastrarAgrotoxico(agrotoxico);

				if (statusCadastro) {
					Utils.exibirMensagem(cadastroAgrotoxicoFrame, "Cadastrado com sucesso!", "Sucesso");
				} else {
					Utils.exibirMensagem(cadastroAgrotoxicoFrame, "Erro na inclusão!", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(cadastroAgrotoxicoFrame, "Erro na inclusão!", "Erro");
				e.printStackTrace();
			}
		} else if (event.getSource() == cancelar) {
			Utils.voltarParaMenu(cadastroAgrotoxicoFrame);
		}

	}

}

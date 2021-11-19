package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

public class ValidarBiometria extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	String primeiroCaminhoImagem;
	String segundoCaminhoImagem;
	String SegundoImagem;
	JButton primeiraDigitalButao, segundaDigitalBotao, compararBotao, porcetagemBotao, statusBotao;
	JLabel titulo, primeiraDigitalLabel, segundaDigitalLabel, porcetagemLabel, statusLabel;
	JLabel primeiraImagemSelecionada, segundaImagemSelecionada;
	JTextField porcetagemInput, statusInput;
	JFileChooser escolherPrimeiraImagem = new JFileChooser();
	ImageIcon primeiraImagem = new ImageIcon();
	ImageIcon SegundaImagem = new ImageIcon();
	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame menu = new JFrame("Indentificação Biométrica");

	public ValidarBiometria() {
		menu.setBounds(20, 20, 800, 800);
		menu.setLayout(null);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titulo = new JLabel("Indentificação Biométrica");
		titulo.setBounds(225, 10, 400, 50);
		titulo.setFont(font1);

		primeiraImagemSelecionada = new JLabel();
		primeiraImagemSelecionada.setBounds(115, 80, 240, 336);
		primeiraImagemSelecionada.setFont(font2);

		segundaImagemSelecionada = new JLabel();
		segundaImagemSelecionada.setBounds(450, 80, 240, 336);
		segundaImagemSelecionada.setFont(font2);

		primeiraDigitalButao = new JButton("Selecionar primeira imagem");
		primeiraDigitalButao.setBounds(117, 430, 230, 40);
		primeiraDigitalButao.setFont(font2);
		primeiraDigitalButao.addActionListener(this);

		segundaDigitalBotao = new JButton("Selecionar segunda imagem");
		segundaDigitalBotao.setBounds(447, 430, 230, 40);
		segundaDigitalBotao.setFont(font2);
		segundaDigitalBotao.addActionListener(this);

		compararBotao = new JButton("Comparar");
		compararBotao.setBounds(290, 500, 200, 40);
		compararBotao.setFont(font);
		compararBotao.addActionListener(this);

		porcetagemLabel = new JLabel("Porcetagem %");
		porcetagemLabel.setBounds(237, 570, 200, 40);
		porcetagemLabel.setFont(font2);

		porcetagemInput = new JTextField();
		porcetagemInput.setBounds(237, 600, 150, 20);

		statusLabel = new JLabel("Status");
		statusLabel.setBounds(417, 570, 200, 40);
		statusLabel.setFont(font2);

		statusInput = new JTextField();
		statusInput.setBounds(417, 600, 150, 20);

		menu.getContentPane().add(titulo);
		menu.getContentPane().add(primeiraImagemSelecionada);
		menu.getContentPane().add(segundaImagemSelecionada);
		menu.getContentPane().add(primeiraDigitalButao);
		menu.getContentPane().add(segundaDigitalBotao);
		menu.getContentPane().add(compararBotao);
		menu.getContentPane().add(porcetagemLabel);
		menu.getContentPane().add(porcetagemInput);
		menu.getContentPane().add(statusLabel);
		menu.getContentPane().add(statusInput);
//        menu.getContentPane().add(primeiraDigitalLabel);
//        menu.getContentPane().add(segundaDigitalLabel);

		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == primeiraDigitalButao) {
			int imagemSelecionada = escolherPrimeiraImagem.showOpenDialog(null);
			primeiraImagem = setarImagemLabel(imagemSelecionada, primeiraImagemSelecionada, "primeira");

		} else if (event.getSource() == segundaDigitalBotao) {
			int imagemSelecionada = escolherPrimeiraImagem.showOpenDialog(null);
			SegundaImagem = setarImagemLabel(imagemSelecionada, segundaImagemSelecionada, "segunda");

		} else if (event.getSource() == compararBotao) {
			try {

				FingerprintTemplate primeiroDedoTemplate = new FingerprintTemplate(new FingerprintImage(
						Files.readAllBytes(Paths.get(primeiroCaminhoImagem)), new FingerprintImageOptions().dpi(500)));

				FingerprintTemplate segundoDedoTemplate = new FingerprintTemplate(new FingerprintImage(
						Files.readAllBytes(Paths.get(segundoCaminhoImagem)), new FingerprintImageOptions().dpi(500)));
				
				double score = new FingerprintMatcher(primeiroDedoTemplate).match(segundoDedoTemplate);
				double threshold = 40;
				boolean matches = score >= threshold;	
				
				if(matches) {
					statusInput.setText("Biometria válida");
					statusInput.setForeground(Color.green);
					
				} else {
					statusInput.setText("Biometria inválida");
					statusInput.setForeground(Color.red);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private ImageIcon setarImagemLabel(int imagemSelecionada, JLabel labelImage, String selecaoImagem) {
		ImageIcon image = null;
		labelImage.setIcon(null);

		if (imagemSelecionada == JFileChooser.APPROVE_OPTION) {
			if(selecaoImagem.equalsIgnoreCase("primeira")) {
				labelImage.setIcon(null);
				primeiroCaminhoImagem = escolherPrimeiraImagem.getCurrentDirectory().getPath().concat("\\").concat(escolherPrimeiraImagem.getSelectedFile().getName());				
				image = new ImageIcon(primeiroCaminhoImagem);
			} else if(selecaoImagem.equalsIgnoreCase("segunda")){
				labelImage.setIcon(null);
				segundoCaminhoImagem = escolherPrimeiraImagem.getCurrentDirectory().getPath().concat("\\").concat(escolherPrimeiraImagem.getSelectedFile().getName());				
				image = new ImageIcon(segundoCaminhoImagem);
			}
			
			statusInput.setText("Carregando...");
			statusInput.setForeground(Color.black);
			
			labelImage.setIcon(image);
			labelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			labelImage.setVerticalTextPosition(SwingConstants.CENTER);

		}

		return image;
	}

}

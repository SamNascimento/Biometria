package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.login.LoginFuncionario;
import view.login.LoginProprietario;

public class Inicio extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;
	
	JButton loginFuncionario, loginProprietario;
	JLabel titulo;

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Tahoma", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame loginFrame = new JFrame("Login");

	public Inicio() {
		loginFrame.setBounds(20, 20, 700, 300);
		loginFrame.setLayout(null);
		loginFrame.setResizable(false);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titulo = new JLabel("Escolha o método de login");
		titulo.setBounds(150, 10, 400, 50);
		titulo.setFont(font1);

		loginFuncionario = new JButton("Funcionário");
		loginFuncionario.setBounds(150, 130, 150, 40);
		loginFuncionario.setFont(font2);
		loginFuncionario.addActionListener(this);

		loginProprietario = new JButton("Proprietário");
		loginProprietario.setBounds(350, 130, 150, 40);
		loginProprietario.setFont(font2);
		loginProprietario.addActionListener(this);

		loginFrame.getContentPane().add(titulo);
		loginFrame.getContentPane().add(loginFuncionario);
		loginFrame.getContentPane().add(loginProprietario);

		loginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == loginFuncionario) {
			new LoginFuncionario();
			loginFrame.dispose();
		} else if(event.getSource() == loginProprietario) {
			new LoginProprietario();
			loginFrame.dispose();
		}
	}
}

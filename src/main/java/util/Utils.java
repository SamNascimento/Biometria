package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

public class Utils {

	public static void exibirMensagem(JFrame frame, String mensagem, String tipo) {
		JOptionPane.showMessageDialog(frame, mensagem, tipo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void voltarParaMenu(JFrame frame) {
		frame.dispose();
	}

	public static void limparCampos(JFrame frame) {
		for (int i = 0; i < frame.getContentPane().getComponentCount(); i++) {

			Component c = (Component) frame.getContentPane().getComponent(i);

			if (c instanceof JTextField) {
				// apaga os valores
				JTextField field = (JTextField) c;
				field.setText("");
			}
		}
	}

}

package view.consultar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import config.NivelAcesso;
import model.AgrotoxicoModel;
import model.ProprietarioModel;
import model.tabela.AgrotoxicoTableModel;
import service.AgrotoxicoService;
import service.ProprietarioService;
import util.Utils;

public class ConsultarAgrotoxico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	private AgrotoxicoService agrotoxicoService = new AgrotoxicoService();
	private ProprietarioService proprietarioService = new ProprietarioService();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JPanel painelFundo;
	JTable tabela;
	JScrollPane scrollPane;
	JButton voltar;
	JLabel titulo;

	JFrame consultarAgrotoxicoFrame = new JFrame("Consultar Funcionário");

	public ConsultarAgrotoxico() throws Exception {
		consultarAgrotoxicoFrame.setBounds(20, 20, 800, 500);
		consultarAgrotoxicoFrame.setLayout(null);
		consultarAgrotoxicoFrame.setResizable(false);
		consultarAgrotoxicoFrame.setLocationRelativeTo(null);
		consultarAgrotoxicoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		painelFundo = new JPanel();
		AgrotoxicoTableModel agrotoxicoTableModel = new AgrotoxicoTableModel();

		tabela = new JTable();
		tabela.setModel(agrotoxicoTableModel);
		agrotoxicoTableModel.addListaDeProprietarios(setListaAgrotoxicos());

		scrollPane = new JScrollPane(tabela);
		tabela.setFillsViewportHeight(true);
		tabela.setBounds(20, 20, 800, 500);

		painelFundo.add(scrollPane);
		painelFundo.setBounds(20, 120, 750, 300);
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.setForeground(Color.red);

		titulo = new JLabel("Consultar Agrotoxico");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);

		voltar = new JButton("Voltar");
		voltar.setBounds(670, 25, 100, 30);
		voltar.setFont(font2);
		voltar.addActionListener(this);

		consultarAgrotoxicoFrame.getContentPane().add(titulo);
		consultarAgrotoxicoFrame.getContentPane().add(painelFundo);
		consultarAgrotoxicoFrame.getContentPane().add(voltar);
		consultarAgrotoxicoFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == voltar) {
			Utils.voltarParaMenu(consultarAgrotoxicoFrame);
		}
	}

	private List<AgrotoxicoModel> setListaAgrotoxicos() throws Exception {
		List<AgrotoxicoModel> listaAgrotoxico = new ArrayList<AgrotoxicoModel>();
		List<ProprietarioModel> listaProprietario = proprietarioService.buscarProprietarios();
		
		if("Funcionario".equals(NivelAcesso.getTipoAcesso())) {
			listaAgrotoxico = agrotoxicoService.buscarAgrotoxicos();
		} else {
			 listaAgrotoxico = agrotoxicoService.buscarAgrotoxicosPorId(NivelAcesso.getIdProprietario());
		}
		
		int index = 0;
		for (AgrotoxicoModel agrotoxicoModel : listaAgrotoxico) {
			for (int proprietarioIndex = 0; proprietarioIndex < listaProprietario.size(); proprietarioIndex++) {
				if (agrotoxicoModel.getIdProprietario() == listaProprietario.get(proprietarioIndex).getIdProprietario()) {
					listaAgrotoxico.get(index).setNomeProprietario(listaProprietario.get(proprietarioIndex).getNome());
				}
			}
			index += 1;
		}
		
		return listaAgrotoxico;
	}

}

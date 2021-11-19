package view.funcionario;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import enums.StatusEnum;
import model.AgrotoxicoModel;
import model.ProprietarioModel;
import model.tabela.AprovarAgrotoxicoTableModel;
import service.AgrotoxicoService;
import service.ProprietarioService;
import util.Utils;
import viewmodel.AprovarAgrotoxicoViewModel;

public class AprovarAgrotoxico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;

	private AgrotoxicoService agrotoxicoService = new AgrotoxicoService();
	private ProprietarioService proprietarioService = new ProprietarioService();

	private List<AprovarAgrotoxicoViewModel> listaAgrotixicosViewModel = new ArrayList<AprovarAgrotoxicoViewModel>();

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JPanel painelFundo;
	JTable tabela;
	JScrollPane scrollPane;
	JButton voltar, enviar, filtrar;
	JLabel titulo, idAgrotoxicoLabel, nomeProprietarioLabel, nomeAgrotoxicoLabel, statusAgrotoxicoLabel, filtrarTitulo;
	JTextField idAgrotoxicoInput;
	JComboBox<Object> statusSelect, statusFiltro;

	String statusItem[] = { "Aprovado", "Reprovado" };
	String filtroOptions[] = {"Aprovado", "Reprovado", "Pendente", "Proprietario"};

	JFrame consultarAgrotoxicoFrame = new JFrame("Aprovar agrotóxico");
	AprovarAgrotoxicoTableModel agrotoxicoTableModel = new AprovarAgrotoxicoTableModel();

	public AprovarAgrotoxico() throws Exception {
		consultarAgrotoxicoFrame.setBounds(20, 20, 1000, 700);
		consultarAgrotoxicoFrame.setLayout(null);
		consultarAgrotoxicoFrame.setResizable(false);
		consultarAgrotoxicoFrame.setLocationRelativeTo(null);
		consultarAgrotoxicoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		titulo = new JLabel("Aprovar agrotóxico");
		titulo.setBounds(20, 10, 400, 50);
		titulo.setFont(font1);

		voltar = new JButton("Voltar");
		voltar.setBounds(870, 25, 100, 30);
		voltar.setFont(font2);
		voltar.addActionListener(this);

		idAgrotoxicoLabel = new JLabel("Id agrotoxico");
		idAgrotoxicoLabel.setBounds(20, 70, 180, 50);

		idAgrotoxicoInput = new JTextField();
		idAgrotoxicoInput.setBounds(20, 110, 100, 20);
		idAgrotoxicoInput.addKeyListener(keyListener);

		statusSelect = new JComboBox<Object>(statusItem);
		statusSelect.setBounds(130, 110, 100, 20);

		enviar = new JButton("Enviar");
		enviar.setBounds(250, 110, 80, 20);
		enviar.addActionListener(this);

		nomeProprietarioLabel = new JLabel();
		nomeProprietarioLabel.setBounds(20, 140, 150, 20);

		nomeAgrotoxicoLabel = new JLabel();
		nomeAgrotoxicoLabel.setBounds(20, 160, 100, 20);

		statusAgrotoxicoLabel = new JLabel();
		statusAgrotoxicoLabel.setBounds(20, 180, 100, 20);

		filtrarTitulo = new JLabel("Filtrar por:");
		filtrarTitulo.setBounds(20, 210, 130, 20);
		filtrarTitulo.setFont(font2);

		statusFiltro = new JComboBox<Object>(filtroOptions);
		statusFiltro.setBounds(20, 240, 100, 20);

		filtrar = new JButton("Filtrar");
		filtrar.setBounds(130, 240, 80, 20);
		filtrar.addActionListener(this);

		painelFundo = new JPanel();

		tabela = new JTable();
		tabela.setModel(agrotoxicoTableModel);
		agrotoxicoTableModel.addListaDeProprietarios(preencherTabela());

		scrollPane = new JScrollPane(tabela);
		tabela.setFillsViewportHeight(true);
		tabela.setBounds(20, 20, 800, 500);

		painelFundo.add(scrollPane);
		painelFundo.setBounds(20, 300, 950, 300);
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.setForeground(Color.red);

		consultarAgrotoxicoFrame.getContentPane().add(titulo);
		consultarAgrotoxicoFrame.getContentPane().add(idAgrotoxicoLabel);
		consultarAgrotoxicoFrame.getContentPane().add(idAgrotoxicoInput);
		consultarAgrotoxicoFrame.getContentPane().add(statusSelect);
		consultarAgrotoxicoFrame.getContentPane().add(enviar);
		consultarAgrotoxicoFrame.getContentPane().add(nomeProprietarioLabel);
		consultarAgrotoxicoFrame.getContentPane().add(nomeAgrotoxicoLabel);
		consultarAgrotoxicoFrame.getContentPane().add(statusAgrotoxicoLabel);
		consultarAgrotoxicoFrame.getContentPane().add(filtrarTitulo);
		consultarAgrotoxicoFrame.getContentPane().add(statusFiltro);
		consultarAgrotoxicoFrame.getContentPane().add(filtrar);
		consultarAgrotoxicoFrame.getContentPane().add(painelFundo);
		consultarAgrotoxicoFrame.getContentPane().add(voltar);
		consultarAgrotoxicoFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == enviar) {
			int status = StatusEnum.status(statusSelect.getSelectedItem().toString());
			int idAgrotoxico = Integer.parseInt(idAgrotoxicoInput.getText());

			try {
				boolean statusQuery = agrotoxicoService.aprovarAgrotoxico(status, idAgrotoxico);

				if (statusQuery == true) {
					agrotoxicoTableModel.limpar();
					agrotoxicoTableModel.addListaDeProprietarios(preencherTabela());
					Utils.exibirMensagem(consultarAgrotoxicoFrame, "Aprovação realizada com sucesso", "Sucesso");
				} else {
					Utils.exibirMensagem(consultarAgrotoxicoFrame, "Id não encontrado", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(consultarAgrotoxicoFrame, "Problema com banco de dados", "Erro");
				e.printStackTrace();
			}
		} else if (event.getSource() == filtrar) {
			List<AprovarAgrotoxicoViewModel> agrotoxicosFiltro = new ArrayList<AprovarAgrotoxicoViewModel>();
			String filtro = statusFiltro.getSelectedItem().toString();

			if (filtro.equalsIgnoreCase("Aprovado") || filtro.equalsIgnoreCase("Reprovado") || filtro.equalsIgnoreCase("Pendente")) {
				agrotoxicosFiltro = listaAgrotixicosViewModel.stream()
						.filter(agrotoxico -> agrotoxico.getStatus().equals(filtro)).collect(Collectors.toList());
			} else if (filtro.equals("Proprietario")) {
				agrotoxicosFiltro = listaAgrotixicosViewModel.stream()
						.sorted((proprietario1, proprietario2) -> proprietario1.getNomeProprietario().compareTo(proprietario2.getNomeProprietario()))
						.collect(Collectors.toList());
			} else {
				agrotoxicosFiltro = listaAgrotixicosViewModel;
			}
			
			agrotoxicoTableModel.limpar();
			agrotoxicoTableModel.addListaDeProprietarios(agrotoxicosFiltro);

		} else if (event.getSource() == voltar) {
			Utils.voltarParaMenu(consultarAgrotoxicoFrame);
		}
	}

	private List<AprovarAgrotoxicoViewModel> preencherTabela() throws Exception {
		listaAgrotixicosViewModel.clear();
		List<AgrotoxicoModel> listaAgrotoxicos = agrotoxicoService.buscarAgrotoxicos();
		List<ProprietarioModel> listaProprietario = proprietarioService.buscarProprietarios();

		for (ProprietarioModel proprietarioModel : listaProprietario) {

			for (AgrotoxicoModel agrotoxicoModel : listaAgrotoxicos) {
				if (proprietarioModel.getIdProprietario() == agrotoxicoModel.getIdProprietario()) {
					AprovarAgrotoxicoViewModel agrotoxicoViewModel = new AprovarAgrotoxicoViewModel();

					agrotoxicoViewModel.setIdAgrotoxico(String.valueOf(agrotoxicoModel.getIdAgrotoxico()));
					agrotoxicoViewModel.setNomeAgrotoxico(agrotoxicoModel.getNomeAgrotoxico());
					agrotoxicoViewModel.setNomeProprietario(proprietarioModel.getNome());
					agrotoxicoViewModel.setStatus(StatusEnum.status(agrotoxicoModel.getStatus()));

					listaAgrotixicosViewModel.add(agrotoxicoViewModel);
				}
			}
		}

		return listaAgrotixicosViewModel;
	}

	KeyListener keyListener = new KeyListener() {
		public void keyPressed(KeyEvent keyEvent) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyChar()) != "Backspace") {
				int idAgrotoxicoDigitado = Integer.parseInt(KeyEvent.getKeyText(e.getKeyChar()));
				boolean encontrouId = false;
				for (AprovarAgrotoxicoViewModel aprovarAgrotoxicoViewModel : listaAgrotixicosViewModel) {
					int idAgrotoxico = Integer.parseInt(aprovarAgrotoxicoViewModel.getIdAgrotoxico());

					if (idAgrotoxicoDigitado == idAgrotoxico) {
						nomeProprietarioLabel.setText(aprovarAgrotoxicoViewModel.getNomeProprietario());
						nomeAgrotoxicoLabel.setText(aprovarAgrotoxicoViewModel.getNomeAgrotoxico());
						statusAgrotoxicoLabel.setText(aprovarAgrotoxicoViewModel.getStatus());
						encontrouId = true;
						break;
					}
				}

				if (!encontrouId)
					Utils.exibirMensagem(consultarAgrotoxicoFrame, "Id não encontrado", "Erro");
			}
		}
	};

}

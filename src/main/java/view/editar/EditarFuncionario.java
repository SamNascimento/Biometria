package view.editar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.FuncionarioModel;
import model.tabela.EditarFuncionarioTableModel;
import service.FuncionarioService;
import util.Utils;

public class EditarFuncionario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 867099008028671483L;
	
	EditarFuncionarioTableModel funcionarioTableModel = new EditarFuncionarioTableModel();
	FuncionarioService funcionarioService = new FuncionarioService();
	FuncionarioModel funcionarioModel = new FuncionarioModel();
	List<FuncionarioModel> listaFuncionarios = funcionarioService.buscarFuncionario();

	String caminhoImagem;

	JButton atualizarFuncionario, pesquisar, voltar;
	JLabel titulo, nomeFuncionarioLabel, cpfFuncionarioLabel, nivelAcessoLabel, pesquisarFuncionarioId;
	JTextField nomeUsuarioInput, cpfUsuarioInput, idFuncionarioInput;
	ButtonGroup nivelAcesso;
	JRadioButton nivelAcesso1, nivelAcesso2, nivelAcesso3;
	JPanel painelFundo;
	JTable tabela;
	JScrollPane scrollPane;

	Font font = new Font("Arial", Font.PLAIN, 22);
	Font font1 = new Font("Arial", Font.BOLD, 28);
	Font font2 = new Font("Arial", Font.PLAIN, 15);

	JFrame editarFuncionarioFrame = new JFrame("Editar Funcionário");

	public EditarFuncionario() throws Exception {
		editarFuncionarioFrame.setBounds(20, 20, 1000, 600);
		editarFuncionarioFrame.setLayout(null);
		editarFuncionarioFrame.setResizable(false);
		editarFuncionarioFrame.setLocationRelativeTo(null);
		editarFuncionarioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titulo = new JLabel("Editar Funcionário");
		titulo.setBounds(10, 10, 400, 50);
		titulo.setFont(font1);

		voltar = new JButton("Voltar");
		voltar.setBounds(860, 10, 100, 30);
		voltar.setFont(font2);
		voltar.addActionListener(this);

		pesquisarFuncionarioId = new JLabel("Digite o id do Funcionário");
		pesquisarFuncionarioId.setBounds(10, 50, 400, 50);

		idFuncionarioInput = new JTextField();
		idFuncionarioInput.setBounds(10, 90, 100, 25);

		pesquisar = new JButton("Pesquisar");
		pesquisar.setBounds(120, 90, 110, 25);
		pesquisar.addActionListener(this);

		nomeFuncionarioLabel = new JLabel("Digite nome do usuário: ");
		nomeFuncionarioLabel.setBounds(10, 120, 200, 40);
		nomeFuncionarioLabel.setFont(font2);

		nomeUsuarioInput = new JTextField();
		nomeUsuarioInput.setBounds(10, 150, 150, 20);

		cpfFuncionarioLabel = new JLabel("Digite cpf do usuário");
		cpfFuncionarioLabel.setBounds(180, 120, 200, 40);
		cpfFuncionarioLabel.setFont(font2);

		cpfUsuarioInput = new JTextField();
		cpfUsuarioInput.setBounds(180, 150, 150, 20);

		nivelAcessoLabel = new JLabel("Selecione nivel de acesso: ");
		nivelAcessoLabel.setBounds(350, 120, 200, 40);
		nivelAcessoLabel.setFont(font2);

		nivelAcesso1 = new JRadioButton("1");
		nivelAcesso1.setBounds(350, 150, 35, 20);

		nivelAcesso2 = new JRadioButton("2");
		nivelAcesso2.setBounds(385, 150, 35, 20);

		nivelAcesso3 = new JRadioButton("3");
		nivelAcesso3.setBounds(420, 150, 35, 20);

		atualizarFuncionario = new JButton("Atualizar");
		atualizarFuncionario.setBounds(560, 130, 100, 30);
		atualizarFuncionario.setFont(font2);
		atualizarFuncionario.addActionListener(this);

		nivelAcesso = new ButtonGroup();
		nivelAcesso.add(nivelAcesso1);
		nivelAcesso.add(nivelAcesso2);
		nivelAcesso.add(nivelAcesso3);

		painelFundo = new JPanel();

		tabela = new JTable(funcionarioTableModel);
		tabela.setModel(funcionarioTableModel);
		funcionarioTableModel.addListaDeProprietarios(listaFuncionarios);

		scrollPane = new JScrollPane(tabela);
		tabela.setFillsViewportHeight(true);
		tabela.setBounds(20, 20, 800, 500);

		painelFundo.add(scrollPane);
		painelFundo.setBounds(10, 190, 950, 300);
		painelFundo.setLayout(new GridLayout(1, 1));
		painelFundo.setForeground(Color.red);

		editarFuncionarioFrame.getContentPane().add(titulo);
		editarFuncionarioFrame.getContentPane().add(pesquisarFuncionarioId);
		editarFuncionarioFrame.getContentPane().add(idFuncionarioInput);
		editarFuncionarioFrame.getContentPane().add(pesquisar);
		editarFuncionarioFrame.getContentPane().add(nomeFuncionarioLabel);
		editarFuncionarioFrame.getContentPane().add(nomeUsuarioInput);
		editarFuncionarioFrame.getContentPane().add(cpfFuncionarioLabel);
		editarFuncionarioFrame.getContentPane().add(cpfUsuarioInput);
		editarFuncionarioFrame.getContentPane().add(nivelAcessoLabel);
		editarFuncionarioFrame.getContentPane().add(nivelAcesso1);
		editarFuncionarioFrame.getContentPane().add(nivelAcesso2);
		editarFuncionarioFrame.getContentPane().add(nivelAcesso3);
		editarFuncionarioFrame.getContentPane().add(painelFundo);
		editarFuncionarioFrame.getContentPane().add(atualizarFuncionario);
		editarFuncionarioFrame.getContentPane().add(voltar);
		editarFuncionarioFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == atualizarFuncionario) {
			try {
				funcionarioModel.setIdFuncionario(Integer.parseInt(idFuncionarioInput.getText()));
				funcionarioModel.setNome(nomeUsuarioInput.getText());
				funcionarioModel.setCpf(cpfUsuarioInput.getText());

				for (Enumeration<?> buttons = nivelAcesso.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = (AbstractButton) buttons.nextElement();

					if (button.isSelected()) {
						funcionarioModel.setNivelAcesso(Integer.parseInt(button.getText()));
						break;
					}
				}

				boolean statusCadastro = funcionarioService.atualizarFuncionario(funcionarioModel);

				if (statusCadastro) {
					funcionarioTableModel.limpar();
					funcionarioTableModel.addListaDeProprietarios(funcionarioService.buscarFuncionario());
					Utils.exibirMensagem(editarFuncionarioFrame, "Atualizado com sucesso!", "Sucesso");
					
				} else {
					Utils.exibirMensagem(editarFuncionarioFrame, "Erro na inclusão!", "Erro");
				}

			} catch (Exception e) {
				Utils.exibirMensagem(editarFuncionarioFrame, "Erro na inclusão!", "Erro");
				e.printStackTrace();
			}
			Utils.voltarParaMenu(editarFuncionarioFrame);
		} else if (event.getSource() == pesquisar) {
			int idFuncionario = Integer.parseInt(idFuncionarioInput.getText());
			listaFuncionarios.stream().filter(funcionario -> funcionario.getIdFuncionario() == idFuncionario)
					.collect(Collectors.toList());

			for (FuncionarioModel funcionarioModel : listaFuncionarios) {
				nomeUsuarioInput.setText(funcionarioModel.getNome());
				cpfUsuarioInput.setText(funcionarioModel.getCpf());

				switch (funcionarioModel.getNivelAcesso()) {
				case 1:
					nivelAcesso1.setSelected(true);
					break;
				case 2:
					nivelAcesso2.setSelected(true);
					break;
				case 3:
					nivelAcesso3.setSelected(true);
					break;
				default:
					break;
				}

			}

		}else if (event.getSource() == voltar) { 
			Utils.voltarParaMenu(editarFuncionarioFrame);
		}

	}

}

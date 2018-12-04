package tela;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import repositorios.ProdutoRepositorio;
import classes.Produto;
import classes.Tipo;

public class CadastroProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfcod;
	private JTextField jtfNomeProduto;
	private JTextField jtfValor;

	private JButton jbtCadastrar;
	private JButton jbtRemover;
	private JButton jbtInicio;

	private JTable jtTabela;
	private JScrollPane jspRolagem; // BARA DE ROLAGEM
	private DefaultTableModel dtmProduto;

	private JLabel jlbCodigoProduto;
	private JLabel jlbDeletarProduto;
	private JLabel jlbValor;
	private JLabel jlbNomeProduto;
	private JComboBox<String> jcbTipo;

	// CAIXAS DE TEXTOS
	public void CamposTextos() {
		

		jtfcod = new JTextField();
		jtfcod.setBounds(20, 44, 200, 20);
		getContentPane().add(jtfcod);

		jtfNomeProduto = new JTextField();
		jtfNomeProduto.setBounds(20, 100, 200, 20);
		getContentPane().add(jtfNomeProduto);

		jtfValor = new JTextField();
		jtfValor.setBounds(20, 150, 200, 20);
		getContentPane().add(jtfValor);

	}
	// CAIXA DE TITULOS
	public void TitulosLabel() {
		
		jlbCodigoProduto = new JLabel("CODIGO PRODUTO:");
		jlbCodigoProduto.setBounds(23, 23, 198, 20);
		jlbCodigoProduto.setVisible(true);
		getContentPane().add(jlbCodigoProduto);

		jlbNomeProduto = new JLabel(" NOME PRODUTO:");
		jlbNomeProduto.setBounds(23, 75, 198, 20);
		jlbNomeProduto.setVisible(true);
		getContentPane().add(jlbNomeProduto);

		jlbValor = new JLabel("VALOR PRODUTO:");
		jlbValor.setBounds(23, 125, 198, 20);
		jlbValor.setVisible(true);
		getContentPane().add(jlbValor);

		jlbDeletarProduto = new JLabel(
				"SELECIONE O COD. PRODUTO A SER DELETADO:");
		jlbDeletarProduto.setBounds(23, 335, 300, 20);
		jlbDeletarProduto.setVisible(true);
		getContentPane().add(jlbDeletarProduto);
	}
    //ALIMENTA A TABELA DE PRODUTOS QUE FORMA CADASTRADOS
	public void ListaCadastrados() {

		dtmProduto = new DefaultTableModel();
		dtmProduto.addColumn("COD.");
		dtmProduto.addColumn("PRODUTO");
		dtmProduto.addColumn("VALOR");
		dtmProduto.addColumn("TIPO");

		jtTabela = new JTable(dtmProduto);
		jspRolagem = new JScrollPane(jtTabela);
		jspRolagem.setBounds(260, 10, 340, 300);
		jspRolagem.setBackground(Color.white);
		getContentPane().add(jspRolagem);

	}
	//COMBOX DO TIPO
	public void Tipo() {
		jcbTipo = new JComboBox<String>();
		jcbTipo.setBounds(20, 180, 200, 30);
		getContentPane().add(jcbTipo);
		jcbTipo.addItem("REFEICAO");
		jcbTipo.addItem("BEBIDA");
		jcbTipo.addItem("LANCHE");
		jcbTipo.addItem("SOBREMESA");
	}
	//AÇOES DO BOTAO
	public void Botoes() {
		jbtRemover = new JButton("EXCLUIR");
		jbtRemover.setBounds(20, 368, 100, 30);
		getContentPane().add(jbtRemover);
		jbtRemover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linha = jtTabela.getSelectedRow();
				Integer codigo = Integer.valueOf((String) dtmProduto
						.getValueAt(linha, 0));

				ProdutoRepositorio produtoRep = new ProdutoRepositorio();
				Produto produto = produtoRep.por(codigo);

				produtoRep.remover(produto);
				dtmProduto.removeRow(linha);

			}
		});

		jbtCadastrar = new JButton("CADASTRAR PRODUTO ");
		jbtCadastrar.setBounds(20, 220, 170, 30);
		getContentPane().add(jbtCadastrar);

		// AÇOES DO BOTÃO CADASTRAR
		jbtCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TESTA SE ESTÁ VAZIO
				if (jtfcod.getText().equals("")
						|| jtfNomeProduto.getText().equals("")
						|| jtfValor.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Por favor, preencha todos os campos !!!");
				} else {
					ProdutoRepositorio produtoRepositario = new ProdutoRepositorio();
					Produto produto = new Produto();

					// CADASTRAR PRODUTO
					produto.setCod(Integer.parseInt(jtfcod.getText()));
					produto.setNomeProduto(jtfNomeProduto.getText());
					produto.setValor(Double.parseDouble(jtfValor.getText()));
					
					//TESTA O ENUM CLASSE
					switch (jcbTipo.getSelectedIndex()) {
					case 0:
						produto.setTipo(Tipo.FEFEICAO);
						break;
					case 1:
						produto.setTipo(Tipo.BEBIDA);
						break;
					case 2:
						produto.setTipo(Tipo.LANCHE);
						break;
					case 3:
						produto.setTipo(Tipo.SOBREMESA);
						break;
					}
					produtoRepositario.salvar(produto);

					// LIMPAR TABELA
					dtmProduto.setNumRows(0);

					// LISTAR PRODUTOS
					for (Produto p : produtoRepositario.todos()) {
						dtmProduto.addRow(new String[] { p.getCod().toString(),
								p.getNomeProduto(), p.getValor().toString(),
								p.getTipo().toString() });
					}
					// LIMPAR CAMPOS
					jtfcod.setText("");
					jtfNomeProduto.setText("");
					jtfValor.setText("");
				}
			}
		});

		jbtInicio = new JButton("Voltar para o inicio");
		jbtInicio.setBounds(360, 368, 240, 30);
		getContentPane().add(jbtInicio);
		jbtInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Principal();
				dispose();

			}
		});

	}

	CadastroProduto() {

		setTitle("Montar Cardápio");
		setLayout(null);

		// COMPONENTES DO SISTEMA
		CamposTextos();
		TitulosLabel();
		ListaCadastrados();
		Botoes();
		Tipo();

		// LISTAR PRODUTOS
		ProdutoRepositorio produtoRep = new ProdutoRepositorio();
		for (Produto p : produtoRep.todos()) {
			dtmProduto.addRow(new String[] { p.getCod().toString(),
					p.getNomeProduto(), p.getValor().toString(),
					p.getTipo().toString() });
		}

		setSize(640, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new CadastroProduto();

	}

}

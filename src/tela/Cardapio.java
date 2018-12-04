package tela;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Tipo;
import repositorios.ProdutoRepositorio;
import classes.Produto;

public class Cardapio extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jpnRefeicao;
	private JPanel jpnLanche;
	private JPanel jpnBebida;
	private JPanel jpnSobremesa;
	private JTabbedPane abas;
	private DefaultTableModel dtmRefeicao;
	private JTable jtTRefeicao;
	private JScrollPane jspRefeicao;
	private DefaultTableModel dtmBebida;
	private JTable jtTBebidas;
	private DefaultTableModel dtmLanche;
	private DefaultTableModel dtmSobremesa;
	private JTable jtTSobremesa;
	private JTable jtTLanche;
	private JButton jbtAdicionar;
	private JButton jbtConcluido;
	private ProdutoRepositorio produtoRepositorio;
	private ArrayList<Produto> produtos;
	private JScrollPane jspLanche;
	private JScrollPane jspBebida;
	private JScrollPane jspSobremesa;

	public Container criaPainel() {
		// LISTAS CRIADOS PELO PEDIDO DO CLIENTE
		this.produtoRepositorio = new ProdutoRepositorio();

		// CRIA PAINEL DO FORMULARIO
		JPanel painel = new JPanel(new GridLayout(1, 1));

		// PAINEL DE
		// REFEICAO--------------------------------------------------------------------------------------------------
		jpnRefeicao = new JPanel(new BorderLayout());

		dtmRefeicao = new DefaultTableModel();
		dtmRefeicao.addColumn("COD.");
		dtmRefeicao.addColumn("PRODUTO");
		dtmRefeicao.addColumn("VALOR");

		// MOSTRA TODOS OS PRODUTO DO TIPO
		for (Produto p : produtoRepositorio.listarPor(Tipo.FEFEICAO)) {
			dtmRefeicao.addRow(new String[] { p.getCod().toString(),
					p.getNomeProduto(), p.getValor().toString() });
		}
		jtTRefeicao = new JTable(dtmRefeicao);
		jspRefeicao = new JScrollPane(jtTRefeicao);
		jspRefeicao.setBounds(20, 100, 340, 100);

		jbtAdicionar = new JButton("Adicionar");
		jbtAdicionar.setBounds(20, 180, 100, 40);
		jpnRefeicao.add(jbtAdicionar);

		jbtAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int linha = jtTRefeicao.getSelectedRow();
				// PEGA O CODO DO PEDIDO NA TELA DO CARDAPIO PARA DEPOIS VER SE
				// EXISTE NO BANCO
				Integer codigo = Integer.valueOf((String) dtmRefeicao
						.getValueAt(linha, 0));
				produtos.add(produtoRepositorio.por(codigo));

				JOptionPane.showMessageDialog(null, "Produto adicionado");
			}
		});

		jbtConcluido = new JButton("Concluído");
		jbtConcluido.setBounds(250, 180, 100, 40);
		jpnRefeicao.add(jbtConcluido);

		jbtConcluido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PedidoCliente(produtos);
				dispose();
			}
		});

		jpnRefeicao.add(jspRefeicao);

		// PAINEL DE
		// LANCHE----------------------------------------------------------------------------------------------------------------
		jpnLanche = new JPanel(new BorderLayout());

		dtmLanche = new DefaultTableModel();
		dtmLanche.addColumn("COD.");
		dtmLanche.addColumn("PRODUTO");
		dtmLanche.addColumn("VALOR");

		// ADICONA OS PRODUTOS DO TIPO
		for (Produto p : produtoRepositorio.listarPor(Tipo.LANCHE)) {
			dtmLanche.addRow(new String[] { p.getCod().toString(),
					p.getNomeProduto(), p.getValor().toString() });
		}
		jtTLanche = new JTable(dtmLanche);
		jspLanche = new JScrollPane(jtTLanche);
		jspLanche.setBounds(20, 320, 340, 100);

		// ACOES BOTAO
		jbtAdicionar = new JButton("Adicionar");
		jbtAdicionar.setBounds(20, 180, 100, 40);
		jpnLanche.add(jbtAdicionar);

		jbtAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = jtTLanche.getSelectedRow();

				// PEGA O CODO DO PEDIDO NA TELA DO CARDAPIO PARA DEPOIS VER SE
				// EXISTE NO BANCO
				Integer codigo = Integer.valueOf((String) dtmLanche.getValueAt(
						linha, 0));
				produtos.add(produtoRepositorio.por(codigo));
				JOptionPane.showMessageDialog(null, "Produto adicionado");
			}
		});

		jbtConcluido = new JButton("Concluído");
		jbtConcluido.setBounds(250, 180, 100, 40);
		jpnLanche.add(jbtConcluido);

		jbtConcluido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PedidoCliente(produtos);
				dispose();
			}
		});

		jpnLanche.add(jspLanche);

		// PAINEL DE
		// BEBIDA----------------------------------------------------------------------------------------------
		jpnBebida = new JPanel(new BorderLayout());

		dtmBebida = new DefaultTableModel();
		dtmBebida.addColumn("COD.");
		dtmBebida.addColumn("PRODUTO");
		dtmBebida.addColumn("VALOR");

		for (Produto p : produtoRepositorio.listarPor(Tipo.BEBIDA)) {
			dtmBebida.addRow(new String[] { p.getCod().toString(),
					p.getNomeProduto(), p.getValor().toString() });
		}
		jtTBebidas = new JTable(dtmBebida);
		jspBebida = new JScrollPane(jtTBebidas);
		jspBebida.setBounds(20, 180, 340, 100);

		// ACOES BOTAO
		jbtAdicionar = new JButton("Adicionar");
		jbtAdicionar.setBounds(20, 180, 100, 40);
		jpnBebida.add(jbtAdicionar);

		jbtAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = jtTBebidas.getSelectedRow();

				// PEGA O CODO DO PEDIDO NA TELA DO CARDAPIO PARA DEPOIS VER SE
				// EXISTE NO BANCO
				Integer codigo = Integer.valueOf((String) dtmBebida.getValueAt(
						linha, 0));
				produtos.add(produtoRepositorio.por(codigo));

				JOptionPane.showMessageDialog(null, "Produto adicionado");
			}
		});

		jbtConcluido = new JButton("Concluído");
		jbtConcluido.setBounds(250, 180, 100, 40);
		jpnBebida.add(jbtConcluido);

		jbtConcluido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PedidoCliente(produtos);
				dispose();
			}
		});

		jpnBebida.add(jspBebida);

		// PAINEL DE
		// SOBREMESA---------------------------------------------------------------------------------
		jpnSobremesa = new JPanel(new BorderLayout());

		dtmSobremesa = new DefaultTableModel();
		dtmSobremesa.addColumn("COD.");
		dtmSobremesa.addColumn("PRODUTO");
		dtmSobremesa.addColumn("VALOR");

		for (Produto p : produtoRepositorio.listarPor(Tipo.SOBREMESA)) {
			dtmSobremesa.addRow(new String[] { p.getCod().toString(),
					p.getNomeProduto(), p.getValor().toString() });
		}
		jtTSobremesa = new JTable(dtmSobremesa);
		jspSobremesa = new JScrollPane(jtTSobremesa);
		jspSobremesa.setBounds(20, 455, 340, 100);
		jpnSobremesa.add(jspSobremesa);

		// ACOES BOTAO
		jbtAdicionar = new JButton("Adicionar");
		jbtAdicionar.setBounds(20, 180, 100, 40);
		jpnSobremesa.add(jbtAdicionar);

		jbtAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = jtTSobremesa.getSelectedRow();

				// PEGA O CODO DO PEDIDO NA TELA DO CARDAPIO PARA DEPOIS VER SE
				// EXISTE NO BANCO
				Integer codigo = Integer.valueOf((String) dtmSobremesa
						.getValueAt(linha, 0));
				produtos.add(produtoRepositorio.por(codigo));
				JOptionPane.showMessageDialog(null, "Produto adicionado");
			}
		});

		jbtConcluido = new JButton("Concluído");
		jbtConcluido.setBounds(250, 180, 100, 40);
		jpnSobremesa.add(jbtConcluido);

		jbtConcluido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new PedidoCliente(produtos);
			}
		});
		jpnSobremesa.add(jspSobremesa);

		// CRIA ABAS
		abas = new JTabbedPane();
		// ADICIONA PAINEIS AS ABAS
		abas.addTab("REFEIÇOES", jpnRefeicao);
		abas.addTab("LANCHES", jpnLanche);
		abas.addTab("BEBIDAS", jpnBebida);
		abas.addTab("SOBREMESA", jpnSobremesa);

		// ADICIONA ABS AO PAINEL DO FORMULARIO
		painel.add(abas);
		return painel;
	}

	public Cardapio(ArrayList<Produto> produtos) {
		super("PEDIDO");
		this.produtos = produtos;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(criaPainel());
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {

		new Cardapio(null);
	}

}

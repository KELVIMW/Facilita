package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import repositorios.PedidoRepositorio;
import classes.Pedido;
import classes.Produto;

public class Garcom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jlbPorMesa;
	private JScrollPane jspRolagem;
	private DefaultTableModel dtmPedidos;
	private JTable jtPedidos;
	private JLabel jlbPedido;
	private JButton jbtPedido;
	private JComboBox<String> jcbTipo;
	private PedidoRepositorio pedidoRepositorio;
	private List<Pedido> pedidos;


	public void Label() {
		jlbPorMesa = new JLabel("PEDIDOS FEITO POR MESA:");
		jlbPorMesa.setBounds(20, 23, 300, 20);
		jlbPorMesa.setVisible(true);
		getContentPane().add(jlbPorMesa);

		jlbPedido = new JLabel("MESAS QUE CONTÉM PEDIDOS:");
		jlbPedido.setBounds(20, 265, 300, 30);
		jlbPedido.setVisible(true);
		getContentPane().add(jlbPedido);

	}

	public void Campos() {
		jcbTipo = new JComboBox<String>();
		jcbTipo.setBounds(260, 270, 100, 20);
		getContentPane().add(jcbTipo);

		// VERIFICA SE EXISTEM MESAS COM PEDIDOS NO BANCO
		pedidos = pedidoRepositorio.listaPedidosNaoConcluidos();
		if (!pedidos.isEmpty()) {
			pedidos.forEach(p -> jcbTipo.addItem(p.getMesa().toString()));
		} else {
			jcbTipo.addItem(":)");
		}
	}

	public void Tabela() {
		this.pedidoRepositorio = new PedidoRepositorio();
		dtmPedidos = new DefaultTableModel();
		dtmPedidos.addColumn("COD.");
		dtmPedidos.addColumn("PRODUTO");
		dtmPedidos.addColumn("TIPO");

		jtPedidos = new JTable(dtmPedidos);
		jspRolagem = new JScrollPane(jtPedidos);
		jspRolagem.setBounds(20, 50, 340, 200);
		getContentPane().add(jspRolagem);
	}

	public void Botoes() {
		jbtPedido = new JButton("MOSTRAR PEDIDO");
		jbtPedido.setBounds(170, 300, 190, 25);
		getContentPane().add(jbtPedido);
		jbtPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dtmPedidos.setNumRows(0);
				for (Produto p : pedidos.get(jcbTipo.getSelectedIndex())
						.getProdutos()) {
					dtmPedidos.addRow(new String[] { p.getCod().toString(),
							p.getNomeProduto(), p.getTipo().toString() });
				}
			}
		});

	}

	Garcom() {
		setTitle("Pedidos Prontos");
		getContentPane().setLayout(null);
		// COMPONENTES DO SISTEMA
		Label();
		Tabela();
		Campos();
		Botoes();
		setSize(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Garcom();
	}
}

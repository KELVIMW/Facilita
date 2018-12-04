package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import repositorios.PedidoRepositorio;
import classes.Pedido;
import classes.Produto;

public class Caixa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jlbPorMesa;
	private JScrollPane jspRolagem;
	private DefaultTableModel dtmPedidos;
	private JTable jtPedidos;
	private JLabel jlbPedido;
	private JButton jbtPedido;
	private JComboBox<String> jcbMesa;
	private PedidoRepositorio pedidoRepositorio;
	private List<Pedido> pedidos;
	private JTextField jtfTotal;
	private JButton jbtInicio;

	public void Label() {
		jlbPorMesa = new JLabel("TOTAL A PAGAR:");
		jlbPorMesa.setBounds(20, 290, 300, 20);
		jlbPorMesa.setVisible(true);
		getContentPane().add(jlbPorMesa);

		jlbPedido = new JLabel("MESAS QUE CONTÉM PEDIDOS:");
		jlbPedido.setBounds(20, 13, 300, 30);
		jlbPedido.setVisible(true);
		getContentPane().add(jlbPedido);

	}

	public void Campos() {
		jtfTotal = new JTextField();
		jtfTotal.setBounds(140, 290, 80, 20);
		getContentPane().add(jtfTotal);

		jcbMesa = new JComboBox<String>();
		jcbMesa.setBounds(20, 50, 100, 20);
		getContentPane().add(jcbMesa);

		// VERIFICA SE EXISTEM MESAS COM PEDIDOS NO BANCO
		pedidos = pedidoRepositorio.listaPedidosNaoConcluidos();
		if (!pedidos.isEmpty()) {
			pedidos.forEach(p -> jcbMesa.addItem(p.getMesa().toString()));
		} else {
			jcbMesa.addItem("Nenhum");
		}
	}

	public void Tabela() {
		this.pedidoRepositorio = new PedidoRepositorio();
		dtmPedidos = new DefaultTableModel();
		dtmPedidos.addColumn("COD.");
		dtmPedidos.addColumn("PRODUTO");
		dtmPedidos.addColumn("TIPO");
		dtmPedidos.addColumn("VALOR");

		jtPedidos = new JTable(dtmPedidos);
		jspRolagem = new JScrollPane(jtPedidos);
		jspRolagem.setBounds(20, 80, 340, 200);
		getContentPane().add(jspRolagem);
	}

	public void Botoes() {
		jbtPedido = new JButton("MOSTRAR PEDIDO");
		jbtPedido.setBounds(207, 50, 150, 20);
		getContentPane().add(jbtPedido);
		jbtPedido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//BUSCA NO BANCO O PEDIDO ESCOLHIDO ATRAVES DO COMBO BOX QUE LISTA TODAS
				//AS MESAS QUE CONTEM ALGUM PEDIDO
				List<Produto> produtos = pedidos
						.get(jcbMesa.getSelectedIndex()).getProdutos();
				dtmPedidos.setNumRows(0);
				for (Produto p : produtos) {
					dtmPedidos.addRow(new String[] { p.getCod().toString(),
							p.getNomeProduto(), p.getTipo().toString(),
							p.getValor().toString() });
				}
				jtfTotal.setText(getTotal(produtos).toString());
			}
		});

		jbtPedido = new JButton("PAGAR");
		jbtPedido.setBounds(257, 290, 100, 20);
		getContentPane().add(jbtPedido);
		jbtPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ATRIBUI A CLASSE PEDIDO NO OBJETO CONCLUIDO E ATUALIZA PARA TRUE
				//ESTÁ AÇÃO É RESPONSAVEL POR PASSAR TRUE AO PEDIDO SENDO ASSIM ELE NÃO E MAIS LISTADO PARA O CAIXA
				//A AÇÃO TRUE DIZ QUE FOI PAGA
				//QUANDO O CLIENTE GERA UM PEDIDO ESTE E CADASTRADO COM FALSE,  O METODO listaPedidosNaoConcluidos 
				//BUSCA TODOS OS PEDIDOS FALSE
				Pedido pedido = pedidoRepositorio.por(Integer.valueOf(jcbMesa
						.getSelectedItem().toString()));
				pedido.setConcluido(true);
				pedidoRepositorio.salvar(pedido);

				jcbMesa.removeItemAt(jcbMesa.getSelectedIndex());
				dtmPedidos.setNumRows(0);
				jtfTotal.setText("");

				JOptionPane.showMessageDialog(null, "Pagamento efetuado");

			}
		});
		
		jbtInicio = new JButton("VOLTAR PARA O INÍCIO");
		jbtInicio.setBounds(100, 330, 200, 20);
		getContentPane().add(jbtInicio);
		jbtInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Principal();
				dispose();
				
			}
		});
		

	}

	// METODO DE TOTAL
	public Double getTotal(List<Produto> produtos) {
		Double total = 0.0;
		if (!produtos.isEmpty()) {
			for (Produto p : produtos) {
				total += p.getValor();
			}
		}
		return total;
	}

	Caixa() {
		setTitle("Caixa");
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
		new Caixa();
	}
}

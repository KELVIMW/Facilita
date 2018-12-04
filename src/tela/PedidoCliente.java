package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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

public class PedidoCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jlbMenuPedido;
	private JLabel jlbNumeroMesa;
	private DefaultTableModel dtmProdutos;
	private JTable jtProdutos;
	private JScrollPane jspRolagem;
	private JButton jbtPedido;
	private JTextField jtfNumeroMesa;
	private JTextField jtfTotal;

	private ArrayList<Produto> produtos;
	private JButton jbtConcluir;

	public void Label() {
		jlbMenuPedido = new JLabel("MEU PEDIDO:");
		jlbMenuPedido.setBounds(20, 23, 198, 20);
		jlbMenuPedido.setVisible(true);
		getContentPane().add(jlbMenuPedido);

		jlbNumeroMesa = new JLabel("Numero da mesa:");
		jlbNumeroMesa.setBounds(20, 260, 198, 20);
		jlbNumeroMesa.setVisible(true);
		getContentPane().add(jlbNumeroMesa);

	}

	public void Campos() {
		jtfNumeroMesa = new JTextField();
		jtfNumeroMesa.setBounds(130, 260, 40, 20);
		getContentPane().add(jtfNumeroMesa);

		jtfTotal = new JTextField(this.getTotal(produtos).toString());
		jtfTotal.setBounds(280, 23, 80, 20);
		getContentPane().add(jtfTotal);
	}

	public void Tabela() {
		dtmProdutos = new DefaultTableModel();
		dtmProdutos.addColumn("PRODUTO");
		dtmProdutos.addColumn("VALOR");

		produtos.forEach(p -> dtmProdutos.addRow(new String[] {
				p.getNomeProduto().toString(), p.getValor().toString() }));

		jtProdutos = new JTable(dtmProdutos);
		jspRolagem = new JScrollPane(jtProdutos);
		jspRolagem.setBounds(20, 45, 340, 200);
		getContentPane().add(jspRolagem);
	}

	public void Botoes() {
		jbtPedido = new JButton("Ver Cardapio");
		jbtPedido.setBounds(20, 305, 140, 40);
		getContentPane().add(jbtPedido);
		jbtPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Cardapio(produtos);
			}
		});
		jbtConcluir = new JButton("Concluir Pedido");
		jbtConcluir.setBounds(220, 305, 140, 40);
		getContentPane().add(jbtConcluir);
		jbtConcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();
				Pedido pedido = new Pedido();
				
				
				if(jtfNumeroMesa.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Por favor digite o numero da mesa");
				}else{
					
				//CADASTRAR MESA E O PEDIDO
				pedido.setMesa(Integer.parseInt(jtfNumeroMesa.getText()));
				pedido.setProdutos(produtos);
				//SALVA O PEDIDO NO BANCO
				pedidoRepositorio.salvar(pedido);
					
				JOptionPane.showMessageDialog(null, "Pedido gerado com sucesso !!! Aguarde que logo você será servido");
				new Principal();
				dispose();
				}
			}
		});
	}
	//METODO DE TOTAL
	public Double getTotal(List<Produto>produtos){
		Double total = 0.0;
		if(!produtos.isEmpty()){
			for(Produto p: produtos){
				total += p.getValor();
			}
			return total;
		}
		return total;
	}
	PedidoCliente(ArrayList<Produto> produtos) {
		if (produtos != null) {
			this.produtos = produtos;
		} else {
			this.produtos = new ArrayList<Produto>();
		}
		setTitle("Pedido do Cliente");
		setLayout(null);

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
		new PedidoCliente(null);
	}

}

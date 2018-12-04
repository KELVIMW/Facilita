package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar barra;
	private JMenu jmnCadastroCardapio;

	private JMenuItem jmiCadastroCardapio;
	private JMenuItem jmiPedidoCliente;
	private JMenu jmnPedidoCliente;
	private JMenu jmnCaixa;
	private JMenuItem jmiCaixa;
	private JMenu jmnCozinha;
	private JMenuItem jmiCozinha;
	private JMenuItem jmiGarcom;
	private JMenu jmnGarcom;
	private JLabel jlbImagem;

	public void Menu() {
		barra = new JMenuBar();
		jmnCadastroCardapio = new JMenu("CADASTRAR CARDAPIO");
		jmiCadastroCardapio = new JMenuItem("Cadastrar Cardapio");
		jmnCadastroCardapio.add(jmiCadastroCardapio);
		barra.add(jmnCadastroCardapio);

		jmnPedidoCliente = new JMenu("PEDIDO CLIENTE");
		jmiPedidoCliente = new JMenuItem("Pedido cliente");
		jmnPedidoCliente.add(jmiPedidoCliente);
		barra.add(jmnPedidoCliente);

		jmnCozinha = new JMenu("COZINHA");
		jmiCozinha = new JMenuItem("Relatorio cozinha");
		jmnCozinha.add(jmiCozinha);
		barra.add(jmnCozinha);

		jmnGarcom = new JMenu("GARÇOM");
		jmiGarcom = new JMenuItem("Relatorio Garçom");
		jmnGarcom.add(jmiGarcom);
		barra.add(jmnGarcom);

		jmnCaixa = new JMenu("CAIXA");
		jmiCaixa = new JMenuItem("Acessar o caixa");
		jmnCaixa.add(jmiCaixa);
		barra.add(jmnCaixa);

		setJMenuBar(barra);
	}

	public void acoesMenu() {
		jmiCadastroCardapio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CadastroProduto();
				dispose();
			}
		});

		jmiPedidoCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PedidoCliente(null);

			}
		});

		jmiCozinha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Cozinha();
				dispose();
			}
		});

		jmiGarcom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Garcom();
				dispose();
			}
		});

		jmiCaixa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Caixa();
				dispose();
			}
		});

	}

	public void imagem() {
		jlbImagem = new JLabel();
		jlbImagem.setBounds(0, 0, 600, 400);
		jlbImagem.setIcon(new ImageIcon("imagem/index.png"));
		getContentPane().add(jlbImagem);
	}

	public Principal() {
		setTitle("Gerenciar");
		setLayout(null);

		Menu();
		acoesMenu();
		imagem();

		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Principal();

	}

}

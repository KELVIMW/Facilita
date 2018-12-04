package classes;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Integer mesa;
	private List<Produto> produtos;
	
	private boolean concluido = false;

	public Pedido(Integer mesa) {
		this.mesa = mesa;
		this.produtos = new ArrayList<>();
	}

	public Pedido() {
		
	}

	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

}

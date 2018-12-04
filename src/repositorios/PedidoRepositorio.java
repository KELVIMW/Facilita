package repositorios;

import java.util.ArrayList;
import java.util.List;

import metodos.gerenciador;
import classes.Pedido;
import classes.Produto;
import classes.Tipo;


public class PedidoRepositorio extends gerenciador<Pedido>{
	//BUSCAR PEDIDO POR MESA
	public Pedido por(Integer mesa){
		for(Pedido p : this.todos()) {
			if (mesa.equals(p.getMesa())) {
				return p;
			}
		}
		
		return null;
	}
	
	//MOSTRAR PEDIDOS
	public List<Produto> listarProdutosPor(Integer cod, Tipo tipo){
		List<Produto> listaFiltro = new ArrayList<Produto>();
		for(Produto p : this.por(cod).getProdutos()){
			if (tipo.equals(p.getTipo())) {
				listaFiltro.add(p);
			}
		}
		return listaFiltro;
	}
	
	//LISTAR TODOS OS PEDIDOS NÃO CONCLUIDOS
	public List<Pedido> listaPedidosNaoConcluidos() {
		List<Pedido> listaFiltro = new ArrayList<>();
		for(Pedido ped : this.todos()) {
			if (!ped.isConcluido()) {
				listaFiltro.add(ped);
			}
		}
		
		return listaFiltro;
	}
	
	@Override
	public Class<Pedido> getClazz() {
		return Pedido.class;
	}
}

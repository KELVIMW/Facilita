package repositorios;

import java.util.ArrayList;
import java.util.List;

import metodos.gerenciador;
import classes.Produto;
import classes.Tipo;

public class ProdutoRepositorio extends gerenciador<Produto> {

//LISTA TODOS OS ITENS DO CARDAPIO POR ENUM
	public List<Produto> listarPor(Tipo tipo){
		List<Produto> listaFiltro = new ArrayList<>();
		for(Produto p : this.todos()) {
			if (tipo.equals(p.getTipo())) {
				listaFiltro.add(p);
			}
		}
		
		return listaFiltro;
	}
	
	
	//CONSULTA NO BANCO SE EXISTE O PEDIDO
	public Produto por(Integer cod){
		for(Produto p : this.todos()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		
		return null;
	}

	
	@Override
	public Class<Produto> getClazz() {
		return Produto.class;
	}

}

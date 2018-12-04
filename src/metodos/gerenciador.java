package metodos;

import java.util.ArrayList;
import java.util.List;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import conexao.Conexao;

public abstract class gerenciador<T> {

	protected ObjectContainer restauranteBanco;

	// METODO DE CONEXAO
	public gerenciador() {
		restauranteBanco = Conexao.getRestaurante();
	}

	// METODO DE CADASTRO DE PRODUTOS
	public void salvar(T objeto) {
		restauranteBanco.store(objeto);
	}

	// METODO DE LISTA DE PRODUTOS
	public List<T> todos() {
		ObjectSet<T> todos = restauranteBanco.query(getClazz());
		return new ArrayList<>(todos);
	}

	// REMOVER PRODUTOS
	public void remover(T objeto) {
		restauranteBanco.delete(objeto);
	}

	public abstract Class<T> getClazz();
}

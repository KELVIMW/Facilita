package conexao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Conexao {

	private static ObjectContainer restauranteBanco;
	private static boolean fechado = false;
	
	static{
		//INICIA O BANCO
		openFile();
	}

	private static void openFile() {
		//CRIA O BANCO RESTAURANTE
		restauranteBanco = Db4oEmbedded.openFile("restaurante.bd40");
		fechado = false;
	}
	
	public static ObjectContainer getRestaurante(){
		if(fechado){
			openFile();
		}
		return restauranteBanco;
	}
	
	public static void fechar(){
		fechado = true;
		restauranteBanco.close();
	}

}

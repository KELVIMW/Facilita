package classes;

public class Produto {
	private Integer cod;
	private String nomeProduto;
	private Double valor = 0.0;
	private Tipo tipo;

	public Produto(Integer cod, String nomeProduto, Double valor, Tipo tipo) {

		this.cod = cod;
		this.nomeProduto = nomeProduto;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Produto() {

	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}

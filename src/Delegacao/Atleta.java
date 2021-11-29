package Delegacao;
import java.io.Serializable;

public abstract class Atleta implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private int numero;
	private String categoria;
	private int provas;
	
	public Atleta (String nome, int numero, String categoria, int provas) {
		this.nome = nome;
		this.numero= numero;
		this.categoria = categoria;
		this.provas = provas;	
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getProvas() {
		return provas;
	}

	public void setProvas(int provas) {
		this.provas = provas;
	}

	@Override
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     							+ this.nome     	+ "\n";
		retorno += "Numero: "   							+ this.numero   	+ "\n";
		retorno += "Categoria: "    						+ this.categoria    + "\n";
		retorno += "Profissional? "							+ profissional()	+ "\n";
		retorno += "Quantidade de provas que participou: "	+ this.provas		+ "\n";
		return retorno;
	}
	
	public abstract String profissional();
	
}

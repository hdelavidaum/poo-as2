package Delegacao;

public class Saltador extends Atleta {

	private static final long serialVersionUID = 1L;
	
	protected double altura;
	final String esporte = "Salto em distância";

	public Saltador(String nome, int numero, String categoria, int provas, double altura) {
		super(nome, numero, categoria, provas);
		this.altura = altura; 
	}

	@Override
	public String profissional() {
		if (this.getCategoria() == "Elite") {
			return "Sim";
		}
		return "Não";
	}
	
	@Override
	public String toString() {
		return "Esporte: " + this.esporte + "\n" + super.toString() + "Altura do atleta: " + this.altura + "\n";
				
	}

}

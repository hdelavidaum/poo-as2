package Delegacao;

public class Nadador extends Atleta {

	private static final long serialVersionUID = 1L;
	protected final String esporte = "Natação";
	protected String estilo;

	public Nadador(String nome, int numero, String categoria, int provas, String estilo) {
		super(nome, numero, categoria, provas);
		this.estilo = estilo;
	}

	@Override
	public String profissional() {
		if (this.getCategoria() != "Amador" && this.getProvas() > 3) {
			return "Sim";
		}
		return "Não";
	}
	
	@Override
	public String toString() {
		return "Esporte: " + this.esporte + "\n" + super.toString() + "Altura do atleta: " + this.estilo + "\n";
				
	}

}

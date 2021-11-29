package Delegacao;

public class Corredor extends Atleta {

	private static final long serialVersionUID = 1L;
	final String esporte = "Corrida";
	double pace;

	public Corredor(String nome, int numero, String categoria, int provas, double pace) {
		super(nome, numero, categoria, provas);
		this.pace = pace;
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
		return "Esporte: " + this.esporte + "\n" + super.toString() + "Pace do atleta: " + this.pace + "\n";
				
	}
}

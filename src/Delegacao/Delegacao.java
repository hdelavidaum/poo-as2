package Delegacao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Delegacao {
	private ArrayList<Atleta> atletas;
	
	public Delegacao() {
		this.atletas = new ArrayList<Atleta>();
	}
	
	public String[] leValores (String[] dadosIn) {
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}
	
	public Corredor leCorredor (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Número", "Categoria\n(Amador, Elite)", "Quantidade de provas", "Pace (em min/km)"};
		valores = leValores (nomeVal);

		int num = this.retornaInteiro(valores[1]);
		int qtProvas = this.retornaInteiro(valores[3]);
		double pace = this.retornaDouble(valores[4]);

		Corredor corredor = new Corredor (valores[0],num ,valores[2], qtProvas, pace);
		return corredor;
	}
	
	public Nadador leNadador (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Número", "Categoria\n(Amador, Elite)", "Quantidade de provas", "Pace (em min/km)"};
		valores = leValores (nomeVal);

		int num = this.retornaInteiro(valores[1]);
		int qtProvas = this.retornaInteiro(valores[3]);

		Nadador nadador = new Nadador (valores[0],num ,valores[2], qtProvas, valores[4]);
		return nadador;
	}
	
	public Saltador leSaltador (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Número", "Categoria\n(Amador, Elite)", "Quantidade de provas", "Pace (em min/km)"};
		valores = leValores (nomeVal);

		int num = this.retornaInteiro(valores[1]);
		int qtProvas = this.retornaInteiro(valores[3]);
		double altura = this.retornaDouble(valores[4]);

		Saltador saltador = new Saltador (valores[0],num ,valores[2], qtProvas, altura);
		return saltador;
	}
	
	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public int retornaInteiro(String entrada) {
		
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	
	private boolean doubleValido(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public double retornaDouble(String entrada) {
		
		while (!this.doubleValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número double\nseparado por ponto\n (Ex: 1.75).");
		}
		return Double.parseDouble(entrada);
	}
	
	public void salvaAtletas (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\delegacao.dados"));
			for (int i=0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar o arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaAtletas (){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\delegacao.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com os atletas NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}
	
	public void menuDelegacao (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Menu da Delegação\n" +
					"Opções:\n" + 
					"1. Inserir Atleta\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atleta(s)\n" +
					"4. Gravar Atleta(s)\n" +
					"5. Recuperar Atleta(s)\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Inscrição de Atletas\n" +
						"Opções:\n" + 
						"1. Corredor\n" +
						"2. Nadador\n" +
						"3. Saltador\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leCorredor());
				break;
				case 2: atletas.add((Atleta)leNadador());
				break;
				case 3: atletas.add((Atleta)leSaltador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Atleta para inscrição não escolhido adequadamente!");
				}

				break;
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Necessário fazer a inscrição dos atletas antes.");
					break;
				}
				String dados = "";
				for (int i=0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Necessário fazer a inscrição dos atletas antes.");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Necessário fazer a inscrição dos atletas antes.");
					break;
				}
				salvaAtletas(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo DELEGACAO");
				break;
			}
		} while (opc1 != 9);
	}
	
	public static void main(String[] args) {
		
		Delegacao delegacao = new Delegacao();
		delegacao.menuDelegacao();
		
	}

}

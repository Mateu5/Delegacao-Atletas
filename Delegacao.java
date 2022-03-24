import javax.swing.JOptionPane;

import javaapplication2.Nadador;

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
	private ArrayList<Atleta> Atleta;

	public Delegacao() {
		this.Atleta = new ArrayList<Atleta>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Saltador leSaltador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Numero", "País","Altura"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Saltador saltador = new Saltador (valores[0],idade,valores[2], idade);
		return saltador;
	}

	public Corredor leCorredor (){

		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "Numero", "País", "Velocidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Corredor corredor = new Corredor (valores[0],idade,valores[2], idade);
		return corredor;
	}
	
	public Nadador leNadador () {
		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "Numero", "País","Altura"};
		valores = leValores (nomeVal);

		int altura = this.retornaInteiro(valores[1]);

		
		int numero = this.retornaInteiro(valores[1]);

		Nadador nadador = new Nadador (valores[0],numero,valores[2]);
		return nadador;
		
		
		
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAtleta (ArrayList<Atleta> atleta){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i=0; i < atleta.size(); i++)
				outputStream.writeObject(atleta.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
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
	public ArrayList<Atleta> recuperaAtleta (){
		ArrayList<Atleta> atletaTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\delegacao.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletaTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo Atletas NÃO existe!");
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
			return atletaTemp;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle PetStore\n" +
					"Opções:\n" + 
					"1. Entrar Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Entrada de Animais Mamíferos\n" +
						"Opções:\n" + 
						"1. Corredor\n" +
						"2. Saltador\n" +
				        "3. Nadador\"";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: Atleta.add((Atleta)leCorredor());
				break;
				case 2: Atleta.add((Atleta)leSaltador());
				break;
				case 3: Atleta.add((Atleta)leSaltador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Atleta delegação para entrada NÃO escolhido!");
				}

				break;
			case 2:
				if (Atleta.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mamíferos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < Atleta.size(); i++)	{
					dados += Atleta.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: 
				if (Atleta.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mamíferos primeiramente");
					break;
				}
				Atleta.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (Atleta.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mamíferos primeiramente");
					break;
				}
				salvaAtleta(Atleta);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				Atleta = recuperaAtleta();
				if (Atleta.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo PETSTORE");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Delegacao del = new Delegacao ();
		del.menuDelegacao();

	}
	private void menuDelegacao() {
		// TODO Auto-generated method stub
		
	}

}

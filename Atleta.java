
public abstract class Atleta {
	
	private String nome;
	private int numero;
	private String pais;
	
	public Atleta(String nome, int numero, String pais) {
		this.nome = nome;
		this.numero = numero;
		this.pais = pais;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	
	
}

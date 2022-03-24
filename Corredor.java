
public class Corredor extends Atleta {
	
	public float velocidade;
	
	public Corredor(String nome, int numero, String pais, float velocidade) {
		super(nome, numero, pais);
		this.velocidade = velocidade;
	}

	public float getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(float velocidade) {
		this.velocidade = velocidade;
	}
	
	

}

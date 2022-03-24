
public class Saltador extends Atleta {
	
	private float altura;

	public Saltador(String nome, int numero, String pais, float altura) {
		super(nome, numero, pais);
		this.altura = altura;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	
	

}

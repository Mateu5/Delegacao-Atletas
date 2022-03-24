
public class Nadador extends Atleta {
    private static final long serialVersionUID = 1L;
	private String categoria;
    
    public String ambiente() {
        return "Piscina"; 
    }

    
    public Nadador(String nome, int numero, String nacionalidade) {
        super(nome, numero, nacionalidade); 
        this.categoria = "Nadador";
    }
}

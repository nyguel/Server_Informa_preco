package entidade;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Empresa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeEmpresa;
    private String cnpj;
    private String enderecoDaEmpresa;
    private float precoLista;
    private int cidadeid;
    private ArrayList<Produto> produtos = new ArrayList<>();
    
    public Empresa(){
    	
    }

    public Empresa(String cnpj,String nomeEmpresa, String enderecoDaEmpresa, int cidadeid) {
		super();
		this.nomeEmpresa = nomeEmpresa;
		this.cnpj = cnpj;
		this.enderecoDaEmpresa = enderecoDaEmpresa;
		this.cidadeid = cidadeid;
	}

	public int getCidadeid() {
		return cidadeid;
	}

	public void setCidadeid(int cidadeid) {
		this.cidadeid = cidadeid;
	}

	public Empresa(String cnpj, String nomeEmpresa, String enderecoDaEmpresa ) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.enderecoDaEmpresa = enderecoDaEmpresa;
    }
    
    public String getEnderecoDaEmpresa() {
		return enderecoDaEmpresa;
	}

	public void setEnderecoDaEmpresa(String enderecoDaEmpresa) {
		this.enderecoDaEmpresa = enderecoDaEmpresa;
	}

	public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public float getPrecoLista() {
		return precoLista;
	}

	public void setPrecoLista(float precoLista) {
		this.precoLista = precoLista;
	}
    
}

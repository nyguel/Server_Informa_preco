package entidade;
import java.io.Serializable;

public class PrecoProduto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	
	private String _dataPreco;
    private float _preco;
	
	
	public PrecoProduto() {
		
	}

	public float get_preco() {return _preco;}
	public Empresa getEmpresa() {return empresa;}
	public String get_dataPreco() {	return _dataPreco;}
	public void set_preco(float _preco) {this._preco = _preco;}
	public void setEmpresa(Empresa empresa) {this.empresa = empresa;}	
	public void set_dataPreco(String _dataPreco) {this._dataPreco = _dataPreco;}
	
	
	
}

package entidade;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Nyguel
 */
public class Produto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _descricao;
    private String _codigo;
    private String _unidade;
    private String _dataPreco;
    private float _preco;
    private int quantidade;
    private ArrayList<PrecoProduto> precoProdutos = new ArrayList<>();

    public Produto(){
    }

    public Produto(String _codigo, String _descricao, String _unidade){
        super();
        this._descricao = _descricao;
        this._codigo = _codigo;
        this._unidade = _unidade;

    }
    
    public float get_preco() {return _preco;}
    public String get_codigo() {return _codigo;}
    public int getQuantidade() {return quantidade;}
    public String get_unidade() { return _unidade;}
    public String get_descricao() {return _descricao;}
    public String get_dataPreco() {return _dataPreco;}
    public ArrayList<PrecoProduto> getPrecoProdutos() {return precoProdutos;}
    
    public void set_preco(float _preco) {this._preco = _preco;}
    public void set_codigo(String _codigo) {this._codigo = _codigo;}
    public void set_unidade(String _unidade) {this._unidade = _unidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}    
    public void set_descricao(String _descricao) {this._descricao = _descricao;}    
    public void set_dataPreco(String _dataPreco) {this._dataPreco = _dataPreco;}
    public void setPrecoProdutos(ArrayList<PrecoProduto> precoProdutos) {this.precoProdutos = precoProdutos;}


    
    
    
    
    
    
	
	

    
}




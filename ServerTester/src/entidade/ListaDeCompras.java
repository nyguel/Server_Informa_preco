package entidade;


import java.io.Serializable;
import java.util.ArrayList;

public class ListaDeCompras implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id_lista;
    private String _nomeLista, _dataLista;
    private float _total = 0;
    private ArrayList<Empresa> empresas = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    public ListaDeCompras() {
    }

    public ListaDeCompras(String _nomeLista) {
        this._nomeLista = _nomeLista;
    }
    public ListaDeCompras(String _nomeLista, String _dataLista,float _total) {
        this._nomeLista = _nomeLista;
        this._dataLista = _dataLista;
        this._total = _total;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public int get_id_lista() {
        return _id_lista;
    }

    public void set_id_lista(int _id_lista) {
        this._id_lista = _id_lista;
    }

    public String get_nomeLista() {
        return _nomeLista;
    }

    public void set_nomeLista(String _nomeLista) {
        this._nomeLista = _nomeLista;
    }

    public float get_total() {
        return _total;
    }

    public void set_total(float _total) {
        this._total = _total;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String get_dataLista() {
        return _dataLista;
    }

    public void set_dataLista(String _dataLista) {
        this._dataLista = _dataLista;
    }
}

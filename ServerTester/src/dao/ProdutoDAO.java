package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidade.Produto;
import config.ModuloConexao;

/**
 *
 * @author Nyguel
 */
public class ProdutoDAO {

	Connection conexaoBanco = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	public boolean insert(Produto produto) {
		try {
			conexaoBanco = ModuloConexao.conector();
			String sql = "select insere_produto(?,?,?)";
			statement = conexaoBanco.prepareStatement(sql);
			statement.setString(1, produto.get_codigo());
			statement.setString(2, produto.get_descricao());
			statement.setString(3, produto.get_unidade());
			rs = statement.executeQuery();
			boolean resultado = rs.getBoolean(1);
			if(resultado) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
		}
		return false;
	}
	
	 public Produto select(String codigo) {
	    	Produto produto = new Produto();
			
			conexaoBanco = ModuloConexao.conector();
			String sql = "select * from produto where produto_cod = ?";

			try {
				statement = conexaoBanco.prepareStatement(sql);	
				statement.setString(1, codigo);
				rs = statement.executeQuery();
				if (rs.next()) {
					produto.set_codigo(rs.getString(1));
					produto.set_descricao(rs.getString(2));		
					produto.set_unidade(rs.getString(3));
				}

			} catch (SQLException e) {
				Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
			}
			return produto;
		}
	    
	    public boolean Delete(String codigo) {		
			conexaoBanco = ModuloConexao.conector();
			String sql = "select delete_produto(?)";

			try {
				statement = conexaoBanco.prepareStatement(sql);	
				statement.setString(1, codigo);
				rs = statement.executeQuery();
				boolean resultado = rs.getBoolean(1);
				if(resultado) {
					return true;
				}
				else {
					return false;
				}
			} catch (SQLException e) {
				Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
			}
			return false;
		}
	    
	    public boolean update(Produto produto) {
	        try {
	            conexaoBanco = ModuloConexao.conector();
	            String sql = "select update_produto(?,?,?)";
	            statement = conexaoBanco.prepareStatement(sql);
	            statement.setString(1, produto.get_codigo());
	            statement.setString(2, produto.get_descricao());
	            statement.setString(3, produto.get_unidade());
	            rs = statement.executeQuery();
	            boolean resultado = rs.getBoolean(1);
				if(resultado) {
					return true;
				}
				else {
					return false;
				}
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return false;
	    }
	    public ArrayList<Produto> selectAll() {
	    	ArrayList<Produto> produtos = new ArrayList<>();
			
			conexaoBanco = ModuloConexao.conector();
			String sql = "select * from produto";

			try {
				statement = conexaoBanco.prepareStatement(sql);			
				rs = statement.executeQuery();
				while (rs.next()) {
					Produto produto = new Produto();
					produto.set_codigo(rs.getString(1));
					produto.set_descricao(rs.getString(2));		
					produto.set_unidade(rs.getString(3));
					produtos.add(produto);
				}
			} catch (SQLException e) {
				Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
			}
			return produtos;
		}
	
	
}

package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidade.Empresa;
import entidade.Produto;
import entidade.Usuario;
import config.ModuloConexao;

/**
 *
 * @author Nyguel
 */
public class UsuarioDAO {
    Connection conexaoBanco = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    
    public boolean insert(String email, String senha) {
        try {
            conexaoBanco = ModuloConexao.conector();
            String sql = "select insere_usuario(?,?)";
            statement = conexaoBanco.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);            
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
    
    public boolean Delete(int id) {		
		conexaoBanco = ModuloConexao.conector();
		String sql = "select delete_usuario(?)";

		try {
			statement = conexaoBanco.prepareStatement(sql);	
			statement.setInt(1, id);
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
    public Integer select(Usuario usuario) {		
		conexaoBanco = ModuloConexao.conector();
		String sql = "select select_usuario(?,?)";
		int id=0;

		try {
			statement = conexaoBanco.prepareStatement(sql);
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getSenha());
			rs = statement.executeQuery();			
			
			if(rs.next()) {
				id = rs.getInt(1);
				return id;
			}
			
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}	
    public boolean update(Usuario usuario) {
        try {
            conexaoBanco = ModuloConexao.conector();
            String sql = "select update_usuario(?,?,?)";
            statement = conexaoBanco.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
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
    
    public ArrayList<Usuario> selectAll() {		
		conexaoBanco = ModuloConexao.conector();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try {
			statement = conexaoBanco.prepareStatement(sql);
			rs = statement.executeQuery();			
			
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setSenha(rs.getString(3));
				usuarios.add(user);
			}
			
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return usuarios;
	}	
    
    public Empresa selectProdutos(int id) {
    	Empresa empresa = new Empresa();
		ArrayList<Produto> listprodutos =  new ArrayList<>();
		conexaoBanco = ModuloConexao.conector();
		String sql = "select e.empresa_cnpj,e.empresa_nome,e.empresa_endereco,p.produto_cod, p.produto_descricao,pdp.produto_preco, pdp.data_preco from empresa_tem_usuario etu "
				+ "join empresa e on e.empresa_cnpj = etu.empresa_cnpj "
				+ "join usuario u on u.usuario_id = etu.usuario_id "
				+ "join preco_de_produto pdp on pdp.empresa_cnpj = e.empresa_cnpj "
				+ "join produto p on p.produto_cod = pdp.produto_cod "
				+ "where u.usuario_id = ?";

		try {
			statement = conexaoBanco.prepareStatement(sql);	
			statement.setInt(1, id);
            
			rs = statement.executeQuery();
			while (rs.next()) {
				if(rs.isFirst()){
				empresa.setCnpj(rs.getString(1));
				empresa.setNomeEmpresa(rs.getString(2));
				empresa.setEnderecoDaEmpresa(rs.getString(3));
				Produto produto = new Produto();
				produto.set_codigo(rs.getString(4));
				produto.set_descricao(rs.getString(5));
				produto.set_preco(rs.getFloat(6));
				produto.set_dataPreco(rs.getString(7));
				listprodutos.add(produto);
				}else {
					Produto produto = new Produto();
					produto.set_codigo(rs.getString(4));
					produto.set_descricao(rs.getString(5));
					produto.set_preco(rs.getFloat(6));
					produto.set_dataPreco(rs.getString(7));
					listprodutos.add(produto);
				}
			}
			empresa.setProdutos(listprodutos);

			
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return empresa;
	}
    
}


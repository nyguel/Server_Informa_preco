package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import entidade.Empresa;
import config.ModuloConexao;

/**
 *
 * @author Nyguel
 */
public class EmpresaDAO {
    Connection conexaoBanco = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    
    public boolean insert(Empresa empresa) {
        try {
            conexaoBanco = ModuloConexao.conector();
            String sql = "select insere_empresa(?,?,?,?)";
            statement = conexaoBanco.prepareStatement(sql);
            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getNomeEmpresa());
            statement.setString(3, empresa.getEnderecoDaEmpresa());
            statement.setInt(4, empresa.getCidadeid());
            rs = statement.executeQuery();
            rs.next();
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
    public Empresa select(String cnpj) {
    	Empresa empresa = new Empresa();
		
		conexaoBanco = ModuloConexao.conector();
		String sql = "select * from empresa where empresa_cnpj = ?";

		try {
			statement = conexaoBanco.prepareStatement(sql);	
			statement.setString(1, cnpj);
			rs = statement.executeQuery();
			if (rs.next()) {
				empresa.setCnpj(rs.getString(1));
				empresa.setNomeEmpresa(rs.getString(2));		
				empresa.setEnderecoDaEmpresa(rs.getString(3));
				empresa.setCidadeid(rs.getInt(4));
			}

		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return empresa;
	}
    
    public boolean delete(String cnpj) {		
		conexaoBanco = ModuloConexao.conector();
		String sql = "select delete_empresa(?)";

		try {
			statement = conexaoBanco.prepareStatement(sql);	
			statement.setString(1, cnpj);
			rs = statement.executeQuery();
			rs.next();
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
    
    public boolean update(Empresa empresa) {
        try {
            conexaoBanco = ModuloConexao.conector();
            String sql = "select update_empresa(?,?,?,?)";
            statement = conexaoBanco.prepareStatement(sql);
            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getNomeEmpresa());
            statement.setString(3, empresa.getEnderecoDaEmpresa());
            statement.setInt(4, empresa.getCidadeid());
            rs = statement.executeQuery();
            rs.next();
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
    public ArrayList<Empresa> selectAll() {
    	ArrayList<Empresa> empresas = new ArrayList<>();
		
		conexaoBanco = ModuloConexao.conector();
		String sql = "select * from empresa";

		try {
			statement = conexaoBanco.prepareStatement(sql);			
			rs = statement.executeQuery();
			while (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setCnpj(rs.getString(1));
				empresa.setNomeEmpresa(rs.getString(2));		
				empresa.setEnderecoDaEmpresa(rs.getString(3));
				empresas.add(empresa);
			}
			return empresas;
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return empresas;
	}
    
}


package utilitarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.ModuloConexao;
import dao.CidadeDAO;

public class LimpaBanco {
	Connection conexaoBanco = null;
    PreparedStatement statement = null;
    
    public void cidade() {		
		conexaoBanco = ModuloConexao.conector();
		String sql = "delete from cidade";

		try {
			statement = conexaoBanco.prepareStatement(sql);				
			statement.executeUpdate();			
		} catch (SQLException e) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
    
    public void empresa() {		
		conexaoBanco = ModuloConexao.conector();
		String sql = "delete from empresa";

		try {
			statement = conexaoBanco.prepareStatement(sql);				
			statement.executeUpdate();			
		} catch (SQLException e) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
}

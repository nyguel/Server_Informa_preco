package Controladores;




import java.util.ArrayList;
import dao.UsuarioDAO;
import entidade.Usuario;

public class ControllerUsuario {

	public static String getAllUsuario() {	
		String retorno ="";
		ArrayList<Usuario> usuarios = new UsuarioDAO().selectAll();
		for(int i = 0; i<usuarios.size();i++) {
			retorno +=usuarios.get(i).getId()+"-"+usuarios.get(i).getEmail()+"-"+usuarios.get(i).getSenha()+"%";
		}				
		return retorno;
	}
	public static String getUsuario() {	
		String retorno ="";
		ArrayList<Usuario> usuarios = new UsuarioDAO().selectAll();
		for(int i = 0; i<usuarios.size();i++) {
			retorno +=usuarios.get(i).getId()+"-"+usuarios.get(i).getEmail()+"-"+usuarios.get(i).getSenha()+"%";
		}				
		return retorno;
	}
	public static String postUsuario() {	
		String retorno ="";
		ArrayList<Usuario> usuarios = new UsuarioDAO().selectAll();
		for(int i = 0; i<usuarios.size();i++) {
			retorno +=usuarios.get(i).getId()+"-"+usuarios.get(i).getEmail()+"-"+usuarios.get(i).getSenha()+"%";
		}				
		return retorno;
	}
	public static String putUsuario() {	
		String retorno ="";
		ArrayList<Usuario> usuarios = new UsuarioDAO().selectAll();
		for(int i = 0; i<usuarios.size();i++) {
			retorno +=usuarios.get(i).getId()+"-"+usuarios.get(i).getEmail()+"-"+usuarios.get(i).getSenha()+"%";
		}				
		return retorno;
	}
	public static String deleteUsuario() {	
		String retorno ="";
		ArrayList<Usuario> usuarios = new UsuarioDAO().selectAll();
		for(int i = 0; i<usuarios.size();i++) {
			retorno +=usuarios.get(i).getId()+"-"+usuarios.get(i).getEmail()+"-"+usuarios.get(i).getSenha()+"%";
		}				
		return retorno;
	}
}

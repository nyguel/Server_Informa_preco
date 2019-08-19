package Controladores;

import java.util.ArrayList;

import dao.CidadeDAO;
import entidade.Cidade;

public class ControllerCidade {		
	
		public static  String getAllCidade() {	
			String retorno ="";
			ArrayList<Cidade> cidades = new CidadeDAO().selectAll();
			for(int i = 0; i<cidades.size();i++) {
				retorno +=cidades.get(i).getCidadeId()+"-"+cidades.get(i).getCidadeNome()+"%";
			}
					
			return retorno;
		}
		public static String getCidade(int id) {	
			String retorno ="";
			Cidade cidade = new CidadeDAO().select(id);
			if(!(cidade.getCidadeNome()==null)) {
				retorno+=cidade.getCidadeId()+"-"+cidade.getCidadeNome();
				return retorno;
			}
								
			return "cidade não encontrada";
		}
		public static  String postCidade(String nome, int id) {	
			
			if(new CidadeDAO().insert(nome, id)) {
				return "Cidade inserida com sucesso";
			}			
					
			return "Problema ao inserir cidade";
		}
		public static  String putCidade(Cidade cidade) {	
			if(new CidadeDAO().update(cidade)) {
				return "Cidade atualizada com sucesso";
			}			
					
			return "Problema ao atualizar a cidade";
		}
		public static  String deleteCidade(int id) {	
			
			if(new CidadeDAO().delete(id)) {
				return "Cidade excluida com sucesso";
			}			
					
			return "Problema ao excluir a cidade";
		}

}

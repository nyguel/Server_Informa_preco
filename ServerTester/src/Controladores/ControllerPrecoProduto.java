package Controladores;


import java.util.ArrayList;

import dao.PrecoProdutoDAO;
import entidade.Produto;


public class ControllerPrecoProduto {	
	
	public String getPrecoProduto(String cod_produto, int id_cidade) {		
		String retorno ="";
		ArrayList<Produto> usuarios = new PrecoProdutoDAO().select(cod_produto,id_cidade);
		for(int i = 0; i<usuarios.size();i++) {
			retorno +="%";
		}				
		return retorno;
	}
	
	public String postPrecoProduto(Produto insert) {					
		return "";
	}
		
	public String putPrecoProduto(Produto produto) {					
		return "";
	}

	public String deletePrecoProduto(Produto produto) {			
		return "";
	}
}

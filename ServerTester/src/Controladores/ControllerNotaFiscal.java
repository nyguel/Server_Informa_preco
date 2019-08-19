package Controladores;


import java.util.ArrayList;

import dao.EmpresaDAO;
import dao.PrecoProdutoDAO;
import dao.ProdutoDAO;
import entidade.Empresa;
import entidade.Produto;
import utilitarios.ExtraiDadosHtml;

public class ControllerNotaFiscal {
	
	public String getUsuario(String url) {
		ExtraiDadosHtml html = new ExtraiDadosHtml();
		Empresa empresa = new Empresa();
		empresa = html.pesquisar(url);	
		ArrayList<Produto> produtos = empresa.getProdutos();
		if(empresa.getCidadeid()==0) {
			return "não foi possivel adicionar a nota Fiscal";
		}else {
			new EmpresaDAO().insert(empresa);
			
			for (int i = 0 ; i<empresa.getProdutos().size();i++) {
				new ProdutoDAO().insert(empresa.getProdutos().get(i));
			}
			for (int i = 0 ; i<empresa.getProdutos().size();i++) {
				new PrecoProdutoDAO().insert(produtos.get(i),empresa.getCnpj());
			}
		}				
		return "Agradecemos a colaboração";
	}
}
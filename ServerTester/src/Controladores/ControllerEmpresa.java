package Controladores;

import java.util.ArrayList;

import dao.EmpresaDAO;

import entidade.Empresa;

public class ControllerEmpresa {
	
	public static String getAllEmpresa() {		
	
		String retorno ="";
		ArrayList<Empresa> empresas = new EmpresaDAO().selectAll();
		for(int i = 0; i<empresas.size();i++) {
			retorno +=empresas.get(i).getNomeEmpresa()+"%";
		}
				
		return retorno;
	}
	public static String getEmpresa(String cnpj) {		
		
		String retorno ="";
		Empresa empresa = new EmpresaDAO().select(cnpj);
		if(!(empresa.getNomeEmpresa()==null)) {
			retorno+=empresa.getCnpj()+"-"+empresa.getNomeEmpresa()+","+empresa.getEnderecoDaEmpresa()+","+empresa.getCidadeid();
			return retorno;
		}
							
		return "Empresa não encontrada";
	}
	public static String postEmpresa(Empresa empresa) {		
		
		if(new EmpresaDAO().insert(empresa)) {
			return "Empresa inserida com sucesso";
		}			
				
		return "Problema ao inserir empresa";
	}
	public static String deleteEmpresa(String cnpj) {		
		
		if(new EmpresaDAO().delete(cnpj)) {
			return "Empresa excluida com sucesso";
		}			
				
		return "Problema ao excluir a empresa";
	}
	public static String putEmpresa(Empresa empresa) {		
		
		if(new EmpresaDAO().update(empresa)) {
			return "Empresa atualizada com sucesso";
		}			
				
		return "Problema ao atualizar a empresa";
	}
}

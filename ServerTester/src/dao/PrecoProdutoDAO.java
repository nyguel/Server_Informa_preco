package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidade.Empresa;
import entidade.ListaDeCompras;
import entidade.PrecoProduto;
import entidade.Produto;
import config.ModuloConexao;

public class PrecoProdutoDAO {
	
	Connection conexaoBanco = null;
	PreparedStatement statement = null;
	ResultSet rs = null;	
	
	public boolean insert(Produto produto, String empresa_cnpj) {
		try {			
			conexaoBanco = ModuloConexao.conector();
			String sql = "select insere_preco_produto(?,?,?,?)";
				
				statement = conexaoBanco.prepareStatement(sql);
				statement.setString(1,produto.get_codigo());
				statement.setString(2,empresa_cnpj);				
				statement.setString(3,produto.get_dataPreco());
				statement.setFloat(4,produto.get_preco());
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
	
	public boolean update(String cod_produto, float preco, String empresa_cnpj, String data) {
		try {			
			conexaoBanco = ModuloConexao.conector();
			String sql = "select update_preco_produto(?,?,?,?);";
				statement = conexaoBanco.prepareStatement(sql);
				
				statement.setString(1,cod_produto);
				statement.setString(2,empresa_cnpj);				
				statement.setString(3,data);
				statement.setFloat(4,preco);
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
	
	public boolean delete(String cod_produto, String empresa_cnpj) {
		try {			
			conexaoBanco = ModuloConexao.conector();
			String sql = "call delete_preco_produto(?,?)";			
				statement = conexaoBanco.prepareStatement(sql);				
				statement.setString(1,cod_produto);
				statement.setString(2,empresa_cnpj);			
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

	public ArrayList<Produto> select(String codigoOuNome, int cidadeId) {
		ArrayList<Produto> produtos = new ArrayList<>();		
		Produto produto = new Produto();				
		Empresa empresa;
		PrecoProduto precoProduto;
		String codigoAnterior="";
		String codigoAtual="" ;
		conexaoBanco = ModuloConexao.conector();		
		String sql = "select pdp.produto_cod,p.produto_descricao,pdp.produto_preco,DATE_FORMAT(pdp.data_preco,'%d-%m-%Y') as 'Data',e.empresa_cnpj,e.empresa_nome,e.empresa_endereco from preco_de_produto pdp join empresa e on pdp.empresa_cnpj = e.empresa_cnpj join produto p on pdp.produto_cod = p.produto_cod where (pdp.produto_cod = ?  or p.produto_descricao like ?) and (e.cidade_id = ? ) order by pdp.produto_cod, pdp.produto_preco";

		try {
			statement = conexaoBanco.prepareStatement(sql);
			
			statement.setString(1, codigoOuNome);
			statement.setString(2, '%' + codigoOuNome + '%');
			statement.setInt(3, cidadeId);
			rs = statement.executeQuery();
			while (rs.next()) {
				if(rs.isFirst()) {					
					precoProduto = new PrecoProduto();
					empresa = new Empresa();
					codigoAtual = rs.getString(1);
					codigoAnterior = codigoAtual;									
					produto.set_codigo(rs.getString(1));
					produto.set_descricao(rs.getString(2));				
					empresa.setCnpj(rs.getString(5));
					empresa.setNomeEmpresa(rs.getString(6));
					empresa.setEnderecoDaEmpresa(rs.getString(7));
					precoProduto.set_preco(rs.getFloat(3));
					precoProduto.set_dataPreco(rs.getString(4));
					precoProduto.setEmpresa(empresa);				
					produto.getPrecoProdutos().add(precoProduto);
					produto.set_preco(rs.getFloat(3));
					produto.set_dataPreco(rs.getString(4));
					produtos.add(produto);
					
				}else {
					codigoAtual = rs.getString(1);
					if(codigoAtual.equals(codigoAnterior)) {						
						precoProduto = new PrecoProduto();
						empresa = new Empresa();						
						empresa.setCnpj(rs.getString(5));
						empresa.setNomeEmpresa(rs.getString(6));
						empresa.setEnderecoDaEmpresa(rs.getString(7));
						precoProduto.set_preco(rs.getFloat(3));
						precoProduto.set_dataPreco(rs.getString(4));
						precoProduto.setEmpresa(empresa);
						produto.getPrecoProdutos().add(precoProduto);						
					}else {
						produto = new Produto();
						precoProduto = new PrecoProduto();
						empresa = new Empresa();
						produto.set_codigo(rs.getString(1));
						produto.set_descricao(rs.getString(2));
						produto.set_preco(rs.getFloat(3));
						empresa.setCnpj(rs.getString(5));
						empresa.setNomeEmpresa(rs.getString(6));
						empresa.setEnderecoDaEmpresa(rs.getString(7));
						precoProduto.set_preco(rs.getFloat(3));
						precoProduto.set_dataPreco(rs.getString(4));
						precoProduto.setEmpresa(empresa);	
						codigoAnterior = codigoAtual;
						produto.getPrecoProdutos().add(precoProduto);						
						produtos.add(produto);
					}
				}				
			}

			return produtos;
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return produtos;
	}
	
	public ListaDeCompras selectMenorPreco(ListaDeCompras lista) {		
		Produto produto;				
		Empresa empresa;
		PrecoProduto precoProduto;
		ListaDeCompras retorno = new ListaDeCompras();
		retorno.setEmpresas(lista.getEmpresas());
		conexaoBanco = ModuloConexao.conector();
		

		try {
			for(int contEmpresa = 0; contEmpresa<lista.getEmpresas().size();contEmpresa++) {
				String sql = "select pdp.produto_cod,p.produto_descricao,pdp.produto_preco,DATE_FORMAT(pdp.data_preco,'%d-%m-%Y'),e.empresa_cnpj,e.empresa_nome,e.empresa_endereco from preco_de_produto pdp join empresa e on pdp.empresa_cnpj = e.empresa_cnpj join produto p on pdp.produto_cod = p.produto_cod where pdp.produto_cod = ? and e.empresa_cnpj = ? order by pdp.produto_preco";
				statement = conexaoBanco.prepareStatement(sql);
				
				for(int contProduto = 0; contProduto<lista.getProdutos().size();contProduto++) {
					statement.setString(1, lista.getProdutos().get(contProduto).get_codigo());
					statement.setString(2, lista.getEmpresas().get(contEmpresa).getCnpj());				
					rs = statement.executeQuery();
					
					if (rs.next()) {
							
							precoProduto = new PrecoProduto();
							produto = new Produto();
							empresa = new Empresa();
							produto.set_codigo(rs.getString(1));
							produto.set_descricao(rs.getString(2));	
							produto.setQuantidade(lista.getProdutos().get(contProduto).getQuantidade());
							
							empresa.setCnpj(rs.getString(5));
							empresa.setNomeEmpresa(rs.getString(6));
							empresa.setEnderecoDaEmpresa(rs.getString(7));
							
							precoProduto.setEmpresa(empresa);
							precoProduto.set_preco(rs.getFloat(3));
							precoProduto.set_dataPreco(rs.getString(4));	
							
							produto.getPrecoProdutos().add(precoProduto);
							produto.set_preco(rs.getFloat(3));
							produto.set_dataPreco(rs.getString(4));							
							retorno.getProdutos().add(produto);	
							retorno.getEmpresas().get(contEmpresa).getProdutos().add(produto);
							
					}						
					
				}			
				
			}
			
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return retorno;
	}
	
	public ListaDeCompras select(ListaDeCompras listaDeCompras) {		
		conexaoBanco = ModuloConexao.conector();
		float total = 0;
		String sql = "select p.produto_cod,p.produto_descricao,pdp.data_preco,pdp.produto_preco from preco_de_produto pdp join empresa e on e.empresa_cnpj = pdp.empresa_cnpj join produto p on p.produto_cod = pdp.produto_cod where pdp.produto_cod = ? and pdp.empresa_cnpj = ?";
		try {
			statement = conexaoBanco.prepareStatement(sql);

			for (int contEmpresas = 0; contEmpresas < listaDeCompras.getEmpresas().size(); contEmpresas++) {
				
				for (int contProdutos = 0; contProdutos < listaDeCompras.getProdutos().size(); contProdutos++) {				

					statement.setString(1, listaDeCompras.getProdutos().get(contProdutos).get_codigo());
					statement.setString(2, listaDeCompras.getEmpresas().get(contEmpresas).getCnpj());
					rs = statement.executeQuery();
					while (rs.next()) {						
						float preco = rs.getFloat(4);
						total = total+(preco * listaDeCompras.getProdutos().get(contProdutos).getQuantidade());											
					}
				}
				listaDeCompras.getEmpresas().get(contEmpresas).setPrecoLista(total);
				total = 0;

			}	
			return listaDeCompras;
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return listaDeCompras;
	}
}

package utilitarios;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import entidade.Empresa;
import entidade.Produto;

import java.io.IOException;

/**
 *
 * @author Nyguel
 */
public class ExtraiDadosHtml {

    Document doc;

    public ExtraiDadosHtml() {

    }

    public Empresa pesquisar(String url)  {
        String descricaoDoProduto, unidadeDoProduto, precoDeProduto, dataNotaFiscal, cnpjEmpresa, codigoDoProduto,nomeDaEmpresa,enderecoDaEmpresa;
        float price;
        Empresa empresa = new Empresa();

        try {
			this.doc = Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			empresa.setCidadeid(0);
			return empresa;
		}
        int quantidadeDeItens = Integer.parseInt(doc.getElementsByClass("totalNumb").first().text());
        for (int i = 1; i <= quantidadeDeItens; i++) {
        
            descricaoDoProduto = doc.getElementById("Item + " + i).getElementsByClass("txtTit").first().text();
            codigoDoProduto = doc.getElementById("Item + " + i).getElementsByClass("RCod").first().text().replaceAll("[Código: ().]", "");
            unidadeDoProduto = doc.getElementById("Item + " + i).getElementsByClass("RUN").first().text().replaceAll("UN: ", "");
            precoDeProduto = doc.getElementById("Item + " + i).getElementsByClass("RvlUnit").first().text().replaceAll("Vl. Unit.: ", "").replaceAll(",", ".");
            price = Float.valueOf(precoDeProduto);
            Produto produto = new Produto(codigoDoProduto,descricaoDoProduto, unidadeDoProduto); 
            dataNotaFiscal = doc.getElementById("infos").getElementsByTag("li").first().text();
            dataNotaFiscal = dataNotaFiscal.substring(dataNotaFiscal.indexOf("/") - 2, dataNotaFiscal.indexOf("/") + 8);   
            produto.set_dataPreco(dataNotaFiscal);
            produto.set_preco(price);
            if(i==0) {
            	empresa.getProdutos().add(produto);
            }else {
            	for(int j = 0;j<empresa.getProdutos().size();j++) {
            		if(!(empresa.getProdutos().get(j).get_codigo().equals(produto.get_codigo()))) {
            			empresa.getProdutos().add(produto);
            		}
            	}            		
            }            
        }        
        cnpjEmpresa = doc.getElementsByClass("txtCenter").first().getElementsByClass("text").get(0).text().replaceAll("[CNPJ: / . -]", "");;
        nomeDaEmpresa = doc.getElementsByClass("txtCenter").first().getElementsByClass("txtTopo").get(0).text(); 
        enderecoDaEmpresa = doc.getElementsByClass("txtCenter").first().getElementsByClass("text").get(1).text();
        empresa.setCnpj(cnpjEmpresa);
        empresa.setEnderecoDaEmpresa(enderecoDaEmpresa);
        empresa.setNomeEmpresa(nomeDaEmpresa);
        String cidade = enderecoDaEmpresa.substring((enderecoDaEmpresa.length()-4)-8,enderecoDaEmpresa.length()-4);
        cidade = cidade.replaceAll("[, ]", "");
        if(cidade.equalsIgnoreCase("ITABUNA")) {
        	empresa.setCidadeid(2);
        }else if(cidade.equalsIgnoreCase("ILHEUS")) {
        	empresa.setCidadeid(1);
        }
        
        return empresa;        
        
    }
}


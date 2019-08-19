package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controladores.ControllerCidade;
import Controladores.ControllerEmpresa;
import entidade.Cidade;
import entidade.Empresa;
import utilitarios.LimpaBanco;

class ControllerEmpresaTest {

	@Test
	void test() {
		new ControllerEmpresa();
		new LimpaBanco().cidade();
		new LimpaBanco().empresa();
		
		String res = ControllerCidade.postCidade("Ilheus", 1);
		assertEquals("Cidade inserida com sucesso", res);
		res = ControllerCidade.postCidade("Itabuna", 2);
		assertEquals("Cidade inserida com sucesso", res);
		
		res = ControllerEmpresa.postEmpresa(new Empresa("00062616000197", "Rondelli Supermercado", " Tv. Lindolfo Collor,101, Malhado, Ilheus, BA",1));
		assertEquals("Empresa inserida com sucesso", res);		
		res = ControllerEmpresa.postEmpresa(new Empresa("39346861021169", "GBabosa Cencosud", "Av. Lomanto Júnior, 786, Pontal, Ilheus, BA",1 ));
		assertEquals("Empresa inserida com sucesso", res);		
		res = ControllerEmpresa.postEmpresa(new Empresa("04259757001127", "Meira Supermercados", "Av. Juracy Magalhães, 426, Centro, Itabuna, BA",2));
		assertEquals("Empresa inserida com sucesso", res);		
		res = ControllerEmpresa.postEmpresa(new Empresa("13436092000107", "Itão Supermercados", "Av. Amélia Amado, 296, , Santo Antõnio, Itabuna, BA",2));
		assertEquals("Empresa inserida com sucesso", res);
		res = ControllerEmpresa.postEmpresa(new Empresa("13436092000107", "Itão Supermercados", "Av. Amélia Amado, 296, , Santo Antõnio, Itabuna, BA",2));
		assertEquals("Problema ao inserir empresa", res);
		res = ControllerEmpresa.getAllEmpresa();
		assertEquals("Rondelli Supermercado%Meira Supermercados%Itão Supermercados%GBabosa Cencosud%", res);

		
		res = ControllerEmpresa.getEmpresa("00062616000197");
		assertEquals("00062616000197-Rondelli Supermercado, Tv. Lindolfo Collor,101, Malhado, Ilheus, BA,1", res);
		
		res = ControllerEmpresa.getEmpresa("39346861021169");
		assertEquals("39346861021169-GBabosa Cencosud,Av. Lomanto Júnior, 786, Pontal, Ilheus, BA,1", res);
		
		res = ControllerEmpresa.getEmpresa("04259757001127");
		assertEquals("04259757001127-Meira Supermercados,Av. Juracy Magalhães, 426, Centro, Itabuna, BA,2", res);
		
		res = ControllerEmpresa.getEmpresa("111111111111111");
		assertEquals("Empresa não encontrada", res);
		res = ControllerEmpresa.getEmpresa("");
		assertEquals("Empresa não encontrada", res);
		
		res = ControllerEmpresa.postEmpresa(new Empresa("111111111111111", "", "",2));
		assertEquals("Empresa inserida com sucesso", res);
		
		res = ControllerEmpresa.deleteEmpresa("111111111111111");
		assertEquals("Empresa excluida com sucesso", res);
				
		
		res = ControllerEmpresa.deleteEmpresa("1111111111111");
		assertEquals("Problema ao excluir a empresa", res);
		res = ControllerEmpresa.deleteEmpresa("2222222222222");
		assertEquals("Problema ao excluir a empresa", res);
		
		res = ControllerEmpresa.putEmpresa(new Empresa("13436092000107", "Mercado Novo", "Av. Amélia Amado, 296, , Santo Antõnio, Itabuna, BA",2));
		assertEquals("Empresa atualizada com sucesso", res);
		
		res = ControllerEmpresa.getAllEmpresa();
		assertEquals("Rondelli Supermercado%Meira Supermercados%Mercado Novo%GBabosa Cencosud%", res);
		res = ControllerCidade.putCidade(new Cidade(1,"Ilheus"));
		assertEquals("Cidade atualizada com sucesso", res);
		res = ControllerEmpresa.putEmpresa(new Empresa("13436092000107", "Itão Supermercados", "Av. Amélia Amado, 296, , Santo Antõnio, Itabuna, BA",2));
		assertEquals("Empresa atualizada com sucesso", res);
		res = ControllerEmpresa.putEmpresa(new Empresa("","","",1));
		assertEquals("Problema ao atualizar a empresa", res);
		
	}

}

package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controladores.ControllerCidade;
import entidade.Cidade;
import utilitarios.LimpaBanco;

class ControllerCidadeTest {

	@Test
	void test() {
		new LimpaBanco().cidade();
		new ControllerCidade();
		String res = ControllerCidade.postCidade("Ilheus", 1);
		assertEquals("Cidade inserida com sucesso", res);
		res = ControllerCidade.postCidade("Itabuna", 2);
		assertEquals("Cidade inserida com sucesso", res);		
		res = ControllerCidade.postCidade("Itajuipe", 3);
		assertEquals("Cidade inserida com sucesso", res);

		res = ControllerCidade.postCidade("Itajuipe",3);
		assertEquals("Problema ao inserir cidade", res);
		
		res = ControllerCidade.getAllCidade();		
		assertEquals("1-Ilheus%2-Itabuna%3-Itajuipe%", res);		
		res = ControllerCidade.postCidade("Canavieiras", 4);
		assertEquals("Cidade inserida com sucesso", res);		
		res = ControllerCidade.getAllCidade();		
		assertEquals("1-Ilheus%2-Itabuna%3-Itajuipe%4-Canavieiras%", res);

		res = ControllerCidade.getCidade(1);
		assertEquals("1-Ilheus", res);
		res = ControllerCidade.getCidade(2);
		assertEquals("2-Itabuna", res);
		res = ControllerCidade.getCidade(3);
		assertEquals("3-Itajuipe", res);
		res = ControllerCidade.getCidade(5);
		assertEquals("cidade não encontrada", res);
		res = ControllerCidade.getCidade(4);
		assertEquals("4-Canavieiras", res);
		
		res = ControllerCidade.deleteCidade(3);
		assertEquals("Cidade excluida com sucesso", res);
		res = ControllerCidade.deleteCidade(4);
		assertEquals("Cidade excluida com sucesso", res);		
		
		res = ControllerCidade.deleteCidade(3);
		assertEquals("Problema ao excluir a cidade", res);
		res = ControllerCidade.deleteCidade(4);
		assertEquals("Problema ao excluir a cidade", res);
		
		res = ControllerCidade.putCidade(new Cidade(1,"Itapitanga"));
		assertEquals("Cidade atualizada com sucesso", res);
		res = ControllerCidade.getAllCidade();
		assertEquals("1-Itapitanga%2-Itabuna%", res);
		res = ControllerCidade.putCidade(new Cidade(1,"Ilheus"));
		assertEquals("Cidade atualizada com sucesso", res);
		
		res = ControllerCidade.putCidade(new Cidade(4,"Canes"));
		assertEquals("Problema ao atualizar a cidade", res);
		
		
		
	}

}

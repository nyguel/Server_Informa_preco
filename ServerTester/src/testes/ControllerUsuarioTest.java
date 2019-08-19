package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Controladores.ControllerUsuario;

class ControllerUsuarioTest {

	@Test
	void test() {
		new ControllerUsuario();
		String res = ControllerUsuario.getAllUsuario();
		System.out.println(res);
		assertEquals("1-nygueltitan@gmail.com-12345%2-martamagda@gmail.com-12345%", res);
	}

}

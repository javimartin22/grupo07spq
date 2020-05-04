package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class UsuarioTest {
	private Usuario usuario;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(UsuarioTest.class);
	}
	@Before
	public void setUp() {
		usuario = new Usuario();
		usuario = new Usuario("Jorgico", "1234", 1);
	}
	
	//Test Getters:
	@Test
	public void testGetNickname() {
		assertEquals("Jorgico", usuario.getNickname());
	}
	@Test
	public void testGetContrasenya() {
		assertEquals("1234", usuario.getContrasenya());
	}
	@Test
	public void testGetTipo() {
		assertEquals(1, usuario.getTipo());
	}
	
	//Test Setters:
	@Test
	public void testSetNickname() {
		usuario.setNickname("Jorgico1");
		assertEquals("Jorgico1", usuario.getNickname());
	}
	@Test
	public void testSetContrasenya() {
		usuario.setContrasenya("12345");
		assertEquals("12345", usuario.getContrasenya());
	}
	@Test
	public void testSetTipo() {
		usuario.setTipo(2);
		assertEquals(2, usuario.getTipo());
	}
}

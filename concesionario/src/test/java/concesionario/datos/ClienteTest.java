package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import concesionario.datos.Cliente;


public class ClienteTest {
	
	private Cliente cliente;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteTest.class);
	}

	@Before
	public void setUp() {
		cliente = new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
	}
	
	
	//Test Getters
	@Test
	public void testGetNombre() {
		assertEquals("javi", cliente.getNombre());
	}
	@Test
	public void testGetDNI() {
		assertEquals("12345678K", cliente.getDNI());
	}
	@Test
	public void testGetApellido() {
		assertEquals("martin", cliente.getApellido());
	}
	@Test
	public void testGetEmail() {
		assertEquals("javi@gmail.com", cliente.getEmail());
	}
	@Test
	public void testGetSexo() {
		assertEquals("masculino", cliente.getSexo());
	}
	@Test
	public void testGetCiudad() {
		assertEquals("bilbo", cliente.getCiudad());
	}
	@Test
	public void testGetCodigoPostal() {
		assertEquals(46009, cliente.getCodigoPostal());
	}
	@Test
	public void testGetDireccion() {
		assertEquals("kalea", cliente.getDireccion());
	}
	@Test
	public void testGetNumeroTelefono() {
		assertEquals("666777872", cliente.getNumeroTelefono());
	}
	
	//Setters
}

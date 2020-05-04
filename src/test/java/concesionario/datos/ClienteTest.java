package concesionario.datos;

import static org.junit.Assert.assertEquals;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	
	private Cliente cliente;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteTest.class);
	}

	@Before
	public void setUp() {
		cliente = new Cliente();
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
	@Test 
	public void testSetDNI() {
		cliente.setDNI("11122233V");
		assertEquals("11122233V", cliente.getDNI());
	}
	@Test 
	public void testSetNombre() {
		cliente.setNombre("Leire");
		assertEquals("Leire", cliente.getNombre());
	}
	@Test 
	public void testSetApellido() {
		cliente.setApellido("Lopez");
		assertEquals("Lopez", cliente.getApellido());
	}
	@Test 
	public void testSetEmail() {
		cliente.setEmail("hola@gmail.com");
		assertEquals("hola@gmail.com", cliente.getEmail());
	}
	@Test 
	public void testSetSexo() {
		cliente.setSexo("Femenino");
		assertEquals("Femenino", cliente.getSexo());
	}
	@Test 
	public void testSetCiudad() {
		cliente.setCiudad("Paris");
		assertEquals("Paris", cliente.getCiudad());
	}
	@Test 
	public void testSetCodigoPostal() {
		cliente.setCodigoPostal(48012);
		assertEquals(48012, cliente.getCodigoPostal());
	}
	@Test 
	public void testSetDireccion() {
		cliente.setDireccion("calle");
		assertEquals("calle", cliente.getDireccion());
	}
	@Test 
	public void testSetNumeroTelefono() {
		cliente.setNumeroTelefono("654654654");
		assertEquals("654654654", cliente.getNumeroTelefono());
	}
}

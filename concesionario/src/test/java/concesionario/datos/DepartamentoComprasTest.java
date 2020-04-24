package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class DepartamentoComprasTest {

	private DepartamentoCompras departamentoCompras;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		departamentoCompras = new DepartamentoCompras("Jorge", "12345", "12345667Ñ", "Jorge", "Martinez", "Masculino", "em@gmail.com", "Barakaldo", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
	}
	
	//Test Getters:
	@Test
	public void testGetNickname() {
		assertEquals("Jorge", departamentoCompras.getNickname());
	}
	@Test
	public void testGetContrasenia() {
		assertEquals("12345", departamentoCompras.getContrasenia());
	}
	@Test
	public void testGetDni() {
		assertEquals("12345667Ñ", departamentoCompras.getDNI());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Jorge", departamentoCompras.getNombre());
	}
	@Test
	public void testGetApellido() {
		assertEquals("Martinez", departamentoCompras.getApellido());
	}
	@Test
	public void testGetSexo() {
		assertEquals("Masculino", departamentoCompras.getSexo());
	}
	@Test
	public void testGetEmail() {
		assertEquals("em@gmail.com", departamentoCompras.getEmail());
	}
	@Test
	public void testGetCiudad() {
		assertEquals("Barakaldo", departamentoCompras.getCiudad());
	}
	@Test
	public void testGetCodigoPostal() {
		assertEquals(48008, departamentoCompras.getCodigoPostal());
	}
	@Test
	public void testGetDireccion() {
		assertEquals("Abando", departamentoCompras.getDireccion());
	}
	@Test
	public void testGetNSS() {
		assertEquals("1231", departamentoCompras.getNSS());
	}
	@Test
	public void testGetNumeroCuenta() {
		assertEquals("1444", departamentoCompras.getNumeroCuenta());
	}
	@Test
	public void testGetSueldo() {
		assertEquals(1200, departamentoCompras.getSueldo());
	}
	@Test
	public void testGetNumeroTelefono() {
		assertEquals("665665665", departamentoCompras.getNumeroTelefono());
	}
	@Test
	public void testGetPedidos(){
		assertEquals(10, departamentoCompras.getPedidos());
	}
	
	//Test Setters
	@Test
	public void testSetNickname() {
		departamentoCompras.setNickname("Jorgico");
		assertEquals("Jorgico", departamentoCompras.getNickname());
	}
	@Test
	public void testSetContrasenia() {
		departamentoCompras.setContrasenia("1234");
		assertEquals("1234", departamentoCompras.getContrasenia());
	}
	@Test
	public void testSetDni() {
		departamentoCompras.setDNI("12345667Z");
		assertEquals("12345667Z", departamentoCompras.getDNI());
	}
	@Test
	public void testSetNombre() {
		departamentoCompras.setNombre("Jorge");
		assertEquals("Jorge", departamentoCompras.getNombre());
	}
	@Test
	public void testSetApellido() {
		departamentoCompras.setApellido("Gonzalez");
		assertEquals("Gonzalez", departamentoCompras.getApellido());
	}
	@Test
	public void testSetSexo() {
		departamentoCompras.setSexo("Masculino");
		assertEquals("Masculino", departamentoCompras.getSexo());
	}
	@Test
	public void testSetEmail() {
		departamentoCompras.setEmail("ema@gmail.com");
		assertEquals("ema@gmail.com", departamentoCompras.getEmail());
	}
	@Test
	public void testSetCiudad() {
		departamentoCompras.setCiudad("Bilbao");
		assertEquals("Bilbao", departamentoCompras.getCiudad());
	}
	@Test
	public void testSetCodigoPostal() {
		departamentoCompras.setCodigoPostal(48007);
		assertEquals(48007, departamentoCompras.getCodigoPostal());
	}
	@Test
	public void testSetDireccion() {
		departamentoCompras.setDireccion("Abando 1");
		assertEquals("Abando 1", departamentoCompras.getDireccion());
	}
	@Test
	public void testSetNSS() {
		departamentoCompras.setNSS("12314");
		assertEquals("12314", departamentoCompras.getNSS());
	}
	@Test
	public void testSetNumeroCuenta() {
		departamentoCompras.setNumeroCuenta("14444");
		assertEquals("14444", departamentoCompras.getNumeroCuenta());
	}
	@Test
	public void testSetSueldo() {
		departamentoCompras.setSueldo(1250);
		assertEquals(1250, departamentoCompras.getSueldo());
	}
	@Test
	public void testSetNumeroTelefono() {
		departamentoCompras.setNumeroTelefono("665665664");
		assertEquals("665665664", departamentoCompras.getNumeroTelefono());
	}
	@Test
	public void testSetPedidos(){
		departamentoCompras.setPedidos(7);
		assertEquals(7, departamentoCompras.getPedidos());
	}
}

package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class MecanicoTest {
	private Mecanico mecanico;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		mecanico = new Mecanico("user", "pass", 1, "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
	}
	
	//Test Getters:
	@Test
	public void testGetNickname() {
		assertEquals("user", mecanico.getNickname());
	}
	@Test
	public void testGetContrasenia() {
		assertEquals("pass", mecanico.getContrasenia());
	}
	@Test
	public void testGetTipo() {
		assertEquals(1, mecanico.getTipo());
	}
	@Test
	public void testGetDni() {
		assertEquals("12345667V", mecanico.getDNI());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Kevin", mecanico.getNombre());
	}
	@Test
	public void testGetApellido() {
		assertEquals("Ibañez", mecanico.getApellido());
	}
	@Test
	public void testGetSexo() {
		assertEquals("Masculino", mecanico.getSexo());
	}
	@Test
	public void testGetEmail() {
		assertEquals("em@gmail.com", mecanico.getEmail());
	}
	@Test
	public void testGetCiudad() {
		assertEquals("City", mecanico.getCiudad());
	}
	@Test
	public void testGetCodigoPostal() {
		assertEquals(48008, mecanico.getCodigoPostal());
	}
	@Test
	public void testGetDireccion() {
		assertEquals("Abando", mecanico.getDireccion());
	}
	@Test
	public void testGetNSS() {
		assertEquals("1231", mecanico.getNSS());
	}
	@Test
	public void testGetNumeroCuenta() {
		assertEquals("1444", mecanico.getNumeroCuenta());
	}
	@Test
	public void testGetSueldo() {
		assertEquals(1200, mecanico.getSueldo());
	}
	@Test
	public void testGetNumeroTelefono() {
		assertEquals("665665665", mecanico.getNumeroTelefono());
	}
	@Test
	public void testGetHoras() {
		assertEquals(10, mecanico.getHoras());
	}
	
	//Test Setters
	@Test
	public void testSetNickname() {
		mecanico.setNickname("Pedro");
		assertEquals("Pedro", mecanico.getNickname());
	}
	@Test
	public void testSetContrasenia() {
		mecanico.setContrasenia("1234");
		assertEquals("1234", mecanico.getContrasenia());
	}
	@Test
	public void testSetTipo() {
		mecanico.setTipo(2);;
		assertEquals(2, mecanico.getTipo());
	}
	@Test
	public void testSetDni() {
		mecanico.setDNI("12345667V");
		assertEquals("12345667V", mecanico.getDNI());
	}
	@Test
	public void testSetNombre() {
		mecanico.setNombre("Kevin");
		assertEquals("Kevin", mecanico.getNombre());
	}
	@Test
	public void testSetApellido() {
		mecanico.setApellido("Ibañez");
		assertEquals("Ibañez", mecanico.getApellido());
	}
	@Test
	public void testSetSexo() {
		mecanico.setSexo("Masculino");
		assertEquals("Masculino", mecanico.getSexo());
	}
	@Test
	public void testSetEmail() {
		mecanico.setEmail("em@gmail.com");
		assertEquals("em@gmail.com", mecanico.getEmail());
	}
	@Test
	public void testSetCiudad() {
		mecanico.setCiudad("City");
		assertEquals("City", mecanico.getCiudad());
	}
	@Test
	public void testSetCodigoPostal() {
		mecanico.setCodigoPostal(48008);
		assertEquals(48008, mecanico.getCodigoPostal());
	}
	@Test
	public void testSetDireccion() {
		mecanico.setDireccion("Abando");
		assertEquals("Abando", mecanico.getDireccion());
	}
	@Test
	public void testSetNSS() {
		mecanico.setNSS("1231");
		assertEquals("1231", mecanico.getNSS());
	}
	@Test
	public void testSetNumeroCuenta() {
		mecanico.setNumeroCuenta("1444");
		assertEquals("1444", mecanico.getNumeroCuenta());
	}
	@Test
	public void testSetSueldo() {
		mecanico.setSueldo(1200);
		assertEquals(1200, mecanico.getSueldo());
	}
	@Test
	public void testSetNumeroTelefono() {
		mecanico.setNumeroTelefono("665665665");
		assertEquals("665665665", mecanico.getNumeroTelefono());
	}
	@Test
	public void testSetHoras() {
		mecanico.setHoras(10);
		assertEquals(10, mecanico.getHoras());
	}
}

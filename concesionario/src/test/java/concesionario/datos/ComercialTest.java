package concesionario.datos;

import static org.junit.Assert.assertEquals;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

public class ComercialTest {
	
	private Comercial comercial;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
	}
	
	//Test Getters:
	@Test
	public void testGetNickname() {
		assertEquals("user", comercial.getNickname());
	}
	@Test
	public void testGetContrasenia() {
		assertEquals("pass", comercial.getContrasenia());
	}
	@Test
	public void testGetDni() {
		assertEquals("12345667V", comercial.getDNI());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Kevin", comercial.getNombre());
	}
	@Test
	public void testGetApellido() {
		assertEquals("Ibañez", comercial.getApellido());
	}
	@Test
	public void testGetSexo() {
		assertEquals("Masculino", comercial.getSexo());
	}
	@Test
	public void testGetEmail() {
		assertEquals("em@gmail.com", comercial.getEmail());
	}
	@Test
	public void testGetCiudad() {
		assertEquals("City", comercial.getCiudad());
	}
	@Test
	public void testGetCodigoPostal() {
		assertEquals(48008, comercial.getCodigoPostal());
	}
	@Test
	public void testGetDireccion() {
		assertEquals("Abando", comercial.getDireccion());
	}
	@Test
	public void testGetNSS() {
		assertEquals("1231", comercial.getNSS());
	}
	@Test
	public void testGetNumeroCuenta() {
		assertEquals("1444", comercial.getNumeroCuenta());
	}
	@Test
	public void testGetSueldo() {
		assertEquals(1200, comercial.getSueldo());
	}
	@Test
	public void testGetNumeroTelefono() {
		assertEquals("665665665", comercial.getNumeroTelefono());
	}
	@Test
	public void testGetTipoEmpleado() {
		assertEquals(0, comercial.getTipoEmpleado());
	}
	@Test
	public void testGetCochesVendidos() {
		assertEquals(2, comercial.getCochesVendidos());
	}
	@Test
	public void testGetImporteObtenido() {
		assertEquals(300, comercial.getImporteObetenido());
	}
	@Test
	public void testGetHoras() {
		assertEquals(10, comercial.getHoras());
	}
	
	//Test Setters
	@Test
	public void testSetNickname() {
		comercial.setNickname("Pedro");
		assertEquals("Pedro", comercial.getNickname());
	}
	@Test
	public void testSetContrasenia() {
		comercial.setContrasenia("1234");
		assertEquals("1234", comercial.getContrasenia());
	}
	@Test
	public void testSetDni() {
		comercial.setDNI("12345667V");
		assertEquals("12345667V", comercial.getDNI());
	}
	@Test
	public void testSetNombre() {
		comercial.setNombre("Kevin");
		assertEquals("Kevin", comercial.getNombre());
	}
	@Test
	public void testSetApellido() {
		comercial.setApellido("Ibañez");
		assertEquals("Ibañez", comercial.getApellido());
	}
	@Test
	public void testSetSexo() {
		comercial.setSexo("Masculino");
		assertEquals("Masculino", comercial.getSexo());
	}
	@Test
	public void testSetEmail() {
		comercial.setEmail("em@gmail.com");
		assertEquals("em@gmail.com", comercial.getEmail());
	}
	@Test
	public void testSetCiudad() {
		comercial.setCiudad("City");
		assertEquals("City", comercial.getCiudad());
	}
	@Test
	public void testSetCodigoPostal() {
		comercial.setCodigoPostal(48008);
		assertEquals(48008, comercial.getCodigoPostal());
	}
	@Test
	public void testSetDireccion() {
		comercial.setDireccion("Abando");
		assertEquals("Abando", comercial.getDireccion());
	}
	@Test
	public void testSetNSS() {
		comercial.setNSS("1231");
		assertEquals("1231", comercial.getNSS());
	}
	@Test
	public void testSetNumeroCuenta() {
		comercial.setNumeroCuenta("1444");
		assertEquals("1444", comercial.getNumeroCuenta());
	}
	@Test
	public void testSetSueldo() {
		comercial.setSueldo(1200);
		assertEquals(1200, comercial.getSueldo());
	}
	@Test
	public void testSetNumeroTelefono() {
		comercial.setNumeroTelefono("665665665");
		assertEquals("665665665", comercial.getNumeroTelefono());
	}
	@Test
	public void testSetTipoEmpleado() {
		comercial.setTipoEmpleado(1);
		assertEquals(1, comercial.getTipoEmpleado());
	}
	@Test
	public void testSetCochesVendidos() {
		comercial.setCochesVendidos(2);
		assertEquals(2, comercial.getCochesVendidos());
	}
	@Test
	public void testSetImporteObtenido() {
		comercial.setImporteObetenido(300);
		assertEquals(300, comercial.getImporteObetenido());
	}
	@Test
	public void testSetHoras() {
		comercial.setHoras(10);
		assertEquals(10, comercial.getHoras());
	}
}

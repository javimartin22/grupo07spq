package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import concesionario.datos.Comercial;


public class ComercialTest {
	
	private Comercial comercial;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
	}
	
	
	
	//Test Getters
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
	
	//Setters

	
}

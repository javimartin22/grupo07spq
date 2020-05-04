package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class EmpleadoTest {
	private Empleado empleado;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EmpleadoTest.class);
	}
	@Before
	public void setUp() {
		empleado = new Empleado();
		empleado = new Empleado("user", "pass", 1, "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0);
	}
	
	//Test Getters:
	@Test
	public void testGetNickname() {
		assertEquals("user", empleado.getNickname());
	}
	@Test
	public void testGetContrasenia() {
		assertEquals("pass", empleado.getContrasenia());
	}
	@Test
	public void testGetTipo() {
		assertEquals(1, empleado.getTipo());
	}
	@Test
	public void testGetDni() {
		assertEquals("12345667V", empleado.getDNI());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Kevin", empleado.getNombre());
	}
	@Test
	public void testGetApellido() {
		assertEquals("Ibañez", empleado.getApellido());
	}
	@Test
	public void testGetSexo() {
		assertEquals("Masculino", empleado.getSexo());
	}
	@Test
	public void testGetEmail() {
		assertEquals("em@gmail.com", empleado.getEmail());
	}
	@Test
	public void testGetCiudad() {
		assertEquals("City", empleado.getCiudad());
	}
	@Test
	public void testGetCodigoPostal() {
		assertEquals(48008, empleado.getCodigoPostal());
	}
	@Test
	public void testGetDireccion() {
		assertEquals("Abando", empleado.getDireccion());
	}
	@Test
	public void testGetNSS() {
		assertEquals("1231", empleado.getNSS());
	}
	@Test
	public void testGetNumeroCuenta() {
		assertEquals("1444", empleado.getNumeroCuenta());
	}
	@Test
	public void testGetSueldo() {
		assertEquals(1200, empleado.getSueldo());
	}
	@Test
	public void testGetNumeroTelefono() {
		assertEquals("665665665", empleado.getNumeroTelefono());
	}
	@Test
	public void testGetTipoEmpleado() {
		assertEquals(0, empleado.getTipoEmpleado());
	}
		
	//Test Setters
	@Test
	public void testSetNickname() {
		empleado.setNickname("Pedro");
		assertEquals("Pedro", empleado.getNickname());
	}
	@Test
	public void testSetContrasenia() {
		empleado.setContrasenia("1234");
		assertEquals("1234", empleado.getContrasenia());
	}
	@Test
	public void testSetTipo() {
		empleado.setTipo(2);
		assertEquals(2, empleado.getTipo());
	}
	@Test
	public void testSetDni() {
		empleado.setDNI("12345667V");
		assertEquals("12345667V", empleado.getDNI());
	}
	@Test
	public void testSetNombre() {
		empleado.setNombre("Kevin");
		assertEquals("Kevin", empleado.getNombre());
	}
	@Test
	public void testSetApellido() {
		empleado.setApellido("Ibañez");
		assertEquals("Ibañez", empleado.getApellido());
	}
	@Test
	public void testSetSexo() {
		empleado.setSexo("Masculino");
		assertEquals("Masculino", empleado.getSexo());
	}
	@Test
	public void testSetEmail() {
		empleado.setEmail("em@gmail.com");
		assertEquals("em@gmail.com", empleado.getEmail());
	}
	@Test
	public void testSetCiudad() {
		empleado.setCiudad("City");
		assertEquals("City", empleado.getCiudad());
	}
	@Test
	public void testSetCodigoPostal() {
		empleado.setCodigoPostal(48008);
		assertEquals(48008, empleado.getCodigoPostal());
	}
	@Test
	public void testSetDireccion() {
		empleado.setDireccion("Abando");
		assertEquals("Abando", empleado.getDireccion());
	}
	@Test
	public void testSetNSS() {
		empleado.setNSS("1231");
		assertEquals("1231", empleado.getNSS());
	}
	@Test
	public void testSetNumeroCuenta() {
		empleado.setNumeroCuenta("1444");
		assertEquals("1444", empleado.getNumeroCuenta());
	}
	@Test
	public void testSetSueldo() {
		empleado.setSueldo(1200);
		assertEquals(1200, empleado.getSueldo());
	}
	@Test
	public void testSetNumeroTelefono() {
		empleado.setNumeroTelefono("665665665");
		assertEquals("665665665", empleado.getNumeroTelefono());
	}
	@Test
	public void testSetTipoEmpleado() {
		empleado.setTipoEmpleado(1);
		assertEquals(1, empleado.getTipoEmpleado());
	}
	
}

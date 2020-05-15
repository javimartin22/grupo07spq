package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class HorasEmpleadoTest {

	private HorasEmpleados empleadoHoras;
	
	public static junit.framework.Test suit(){
		return new JUnit4TestAdapter(HorasEmpleadoTest.class);
	}
	
	@Before
	public void setUp() {
		empleadoHoras = new HorasEmpleados();
		empleadoHoras = new HorasEmpleados(10, 10, "Jorge");	
	}
	
	@Test
	public void testGetNickname() {
		assertEquals("Jorge", empleadoHoras.getNickname());
	}
	
	@Test
	public void testGetMinutos() {
		assertEquals(10, empleadoHoras.getMinutos());
	}
	
	@Test
	public void testGetHoras() {
		assertEquals(10, empleadoHoras.getHoras());
	}
	
	@Test
	public void testSetNickname() {
		empleadoHoras.setNickname("Jorgico1");
		assertEquals("Jorgico1", empleadoHoras.getNickname());
	}
	
	
	@Test
	public void testSetMinutos() {
		empleadoHoras.setMinutos(0);;
		assertEquals(0, empleadoHoras.getMinutos());
	}
	
	@Test
	public void testSetHoras() {
		empleadoHoras.setHoras(0);
		assertEquals(0, empleadoHoras.getHoras());
	}
	
	
}

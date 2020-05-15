package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class EmpleadoHorasTest {

	private EmpleadoHoras empleadoHoras;
	
	public static junit.framework.Test suit(){
		return new JUnit4TestAdapter(EmpleadoHorasTest.class);
	}
	
	@Before
	public void setUp() {
		empleadoHoras = new EmpleadoHoras();
		empleadoHoras = new EmpleadoHoras("Jorgico", "Jorge", "12345678A", 10);	
	}
	
	@Test
	public void testGetNickname() {
		assertEquals("Jorgico", empleadoHoras.getNickname());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Jorge", empleadoHoras.getNombre());
	}
	
	@Test
	public void testGetDni() {
		assertEquals("12345678A", empleadoHoras.getDni());
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
	public void testSetNombre() {
		empleadoHoras.setNombre("Jorge1");
		assertEquals("Jorge1", empleadoHoras.getNombre());
	}
	
	@Test
	public void testSetDni() {
		empleadoHoras.setDni("12345678B");
		assertEquals("12345678B", empleadoHoras.getDni());
	}
	
	@Test
	public void testSetHoras() {
		empleadoHoras.setHoras(0);
		assertEquals(0, empleadoHoras.getHoras());
	}
	
	
}

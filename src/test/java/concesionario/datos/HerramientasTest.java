package concesionario.datos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class HerramientasTest {

	private Herramientas h;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(HerramientasTest.class);
	}
	
	@Before
	public void setUp() {
		h = new Herramientas();
		h = new Herramientas("H1", "Alicates manguera", 4, "Alicates", "Oscaro");
	}
	@Test
	public void testGetCodigo() {
		assertEquals("H1", h.getCodigo());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Alicates manguera", h.getNombre());
	}
	@Test
	public void testGetTiempo() {
		assertEquals(4, h.getTiempo());
	}
	@Test
	public void testGetTipo() {
		assertEquals("Alicates", h.getTipo());
	}
	@Test
	public void testGetCodProveedor() {
		assertEquals("Oscaro", h.getCodProveedor());
	}
	@Test
	public void testSetCodigo() {
		h.setCodigo("H2");
		assertEquals("H2", h.getCodigo());
	}
	@Test
	public void testSetNombre() {
		h.setNombre("Presion aceite");
		assertEquals("Presion aceite", h.getNombre());
	}
	@Test
	public void testSetTiempo() {
		h.setTiempo(6);
		assertEquals(6, h.getTiempo());
	}
	@Test
	public void testSetTipo() {
		h.setTipo("Inspeccion");
		assertEquals("Inspeccion", h.getTipo());
	}
	@Test 
	public void testSetCodProveedor() {
		h.setCodProveedor("Oscaro");
		assertEquals("Oscaro", h.getCodProveedor());
	}
}

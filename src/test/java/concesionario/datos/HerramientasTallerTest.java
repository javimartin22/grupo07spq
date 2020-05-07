package concesionario.datos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class HerramientasTallerTest {

	private HerramientasTaller ht;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(HerramientasTallerTest.class);
	}
	
	@Before
	public void setUp() {
		ht = new HerramientasTaller();
		ht = new HerramientasTaller("H1", "herramienta1", 24,"Almacen H1 - Estanteria 1");
	}
	
	@Test
	public void testGetCodigo() {
		assertEquals("H1", ht.getCodigo());
	}
	@Test
	public void testGetNombre() {
		assertEquals("herramienta1", ht.getNombre());
	}
	@Test
	public void testGetUnidades() {
		assertEquals(24, ht.getUnidades());
	}
	@Test
	public void testGetUbicacion() {
		assertEquals("Almacen H1 - Estanteria 1", ht.getUbicacion());
	}
	@Test
	public void testSetCodigo() {
		ht.setCodigo("H2");
		assertEquals("H2", ht.getCodigo());
	}
	@Test
	public void testsetNombre() {
		ht.setNombre("herramienta2");
		assertEquals("herramienta2", ht.getNombre());
	}
	@Test
	public void testsetUnidades() {
		ht.setUnidades(30);
		assertEquals(30, ht.getUnidades());
	}
	@Test
	public void testsetUbicacion() {
		ht.setUbicacion("Almacen H2 - Estanteria 1");
		assertEquals("Almacen H2 - Estanteria 1", ht.getUbicacion());
	}
	
}

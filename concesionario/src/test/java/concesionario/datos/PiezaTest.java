package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import junit.framework.JUnit4TestAdapter;

public class PiezaTest {
	private Pieza pieza;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		pieza = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
	}
	
	//Test Getters:
	public void testGetCodigo() {
		assertEquals("P1", pieza.getCodigo());
	}
	public void testGetNombre() {
		assertEquals("Amortiguador", pieza.getNombre());
	}
	public void testGetUnidades() {
		assertEquals(5, pieza.getUnidades());
	}
	public void testGetUbicacion() {
		assertEquals("Almacen 1", pieza.getUbicacion());
	}
	
	//Test Setters:
		public void testSetCodigo() {
			pieza.setCodigo("P2");
			assertEquals("P2", pieza.getCodigo());
		}
		public void testsetNombre() {
			pieza.setNombre("Tuerca");
			assertEquals("Tuerca", pieza.getNombre());
		}
		public void testSetUnidades() {
			pieza.setUnidades(10);
			assertEquals(10, pieza.getUnidades());
		}
		public void testSetUbicacion() {
			pieza.setUbicacion("Almacen 2");
			assertEquals("Almacen 2", pieza.getUbicacion());
		}
}

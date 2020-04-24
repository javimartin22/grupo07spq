package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import junit.framework.JUnit4TestAdapter;

public class PiezaProveedoresTest {
	private PiezaProveedores pieza;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		pieza = new PiezaProveedores("P1", "Amortiguador", 5, "Ok");
	}
	
	//Test Getters:
	public void testGetCodigo() {
		assertEquals("P1", pieza.getCodigo());
	}
	public void testGetNombre() {
		assertEquals("Amortiguador", pieza.getNombre());
	}
	public void testGetUnidades() {
		assertEquals(5, pieza.getTiempo());
	}
	public void testGetUbicacion() {
		assertEquals("Ok", pieza.getEstado());
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
			pieza.setTiempo(10);
			assertEquals(10, pieza.getTiempo());
		}
		public void testSetUbicacion() {
			pieza.setEstado("Acabado");
			assertEquals("Acabado", pieza.getEstado());
		}
}

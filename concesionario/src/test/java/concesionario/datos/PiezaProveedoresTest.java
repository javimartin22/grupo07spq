package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class PiezaProveedoresTest {
	private PiezaProveedores pieza;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PiezaProveedoresTest.class);
	}
	@Before
	public void setUp() {
		pieza = new PiezaProveedores();
		pieza = new PiezaProveedores("P1", "Amortiguador", 5, "Ok", "Prov-1");
	}
	
	//Test Getters:
	@Test
	public void testGetCodigo() {
		assertEquals("P1", pieza.getCodigo());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Amortiguador", pieza.getNombre());
	}
	@Test
	public void testGetUnidades() {
		assertEquals(5, pieza.getTiempo());
	}
	@Test
	public void testGetUbicacion() {
		assertEquals("Ok", pieza.getTipo());
	}
	
	//Test Setters:
	@Test
		public void testSetCodigo() {
			pieza.setCodigo("P2");
			assertEquals("P2", pieza.getCodigo());
		}
	@Test
		public void testsetNombre() {
			pieza.setNombre("Tuerca");
			assertEquals("Tuerca", pieza.getNombre());
		}
	@Test
		public void testSetUnidades() {
			pieza.setTiempo(10);
			assertEquals(10, pieza.getTiempo());
		}
	@Test
		public void testSetUbicacion() {
			pieza.setTipo("Acabado");
			assertEquals("Acabado", pieza.getTipo());
	}
	
	@Test 
	public void testSetCodProveedor() {
		pieza.setCodProveedor("Prov-2");
		assertEquals("Prov-2", pieza.getCodProveedor());
	}
}

package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class SolicitudCompraTest {

		
		private SolicitudCompra sol;

		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(SolicitudCompraTest.class);
		}
		
		@Before
		public void setUp() {
			sol = new SolicitudCompra();
			sol = new SolicitudCompra("S1", "Barra","Herramienta", 4);
		}
		

		@Test
		public void testGetCodigo() {
			assertEquals("S1", sol.getCodigo());
		}
		@Test
		public void testGetNombre() {
			assertEquals("Barra", sol.getNombre());
		}
		@Test
		public void testGetTipo() {
			assertEquals("Herramienta", sol.getTipo());
		}
		@Test
		public void testGetUnidades() {
			assertEquals(4, sol.getUnidades());
		}
		
		@Test
		public void testSetCodigo() {
			sol.setCodigo("S2");
			assertEquals("S2", sol.getCodigo());
		}
		@Test
		public void testSetNombre() {
			sol.setNombre("Amortiguador");
			assertEquals("Amortiguador", sol.getNombre());
		}
		@Test
		public void testSetTipo() {
			sol.setTipo("Piezas");
			assertEquals("Piezas", sol.getTipo());
		}
		@Test
		public void testSetUnidades() {
			sol.setUnidades(5);
			assertEquals(5, sol.getUnidades());
		}

	}



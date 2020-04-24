package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class PresupuestoTest {
	private Presupuesto presupuesto;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteTest.class);
	}

	@Before
	public void setUp() {
		presupuesto = new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
	}
	
	//Test Getters:
	@Test
	public void testGetCodigo() {
		assertEquals("PE-1", presupuesto.getCodigo());
	}
	@Test
	public void testGetDniCliente() {
		assertEquals("12345678A", presupuesto.getDniCliente());
	}
	@Test
	public void testGetMecanico() {
		assertEquals("Jorge", presupuesto.getMecanico());
	}
	@Test
	public void testGetMarca() {
		assertEquals("Seat", presupuesto.getMarca());
	}
	@Test
	public void testGetModelo() {
		assertEquals("Leon", presupuesto.getModelo());
	}
	@Test
	public void testGetProblema() {
		assertEquals("Aceite", presupuesto.getProblema());
	}
	@Test
	public void testGetNumPiezas() {
		assertEquals(1, presupuesto.getNumPiezas());
	}
	@Test
	public void testGetListaPiezas() {
		assertEquals("Lata Aceite", presupuesto.getListaPiezas());
	}
	@Test
	public void testGetObservaciones() {
		assertEquals("Cambio de Aceite", presupuesto.getObservaciones());
	}
	@Test
	public void testGetPrecio() {
		assertEquals(50, presupuesto.getPrecio());
	}
	@Test
	public void testGetFecha() {
		assertEquals("22-1-2020", presupuesto.getFecha());
	}
	
	//Test Setters:
		@Test
		public void testSetCodigo() {
			presupuesto.setCodigo("PE-2");
			assertEquals("PE-2", presupuesto.getCodigo());
		}
		@Test
		public void testSetDniCliente() {
			presupuesto.setDniCliente("12345678B");
			assertEquals("12345678B", presupuesto.getDniCliente());
		}
		@Test
		public void testSetMecanico() {
			presupuesto.setMecanico("Arturo");
			assertEquals("Arturo", presupuesto.getMecanico());
		}
		@Test
		public void testSetMarca() {
			presupuesto.setMarca("Renault");
			assertEquals("Renault", presupuesto.getMarca());
		}
		@Test
		public void testsetModelo() {
			presupuesto.setModelo("Clio");
			assertEquals("Clio", presupuesto.getModelo());
		}
		@Test
		public void testSetProblema() {
			presupuesto.setProblema("Rueda");
			assertEquals("Rueda", presupuesto.getProblema());
		}
		@Test
		public void testSetNumPiezas() {
			presupuesto.setNumPiezas(2);
			assertEquals(2, presupuesto.getNumPiezas());
		}
		@Test
		public void testSetListaPiezas() {
			presupuesto.setListaPiezas("Llanta, Neumatico");
			assertEquals("Llanta, Neumatico", presupuesto.getListaPiezas());
		}
		@Test
		public void testSetObservaciones() {
			presupuesto.setObservaciones("Cambio de Rueda");
			assertEquals("Cambio de Rueda", presupuesto.getObservaciones());
		}
		@Test
		public void testSetPrecio() {
			presupuesto.setPrecio(180);
			assertEquals(180, presupuesto.getPrecio());
		}
		@Test
		public void testSetFecha() {
			presupuesto.setFecha("22-2-2020");
			assertEquals("22-2-2020", presupuesto.getFecha());
		}
}

package concesionario.datos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class ProveedorTest {
	
	private Proveedor prov;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ProveedorTest.class);
	}
	
	@Before
	public void setUp() {
		prov = new Proveedor();
		prov = new Proveedor("Bosch", "Bosch","Alemania", "Amortiguacion");
	}
	

	@Test
	public void testGetIdProveedor() {
		assertEquals("Bosch", prov.getIdProveedor());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Bosch", prov.getNombre());
	}
	@Test
	public void testGetPais() {
		assertEquals("Alemania", prov.getPais());
	}
	@Test
	public void testGetgetTipoPiezas() {
		assertEquals("Amortiguacion", prov.getTipoPiezas());
	}
	
	@Test
	public void testSetIdProveedor() {
		prov.setIdProveedor("Castrol");
		assertEquals("Castrol", prov.getIdProveedor());
	}
	@Test
	public void testSetNombre() {
		prov.setNombre("Castrol");
		assertEquals("Castrol", prov.getNombre());
	}
	@Test
	public void testSetPais() {
		prov.setPais("Reino Unido");
		assertEquals("Reino Unido", prov.getPais());
	}
	@Test
	public void testSetgetTipoPiezas() {
		prov.setTipoPiezas("Frenos");
		assertEquals("Frenos", prov.getTipoPiezas());
	}

}

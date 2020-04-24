package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TarifaTest {
	private Tarifa tarifa;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		tarifa = new Tarifa("T1", "Cambio Aceite", 50, 1);
	}
	
	//Test Getters:
	@Test
	public void testGetIdTarifa() {
		assertEquals("T1", tarifa.getIdTarifa());
	}
	@Test
	public void testGetNomTarifa() {
		assertEquals("Cambio Aceite", tarifa.getNomTarifa());
	}
	@Test
	public void testGetPrecioAprox() {
		assertEquals(50, tarifa.getPrecioAprox());
	}
	@Test
	public void testGetHoras_manodeobra() {
		assertEquals(1, tarifa.getHoras_manodeobra());
	}
	
	//TestSetters:
	@Test
	public void testSetIdTarifa() {
		tarifa.setIdTarifa("T2");
		assertEquals("T2", tarifa.getIdTarifa());
	}
	@Test
	public void testSetNomTarifa() {
		tarifa.setNomTarifa("Cambio Rueda");
		assertEquals("Cambio Rueda", tarifa.getNomTarifa());
	}
	@Test
	public void testSetPrecioAprox() {
		tarifa.setPrecioAprox(80);
		assertEquals(80, tarifa.getPrecioAprox());
	}
	@Test
	public void testSetHoras_manodeobra() {
		tarifa.setHoras_manodeobra(2);
		assertEquals(2, tarifa.getHoras_manodeobra());
	}
}

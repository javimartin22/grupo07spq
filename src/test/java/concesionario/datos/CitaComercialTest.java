package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class CitaComercialTest {
	private CitaComercial citaComercial;
	
	public static junit.framework.Test suit(){
		return new JUnit4TestAdapter(CitaComercialTest.class);
	}
	
	@Before
	public void setUp() {
		citaComercial = new CitaComercial();
		citaComercial = new CitaComercial("Mikel", "12332112A", "10/10/20", "10:30", "Unai");	
	}
	
	//Test getters
	@Test
	public void testGetNombre() {
		assertEquals("Mikel", citaComercial.getNombre());
	}
	
	@Test
	public void testGetDni() {
		assertEquals("12332112A", citaComercial.getDniCliente());
	}
	
	@Test
	public void testGetFecha() {
		assertEquals("10/10/20", citaComercial.getFecha());
	}
	
	@Test
	public void testGetHora() {
		assertEquals("10:30", citaComercial.getHora());
	}
	
	@Test
	public void testGetComercial() {
		assertEquals("Unai", citaComercial.getComercial());
	}
	
	
	//Test setters
	@Test
	public void testSetNombre() {
		citaComercial.setNombre("Pablo");
		assertEquals("Pablo", citaComercial.getNombre());
	}
	
	@Test
	public void testSetDniCliente() {
		citaComercial.setDniCliente("11111111A");
		assertEquals("11111111A", citaComercial.getDniCliente());
	}
	
	@Test
	public void testSetFecha() {
		citaComercial.setFecha("01/01/2020");
		assertEquals("01/01/2020", citaComercial.getFecha());
	}
	
	@Test
	public void testSetHora() {
		citaComercial.setHora("11:30");
		assertEquals("11:30", citaComercial.getHora());
	}
	
	@Test
	public void testSetComercial() {
		citaComercial.setComercial("Juan");
		assertEquals("Juan", citaComercial.getComercial());
	}
}
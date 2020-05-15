package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class CitaTallerTest {

	private CitaTaller cita;
	
	public static junit.framework.Test suit(){
		return new JUnit4TestAdapter(CitaTallerTest.class);
	}
	
	@Before
	public void setUp() {
		cita = new CitaTaller();
		cita = new CitaTaller("Jorge", "12345678Ñ", "22-05-2020", "11:00", "Unai", "Aceite");	
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Jorge", cita.getNombre());
	}
	
	@Test
	public void testGetDni() {
		assertEquals("12345678Ñ", cita.getDniCliente());
	}
	
	@Test
	public void testGetFecha() {
		assertEquals("22-05-2020", cita.getFecha());
	}
	
	@Test
	public void testGetHora() {
		assertEquals("11:00", cita.getHora());
	}
	
	@Test
	public void testGetComercial() {
		assertEquals("Unai", cita.getComercial());
	}
	
	@Test
	public void testGetProblema() {
		assertEquals("Aceite", cita.getProblema());
	}
	
	@Test
	public void testSetNombre() {
		cita.setNombre("Pablo");
		assertEquals("Pablo", cita.getNombre());
	}
	
	@Test
	public void testSetDniCliente() {
		cita.setDniCliente("11111111A");
		assertEquals("11111111A", cita.getDniCliente());
	}
	
	@Test
	public void testSetFecha() {
		cita.setFecha("01/01/2020");
		assertEquals("01/01/2020", cita.getFecha());
	}
	
	@Test
	public void testSetHora() {
		cita.setHora("11:30");
		assertEquals("11:30", cita.getHora());
	}
	
	@Test
	public void testSetComercial() {
		cita.setComercial("Juan");
		assertEquals("Juan", cita.getComercial());
	}
	
	@Test
	public void testSetProblema() {
		cita.setProblema("Pinchazo");
		assertEquals("Pinchazo", cita.getProblema());
	}
}

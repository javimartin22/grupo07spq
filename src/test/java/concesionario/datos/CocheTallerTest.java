package concesionario.datos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

public class CocheTallerTest {
	
	private CocheTaller cocheT;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CocheTallerTest.class);
	}
	@Before
	public void setUp() {
		cocheT = new CocheTaller();
		cocheT = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
	}
	
	//Test Getters
	@Test
	public void testGetMarca() {
		assertEquals("Honda", cocheT.getMarca());
	}
	@Test
	public void testGetModelo() {
		assertEquals("Civic", cocheT.getModelo());
	}
	@Test
	public void testGetMatricula() {
		assertEquals("2544KLB", cocheT.getMatricula());
	}
	@Test
	public void testGetMecanico() {
		assertEquals("Andres", cocheT.getMecanico());
	}
	@Test
	public void testGetDniCliente() {
		assertEquals("79076345T", cocheT.getDniCliente());
	}
	@Test
	public void testGetCoste() {
		assertTrue(cocheT.getCoste() == 1300);
	}
	@Test
	public void testGetEstado() {
		assertEquals(0, cocheT.getEstado());
	}
	
	//Test Setters
	@Test 
	public void testSetMarca() {
		cocheT.setMarca("Ford");
		assertEquals("Ford", cocheT.getMarca());
	}
	@Test 
	public void testSetModelo() {
		cocheT.setModelo("Focus");
		assertEquals("Focus", cocheT.getModelo());
	}
	@Test 
	public void testSetMatricula() {
		cocheT.setMatricula("0388FGT");
		assertEquals("0388FGT", cocheT.getMatricula());
	}
	@Test 
	public void testSetMecanico() {
		cocheT.setMecanico("Pablo");
		assertEquals("Pablo", cocheT.getMecanico());
	}
	@Test 
	public void testSetDniCliente() {
		cocheT.setDniCliente("76096754k");
		assertEquals("76096754k", cocheT.getDniCliente());
	}
	@Test 
	public void testSetCoste() {
		cocheT.setCoste(540);
		assertTrue(cocheT.getCoste() == 540);
	}
	@Test 
	public void testSetEstado() {
		cocheT.setEstado(1);
		assertEquals(1, cocheT.getEstado());
	}
}

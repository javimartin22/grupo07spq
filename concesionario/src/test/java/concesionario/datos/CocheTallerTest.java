package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import concesionario.datos.CocheTaller;


public class CocheTallerTest {
	
	private CocheTaller cocheT;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CocheMatriculadoTest.class);
	}
	@Before
	public void setUp() {
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
		assertEquals(1300, cocheT.getCoste());
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
		assertEquals(100, cocheT.getDniCliente());
	}
	@Test 
	public void testSetCoste() {
		cocheT.setCoste(540);
		assertEquals(540 , cocheT.getCoste());
	}
	@Test 
	public void testSetEstado() {
		cocheT.setEstado(1);
		assertEquals(1, cocheT.getEstado());
	}
	
}

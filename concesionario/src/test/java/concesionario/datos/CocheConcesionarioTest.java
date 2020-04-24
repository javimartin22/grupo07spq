package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import concesionario.datos.CocheConcesionario;


public class CocheConcesionarioTest {
	
	private CocheConcesionario cocheCon;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CocheConcesionarioTest.class);
	}
	@Before
	public void setUp() {
		cocheCon = new CocheConcesionario("Renault", "Clio", 14000, 115, 5, "Blanco", 1);
	}
	
	
	//Test Getters
	@Test
	public void testGetMarca() {
		assertEquals("Renault", cocheCon.getMarca());
	}
	@Test
	public void testGetModelo() {
		assertEquals("Clio", cocheCon.getModelo());
	}
	@Test
	public void testGetPrecio() {
		assertEquals(14000, cocheCon.getPrecio());
	}
	@Test
	public void testGetUnidades() {
		assertEquals(1, cocheCon.getUnidades());
	}
	@Test
	public void testGetCV() {
		assertEquals(115, cocheCon.getCv());
	}
	@Test
	public void testGetNumPuertas() {
		assertEquals(5, cocheCon.getNumPuertas());
	}
	@Test
	public void testGetColor() {
		assertEquals("Blanco", cocheCon.getColor());
	}
	
	//Setters
	@Test 
	public void testSetMarca() {
		cocheCon.setMarca("Ford");
		assertEquals("Ford", cocheCon.getMarca());
	}
	@Test 
	public void testSetModelo() {
		cocheCon.setModelo("Focus");
		assertEquals("Focus", cocheCon.getModelo());
	}
	@Test 
	public void testSetPrecio() {
		cocheCon.setPrecio(10000);
		assertEquals(10000, cocheCon.getPrecio());
	}
	@Test 
	public void testSetUnidades() {
		cocheCon.setUnidades(2);
		assertEquals(2, cocheCon.getUnidades());
	}
	@Test 
	public void testSetCV() {
		cocheCon.setCv(100);
		assertEquals(100, cocheCon.getCv());
	}
	@Test 
	public void testSetNumPuertas() {
		cocheCon.setNumPuertas(3);
		assertEquals(3, cocheCon.getNumPuertas());
	}
	@Test 
	public void testSetColor() {
		cocheCon.setColor("Negro");
		assertEquals("Negro", cocheCon.getColor());
	}
	
}

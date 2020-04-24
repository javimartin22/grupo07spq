package concesionario.datos;

import static org.junit.Assert.assertEquals;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

public class CocheMatriculadoTest {
	
	private CocheMatriculado cocheMat;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CocheMatriculadoTest.class);
	}
	@Before
	public void setUp() {
		cocheMat = new CocheMatriculado();
		cocheMat = new CocheMatriculado("Opel", "Corsa", "2838GBJ", "Juan", "Azul",3, 2006, 80, 4);
	}
	
	//Test Getters
	@Test
	public void testGetMarca() {
		assertEquals("Opel", cocheMat.getMarca());
	}
	@Test
	public void testGetModelo() {
		assertEquals("Corsa", cocheMat.getModelo());
	}
	@Test
	public void testGetMatricula() {
		assertEquals("2838GBJ", cocheMat.getMatricula());
	}
	@Test
	public void testGetAnyoMatriculacion() {
		assertEquals(2006, cocheMat.getAnyoMatriculacion());
	}
	@Test
	public void testGetNumPuertas() {
		assertEquals(3, cocheMat.getNumPuertas());
	}
	@Test
	public void testGetColor() {
		assertEquals("Azul", cocheMat.getColor());
	}
	@Test
	public void testGetRevisiones() {
		assertEquals(4, cocheMat.getRevisiones());
	}
	@Test
	public void testGetNombrePropietario() {
		assertEquals("Juan", cocheMat.getNombrePropietario());
	}
	
	//Test Setters
	@Test 
	public void testSetMarca() {
		cocheMat.setMarca("Ford");
		assertEquals("Ford", cocheMat.getMarca());
	}
	@Test 
	public void testSetModelo() {
		cocheMat.setModelo("Focus");
		assertEquals("Focus", cocheMat.getModelo());
	}
	@Test 
	public void testSetMatricula() {
		cocheMat.setMatricula("0388FGT");
		assertEquals("0388FGT", cocheMat.getMatricula());
	}
	@Test 
	public void testSetAnyoMatriculacion() {
		cocheMat.setAnyoMatriculacion(2012);
		assertEquals(2012, cocheMat.getAnyoMatriculacion());
	}
	@Test 
	public void testSetCV() {
		cocheMat.setCv(100);
		assertEquals(100, cocheMat.getCv());
	}
	@Test 
	public void testSetNumPuertas() {
		cocheMat.setNumPuertas(5);
		assertEquals(5, cocheMat.getNumPuertas());
	}
	@Test 
	public void testSetColor() {
		cocheMat.setColor("Negro");
		assertEquals("Negro", cocheMat.getColor());
	}
	@Test 
	public void testSetRevisiones() {
		cocheMat.setRevisiones(7);
		assertEquals(7, cocheMat.getRevisiones());
	}
	@Test 
	public void testSetNombrePropietario() {
		cocheMat.setNombrePropietario("Andoni");
		assertEquals("Andoni", cocheMat.getNombrePropietario());
	}
}

package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class VentaTest {
	private Venta venta;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ComercialTest.class);
	}
	@Before
	public void setUp() {
		venta = new Venta("22-1-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Alberto");
	}
	
	//Test Getters:
	@Test
	public void testGetFecha() {
		assertEquals("22-1-2020", venta.getFecha());
	}
	@Test
	public void testGetModelo() {
		assertEquals("Leon", venta.getModelo());
	}
	@Test
	public void testGetMarca() {
		assertEquals("Seat", venta.getMarca());
	}
	@Test
	public void testGetMatricula() {
		assertEquals("9600JPT", venta.getMatricula());
	}
	@Test
	public void testGetNicknameMecanico() {
		assertEquals("Jorgico", venta.getNicknameComercial());
	}
	@Test
	public void testGetNombreComprador() {
		assertEquals("Alberto", venta.getNombreComprador());
	}
	
	//Test Setters:
	@Test
	public void testSetFecha() {
		venta.setFecha("23-1-2020");
		assertEquals("23-1-2020", venta.getFecha());
	}
	@Test
	public void testSetModelo() {
		venta.setModelo("Clio");
		assertEquals("Clio", venta.getModelo());
	}
	@Test
	public void testSetMarca() {
		venta.setMarca("Renault");
		assertEquals("Renault", venta.getMarca());
	}
	@Test
	public void testSetMatricula() {
		venta.setMatricula("9601JPT");
		assertEquals("9601JPT", venta.getMatricula());
	}
	@Test
	public void testSetNicknameMecanico() {
		venta.setNicknameComercial("Jorge");
		assertEquals("Jorge", venta.getNicknameComercial());
	}
	@Test
	public void testSetNombreComprador() {
		venta.setNombreComprador("Javier");
		assertEquals("Javier", venta.getNombreComprador());
	}
}

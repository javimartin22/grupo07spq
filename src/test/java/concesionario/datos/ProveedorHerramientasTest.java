package concesionario.datos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class ProveedorHerramientasTest {

	private ProveedorHerramientas provH;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ProveedorHerramientasTest.class);
	}
	
	@Before
	public void setUp() {
		provH = new ProveedorHerramientas();
		provH = new ProveedorHerramientas("Oscaro", "Oscaro","España", "Alicates, inspeccion");
	}
	
	@Test
	public void testGetIdProveedor() {
		assertEquals("Oscaro", provH.getIdProveedor());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Oscaro", provH.getNombre());
	}
	@Test
	public void testGetPais() {
		assertEquals("España", provH.getPais());
	}
	@Test
	public void testGetTipoHerramientas() {
		assertEquals("Alicates, inspeccion", provH.gettipoHerramientas());
	}
	
	@Test
	public void testSetIdProveedor() {
		provH.setIdProveedor("Iberisa");
		assertEquals("Iberisa", provH.getIdProveedor());
	}
	@Test
	public void testSetNombre() {
		provH.setNombre("Iberisa");
		assertEquals("Iberisa", provH.getNombre());
	}
	@Test
	public void testSetPais() {
		provH.setPais("España");
		assertEquals("España", provH.getPais());
	}
	@Test
	public void testSetTipoHerramientas() {
		provH.settipoHerramientas("Tapiceria, bateria");
		assertEquals("Tapiceria, bateria", provH.gettipoHerramientas());
	}
}

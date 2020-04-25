package concesionario.datos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class ClienteFidelidadTest {
	private ClienteFidelidad cliente;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteFidelidadTest.class);
	}

	@Before
	public void setUp() {
		cliente = new ClienteFidelidad();
		cliente = new ClienteFidelidad("12345678K", 2);
	}
	
	//Test Getters
		@Test
		public void testGetDni() {
			assertEquals("12345678K", cliente.getDni());
		}
		@Test
		public void testGetFidelidad() {
			assertEquals(0, cliente.getFidelidad());
		}
		
		//Setters
		@Test 
		public void testSetDni() {
			cliente.setDni("11122233V");
			assertEquals("11122233V", cliente.getDni());
		}
		@Test
		public void testSetFidelidad() {
			cliente.setFidelidad(1);
			assertEquals(1, cliente.getFidelidad());
		}
}

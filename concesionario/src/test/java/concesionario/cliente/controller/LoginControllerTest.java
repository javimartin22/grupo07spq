package concesionario.cliente.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Usuario;




@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	public LoginController loginController;
	
	@Mock
	ClienteApp cliente;
	
	@Before
	public void setUp() {
		loginController = new LoginController(cliente);
	}
	
	@Test
	public void testLogin() {
		String tipo_admin = "0";
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(tipo_admin);
		
		when(cliente.login(any(Usuario.class))).thenReturn(response);
		
		assertTrue(loginController.login("admin", "admin") == 0);
	}
	

	
}
package concesionario.cliente.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Usuario;




@RunWith(MockitoJUnitRunner.Silent.class) 
public class LoginControllerTest {

	public LoginController loginController;
	
	@Mock
	ClienteApp cliente;
	
	@Before
	public void setUp() {
		loginController = new LoginController(cliente);
		cliente = loginController.getClienteApp();
	}
	
	@Test
	public void testLogin() {
		String tipo_admin = "0";
		String tipo_not_accept = "5";
		String tipo_not_found = "6";
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(Status.OK.getStatusCode());
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(tipo_admin);
		
		when(cliente.login(any(Usuario.class))).thenReturn(response);
		
		assertTrue(loginController.login("admin", "admin") == 0);
		
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(Status.NOT_ACCEPTABLE.getStatusCode());
		
		when(cliente.login(any(Usuario.class))).thenReturn(response1);
		
		assertTrue(loginController.login("admin", "ad") == 5);
		
		Response response2 = Mockito.mock(Response.class);
		Mockito.when(response2.getStatus()).thenReturn(Status.NOT_FOUND.getStatusCode());
		Mockito.when(response2.readEntity(Mockito.any(Class.class))).thenReturn(tipo_not_found);
		
		when(cliente.login(any(Usuario.class))).thenReturn(response2);
		
		assertTrue(loginController.login("ad", "ad") == 6);
		
	}
	
	
	
	
	

	
}
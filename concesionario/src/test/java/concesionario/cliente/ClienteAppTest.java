package concesionario.cliente;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import concesionario.cliente.controller.ClienteController;
import concesionario.datos.Cliente;
import concesionario.datos.Usuario;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteAppTest {

	ClienteApp clienteApp;
	WebTarget webtarget = mock(WebTarget.class, Mockito.RETURNS_DEEP_STUBS);
	
	@Before
	public void setUp() {
		clienteApp = new ClienteApp();
		clienteApp.setInicioTarget(webtarget);
		
	}
	
	@Test
	public void testLogin() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(webtarget.request(any(MediaType.class)).post(any(Entity.class))).thenReturn(response);
		
		Usuario usu= new Usuario("Pablo","1234",0);
		Response s = clienteApp.login(usu);
		
		assertTrue(s.getStatus() == 200);
	}
	
	
}

package concesionario.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.datos.Cliente;
import concesionario.datos.ClienteTest;
import concesionario.datos.Usuario;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;



@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteAppTest {
	
	
	public ClienteApp clienteApp;
	
	@Mock
	WebTarget inicio_Target;
	
	
	@Before
	public void setUp() {
		clienteApp = new ClienteApp();
		clienteApp.setInicioTarget(inicio_Target);
		inicio_Target = clienteApp.getInicioTarget();
		
	}
	
	@Test
	public void testLogin() {
		Usuario usu= new Usuario("usu","pass",0);
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.getInicioTarget().request(Mockito.any(MediaType.class)).post(Mockito.any(javax.ws.rs.client.Entity.class))).thenReturn(response);
		
		Response result = clienteApp.login(usu);
		assertTrue(result.getStatus() == Status.OK.getStatusCode());
	}

}

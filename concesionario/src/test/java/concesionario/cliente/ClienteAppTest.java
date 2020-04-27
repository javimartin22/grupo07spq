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
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteAppTest {

	public ClienteApp clienteApp;
	
	WebTarget webtarget = mock(WebTarget.class, Mockito.RETURNS_DEEP_STUBS);
	
	@Before
	public void setUp() {
		clienteApp = new ClienteApp(webtarget);
		webtarget = clienteApp.getLoginTarget();
		
	}
	
	//Test Login
	@Test
	public void testLogin() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Usuario usu= new Usuario("Pab","1234",0);
		Response result = clienteApp.login(usu);
		
		assertEquals(200, result.getStatus());		
	}
	
	//Test getter
	@Test
	public void testGetLoginTarget() {
		assertTrue(clienteApp.getLoginTarget().getUri() == webtarget.getUri()) ;

	}
	
	//Test de Metodos de Registro
	@Test
	public void testRegistrarCoche() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		CocheConcesionario auto = new CocheConcesionario("Renault", "Clio", 14000, 115, 5, "Blanco", 1);
		Response result = clienteApp.registrarCoche(auto);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistrarCocheTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		CocheTaller cocheTaller = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		Response result = clienteApp.registrarCocheTaller(cocheTaller);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroCliente() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Cliente cliente = new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
		Response result = clienteApp.registroCliente(cliente);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroMecanico() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Mecanico mecanico = new Mecanico("user", "pass", 1, "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
		Response result = clienteApp.registroMecanico(mecanico);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroComercial() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Comercial comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
		Response result = clienteApp.registroComercial(comercial);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroDepartamentoCompras() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		DepartamentoCompras depcompras = new DepartamentoCompras("Jorge", "12345", "12345667Ñ", "Jorge", "Martinez", "Masculino", "em@gmail.com", "Barakaldo", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
		Response result = clienteApp.registroDepartamentoCompras(depcompras);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroTarifa() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Tarifa tarifa = new Tarifa("T1", "Cambio Aceite", 50, 1); 
		Response result = clienteApp.registroTarifa(tarifa);
		
		assertEquals(200, result.getStatus());		

	}
	
	
	// Test cambio password- nickname
	@Test
	public void testCambiarContraseniaCliente() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteClient")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		when(webtarget.path(eq("insertClient")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Cliente cliente = new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
		String password = "password";
		
		Response result = clienteApp.cambiarContraseniaCliente(cliente, password);
		assertEquals(200, result.getStatus());	
		
		Response response2 = mock(Response.class);
		Mockito.when(response2.getStatus()).thenReturn(406);
		when(webtarget.path(eq("deleteClient")).request(anyString()).post(any(Entity.class))).thenReturn(response2);
		assertEquals(null, clienteApp.cambiarContraseniaCliente(cliente, password));
	}
	
	@Test
	public void testCambiarNicknameCliente() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteClient")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		when(webtarget.path(eq("insertClient")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Cliente cliente = new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
		String nickname = "nick";
		
		Response result = clienteApp.cambiarNicknameCliente(cliente, nickname);
		assertEquals(200, result.getStatus());	
		
		Response response2 = mock(Response.class);
		Mockito.when(response2.getStatus()).thenReturn(406);
		when(webtarget.path(eq("deleteClient")).request(anyString()).post(any(Entity.class))).thenReturn(response2);
		assertEquals(null, clienteApp.cambiarNicknameCliente(cliente, nickname));
	}
	
	// Test Selecciones
	@Test
	public void testSeleccionarCocheTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectCocheTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String matricula = "1234KLB";
		
		Response result = clienteApp.seleccionarCocheTaller(matricula);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testClienteSelect() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectClient")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "nick";
		
		Response result = clienteApp.clienteSelect(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testCargarTablaCochesConcesionario() {
		CocheConcesionario coche1 = new CocheConcesionario("Renault", "Clio", 14000, 115, 5, "Blanco", 1);
		CocheConcesionario coche2 = new CocheConcesionario("Ford", "Mondeo", 14000, 115, 5, "Blanco", 1);
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
		coches.add(coche1);
		coches.add(coche2);
		
		when(webtarget.path(eq("loadCocheConcesionarioTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->coches);
		List<CocheConcesionario> cochesSeleccionados = clienteApp.cargarTablaCochesConcesionario();
		
		for (int i = 0; i < coches.size(); i++) {
			assertTrue(cochesSeleccionados.get(i).getMarca().equals(coches.get(i).getMarca()));
		}
	}
	
	
}


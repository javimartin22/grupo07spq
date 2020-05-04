package concesionario.cliente.controller;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Tarifa;


@RunWith(MockitoJUnitRunner.Silent.class) 
public class ClienteControllerTest {
	
	public ClienteController clienteController;
	
	@Mock
	ClienteApp clienteApp;
	
	@Before
	public void setUp() {
		clienteController = new ClienteController(clienteApp);
		clienteApp = clienteController.getClienteApp();
	}
		
	@Test
	public void testGetClienteApp() {
		ClienteApp clientApp = null;
		assertEquals(null, clientApp);;
	}
		
	@Test
	public void testRegistroCliente() {
		Cliente cliente = new Cliente("78945612A", "Pablito", 1, "12345", "Pablo", "Gaviria", "Hombre", "pablogaviria@opendeusto.es", "bilbao", 48007, "direccion", "123456789");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroCliente(any(Cliente.class))).thenReturn(response);
		
		assertTrue(clienteController.registroCliente(cliente) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroCliente(any(Cliente.class))).thenReturn(response1);
		
		assertTrue(clienteController.registroCliente(cliente) == false);
	}
	
	@Test
	public void testSeleccionarCocheConcesionario() {
		String modelo = "Leon";
		CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(coche);
		when(clienteApp.seleccionarCocheConcesionario(any(String.class))).thenReturn(response);
		
		CocheConcesionario cocheConcesionario = clienteController.seleccionarCocheConcesionario(modelo);
		
		assertTrue(cocheConcesionario.getModelo().equals(modelo));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.seleccionarCocheConcesionario(any(String.class))).thenReturn(response1);
		
		assertTrue(clienteController.seleccionarCocheConcesionario(modelo) == null);
	}
		
	@Test
	public void testSeleccionarCliente() {
		Cliente cliente = new Cliente("78945612A", "Pablito", 1, "12345", "Pablo", "Gaviria", "Hombre", "pablogaviria@opendeusto.es", "bilbao", 48007, "direccion", "123456789");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(cliente);
		when(clienteApp.clienteSelect(any(String.class))).thenReturn(response);
		
		Cliente clienteSeleccionado = clienteController.seleccionarCliente("Pablito");
		assertTrue(clienteSeleccionado.getNickname().equals("Pablito"));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.clienteSelect(any(String.class))).thenReturn(response1);
		assertTrue(clienteController.seleccionarCliente("Pablito") == null);
	}
		
	@Test
	public void testCargarCochesConcesionario() {
		CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
		coches.add(coche);
		
		Mockito.when(clienteApp.cargarTablaCochesConcesionario()).thenAnswer(x ->coches);
		List<CocheConcesionario> cochesConcesionario = clienteController.cargarTablaCochesConcesionario();
		
		for (int i = 0; i < coches.size(); i++) {
			assertTrue(cochesConcesionario.get(i).getMarca().equals(coches.get(i).getMarca()));
		}
	}
		
	@Test
	public void testCargarTablaTarifa() {
		Tarifa tarifa = new Tarifa("T1", "Cambio de Aceite", 50, 1);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		tarifas.add(tarifa);
		
		Mockito.when(clienteApp.cargarTablaTarifas()).thenAnswer(x ->tarifas);
		List<Tarifa> tarifasSeleccionadas = clienteController.cargarTablaTarifas();
		
		for (int i = 0; i < tarifas.size(); i++) {
			assertTrue(tarifasSeleccionadas.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
		}
	}
	
	@Test
	public void testFiltrarTarifaHorasMin() {
		List<Tarifa> tarifas= new ArrayList<Tarifa>();
		tarifas.add(new Tarifa("T1", "Cambio Aceite", 50, 1));
		tarifas.add(new Tarifa("T2", "Frenos", 50, 1));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->tarifas);
		when(clienteApp.filtrarTarifaHorasMin(any(Integer.class))).thenReturn(response);
		
		List<Tarifa> tarif_result = clienteController.filtrarTarifaHorasMin(10);
		for(int i =0; i<tarifas.size(); i++) {
			assertTrue(tarif_result.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarTarifaHorasMin(any(Integer.class))).thenReturn(response1);
		
		assertTrue(clienteController.filtrarTarifaHorasMin(10) == null);
	}
		
	@Test
		public void testFiltrarTarifaHorasMax() {
			List<Tarifa> tarifas= new ArrayList<Tarifa>();
			tarifas.add(new Tarifa("T1", "Cambio Aceite", 50, 1));
			tarifas.add(new Tarifa("T2", "Frenos", 50, 1));
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->tarifas);
			when(clienteApp.filtrarTarifaHorasMax(any(Integer.class))).thenReturn(response);
			
			List<Tarifa> tarif_result = clienteController.filtrarTarifaHorasMax(10);
			for(int i =0; i<tarifas.size(); i++) {
				assertTrue(tarif_result.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
			}
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
			when(clienteApp.filtrarTarifaHorasMax(any(Integer.class))).thenReturn(response1);
			
			assertTrue(clienteController.filtrarTarifaHorasMax(10) == null);
		}
		
		@Test
		public void testFiltrarTarifaPrecio() {
			List<Tarifa> tarifas= new ArrayList<Tarifa>();
			tarifas.add(new Tarifa("T1", "Cambio Aceite", 50, 1));
			tarifas.add(new Tarifa("T2", "Frenos", 50, 1));
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->tarifas);
			when(clienteApp.filtrarTarifaPrecio(any(Integer.class))).thenReturn(response);
			
			List<Tarifa> tarif_result = clienteController.filtrarTarifaPrecio(10);
			for(int i =0; i<tarifas.size(); i++) {
				assertTrue(tarif_result.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
			}
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
			when(clienteApp.filtrarTarifaPrecio(any(Integer.class))).thenReturn(response1);
			
			assertTrue(clienteController.filtrarTarifaPrecio(10) == null);
		}
		
		@Test
		public void testFiltrarTarifaPrecioMin() {
			List<Tarifa> tarifas= new ArrayList<Tarifa>();
			tarifas.add(new Tarifa("T1", "Cambio Aceite", 50, 1));
			tarifas.add(new Tarifa("T2", "Frenos", 50, 1));
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->tarifas);
			when(clienteApp.filtrarTarifaPrecioMin(any(Integer.class))).thenReturn(response);
			
			List<Tarifa> tarif_result = clienteController.filtrarTarifaPrecioMin(10);
			for(int i =0; i<tarifas.size(); i++) {
				assertTrue(tarif_result.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
			}
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
			when(clienteApp.filtrarTarifaPrecioMin(any(Integer.class))).thenReturn(response1);
			
			assertTrue(clienteController.filtrarTarifaPrecioMin(10) == null);
		}
		
		@Test
		public void testFiltrarCocheCocesionario() {
			CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
			List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
			coches.add(coche);
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->coches);
			when(clienteApp.filtrarCocheConcesionario(any(String.class))).thenReturn(response);
			
			List<CocheConcesionario> cochesSeleccionados = clienteController.filtrarCocheConcesionario("");
			for(int i =0; i<coches.size(); i++) {
				assertTrue(cochesSeleccionados.get(i).getMarca().equals(coches.get(i).getMarca()));
			}
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
			when(clienteApp.filtrarCocheConcesionario(any(String.class))).thenReturn(response1);
			
			assertTrue(clienteController.filtrarCocheConcesionario("") == null);
		}
		
		@Test
		public void testCambiarContraseniaCliente() {
			Cliente c = new Cliente("78945612A", "Pablito", 1, "12345", "Pablo", "Gaviria", "Hombre", "pablogaviria@opendeusto.es", "bilbao", 48007, "direccion", "123456789");
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			when(clienteApp.cambiarContraseniaCliente(any(Cliente.class),(any(String.class)))).thenReturn(response);
			
			assertTrue(clienteController.cambiarContraseniaCliente(c, "12345") == true);
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			when(clienteApp.cambiarContraseniaCliente(any(Cliente.class),(any(String.class)))).thenReturn(response1);
			
			assertTrue(clienteController.cambiarContraseniaCliente(c, "12345") == false);
		}
		
		@Test
		public void testCambiarNicknameCliente() {
			Cliente c = new Cliente("78945612A", "Pablito", 1, "12345", "Pablo", "Gaviria", "Hombre", "pablogaviria@opendeusto.es", "bilbao", 48007, "direccion", "123456789");
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			when(clienteApp.cambiarNicknameCliente(any(Cliente.class),(any(String.class)))).thenReturn(response);
			
			assertTrue(clienteController.cambiarNicknameCliente(c, "Pablito") == true);
			
			Response response1 = Mockito.mock(Response.class);
			Mockito.when(response1.getStatus()).thenReturn(404);
			when(clienteApp.cambiarNicknameCliente(any(Cliente.class),(any(String.class)))).thenReturn(response1);
			
			assertTrue(clienteController.cambiarNicknameCliente(c, "Pablito") == false);
		}
}

package concesionario.cliente.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import concesionario.datos.Venta;

@RunWith(MockitoJUnitRunner.class)
public class ComercialControllerTest {
	public ComercialController comercialController;
	public Cliente cliente;
	
	@Mock
	ClienteApp clienteApp;
	
	@Before
	public void setUp() {
		comercialController = new ComercialController(clienteApp);
	}
	
	@Test
	public void testRegistrarCoche() {
		CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registrarCoche(any(CocheConcesionario.class))).thenReturn(response);
		
		assertTrue(comercialController.registrarCoche(coche) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registrarCoche(any(CocheConcesionario.class))).thenReturn(response1);
		
		assertTrue(comercialController.registrarCoche(coche) == false);
	}
	
	@Test
	public void testCargarTablaCochesConcesionario() {
		CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
		coches.add(coche);

		Mockito.when(clienteApp.cargarTablaCochesConcesionario()).thenAnswer(x ->coches);
		List<CocheConcesionario> cochesConcesionario = comercialController.cargarTablaCochesConcesionario();
		
		for (int i = 0; i < coches.size(); i++) {
			assertTrue(cochesConcesionario.get(i).getMarca().equals(coches.get(i).getMarca()));
		}
	}
	
	@Test
	public void testRegistrarVenta() {
		Venta venta = new Venta("22-01-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Pablo Gaviria");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroVenta(any(Venta.class))).thenReturn(response);
		
		assertTrue(comercialController.registrarVenta(venta) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroVenta(any(Venta.class))).thenReturn(response1);
		
		assertTrue(comercialController.registrarVenta(venta) == false);
	}
	
	@Test
	public void testCargarTablaVentas() {
		Venta venta = new Venta("22-01-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Pablo Gaviria");
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);

		Mockito.when(clienteApp.cargarTablaVenta()).thenAnswer(x ->ventas);
		List<Venta> ventasSeleccionadas = comercialController.cargarTablaVenta();
		
		for (int i = 0; i < ventas.size(); i++) {
			assertTrue(ventasSeleccionadas.get(i).getMarca().equals(ventas.get(i).getMarca()));
		}
	}
	
	@Test
	public void testComprobarColor() {
		assertEquals("Rojo", comercialController.comprobarColor(0));
		assertEquals("Azul", comercialController.comprobarColor(1));
		assertEquals("Plata", comercialController.comprobarColor(2));
		assertEquals("Amarillo", comercialController.comprobarColor(3));
		assertEquals("Verde", comercialController.comprobarColor(4));
		assertEquals("Blanco", comercialController.comprobarColor(5));
		assertEquals("Negro", comercialController.comprobarColor(6));
		assertEquals("Blanco Marfil", comercialController.comprobarColor(7));
		assertEquals("Gris", comercialController.comprobarColor(8));
	}
	
	@Test
	public void testFiltrarCocheConcesionario() {
		CocheConcesionario coche = new CocheConcesionario("Seat", "Leon", 15000, 115, 5, "Blanco", 2);
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
		coches.add(coche);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->coches);
		
		when(clienteApp.filtrarCocheConcesionario(any(String.class))).thenReturn(response);
		
		List<CocheConcesionario> cochesSeleccionados = comercialController.filtrarCocheConcesionario("");
		for(int i =0; i<coches.size(); i++) {
			assertTrue(cochesSeleccionados.get(i).getMarca().equals(coches.get(i).getMarca()));
		}
	}
	
	@Test
	public void testFiltrarVentaComercial() {
		Venta venta = new Venta("22-01-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Pablo Gaviria");
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->ventas);
		
		when(clienteApp.filtrarVentaComercial(any(String.class))).thenReturn(response);
		
		List<Venta> ventasSeleccionados = comercialController.filtrarVentaComercial("");
		for(int i =0; i<ventas.size(); i++) {
			assertTrue(ventasSeleccionados.get(i).getMatricula().equals(ventas.get(i).getMatricula()));
		}
	}
	
	@Test
	public void testFiltrarVentaModelo() {
		Venta venta = new Venta("22-01-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Pablo Gaviria");
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->ventas);
		
		when(clienteApp.filtrarVentaModelo(any(String.class))).thenReturn(response);
		
		List<Venta> ventasSeleccionados = comercialController.filtrarVentaModelo("");
		for(int i =0; i<ventas.size(); i++) {
			assertTrue(ventasSeleccionados.get(i).getMarca().equals(ventas.get(i).getMarca()));
		}
	}
	
	@Test
	public void testFiltrarVentaMarca() {
		Venta venta = new Venta("22-01-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Pablo Gaviria");
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->ventas);
		
		when(clienteApp.filtrarVentaMarca(any(String.class))).thenReturn(response);
		
		List<Venta> ventasSeleccionados = comercialController.filtrarVentaMarca("");
		for(int i =0; i<ventas.size(); i++) {
			assertTrue(ventasSeleccionados.get(i).getMatricula().equals(ventas.get(i).getMatricula()));
		}
	}
}

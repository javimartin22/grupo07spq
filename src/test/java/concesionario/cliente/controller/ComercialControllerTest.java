package concesionario.cliente.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import concesionario.datos.CitaComercial;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Comercial;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Presupuesto;
import concesionario.datos.Venta;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ComercialControllerTest {
	public ComercialController comercialController;
	public Cliente cliente;
	
	@Mock
	ClienteApp clienteApp;
	
	@Before
	public void setUp() {
		comercialController = new ComercialController(clienteApp);
		clienteApp = comercialController.getClienteApp();
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
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarCocheConcesionario(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.filtrarCocheConcesionario("") == null);
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
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarVentaComercial(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.filtrarVentaComercial("") == null);
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
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarVentaModelo(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.filtrarVentaModelo("") == null);
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
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarVentaMarca(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.filtrarVentaMarca("") == null);
	}
	
	@Test
	public void testCargarCitaComercial() {
		CitaComercial cita= new CitaComercial("Mikel", "12312312A", "11-5-2020", "10:30", "Jorge");
		List<CitaComercial> citas = new ArrayList<CitaComercial>();
		citas.add(cita);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->citas);
		when(clienteApp.cargarCitaComercial(any(String.class))).thenReturn(response);
		
		List<CitaComercial> CitaComercial = comercialController.cargarCitaComercial("");
		for(int i =0; i<citas.size(); i++) {
			assertTrue(CitaComercial.get(i).getNombre().equals(citas.get(i).getNombre()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.cargarCitaComercial(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.cargarCitaComercial("") == null);
	}
	
	@Test
	public void testFiltrarCitaComercial() {
		CitaComercial cita= new CitaComercial("Mikel", "12312312A", "11-5-2020", "10:30", "Jorge");
		List<CitaComercial> citas = new ArrayList<CitaComercial>();
		citas.add(cita);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->citas);
		when(clienteApp.filtrarCitaComercial(any(String.class))).thenReturn(response);
		
		List<CitaComercial> CitaComercial = comercialController.filtrarCitaComercial("");
		for(int i =0; i<citas.size(); i++) {
			assertTrue(CitaComercial.get(i).getFecha().equals(citas.get(i).getFecha()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(clienteApp.filtrarCitaComercial(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.filtrarCitaComercial("") == null);
	}
	
	@Test 
	public void testDeleteCitaComercial() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.citasComercialDelete(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.deleteCitaComercial("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.citasComercialDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.deleteCitaComercial("nickname") == false);
	}
	
	@Test
	public void testValidarFecha() {
		String fecha = "1-10-2020";
		String fecha2 = "1/10/2020";
		assertTrue(comercialController.validarFecha(fecha));
		assertFalse(comercialController.validarFecha(fecha2));
	}
	
	@Test 
	public void testDeleteComercial() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.comercialDelete(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.deleteComercial("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.comercialDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.deleteComercial("nickname") == false);
	}
	
	@Test 
	public void testDeleteHorasEmpleados() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.horasEmpleadosDelete(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.deleteHorasEmpleados("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.horasEmpleadosDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.deleteHorasEmpleados("nickname") == false);
	}
	
	@Test 
	public void testDeleteHorasEmpleadosTemporal() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.horasEmpleadosTemporalDelete(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.deleteHorasEmpleadosTemporal("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.horasEmpleadosTemporalDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.deleteHorasEmpleadosTemporal("nickname") == false);
	}
	
	@Test
	public void testSeleccionarHorasComercial() {
		HorasEmpleados p =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(p);
		when(clienteApp.seleccionarHorsEmpleado(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.seleccionarHorasComercial(("Jorge")).getNickname().equals(p.getNickname()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.seleccionarHorsEmpleado(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.seleccionarHorasComercial("PE-1") == null);
	}
	
	@Test
	public void testSeleccionarHorasComercialTemporal() {
		HorasEmpleados p =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(p);
		when(clienteApp.seleccionarHorsEmpleadoTemporal(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.seleccionarHorasComercialTemporal(("Jorge")).getNickname().equals(p.getNickname()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.seleccionarHorsEmpleadoTemporal(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.seleccionarHorasComercialTemporal("PE-1") == null);
	}
	
	@Test
	public void testSeleccionarComercial() {
		Comercial comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(comercial);
		when(clienteApp.comercialSelect(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.seleccionarComercial(("Jorge")).getNickname().equals(comercial.getNickname()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.comercialSelect(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.seleccionarComercial("PE-1") == null);
	}
	
	@Test
	public void testRegistroComercial() {
		Comercial comercial = new Comercial("nickname", "contrasenia", "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1, 1, 1, 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroComercial(any(Comercial.class))).thenReturn(response);
		
		assertTrue(comercialController.registroComercial(comercial) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroComercial(any(Comercial.class))).thenReturn(response1);
		
		assertTrue(comercialController.registroComercial(comercial) == false);
	}
	
	@Test
	public void testRegistrarHorasComercial() {
		HorasEmpleados horas =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroHorasEmpleado(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.registrarHorasComercial("") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroHorasEmpleado(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.registrarHorasComercial("") == false);
	}
	
	@Test
	public void testRegistrarHorasComercialTemporal() {
		HorasEmpleados horas =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroHorasEmpleadoTemporal(any(String.class))).thenReturn(response);
		
		assertTrue(comercialController.registrarHorasComercialTemporal("") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroHorasEmpleadoTemporal(any(String.class))).thenReturn(response1);
		
		assertTrue(comercialController.registrarHorasComercialTemporal("") == false);
	}
}

package concesionario.cliente.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle.Control;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.cliente.ClienteApp;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;

@RunWith(MockitoJUnitRunner.class)
public class GerenteControllerTest {
	public GerenteController gerenteController;
	
	@Mock
	ClienteApp clienteApp;
	
	@Before
	public void setUp() {
		gerenteController = new GerenteController(clienteApp);
		clienteApp = gerenteController.getClienteApp();
	}

	@Test
	public void testCargarTablaEmpleado() {
		Empleado empleado = new Empleado("nickname", "contrasenia", 0, "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(empleado);

		Mockito.when(clienteApp.cargarTablaEmpleados()).thenAnswer(x ->empleados);
		List<Empleado> empleadosSeleccionados = gerenteController.cargarTablaEmpleado();
		
		for (int i = 0; i < empleados.size(); i++) {
			assertTrue(empleadosSeleccionados.get(i).getDNI().equals(empleados.get(i).getDNI()));
		}
	}
	
	@Test
	public void testRegistroMecanico() {
		Mecanico mecanico = new Mecanico("nickname", "contrasenia", 0, "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroMecanico(any(Mecanico.class))).thenReturn(response);
		
		assertTrue(gerenteController.registroMecanico(mecanico) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroMecanico(any(Mecanico.class))).thenReturn(response1);
		
		assertTrue(gerenteController.registroMecanico(mecanico) == false);
	}
	
	@Test
	public void testRegistroComercial() {
		Comercial comercial = new Comercial("nickname", "contrasenia", "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1, 1, 1, 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroComercial(any(Comercial.class))).thenReturn(response);
		
		assertTrue(gerenteController.registroComercial(comercial) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroComercial(any(Comercial.class))).thenReturn(response1);
		
		assertTrue(gerenteController.registroComercial(comercial) == false);
	}
	
	@Test
	public void testRegistroDepartametoCompras() {
		DepartamentoCompras departamentoCompras = new DepartamentoCompras("nickname", "contrasenia", "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroDepartamentoCompras(any(DepartamentoCompras.class))).thenReturn(response);
		
		assertTrue(gerenteController.registroDepartamentoCompras(departamentoCompras) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroDepartamentoCompras(any(DepartamentoCompras.class))).thenReturn(response1);
		
		assertTrue(gerenteController.registroDepartamentoCompras(departamentoCompras) == false);
	}
	
	@Test
	public void testRegistroTarifa() {
		Tarifa tarifa = new Tarifa("T1", "Cambio de Aceite", 50, 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroTarifa(any(Tarifa.class))).thenReturn(response);
		
		assertTrue(gerenteController.registroTarifa(tarifa) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroTarifa(any(Tarifa.class))).thenReturn(response1);
		
		assertTrue(gerenteController.registroTarifa(tarifa) == false);
	}
	
	@Test
	public void testEliminarMecanico() {
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.mecanicoDelete(any(String.class))).thenReturn(response);
		
		assertTrue(gerenteController.eliminarMecanico("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.mecanicoDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(gerenteController.eliminarMecanico("nickname") == false);
	}
	
	@Test
	public void testEliminarComercial() {
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.comercialDelete(any(String.class))).thenReturn(response);
		
		assertTrue(gerenteController.eliminarComercial("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.comercialDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(gerenteController.eliminarComercial("nickname") == false);
	}
	
	@Test
	public void testEliminarDepartamentoCompras() {
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(clienteApp.departamentoComprasDelete(any(String.class))).thenReturn(response);
		
		assertTrue(gerenteController.eliminarDepartamentoCompras("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(clienteApp.departamentoComprasDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(gerenteController.eliminarDepartamentoCompras("nickname") == false);
	}
	
	@Test
	public void testSeleccionarMecanico() {
		Mecanico mecanico = new Mecanico("nickname", "contrasenia", 0, "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(mecanico);
		
		when(clienteApp.mecanicoSelect(any(String.class))).thenReturn(response);
		
		Mecanico mecanicoSeleccionado = gerenteController.seleccionarMecanico("nickname");
		
		assertTrue(mecanicoSeleccionado.getNickname().equals("nickname"));
	}
	
	@Test
	public void testSeleccionarComercial() {
		Comercial comercial = new Comercial("nickname", "contrasenia", "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1, 1, 1, 1);

		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(comercial);
		
		when(clienteApp.comercialSelect(any(String.class))).thenReturn(response);
		
		Comercial comercialSeleccionado = gerenteController.seleccionarComercial("nickname");
		
		assertTrue(comercialSeleccionado.getNickname().equals("nickname"));
	}
	
	@Test
	public void testSeleccionarDepartamentoCompras() {
		DepartamentoCompras departamentoCompras = new DepartamentoCompras("nickname", "contrasenia", "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);

		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(departamentoCompras);
		
		when(clienteApp.departamentoComprasSelect(any(String.class))).thenReturn(response);
		
		DepartamentoCompras departamentoComprasSeleccionado = gerenteController.seleccionarDepartamentoCompras("nickname");
		
		assertTrue(departamentoComprasSeleccionado.getNickname().equals("nickname"));
	}
	
	@Test
	public void testCargarTablaTarifas() {
		Tarifa tarifa = new Tarifa("T1", "Cambio de Aceite", 50, 1);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		tarifas.add(tarifa);
		
		Mockito.when(clienteApp.cargarTablaTarifas()).thenAnswer(x ->tarifas);
		List<Tarifa> tarifasSeleccionados = gerenteController.cargarTablaTarifas();
		
		for (int i = 0; i < tarifas.size(); i++) {
			assertTrue(tarifasSeleccionados.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
		}
	}
	
	@Test
	public void testEliminarTarifa() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.tarifaDelete(any(String.class))).thenReturn(response);
		
		assertTrue(gerenteController.eliminarTarifa("idTarifa") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.tarifaDelete(any(String.class))).thenReturn(response1);

		assertTrue(gerenteController.eliminarTarifa("nickname") == false);
	}
	
	@Test 
	public void testCambioTipo() {
		assertEquals("Mecanico", gerenteController.cambioTipo(0));
		assertEquals("Comercial", gerenteController.cambioTipo(1));
		assertEquals("Departamento Compras", gerenteController.cambioTipo(2));
	}
	
	@Test
	public void testComprobarSexo() {
		assertEquals("Hombre", gerenteController.comprobarSexo(0));
		assertEquals("Mujer", gerenteController.comprobarSexo(1));
		assertEquals("Otro", gerenteController.comprobarSexo(2));
	}
}

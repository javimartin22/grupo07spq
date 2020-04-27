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
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Mecanico;
import concesionario.datos.Presupuesto;
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
	
	@Test
	public void testCambiarEstadoCocheTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteCocheTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		when(webtarget.path(eq("insertCocheTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		CocheTaller cocheTaller = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		int estado = 0;
		
		Response result = clienteApp.cambiarEstadoCocheTaller(cocheTaller, estado);
		assertEquals(200, result.getStatus());	
		
		Response response2 = mock(Response.class);
		Mockito.when(response2.getStatus()).thenReturn(406);
		when(webtarget.path(eq("deleteCocheTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response2);
		assertEquals(null, clienteApp.cambiarEstadoCocheTaller(cocheTaller, estado));
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
	
	//Test Filtros
	@Test
	public void testFiltrarTarifaPrecio() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("precioTarifa")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		int precio = 300;
		
		Response result = clienteApp.filtrarTarifaPrecio(precio);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarTarifaPrecioMin() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("precioMinTarifa")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		int precio = 300;
		
		Response result = clienteApp.filtrarTarifaPrecioMin(precio);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarTarifaHorasMax() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("horasMaxTarifa")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		int horas = 20;
		
		Response result = clienteApp.filtrarTarifaHorasMax(horas);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarTarifaHorasMin() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("horasMinTarifa")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		int horas = 10;
		
		Response result = clienteApp.filtrarTarifaHorasMin(horas);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarVentaMarca() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaMarcaVentas")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String marca = "marca";
		
		Response result = clienteApp.filtrarVentaMarca(marca);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarVentaModelo() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaModeloVentas")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String modelo = "modelo";
		
		Response result = clienteApp.filtrarVentaModelo(modelo);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarVentaComerical() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaComercialVentas")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String comercial = "comercial";
		
		Response result = clienteApp.filtrarVentaComercial(comercial);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarPresupuestoCodigo() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaPresupuestoCodigo")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String codigo = "cod";
		
		Response result = clienteApp.filtrarPresupuestoCodigo(codigo);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarPresupuestoCliente() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaPresupuestoCliente")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String cliente = "Cliente";
		
		Response result = clienteApp.filtrarPresupuestoCliente(cliente);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarPresupuestoProblema() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaPresupuestoProblema")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String problema = "Problema";
		
		Response result = clienteApp.filtrarPresupuestoProblema(problema);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarCocheConcesionario() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaCocheConcesionarioFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarCocheConcesionario(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarCocheMatriculado() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaCocheMatriculadoFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarCocheMatriculado(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarCocheTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaCocheTallerFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarCocheTaller(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarPiezaMecanico() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaPiezaMecanicoFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarPiezaMecanico(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarPiezaUtilizadas() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaPiezaUtilizadasFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarPiezaUtilizadas(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testCargarTablaClientesFidelidad() {
		ClienteFidelidad c1 = new ClienteFidelidad("12345678K", 2);
		ClienteFidelidad c2 = new ClienteFidelidad("22345678K", 3);
		List<ClienteFidelidad> clientes = new ArrayList<ClienteFidelidad>();
		clientes.add(c1);
		clientes.add(c2);
		
		when(webtarget.path(eq("loadClientesFidelidadTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->clientes);
		List<ClienteFidelidad> clientesSeleccionados = clienteApp.cargarTablaClientesFidelidad();
		
		for (int i = 0; i < clientes.size(); i++) {
			assertTrue(clientesSeleccionados.get(i).getDni().equals(clientes.get(i).getDni()));
		}
	}
	
	@Test
	public void testCargarTablaTarifas() {
		Tarifa t1 = new Tarifa("T1", "Cambio Aceite", 50, 1);
		Tarifa t2 = new Tarifa("T2", "Liqudio frenos", 50, 1);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		tarifas.add(t1);
		tarifas.add(t2);
		
		when(webtarget.path(eq("loadTarifasTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->tarifas);
		List<Tarifa> tarifasSeleccionadas = clienteApp.cargarTablaTarifas();
		
		for (int i = 0; i < tarifas.size(); i++) {
			assertTrue(tarifasSeleccionadas.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
		}
	}
	
	@Test
	public void testSeleccionarPresupuesto() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectPresupuesto")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String codigo = "codigo";
		
		Response result = clienteApp.seleccionarPresupuesto(codigo);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testCargarTablaPresupuestos() {
		Presupuesto p1 = new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		Presupuesto p2 = new Presupuesto("PE-2", "12345678B", "Andres", "Nissan", "Panamera", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		presupuestos.add(p1);
		presupuestos.add(p2);
		
		when(webtarget.path(eq("loadPresupuestosTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->presupuestos);
		List<Presupuesto> presupSeleccionados = clienteApp.cargarTablaPresupuestos();
		
		for (int i = 0; i < presupuestos.size(); i++) {
			assertTrue(presupSeleccionados.get(i).getCodigo().equals(presupuestos.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testSeleccionarCocheConcesionario() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectCocheConcesionario")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String modelo = "modelo";
		
		Response result = clienteApp.seleccionarCocheConcesionario(modelo);
		assertEquals(200, result.getStatus());	
	}
	
	
}


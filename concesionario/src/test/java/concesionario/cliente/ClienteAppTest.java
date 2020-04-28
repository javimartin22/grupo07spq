

package concesionario.cliente;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
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
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Presupuesto;
import concesionario.datos.Proveedor;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;

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
	public void testMecanicoSelect() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectMecanico")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.mecanicoSelect(nickname);
		assertEquals(200, result.getStatus());
	}
	
	@Test 
	public void testMecanicoDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteMecanico")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.mecanicoDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testComercialSelect() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "Jorgico";
		
		Response result = clienteApp.comercialSelect(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testComercialDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "Jorgico";
		
		Response result = clienteApp.comercialDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testCocheTallerDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteCocheTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String matricula = "matricul";
		
		Response result = clienteApp.CocheTallerDelete(matricula);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testTarifaDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteTarifa")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String idTarifa = "T1";
		
		Response result = clienteApp.tarifaDelete(idTarifa);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testDepartamentoComprasSelect() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectDepartamentoCompras")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.departamentoComprasSelect(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testDepartamentoComprasDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteDepartamentoCompras")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.departamentoComprasDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testRegistroVenta() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Venta venta = new Venta("", "Leon", "Seat", "1234JJJ", "Jorge", "Pablo"); 
		Response result = clienteApp.registroVenta(venta);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testCargarTablaPiezas() {
		Pieza pieza1 = new Pieza("P1", "pieza1", 0, "");
		Pieza pieza2 = new Pieza("P2", "", 0, "");
		List<Pieza> piezas = new ArrayList<Pieza>();
		piezas.add(pieza1);
		piezas.add(pieza2);
		
		when(webtarget.path(eq("loadPiezaTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->piezas);
		List<Pieza> piezasSelect = clienteApp.cargarTablaPiezas();
		
		for (int i = 0; i < piezas.size(); i++) {
			assertTrue(piezasSelect.get(i).getCodigo().equals(piezas.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testPiezaUtilizadaSelect() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectPiezaUtilizada")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String codigo = "P1";
		
		Response result = clienteApp.piezaUtilizadaSelect(codigo);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testRegistroPieza() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Pieza pieza = new Pieza("P1", "", 0, "");
		Response result = clienteApp.registroPieza(pieza);
		
		assertEquals(200, result.getStatus());		
	}
	
	@Test
	public void testRegistroPresupuesto() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		Presupuesto presupuesto = new Presupuesto("PE1", "", "", "", "·", "", 0, "", "", 0, "");
		Response result = clienteApp.registroPresupuesto(presupuesto);
		
		assertEquals(200, result.getStatus());		
	}
	
	@Test
	public void testCargarTablaCochesCocheMatriculados() {
		CocheMatriculado cocheMatriculado1 = new CocheMatriculado("Seat", "Leon", "", "", "", 0, 0, 0, 0);
		CocheMatriculado cocheMatriculado2 = new CocheMatriculado("Renault", "Clio", "", "", "", 0, 0, 0, 0);
		List<CocheMatriculado> coches = new ArrayList<CocheMatriculado>();
		coches.add(cocheMatriculado1);
		coches.add(cocheMatriculado2);
		
		when(webtarget.path(eq("loadCochesMatricTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->coches);
		List<CocheMatriculado> cochesSelect = clienteApp.cargarTablaCochesCocheMatriculados();
		
		for (int i = 0; i < coches.size(); i++) {
			assertTrue(cochesSelect.get(i).getMarca().equals(coches.get(i).getMarca()));
		}
	}
	
	@Test
	public void testCargarTablaEmpleados() {
		Empleado empleado = new Empleado("Pablo", "", 0, "", "", "", "", "", "", 0, "", "", "", 0, "", 0);
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados.add(empleado);
		
		when(webtarget.path(eq("loadEmpleadosTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->empleados);
		List<Empleado> empleadoSelect = clienteApp.cargarTablaEmpleados();
		
		for (int i = 0; i < empleados.size(); i++) {
			assertTrue(empleadoSelect.get(i).getNickname().equals(empleados.get(i).getNickname()));
		}
	}
	
	@Test 
	public void testCargarTablaVenta() {
		Venta venta = new Venta("V1", "", "", "", "", "");
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		
		when(webtarget.path(eq("loadVentaTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->ventas);
		List<Venta> ventasSelect = clienteApp.cargarTablaVenta();
		
		for (int i = 0; i < ventas.size(); i++) {
			assertTrue(ventasSelect.get(i).getFecha().equals(ventas.get(i).getFecha()));
		}
	}
	
	@Test 
	public void testCargarTablaCocheTaller() {
		CocheTaller coche = new CocheTaller("12345JJJ", "", "", "", "", 0, 0);
		List<CocheTaller> coches = new ArrayList<CocheTaller>();
		coches.add(coche);
		
		when(webtarget.path(eq("loadCocheTallerTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->coches);
		List<CocheTaller> cochesSelect = clienteApp.cargarTablaCocheTaller();
		
		for (int i = 0; i < coches.size(); i++) {
			assertTrue(cochesSelect.get(i).getMatricula().equals(coches.get(i).getMatricula()));
		}
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
	
		
	@Test
	public void testCargarListaProveedores() {
		Proveedor p1 = new Proveedor("idProveedor1", "nombre1", "pais1", "tipoPiezas1");
		Proveedor p2 = new Proveedor("idProveedor2", "nombre2", "pais2", "tipoPiezas2");
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		proveedores.add(p1);
		proveedores.add(p2);
		
		when(webtarget.path(eq("loadProveedores")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->proveedores);
		List<Proveedor> proveedoresSelect = clienteApp.cargarListaProveedores();
		
		for (int i = 0; i < proveedores.size(); i++) {
			assertTrue(proveedoresSelect.get(i).getIdProveedor().equals(proveedores.get(i).getIdProveedor()));
		}
	}
	
	@Test
	public void testCargarListaPiezasProveedores() {
		PiezaProveedores p1 = new PiezaProveedores("P1", "Amortiguador", 0, "", "");
		PiezaProveedores p2 = new PiezaProveedores("P2", "Amortiguador", 0, "", "");
		List<PiezaProveedores> proveedores = new ArrayList<PiezaProveedores>();
		proveedores.add(p1);
		proveedores.add(p2);
		
		when(webtarget.path(eq("loadPiezasProveedoresList")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->proveedores);
		List<PiezaProveedores> proveedoresSelect = clienteApp.cargarListaPiezasProveedores();
		
		for (int i = 0; i < proveedores.size(); i++) {
			assertTrue(proveedoresSelect.get(i).getCodigo().equals(proveedores.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testCargarTablaPiezasUtilizadas() {
		Pieza pieza1 = new Pieza("P1", "pieza1", 0, "");
		Pieza pieza2 = new Pieza("P2", "", 0, "");
		List<Pieza> piezas = new ArrayList<Pieza>();
		piezas.add(pieza1);
		piezas.add(pieza2);
		
		when(webtarget.path(eq("loadPiezaUtilizadasTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->piezas);
		List<Pieza> piezasSelect = clienteApp.cargarTablaPiezasUtilizadas();
		
		for (int i = 0; i < piezas.size(); i++) {
			assertTrue(piezasSelect.get(i).getCodigo().equals(piezas.get(i).getCodigo()));
		}
	}
	
	@After
	public void setDown() {
		Cliente cliente = new Cliente();
	}
	
}


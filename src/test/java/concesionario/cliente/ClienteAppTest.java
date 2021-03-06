package concesionario.cliente;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.datos.CitaComercial;
import concesionario.datos.CitaTaller;
import concesionario.datos.Cliente;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Presupuesto;
import concesionario.datos.Proveedor;
import concesionario.datos.ProveedorHerramientas;
import concesionario.datos.SolicitudCompra;
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
	public void testRegistroHorasEmpleado() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String horasempleado = "10-40";
		Response result = clienteApp.registroHorasEmpleado(horasempleado);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroHorasEmpleadoTemporal() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String horasempleado = "10-40";
		Response result = clienteApp.registroHorasEmpleadoTemporal(horasempleado);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroCitaTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		CitaTaller citataller = new CitaTaller();
		Response result = clienteApp.registroCitaTaller(citataller);
		
		assertEquals(200, result.getStatus());		

	}
	
	@Test
	public void testRegistroCitaComercial() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		CitaComercial citacomercial = new CitaComercial();
		Response result = clienteApp.registroCitaComercial(citacomercial);
		
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
	public void testCitasMecanicoDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteCitasMecanico")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.citasMecanicoDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test 
	public void testCitasComercialDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteCitasComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.citasComercialDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test 
	public void testHorasEmpleadoTemporalDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteHorasEmpleadosTemporal")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.horasEmpleadosTemporalDelete(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test 
	public void testHorasEmpleadoDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteHorasEmpleados")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "user";
		
		Response result = clienteApp.horasEmpleadosDelete(nickname);
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
	public void testSolicitudCompraDelete() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("deleteSolicitudCompra")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String cod = "S1";
		
		Response result = clienteApp.SolicitudCompraDelete(cod);
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
	public void testCargarTablaComercial() {
		Comercial comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(comercial);
		
		when(webtarget.path(eq("loadComercialTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->comerciales);
		List<Comercial> piezasSelect = clienteApp.cargarTablaComercial();
		
		for (int i = 0; i < comerciales.size(); i++) {
			assertTrue(piezasSelect.get(i).getDNI().equals(comerciales.get(i).getDNI()));
		}
	}
	
	@Test
	public void testCargarTablaHerramientasTaller() {
		HerramientasTaller h1 = new HerramientasTaller("H1", "herr1", 0, "tipo1");
		HerramientasTaller h2 = new HerramientasTaller("H2", "herr2", 0, "tipo2");
		List<HerramientasTaller> herramientas = new ArrayList<HerramientasTaller>();
		herramientas.add(h1);
		herramientas.add(h2);
		
		when(webtarget.path(eq("loadHerramientaTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->herramientas);
		List<HerramientasTaller> herramientasSelect = clienteApp.cargarTablaHerramientasTaller();
		
		for (int i = 0; i < herramientas.size(); i++) {
			assertTrue(herramientasSelect.get(i).getCodigo().equals(herramientas.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testCargarTablaSolicitudCompra() {
		SolicitudCompra s1 = new SolicitudCompra("S1", "barrita","Piezas", 7);
		SolicitudCompra s2 = new SolicitudCompra("S2", "medidor","Herramientas", 9);
		List<SolicitudCompra> solicitudes = new ArrayList<SolicitudCompra>();
		solicitudes.add(s1);
		solicitudes.add(s2);
		
		when(webtarget.path(eq("loadSolicitudTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->solicitudes);
		List<SolicitudCompra> solSelect = clienteApp.cargarTablaSolicitudCompra();
		
		for (int i = 0; i < solicitudes.size(); i++) {
			assertTrue(solSelect.get(i).getCodigo().equals(solicitudes.get(i).getCodigo()));
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
	public void testRegistroHerramienta() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		HerramientasTaller herramienta = new HerramientasTaller("H1", "herr1", 0, "tipo1");
		Response result = clienteApp.registroHerramienta(herramienta);
		
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
	public void testRegistroSolicitud() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(anyString()).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		SolicitudCompra solicitud = new SolicitudCompra("S2", "Herr", "Herramientas", 6);
		Response result = clienteApp.registroSolicitud(solicitud);
		
		assertEquals(200, result.getStatus());		
	}
	
	@Test
	public void testInsertarHerramientas() {
		
		Herramientas h1 = new Herramientas("H1", "herr1", 42, "tipo1","Oscaro");
		Herramientas h2 = new Herramientas("H2", "herr2", 23, "tipo2","Oscaro");
		List<Herramientas> herramientas = new ArrayList<Herramientas>();
		herramientas.add(h1);
		herramientas.add(h2);
		
		when(webtarget.path(eq("insertHerramienta")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->herramientas);
		List<Herramientas> herramientasSelect = clienteApp.insertarHerramientas();	
		for (int i = 0; i < herramientas.size(); i++) {
			assertTrue(herramientasSelect.get(i).getCodigo().equals(herramientas.get(i).getCodigo()));
		}
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
	public void testFiltrarHerramientaMecanico() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaHerramientaMecanicoFiltro")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "Filtro";
		
		Response result = clienteApp.filtrarHerramientaMecanico(filtro);
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
	public void testCargarEmpleadoHoras() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("cargarEmpleadoHoras")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		int filtro = 0;
		
		Response result = clienteApp.cargarEmpleadoHoras(filtro);
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
	public void testSeleccionarHorsEmpleadoTemporal() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectHorasEmpleadosTemporal")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "nickname";
		
		Response result = clienteApp.seleccionarHorsEmpleadoTemporal(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testSeleccionarHorsEmpleado() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectHorasEmpleados")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String nickname = "nickname";
		
		Response result = clienteApp.seleccionarHorsEmpleado(nickname);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testSeleccionarCitaComercial() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectCitaComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String restriccion = "Nick-0";
		
		Response result = clienteApp.seleccionarCitaComercial(restriccion);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testSeleccionarCitaTaller() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("selectCitaTaller")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String restriccion = "Nick-0";
		
		Response result = clienteApp.seleccionarCitaTaller(restriccion);
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
	public void testCargarTablaMecanico() {
		Mecanico m1 = new Mecanico("nickname1","1234", 1, "45945312X", "Federico", "Malatrustegui", "Hombre", "fede@fedex.com", "Portugalete", 48920, "Carlos VII", "123456789", "987654321", 2500, "675541190", 25);
		Mecanico m2 = new Mecanico("nickname2","12354", 1, "459467212D", "Agustin", "Zalatrustegui", "Hombre", "agus@agusto.com", "Portugalete", 48920, "Calle falsa", "123954789", "887654721", 3500, "775521190", 30);
		List<Mecanico> mecanicos = new ArrayList<Mecanico>();
		mecanicos.add(m1);
		mecanicos.add(m2);
		
		when(webtarget.path(eq("loadMecanicoTable")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->mecanicos);
		List<Mecanico> mecanicoSelect = clienteApp.cargarTablaMecanico();
		
		for (int i = 0; i < mecanicoSelect.size(); i++) {
			assertTrue(mecanicoSelect.get(i).getDNI().equals(mecanicos.get(i).getDNI()));
		}
	}
	
	@Test
	public void testCargarListaProveedoresHerramientas() {
		ProveedorHerramientas p1 = new ProveedorHerramientas("idProveedor1", "nombre1", "pais1", "tipoHerramientas1");
		ProveedorHerramientas p2 = new ProveedorHerramientas("idProveedor2", "nombre2", "pais2", "tipoHerramientas2");
		List<ProveedorHerramientas> proveedoresHerramientas = new ArrayList<ProveedorHerramientas>();
		proveedoresHerramientas.add(p1);
		proveedoresHerramientas.add(p2);
		
		when(webtarget.path(eq("loadProveedoresHerramientaList")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->proveedoresHerramientas);
		List<ProveedorHerramientas> proveedoresSelect = clienteApp.cargarListaProveedoresHerramientas();
		
		for (int i = 0; i < proveedoresHerramientas.size(); i++) {
			assertTrue(proveedoresSelect.get(i).getIdProveedor().equals(proveedoresHerramientas.get(i).getIdProveedor()));
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
	
	@Test
	public void testCargarListaHerramientas() {
		Herramientas h1 = new Herramientas("H1","Alicates manguera",4,"Alicates","Oscaro");
		Herramientas h2 = new Herramientas("H2","Presion aceite",4,"Inspeccion","Oscaro");
		List<Herramientas> herramientas = new ArrayList<Herramientas>();
		herramientas.add(h1);
		herramientas.add(h2);
		
		when(webtarget.path(eq("loadHerramientasList")).request(anyString()).get(any(GenericType.class))).thenAnswer(x ->herramientas);
		List<Herramientas> herramientasSelect = clienteApp.cargarListaHerramientas();
		
		for (int i = 0; i < herramientas.size(); i++) {
			assertTrue(herramientasSelect.get(i).getCodigo().equals(herramientas.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testCargarCitaComercial() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaCitaComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String comercial = "";
		
		Response result = clienteApp.cargarCitaComercial(comercial);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarCitaComercial() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("filtrarTablaCitaComercial")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "filtro";
		
		Response result = clienteApp.filtrarCitaComercial(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testCargarCitaMecanico() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("loadTablaCitaMecanico")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String mecanico = "";
		
		Response result = clienteApp.cargarCitaMecanico(mecanico);
		assertEquals(200, result.getStatus());	
	}
	
	@Test
	public void testFiltrarCitaMecanico() {
		Response response = mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(webtarget.path(eq("filtrarTablaCitaMecanico")).request(anyString()).post(any(Entity.class))).thenReturn(response);
		
		String filtro = "filtro";
		
		Response result = clienteApp.filtrarCitaMecanico(filtro);
		assertEquals(200, result.getStatus());	
	}
	
	@After
	public void setDown() {
		Cliente cliente = new Cliente();
	}
	
}


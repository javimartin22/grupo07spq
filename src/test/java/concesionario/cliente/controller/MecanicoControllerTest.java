package concesionario.cliente.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
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
import concesionario.datos.CitaTaller;
import concesionario.datos.Cliente;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.SolicitudCompra;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class MecanicoControllerTest {
	
	public MecanicoController mecanicoController;
	
	@Mock
	ClienteApp cliente;
	
	@Before
	public void setUp() {
		mecanicoController = new MecanicoController(cliente);
		cliente = mecanicoController.getClienteApp();
	}
	
	@Test
	public void testRegistrarCocheTaller() {
		CocheTaller coche = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registrarCocheTaller(any(CocheTaller.class))).thenReturn(response);
		assertTrue(mecanicoController.registrarCocheTaller(coche) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registrarCocheTaller(any(CocheTaller.class))).thenReturn(response1);
		assertTrue(mecanicoController.registrarCocheTaller(coche) == false);
	}
	
	@Test
	public void testCargarPiezas() {
		List<Pieza> piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
		Pieza p2 = new Pieza("P2", "Correa", 7, "Almacen 2");
		piezas.add(p1);
		piezas.add(p2);
		
		
		Mockito.when(cliente.cargarTablaPiezas()).thenAnswer(x ->piezas);
		List<Pieza> piezas_result = mecanicoController.cargarPiezas();
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
	}
	
	@Test
	public void testCargarHerramientasTaller() {

		List<HerramientasTaller> herramientas = new ArrayList<HerramientasTaller>();
		HerramientasTaller h1 = new HerramientasTaller("H3", "Presion aceite", 4, "Alamacen 1 - Estanteria 3");
		HerramientasTaller h2 = new HerramientasTaller("H4", "Saca grapas", 3, "Alamacen 2 - Estanteria 1");
		herramientas.add(h1);
		herramientas.add(h2);
		
		
		Mockito.when(cliente.cargarTablaHerramientasTaller()).thenAnswer(x ->herramientas);
		List<HerramientasTaller> piezas_result = mecanicoController.cargarHerramientasTaller();
		
		for(int i=0; i<herramientas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(herramientas.get(i).getNombre()));
		}
	}
	@Test
	public void testCargarListaHerramientas() {

		List<Herramientas> herramientas = new ArrayList<Herramientas>();
		Herramientas h1 = new Herramientas("H3", "Presion aceite", 4, "Alamacen 1 - Estanteria 3","Oscaro");
		Herramientas h2 = new Herramientas("H4", "Saca grapas", 3, "Alamacen 2 - Estanteria 1","Iberisa");
		herramientas.add(h1);
		herramientas.add(h2);
		
		
		Mockito.when(cliente.cargarListaHerramientas()).thenAnswer(x ->herramientas);
		List<Herramientas> herramientas_result = mecanicoController.cargarListaHerramientas();
		
		for(int i=0; i<herramientas.size(); i++) {
			assertTrue(herramientas_result.get(i).getNombre().equals(herramientas.get(i).getNombre()));
		}
	}
	@Test
	public void testCargarPiezasUtilizadas() {
		List<Pieza> piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
		Pieza p2 = new Pieza("P2", "Correa", 7, "Almacen 2");
		piezas.add(p1);
		piezas.add(p2);
		
		Mockito.when(cliente.cargarTablaPiezasUtilizadas()).thenAnswer(x ->piezas);
		List<Pieza> piezas_result = mecanicoController.cargarPiezasUtilizadas();
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
	}
	
	@Test
	public void testCargarCochesMatriculados() {
		List<CocheMatriculado> coches = new ArrayList<CocheMatriculado>();
		CocheMatriculado cocheMat = new CocheMatriculado("Opel", "Corsa", "2838GBJ", "Juan", "Azul",3, 2006, 80, 4);
		CocheMatriculado cocheMat2 = new CocheMatriculado("Opel", "Corsa", "2838GBH", "Juan", "Azul",3, 2006, 80, 4);
		coches.add(cocheMat);
		coches.add(cocheMat2);
		
		Mockito.when(cliente.cargarTablaCochesCocheMatriculados()).thenAnswer(x ->coches);
		List<CocheMatriculado>coches_result = mecanicoController.cargarCochesMatriculados();
		
		for(int i=0; i<coches.size(); i++) {
			assertTrue(coches_result.get(i).getMatricula().equals(coches.get(i).getMatricula()));
		}
	}
	
	@Test 
	public void testCargarTablaCocheTaller() {
		List<CocheTaller> coches = new ArrayList<CocheTaller>();
		CocheTaller cocheTa = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		CocheTaller cocheTa2 = new CocheTaller("2544KKB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		coches.add(cocheTa);
		coches.add(cocheTa2);
		
		Mockito.when(cliente.cargarTablaCocheTaller()).thenAnswer(x ->coches);
		List<CocheTaller>coches_result = mecanicoController.cargarTablaCocheTaller();
		
		for(int i=0; i<coches.size(); i++) {
			assertTrue(coches_result.get(i).getMatricula().equals(coches.get(i).getMatricula()));
		}
	}
		
	@Test
	public void testDeleteCocheTaller() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.CocheTallerDelete(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.deleteCocheTaller("0000ABC") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.CocheTallerDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.deleteCocheTaller("0000ABC") == false);
	}
	@Test
	public void testRegistroPresupuesto() {
		Presupuesto p= new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registroPresupuesto(any(Presupuesto.class))).thenReturn(response);
		
		assertTrue(mecanicoController.registroPresupuesto(p)== true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registroPresupuesto(any(Presupuesto.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.registroPresupuesto(p)== false);
	}
	@Test
	public void testRegistroSolicitud() {
		SolicitudCompra solicitud =  new SolicitudCompra("","","",3);
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registroSolicitud(any(SolicitudCompra.class))).thenReturn(response);
		
		assertTrue(mecanicoController.registroSolicitud(solicitud)== true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registroSolicitud(any(SolicitudCompra.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.registroSolicitud(solicitud)== false);
	}
	@Test
	public void testSeleccionarCocheTaller() {
		CocheTaller coche = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(coche);
		when(cliente.seleccionarCocheTaller(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.seleccionarCocheTaller("2444KLB").getMatricula().equals(coche.getMatricula()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(cliente.seleccionarCocheTaller(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.seleccionarCocheTaller("2444KLB") == null);
	}
	
	@Test
	public void testCambiarEstadoCocheTaller() {
		CocheTaller coche = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.cambiarEstadoCocheTaller(any(CocheTaller.class),any(Integer.class))).thenReturn(response);
		
		assertTrue(mecanicoController.cambiarEstadoCocheTaller(coche,0) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.cambiarEstadoCocheTaller(any(CocheTaller.class),any(Integer.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.cambiarEstadoCocheTaller(coche,0) == false);
	}
	
	@Test 
	public void testCargarTablaPresupuesto() {
		List<Presupuesto> presups = new ArrayList<Presupuesto>();
		Presupuesto p1 = new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		Presupuesto p2 = new Presupuesto("PE-2", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		presups.add(p1);
		presups.add(p2);
		
		
		Mockito.when(cliente.cargarTablaPresupuestos()).thenAnswer(x ->presups);
		List<Presupuesto>presup_result = mecanicoController.cargarTablaPresupuesto();
		
		for(int i=0; i<presups.size(); i++) {
			assertTrue(presup_result.get(i).getCodigo().equals(presups.get(i).getCodigo()));
		}
	}
	
	@Test
	public void testSeleccionarPresupuesto() {
		Presupuesto p =  new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(p);
		when(cliente.seleccionarPresupuesto(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.seleccionarPresupuesto("PE-1").getCodigo().equals(p.getCodigo()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(cliente.seleccionarPresupuesto(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.seleccionarPresupuesto("PE-1") == null);
	}

	@Test
	public void testFiltrarPresupuestoCodigo() {
		List<Presupuesto> presups= new ArrayList<Presupuesto>();
		presups.add(new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		presups.add(new Presupuesto("PE-2", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->presups);
		when(cliente.filtrarPresupuestoCodigo(any(String.class))).thenReturn(response);
		
		List<Presupuesto> presup_result = mecanicoController.filtrarPresupuestoCodigo("PE-");
		for(int i =0; i<presups.size(); i++) {
			assertTrue(presup_result.get(i).getCodigo().equals(presups.get(i).getCodigo()));
		}
		
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarPresupuestoCodigo(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarPresupuestoCodigo("PE-") == null);
	}

	@Test
	public void testFiltrarPresupuestoCliente() {
		List<Presupuesto> presups= new ArrayList<Presupuesto>();
		presups.add(new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		presups.add(new Presupuesto("PE-2", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->presups);
		when(cliente.filtrarPresupuestoCliente(any(String.class))).thenReturn(response);
		
		List<Presupuesto> presup_result = mecanicoController.filtrarPresupuestoCliente("Jorge");
		for(int i =0; i<presups.size(); i++) {
			assertTrue(presup_result.get(i).getCodigo().equals(presups.get(i).getCodigo()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarPresupuestoCliente(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarPresupuestoCliente("Jorge") == null);
	}
	
	@Test
	public void testFiltrarPresupuestoProblema() {
		List<Presupuesto> presups= new ArrayList<Presupuesto>();
		presups.add(new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		presups.add(new Presupuesto("PE-2", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->presups);
		when(cliente.filtrarPresupuestoProblema(any(String.class))).thenReturn(response);
		
		List<Presupuesto> presup_result = mecanicoController.filtrarPresupuestoProblema("Aceite");
		for(int i =0; i<presups.size(); i++) {
			assertTrue(presup_result.get(i).getCodigo().equals(presups.get(i).getCodigo()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarPresupuestoProblema(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarPresupuestoProblema("Aceite") == null);
	}
	
	@Test
	public void testFiltrarCocheMatriculado() {
		List<CocheMatriculado>coches= new ArrayList<CocheMatriculado>();
		coches.add(new CocheMatriculado("Opel", "Corsa", "2838GBJ", "Juan", "Azul",3, 2006, 80, 4));
		coches.add(new CocheMatriculado("Opel", "Corsa", "2838KBJ", "Juan", "Azul",3, 2006, 80, 4));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->coches);
		when(cliente.filtrarCocheMatriculado(any(String.class))).thenReturn(response);
		
		List<CocheMatriculado> coches_result = mecanicoController.filtrarCocheMatriculado("filtro");
		for(int i =0; i<coches.size(); i++) {
			assertTrue(coches_result.get(i).getMatricula().equals(coches.get(i).getMatricula()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarCocheMatriculado(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarCocheMatriculado("filtro") == null);
	}
	
	@Test
	public void testFiltrarPiezaMecanico() {
		List<Pieza>piezas= new ArrayList<Pieza>();
		piezas.add(new Pieza("P1", "Amortiguador", 5, "Almacen 1"));
		piezas.add(new Pieza("P2", "Amortiguador", 5, "Almacen 1"));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->piezas);
		when(cliente.filtrarPiezaMecanico(any(String.class))).thenReturn(response);
		
		List<Pieza> piezas_result = mecanicoController.filtrarPiezaMecanico("filtro");
		for(int i =0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getCodigo().equals(piezas.get(i).getCodigo()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarPiezaMecanico(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarPiezaMecanico("filtro") == null);
	}
	
	@Test
	public void testFiltrarHerramientaMecanico() {
		List<HerramientasTaller>herramientas= new ArrayList<HerramientasTaller>();
		herramientas.add(new HerramientasTaller("H3", "Presion aceite", 4, "Alamacen 1 - Estanteria 3"));
		herramientas.add(new HerramientasTaller("H4", "Saca grapas", 3, "Alamacen 2 - Estanteria 1"));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->herramientas);
		when(cliente.filtrarHerramientaMecanico(any(String.class))).thenReturn(response);
		
		List<HerramientasTaller> herramientas_result = mecanicoController.filtrarHerramientaMecanico("filtro");
		for(int i =0; i<herramientas.size(); i++) {
			assertTrue(herramientas_result.get(i).getCodigo().equals(herramientas.get(i).getCodigo()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarHerramientaMecanico(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarHerramientaMecanico("filtro") == null);
	}

	@Test
	public void testFiltrarCocheTaller() {
		List<CocheTaller>coches= new ArrayList<CocheTaller>();
		coches.add(new CocheTaller("1044HLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0));
		coches.add(new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0));
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->coches);
		when(cliente.filtrarCocheTaller(any(String.class))).thenReturn(response);
		
		List<CocheTaller> coches_result = mecanicoController.filtrarCocheTaller("filtro");
		for(int i =0; i<coches.size(); i++) {
			assertTrue(coches_result.get(i).getMatricula().equals(coches.get(i).getMatricula()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarCocheTaller(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarCocheTaller("filtro") == null);
	}
	@Test
	public void testTraducirEstado() {
		assertEquals("Sin Empezar", mecanicoController.traducirEstado(0));
		assertEquals("En proceso", mecanicoController.traducirEstado(1));
		assertEquals("Terminado", mecanicoController.traducirEstado(2));
	}
	
	@Test public void testParseFechaPresupuesto() {
		Calendar c = Calendar.getInstance();
		String fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " - Bilbao"; 
		
		assertEquals(fecha, mecanicoController.parseFechaPresupuesto());
	}
		
	@Test
	public void testParseFecha() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE); 
		assertEquals(fecha, mecanicoController.parseFecha());
	}
	
	@Test 
	public void testCrearCodigo() {
		List<Presupuesto> presupuestos= new ArrayList<Presupuesto>();
		presupuestos.add(new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		presupuestos.add(new Presupuesto("PE-2", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020"));
		assertEquals("P3", mecanicoController.crearCodigo(presupuestos));
		
		List<Presupuesto> presupuestos1 = new ArrayList<Presupuesto>();
		assertEquals("P1", mecanicoController.crearCodigo(presupuestos1));
	}
	
	@Test 
	public void testCargarSolicitud() {
		List<SolicitudCompra> solicitud= new ArrayList<SolicitudCompra>();
		solicitud.add(new SolicitudCompra("S1","Barrita","Herramientas",3));
		solicitud.add(new SolicitudCompra("S2","Amortiguador","Piezas",3));
		
		Mockito.when(cliente.cargarTablaSolicitudCompra()).thenAnswer(x ->solicitud);
		List<SolicitudCompra> sol = mecanicoController.cargarSolicitud();
		
		for(int i=0; i<solicitud.size(); i++) {
			assertTrue(sol.get(i).getCodigo().equals(solicitud.get(i).getCodigo()));
		}
	}
	
	@Test 
	public void testCargarClienteFidelidad() {
		List<ClienteFidelidad> clientes= new ArrayList<ClienteFidelidad>();
		clientes.add(new ClienteFidelidad("dni", 1));
		clientes.add(new ClienteFidelidad("dni1", 3));
		
		Mockito.when(cliente.cargarTablaClientesFidelidad()).thenAnswer(x ->clientes);
		List<ClienteFidelidad> clientesFidelidad = mecanicoController.cargarClienteFidelidad();
		
		for(int i=0; i<clientes.size(); i++) {
			assertTrue(clientesFidelidad.get(i).getDni().equals(clientes.get(i).getDni()));
		}
	}
	
	@Test
	public void testCargarCitaMecanico() {
		CitaTaller cita= new CitaTaller("Mikel", "12312312A", "11-5-2020", "10:30", "Jorge", "Suspension");
		List<CitaTaller> citas = new ArrayList<CitaTaller>();
		citas.add(cita);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->citas);
		when(cliente.cargarCitaMecanico(any(String.class))).thenReturn(response);
		
		List<CitaTaller> CitaMecanico = mecanicoController.cargarCitaMecanico("");
		for(int i =0; i<citas.size(); i++) {
			assertTrue(CitaMecanico.get(i).getNombre().equals(citas.get(i).getNombre()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.cargarCitaMecanico(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.cargarCitaMecanico("") == null);
	}
	
	@Test
	public void testFiltrarCitaMecanico() {
		CitaTaller cita= new CitaTaller("Mikel", "12312312A", "11-5-2020", "10:30", "Jorge", "BBombillas");
		List<CitaTaller> citas = new ArrayList<CitaTaller>();
		citas.add(cita);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->citas);
		when(cliente.filtrarCitaMecanico(any(String.class))).thenReturn(response);
		
		List<CitaTaller> CitaMecanico = mecanicoController.filtrarCitaMecanico("");
		for(int i =0; i<citas.size(); i++) {
			assertTrue(CitaMecanico.get(i).getFecha().equals(citas.get(i).getFecha()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->null);
		when(cliente.filtrarCitaMecanico(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.filtrarCitaMecanico("") == null);
	}
	
	@Test
	public void testValidarFecha() {
		String fecha = "1-10-2020";
		String fecha2 = "1/10/2020";
		assertTrue(mecanicoController.validarFecha(fecha));
		assertFalse(mecanicoController.validarFecha(fecha2));
	}
	
	@Test 
	public void testDeleteCitaMecanico() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(cliente.citasMecanicoDelete(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.deleteCitaMecanico("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(cliente.citasMecanicoDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.deleteCitaMecanico("nickname") == false);
	}
	
	@Test 
	public void testCalcularCodigoSolicitud() {
		List<SolicitudCompra> solicitud= new ArrayList<SolicitudCompra>();
		solicitud.add(new SolicitudCompra("S1","Barrita","Herramientas",3));
		solicitud.add(new SolicitudCompra("S2","Amortiguador","Piezas",3));
	
		String solicitudSelect = mecanicoController.calcularCodigoSolicitud(solicitud);
		assertTrue(solicitudSelect.equals("S3"));
	}
	
	@Test
	public void testSeleccionarHorasMecanico() {
		HorasEmpleados p =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(p);
		when(cliente.seleccionarHorsEmpleado(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.seleccionarHorasMecanico(("Jorge")).getNickname().equals(p.getNickname()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(cliente.seleccionarHorsEmpleado(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.seleccionarHorasMecanico("PE-1") == null);
	}
	
	@Test
	public void testSeleccionarHorasMecanicoTemporal() {
		HorasEmpleados p =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(p);
		when(cliente.seleccionarHorsEmpleadoTemporal(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.seleccionarHorasMecanicoTemporal(("Jorge")).getNickname().equals(p.getNickname()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(cliente.seleccionarHorsEmpleadoTemporal(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.seleccionarHorasMecanicoTemporal("PE-1") == null);
	}
	
	@Test 
	public void testDeleteHorasEmpleados() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(cliente.horasEmpleadosDelete(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.deleteHorasEmpleados("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(cliente.horasEmpleadosDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.deleteHorasEmpleados("nickname") == false);
	}
	
	@Test 
	public void testDeleteHorasEmpleadosTemporal() {
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(cliente.horasEmpleadosTemporalDelete(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.deleteHorasEmpleadosTemporal("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(cliente.horasEmpleadosTemporalDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.deleteHorasEmpleadosTemporal("nickname") == false);
	}
	
	@Test
	public void testRegistrarHorasMecanico() {
		HorasEmpleados horas =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registroHorasEmpleado(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.registrarHorasMecanico("") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registroHorasEmpleado(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.registrarHorasMecanico("") == false);
	}
	
	@Test
	public void testRegistrarHorasMecanicoTemporal() {
		HorasEmpleados horas =  new HorasEmpleados(10, 10, "Jorge");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registroHorasEmpleadoTemporal(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.registrarHorasMecanicoTemporal("") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registroHorasEmpleadoTemporal(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.registrarHorasMecanicoTemporal("") == false);
	}
	
	@Test
	public void testRegistroMecanico() {
		Mecanico mecanico = new Mecanico("nickname", "contrasenia", 0, "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(cliente.registroMecanico(any(Mecanico.class))).thenReturn(response);
		
		assertTrue(mecanicoController.registroMecanico(mecanico) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(cliente.registroMecanico(any(Mecanico.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.registroMecanico(mecanico) == false);
	}
	
	@Test
	public void testDeleteMecanico() {
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		
		when(cliente.mecanicoDelete(any(String.class))).thenReturn(response);
		
		assertTrue(mecanicoController.deleteMecanico("nickname") == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		
		when(cliente.mecanicoDelete(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.deleteMecanico("nickname") == false);
	}
	
	@Test
	public void testSeleccionarMecanico() {
		Mecanico mecanico = new Mecanico("nickname", "contrasenia", 0, "12345678A", "Jorge", "Gonzalez", "Hombre", "jorge@gmail.com", "Bilbao", 48007, "direccion", "nSS", "numeroCuenta", 1500, "numeroTelefono", 1);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(mecanico);
		when(cliente.mecanicoSelect(any(String.class))).thenReturn(response);
		
		Mecanico mecanicoSeleccionado = mecanicoController.seleccionarMecanico("nickname");
		assertTrue(mecanicoSeleccionado.getNickname().equals("nickname"));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(cliente.mecanicoSelect(any(String.class))).thenReturn(response1);
		
		assertTrue(mecanicoController.seleccionarMecanico("nickname") == null);
	}
}
	

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
import concesionario.datos.Cliente;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;

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
}
	

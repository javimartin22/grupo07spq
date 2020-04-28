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
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Proveedor;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class DepartamentoComprasControllerTest {
	public DepartmentoComprasController departamentoComprasController;
	
	@Mock
	ClienteApp clienteApp;
	
	@Before
	public void setUp() {
		departamentoComprasController = new DepartmentoComprasController(clienteApp);
		clienteApp = departamentoComprasController.getClienteApp();
	}
	
	@Test
	public void testRegistroPieza() {
		Pieza pieza = new Pieza("P1", "Amortiguador", 2, "Almacen 1");
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		when(clienteApp.registroPieza(any(Pieza.class))).thenReturn(response);
		assertTrue(departamentoComprasController.registroPieza(pieza) == true);
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		when(clienteApp.registroPieza(any(Pieza.class))).thenReturn(response1);
		assertTrue(departamentoComprasController.registroPieza(pieza) == false);
	}
	
	@Test
	public void testCargarPiezas() {
		List<Pieza> piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
		Pieza p2 = new Pieza("P2", "Correa", 7, "Almacen 2");
		piezas.add(p1);
		piezas.add(p2);
		
		
		Mockito.when(clienteApp.cargarTablaPiezas()).thenAnswer(x ->piezas);
		List<Pieza> piezas_result = departamentoComprasController.cargarPiezas();
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
	}
	
	@Test
	public void testSeleccionarPiezaUtilizada() {
		Pieza pieza = new Pieza("P1", "Amortiguador", 2, "Almacen 1");
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(pieza);
		
		when(clienteApp.piezaUtilizadaSelect(any(String.class))).thenReturn(response);
		
		assertTrue(departamentoComprasController.seleccionarPiezaUtilizada("P1").getCodigo().equals(pieza.getCodigo()));
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		
		when(clienteApp.piezaUtilizadaSelect(any(String.class))).thenReturn(response1);
		
		assertTrue(departamentoComprasController.seleccionarPiezaUtilizada("P1") == null);
	}
	
	@Test
	public void testCargarPiezasUtilizadas() {
		List<Pieza> piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
		Pieza p2 = new Pieza("P2", "Correa", 7, "Almacen 2");
		piezas.add(p1);
		piezas.add(p2);
		
		
		Mockito.when(clienteApp.cargarTablaPiezasUtilizadas()).thenAnswer(x ->piezas);
		List<Pieza> piezas_result = departamentoComprasController.cargarPiezasUtilizadas();
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
	}
	
	@Test
	public void testFiltrarPiezaUtilizada() {
		List<Pieza> piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza("P1", "Amortiguador", 5, "Almacen 1");
		Pieza p2 = new Pieza("P2", "Correa", 7, "Almacen 2");
		piezas.add(p1);
		piezas.add(p2);
		
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->piezas);
		when(clienteApp.filtrarPiezaUtilizadas(any(String.class))).thenReturn(response);
		
		List<Pieza> piezas_result = departamentoComprasController.filtrarPiezaUtilizadas("filtro");
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezas_result.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
		
		Response response1 = Mockito.mock(Response.class);
		Mockito.when(response1.getStatus()).thenReturn(404);
		Mockito.when(response1.readEntity(Mockito.any(Class.class))).thenReturn(null);
		when(clienteApp.filtrarPiezaUtilizadas(any(String.class))).thenReturn(response1);
		
		assertTrue(departamentoComprasController.filtrarPiezaUtilizadas("P1") == null);
	}
	
	@Test
	public void testParseUbicacion() {
		assertEquals("Alamacen 1 - Estanteria 1", departamentoComprasController.parseUbicacion(0));
		assertEquals("Alamacen 1 - Estanteria 2", departamentoComprasController.parseUbicacion(1));
		assertEquals("Alamacen 1 - Estanteria 3", departamentoComprasController.parseUbicacion(2));
		assertEquals("Alamacen 2 - Estanteria 1", departamentoComprasController.parseUbicacion(3));
		assertEquals("Alamacen 2 - Estanteria 2", departamentoComprasController.parseUbicacion(4));
		assertEquals("Alamacen 2 - Estanteria 3", departamentoComprasController.parseUbicacion(5));
	}
	
	@Test 
	public void testCargarListaProveedores() {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Proveedor p1 = new Proveedor("idProveedor1", "nombre1", "pais1", "tipoPiezas1");
		Proveedor p2 = new Proveedor("idProveedor2", "nombre2", "pais2", "tipoPiezas2");
		proveedores.add(p1);
		proveedores.add(p2);
		
		
		Mockito.when(clienteApp.cargarListaProveedores()).thenAnswer(x ->proveedores);
		List<Proveedor> proveedoresSelect = departamentoComprasController.cargarListaProveedores();
		
		for(int i=0; i<proveedores.size(); i++) {
			assertTrue(proveedoresSelect.get(i).getNombre().equals(proveedores.get(i).getNombre()));
		}
	}
	
	@Test 
	public void testCargarListaPiezasProveedores() {
		List<PiezaProveedores> piezas = new ArrayList<PiezaProveedores>();
		PiezaProveedores p1 = new PiezaProveedores("idProveedor1", "nombre1", 5, "tipoPiezas1", "Codigo1");
		PiezaProveedores p2 = new PiezaProveedores("idProveedor2", "nombre2", 5, "tipoPiezas2", "Codigo2");
		piezas.add(p1);
		piezas.add(p2);
		
		
		Mockito.when(clienteApp.cargarListaPiezasProveedores()).thenAnswer(x ->piezas);
		List<PiezaProveedores> piezasSelect = departamentoComprasController.cargarListaPiezasProveedores();
		
		for(int i=0; i<piezas.size(); i++) {
			assertTrue(piezasSelect.get(i).getNombre().equals(piezas.get(i).getNombre()));
		}
	}
	
}

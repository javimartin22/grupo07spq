package concesionario.cliente.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Mecanico;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Pieza;
import concesionario.datos.Usuario;
import concesionario.datos.Presupuesto;

@RunWith(MockitoJUnitRunner.class)
public class MecanicoControllerTest {
	
	public MecanicoController mecanicoController;
	
	@Mock
	ClienteApp cliente;
	
	@Before
	public void setUp() {
		mecanicoController = new MecanicoController(cliente);
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
			
	}
	
	}
	

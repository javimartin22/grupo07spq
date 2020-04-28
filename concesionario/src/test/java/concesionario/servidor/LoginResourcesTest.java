package concesionario.servidor;

import static org.junit.Assert.*;


import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import concesionario.datos.Cliente;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.servidor.BaseDatos.BD;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;




@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.", "com.sun.org.apache.xerces.", "javax.xml.", "org.xml.", "org.w3c.dom.",
"com.sun.org.apache.xalan.", "javax.activation.*"})
@PrepareForTest(BD.class)
public class LoginResourcesTest {
	
	LoginResources loginResources;
	Statement st;
	
	@Before
	public void setUp() {
//		loginResources = new LoginResources();
//        HttpServer server = Main.startServer();
		 MockitoAnnotations.initMocks(this);
		 loginResources = new LoginResources();
		 st = loginResources.getSt();
	}
	
	@Test
	public void testAnadirUsuario() {
		try {
			
			PowerMockito.mockStatic(BD.class);
			Usuario usu = new Usuario("Prueba","test",0);
			
			when(BD.usuarioSelect(st, usu.getNickname())).thenReturn(usu);
			
			Response r= loginResources.anadirUsuario(usu);
		        
			assertEquals(200, r.getStatus());
			
			Usuario usu_null = null;
			when(BD.usuarioSelect(st, usu.getNickname())).thenReturn(usu_null);
			
			Response r2= loginResources.anadirUsuario(usu);
			assertEquals(404, r2.getStatus()); 
			
			Usuario usu3 = new Usuario("pr","t",0);
			when(BD.usuarioSelect(st, usu.getNickname())).thenReturn(usu3);
			loginResources.anadirUsuario(usu);
			
			Response r3= loginResources.anadirUsuario(usu);
			assertEquals(406, r3.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	@Test
	public void testSetSt() {
		Connection con =BD.initBD("Taller");
		Statement ssst = BD.usarCrearTablasBD(con);
		
		loginResources.setSt(ssst);
		assertFalse(loginResources.getSt() == null);
	}
	
	@Test
	public void testGetSt() {
		Connection con =BD.initBD("Taller");
		Statement ssst = BD.usarCrearTablasBD(con);
		
		loginResources.setSt(ssst);
		assertFalse(loginResources.getSt() == null);
	}
	
	@Test
	public void testDeleteCliente() {
		try {
			
			PowerMockito.mockStatic(BD.class);
			Cliente cliente =  new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
			
			boolean b = true;
			when(BD.clientesDelete(st, cliente.getDNI())).thenReturn(b);
			when(BD.usuariosDelete(st, cliente.getNickname())).thenReturn(b);
			when(BD.clienteSelect(st, cliente.getNickname())).thenReturn(cliente);
			
			
			Response r= loginResources.deleteCliente(cliente);
		        
			assertEquals(404, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
			
			Cliente cnull = null;
			when(BD.clientesDelete(st, cliente.getDNI())).thenReturn(b);
			when(BD.usuariosDelete(st, cliente.getNickname())).thenReturn(b);
			when(BD.clienteSelect(st, cliente.getNickname())).thenReturn(cnull);
			
			Response r2= loginResources.deleteCliente(cliente);
			assertEquals(200, r2.getStatus());   //AL REVES POR DELETE
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	@Test
	public void testSelectComercial() {
		try {
			
			PowerMockito.mockStatic(BD.class);
			Comercial comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
			
			when(BD.ComercialSelect(st, comercial.getNickname())).thenReturn(comercial);
			
			Response r= loginResources.selectComercial(comercial.getNickname());
		        
			assertEquals(200, r.getStatus());
			
			Comercial com_null = null;
			when(BD.ComercialSelect(st, comercial.getNickname())).thenReturn(com_null);
			
			Response r2= loginResources.selectComercial(comercial.getNickname());
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	@Test
	public void testRegistrarCocheTaller() {
		try {
			
			PowerMockito.mockStatic(BD.class);
			CocheTaller cocheTaller = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
			boolean b = true;
			when(BD.cocheTallerInsert(st, cocheTaller.getMatricula(), cocheTaller.getMarca(), cocheTaller.getModelo(), cocheTaller.getMecanico(),cocheTaller.getDniCliente(),cocheTaller.getCoste(), cocheTaller.getEstado())).thenReturn(b);
			
			Response r= loginResources.registrarCocheTaller(cocheTaller);
		        
			assertEquals(200, r.getStatus());
			
			boolean f = false;
			when(BD.cocheTallerInsert(st, cocheTaller.getMatricula(), cocheTaller.getMarca(), cocheTaller.getModelo(), cocheTaller.getMecanico(),cocheTaller.getDniCliente(),cocheTaller.getCoste(), cocheTaller.getEstado())).thenReturn(f);
			
			Response r2= loginResources.registrarCocheTaller(cocheTaller);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

	@Test
	public void testCargarTablaTarifas() {
		try {
			PowerMockito.mockStatic(BD.class);
			
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
//			List<Tarifa> tarifas= loginResources.cargarTablaTarifas();
//			System.out.println(tarifas);
//		    assertTrue(tarifas.size() > 0);
			
		    
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifasTodosSelect(st)).thenReturn(res);
			
			List<Tarifa> resultado= loginResources.cargarTablaTarifas();
			
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}

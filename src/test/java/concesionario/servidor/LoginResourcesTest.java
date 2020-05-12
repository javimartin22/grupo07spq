package concesionario.servidor;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
import concesionario.datos.HorasEmpleados;
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
import concesionario.servidor.BaseDatos.BD;

import org.junit.Test;
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
	public void testRegistrarClientete() {
		try {
			PowerMockito.mockStatic(BD.class);
			Cliente client = new Cliente("dni", "nickname", 3, "contrasenya", "nombre", "apellido", "sexo", "email", "ciudad", 12345, "direccion", "numeroTelefono");
			when(BD.clienteSelect(st, "nickname")).thenReturn(client);
			Response r= loginResources.registrarCliente(client);
			assertEquals(200, r.getStatus());
			
			Cliente cliente_null = null;
			when(BD.clienteSelect(st, client.getNickname())).thenReturn(cliente_null);
			Response r2 = loginResources.registrarCliente(client);
			assertEquals(404, r2.getStatus()); 
			
			Cliente clientePrueba = new Cliente("dn1i", "nickname1", 3, "contrasenya1", "nombre1", "apellido1", "sexo1", "email1", "ciudad1", 12345, "direccio1n", "numero1Telefono");
			when(BD.clienteSelect(st, client.getNickname())).thenReturn(clientePrueba);
			loginResources.registrarCliente(client);
			Response r3= loginResources.registrarCliente(client);
			assertEquals(200, r3.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarComercial() {
		try {
			PowerMockito.mockStatic(BD.class);
			Comercial comercial = new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
			Usuario usuario = new Usuario(comercial.getNickname(), comercial.getContrasenia(), 2);
			  
	        //BD.comercialesInsert(st, comercial.getDNI(), comercial.getNickname(), comercial.getNickname(), comercial.getNombre(), comercial.getApellido(), comercial.getSexo(), comercial.getEmail(), comercial.getCiudad(), comercial.getCodigoPostal(), comercial.getDireccion(), comercial.getNumeroTelefono(),comercial.getNSS(), comercial.getNumeroCuenta(), comercial.getSueldo(), comercial.getHoras(), comercial.getCochesVendidos(), comercial.getImporteObetenido());
	        //BD.empleadosInsert(st, comercial.getDNI(), comercial.getNickname(), comercial.getContrasenia(), comercial.getNombre(), comercial.getApellido(), comercial.getSexo(), comercial.getEmail(), comercial.getCiudad(), comercial.getCodigoPostal(), comercial.getDireccion(), comercial.getNumeroTelefono(), comercial.getNSS(), comercial.getNumeroCuenta(), 1200, 0);
	        //BD.usuariosInsert(st, comercial.getNickname(), comercial.getContrasenia(), 2);
			when(BD.usuarioSelect(st, comercial.getNickname())).thenReturn(usuario);
			Response r= loginResources.registrarComercial(comercial);
			assertEquals(200, r.getStatus());
			
			Usuario comercial_null = null;
			when(BD.usuarioSelect(st, comercial.getNickname())).thenReturn(comercial_null);
			Response r2 = loginResources.registrarComercial(comercial);
			assertEquals(404, r2.getStatus());  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarDepartamentoCompras() {
		try {
			PowerMockito.mockStatic(BD.class);
			DepartamentoCompras depcompras = new DepartamentoCompras("Jorge", "12345", "12345667Ñ", "Jorge", "Martinez", "Masculino", "em@gmail.com", "Barakaldo", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
			Usuario usuario = new Usuario(depcompras.getNickname(), depcompras.getContrasenia(), 4);
			
	        //BD.comercialesInsert(st, comercial.getDNI(), comercial.getNickname(), comercial.getNickname(), comercial.getNombre(), comercial.getApellido(), comercial.getSexo(), comercial.getEmail(), comercial.getCiudad(), comercial.getCodigoPostal(), comercial.getDireccion(), comercial.getNumeroTelefono(),comercial.getNSS(), comercial.getNumeroCuenta(), comercial.getSueldo(), comercial.getHoras(), comercial.getCochesVendidos(), comercial.getImporteObetenido());
	        //BD.empleadosInsert(st, comercial.getDNI(), comercial.getNickname(), comercial.getContrasenia(), comercial.getNombre(), comercial.getApellido(), comercial.getSexo(), comercial.getEmail(), comercial.getCiudad(), comercial.getCodigoPostal(), comercial.getDireccion(), comercial.getNumeroTelefono(), comercial.getNSS(), comercial.getNumeroCuenta(), 1200, 0);
	        //BD.usuariosInsert(st, comercial.getNickname(), comercial.getContrasenia(), 2);
			
			when(BD.usuarioSelect(st, depcompras.getNickname())).thenReturn(usuario);
			Response r= loginResources.registrarDepartamentoCompras(depcompras);
			assertEquals(200, r.getStatus());
			
			Usuario depcompras_null = null;
			when(BD.usuarioSelect(st, depcompras.getNickname())).thenReturn(depcompras_null);
			Response r2 = loginResources.registrarDepartamentoCompras(depcompras);
			assertEquals(404, r2.getStatus());  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRegistrarMecanico() {
		try {
			PowerMockito.mockStatic(BD.class);
			Mecanico client = new Mecanico("user", "pass", 1, "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
			Usuario usuario = new Usuario(client.getNickname(), client.getContrasenia(), 1);
			  
	        BD.mecanicosInsert(st, client.getDNI(), client.getNickname(), client.getContrasenia(), client.getNombre(), client.getApellido(), client.getSexo(), client.getEmail(), client.getCiudad(), client.getCodigoPostal(), client.getDireccion(), client.getNumeroTelefono(), client.getNSS(), client.getNumeroCuenta(), 1200, client.getHoras());
	        BD.empleadosInsert(st, client.getDNI(), client.getNickname(), client.getContrasenia(), client.getNombre(), client.getApellido(), client.getSexo(), client.getEmail(), client.getCiudad(), client.getCodigoPostal(), client.getDireccion(), client.getNumeroTelefono(), client.getNSS(), client.getNumeroCuenta(), 1200, 0);
	        BD.usuariosInsert(st, client.getNickname(), client.getContrasenia(), 1);
			when(BD.usuarioSelect(st, client.getNickname())).thenReturn(usuario);
			Response r= loginResources.registrarMecanico(client);
			assertEquals(200, r.getStatus());
			
			Usuario cliente_null = null;
			when(BD.usuarioSelect(st, client.getNickname())).thenReturn(cliente_null);
			Response r2 = loginResources.registrarMecanico(client);
			assertEquals(404, r2.getStatus());  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testInsertarHerramientas() {
		try {
			PowerMockito.mockStatic(BD.class);
			HerramientasTaller h1 = new HerramientasTaller("", "", 3, "");		
			when(BD.herramientasTallerInsert(st, h1.getCodigo(), h1.getNombre(), h1.getUnidades(), h1.getUbicacion())).thenReturn(true);
			Response r= loginResources.insertarHerramientas(h1);
			assertEquals(200, r.getStatus());
		
			when(BD.herramientasTallerInsert(st, h1.getCodigo(), h1.getNombre(), h1.getUnidades(), h1.getUbicacion())).thenReturn(false);
			Response r2= loginResources.insertarHerramientas(h1);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	@Test
	public void testRegistroSolicitud() {
		try {

			try {
				PowerMockito.mockStatic(BD.class);
				SolicitudCompra solicitud =  new SolicitudCompra("","","",3);
				when(BD.solicitudInsert(st, solicitud.getCodigo(), solicitud.getNombre(), solicitud.getTipo(), solicitud.getUnidades())).thenReturn(true);
				Response r= loginResources.registroSolicitud(solicitud);
				assertEquals(200, r.getStatus());
			
				when(BD.solicitudInsert(st, solicitud.getCodigo(), solicitud.getNombre(), solicitud.getTipo(), solicitud.getUnidades())).thenReturn(false);
				Response r2= loginResources.registroSolicitud(solicitud);
				assertEquals(404, r2.getStatus()); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testRegistrarPiezaUtilizada() {
		try {
			PowerMockito.mockStatic(BD.class);
			Pieza piezas = new Pieza("P1", "Amortiguador", 12, "Almacen 1");
			BD.piezasUtilizadasInsert(st,piezas.getCodigo(), piezas.getNombre(), piezas.getUnidades(),piezas.getUbicacion());
			when(BD.piezaUtilizadaSelect(st, piezas.getCodigo())).thenReturn(piezas);
			Response r= loginResources.registrarPiezaUtilizada(piezas);
			assertEquals(200, r.getStatus());
		
			Pieza com_null = null;
			when(BD.piezaUtilizadaSelect(st, piezas.getCodigo())).thenReturn(com_null);
			Response r2= loginResources.registrarPiezaUtilizada(piezas);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistroPresupuesto() {
		try {
			PowerMockito.mockStatic(BD.class);
			Presupuesto presup = new Presupuesto("PE-1", "12345678A", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "22-1-2020");
			when(BD.PresupuestoInsert(st, presup.getCodigo(), presup.getDniCliente(), presup.getMecanico(), presup.getMarca(), presup.getModelo(), presup.getProblema(), presup.getNumPiezas(), presup.getListaPiezas(), presup.getObservaciones(), presup.getPrecio(), presup.getFecha())).thenReturn(true);
			Response r= loginResources.registroPresupuesto(presup);
			assertEquals(200, r.getStatus());
		
			when(BD.PresupuestoInsert(st, presup.getCodigo(), presup.getDniCliente(), presup.getMecanico(), presup.getMarca(), presup.getModelo(), presup.getProblema(), presup.getNumPiezas(), presup.getListaPiezas(), presup.getObservaciones(), presup.getPrecio(), presup.getFecha())).thenReturn(false);
			Response r2= loginResources.registroPresupuesto(presup);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarVenta() {
		try {
			PowerMockito.mockStatic(BD.class);
			Venta venta = new Venta("22-1-2020", "Leon", "Seat", "9600JPT", "Jorgico", "Alberto");
			when(BD.ventaSelect(st, venta.getMatricula())).thenReturn(venta);
			Response r= loginResources.registrarVenta(venta);
			assertEquals(200, r.getStatus());
		
			Venta venta_null = null;
			when(BD.ventaSelect(st, venta.getMatricula())).thenReturn(venta_null);
			Response r2= loginResources.registrarVenta(venta);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRegistrarTarifa() {
		try {
			PowerMockito.mockStatic(BD.class);
			Tarifa tarifa = new Tarifa("T1", "Cambio Aceite", 50, 1);
			when(BD.tarifaIdSelect(st, tarifa.getIdTarifa())).thenReturn(tarifa);
			Response r= loginResources.registrarTarifa(tarifa);
			assertEquals(200, r.getStatus());
		
			Tarifa tarifa_null = null;
			when(BD.tarifaIdSelect(st, tarifa.getIdTarifa())).thenReturn(tarifa_null);
			Response r2= loginResources.registrarTarifa(tarifa);
			assertEquals(404, r2.getStatus()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRegistrarHorasEmpleado() {
		try {
			PowerMockito.mockStatic(BD.class);
			String horasempleado = "Ander-5-30";
			when(BD.HorasEmpleadoInsert(st, "Ander", 5, 30)).thenReturn(true);
			Response r= loginResources.registrarHorasEmpleado(horasempleado);
			assertEquals(200, r.getStatus());
		
			when(BD.HorasEmpleadoInsert(st, "Ander", 5, 30)).thenReturn(false);
			Response r1= loginResources.registrarHorasEmpleado(horasempleado);
			assertEquals(404, r1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarHorasEmpleadoTemporal() {
		try {
			PowerMockito.mockStatic(BD.class);
			String horasempleado = "Ander-5-30";
			when(BD.HorasEmpleadoTemporalInsert(st, "Ander", 5, 30)).thenReturn(true);
			Response r= loginResources.registrarHorasEmpleadoTemporal(horasempleado);
			assertEquals(200, r.getStatus());
		
			when(BD.HorasEmpleadoTemporalInsert(st, "Ander", 5, 30)).thenReturn(false);
			Response r1= loginResources.registrarHorasEmpleadoTemporal(horasempleado);
			assertEquals(404, r1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarCitaComercial() {
		try {
			PowerMockito.mockStatic(BD.class);
			CitaComercial citacomercial = new CitaComercial("Mikel", "12332112A", "10/10/20", "10:30", "Unai");
			when(BD.CitaComercialInsert(st, citacomercial.getNombre(), citacomercial.getDniCliente(), citacomercial.getFecha(), citacomercial.getHora(), citacomercial.getComercial())).thenReturn(true);
			Response r= loginResources.registrarCitaComercial(citacomercial);
			assertEquals(200, r.getStatus());
		
			when(BD.CitaComercialInsert(st, citacomercial.getNombre(), citacomercial.getDniCliente(), citacomercial.getFecha(), citacomercial.getHora(), citacomercial.getComercial())).thenReturn(false);
			Response r1= loginResources.registrarCitaComercial(citacomercial);
			assertEquals(404, r1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarCitaComercialT() {
		try {
			PowerMockito.mockStatic(BD.class);
			CitaTaller citataller = new CitaTaller("Nombre", "12345678K", "01-01-2000", "13", "Amin", "Problema");
			when(BD.CitaTallerInsert(st, citataller.getNombre(), citataller.getDniCliente(), citataller.getFecha(), citataller.getHora(), citataller.getComercial(), citataller.getProblema())).thenReturn(true);
			Response r= loginResources.registrarCitaComercial(citataller);
			assertEquals(200, r.getStatus());
		
			when(BD.CitaTallerInsert(st, citataller.getNombre(), citataller.getDniCliente(), citataller.getFecha(), citataller.getHora(), citataller.getComercial(), citataller.getProblema())).thenReturn(false);
			Response r1= loginResources.registrarCitaComercial(citataller);
			assertEquals(404, r1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarCocheConcesionario() {
		try {
			PowerMockito.mockStatic(BD.class);
			CocheConcesionario usu = new CocheConcesionario("marca", "modelo", 1, 1, 1, "Color", 1);
			when(BD.cocheConcesionarioSelect(st, usu.getModelo())).thenReturn(usu);
			Response r= loginResources.registrarCocheConcesionario(usu);
			assertEquals(200, r.getStatus());
			
			CocheConcesionario usu_null = null;
			when(BD.cocheConcesionarioSelect(st, usu.getModelo())).thenReturn(usu_null);
			Response r2= loginResources.registrarCocheConcesionario(usu);
			assertEquals(404, r2.getStatus()); 			
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
	public void testSelectMecanico() {
		try {
			PowerMockito.mockStatic(BD.class);
			Mecanico mecanico = new Mecanico("dni", "nickname", 3, "contrasenya", "nombre", "apellido", "sexo", "email", "ciudad", 12345, "direccion", "", "", 1, "", 1);
			when(BD.mecanicoSelect(st, mecanico.getNickname())).thenReturn(mecanico);
			Response r= loginResources.selectMecanico(mecanico.getNickname());
			assertEquals(200, r.getStatus());
			
			Mecanico com_null = null;
			when(BD.mecanicoSelect(st, mecanico.getNickname())).thenReturn(com_null);
			Response r2= loginResources.selectMecanico(mecanico.getNickname());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectClient() {
		try {
			PowerMockito.mockStatic(BD.class);
			Cliente cliente = new Cliente("12345678K", "user", 1, "pass", "javi", "martin", "masculino", "javi@gmail.com", "bilbo", 46009, "kalea", "666777872");
			when(BD.clienteSelect(st, cliente.getNickname())).thenReturn(cliente);
			Response r= loginResources.selectCliente(cliente.getNickname());
			assertEquals(200, r.getStatus());
			
			Cliente com_null = null;
			when(BD.clienteSelect(st, cliente.getNickname())).thenReturn(com_null);
			Response r2= loginResources.selectCliente(cliente.getNickname());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectCocheTaller() {
		try {
			PowerMockito.mockStatic(BD.class);
			CocheTaller cocheTaller = new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
			when(BD.cocheTalleSelect(st, cocheTaller.getMatricula())).thenReturn(cocheTaller);
			Response r= loginResources.selectCocheTaller(cocheTaller.getMatricula());
			assertEquals(200, r.getStatus());
			
			CocheTaller com_null = null;
			when(BD.cocheTalleSelect(st, cocheTaller.getMatricula())).thenReturn(com_null);
			Response r2= loginResources.selectCocheTaller(cocheTaller.getMatricula());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectCocheConcesionario() {
		try {
			PowerMockito.mockStatic(BD.class);
			CocheConcesionario cocheTaller = new CocheConcesionario("Honda", "Civic", 10000, 115, 5, "Rojo", 1);
			when(BD.cocheConcesionarioSelect(st, cocheTaller.getModelo())).thenReturn(cocheTaller);
			Response r= loginResources.selectCocheConcesionario(cocheTaller.getModelo());
			assertEquals(200, r.getStatus());
			
			CocheConcesionario com_null = null;
			when(BD.cocheConcesionarioSelect(st, cocheTaller.getModelo())).thenReturn(com_null);
			Response r2= loginResources.selectCocheConcesionario(cocheTaller.getModelo());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectDepartamentoCompras() {
		try {
			PowerMockito.mockStatic(BD.class);
			DepartamentoCompras cocheTaller = new DepartamentoCompras("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0);
			when(BD.departamentoCompraSelect(st, cocheTaller.getNickname())).thenReturn(cocheTaller);
			Response r= loginResources.selectDepartamentoCompras(cocheTaller.getNickname());
			assertEquals(200, r.getStatus());
			
			DepartamentoCompras com_null = null;
			when(BD.departamentoCompraSelect(st, cocheTaller.getNickname())).thenReturn(com_null);
			Response r2= loginResources.selectDepartamentoCompras(cocheTaller.getNickname());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectHorasEmpledoTemporalSelect() {
		try {
			PowerMockito.mockStatic(BD.class);
			HorasEmpleados horasemp = new HorasEmpleados(10, 20, "Nick");
			when(BD.horaEmpleadoTemporalSelect(st, "Nick")).thenReturn(horasemp);
			Response r= loginResources.selectHorasEmpledoTemporalSelect("Nick");
			assertEquals(200, r.getStatus());
			
			PowerMockito.mockStatic(BD.class);
			HorasEmpleados horasemp_null = null;
			when(BD.horaEmpleadoTemporalSelect(st, horasemp.getNickname())).thenReturn(horasemp_null);
			Response r2= loginResources.selectHorasEmpledoTemporalSelect("Nick");
			assertEquals(404, r2.getStatus());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testSelectHorasEmpledoSelect() {
		try {
			PowerMockito.mockStatic(BD.class);
			HorasEmpleados horasemp = new HorasEmpleados(10, 20, "Nick");
			when(BD.horaEmpleadoSelect(st, "Nick")).thenReturn(horasemp);
			Response r= loginResources.selectHorasEmpledoSelect("Nick");
			assertEquals(200, r.getStatus());
			
			PowerMockito.mockStatic(BD.class);
			HorasEmpleados horasemp_null = null;
			when(BD.horaEmpleadoSelect(st, horasemp.getNickname())).thenReturn(horasemp_null);
			Response r2= loginResources.selectHorasEmpledoSelect("Nick");
			assertEquals(404, r2.getStatus());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCitaTallerSelect() {
		try {
			PowerMockito.mockStatic(BD.class);
			String restriccion = "11-02-20;23:30;Andoni";
			CitaTaller citataller = new CitaTaller("Joseba1", "12345677K", "11-03-20","2:10", "Jorge", "problema");
			when(BD.citaTallerSelect(st,"11-02-20","23:30","Andoni")).thenReturn(citataller);
			Response r= loginResources.citaTallerSelect(restriccion);
			assertEquals(200, r.getStatus());
			
			CitaTaller citataller_null = null;
			when(BD.citaTallerSelect(st,"11-02-20","23:30","Andoni")).thenReturn(citataller_null);
			Response r2= loginResources.citaTallerSelect(restriccion);
			assertEquals(404, r2.getStatus());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitaComercialSelect() {
		try {
			PowerMockito.mockStatic(BD.class);
			String restriccion = "11-02-20;23:30;Andoni";
			CitaComercial citacomercial = new CitaComercial("name","12345677O", "22-10-20", "10:00", "Jacobo");
			when(BD.citaComercialSelect(st,"11-02-20","23:30","Andoni")).thenReturn(citacomercial);
			Response r= loginResources.citaComercialSelect(restriccion);
			assertEquals(200, r.getStatus());
			
			CitaComercial citacomercial_null = null;
			when(BD.citaComercialSelect(st,"11-02-20","23:30","Andoni")).thenReturn(citacomercial_null);
			Response r2= loginResources.citaComercialSelect(restriccion);
			assertEquals(404, r2.getStatus());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectPiezaUtilizada() {
		try {
			PowerMockito.mockStatic(BD.class);
			Pieza cocheTaller = new Pieza("P1", "Amortiguador", 12, "Almacen 1");
			when(BD.piezaUtilizadaSelect(st, cocheTaller.getCodigo())).thenReturn(cocheTaller);
			Response r= loginResources.selectPiezaUtilizada(cocheTaller.getCodigo());
			assertEquals(200, r.getStatus());
			
			Pieza com_null = null;
			when(BD.piezaUtilizadaSelect(st, cocheTaller.getCodigo())).thenReturn(com_null);
			Response r2= loginResources.selectPiezaUtilizada(cocheTaller.getCodigo());
			assertEquals(404, r2.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	@Test
	public void testRegistrarPieza() {
	try {
		PowerMockito.mockStatic(BD.class);
		Pieza pieza = new Pieza("P1", "Amortiguador", 12, "Almacen 1");		
		when(BD.piezasInsert(st, pieza.getCodigo(), pieza.getNombre(), pieza.getUnidades(), pieza.getUbicacion())).thenReturn(true);
		Response r= loginResources.registrarPieza(pieza);
		assertEquals(200, r.getStatus());
	
		when(BD.piezasInsert(st, pieza.getCodigo(), pieza.getNombre(), pieza.getUnidades(), pieza.getUbicacion())).thenReturn(false);
		Response r2= loginResources.registrarPieza(pieza);
		assertEquals(404, r2.getStatus()); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	@Test
	public void testSelectPresupuesto() {
		try {
			PowerMockito.mockStatic(BD.class);
			Presupuesto presupuesto = new Presupuesto("PE1", "12345678D", "Jorge", "Seat", "Leon", "Aceite", 1, "Lata Aceite", "Cambio de Aceite", 50, "Hoy");
			when(BD.presupuestoCodigoSelect(st, presupuesto.getCodigo())).thenReturn(presupuesto);
			Response r= loginResources.selectPresupuesto(presupuesto.getCodigo());
			assertEquals(200, r.getStatus());
			
			Presupuesto com_null = null;
			when(BD.presupuestoCodigoSelect(st, presupuesto.getCodigo())).thenReturn(com_null);
			Response r2= loginResources.selectPresupuesto(presupuesto.getCodigo());
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
	public void testDeletePiezaUtilizada() {
		try {
			PowerMockito.mockStatic(BD.class);
			Pieza pieza = new Pieza("P1", "Amortiguador", 12, "Almacen 1");
			
			boolean b = true;
			when(BD.piezaUtilizadaDelete(st, pieza.getCodigo())).thenReturn(b);
			Response r= loginResources.deletePiezaUtilizada(pieza.getCodigo());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
			
		
			when(BD.piezaUtilizadaDelete(st, pieza.getCodigo())).thenReturn(b);
			when(BD.piezaSelect(st, pieza.getCodigo())).thenReturn(pieza);
			Response r2= loginResources.deletePiezaUtilizada(pieza.getCodigo());
			assertEquals(404, r2.getStatus()); 
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteDepartamentoCompras() {
		try {
			PowerMockito.mockStatic(BD.class);
			DepartamentoCompras dep =  new DepartamentoCompras("Jorge", "12345", "12345667Ñ", "Jorge", "Martinez", "Masculino", "em@gmail.com", "Barakaldo", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
			
			boolean b = true;
			when(BD.departamentoComprasDelete(st, dep.getDNI())).thenReturn(b);
			Response r= loginResources.deleteDepartamentoCompras(dep.getDNI());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteComercial() {
		try {
			PowerMockito.mockStatic(BD.class);
			Comercial comercial =  new Comercial("user", "pass", "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 0, 2, 300, 10 );
			
			boolean b = true;
			when(BD.comercialesDelete(st, comercial.getDNI())).thenReturn(b);
			Response r= loginResources.deleteComercial(comercial.getDNI());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCitasComercial() {
		try {
			ResultSet rs_null = null;
			PowerMockito.mockStatic(BD.class);
			
			String nickfecha = "Pablo;22-05-2020";
			when(BD.filtrarCitasPorFecha(st, "Pablo", "22-05-2020")).thenReturn(rs_null);
			Response r = loginResources.deleteCitasComercial(nickfecha);
			assertEquals(200, r.getStatus());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCitasMecanico() {
		try {
			ResultSet rs_null = null;
			PowerMockito.mockStatic(BD.class);
			
			String nickfecha = "Pablo;22-05-2020";
			when(BD.filtrarCitasMecanicoPorFecha(st, "Pablo", "22-05-2020")).thenReturn(rs_null);
			Response r = loginResources.deleteCitasMecanico(nickfecha);
			assertEquals(200, r.getStatus());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteHorasEmpleados() {
		try {
			HorasEmpleados horas_null = null;
			HorasEmpleados horas_emp = new HorasEmpleados(10, 10, "Javi");
			PowerMockito.mockStatic(BD.class);
			
			when(BD.horaEmpleadoSelect(st, "Javi")).thenReturn(horas_null);
			Response r = loginResources.deleteHorasEmpleados("Javi");
			assertEquals(200, r.getStatus());
			
			when(BD.horaEmpleadoSelect(st, "Javi")).thenReturn(horas_emp);
			Response r2 = loginResources.deleteHorasEmpleados("Javi");
			assertEquals(404, r2.getStatus());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testDeleteHorasEmpleadosTemporal() {
		try {
			HorasEmpleados horas_null = null;
			HorasEmpleados horas_emp = new HorasEmpleados(10, 10, "Javi");
			PowerMockito.mockStatic(BD.class);
			
			when(BD.horaEmpleadoTemporalSelect(st, "Javi")).thenReturn(horas_null);
			Response r = loginResources.deleteHorasEmpleadosTemporal("Javi");
			assertEquals(200, r.getStatus());
			
			when(BD.horaEmpleadoTemporalSelect(st, "Javi")).thenReturn(horas_emp);
			Response r2 = loginResources.deleteHorasEmpleadosTemporal("Javi");
			assertEquals(404, r2.getStatus());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteMecanico() {
		try {
			PowerMockito.mockStatic(BD.class);
			Mecanico mecanico =  new Mecanico("user", "pass", 1, "12345667V", "Kevin", "Ibañez", "Masculino", "em@gmail.com", "City", 48008, "Abando", "1231","1444", 1200, "665665665", 10);
			
			boolean b = true;
			when(BD.mecanicosDelete(st, mecanico.getDNI())).thenReturn(b);
			Response r= loginResources.deleteMecanico(mecanico.getDNI());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteSolicitudCompra() {
		try {
			PowerMockito.mockStatic(BD.class);
			SolicitudCompra solicitud =  new SolicitudCompra("","","",3);
			
			boolean b = true;
			when(BD.solicitudCompraDelete(st, solicitud.getCodigo())).thenReturn(b);
			Response r= loginResources.deleteSolicitudCompra(solicitud.getCodigo());
			assertEquals(200, r.getStatus()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteTarifa() {
		try {
			PowerMockito.mockStatic(BD.class);
			Tarifa mecanico =  new Tarifa("T1", "Cambio Aceite", 50, 1);
			
			boolean b = true;
			when(BD.tarifasDelete(st, mecanico.getIdTarifa())).thenReturn(b);
			Response r= loginResources.deleteTarifa(mecanico.getIdTarifa());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCocheTaller() {
		try {
			PowerMockito.mockStatic(BD.class);
			CocheTaller coches =  new CocheTaller("2544KLB", "Honda", "Civic", "Andres", "79076345T", 1300, 0);
			
			boolean b = true;
			when(BD.cocheTallerDelete(st, coches.getMatricula())).thenReturn(b);
			Response r= loginResources.deleteCocheTaller(coches.getMatricula());
			assertEquals(200, r.getStatus()); // AQUI AL REVES PORQUE HAGO DELETE
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCargarTablaTarifas() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Tarifa> tarifas= loginResources.cargarTablaTarifas();
		    assertTrue(tarifas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifasTodosSelect(st)).thenReturn(res);
			
			List<Tarifa> resultado = loginResources.cargarTablaTarifas();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarComercialTable() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Comercial> comerciales= loginResources.cargarComercialTable();
		    assertTrue(comerciales.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.comercialesTodasSelect(st)).thenReturn(res);
			List<Tarifa> resultado = loginResources.cargarTablaTarifas();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarMecanicoTable() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Mecanico> mecanicos= loginResources.cargarMecanicoTable();
		    assertTrue(mecanicos.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.mecanicosTodasSelect(st)).thenReturn(res);
			List<Mecanico> resultado = loginResources.cargarMecanicoTable();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarEmpleadoHoras() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response resp= loginResources.cargarEmpleadoHoras(0);
		    assertEquals(200, resp.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.empleadosHorasFiltroSelect(st, 0)).thenReturn(res);
			Response resp1= loginResources.cargarEmpleadoHoras(0);
		    assertEquals(404, resp1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarTablaClienteFidelidad() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<ClienteFidelidad> tarifas= loginResources.cargarTablaClienteFidelidad();
		    assertTrue(tarifas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.fidelidadSelect(st)).thenReturn(res);
			
			List<ClienteFidelidad> resultado = loginResources.cargarTablaClienteFidelidad();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarTablaPresupuestos() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Presupuesto> tarifas= loginResources.cargarTablaPresupuesto();
		    assertTrue(tarifas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.presupuestosTodosSelect(st)).thenReturn(res);
			
			List<Presupuesto> resultado = loginResources.cargarTablaPresupuesto();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarEmpleadoTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Empleado> empleados= loginResources.cargarEmpleadoTabla();
		    assertTrue(empleados.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.empleadosTodasSelect(st)).thenReturn(res);
			
			List<Empleado> resultado = loginResources.cargarEmpleadoTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarCochesMatriculadosTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<CocheMatriculado> coches= loginResources.cargarCochesMatriculadosTabla();
		    assertTrue(coches.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.cochesMatricTodosSelect(st)).thenReturn(res);
			
			List<CocheMatriculado> resultado = loginResources.cargarCochesMatriculadosTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarVentaTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Venta> ventas= loginResources.cargarVentaTabla();
		    assertTrue(ventas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.ventasTodasSelect(st)).thenReturn(res);
			
			List<Venta> resultado = loginResources.cargarVentaTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarPiezaUtilizadaTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Pieza> piezas= loginResources.cargarPiezaUtilizadaTabla();
		    assertTrue(piezas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.piezasUtilizadasTodasSelect(st)).thenReturn(res);
			
			List<Pieza> resultado = loginResources.cargarPiezaUtilizadaTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarCocheTallerTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<CocheTaller> coches= loginResources.cargarCocheTallerTabla();
		    assertTrue(coches.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.cochesTallerSelect(st)).thenReturn(res);
			
			List<CocheTaller> resultado = loginResources.cargarCocheTallerTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarProveedores() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Proveedor> proveedores= loginResources.cargarProveedores();
		    assertTrue(proveedores.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.proveedoresSelect(st)).thenReturn(res);
			
			List<Proveedor> resultado = loginResources.cargarProveedores();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarProveedoresHerramientas() {
		try {
			List<ProveedorHerramientas> proveedores= loginResources.cargarProveedoresHerramientas();
		    assertTrue(proveedores.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.proveedoresHerramientasSelect(st)).thenReturn(res);
			
			List<ProveedorHerramientas> resultado = loginResources.cargarProveedoresHerramientas();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCargarPiezaProveedores() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<PiezaProveedores> piezas= loginResources.cargarPiezaProveedores();
		    assertTrue(piezas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.piezasProveedoresSelect(st)).thenReturn(res);
			
			List<PiezaProveedores> resultado = loginResources.cargarPiezaProveedores();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testCargarListaHerramientas() {
		try {
			List<Herramientas> herramientas= loginResources.cargarListaHerramientas();
		    assertTrue(herramientas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.herramientasSelect(st)).thenReturn(res);
			
			List<Herramientas> resultado = loginResources.cargarListaHerramientas();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarTablaSolicitudCompra() {
		try {
			List<SolicitudCompra> solicitud= loginResources.cargarTablaSolicitudCompra();
		    assertTrue(solicitud.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.solicitudTodasSelect(st)).thenReturn(res);
			
			List<SolicitudCompra> resultado = loginResources.cargarTablaSolicitudCompra();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarPiezaTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<Pieza> piezas= loginResources.cargarPiezaTabla();
		    assertTrue(piezas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.piezasTodasSelect(st)).thenReturn(res);
			
			List<Pieza> resultado = loginResources.cargarPiezaTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTablaHerramientasTaller() {
		try {
			List<HerramientasTaller> herramientas= loginResources.cargarTablaHerramientasTaller();
		    assertTrue(herramientas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);

			ResultSet res = null;
			when(BD.herramientasTallerTodasSelect(st)).thenReturn(res);
			
			List<HerramientasTaller> resultado = loginResources.cargarTablaHerramientasTaller();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCargarCocheConcesionarioTabla() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			List<CocheConcesionario> piezas= loginResources.cargarCocheConcesionarioTabla();
		    assertTrue(piezas.size() > 0);
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.cochesTodosSelect(st)).thenReturn(res);
			
			List<CocheConcesionario> resultado = loginResources.cargarCocheConcesionarioTabla();
			assertTrue(resultado.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarVentaModelo() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response piezas= loginResources.filtrarVentaModelo("Leon");
		    assertEquals(200, piezas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.ventasModeloSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.filtrarVentaModelo("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarVentaMarca() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response piezas= loginResources.filtrarVentaMarca("Seat");
		    assertEquals(200, piezas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.ventasModeloSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.filtrarVentaMarca("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarHorasMin() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarHorasMin(0);
		    assertEquals(200, tarifas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifaHorasMinSelect(st, 0)).thenReturn(res);
			
			Response resultado = loginResources.filtrarHorasMin(0);
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarHorasMax() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarHorasMax(4);
		    assertEquals(200, tarifas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifaHorasMaxSelect(st, 0)).thenReturn(res);
			
			Response resultado = loginResources.filtrarHorasMax(0);
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPrecioMin() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarPrecioMin(0);
		    assertEquals(200, tarifas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifaPrecioMinSelect(st, 0)).thenReturn(res);
			
			Response resultado = loginResources.filtrarPrecioMin(0);
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPrecio() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarPrecio(100);
		    assertEquals(200, tarifas.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.tarifaPrecioSelect(st, 0)).thenReturn(res);
			
			Response resultado = loginResources.filtrarPrecio(0);
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPresupuestoCliente() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarPresupuestoCliente("12345678A");
		    assertEquals(200, tarifas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			Presupuesto res = null;
			when(BD.presupuestoDNIClienteSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.filtrarPresupuestoCliente("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarCocheMatriculadoFiltro() {
		try {
			Response piezas= loginResources.filtrarCocheMatriculadoFiltro("9600JPT-0");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.cochesMatriculadosFiltroSelect(st, 0, "9600JPT")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarCocheMatriculadoFiltro("9600JPT-0");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarCocheConcesionarioFiltro() {
		try {
			Response piezas= loginResources.filtrarCocheConcesionarioFiltro("Seat-0");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.cochesConcesionarioFiltroSelect(st, "Seat", 0)).thenReturn(res);
			
			Response resultado =  loginResources.filtrarCocheMatriculadoFiltro("Seat-0");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarCocheTallerFiltro() {
		try {
			Response piezas= loginResources.filtrarCocheTallerFiltro("9600JPT-0");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.cochesTallerFiltroSelect(st, 0, "9600JPT" )).thenReturn(res);
			
			Response resultado =  loginResources.filtrarCocheTallerFiltro("9600JPT-0");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPresupuestoCodigo() {
		try {
			Response piezas= loginResources.filtrarPresupuestoCodigo("PRE1");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.presupuestosFiltroCodigoSelect(st, "PRE1")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarPresupuestoCodigo("PRE1");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testFiltrarPiezaUtilizadasFiltro() {
		try {
			Response piezas= loginResources.filtrarPiezaUtilizadasFiltro("P1-2");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.piezasUtilizadasFiltroSelect(st, 2, "P1")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarPiezaUtilizadasFiltro("P1-2");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarComercialModelo() {
		try {
			Response piezas= loginResources.filtrarComercialModelo("Jaimito");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.ventasComercialSelect(st,"Jaimito")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarComercialModelo("Jaimito");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarHerramientaMecanicoFiltro() {
		try {
			Response piezas= loginResources.filtrarHerramientaMecanicoFiltro("H1-0");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.herramientasMecanicoFiltroSelect(st,0, "H1")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarHerramientaMecanicoFiltro("H1-0");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPiezaMecanicoFiltro() {
		try {
			Response piezas= loginResources.filtrarPiezaMecanicoFiltro("P1-0");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.piezaMecanicoFiltroSelect(st,0, "P1")).thenReturn(res);
			
			Response resultado =  loginResources.filtrarPiezaMecanicoFiltro("P1-0");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitasMecanicoFiltrar() {
		try {
			Response piezas= loginResources.citasMecanicosFiltrar("Javiertxu;7-5-2020");
		    assertEquals(200, piezas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			ResultSet res = null;
			when(BD.filtrarCitasMecanicoPorFecha(st,"Javiertxu", "7-5-2020")).thenReturn(res);
			
			Response resultado =  loginResources.citasMecanicosFiltrar("Javiertxu;7-5-2020");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateMecanico() {
		try {
			PowerMockito.mockStatic(BD.class);
			when(BD.mecanicoUpdate(st, "Nick", 3)).thenReturn(true);
			Response resultado =  loginResources.updateMecanico("Nick-3");
			assertEquals(404, resultado.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			when(BD.mecanicoUpdate(st, "Nick", 3)).thenReturn(false);
			Response resultado1 =  loginResources.updateMecanico("Nick-3");
			assertEquals(200, resultado1.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiltrarPresupuestoProbema() {
		try {
			//Opcion 1 sin mock, al ser resultset (NO FUNCIONA/ HAY QUE CREAR UN RESULTSET)
			Response tarifas= loginResources.filtrarPresupuestoProblema("Aceite");
		    assertEquals(200, tarifas.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			//Opcion 2 con mock al poder poner null
			ResultSet res = null;
			when(BD.presupuestosFiltroProblemaSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.filtrarPresupuestoProblema("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitasComercialesCargar() {
		
		try {
			
			Response nickname = loginResources.citasComercialesCargar("Jorgico");
		    assertEquals(200, nickname.getStatus());;
		    
			PowerMockito.mockStatic(BD.class);
			
			ResultSet res = null;
			when(BD.citasDelComercialSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.citasComercialesCargar("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitasComercialesFiltrar() {
		
		try {
			
			Response nickname = loginResources.citasComercialesFiltrar("Jorgico;22-5-2020");
		    assertEquals(200, nickname.getStatus());
			PowerMockito.mockStatic(BD.class);
			
			ResultSet res = null;
			when(BD.filtrarCitasPorFecha(st, "", "")).thenReturn(res);
			
			Response resultado = loginResources.citasComercialesFiltrar("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitasMecanicoCargar() {
		
		try {
			
			Response nickname = loginResources.citasMecanicosCargar("Francisco");
		    assertEquals(200, nickname.getStatus());
		    
			PowerMockito.mockStatic(BD.class);
			
			ResultSet res = null;
			when(BD.citasDelMecanicoSelect(st, "")).thenReturn(res);
			
			Response resultado = loginResources.citasMecanicosCargar("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCitasMecanicosFiltrar() {
		
		try {
			
			Response nickname = loginResources.citasMecanicosFiltrar("Francisco;8-5-2020");
		    assertEquals(200, nickname.getStatus());
			PowerMockito.mockStatic(BD.class);
			
			ResultSet res = null;
			when(BD.filtrarCitasMecanicoPorFecha(st, "", "")).thenReturn(res);
			
			Response resultado = loginResources.citasMecanicosFiltrar("");
			assertEquals(404, resultado.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


//package concesionario.servidor;
//
//import static org.junit.Assert.*;
//
//
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import concesionario.datos.Usuario;
//import concesionario.servidor.BaseDatos.BD;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(BD.class)
//public class LoginResourcesTest {
//	
//	LoginResources loginResources;
//	
//	@Before
//	public void setUp() {
//			loginResources = new LoginResources();
//        	HttpServer server = Main.startServer();
//       
//	}
//	
//	@Test
//	public void testAnadirUsuario() {
//
//		try {
//			PowerMockito.mockStatic(BD.class);
//			
//			Connection con;
//			con = DriverManager.getConnection("jdbc:sqlite:BD/"+"Taller");
//			PowerMockito.doReturn(con).when(BD.class, BD.class.getDeclaredMethod("initBD", String.class));
//			PowerMockito.doReturn(con.createStatement()).when(BD.class, BD.class.getDeclaredMethod("usarCrearTablasBD", Connection.class));
//			Usuario usu= new Usuario("Pablo", "1234", 3);
//			PowerMockito.doReturn(usu).when(BD.class, BD.class.getDeclaredMethod("usuarioSelect", Statement.class, String.class));
//			Response result = loginResources.anadirUsuario(usu);
//			assertEquals(result.getStatus(), Status.OK.getStatusCode());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//			
//	
//		
//	}
//	
//
//}

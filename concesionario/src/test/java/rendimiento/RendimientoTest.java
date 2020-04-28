package rendimiento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Usuario;
import concesionario.servidor.Main;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@PerfTest(invocations = 5)
public class RendimientoTest {
	
	
	
	// If you use the EmptyReportModule, the report is not generated
	//@Rule public ContiPerfRule rule = new ContiPerfRule(new EmptyReportModule());
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	static Logger logger = Logger.getLogger(RendimientoTest.class.getName());

	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(RendimientoTest.class);
	}

	@Before 
	public void setUp() {
		logger.info("Entering setUp");
		HttpServer server = Main.startServer();
		logger.info("Exiting setUp");
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 10)
	public void testConectividadLogin(){
		ClienteApp clienteapp = new ClienteApp();
		Usuario admin = new Usuario("admin","admin",0);
		assertEquals(200, clienteapp.login(admin).getStatus());
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 10)
	public void testConectividadSelect(){
		ClienteApp clienteapp = new ClienteApp();
		String comercial = "Jorgico";
		assertEquals(200, clienteapp.comercialSelect(comercial).getStatus());
	}
}
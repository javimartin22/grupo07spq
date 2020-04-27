package concesionario.servidor.BaseDatos;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;

public class BDTest {
	private BD bd;
	Connection con;
	Statement st;
	
	@Before
	public void setUp() {
		bd = new BD();
		con = BD.initBD("Tests");
		BD.initBD("");
		
		st = BD.usarBD(con);
		BD.usarCrearTablasBD(con);
		BD.getLastError();
	}
	
	@After
	public void setDown() {
		Statement st = BD.usarBD(con);
		BD.reiniciarBD(con);
		BD.cerrarBD(con, st);
		BD.cerrarBD(null, null);
	}
	
	@Test
	public void TestUsuariosInsert() {
		boolean bool = BD.usuariosInsert(st, "Pablo", "1234", 1);
		assertTrue(bool);
		boolean bool1 = BD.usuariosInsert(st, "Pablo", "1234", 1);
		assertFalse(bool1);
	}
	
	@Test
	public void TetsClientesInsert() {
		boolean bool = BD.clientesInsert(st, "", "", "", "", "", "", "", "", 48007, "", "");
		assertTrue(bool);
		boolean bool1 = BD.clientesInsert(st, "", "", "", "", "", "", "", "", 48007, "", "");
		assertFalse(bool1);
	}
	
	@Test
	public void TestEmpleadosInsert() {
		boolean bool = BD.empleadosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		assertTrue(bool);
		boolean bool1 = BD.empleadosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		assertFalse(bool1);
	}
	
	@Test
	public void TestComercialesInsert() {
		boolean bool = BD.comercialesInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1, 1, 1);
		assertTrue(bool);
		boolean bool1 = BD.comercialesInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1, 1, 1);
		assertFalse(bool1);
	}
	
	@Test 
	public void TestMecanicosInsert() {
		boolean bool = BD.mecanicosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		boolean bool1 = BD.mecanicosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
	}
	
	@Test
	public void testDepartamentoComprasInsert() {
		assertTrue(BD.departamentoComprasInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1));
		assertFalse(BD.departamentoComprasInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1));
	}
	
	@Test 
	public void testPiezasInsert() {
		assertTrue(BD.piezasInsert(st, "", "", 1, ""));
		assertFalse(BD.piezasInsert(st, "", "", 1, ""));
	}
	
	@Test 
	public void testPiezasUtilizadasInsert() {
		assertTrue(BD.piezasUtilizadasInsert(st, "", "", 0, ""));
		assertFalse(BD.piezasUtilizadasInsert(st, "", "", 0, ""));
	}
	
//	@Test 
//	public void testCochesInsert() {
//		assertTrue(BD.cochesInsert(st, "", "", "", 1, 0, 0, 0));
//		assertFalse(BD.cochesInsert(st, "", "", "", 1, 0, 0, 0));
//	}
	
	@Test 
	public void testCochesMatriculadosInsert() {
		assertTrue(BD.cochesMatriculadosInsert(st, "", "", "", 0, 0, 0, "", 0, ""));
		assertFalse(BD.cochesMatriculadosInsert(st, "", "", "", 0, 0, 0, "", 0, ""));
	}
	
	@Test
	public void testCochesVendidodsInsert() {
		assertTrue(BD.cochesVendidodsInsert(st, "", "", "", "", "", ""));
		assertFalse(BD.cochesVendidodsInsert(st, "", "", "", "", "", ""));
	}
	
	@Test 
	public void testCocheTallerInsert() {
		assertTrue(BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0));
		assertFalse(BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0));
	}
	
	@Test 
	public void testPresupuestoInsert() {
		assertTrue(BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, ""));
		assertFalse(BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, ""));
	}
	
	@Test
	public void testTarifaInsert() {
		assertTrue(BD.TarifaInsert(st, "", "", 0, 0));
		assertFalse(BD.TarifaInsert(st, "", "", 0, 0));
	}
	
	@Test
	public void testUsuarioSelect(){
		Usuario user = new Usuario("Pablo", "1234", 1);
		BD.usuariosInsert(st, "Pablo", "1234", 1);
		Usuario usuario = BD.usuarioSelect(st, "Pablo");
		assertEquals(user.getNickname(), usuario.getNickname());
	}
	
	@Test 
	public void testClienteSelect() {
		Cliente client = new Cliente("", "", 0, "", "", "", "", "", "", 0, "", "");
		BD.clientesInsert(st, "", "", "", "", "", "", "", "", 0, "", "");
		Cliente cliente = BD.clienteSelect(st, "");
		assertEquals(client.getDNI(), cliente.getDNI());
	}
	
	@Test
	public void testEmpleadoSelect() {
		Empleado empleado = new Empleado("", "", 1, "", "", "", "", "", "", 1, "", "", "", 1, "", 1);
		BD.empleadosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		Empleado emp = BD.empleadoSelect(st, "");
		assertEquals(empleado.getDNI(), emp.getDNI());
	}
	
	@Test
	public void testEmpleadosTodosSelect() {
		BD.empleadosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		ResultSet rst = BD.empleadosTodasSelect(st);
		assertTrue(rst != null);
	}
	
	@Test 
	public void testComercialSelect() {
		Comercial comercial = new Comercial("", "", "", "", "", "", "", "", 0, "", "", "", 0, "", 0, 0, 0, 0);
		BD.comercialesInsert(st, "", "", "", "", "", "", "", "", 0, "", "", "", "", 0, 0, 0, 0);
		Comercial comercial1 = BD.ComercialSelect(st, "");
		assertEquals(comercial.getDNI(), comercial1.getDNI());
	}
	
	@Test
	public void testMecanicoSelect() {
		Mecanico mecanic = new Mecanico("", "", 1, "", "", "", "", "", "", 1, "", "", "", 1, "", 1);
		BD.mecanicosInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		Mecanico mecanico = BD.mecanicoSelect(st, "");
		assertEquals(mecanic.getDNI(), mecanico.getDNI());
	}
	
	@Test
	public void testDepartamentoCompras() {
		DepartamentoCompras dep = new DepartamentoCompras("", "", "", "", "", "", "", "", 1, "", "", "", 1, "", 1);
		BD.departamentoComprasInsert(st, "", "", "", "", "", "", "", "", 1, "", "", "", "", 1, 1);
		DepartamentoCompras departamentoCompras = BD.departamentoCompraSelect(st, "");
		assertEquals(dep.getDNI(), departamentoCompras.getDNI());
	}
	
	@Test
	public void testPiezasTodasSelect() {
		BD.piezasInsert(st, "", "", 1, "");
		BD.piezasInsert(st, "1", "", 0, "");
		ResultSet rst = BD.piezasTodasSelect(st);
		assertTrue(rst != null);
	}
	
	@Test
	public void testPiezaMecanicoFiltroSelect() {
		BD.piezasInsert(st, "", "", 1, "");
		BD.piezasInsert(st, "1", "", 0, "");
		ResultSet rst = BD.piezaMecanicoFiltroSelect(st, 0, "");
		assertTrue(rst != null);
		ResultSet rst1 = BD.piezaMecanicoFiltroSelect(st, 1, "");
		assertTrue(rst1 != null);
		ResultSet rst2 = BD.piezaMecanicoFiltroSelect(st, 2, "1");
		assertTrue(rst2 != null);
	}
	
	@Test
	public void testPiezaSelect() {
		BD.piezasInsert(st, "", "", 1, "");
		Pieza pieza = BD.piezaSelect(st, "");
		assertTrue(pieza != null);
	}
	
	@Test 
	public void testPiezasUtilizadasTodasSelect() {
		BD.piezasInsert(st, "", "", 1, "");
		BD.piezasInsert(st, "1", "", 0, "");
		ResultSet rst = BD.piezasUtilizadasTodasSelect(st);
		assertTrue(rst != null);
	}
	
	@Test
	public void testPiezasUtilizadasFiltroSelect() {
		BD.piezasInsert(st, "", "", 1, "");
		BD.piezasInsert(st, "1", "", 0, "");
		ResultSet rst = BD.piezasUtilizadasFiltroSelect(st, 0, "1");
		assertTrue(rst != null);
		ResultSet rst1 = BD.piezasUtilizadasFiltroSelect(st, 1, "1");
		assertTrue(rst1 != null);
		ResultSet rst2 = BD.piezasUtilizadasFiltroSelect(st, 2, "");
		assertTrue(rst2 != null);
	}
	
	@Test
	public void testPiezaUtilizadaSelect() {
		BD.piezasUtilizadasInsert(st, "", "", 1, "");
		Pieza pieza = BD.piezaUtilizadaSelect(st, "");
		assertTrue(pieza != null);
	}
	
	@Test
	public void testCocheConcesionarioSelect() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		CocheConcesionario coche = BD.cocheConcesionarioSelect(st, "");
		assertTrue(coche != null);
	}
	
	@Test 
	public void testCochesTodosSelect() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		ResultSet rst = BD.cochesTodosSelect(st);
		assertTrue(rst != null);
	}
	
	@Test 
	public void testCochesConcesionarioFiltroSelect() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		ResultSet rst = BD.cochesConcesionarioFiltroSelect(st, "", 0);
		assertTrue(rst != null);
		ResultSet rst1 = BD.cochesConcesionarioFiltroSelect(st, "", 1);
		assertTrue(rst1 != null);
		ResultSet rst2 = BD.cochesConcesionarioFiltroSelect(st, "0", 2);
		assertTrue(rst2 != null);
		ResultSet rst3 = BD.cochesConcesionarioFiltroSelect(st, "1", 3);
		assertTrue(rst3 != null);
	}
	
	@Test
	public void testCocheMarcaSelect() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		ResultSet rst = BD.cochesMarcaSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testCochesModeloSelect() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		ResultSet rst = BD.cochesModeloSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testCochesMatricTodosSelect() {
		BD.cochesMatriculadosInsert(st, "", "", "", 0, 0, 0, "", 0, "");
		ResultSet rst = BD.cochesMatricTodosSelect(st);
		assertTrue(rst != null);
	}
	
	@Test 
	public void testCochesMatriculadosFiltroSelect() {
		BD.cochesMatriculadosInsert(st, "", "", "", 0, 0, 0, "", 0, "");
		ResultSet rst = BD.cochesMatriculadosFiltroSelect(st, 0, "");
		assertTrue(rst != null);
		ResultSet rst1 = BD.cochesMatriculadosFiltroSelect(st, 1, "");
		assertTrue(rst1 != null);
		ResultSet rst2 = BD.cochesMatriculadosFiltroSelect(st, 2, "");
		assertTrue(rst2 != null);
	}
	
	@Test
	public void testCocheTallerSelect() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		ResultSet rst = BD.cochesTallerSelect(st);
		assertTrue(rst != null);
	}
	
	@Test
	public void testCocheTallerMecanicoSelect() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		ResultSet rst = BD.cochesTallerMecanicoSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testCochesTallerFiltroSelect() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		ResultSet rst = BD.cochesTallerFiltroSelect(st, 0, "");
		assertTrue(rst != null);
		ResultSet rst1 = BD.cochesTallerFiltroSelect(st, 1, "");
		assertTrue(rst1 != null);
		ResultSet rst2 = BD.cochesTallerFiltroSelect(st, 2, "0");
		assertTrue(rst2 != null);
		ResultSet rst3 = BD.cochesTallerFiltroSelect(st, 3, "0");
		assertTrue(rst3 != null);
	}
	
	@Test
	public void testCocheTalleSelect() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		CocheTaller coche = BD.cocheTalleSelect(st, "");
		assertTrue(coche != null);
	}
	
	@Test
	public void testVentaSelect() {
		BD.cochesVendidodsInsert(st, "", "", "", "", "", "");
		Venta venta = BD.ventaSelect(st, "");
		assertTrue(venta != null);
	}
	
	@Test 
	public void testVentasTodasSelect() {
		BD.cochesVendidodsInsert(st, "", "", "", "", "", "");
		ResultSet rst = BD.ventasTodasSelect(st);
		assertTrue(rst != null);
	}
	
	@Test
	public void testVentasMarcaSelect() {
		BD.cochesVendidodsInsert(st, "", "", "", "", "", "");
		ResultSet rst = BD.ventasMarcaSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testVentasModeloSelect() {
		BD.cochesVendidodsInsert(st, "", "", "", "", "", "");
		ResultSet rst = BD.ventasModeloSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testVentasComercialSelect() {
		BD.cochesVendidodsInsert(st, "", "", "", "", "", "");
		ResultSet rst = BD.ventasComercialSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test 
	public void testPresupuestoDNIClienteSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		Presupuesto presupuesto = BD.presupuestoDNIClienteSelect(st, "");
		assertTrue(presupuesto != null);
	}
	
	@Test 
	public void testPresupuestoCodigoSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		Presupuesto presupuesto = BD.presupuestoCodigoSelect(st, "");
		assertTrue(presupuesto != null);
	}
	
	@Test 
	public void testPresupuestosTodosSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		ResultSet rst = BD.presupuestosTodosSelect(st);
		assertTrue(rst != null);
	}
	
	@Test 
	public void testPresupuestosFiltroCodigoSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		ResultSet rst = BD.presupuestosFiltroCodigoSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testPresupuestoFiltroClienteSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		ResultSet rst = BD.presupuestosFiltroClienteSelect(st, "");
		assertTrue(rst != null);
	}
	
	@Test
	public void testPresupuestoFiltroProblemaSelect() {
		BD.PresupuestoInsert(st, "", "", "", "", "", "", 0, "", "", 0, "");
		ResultSet rst = BD.presupuestosFiltroProblemaSelect(st, "");
		assertTrue(rst != null);
	} 
	
	@Test
	public void testTarifaPrecioSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		ResultSet rst = BD.tarifaPrecioSelect(st, 0);
		assertTrue(rst != null);
	}
	
	@Test
	public void testTarifaPrecioMinSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		ResultSet rst = BD.tarifaPrecioMinSelect(st, 0);
		assertTrue(rst != null);
	}
	
	@Test
	public void testTarifaHorasMaxSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		ResultSet rst = BD.tarifaHorasMaxSelect(st, 0);
		assertTrue(rst != null);
	}
	
	@Test
	public void testTarifaHorasMinSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		ResultSet rst = BD.tarifaHorasMinSelect(st, 0);
		assertTrue(rst != null);
	}
	
	@Test
	public void testTarifaIdSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		Tarifa tarifa = BD.tarifaIdSelect(st, "");
		assertTrue(tarifa != null);
	}
	
	@Test
	public void testTarifasTodosSelect() {
		BD.TarifaInsert(st, "", "", 0, 0);
		ResultSet rst = BD.tarifasTodosSelect(st);
		assertTrue(rst != null);
	}
	
	@Test 
	public void testFidelidadSelect() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		ResultSet rst = BD.fidelidadSelect(st);
		assertTrue(rst != null);
	}
	
	@Test
	public void testUsuariosDelete() {
		BD.usuariosInsert(st, "Pablo", "1234", 1);
		assertTrue(BD.usuariosDelete(st, "Pablo"));
	}
	
	@Test
	public void testClientesDelete() {
		BD.clientesInsert(st, "", "", "", "", "", "", "", "", 0, "", "");
		assertFalse(BD.clientesDelete(st, "Pablo"));
	}
	
	@Test 
	public void testEmpleadosDelete() {
		BD.empleadosInsert(st, "", "", "", "", "", "", "", "", 0, "", "", "", "", 0, 0);
		assertTrue(BD.empleadosDelete(st, ""));
	}
	
	@Test 
	public void testMecanicosDelete() {
		BD.mecanicosInsert(st, "", "", "", "", "", "", "", "", 0, "", "", "", "", 0, 0);
		assertTrue(BD.mecanicosDelete(st, ""));
	}
	
	@Test 
	public void testComercialesDelete() {
		BD.comercialesInsert(st, "", "", "", "", "", "", "", "", 0, "", "", "", "", 0, 0, 0, 0);
		assertTrue(BD.comercialesDelete(st, ""));
	}
	
	@Test 
	public void testDepartamentoComprasDelete() {
		BD.departamentoComprasInsert(st, "", "", "", "", "", "", "", "", 0, "", "", "", "", 0, 0);
		assertTrue(BD.departamentoComprasDelete(st, ""));
	}
	
	@Test
	public void testPiezaDelete() {
		BD.piezasInsert(st, "", "", 0, "");
		assertTrue(BD.piezaDelete(st, ""));
	}
	
	@Test 
	public void testPiezaUtilizadaDelete() {
		BD.piezasUtilizadasInsert(st, "", "", 0, "");
		assertTrue(BD.piezaUtilizadaDelete(st, ""));
	}
	
	@Test
	public void testCochesDelete() {
		BD.cochesInsert(st, "", "", "", 0, 0, 0, 0);
		assertTrue(BD.cochesDelete(st, ""));
	}
	
	@Test 
	public void testCocheTallerDelete() {
		BD.cocheTallerInsert(st, "", "", "", "", "", 0, 0);
		assertTrue(BD.cocheTallerDelete(st, ""));
	}
	
	@Test
	public void testTarifasDelete() {
		BD.TarifaInsert(st, "", "", 0, 0);
		assertTrue(BD.tarifasDelete(st, ""));
	}
}

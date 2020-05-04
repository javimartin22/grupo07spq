package concesionario.servidor;

import concesionario.datos.*;
import concesionario.servidor.BaseDatos.BD;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("loginp")
public class LoginResources {
	
	private Connection con;
	private Statement st;
	
	
	//Por ahora solo funciona este metodo
	@POST
	@Path("inicio")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response anadirUsuario(Usuario concat) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String username = concat.getNickname();
		String pass = concat.getContrasenya();
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			String tipo = Integer.toString(nuevo.getTipo());
			if(nuevo.getNickname().equals(username) && nuevo.getContrasenya().equals(pass)) {
				return Response.status(Response.Status.OK).entity(tipo).build();
			}else {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			}
		}
	}
	
	@POST
	@Path("insertClient")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarCliente(Cliente client) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = client.getNickname();
		String pass = client.getContrasenya();
		String dni = client.getDNI();
		String nombre = client.getNombre();
		String apellido = client.getApellido();
		String sexo = client.getSexo();
		String email = client.getEmail();
		String ciudad = client.getCiudad();
		int codigoPostal = client.getCodigoPostal();
		String dir = client.getDireccion();
		String numTelefono = client.getNumeroTelefono();
		
		BD.clientesInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono);
		BD.usuariosInsert(st, username, pass, 3);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		Cliente clienteNuevo = BD.clienteSelect(st, username);
		
		if (nuevo == null) {
			if (clienteNuevo == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.status(Response.Status.OK).build();
			}
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertCocheConcesionario")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCocheConcesionario(CocheConcesionario auto) {
		System.out.println(auto.getModelo());
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.cochesInsert(st, auto.getModelo(), auto.getMarca(), auto.getColor(), auto.getCv(), auto.getNumPuertas(), auto.getUnidades(), auto.getPrecio());
		CocheConcesionario coche = BD.cocheConcesionarioSelect(st, auto.getModelo());
		
		if (coche == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	

	@POST
	@Path("insertCocheTaller")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCocheTaller(CocheTaller cocheTaller) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		boolean b = BD.cocheTallerInsert(st, cocheTaller.getMatricula(), cocheTaller.getMarca(), cocheTaller.getModelo(), cocheTaller.getMecanico(),cocheTaller.getDniCliente(),cocheTaller.getCoste(), cocheTaller.getEstado());
		
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	
	@POST
 	@Path("insertVenta")
 	@Consumes(MediaType.APPLICATION_JSON)
 	public Response registrarVenta(Venta venta) {
 		con =BD.initBD("Taller");
 		st = BD.usarCrearTablasBD(con);

 		BD.cochesVendidodsInsert(st, venta.getFecha(), venta.getNicknameComercial(), venta.getNombreComprador(), venta.getMarca(), venta.getModelo(), venta.getMatricula());
 		Venta v = BD.ventaSelect(st, venta.getMatricula());
 		
 		if (v == null) {
 			return Response.status(Response.Status.NOT_FOUND).build();
 		} else {
 			return Response.status(Response.Status.OK).build();
 		}
 	}
	
	@POST
	@Path("insertMecanic")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarMecanico(Mecanico mecanic) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String username = mecanic.getNickname();
		String pass = mecanic.getContrasenia();
		String dni = mecanic.getDNI();
		String nombre = mecanic.getNombre();
		String apellido = mecanic.getApellido();
		String sexo = mecanic.getSexo();
		String email = mecanic.getEmail();
		String ciudad = mecanic.getCiudad();
		int codigoPostal = mecanic.getCodigoPostal();
		String dir = mecanic.getDireccion();
		String numTelefono = mecanic.getNumeroTelefono();
		String NSS = mecanic.getNSS();
		String numeroCuenta = mecanic.getNumeroCuenta();
		int sueldo = mecanic.getSueldo();
		int horas = mecanic.getHoras();
		
		BD.mecanicosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, horas);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 0);
		BD.usuariosInsert(st, username, pass, 1);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarComercial(Comercial comercial) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = comercial.getNickname();
		String pass = comercial.getContrasenia();
		String dni = comercial.getDNI();
		String nombre = comercial.getNombre();
		String apellido = comercial.getApellido();
		String sexo = comercial.getSexo();
		String email = comercial.getEmail();
		String ciudad = comercial.getCiudad();
		int codigoPostal = comercial.getCodigoPostal();
		String dir = comercial.getDireccion();
		String numTelefono = comercial.getNumeroTelefono();
		String NSS = comercial.getNSS();
		String numeroCuenta = comercial.getNumeroCuenta();
		int sueldo = comercial.getSueldo();
		int horas = comercial.getHoras();
		int cochesVendidos = comercial.getCochesVendidos();
		int importeObtenido = comercial.getImporteObetenido();
		
		
		BD.comercialesInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, horas,cochesVendidos, importeObtenido);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 1);
		BD.usuariosInsert(st, username, pass, 2);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertDepartamentoCompras")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarDepartamentoCompras(DepartamentoCompras departamentoCompras) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = departamentoCompras.getNickname();
		String pass = departamentoCompras.getContrasenia();
		String dni = departamentoCompras.getDNI();
		String nombre = departamentoCompras.getNombre();
		String apellido = departamentoCompras.getApellido();
		String sexo = departamentoCompras.getSexo();
		String email = departamentoCompras.getEmail();
		String ciudad = departamentoCompras.getCiudad();
		int codigoPostal = departamentoCompras.getCodigoPostal();
		String dir = departamentoCompras.getDireccion();
		String numTelefono = departamentoCompras.getNumeroTelefono();
		String NSS = departamentoCompras.getNSS();
		String numeroCuenta = departamentoCompras.getNumeroCuenta();
		int sueldo = departamentoCompras.getSueldo();
		int pedidos = departamentoCompras.getPedidos();
		
		
		BD.departamentoComprasInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, pedidos);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 2);
		BD.usuariosInsert(st, username, pass, 4);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarTarifa(Tarifa tarifa) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String idTarifa = tarifa.getIdTarifa();
		String nomTarifa = tarifa.getNomTarifa();
		int precioAprox = tarifa.getPrecioAprox();
		int horas_manodeobra = tarifa.getHoras_manodeobra();
		
		BD.TarifaInsert(st, idTarifa, nomTarifa, precioAprox, horas_manodeobra);
		Tarifa nuevo = BD.tarifaIdSelect(st, idTarifa);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	
	
	@POST
	@Path("deleteClient")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteCliente(Cliente client) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.clientesDelete(st, client.getDNI());
		BD.usuariosDelete(st, client.getNickname());
		Cliente nuevo = BD.clienteSelect(st, client.getNickname());
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("deleteCocheTaller")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteCocheTaller(String matricula) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		boolean b = BD.cocheTallerDelete(st, matricula);
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("deleteTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteTarifa(String idTarifa) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		boolean b = BD.tarifasDelete(st, idTarifa);
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("deleteMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteMecanico(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.mecanicosDelete(st, nickname);
		BD.empleadosDelete(st, nickname);
		BD.usuariosDelete(st, nickname);
		Mecanico nuevo = BD.mecanicoSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("deleteComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteComercial(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.comercialesDelete(st, nickname);
		BD.empleadosDelete(st, nickname);
		BD.usuariosDelete(st, nickname);
		Comercial nuevo = BD.ComercialSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("deleteDepartamentoCompras")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteDepartamentoCompras(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.departamentoComprasDelete(st, nickname);
		BD.empleadosDelete(st, nickname);
		BD.usuariosDelete(st, nickname);
		DepartamentoCompras nuevo = BD.departamentoCompraSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("selectClient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectCliente(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Cliente nuevo = BD.clienteSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectCocheTaller")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectCocheTaller(String matricula) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		CocheTaller nuevo = BD.cocheTalleSelect(st, matricula);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectCocheConcesionario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectCocheConcesionario(String modelo) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		CocheConcesionario nuevo = BD.cocheConcesionarioSelect(st, modelo);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectMecanico(String nickname) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Mecanico nuevo = BD.mecanicoSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectComercial(String nickname) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Comercial nuevo = BD.ComercialSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectDepartamentoCompras")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectDepartamentoCompras(String nickname) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		DepartamentoCompras nuevo = BD.departamentoCompraSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectPiezaUtilizada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectPiezaUtilizada(String codigo) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Pieza nuevo = BD.piezaUtilizadaSelect(st, codigo);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("insertPiezas")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarPieza(Pieza pieza) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = pieza.getCodigo();
		String nombre = pieza.getNombre();
		String ubicacion = pieza.getUbicacion();
		int stock = pieza.getUnidades();
		
		BD.piezasInsert(st, codigo, nombre, stock, ubicacion);
		BD.piezasUtilizadasInsert(st, codigo, nombre, 0, ubicacion);
		Pieza nuevo = BD.piezaSelect(st, pieza.getCodigo());
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	
	@POST
	@Path("insertPresupuesto")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registroPresupuesto(Presupuesto presupuesto) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = presupuesto.getCodigo();
		String dniCliente = presupuesto.getDniCliente();
		String mecanico = presupuesto.getMecanico();
		String marca = presupuesto.getMarca();
		String modelo = presupuesto.getModelo();
		String problema = presupuesto.getProblema();
		int numPiezas = presupuesto.getNumPiezas();
		String piezas = presupuesto.getListaPiezas();
		String observaciones = presupuesto.getObservaciones();
		int precio = presupuesto.getPrecio();
		String fecha = presupuesto.getFecha();
		
		boolean bool = BD.PresupuestoInsert(st, codigo, dniCliente, mecanico, marca, modelo, problema, numPiezas, piezas, observaciones, precio, fecha);
		
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("insertPiezaUtilizada")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarPiezaUtilizada(Pieza pieza) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = pieza.getCodigo();
		String nombre = pieza.getNombre();
		String ubicacion = pieza.getUbicacion();
		int stock = pieza.getUnidades();
		
		BD.piezasUtilizadasInsert(st, codigo, nombre, stock, ubicacion);
		Pieza nuevo = BD.piezaUtilizadaSelect(st, pieza.getCodigo());
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("deletePiezaUtilizada")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deletePiezaUtilizada(String codigo) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.piezaUtilizadaDelete(st, codigo);
		Pieza nuevo = BD.piezaSelect(st, codigo);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("loadCocheConcesionarioTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheConcesionario> cargarCocheConcesionarioTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.cochesTodosSelect(st);
		List<CocheConcesionario> coches_result = new ArrayList<CocheConcesionario>();
		
		if (rs == null) {
			return coches_result;
		} else {
			while(rs.next()) {
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String color = rs.getString("color");
				int CV = rs.getInt("CV");
				int numeroPuertas = rs.getInt("numeroPuertas");
				int unidades = rs.getInt("unidades");
				int precio = rs.getInt("precio");
						
				CocheConcesionario coche = new CocheConcesionario(marca, modelo, precio, CV , numeroPuertas, color, unidades );
				coches_result.add(coche);
			}
			return coches_result;
		}
	}
	
	
	@GET
	@Path("loadPiezaTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pieza> cargarPiezaTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.piezasTodasSelect(st);
		List<Pieza> pieza_result = new ArrayList<Pieza>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("stock");
				String ubicacion = rs.getString("ubicacion");
				
				Pieza pieza = new Pieza(codigo, nombre, unidades, ubicacion);	
				pieza_result.add(pieza);
			}
			 
			return pieza_result;
		}
	}
	
	@GET
	@Path("loadPiezasProveedoresList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaProveedores> cargarPiezaProveedores()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.piezasProveedoresSelect(st);
		List<PiezaProveedores> pieza_result = new ArrayList<PiezaProveedores>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int tiempo = rs.getInt("tiempo");
				String tipo = rs.getString("tipo");
				String codProveedor = rs.getString("codProveedor");
				
				PiezaProveedores pieza = new PiezaProveedores(codigo, nombre, tiempo, tipo, codProveedor);	
				pieza_result.add(pieza);
			}
			 
			return pieza_result;
		}
	}
	
	@GET
	@Path("loadProveedores")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Proveedor> cargarProveedores()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.proveedoresSelect(st);
		List<Proveedor> pieza_result = new ArrayList<Proveedor>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("idProveedor");
				String nombre = rs.getString("nombre");
				String pais = rs.getString("pais");
				String tipo = rs.getString("tipo");
				System.out.println(codigo + " " + nombre + pais + tipo);
				
				Proveedor proveedor = new Proveedor(codigo, nombre, pais, tipo);	
				pieza_result.add(proveedor);
			}
			return pieza_result;
		}
	}
	
	
	
	@GET
	@Path("loadCocheTallerTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheTaller> cargarCocheTallerTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.cochesTallerSelect(st);
		List<CocheTaller> coches_result = new ArrayList<CocheTaller>();
		
		if (rs == null) {
			return coches_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String matricula = rs.getString("matriculaCoche");
				String marca = rs.getString("marca");
				int estado = rs.getInt("estado");
				String modelo = rs.getString("modelo");
				String mecanico = rs.getString("mecanico");
				String dniCliente = rs.getString("dniCliente");
				double coste = rs.getDouble("coste");
				
				CocheTaller coche = new CocheTaller(matricula, marca, modelo, mecanico, dniCliente, coste, estado);
				coches_result.add(coche);
			}
			return coches_result;
		}
	}
	
	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	@GET
	@Path("loadPiezaUtilizadasTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pieza> cargarPiezaUtilizadaTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.piezasUtilizadasTodasSelect(st);
		List<Pieza> pieza_result = new ArrayList<Pieza>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("unidades");
				String ubicacion = rs.getString("ubicacion");
				
				Pieza pieza = new Pieza(codigo, nombre, unidades, ubicacion);	
				pieza_result.add(pieza);
			}
			 
			return pieza_result;
		}
	}
	
	@GET
	@Path("loadVentaTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venta> cargarVentaTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.ventasTodasSelect(st);
		List<Venta> venta_result = new ArrayList<Venta>();
		
		if (rs == null) {
			return venta_result;
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String fecha = rs.getString("fecha");
				String modelo = rs.getString("modelo");
				String marca = rs.getString("marca");
				String matricula = rs.getString("matricula");
				String nicknameComercial = rs.getString("nombreVendedor");
				String nombreComprador = rs.getString("nombreComprador");
				
				Venta venta = new Venta(fecha, modelo, marca, matricula, nicknameComercial, nombreComprador);
				venta_result.add(venta);
			}
			return venta_result;
		}
	}
	
	
	
	@GET
	@Path("loadCochesMatricTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheMatriculado> cargarCochesMatriculadosTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.cochesMatricTodosSelect(st);
		List<CocheMatriculado> cochesMatric_result = new ArrayList<CocheMatriculado>();
		
		if (rs == null) {
			System.out.println("No hay coches bd");
			return cochesMatric_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String matricula = rs.getString("matricula");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				int anyo_matric = rs.getInt("anyomatriculacion");
				int revisiones = rs.getInt("revisiones");
				int cv = rs.getInt("cv");
				String nombrePropietario = rs.getString("nombreCliente");
				String color = rs.getString("Color");
				int numPuertas = rs.getInt("numPuertas");
				CocheMatriculado cocheMatric = new CocheMatriculado(marca, modelo, matricula, nombrePropietario, color, numPuertas, anyo_matric, cv, revisiones);
				
				cochesMatric_result.add(cocheMatric);
			}
			 
			return cochesMatric_result;
		}
	}
	
	@GET
	@Path("loadEmpleadosTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> cargarEmpleadoTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.empleadosTodasSelect(st);
		List<Empleado> empleados_result = new ArrayList<Empleado>();
		
		if (rs == null) {
			System.out.println("No hay empleados bd");
			return empleados_result;
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String nickname = rs.getString("nickname");
				String contrasenia = rs.getString("contrasenia");
				String dNI = rs.getString("dni");
				int tipo = 0;
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				String ciudad = rs.getString("ciudad");
				int codigoPostal = rs.getInt("codigoPostal");
				String direccion = rs.getString("dir");
				String nSS = rs.getString("NSS");
				String numeroCuenta = rs.getString("numeroCuenta");
				String numeroTelefono = rs.getString("numTelefono");
				int sueldo = rs.getInt("sueldo");
				int tipoEmpleado = rs.getInt("tipoEmpleado");
				Empleado empleado = new Empleado(nickname, contrasenia, tipo, dNI, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, nSS, numeroCuenta, sueldo, numeroTelefono, tipoEmpleado);
				empleados_result.add(empleado);
			}
			return empleados_result;
		}
	}
	
	@GET
	@Path("loadPresupuestosTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Presupuesto> cargarTablaPresupuesto()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.presupuestosTodosSelect(st);
		List<Presupuesto> presupuestos_result = new ArrayList<Presupuesto>();
		
		if (rs == null) {
			System.out.println("No hay empleados bd");
			return presupuestos_result;
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
					String dniCliente = rs.getString("dniCliente");
					String mecanico = rs.getString("mecanico");
					String marca = rs.getString("marca");
					String modelo = rs.getString("modelo");
					String problema = rs.getString("problema");
					int numPiezas = rs.getInt("numPiezas");
					String listaPiezas = rs.getString("piezas");
					String observaciones = rs.getString("observaviones");
					int precio = rs.getInt("precio");
					String fecha = rs.getString("fecha");
				Presupuesto presupuesto = new Presupuesto(codigo, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);
				presupuestos_result.add(presupuesto);
			}
			return presupuestos_result;
		}
	}
	
	@POST
	@Path("selectPresupuesto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectPresupuesto(String codigo) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Presupuesto nuevo = BD.presupuestoCodigoSelect(st, codigo);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@GET
	@Path("loadTarifasTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarifa> cargarTablaTarifas()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.tarifasTodosSelect(st);
		List<Tarifa> tarifas_result = new ArrayList<Tarifa>();
		
		if (rs == null) {
			System.out.println("No hay empleados bd");
			return tarifas_result;
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String idTarifa = rs.getString("idTarifa");
					String nomTarifa = rs.getString("nomTarifa");
					int precioAprox = rs.getInt("precioAprox");
					int horas_manodeobra = rs.getInt("horas_manodeobra");
					Tarifa tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					tarifas_result.add(tarifa);
			}
			return tarifas_result;
		}
	}
	

	@POST
	@Path("precioTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPrecio(int precio) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.tarifaPrecioSelect(st, precio);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		//"(idTarifa string PRIMARY KEY, nomTarifa string, precioAprox int, horas_manodeobra int)";
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String idTarifa = rs.getString("idTarifa");
					String nomTarifa = rs.getString("nomTarifa");
					int precioAprox = rs.getInt("precioAprox");
					int horas_manodeobra = rs.getInt("horas_manodeobra");

					Tarifa tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					tarifas.add(tarifa);
				}
				for(Tarifa t :tarifas) {
					System.out.println(t.getPrecioAprox());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(tarifas).build();
		}
	
	}
	
	@POST
	@Path("precioMinTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPrecioMin(int precio) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.tarifaPrecioMinSelect(st, precio);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String idTarifa = rs.getString("idTarifa");
					String nomTarifa = rs.getString("nomTarifa");
					int precioAprox = rs.getInt("precioAprox");
					int horas_manodeobra = rs.getInt("horas_manodeobra");

					Tarifa tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					tarifas.add(tarifa);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(tarifas).build();
		}
	
	}
	
	@POST
	@Path("horasMaxTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarHorasMax(int horas) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.tarifaHorasMaxSelect(st, horas);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String idTarifa = rs.getString("idTarifa");
					String nomTarifa = rs.getString("nomTarifa");
					int precioAprox = rs.getInt("precioAprox");
					int horas_manodeobra = rs.getInt("horas_manodeobra");

					Tarifa tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					tarifas.add(tarifa);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(tarifas).build();
		}
	
	}
	
	@POST
	@Path("horasMinTarifa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarHorasMin(int horas) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.tarifaHorasMinSelect(st, horas);
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String idTarifa = rs.getString("idTarifa");
					String nomTarifa = rs.getString("nomTarifa");
					int precioAprox = rs.getInt("precioAprox");
					int horas_manodeobra = rs.getInt("horas_manodeobra");

					Tarifa tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					tarifas.add(tarifa);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(tarifas).build();
		}
	
	}
	
	@POST
	@Path("loadTablaMarcaVentas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarVentaMarca(String marca) {
		System.out.println(marca);
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.ventasMarcaSelect(st, marca);
		List<Venta> ventas = new ArrayList<Venta>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String fecha = rs.getString("fecha");
					String nicknameComercial = rs.getString("nombreVendedor");
					String marc = rs.getString("marca");
					String modelo = rs.getString("modelo");
					String nombreComprador = rs.getString("nombreComprador");
					String matricul = rs.getString("matricula");
					Venta venta = new Venta(fecha, modelo, marc, matricul, nicknameComercial, nombreComprador);					
					ventas.add(venta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(ventas).build();
		}
	}

	@POST
	@Path("loadTablaModeloVentas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarVentaModelo(String modelo) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.ventasModeloSelect(st, modelo);
		List<Venta> ventas = new ArrayList<Venta>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String fecha = rs.getString("fecha");
					String nicknameComercial = rs.getString("nombreVendedor");
					String marca = rs.getString("marca");
					String model = rs.getString("modelo");
					String nombreComprador = rs.getString("nombreComprador");
					String matricul = rs.getString("matricula");
					Venta venta = new Venta(fecha, model, marca, matricul, nicknameComercial, nombreComprador);					
					ventas.add(venta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(ventas).build();
		}
	}
	
	@POST
	@Path("loadTablaComercialVentas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarComercialModelo(String comercial) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.ventasComercialSelect(st, comercial);
		List<Venta> ventas = new ArrayList<Venta>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String fecha = rs.getString("fecha");
					String nicknameComercial = rs.getString("nombreVendedor");
					String marca = rs.getString("marca");
					String model = rs.getString("modelo");
					String nombreComprador = rs.getString("nombreComprador");
					String matricul = rs.getString("matricula");
					Venta venta = new Venta(fecha, model, marca, matricul, nicknameComercial, nombreComprador);					
					ventas.add(venta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(ventas).build();
		}
	}
	
	@POST
	@Path("loadTablaPresupuestoCodigo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPresupuestoCodigo(String codigo) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.presupuestosFiltroCodigoSelect(st, codigo);
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
 					String dniCliente = rs.getString("dniCliente");
 					String mecanico = rs.getString("mecanico");
 					String marca = rs.getString("marca");
 					String modelo = rs.getString("modelo");
 					String problema = rs.getString("problema");
 					int numPiezas = rs.getInt("numPiezas");
 					String listaPiezas = rs.getString("piezas");
 					String observaciones = rs.getString("observaviones");
 					int precio = rs.getInt("precio");
 					String fecha = rs.getString("fecha");
					Presupuesto presupuesto = new Presupuesto(cod, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);		
					presupuestos.add(presupuesto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(presupuestos).build();
		}
	}
	
	@POST
	@Path("loadTablaPresupuestoCliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPresupuestoCliente(String cliente) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.presupuestosFiltroClienteSelect(st, cliente);
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
 					String dniCliente = rs.getString("dniCliente");
 					String mecanico = rs.getString("mecanico");
 					String marca = rs.getString("marca");
 					String modelo = rs.getString("modelo");
 					String problema = rs.getString("problema");
 					int numPiezas = rs.getInt("numPiezas");
 					String listaPiezas = rs.getString("piezas");
 					String observaciones = rs.getString("observaviones");
 					int precio = rs.getInt("precio");
 					String fecha = rs.getString("fecha");
					Presupuesto presupuesto = new Presupuesto(cod, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);		
					presupuestos.add(presupuesto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(presupuestos).build();
		}
	}
	
	@POST
	@Path("loadTablaPresupuestoProblema")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPresupuestoProblema(String problema) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.presupuestosFiltroProblemaSelect(st, problema);
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
 					String dniCliente = rs.getString("dniCliente");
 					String mecanico = rs.getString("mecanico");
 					String marca = rs.getString("marca");
 					String modelo = rs.getString("modelo");
 					String problem = rs.getString("problema");
 					int numPiezas = rs.getInt("numPiezas");
 					String listaPiezas = rs.getString("piezas");
 					String observaciones = rs.getString("observaviones");
 					int precio = rs.getInt("precio");
 					String fecha = rs.getString("fecha");
					Presupuesto presupuesto = new Presupuesto(cod, dniCliente, mecanico, marca, modelo, problem, numPiezas, listaPiezas, observaciones, precio, fecha);		
					presupuestos.add(presupuesto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(presupuestos).build();
		}
	}
	
	@POST
	@Path("loadTablaCocheConcesionarioFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarCocheConcesionarioFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.cochesConcesionarioFiltroSelect(st, restriccion, tipo);
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String model = rs.getString("modelo");
					String marca = rs.getString("marca");
					int precio = rs.getInt("precio");
					int unidades = rs.getInt("unidades");
					int CV = rs.getInt("CV");
					int numPuertas = rs.getInt("numeroPuertas");
					String color = rs.getString("color");
					CocheConcesionario coche = new CocheConcesionario(marca, model, precio, CV, numPuertas, color, unidades);
					coches.add(coche);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(coches).build();
		}
	}
	
	@POST
	@Path("loadTablaCocheMatriculadoFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarCocheMatriculadoFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.cochesMatriculadosFiltroSelect(st, tipo, restriccion);
		List<CocheMatriculado> coches = new ArrayList<CocheMatriculado>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String modelo = rs.getString("modelo");
					String marca = rs.getString("marca");
					String matricula = rs.getString("matricula");
					String nombrePropietario = rs.getString("nombreCliente");
					int anyo_matriculacion = rs.getInt("anyomatriculacion");
					int revisiones = rs.getInt("revisiones");
					int cv = rs.getInt("cv");
					int numPuertas = rs.getInt("numPuertas");
					String color = rs.getString("color");
					CocheMatriculado coche = new CocheMatriculado(marca, modelo, matricula, nombrePropietario, color, numPuertas, anyo_matriculacion, cv, revisiones);
					coches.add(coche);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(coches).build();
		}
	}
	
	@POST
	@Path("loadTablaCocheTallerFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarCocheTallerFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.cochesTallerFiltroSelect(st, tipo, restriccion);
		List<CocheTaller> coches = new ArrayList<CocheTaller>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String matriculaCoche = rs.getString("matriculaCoche");
					String marca = rs.getString("marca");
					String mecanico = rs.getString("mecanico");
					String modelo = rs.getString("modelo");
					String dniCliente = rs.getString("dniCliente");
					double coste = rs.getDouble("coste");
					int estado = rs.getInt("estado");
					CocheTaller coche = new CocheTaller(matriculaCoche, marca, modelo, mecanico, dniCliente, coste, estado);
					coches.add(coche);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(coches).build();
		}
	}
	
	@POST
	@Path("loadTablaPiezaMecanicoFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPiezaMecanicoFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.piezaMecanicoFiltroSelect(st, tipo, restriccion);
		List<Pieza> piezas = new ArrayList<Pieza>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
					String nombre = rs.getString("nombre");
					int unidades = rs.getInt("stock");
					String ubicacion = rs.getString("ubicacion");
					Pieza pieza = new Pieza(cod, nombre, unidades, ubicacion);
					piezas.add(pieza);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(piezas).build();
		}
	}
	
	@POST
	@Path("loadTablaPiezaUtilizadasFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarPiezaUtilizadasFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.piezasUtilizadasFiltroSelect(st, tipo, restriccion);
		List<Pieza> piezas = new ArrayList<Pieza>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
					String nombre = rs.getString("nombre");
					int unidades = rs.getInt("unidades");
					String ubicacion = rs.getString("ubicacion");
					Pieza pieza = new Pieza(cod, nombre, unidades, ubicacion);
					piezas.add(pieza);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(piezas).build();
		}
	}
	
	@GET
	@Path("loadClientesFidelidadTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteFidelidad> cargarTablaClienteFidelidad()throws SQLException {
		System.out.println("Llega al server");
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.fidelidadSelect(st);
		List<ClienteFidelidad> clientes = new ArrayList<ClienteFidelidad>();
		
		if (rs == null) {
			System.out.println("No hay empleados bd");
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String dni = rs.getString("dniCliente");
				int fidelidad = rs.getInt("fidelidad");
				ClienteFidelidad cliente = new ClienteFidelidad(dni, fidelidad);
				clientes.add(cliente);
			}
		}
		System.out.println(clientes.size());
		System.out.println("Regresa");
		return clientes;
	}
}




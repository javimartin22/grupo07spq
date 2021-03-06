package concesionario.servidor;

import concesionario.datos.*;
import concesionario.servidor.BaseDatos.BD;

import javax.ws.rs.Path;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("loginp")
/**
 *Clase LoginResources (Servidor de la Aplicacion).
 */
public class LoginResources {
	
	private Connection con;
	private Statement st;
	
	
	/**
	 * Metodo para registrar un añadir un usuario en la aplicacion
	 * @param concat (Objeto tipo Usuario)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar un nuevo cliente en la aplicacion
	 * @param client (Objeto tipo Cliente)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar un nuevo coche al Catalogo del Concesionario
	 * @param auto (Objeto tipo CocheConcesionario)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertCocheConcesionario")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCocheConcesionario(CocheConcesionario auto) {
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
	
	/**
	 * Metodo para registrar un nuevo coche al Taller
	 * @param cocheTaller (Objeto tipo CocheConcesionario)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar las horas de un empleado
	 * @param horasEmpleado (String de formato empleado-hh-mm)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertHorasEmpleado")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarHorasEmpleado(String horasEmpleado) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String[] strings = horasEmpleado.split("-");
		int hora = Integer.parseInt(strings[1]);
		int minuto = Integer.parseInt(strings[2]);
		
		boolean b = BD.HorasEmpleadoInsert(st, strings[0], hora, minuto);
		
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	

	/**
	 * Metodo para registrar las horas de un empleado temporal
	 * @param horasEmpleado (String de formato empleado-hh-mm)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertHorasEmpleadoTemporal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarHorasEmpleadoTemporal(String horasEmpleado) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String[] strings = horasEmpleado.split("-");
		int hora = Integer.parseInt(strings[1]);
		int minuto = Integer.parseInt(strings[2]);
		
		boolean b = BD.HorasEmpleadoTemporalInsert(st, strings[0], hora, minuto);
		
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * Metodo para registrar una nueva venta en la BD
	 * @param venta (Objeto del tipo Venta)
	 * @return response (Objeto tipo Response)
	 */
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
	

	/**
	 * Metodo para registrar un Mecanico a la plantilla de empleados
	 * @param mecanic (Objeto tipo Mecanico)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar un nuevo Comercial a la plantilla de empleados
	 * @param comercial (Objeto tipo Comercial)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar un nuevo DepartamentoCompras a la plantilla de empleados
	 * @param departamentoCompras (Objeto tipo DepartamentoCompras)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para registrar una nueva tarifa en la BD
	 * @param tarifa (Objeto del tipo Tarifa)
	 * @return response (Objeto tipo Response)
	 */
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
	
	
	/**
	 * Metodo para eliminar un cliente de la BD
	 * @param client (Objeto Cliente a eliminar)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para eliminar un coche del taller
	 * @param matricula (Matricula del coche)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para eliminar una solicitud de compra
	 * @param sol (Identificador de la solicitud)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("deleteSolicitudCompra")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteSolicitudCompra(String sol) 
	{
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		boolean b = BD.solicitudCompraDelete(st, sol);
		if (b) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * Metodo para eliminar una tarifa de la BD
	 * @param idTarifa (Identificador unico de la tarifa)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para eliminar un mecanico de la BD
	 * @param nickname (Nickname del Mecanico)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para eliminar un comercial de la BD
	 * @param nickname (Nickname del comercial)
	 * @return response (Objeto tipo Response)
	 */
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
	
	
	/**
	 * Metodo para eliminar a un departamento de compras
	 * @param nickname (Nickname del DepartamentoCompras)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtencion de un Cliente.
	 * @param nickname (Nickname del Cliente)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtencion de la Hora Temporal de un Empleado.
	 * @param nickname (Nickname del Empleado)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("selectHorasEmpleadosTemporal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectHorasEmpledoTemporalSelect(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		HorasEmpleados nuevo = BD.horaEmpleadoTemporalSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	/**
	 * Metodo para la obtencion de las Horas de un Empleado.
	 * @param nickname (Nickname del Empleado)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("selectHorasEmpleados")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectHorasEmpledoSelect(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		HorasEmpleados nuevo = BD.horaEmpleadoSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	/**
	 * Metodo para la obtencion de un Coche del Taller.
	 * @param matricula (Matricula del Coche)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtencion de un Coche del Concesionario.
	 * @param modelo (Modelo del Coche)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtecion de un Mecanico
	 * @param nickname (Nickname del Mecanico)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtecion de un Comercial
	 * @param nickname (Nickname del Comercial)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtecion de un Empleado del Departamento de Compras
	 * @param nickname (Nickname del Empledado del Departamento de Compras)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para la obtecion de una Pieza
	 * @param codigo (Codigo Identificativo de la Pieza)
	 * @return response (Objeto tipo Response)
	 */
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
	

	/**
	 * Metodo para registrar una pieza nueva en la BD
	 * @param pieza (Objeto del tipo Pieza)
	 * @return response (Objeto tipo Response)
	 */
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
		
		boolean bool = BD.piezasInsert(st, codigo, nombre, stock, ubicacion);
		BD.piezasUtilizadasInsert(st, codigo, nombre, 0, ubicacion);

		
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * Metodo para insertar nuevas herramientas en la BD 
	 * @param herramienta (Objeto tipo Herramienta)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertHerramientas")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response insertarHerramientas(HerramientasTaller herramienta) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = herramienta.getCodigo();
		String nombre = herramienta.getNombre();
		String ubicacion = herramienta.getUbicacion();
		int stock = herramienta.getUnidades();
		
		boolean bool =	BD.herramientasTallerInsert(st, codigo, nombre, stock, ubicacion);
		
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
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
	@Path("insertSolicitud")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registroSolicitud(SolicitudCompra solicitud) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = solicitud.getCodigo();
		String nombre = solicitud.getNombre();
		String tipo = solicitud.getTipo();
		int unidades = solicitud.getUnidades();
		
		boolean bool = BD.solicitudInsert(st, codigo, nombre, tipo, unidades);
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * Metodo para registrar una pieza utilizada
	 * @param pieza (Objeto del tipo Pieza)
	 * @return response (Objeto tipo Response)
	 */
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
	
	
	/**
	 * Metodo para eliminar una pieza utilizada
	 * @param codigo (Codigo de la Pieza)
	 * @return response (Objeto tipo Response)
	 */
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
	/**
	 * Metodo para cargar una coche del concesionario
	 * @return coches_result (Lista tipo CocheConcesionario)
	 */
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
	/**
	 * Metodo para cargar las solicitudes de compra de un mecanico
	 * @return sol_result (Lista tipo SolicitudCompra)
	 */
	@GET
	@Path("loadSolicitudTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SolicitudCompra> cargarTablaSolicitudCompra()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.solicitudTodasSelect(st);
		List<SolicitudCompra> sol_result = new ArrayList<SolicitudCompra>();
		
		if (rs == null) {
			return sol_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				String tipo = rs.getString("tipo");
				int unidades = rs.getInt("unidades");
				
				SolicitudCompra sol = new SolicitudCompra(codigo, nombre, tipo, unidades);	
				sol_result.add(sol);
			}
			 
			return sol_result;
		}
	}
	/**
	 * Metodo para cargar las piezas
	 * @return pieza_result (Lista tipo Pieza)
	 */
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
	
	 
	/**
	 * Metodo para cargar las herramientas de un taller
	 * @return herramienta_result (Lista tipo HerramientasTaller)
	 */
	@GET
	@Path("loadHerramientaTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HerramientasTaller> cargarTablaHerramientasTaller()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.herramientasTallerTodasSelect(st);
		List<HerramientasTaller> herramienta_result = new ArrayList<HerramientasTaller>();
		
		if (rs == null) {
			return herramienta_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("stock");
				String ubicacion = rs.getString("ubicacion");
				
				HerramientasTaller herramienta = new HerramientasTaller(codigo, nombre, unidades, ubicacion);	
				herramienta_result.add(herramienta);
			}
			 
			return herramienta_result;
		}
	}
	/**
	 * Metodo para cargar las piezas de los proveedores
	 * @return pieza_result (Lista tipo PiezaProveedores)
	 */
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
	
	/**
	 * Metodo para cargar las herramientas
	 * @return herramienta_result (Lista tipo Herramientas)
	 */
	@GET
	@Path("loadHerramientasList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Herramientas> cargarListaHerramientas()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.herramientasSelect(st);
		List<Herramientas> herramienta_result = new ArrayList<Herramientas>();
		
		if (rs == null) {
			return herramienta_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int tiempo = rs.getInt("tiempo");
				String tipo = rs.getString("tipo");
				String codProveedor = rs.getString("codProveedor");
				
				Herramientas pieza = new Herramientas(codigo, nombre, tiempo, tipo, codProveedor);	
				herramienta_result.add(pieza);
			}
			 
			return herramienta_result;
		}
	}
	/**
	 * Metodo para cargar los proveedores de piezas
	 * @return pieza_result (Lista tipo Proveedor)
	 */
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
				
				Proveedor proveedor = new Proveedor(codigo, nombre, pais, tipo);	
				pieza_result.add(proveedor);
			}
			return pieza_result;
		}
	}
	/**
	 * Metodo para cargar los proveedores de herramientas
	 * @return pieza_result (Lista tipo ProveedorHerramientas)
	 */
	@GET
	@Path("loadProveedoresHerramientaList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProveedorHerramientas> cargarProveedoresHerramientas()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.proveedoresHerramientasSelect(st);
		List<ProveedorHerramientas> pieza_result = new ArrayList<ProveedorHerramientas>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("idProveedor");
				String nombre = rs.getString("nombre");
				String pais = rs.getString("pais");
				String tipo = rs.getString("tipo");
				
				ProveedorHerramientas proveedor = new ProveedorHerramientas(codigo, nombre, pais, tipo);	
				pieza_result.add(proveedor);
			}
			return pieza_result;
		}
	}
	/**
	 * Metodo para cargar los coches de un taller
	 * @return coches_result (Lista tipo CocheTaller)
	 */
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
	
	/**
	 * Metodo para obtener el statement con la conexion a la BD
	 * @return st (Objeto tipo Statement)
	 */
	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}
	/**
	 * Metodo para cargar las piezas que han sido utilizadas 
	 * @return pieza_result (Lista tipo Pieza)
	 */
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
	/**
	 * Metodo para cargar las ventas realizadas
	 * @return venta_result (Lista tipo Venta)
	 */
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
	
	
	/**
	 * Metodo para cargar un coche matriculado
	 * @return cochesMatric_result (Lista tipo CocheMatriculado)
	 */
	@GET
	@Path("loadCochesMatricTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheMatriculado> cargarCochesMatriculadosTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.cochesMatricTodosSelect(st);
		List<CocheMatriculado> cochesMatric_result = new ArrayList<CocheMatriculado>();
		
		if (rs == null) {
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
	/**
	 * Metodo para cargar los empleados
	 * @return empleados_result (Lista tipo Empleado)
	 */
	@GET
	@Path("loadEmpleadosTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> cargarEmpleadoTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.empleadosTodasSelect(st);
		List<Empleado> empleados_result = new ArrayList<Empleado>();
		
		if (rs == null) {
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
	/**
	 * Metodo para cargar los presupuestos
	 * @return presupuestos_result (Lista tipo Presupuesto)
	 */
	@GET
	@Path("loadPresupuestosTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Presupuesto> cargarTablaPresupuesto()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.presupuestosTodosSelect(st);
		List<Presupuesto> presupuestos_result = new ArrayList<Presupuesto>();
		
		if (rs == null) {
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
	
	/**
	 * Metodo para la obtecion de un Presupuesto
	 * @param codigo (Codigo Identificativo del Presupuesto)
	 * @return response (Objeto tipo Response)
	 */
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
	/**
	 * Metodo para cargar las tarifas del taller
	 * @return tarifas_result (Lista tipo Tarifa)
	 */
	@GET
	@Path("loadTarifasTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tarifa> cargarTablaTarifas()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.tarifasTodosSelect(st);
		List<Tarifa> tarifas_result = new ArrayList<Tarifa>();
		
		if (rs == null) {
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
	
	/**
	 * Metodo para filtrar las Tarifas del taller con un precio maximo
	 * @param precio (Precio maximo de tarifa)
	 * @return response (Objeto tipo Response)
	 */
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(tarifas).build();
		}
	
	}
	
	/**
	 * Metodo para filtrar las Tarifas del taller con un precio minimo
	 * @param precio (Precio minimo de tarifa)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar las Tarifas con un maximo de horas de mano de obra
	 * @param horas (Numero maximo de horas de mano de obra)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar las Tarifas con un minimo de horas de mano de obra
	 * @param horas (Numero minimo de horas de mano de obra)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar las Ventas de Coches por Marca 
	 * @param marca (Nombre de la marca)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("loadTablaMarcaVentas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarVentaMarca(String marca) {
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
	
	/**
	 * Metodo para filtrar las Ventas de Coches por Modelo
	 * @param modelo (Nombre del modelo)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los las Ventas por Comercial
	 * @param comercial (Nickname del comercial)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los Presupuestos a partir de su codigo
	 * @param codigo (Codigo del Presupuesto)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los Presupuestos a partir del nombre del cliente
	 * @param cliente (Nickname del cliente)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los Presupuestos a partir del problema o averia
	 * @param problema (Problema o averia de la reparacion del Presupuesto)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los CocheConcesionario a traves de un filtro
	 * @param filtro (Filtro de busqueda: modelo, marca...)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los CocheMatriculado a traves de un filtro
	 * @param filtro (Filtro de busqueda: modelo, marca...)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar los Coches del Taller a traves de un filtro
	 * @param filtro (Filtro de busqueda: matricula, marca...)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar las Herramientas a traves de un filtro
	 * @param filtro (Filtro de busqueda: codigo, nombre...)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("loadTablaHerramientaMecanicoFiltro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response filtrarHerramientaMecanicoFiltro(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);

		String [] parts = filtro.split("-");
		String restriccion = parts[0];
		int tipo = Integer.parseInt(parts[1]);
		
		ResultSet rs = BD.herramientasMecanicoFiltroSelect(st, tipo, restriccion);
		List<HerramientasTaller> herramientas = new ArrayList<HerramientasTaller>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String cod = rs.getString("codigo");
					String nombre = rs.getString("nombre");
					int unidades = rs.getInt("stock");
					String ubicacion = rs.getString("ubicacion");
					HerramientasTaller herramienta = new HerramientasTaller(cod, nombre, unidades, ubicacion);
					herramientas.add(herramienta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(herramientas).build();
		}
	}
	
	/**
	 * Metodo para filtrar las Piezas de los Mecanicos
	 * @param filtro (Filtro de busqueda: codigo, nombre...)
	 * @return response (Objeto tipo Response)
	 */
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
	
	/**
	 * Metodo para filtrar las Piezas que han sido utilizadas
	 * @param filtro (Filtro de busqueda: codigo, nombre, unidades...)
	 * @return response (Objeto tipo Response)
	 */
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
	/**
	 * Metodo para cargar la fidelidad de los distintos clientes
	 * @return clientes (Lista tipo ClienteFidelidad)
	 */
	@GET
	@Path("loadClientesFidelidadTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteFidelidad> cargarTablaClienteFidelidad()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.fidelidadSelect(st);
		List<ClienteFidelidad> clientes = new ArrayList<ClienteFidelidad>();
		
		if (rs == null) {
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String dni = rs.getString("dniCliente");
				int fidelidad = rs.getInt("fidelidad");
				ClienteFidelidad cliente = new ClienteFidelidad(dni, fidelidad);
				clientes.add(cliente);
			}
		}
		return clientes;
	}
	/**
	 * Metodo para cargar los comerciales
	 * @return comerciales (Lista tipo Comercial)
	 */
	@GET
	@Path("loadComercialTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comercial> cargarComercialTable()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.comercialesTodasSelect(st);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		
		if (rs == null) {
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String dni = rs.getString("dni");
				String nick = rs.getString("nickname");
				String contrasenya = rs.getString("contrasenia");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				String ciudad = rs.getString("ciudad");
				int codigoPostal = rs.getInt("codigoPostal");
				String direccion = rs.getString("dir");
				String numeroTelefono = rs.getString("numTelefono");
				String NSS = rs.getString("NSS");
				String numeroCuenta = rs.getString("numeroCuenta");
				int sueldo = rs.getInt("sueldo");
				int cochesVendidos = rs.getInt("cochesVendidos");
				int importeObtenido = rs.getInt("importeObtenido");
				int horas = rs.getInt("horas");
				Comercial comercial = new Comercial(nick, contrasenya, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, 1, cochesVendidos, importeObtenido, horas);
				comerciales.add(comercial);
			}
		}
		return comerciales;
	}
	/**
	 * Metodo para cargar los mecanicos
	 * @return mecanicos (Lista tipo Mecanico)
	 */
	@GET
	@Path("loadMecanicoTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mecanico> cargarMecanicoTable()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.mecanicosTodasSelect(st);
		List<Mecanico> mecanicos = new ArrayList<Mecanico>();
		
		if (rs == null) {
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String dni = rs.getString("dni");
				String nick = rs.getString("nickname");
				String contrasenya = rs.getString("contrasenia");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				String ciudad = rs.getString("ciudad");
				int codigoPostal = rs.getInt("codigoPostal");
				String direccion = rs.getString("dir");
				String numeroTelefono = rs.getString("numTelefono");
				String NSS = rs.getString("NSS");
				String numeroCuenta = rs.getString("numeroCuenta");
				int sueldo = rs.getInt("sueldo");
				int horas = rs.getInt("horas");
				Mecanico mecanico = new Mecanico(nick, contrasenya, 1, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, horas);
				mecanicos.add(mecanico);
			}
		}
		return mecanicos;
	}
	
	/**
	 * Metodo para seleccionar una cita con los comerciales de la BD
	 * @param restriccion (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("selectCitaComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citaComercialSelect(String restriccion)throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = restriccion.split(";");
		String fecha = strings[0];
		String hora = strings [1];
		String comercial = strings[2];
		
		CitaComercial nuevo = BD.citaComercialSelect(st, fecha, hora, comercial);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	/**
	 * Metodo para registrar nuevas citas con los comerciales
	 * @param citaComercial (Objeto tipo CitaComercial)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertCitaComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarCitaComercial(CitaComercial citaComercial) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		boolean bool = BD.CitaComercialInsert(st, citaComercial.getNombre(), citaComercial.getDniCliente(), citaComercial.getFecha(), citaComercial.getHora(), citaComercial.getComercial());
		
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	/**
	 * Metodo para seleccionar nuevas Citas de Taller con los Mecanicos
	 * @param restriccion (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("selectCitaTaller")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citaTallerSelect(String restriccion)throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = restriccion.split(";");
		String fecha = strings[0];
		String hora = strings [1];
		String mecanico = strings[2];
		
		CitaTaller nuevo = BD.citaTallerSelect(st, fecha, hora, mecanico);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	/**
	 * Metodo para registrar nuevas Citas de Taller con los Mecanicos
	 * @param citaTaller (Objeto tipo CitaTaller)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("insertCitaTaller")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarCitaComercial(CitaTaller citaTaller) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		boolean bool = BD.CitaTallerInsert(st, citaTaller.getNombre(), citaTaller.getDniCliente(), citaTaller.getFecha(), citaTaller.getHora(), citaTaller.getComercial(), citaTaller.getProblema());
		
		if (bool) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	/**
	 * Metodo para cargar las Citas con un comercial
	 * @param filtro (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("loadTablaCitaComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citasComercialesCargar(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.citasDelComercialSelect(st, filtro);
		List<CitaComercial> citas = new ArrayList<CitaComercial>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String nombre = rs.getString("nombre");
	 				String dniCliente = rs.getString("dniCliente");
	 				String f	= rs.getString("fecha");
	 				String h = rs.getString("hora");
	 				String c = rs.getString("comercial");
	 				CitaComercial citaComercial = new CitaComercial(nombre, dniCliente, f, h, c);
					citas.add(citaComercial);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(citas).build();
		}
	}
	/**
	 * Metodo para filtrar las Citas de un comercial
	 * @param filtro (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("filtrarTablaCitaComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citasComercialesFiltrar(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = filtro.split(";");
		String nickname = strings[0];
		String fecha = strings [1];
		
		ResultSet rs = BD.filtrarCitasPorFecha(st, nickname, fecha);
		List<CitaComercial> citas = new ArrayList<CitaComercial>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String nombre = rs.getString("nombre");
	 				String dniCliente = rs.getString("dniCliente");
	 				String f	= rs.getString("fecha");
	 				String h = rs.getString("hora");
	 				String c = rs.getString("comercial");
	 				CitaComercial citaComercial = new CitaComercial(nombre, dniCliente, f, h, c);
					citas.add(citaComercial);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(citas).build();
		}
	}
	/**
	 * Metodo para cargar las Citas de Taller con los Mecanicos
	 * @param filtro (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("loadTablaCitaMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citasMecanicosCargar(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.citasDelMecanicoSelect(st, filtro);		
		List<CitaTaller> citas = new ArrayList<CitaTaller>();

		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String nombre = rs.getString("nombre");
	 				String dniCliente = rs.getString("dniCliente");
	 				String f	= rs.getString("fecha");
	 				String h = rs.getString("hora");
	 				String c = rs.getString("mecanico");
	 				String m = rs.getString("problema");
	 				CitaTaller citaMecanico = new CitaTaller(nombre, dniCliente, f, h, c, m);
					citas.add(citaMecanico);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(citas).build();
		}
	}
	/**
	 * Metodo para filtrar las Citas de Taller con los Mecanicos
	 * @param filtro (Objeto tipo String)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("filtrarTablaCitaMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response citasMecanicosFiltrar(String filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = filtro.split(";");
		String nickname = strings[0];
		String fecha = strings [1];
		
		ResultSet rs = BD.filtrarCitasMecanicoPorFecha(st, nickname, fecha);
		List<CitaTaller> citas = new ArrayList<CitaTaller>();
		
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String nombre = rs.getString("nombre");
	 				String dniCliente = rs.getString("dniCliente");
	 				String f	= rs.getString("fecha");
	 				String h = rs.getString("hora");
	 				String c = rs.getString("mecanico");
	 				String m = rs.getString("problema");
	 				CitaTaller citaMecanico = new CitaTaller(nombre, dniCliente, f, h, c, m);
					citas.add(citaMecanico);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(citas).build();
		}
	}
	
	/**
	 * Metodo para cargar las horas trabajadas de un empleado
	 * @param filtro (Objeto tipo int)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("cargarEmpleadoHoras")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response cargarEmpleadoHoras(int filtro) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.empleadosHorasFiltroSelect(st, filtro);
		List<EmpleadoHoras> empleadosHoras = new ArrayList<EmpleadoHoras>();
		
		if (rs == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			try {
				while(rs.next()) {
					String nickname = rs.getString("nickname");
					String nombre = rs.getString("nombre");
					String dni = rs.getString("dni");
					int horas = rs.getInt("horas");
					EmpleadoHoras empleadoHoras = new EmpleadoHoras(nickname, nombre, dni, horas);
					empleadosHoras.add(empleadoHoras);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Response.status(Response.Status.OK).entity(empleadosHoras).build();
		}
	}
	
	/**
	 * Metodo para eliminar un registro de las horas de los empleados
	 * @param nickname (Nickname del empleado)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("deleteHorasEmpleados")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteHorasEmpleados(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.horasEmpleadoDelete(st, nickname);
		HorasEmpleados nuevo = BD.horaEmpleadoSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * Metodo para eliminar un registro de las horas de los empleados temporal
	 * @param nickname (Nickname del empleado)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("deleteHorasEmpleadosTemporal")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteHorasEmpleadosTemporal(String nickname) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.horasEmpleadoTemporarlDelete(st, nickname);
		HorasEmpleados nuevo = BD.horaEmpleadoTemporalSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	/**
	 * Metodo para eliminar una cita con un comercial
	 * @param nickFecha (Nickname;fecha de la cita)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("deleteCitasComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteCitasComercial(String nickFecha) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = nickFecha.split(";");
		String nickname = strings[0];
		String fecha = strings [1];
		
		BD.citasComercialDelete(st, nickname, fecha);
		ResultSet rs = BD.filtrarCitasPorFecha(st, nickname, fecha);
		
		BD.cerrarBD(con, st);
		
		if (rs == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	/**
	 * Metodo para eliminar las Citas de Taller con los Mecanicos
	 * @param nickFecha (Nickname;fecha de la cita)
	 * @return response (Objeto tipo Response)
	 */
	@POST
	@Path("deleteCitasMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteCitasMecanico(String nickFecha) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String [] strings = nickFecha.split(";");
		String nickname = strings[0];
		String fecha = strings [1];
		
		BD.citasMecanicoDelete(st, nickname, fecha);
		ResultSet rs = BD.filtrarCitasMecanicoPorFecha(st, nickname, fecha);
		
		BD.cerrarBD(con, st);
		
		if (rs == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}




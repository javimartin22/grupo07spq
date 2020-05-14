package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.EmpleadoHoras;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;
import concesionario.datos.Venta;
/**
 * Controller GerenteController (Controller para las clases de Gerente)
 */
public class GerenteController {
	private ClienteApp cliente;
	/**
	 * Constructor de la Clase Gerente Controller.
	 * @param clienteApp (Objeto de la clase ClienteApp).
	 */
	public GerenteController(ClienteApp clienteApp) {
		this.cliente = clienteApp;
	}
	/**
	 * Metodo para inicializar el ClienteApp
	 * @return cliente ClienteApp
	 */
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	/**
	 * Metodo para cargar los Empleados.
	 * @return List (Lista de Empleados).
	 */
	public List<Empleado> cargarTablaEmpleado(){
		return cliente.cargarTablaEmpleados();
	}
	/**
	 * Metodo para cargar las Ventas.
	 * @return List (Lista de Ventas)
	 */
	public List<Venta> cargarTablaVenta(){
		return cliente.cargarTablaVenta();
	}
	/**
	 * Metodo para el registro de un nuevo Mecanico.
	 * @param mecanic (Objeto Mecanico que se desea registrar)
	 * @return Boolean (True en caso de que sea correcto / False en caso de que sea incorrecto)
	 */
	public boolean registroMecanico(Mecanico mecanic) {
		Response response = cliente.registroMecanico(mecanic);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para el registro de un nuevo Comercial.
	 * @param comercial (Objeto Comercial que se desea registrar)
	 * @return Boolean (True en caso de que sea correcto / False en caso de que sea incorrecto)
	 */
	public boolean registroComercial(Comercial comercial) {
		Response response = cliente.registroComercial(comercial);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para el registro de un nuevo Empleado del Departamento de Compras.
	 * @param dep (Objeto DepartamentoCompras que se desea registrar)
	 * @return Boolean (True en caso de que sea correcto / False en caso de que sea incorrecto)
	 */
	public boolean registroDepartamentoCompras(DepartamentoCompras dep) {
		Response response = cliente.registroDepartamentoCompras(dep);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para el registro de una nueva Tarifa.
	 * @param tarifa (Objeto Tarifa que se desea registrar)
	 * @return Boolean (True en caso de que sea correcto / False en caso de que sea incorrecto)
	 */
	public boolean registroTarifa(Tarifa tarifa) {
		Response response1 = cliente.registroTarifa(tarifa);
		if(response1.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para la eliminacion de un Mecanico
	 * @param nickname (Nickname del Mecanico)
	 * @return Mecanico (Objeto Mecanico)
	 */
	public boolean eliminarMecanico(String nickname) {
		Response response = cliente.mecanicoDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para la eliminacion de un Comercial
	 * @param nickname (Nickname del Comercial)
	 * @return Comercial (Objeto Comercial)
	 */
	public boolean eliminarComercial(String nickname) {
		Response response = cliente.comercialDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para la eliminacion de un Empleado del Departamento de Compras
	 * @param nickname (Nickname del Empleado)
	 * @return DepartamentoCompras (Objeto DepartamentoCompras)
	 */
	public boolean eliminarDepartamentoCompras(String nickname) {
		Response response = cliente.departamentoComprasDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para la obtencion de un Mecanico
	 * @param nickname (Nickname del Mecanico)
	 * @return Mecanico (Objeto Mecanico)
	 */
	public Mecanico seleccionarMecanico(String nickname) {
		Response response = cliente.mecanicoSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Mecanico.class);
		} else {
			return null;
		}
	}
	/**
	 * Metodo para la obtencion de un Comercial
	 * @param nickname (Nickname del Comercial)
	 * @return Comercial (Objeto Comercial)
	 */
	public Comercial seleccionarComercial(String nickname) {
		Response response = cliente.comercialSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Comercial.class);
		} else {
			return null;
		}
	}
	/**
	 * Metodo para la obtencion de un Empleado del Departamento de Compras
	 * @param nickname (Nickname del Empleado)
	 * @return DepartamentoCompras (Objeto DepartamentoCompras)
	 */
	public DepartamentoCompras seleccionarDepartamentoCompras(String nickname) {
		Response response = cliente.departamentoComprasSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(DepartamentoCompras.class);
		} else {
			return null;
		}
	}
	/**
	 * Metodo para cargar las Tarifas
	 * @return List (Lista de Tarifas)
	 */
	public List<Tarifa> cargarTablaTarifas(){
		return cliente.cargarTablaTarifas();
	}

	public boolean eliminarTarifa(String idTarifa) {
		Response response = cliente.tarifaDelete(idTarifa);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para renombrar el tipo de Empleado.
	 * @param tipoEmpleado (Int que identifica el tipo de Empleado proveniente del JComboBox).
	 * @return tipo (Tipo renombrado y en texto)
	 */
	public String cambioTipo(int tipoEmpleado) {
		String tipo = "";
		switch (tipoEmpleado) {
		case 0:
			tipo = "Mecanico";
			break;
		case 1: 
			tipo = "Comercial";
			break;
		case 2:
			tipo = "Departamento Compras";
			break;
		}
		return tipo;
	}
	/**
	 * Metodo para renombrar el Sexo de los Empleados.
	 * @param s (Sexo del Empleado)
	 * @return sexo (Sexo en texto)
	 */
	public String comprobarSexo(int s){
		String sexo = "";
		switch (s) {
		case 0:
			sexo = "Hombre";
			break;
		case 1: 
			sexo = "Mujer";
			break;
		case 2: 
			sexo = "Otro";
			break;
		}
		return sexo;
	}
	/**
	 * Metodo para cargar las horas de los Empleados.
	 * @param filtro (Filtro para indentificar los tipos de Empleados)
	 * @return List (Lista de EmpleadoHoras)
	 */
	public List<EmpleadoHoras> cargarEmpleadoHoras(int filtro){
		Response response = cliente.cargarEmpleadoHoras(filtro);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<EmpleadoHoras>> genericType = new GenericType<List<EmpleadoHoras>>() {};
			return response.readEntity(genericType);
		} else {
			return null;
		}
	}
	
}

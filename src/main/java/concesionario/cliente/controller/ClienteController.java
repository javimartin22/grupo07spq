package concesionario.cliente.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.CitaComercial;
import concesionario.datos.CitaTaller;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Comercial;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;

/**
 * Controller ClienteController (Controller para las clases de Cliente)
 */
public class ClienteController {

	private ClienteApp cliente;
	/**
	 * Constructor de la clase ClienteController
	 * @param clienteApp ClienteApp
	 */
	public ClienteController(ClienteApp clienteApp) {
		this.cliente = clienteApp;
	}
	/**
	 * Metodo para inicializar el ClienteApp
	 * @return clienteApp ClienteApp
	 */
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	/**
	 * Metodo para cambiar contrasena del cliente
	 * @param client Objeto cliente al que se desea cambiar la contrasena
	 * @param contrasenia Contrasena nueva
	 * @return boolean Devuelve true si el proceso fue exitoso, false si no fue posible
	 */
	public boolean cambiarContraseniaCliente(Cliente client, String contrasenia) {
		Response response = cliente.cambiarContraseniaCliente(client, contrasenia);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para cambiar nickname del cliente.
	 * @param client Objeto cliente al que se desea cambiar el nickname
	 * @param nickname Nickname nuevo
	 * @return boolean Devuelve true si el proceso fue exitoso, false si no fue posible
	 */
	public boolean cambiarNicknameCliente(Cliente client, String nickname) {
		Response response = cliente.cambiarNicknameCliente(client, nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo que devuelve un objeto tipo Cliente a partir de su nickname
	 * @param nickname Nickname del cliente
	 * @return Cliente Objeto Cliente con el nickname suministrado
	 */
	public Cliente seleccionarCliente(String nickname) {
		Response response = cliente.clienteSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Cliente.class);
		} else {
			return null;
		}
	}
	/**
	 * Metodo que comprueba si existe una cita comercial con una restriccion 
	 * @param restriccion Filtro para obtener citas de una fecha o con un determinado comercial
	 * @return boolean Devuelve true si existe una cita con los parámetros indicados, false si no existe ninguna
	 */
	public boolean comprobarCitaComercial(String restriccion) {
		Response response = cliente.seleccionarCitaComercial(restriccion);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Metodo que carga la oferta de coches que hay en el concesionario 
	 * @return List<CocheConcesionario> Lista de tipo CocheConcesionario
	 */
	public List<CocheConcesionario> cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	/**
	 * Metodo que obtiene un coche determinado a partir del modelo suministrado 
	 * @param modelo Nombre del modelo del coche a obtener
	 * @return CocheConcesionario Objeto tipo CocheConcesionario
	 */
	public CocheConcesionario seleccionarCocheConcesionario(String modelo) {
		Response response = cliente.seleccionarCocheConcesionario(modelo);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(CocheConcesionario.class);
		} else {
			return null;
		}
	}
	/**
	 * Metodo que carga la oferta de tarifas con un precio maximo
	 * @param precio Precio maximo
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<Tarifa> filtrarTarifaPrecio(int precio) {
		Response response = cliente.filtrarTarifaPrecio(precio);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			List<Tarifa> tarifas = response.readEntity(genericType);
			return tarifas;
		} else {
			return null;
		}
	}
	/**
	 * Metodo que carga la oferta de tarifas a partir de un precio minimo
	 * @param precio Precio minimo
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<Tarifa> filtrarTarifaPrecioMin(int precio) {
		Response response = cliente.filtrarTarifaPrecioMin(precio);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			List<Tarifa> tarifas = response.readEntity(genericType);
			return tarifas;
		} else {
			return null;
		}
	}
	/**
	 * Metodo que carga la oferta de tarifas con un maximo de horas de mano de obra
	 * @param horas	Maximo de horas de mano de obra de estimacion del esfuerzo del servicio
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<Tarifa> filtrarTarifaHorasMax(int horas) {
		Response response = cliente.filtrarTarifaHorasMax(horas);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			return response.readEntity(genericType);
		} else {
			return null;
		}
	}
	/**
	 * Metodo que carga la oferta de tarifas a partir de un minimo de horas de mano de obra
	 * @param horas	Minimo de horas de mano de obra de estimacion del esfuerzo del servicio
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<Tarifa> filtrarTarifaHorasMin(int horas) {
		Response response = cliente.filtrarTarifaHorasMin(horas);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			return response.readEntity(genericType);
		} else {
			return null;
		}
	}
	/**
	 * Metodo que carga la oferta de todas las tarifas disponibles
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<Tarifa> cargarTablaTarifas(){
		return cliente.cargarTablaTarifas();
	}
	/**
	 * Metodo que carga la oferta de coches del concesionario en funcion de un filtro
	 * @param filtro Filtro a partir de distintos parametros: marca, precio, matricula
	 * @return List<Tarifa> Lista de objetos tipo Tarifa
	 */
	public List<CocheConcesionario> filtrarCocheConcesionario(String filtro) {
		Response response = cliente.filtrarCocheConcesionario(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	/**
	 * Metodo que registra un cliente en la aplicacion
	 * @param client Objeto del tipo Cliente a registrar
	 * @return boolean Devuelve true si el proceso ha sido exitoso, false si no fue posible
	 */
	public boolean registroCliente(Cliente client) {
		Response response = cliente.registroCliente(client);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo que registra una cita con un comercial
	 * @param citaComercial Objeto tipo CitaComercial de la cita creada
	 * @return boolean Devuelve true si el proceso ha sido exitoso, false si no fue posible
	 */
	public boolean registroCitaComercial(CitaComercial citaComercial) {
		Response response = cliente.registroCitaComercial(citaComercial);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo que carga los nombres de los comerciales disponibles
	 * @return List<String> Nombres de los comerciales disponibles
	 */
	public List<String> cargarTablaComercial(){
		List<Comercial> comerciales = cliente.cargarTablaComercial();
		List<String> nombres = new ArrayList<String>();
		for (Comercial comercial : comerciales) {
			nombres.add(comercial.getNombre());
		}
		return nombres;
	}
	/**
	 * Metodo que comprueba si existe una cita de taller con una restriccion 
	 * @param restriccion Filtro para obtener citas de una fecha o con un determinado mecanico
	 * @return boolean Devuelve true si existe una cita con los parámetros indicados, false si no existe ninguna
	 */
	public boolean comprobarCitaTaller(String restriccion) {
		Response response = cliente.seleccionarCitaTaller(restriccion);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Metodo que registra una cita de taller con un mecanico
	 * @param citaTaller Objeto tipo CitaTaller de la cita creada
	 * @return boolean Devuelve true si el proceso ha sido exitoso, false si no fue posible
	 */
	public boolean registroCitaTaller(CitaTaller citaTaller) {
		Response response = cliente.registroCitaTaller(citaTaller);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo que carga los nombres de los mecanicos disponibles
	 * @return List<String> Nombres de los mecanicos disponibles
	 */
	public List<String> cargarTablaMecanicos(){
		List<Mecanico> mecanicos = cliente.cargarTablaMecanico();
		List<String> nombres = new ArrayList<String>();
		for (Mecanico comercial : mecanicos) {
			nombres.add(comercial.getNombre());
		}
		return nombres;
	}
	/**
	 * Metodo para la creacion de un listado de horas para el Concesionario.
	 * @return List (Lista de las horas).
	 */
	public List<String> crearHorasConcesionario(){
		List<String> horas = new ArrayList<String>();
		for (int i = 0; i <= 2; i++) {
			int a = 10 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		} 
		for (int i = 0; i <= 3; i++) {
			int a = 16 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		}
		return horas;
	}
	/**
	 * Metodo para la creacion de un listado de horas para el Taller.
	 * @return List (Lista de las horas).
	 */
	public List<String> crearHorasTaller(){
		List<String> horas = new ArrayList<String>();
		for (int i = 0; i <= 2; i++) {
			int a = 10 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		} 
		for (int i = 0; i <= 4; i++) {
			int a = 15 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		}
		return horas;
	}
}

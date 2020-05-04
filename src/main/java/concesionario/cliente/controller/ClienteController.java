package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Tarifa;

public class ClienteController {

	private ClienteApp cliente;

	public ClienteController(ClienteApp clienteApp) {
		this.cliente = clienteApp;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public boolean cambiarContraseniaCliente(Cliente client, String contrasenia) {
		Response response = cliente.cambiarContraseniaCliente(client, contrasenia);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean cambiarNicknameCliente(Cliente client, String nickname) {
		Response response = cliente.cambiarNicknameCliente(client, nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Cliente seleccionarCliente(String nickname) {
		Response response = cliente.clienteSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Cliente.class);
		} else {
			return null;
		}
	}
	
	public boolean comprobarCitaComercial(String restriccion) {
		Response response = cliente.seleccionarCitaComercial(restriccion);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<CocheConcesionario> cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	
	public CocheConcesionario seleccionarCocheConcesionario(String modelo) {
		Response response = cliente.seleccionarCocheConcesionario(modelo);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(CocheConcesionario.class);
		} else {
			return null;
		}
	}
	
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
	
	public List<Tarifa> filtrarTarifaHorasMax(int horas) {
		Response response = cliente.filtrarTarifaHorasMax(horas);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			return response.readEntity(genericType);
		} else {
			return null;
		}
	}
	
	public List<Tarifa> filtrarTarifaHorasMin(int horas) {
		Response response = cliente.filtrarTarifaHorasMin(horas);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
			return response.readEntity(genericType);
		} else {
			return null;
		}
	}
	
	public List<Tarifa> cargarTablaTarifas(){
		return cliente.cargarTablaTarifas();
	}
	
	public List<CocheConcesionario> filtrarCocheConcesionario(String filtro) {
		Response response = cliente.filtrarCocheConcesionario(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public boolean registroCliente(Cliente client) {
		Response response = cliente.registroCliente(client);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
}

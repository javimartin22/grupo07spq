package concesionario.cliente.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;

public class MecanicoController {
	
	public ClienteApp cliente;
	
	public MecanicoController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public boolean registrarCocheTaller(CocheTaller cocheTaller) {
		Response response = cliente.registrarCocheTaller(cocheTaller);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	
	public Response seleccionarPiezaUtilizada(String codigo) {
		return cliente.piezaUtilizadaSelect(codigo);
	}
	
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	
	public List<CocheMatriculado> cargarCochesMatriculados(){
		return cliente.cargarTablaCochesCocheMatriculados();
	}
	
	public List<CocheTaller> cargarTablaCocheTaller(){
		return cliente.cargarTablaCocheTaller();
	}
	
	public boolean deleteCocheTaller(String matricula) {
		Response response = cliente.CocheTallerDelete(matricula); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroPresupuesto(Presupuesto presupuesto) {
		Response response = cliente.registroPresupuesto(presupuesto); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public CocheTaller seleccionarCocheTaller(String matricula) {
		Response response = cliente.seleccionarCocheTaller(matricula);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(CocheTaller.class);
		} else {
			return null;
		}
	}
	
	public boolean cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		Response response = cliente.cambiarEstadoCocheTaller(coche, estado); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Presupuesto> cargarTablaPresupuesto(){
		return cliente.cargarTablaPresupuestos();
	}
	
	public Presupuesto seleccionarPresupuesto(String codigo) {
		Response response = cliente.seleccionarPresupuesto(codigo); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Presupuesto.class);
		} else {
			return null;
		}
	}
	
	public List<Presupuesto> filtrarPresupuestoCodigo(String codigo) {
		Response response_1 = cliente.filtrarPresupuestoCodigo(codigo);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	
	public List<Presupuesto> filtrarPresupuestoCliente(String client) {
		Response response_1 = cliente.filtrarPresupuestoCliente(client);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Presupuesto> filtrarPresupuestoProblema(String problema) {
		Response response_1 = cliente.filtrarPresupuestoProblema(problema);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CocheMatriculado> filtrarCocheMatriculado(String filtro) {
		Response response = cliente.filtrarCocheMatriculado(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheMatriculado>> genericType = new GenericType<List<CocheMatriculado>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Pieza> filtrarPiezaMecanico(String filtro) {
		Response response = cliente.filtrarPiezaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CocheTaller> filtrarCocheTaller(String filtro) {
		Response response = cliente.filtrarCocheTaller(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheTaller>> genericType = new GenericType<List<CocheTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	public String parseFecha() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE); 
		return fecha;
	}
	
	public String crearCodigo() {
		String codigo = "";
		List<Presupuesto> presupuestos = cargarTablaPresupuesto();
		if (presupuestos.size() != 0) {
			int numero = presupuestos.size() + 1;
			codigo = "P" + numero;
		} else {
			codigo = "P1";
		}
		return codigo;
	}
	
	public String traducirEstado(int estado) {
		String estad = "";
		switch (estado) {
		case 0:
			estad = "Sin Empezar";
			break;
		case 1:
			estad = "En proceso";
			break;
		case 2:
			estad = "Terminado";
			break;
		}
		return estad;
	}
	
	public String parseFechaPresupuesto() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " - Bilbao"; 
		return fecha;
	}
}

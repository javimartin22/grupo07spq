package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;
import concesionario.datos.Venta;

public class GerenteController {
	private ClienteApp cliente;

	public GerenteController(ClienteApp clienteApp) {
		this.cliente = clienteApp;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public List<Empleado> cargarTablaEmpleado(){
		return cliente.cargarTablaEmpleados();
	}
	
	public List<Venta> cargarTablaVenta(){
		return cliente.cargarTablaVenta();
	}
	
	public boolean registroMecanico(Mecanico mecanic) {
		Response response = cliente.registroMecanico(mecanic);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroComercial(Comercial comercial) {
		Response response = cliente.registroComercial(comercial);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroDepartamentoCompras(DepartamentoCompras dep) {
		Response response = cliente.registroDepartamentoCompras(dep);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroTarifa(Tarifa tarifa) {
		Response response1 = cliente.registroTarifa(tarifa);
		if(response1.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eliminarMecanico(String nickname) {
		Response response = cliente.mecanicoDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eliminarComercial(String nickname) {
		Response response = cliente.comercialDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eliminarDepartamentoCompras(String nickname) {
		Response response = cliente.departamentoComprasDelete(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Mecanico seleccionarMecanico(String nickname) {
		Response response = cliente.mecanicoSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Mecanico.class);
		} else {
			return null;
		}
	}
	
	public Comercial seleccionarComercial(String nickname) {
		Response response = cliente.comercialSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Comercial.class);
		} else {
			return null;
		}
	}
	
	public DepartamentoCompras seleccionarDepartamentoCompras(String nickname) {
		Response response = cliente.departamentoComprasSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(DepartamentoCompras.class);
		} else {
			return null;
		}
	}
	
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
	
	
}

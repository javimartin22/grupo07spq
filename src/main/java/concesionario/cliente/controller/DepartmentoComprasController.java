package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Proveedor;
import concesionario.datos.ProveedorHerramientas;

public class DepartmentoComprasController {
	private ClienteApp cliente;

	public DepartmentoComprasController(ClienteApp clienteApp) {
		this.cliente = clienteApp;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public boolean registroPieza(Pieza pieza) {
		Response response = cliente.registroPieza(pieza); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroHerramienta(HerramientasTaller herramienta) {
		Response response = cliente.registroHerramienta(herramienta); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	
	public List<HerramientasTaller> cargarHerramientas(){
		return cliente.cargarTablaHerramientasTaller();
	}
	
	
	public Pieza seleccionarPiezaUtilizada(String codigo) {
		Response response = cliente.piezaUtilizadaSelect(codigo);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Pieza.class);
		} else {
			return null;
		} 
	}
	
	
	
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	
	public List<Pieza> filtrarPiezaUtilizadas(String filtro) {
		Response response = cliente.filtrarPiezaUtilizadas(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<HerramientasTaller> filtrarHerramientaMecanico(String filtro) {
		Response response = cliente.filtrarHerramientaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<HerramientasTaller>> genericType = new GenericType<List<HerramientasTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Proveedor> cargarListaProveedores(){
		return cliente.cargarListaProveedores();
	}
	
	
	public List<ProveedorHerramientas> cargarListaProveedoresHerramientas(){
		return cliente.cargarListaProveedoresHerramientas();
	}
	
	
	public List<PiezaProveedores> cargarListaPiezasProveedores(){
		return cliente.cargarListaPiezasProveedores();
	}
	
	
	public List<Herramientas> cargarListaHerramientas(){
		return cliente.cargarListaHerramientas();
	}
	
	
	
	public String parseUbicacion(int ubicacion) {
		String ub = "";
		switch (ubicacion) {
		case 0:
			ub = "Alamacen 1 - Estanteria 1";
			break;
		case 1:
			ub = "Alamacen 1 - Estanteria 2";
			break;
		case 2:
			ub = "Alamacen 1 - Estanteria 3";
			break;
		case 3:
			ub = "Alamacen 2 - Estanteria 1";
			break;
		case 4:
			ub = "Alamacen 2 - Estanteria 2";
			break;
		case 5:
			ub = "Alamacen 2 - Estanteria 3";
			break;
		}
		return ub;
	}
	
	
	
	public String carlcularCodigo(List<Pieza> piezas) {
		int numero = piezas.size() + 1;
		return "PI-" + numero;
	}
	
	public String calcularCodigoHerramienta(List<HerramientasTaller> herramientas) {
		int numero = herramientas.size() + 1;
		return "H" + numero;
	}
}

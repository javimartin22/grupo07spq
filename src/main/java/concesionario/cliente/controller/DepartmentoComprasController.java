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
import concesionario.datos.SolicitudCompra;

/**
 * Controller DepartmentoComprasController (Controller para las clases de Departamento Compras)
 */
public class DepartmentoComprasController {
	private ClienteApp cliente;

	/**
	 * Contructor de la clase DepartmentoComprasController
	 * @param clienteApp ClienteApp
	 */
	public DepartmentoComprasController(ClienteApp clienteApp) {
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
	 * Metodo para registrar una Pieza
	 * @param pieza (Pieza que se va a registrar)
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroPieza(Pieza pieza) {
		Response response = cliente.registroPieza(pieza); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para registrar una Herramienta
	 * @param herramienta (Herramienta que se va a registrar)
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroHerramienta(HerramientasTaller herramienta) {
		Response response = cliente.registroHerramienta(herramienta); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Metodo para obtener todas las piezas que se encuentran en la BD.
	 * @return List (Lista de Piezas)
	 */
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	/**
	 * Metodo para obtener todas las Herramientas que se encuentran en la BD.
	 * @return List (Lista de Herramientas)
	 */
	public List<HerramientasTaller> cargarHerramientas(){
		return cliente.cargarTablaHerramientasTaller();
	}
	/**
	 * Metodo para obtener todas las Solicitudes que se encuentran en la BD.
	 * @return List (Lista de Solicitudes)
	 */
	public List<SolicitudCompra> cargarSolicitud(){
		return cliente.cargarTablaSolicitudCompra();
	}
	/**
	 * Metodo para la obtencion de una Pieza Utilizada.
	 * @param codigo (Codigo de la pieza que se desea obtener)
	 * @return pieza (Pieza seleccionada si existe / Null si no existe)
	 */
	public Pieza seleccionarPiezaUtilizada(String codigo) {
		Response response = cliente.piezaUtilizadaSelect(codigo);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Pieza.class);
		} else {
			return null;
		} 
	}
	/**
	 * Metodo para obtener todas las Piezas Utilizadas que se encuentran en la BD.
	 * @return List (Lista de PiezasUtilizadas)
	 */
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	/**
	 * Metodo para la obtencion de PiezasUtilizadas con un Filtro.
	 * @param filtro (Filtro para realizar el filtrado de PiezasUtilizadas)
	 * @return List (Lista de Piezas)
	 */
	public List<Pieza> filtrarPiezaUtilizadas(String filtro) {
		Response response = cliente.filtrarPiezaUtilizadas(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	/**
	 * Metodo para la obtencion de HerramientasTaller con un Filtro.
	 * @param filtro (Filtro para realizar el filtrado de HerramientasTaller)
	 * @return List (Lista de HerramientasTaller)
	 */
	public List<HerramientasTaller> filtrarHerramientaMecanico(String filtro) {
		Response response = cliente.filtrarHerramientaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<HerramientasTaller>> genericType = new GenericType<List<HerramientasTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	/**
	 * Metodo para obtener todos los Proveedores que se encuentran en la BD.
	 * @return List (Lista de Proveedores)
	 */
	public List<Proveedor> cargarListaProveedores(){
		return cliente.cargarListaProveedores();
	}
	
	/**
	 * Metodo para obtener todos los Proveedores de Herramientas que se encuentran en la BD.
	 * @return List (Lista de ProveedoresHerramientas)
	 */
	public List<ProveedorHerramientas> cargarListaProveedoresHerramientas(){
		return cliente.cargarListaProveedoresHerramientas();
	}
	
	/**
	 * Metodo para obtener todas las Piezas Proveedores que se encuentran en la BD.
	 * @return List (Lista de PiezasProveedores)
	 */
	public List<PiezaProveedores> cargarListaPiezasProveedores(){
		return cliente.cargarListaPiezasProveedores();
	}
	
	/**
	 * Metodo para obtener todas las Herramientas que se encuentran en la BD.
	 * @return List (Lista de Herramientas)
	 */
	public List<Herramientas> cargarListaHerramientas(){
		return cliente.cargarListaHerramientas();
	}
	/**
	 * Metodo para la elimincacion de una Solicitud.
	 * @param cod (Codigo de la Solicitud)
	 * @return boolean (Devuelve true en caso afirmativo / false en negativo)
	 */
	public boolean deleteSolicitud(String cod) {
		Response response = cliente.SolicitudCompraDelete(cod); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
/**
 * Metodo para renombrar la ubicacion de una Pieza.	
 * @param ubicacion (Int que indica el valor del JComboBox)
 * @return ub (Ubicacion en texto)
 */
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
	/**
	 * Metodo para el calcular el Codigo de la siguiente Pieza.
	 * @param piezas (Lista de Piezas proveniente de la BD)
	 * @return codigo (Codigo Alphanumerico)
	 */
	public String carlcularCodigo(List<Pieza> piezas) {
		int numero = piezas.size() + 1;
		return "PI-" + numero;
	}
	/**
	 * Metodo para el calcular el Codigo de la siguiente Herramienta.
	 * @param herramientas (Lista de Herramientas proveniente de la BD)
	 * @return codigo (Codigo Alphanumerico)
	 */
	public String calcularCodigoHerramienta(List<HerramientasTaller> herramientas) {
		int numero = herramientas.size() + 1;
		return "H" + numero;
	}
}

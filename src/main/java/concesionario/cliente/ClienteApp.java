package concesionario.cliente;


import java.util.List;

import javax.swing.SwingUtilities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Entity;

import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.CitaComercial;
import concesionario.datos.CitaTaller;
import concesionario.datos.Cliente;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Presupuesto;
import concesionario.datos.Proveedor;
import concesionario.datos.ProveedorHerramientas;
import concesionario.datos.SolicitudCompra;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;

/**
 * Clase ClienteApp (La clase ClienteApp es la clase utilizada para la ejecucion del cliente del proyecto y la encargada de la comunicacion con el servidor).
 */
public class ClienteApp {
	
	private Client client;
	
	
	private WebTarget appTarget;
	private WebTarget loginTarget;
	
	public final  String URL_SERVER = "http://localhost:8080/concesionario";
	
	/**
	 * Constructor de la Clase ClienteApp
	 * @param loginTarget (Target General)
	 */
	public ClienteApp(WebTarget loginTarget) {
		this.loginTarget = loginTarget;
		
	}
	/**
	 * Constructor para la incialiacion de las variables.
	 */
	public ClienteApp() {
		client = ClientBuilder.newClient();
		 appTarget = client.target(URL_SERVER);
		 loginTarget= appTarget.path("loginp");
	}
	
	/**
	 * Metodo para el Incio de Sesion
	 * @param concat (Usuario que envia para comprobar e iniciar sesion).
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response login(Usuario concat) {
		Entity<Usuario> entity = Entity.entity(concat, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("inicio").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	/**
	 * Metodo para obtener el Target.
	 * @return loginTarget (Target General).
	 */
	public WebTarget getLoginTarget() {
		return this.loginTarget;
	}

	/**
	 * Metodo para el registro de Coches Concesionario.
	 * @param auto (CocheConcesionario que se envia al Server)
	 * @return response (Respuesta del Server).
	 */
	public Response registrarCoche(CocheConcesionario auto) {
		Entity<CocheConcesionario> entity = Entity.entity(auto, MediaType.APPLICATION_JSON);
		Response response =  loginTarget.path("insertCocheConcesionario").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	 /**
	  * Metodo para el registro de Coches Taller.
	 * @param cocheTaller (CocheTaller que se envia al Server)
	 * @return response (Respuesta del Server).
	  */
	public Response registrarCocheTaller(CocheTaller cocheTaller) {
		Entity<CocheTaller> entity = Entity.entity(cocheTaller, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertCocheTaller").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	/**
	 * Metodo para el registro de Clientes.
	 * @param client (Cliente que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroCliente (Cliente client) {
		Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertClient").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	/**
	 * Metodo para el registo de Mecanicos.
	 * @param mecanic (Mecanico que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroMecanico(Mecanico mecanic) {
		Entity<Mecanico> entity = Entity.entity(mecanic, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertMecanic").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	/**
	 * Metodo para el regitro de Comerciales.
	 * @param comercial (Comercial que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroComercial(Comercial comercial) {
		Entity<Comercial> entity = Entity.entity(comercial, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertComercial").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	/**
	 * Metodo para el regitro de Empleados del Departamento Compras.
	 * @param dep (Empleado del Departamento Compras que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroDepartamentoCompras(DepartamentoCompras dep) {
		WebTarget insertDepartamentoComprasTarget = loginTarget.path("insertDepartamentoCompras");
		Entity<DepartamentoCompras> entity = Entity.entity(dep, MediaType.APPLICATION_JSON);
		Response response = insertDepartamentoComprasTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	/**
	 * Metodo para el regitro de Tarifas.
	 * @param tarifa (Tarifa que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroTarifa(Tarifa tarifa) {
		Entity<Tarifa> entity = Entity.entity(tarifa, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertTarifa").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	/**
	 * Metodo para modificar la Contrasenia del Cliente.
	 * @param client (Objeto Cliente)
	 * @param contrasenia (Nueva Constrasenia)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cambiarContraseniaCliente(Cliente client, String contrasenia) {
		Entity<Cliente> ent = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response resp =  loginTarget.path("deleteClient").request(MediaType.TEXT_PLAIN).post(ent);
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			client.setContrasenya(contrasenia);
			Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
			Response response = loginTarget.path("insertClient").request(MediaType.TEXT_PLAIN).post(entity);
			return response;
		} else {
			return null;
		}
	}
	/**
	 * Metodo para modificar la Contrasenia del Cliente.
	 * @param client (Objeto Cliente)
	 * @param nickname (Nuevo nickname)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cambiarNicknameCliente(Cliente client, String nickname) {
		Entity<Cliente> ent = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response resp = loginTarget.path("deleteClient").request(MediaType.TEXT_PLAIN).post(ent);
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			client.setNickname(nickname);
			Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
			Response response = loginTarget.path("insertClient").request(MediaType.TEXT_PLAIN).post(entity);
			return response;
		} else {
			return null;
		}
	}
	/**
	 * Metodo para modificar el Estado del Coche que se encuentra en el Taller.
	 * @param coche (Objeto CocheTaller)
	 * @param estado (Nuevo Estado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		String matricula = coche.getMatricula();
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response resp =  loginTarget.path("deleteCocheTaller").request(MediaType.TEXT_PLAIN).post(ent);
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			coche.setEstado(estado);
			Entity<CocheTaller> entity = Entity.entity(coche, MediaType.APPLICATION_JSON);
			Response response = loginTarget.path("insertCocheTaller").request(MediaType.TEXT_PLAIN).post(entity);
			return response;
		} else {
			return null;
		}
	}
	/**
	 * Metodo para seleccionar un Coche Taller
	 * @param matricula (Matricula que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarCocheTaller (String matricula) {
		WebTarget selectClienteTarget = loginTarget.path("selectCocheTaller");
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para seleccionar un Cliente
	 * @param nickname (Nickname del Cliente que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response clienteSelect(String nickname) {
		WebTarget selectClienteTarget = loginTarget.path("selectClient");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para cargar los Coches del Concesionario
	 * @return coches (Lista que devuelve el Server)
	 */
	public List<CocheConcesionario> cargarTablaCochesConcesionario(){
		WebTarget loadTableTarget = loginTarget.path("loadCocheConcesionarioTable");
		GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
        List<CocheConcesionario> coches = loadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		return coches;
	}
	/**
	 * Metodo para seleccionar un Mecanico
	 * @param nickname (Nickname del Mecanico que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response mecanicoSelect(String nickname) {
		WebTarget selectMecanicoTarget = loginTarget.path("selectMecanico");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectMecanicoTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar un Mecanico
	 * @param nickname (Nickname del Mecanico que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response mecanicoDelete(String nickname) {
		WebTarget deleteMecanicoTarget = loginTarget.path("deleteMecanico");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteMecanicoTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para seleccionar un Comercial
	 * @param nickname (Nickname del Comercial que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response comercialSelect(String nickname) {
		WebTarget selectComercialTarget = loginTarget.path("selectComercial");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectComercialTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar un Comercial
	 * @param nickname (Nickname del Comercial que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response comercialDelete(String nickname) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteComercial");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar un Coche Taller
	 * @param matricula (Matricula del Coche Taller que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response CocheTallerDelete(String matricula) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteCocheTaller");
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar una Solicitu de Compra
	 * @param solicitud (Solicitud que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response SolicitudCompraDelete(String solicitud) {
		WebTarget deleteSolicitudTarget = loginTarget.path("deleteSolicitudCompra");
		Entity<String> ent = Entity.entity(solicitud, MediaType.APPLICATION_JSON);
		Response response = deleteSolicitudTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar una Tarifa 
	 * @param idTarifa (Codigo Identificativo de la Tarifa que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response tarifaDelete(String idTarifa) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteTarifa");
		Entity<String> ent = Entity.entity(idTarifa, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para seleccionar un Empleado del Departamento Compras
	 * @param nickname (Nickname del Empleado del Departamento Compras que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response departamentoComprasSelect(String nickname) {
		WebTarget selectDepartamentoComprasTarget = loginTarget.path("selectDepartamentoCompras");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectDepartamentoComprasTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar un Empleado del Departamento Compras
	 * @param nickname (Nickname del Empleado del Departamento Compras que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response departamentoComprasDelete(String nickname) {
		WebTarget deleteDepartamentoComprasTarget = loginTarget.path("deleteDepartamentoCompras");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteDepartamentoComprasTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para registrar una Venta
	 * @param venta (Venta que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroVenta(Venta venta) {
 		WebTarget insertVentaTarget = loginTarget.path("insertVenta");
 		Entity<Venta> entity = Entity.entity(venta, MediaType.APPLICATION_JSON);
 		Response response = insertVentaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
 	}
	/**
	 * Metodo para cargar los Piezas 
	 * @return piezas (Lista que devuelve el Server)
	 */
	public List<Pieza> cargarTablaPiezas(){
		WebTarget loadPiezaTableTarget = loginTarget.path("loadPiezaTable");
		GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
        List<Pieza> piezas = loadPiezaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezas;
	}
	/**
	 * Metodo para cargar los Herremientas
	 * @return herramientas (Lista que devuelve el Server)
	 */
	public List<HerramientasTaller> cargarTablaHerramientasTaller(){
		WebTarget loadPiezaTableTarget = loginTarget.path("loadHerramientaTable");
		GenericType<List<HerramientasTaller>> genericType = new GenericType<List<HerramientasTaller>>() {};
        List<HerramientasTaller> herramientas = loadPiezaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return herramientas;
	
	}
	/**
	 * Metodo para cargar los Solicitudes de Compra
	 * @return sol (Lista que devuelve el Server)
	 */
	public List<SolicitudCompra> cargarTablaSolicitudCompra(){
		WebTarget loadSolicitudTarget = loginTarget.path("loadSolicitudTable");
		GenericType<List<SolicitudCompra>> genericType = new GenericType<List<SolicitudCompra>>() {};
        List<SolicitudCompra> sol = loadSolicitudTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return sol;
	}
	/**
	 * Metodo para seleccionar una PiezaUtilizada 
	 * @param codigo (Codigo de la Pieza Utilizada que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response piezaUtilizadaSelect(String codigo) {
		WebTarget selectPiezaUtilizadaTarget = loginTarget.path("selectPiezaUtilizada");
		Entity<String> ent = Entity.entity(codigo, MediaType.APPLICATION_JSON);
		Response response = selectPiezaUtilizadaTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para registrar una Pieza
	 * @param pieza (Pieza que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroPieza(Pieza pieza) {
		WebTarget insertPiezaTarget = loginTarget.path("insertPiezas");
 		Entity<Pieza> entity = Entity.entity(pieza, MediaType.APPLICATION_JSON);
 		Response response = insertPiezaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	/**
	 * Metodo para registrar una Herramienta
	 * @param herramienta (Herramienta que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroHerramienta(HerramientasTaller herramienta) {
		WebTarget insertPiezaTarget = loginTarget.path("insertHerramientas");
 		Entity<HerramientasTaller> entity = Entity.entity(herramienta, MediaType.APPLICATION_JSON);
 		Response response = insertPiezaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	/**
	 * Metodo para registrar un Presupuesto
	 * @param presupuesto (Presupuesto que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroPresupuesto(Presupuesto presupuesto) {
		WebTarget insertPresupuestoTarget = loginTarget.path("insertPresupuesto");
 		Entity<Presupuesto> entity = Entity.entity(presupuesto, MediaType.APPLICATION_JSON);
 		Response response = insertPresupuestoTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	/**
	 * Metodo para registrar una Solicitu de Compra
	 * @param solicitud (SolicitudCompra que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroSolicitud(SolicitudCompra solicitud) {
		WebTarget insertSolicitudTarget = loginTarget.path("insertSolicitud");
 		Entity<SolicitudCompra> entity = Entity.entity(solicitud, MediaType.APPLICATION_JSON);
 		Response response = insertSolicitudTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	/**
	 * Metodo para insertar las Herramientas
	 * @return herramienta (Lista que devuelve el Server)
	 */
	public List<Herramientas> insertarHerramientas(){
		WebTarget insertHerramientaTableTarget = loginTarget.path("insertHerramienta");
		GenericType<List<Herramientas>> genericType = new GenericType<List<Herramientas>>() {};
        List<Herramientas> herramienta = insertHerramientaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return herramienta;
	}
	/**
	 * Metodo para cargar los Piezas Utilizadas
	 * @return piezas (Lista que devuelve el Server)
	 */
	public List<Pieza> cargarTablaPiezasUtilizadas(){
		WebTarget loadPiezaUtilizadaTableTarget = loginTarget.path("loadPiezaUtilizadasTable");
		GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
        List<Pieza> piezas = loadPiezaUtilizadaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezas;
	}
	/**
	 * Metodo para cargar los Coches Matriculados
	 * @return coches_matric (Lista que devuelve el Server)
	 */
	public List<CocheMatriculado> cargarTablaCochesCocheMatriculados(){
		WebTarget loadTableTarget = loginTarget.path("loadCochesMatricTable");
		GenericType<List<CocheMatriculado>> genericType = new GenericType<List<CocheMatriculado>>() {};
        List<CocheMatriculado> coches_matric = loadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
        return coches_matric;
	}
	/**
	 * Metodo para cargar los Empleados
	 * @return empleados (Lista que devuelve el Server)
	 */
	public List<Empleado> cargarTablaEmpleados(){
		WebTarget loadEmpleadosTableTarget = loginTarget.path("loadEmpleadosTable");
		GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>() {};
        List<Empleado> empleados = loadEmpleadosTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return empleados;
	}
	/**
	 * Metodo para cargar los Ventas
	 * @return ventas (Lista que devuelve el Server)
	 */
	public List<Venta> cargarTablaVenta(){
		WebTarget loadVentaTableTarget = loginTarget.path("loadVentaTable");
		GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
        List<Venta> ventas = loadVentaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return ventas;
	}
	/**
	 * Metodo para cargar los Coches Taller
	 * @return coches (Lista que devuelve el Server)
	 */
	public List<CocheTaller> cargarTablaCocheTaller(){
		WebTarget loadVentaTableTarget = loginTarget.path("loadCocheTallerTable");
		GenericType<List<CocheTaller>> genericType = new GenericType<List<CocheTaller>>() {};
        List<CocheTaller> coches = loadVentaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return coches;
	}
	/**
	 * Metodo para seleccionar un Coche Concerionario
	 * @param modelo (Modelo del CocheConcesionario que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarCocheConcesionario(String modelo) {
		WebTarget selectCocheConcesionarioTarget = loginTarget.path("selectCocheConcesionario");
		Entity<String> ent = Entity.entity(modelo, MediaType.APPLICATION_JSON);
		Response response = selectCocheConcesionarioTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para cargar los Presupuestos
	 * @return presupuestos (Lista que devuelve el Server)
	 */
	public List<Presupuesto> cargarTablaPresupuestos(){
		WebTarget loadPresupuestosTableTarget = loginTarget.path("loadPresupuestosTable");
		GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
        List<Presupuesto> presupuestos = loadPresupuestosTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return presupuestos;
	}
	/**
	 * Metodo para seleccionar un Presupuesto
	 * @param codigo (Codigo del Presupuesto que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarPresupuesto(String codigo) {
		WebTarget selectPresupuestoTarget = loginTarget.path("selectPresupuesto");
		Entity<String> ent = Entity.entity(codigo, MediaType.APPLICATION_JSON);
		Response response = selectPresupuestoTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para cargar los Tarifas
	 * @return tarifas (Lista que devuelve el Server)
	 */
	public List<Tarifa> cargarTablaTarifas(){
		WebTarget loadTarifasTableTarget = loginTarget.path("loadTarifasTable");
		GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
        List<Tarifa> tarifas = loadTarifasTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return tarifas;
	}
	
	public Response filtrarTarifaPrecio(int precio) {
		WebTarget filtroPrecioTarifaTarget = loginTarget.path("precioTarifa");
		Entity<Integer> entity = Entity.entity(precio, MediaType.APPLICATION_JSON);
		Response response = filtroPrecioTarifaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarTarifaPrecioMin(int precio) {
		WebTarget filtroPrecioMinTarifaTarget = loginTarget.path("precioMinTarifa");
		Entity<Integer> entity = Entity.entity(precio, MediaType.APPLICATION_JSON);
		Response response = filtroPrecioMinTarifaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarTarifaHorasMax(int horas) {
		WebTarget filtroHorasMaxTarifaTarget = loginTarget.path("horasMaxTarifa");
		Entity<Integer> entity = Entity.entity(horas, MediaType.APPLICATION_JSON);
		Response response = filtroHorasMaxTarifaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarTarifaHorasMin(int horas) {
		WebTarget filtroHorasMinTarifaTarget = loginTarget.path("horasMinTarifa");
		Entity<Integer> entity = Entity.entity(horas, MediaType.APPLICATION_JSON);
		Response response = filtroHorasMinTarifaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarVentaMarca(String marca) {
		WebTarget filtroMarcaVentaTarget = loginTarget.path("loadTablaMarcaVentas");
		Entity<String> entity = Entity.entity(marca, MediaType.APPLICATION_JSON);
		Response response = filtroMarcaVentaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarVentaModelo(String modelo) {
		WebTarget filtroModeloVentaTarget = loginTarget.path("loadTablaModeloVentas");
		Entity<String> entity = Entity.entity(modelo, MediaType.APPLICATION_JSON);
		Response response = filtroModeloVentaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarVentaComercial(String comercial) {
		WebTarget filtroComercialVentaTarget = loginTarget.path("loadTablaComercialVentas");
		Entity<String> entity = Entity.entity(comercial, MediaType.APPLICATION_JSON);
		Response response = filtroComercialVentaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarPresupuestoCodigo(String codigo) {
		WebTarget filtroPresupuestoClienteTarget = loginTarget.path("loadTablaPresupuestoCodigo");
		Entity<String> entity = Entity.entity(codigo, MediaType.APPLICATION_JSON);
		Response response = filtroPresupuestoClienteTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarPresupuestoCliente(String cliente) {
		WebTarget filtroPresupuestoCodigoTarget = loginTarget.path("loadTablaPresupuestoCliente");
		Entity<String> entity = Entity.entity(cliente, MediaType.APPLICATION_JSON);
		Response response = filtroPresupuestoCodigoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarPresupuestoProblema(String problema) {
		WebTarget filtroPresupuestoProblemaTarget = loginTarget.path("loadTablaPresupuestoProblema");
		Entity<String> entity = Entity.entity(problema, MediaType.APPLICATION_JSON);
		Response response = filtroPresupuestoProblemaTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarCocheConcesionario(String filtro) {
		WebTarget filtroCocheConcesionarioTarget = loginTarget.path("loadTablaCocheConcesionarioFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroCocheConcesionarioTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarCocheMatriculado(String filtro) {
		WebTarget filtroCocheMatriculadoTarget = loginTarget.path("loadTablaCocheMatriculadoFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroCocheMatriculadoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarCocheTaller(String filtro) {
		WebTarget filtroCocheTallerTarget = loginTarget.path("loadTablaCocheTallerFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroCocheTallerTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	
	public Response filtrarHerramientaMecanico(String filtro) {
		WebTarget filtroHerramientaMecanicoTarget = loginTarget.path("loadTablaHerramientaMecanicoFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroHerramientaMecanicoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarPiezaMecanico(String filtro) {
		WebTarget filtroPiezaMecanicoTarget = loginTarget.path("loadTablaPiezaMecanicoFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroPiezaMecanicoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	
	public Response filtrarPiezaUtilizadas(String filtro) {
		WebTarget filtroPiezaUtilizadasTarget = loginTarget.path("loadTablaPiezaUtilizadasFiltro");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtroPiezaUtilizadasTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para cargar las Fidelidades de los Clientes
	 * @return clientes (Lista que devuelve el Server)
	 */
	public List<ClienteFidelidad> cargarTablaClientesFidelidad(){
		WebTarget loadClienteFidelidadTableTarget = loginTarget.path("loadClientesFidelidadTable");
		GenericType<List<ClienteFidelidad>> genericType = new GenericType<List<ClienteFidelidad>>() {};
        List<ClienteFidelidad> clientes = loadClienteFidelidadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return clientes;
	}
	/**
	 * Metodo para cargar los PiezasProveedores
	 * @return piezasProveedores (Lista que devuelve el Server)
	 */
	public List<PiezaProveedores> cargarListaPiezasProveedores(){
		WebTarget loadPiezasProveedorListTarget = loginTarget.path("loadPiezasProveedoresList");
		GenericType<List<PiezaProveedores>> genericType = new GenericType<List<PiezaProveedores>>() {};
        List<PiezaProveedores> piezasProveedores = loadPiezasProveedorListTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezasProveedores;
	}
	/**
	 * Metodo para cargar la Lista de Proveedores
	 * @return PiezaProveedores (Lista que devuelve el Server)
	 */
	public List<Proveedor> cargarListaProveedores(){
		WebTarget loadPiezasProveedorListTarget = loginTarget.path("loadProveedores");
		GenericType<List<Proveedor>> genericType = new GenericType<List<Proveedor>>() {};
        List<Proveedor> piezasProveedores = loadPiezasProveedorListTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezasProveedores;
	}
	/**
	 * Metodo para cargar los Comerciales
	 * @return comerciales (Lista que devuelve el Server)
	 */
	public List<Comercial> cargarTablaComercial(){
		WebTarget loadComercialTableTarget = loginTarget.path("loadComercialTable");
		GenericType<List<Comercial>> genericType = new GenericType<List<Comercial>>() {};
        List<Comercial> comerciales = loadComercialTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return comerciales;
	}
	/**
	 * Metodo para seleccionar una Cita de un Comercial
	 * @param restriccion (Restriccion de la CitaComercial que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarCitaComercial (String restriccion) {
		WebTarget selectCitaComercialTarget = loginTarget.path("selectCitaComercial");
		Entity<String> ent = Entity.entity(restriccion, MediaType.APPLICATION_JSON);
		Response response = selectCitaComercialTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para registrar una Cita de un Comercial
	 * @param citaComercial (CitaComercial que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroCitaComercial(CitaComercial citaComercial) {
		Entity<CitaComercial> entity = Entity.entity(citaComercial, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertCitaComercial").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	/**
	 * Metodo para seleccionar una Cita de un Empleado del Taller
	 * @param restriccion (Restriccion de la CitaTaller que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarCitaTaller (String restriccion) {
		WebTarget selectCitaTallerTarget = loginTarget.path("selectCitaTaller");
		Entity<String> ent = Entity.entity(restriccion, MediaType.APPLICATION_JSON);
		Response response = selectCitaTallerTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para registrar una Cita de un Empleado del Taller
	 * @param citaTaller (CitaTaller que se envia al Server)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroCitaTaller(CitaTaller citaTaller) {
		Entity<CitaTaller> entity = Entity.entity(citaTaller, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertCitaTaller").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	/**
	 * Metodo para cargar los Mecanicos
	 * @return mecanicos (Lista que devuelve el Server)
	 */
	public List<Mecanico> cargarTablaMecanico(){
		WebTarget loadMecanicoTableTarget = loginTarget.path("loadMecanicoTable");
		GenericType<List<Mecanico>> genericType = new GenericType<List<Mecanico>>() {};
        List<Mecanico> mecanicos = loadMecanicoTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return mecanicos;
	}
	/**
	 * Metodo para cargar la Lista de Proveedores de Herramientas
	 * @return Proveedores (Lista que devuelve el Server)
	 */
	public List<ProveedorHerramientas> cargarListaProveedoresHerramientas() {
		WebTarget loadHerramiemtasProveedorListTarget = loginTarget.path("loadProveedoresHerramientaList");
		GenericType<List<ProveedorHerramientas>> genericType = new GenericType<List<ProveedorHerramientas>>() {
		};
		List<ProveedorHerramientas> Proveedores = loadHerramiemtasProveedorListTarget
				.request(MediaType.APPLICATION_JSON).get(genericType);
		return Proveedores;
	}
	/**
	 * Metodo para cargar la Lista de Herramientas
	 * @return herramientasProveedor (Lista que devuelve el Server)
	 */
	public List<Herramientas> cargarListaHerramientas() {
		WebTarget loadHerramiemtasProveedorListTarget = loginTarget.path("loadHerramientasList");
		GenericType<List<Herramientas>> genericType = new GenericType<List<Herramientas>>() {
		};
		List<Herramientas> herramientasProveedores = loadHerramiemtasProveedorListTarget
				.request(MediaType.APPLICATION_JSON).get(genericType);
		return herramientasProveedores;
	}
	/**
	 * Metodo para cargar las Citas de las Citas de Comerciales con un filtro
	 * @param comercial (Nickname del Comercial)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cargarCitaComercial(String comercial) {
		WebTarget cargarCitaComercialTarget = loginTarget.path("loadTablaCitaComercial");
		Entity<String> entity = Entity.entity(comercial, MediaType.APPLICATION_JSON);
		Response response = cargarCitaComercialTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para cargar las Citas de los Comerciales con un filtro
	 * @param filtro (Filtro para las Citas de los Comerciales)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response filtrarCitaComercial(String filtro) {
		WebTarget filtrarCitaComercialTarget = loginTarget.path("filtrarTablaCitaComercial");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtrarCitaComercialTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para cargar las Citas de los Mecanicos
	 * @param mecanico (Nickname del Mecanicos)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cargarCitaMecanico(String mecanico) {
		WebTarget cargarCitaMecanicoTarget = loginTarget.path("loadTablaCitaMecanico");
		Entity<String> entity = Entity.entity(mecanico, MediaType.APPLICATION_JSON);
		Response response = cargarCitaMecanicoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para cargar las Citas de los Mecanicos con un filtro
	 * @param filtro (Filtro para las Citas de los Mecanicos)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response filtrarCitaMecanico(String filtro) {
		WebTarget filtrarCitaMecanicoTarget = loginTarget.path("filtrarTablaCitaMecanico");
		Entity<String> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = filtrarCitaMecanicoTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para cargar las Horas de los Empleado
	 * @param filtro (Filtro para las Horas del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response cargarEmpleadoHoras(int filtro) {
		WebTarget cargarEmpleadoHorasTarget = loginTarget.path("cargarEmpleadoHoras");
		Entity<Integer> entity = Entity.entity(filtro, MediaType.APPLICATION_JSON);
		Response response = cargarEmpleadoHorasTarget.request(MediaType.APPLICATION_JSON).post(entity);
        return response;
	}
	/**
	 * Metodo para registrar las Horas del Empleado
	 * @param horasEmpleado (Horas del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroHorasEmpleado (String horasEmpleado) {
		Entity<String> entity = Entity.entity(horasEmpleado, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertHorasEmpleado").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	/**
	 * Metodo para registrar las Horas Temporales del Empleado
	 * @param horasEmpleado (Horas del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response registroHorasEmpleadoTemporal (String horasEmpleado) {
		Entity<String> entity = Entity.entity(horasEmpleado, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertHorasEmpleadoTemporal").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	/**
	 * Metodo para seleccionar las Horas Temporales del Empleado
	 * @param nickname (Nickname del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarHorsEmpleadoTemporal (String nickname) {
		WebTarget selectHorasEmpleadosTemporalTarget = loginTarget.path("selectHorasEmpleadosTemporal");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectHorasEmpleadosTemporalTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para seleccionar las Horas del Empleado
	 * @param nickname (Nickname del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response seleccionarHorsEmpleado (String nickname) {
		WebTarget selectHorasEmpleadosTarget = loginTarget.path("selectHorasEmpleados");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectHorasEmpleadosTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar las Horas del Empleado
	 * @param nickname (Nickname del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response horasEmpleadosDelete(String nickname) {
		WebTarget deleteHorasEmpleadoTarget = loginTarget.path("deleteHorasEmpleados");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteHorasEmpleadoTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar las Horas Temporales del Empleado
	 * @param nickname (Nickname del Empleado)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response horasEmpleadosTemporalDelete(String nickname) {
		WebTarget deleteHorasEmpleadoTemporalTarget = loginTarget.path("deleteHorasEmpleadosTemporal");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteHorasEmpleadoTemporalTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar las Citas del Comercial
	 * @param nickFecha (Nickname del Comercial + Fecha)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response citasComercialDelete(String nickFecha) {
		WebTarget deleteCitasComercialTarget = loginTarget.path("deleteCitasComercial");
		Entity<String> ent = Entity.entity(nickFecha, MediaType.APPLICATION_JSON);
		Response response = deleteCitasComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo para eliminar las Citas del Mecanico
	 * @param nickFecha (Nickname del Mecanico + Fecha)
	 * @return response (Respuesta que devuelve el Server)
	 */
	public Response citasMecanicoDelete(String nickFecha) {
		WebTarget deleteCitasMecanicoTarget = loginTarget.path("deleteCitasMecanico");
		Entity<String> ent = Entity.entity(nickFecha, MediaType.APPLICATION_JSON);
		Response response = deleteCitasMecanicoTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	/**
	 * Metodo Main (Iniciar la aplicacion).
	 * @param args (Argumentos)
	 */
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable(){
	        
	            @Override
	            public void run() {
	                ClienteApp cliente = new ClienteApp();
	                
	                LoginController loginController = new LoginController(cliente);
	                VentanaLogin vlogin = new VentanaLogin(loginController);
	                vlogin.setVisible(true);
	                
	            }
	        });
	    }

	
	}

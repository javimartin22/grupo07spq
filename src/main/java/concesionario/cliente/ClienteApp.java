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
import concesionario.datos.Cliente;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Presupuesto;
import concesionario.datos.Proveedor;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;

//Cambiar el Pom.xml para que luego nos runnee esta aplicaicon
public class ClienteApp {
	
	private Client client;
	
	
	private WebTarget appTarget;
	private WebTarget loginTarget;
	
	public final  String URL_SERVER = "http://localhost:8080/concesionario";
	
	public ClienteApp(WebTarget loginTarget) {
		this.loginTarget = loginTarget;
		
	}
	
	public ClienteApp() {
		client = ClientBuilder.newClient();
		
		 appTarget = client.target(URL_SERVER);
		 loginTarget= appTarget.path("loginp");
	}
	
	public Response login(Usuario concat) {
		Entity<Usuario> entity = Entity.entity(concat, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("inicio").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	public WebTarget getLoginTarget() {
		return this.loginTarget;
	}

	public Response registrarCoche(CocheConcesionario auto) {
		Entity<CocheConcesionario> entity = Entity.entity(auto, MediaType.APPLICATION_JSON);
		Response response =  loginTarget.path("insertCocheConcesionario").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	
	public Response registrarCocheTaller(CocheTaller cocheTaller) {
		Entity<CocheTaller> entity = Entity.entity(cocheTaller, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertCocheTaller").request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	
	public Response registroCliente (Cliente client) {
		Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertClient").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroMecanico(Mecanico mecanic) {
		Entity<Mecanico> entity = Entity.entity(mecanic, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertMecanic").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroComercial(Comercial comercial) {
		Entity<Comercial> entity = Entity.entity(comercial, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertComercial").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroDepartamentoCompras(DepartamentoCompras dep) {
		WebTarget insertDepartamentoComprasTarget = loginTarget.path("insertDepartamentoCompras");
		Entity<DepartamentoCompras> entity = Entity.entity(dep, MediaType.APPLICATION_JSON);
		Response response = insertDepartamentoComprasTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroTarifa(Tarifa tarifa) {
		Entity<Tarifa> entity = Entity.entity(tarifa, MediaType.APPLICATION_JSON);
		Response response = loginTarget.path("insertTarifa").request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
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
	
	public Response seleccionarCocheTaller (String matricula) {
		WebTarget selectClienteTarget = loginTarget.path("selectCocheTaller");
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public Response clienteSelect(String nickname) {
		WebTarget selectClienteTarget = loginTarget.path("selectClient");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public List<CocheConcesionario> cargarTablaCochesConcesionario(){
		WebTarget loadTableTarget = loginTarget.path("loadCocheConcesionarioTable");
		GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
        List<CocheConcesionario> usuarios = loadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		return usuarios;
	}
	
	public Response mecanicoSelect(String nickname) {
		WebTarget selectMecanicoTarget = loginTarget.path("selectMecanico");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectMecanicoTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public Response mecanicoDelete(String nickname) {
		WebTarget deleteMecanicoTarget = loginTarget.path("deleteMecanico");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteMecanicoTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response comercialSelect(String nickname) {
		WebTarget selectComercialTarget = loginTarget.path("selectComercial");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectComercialTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public Response comercialDelete(String nickname) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteComercial");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response CocheTallerDelete(String matricula) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteCocheTaller");
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response tarifaDelete(String idTarifa) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteTarifa");
		Entity<String> ent = Entity.entity(idTarifa, MediaType.APPLICATION_JSON);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response departamentoComprasSelect(String nickname) {
		WebTarget selectDepartamentoComprasTarget = loginTarget.path("selectDepartamentoCompras");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectDepartamentoComprasTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public Response departamentoComprasDelete(String nickname) {
		WebTarget deleteDepartamentoComprasTarget = loginTarget.path("deleteDepartamentoCompras");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = deleteDepartamentoComprasTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response registroVenta(Venta venta) {
 		WebTarget insertVentaTarget = loginTarget.path("insertVenta");
 		Entity<Venta> entity = Entity.entity(venta, MediaType.APPLICATION_JSON);
 		Response response = insertVentaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
 	}
	
	public List<Pieza> cargarTablaPiezas(){
		WebTarget loadPiezaTableTarget = loginTarget.path("loadPiezaTable");
		GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
        List<Pieza> piezas = loadPiezaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezas;
	}
	
	public Response piezaUtilizadaSelect(String codigo) {
		WebTarget selectPiezaUtilizadaTarget = loginTarget.path("selectPiezaUtilizada");
		Entity<String> ent = Entity.entity(codigo, MediaType.APPLICATION_JSON);
		Response response = selectPiezaUtilizadaTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public Response registroPieza(Pieza pieza) {
		WebTarget insertPiezaTarget = loginTarget.path("insertPiezas");
 		Entity<Pieza> entity = Entity.entity(pieza, MediaType.APPLICATION_JSON);
 		Response response = insertPiezaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	
	public Response registroPresupuesto(Presupuesto presupuesto) {
		WebTarget insertPresupuestoTarget = loginTarget.path("insertPresupuesto");
 		Entity<Presupuesto> entity = Entity.entity(presupuesto, MediaType.APPLICATION_JSON);
 		Response response = insertPresupuestoTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	
	public List<Pieza> cargarTablaPiezasUtilizadas(){
		WebTarget loadPiezaUtilizadaTableTarget = loginTarget.path("loadPiezaUtilizadasTable");
		GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
        List<Pieza> piezas = loadPiezaUtilizadaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezas;
	}
	
	public List<CocheMatriculado> cargarTablaCochesCocheMatriculados(){
		WebTarget loadTableTarget = loginTarget.path("loadCochesMatricTable");
		GenericType<List<CocheMatriculado>> genericType = new GenericType<List<CocheMatriculado>>() {};
        List<CocheMatriculado> coches_matric = loadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
        return coches_matric;
	}
	
	public List<Empleado> cargarTablaEmpleados(){
		WebTarget loadEmpleadosTableTarget = loginTarget.path("loadEmpleadosTable");
		GenericType<List<Empleado>> genericType = new GenericType<List<Empleado>>() {};
        List<Empleado> empleados = loadEmpleadosTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return empleados;
	}
	
	public List<Venta> cargarTablaVenta(){
		WebTarget loadVentaTableTarget = loginTarget.path("loadVentaTable");
		GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
        List<Venta> ventas = loadVentaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return ventas;
	}
	
	public List<CocheTaller> cargarTablaCocheTaller(){
		WebTarget loadVentaTableTarget = loginTarget.path("loadCocheTallerTable");
		GenericType<List<CocheTaller>> genericType = new GenericType<List<CocheTaller>>() {};
        List<CocheTaller> coches = loadVentaTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return coches;
	}
	
	public Response seleccionarCocheConcesionario(String modelo) {
		WebTarget selectCocheConcesionarioTarget = loginTarget.path("selectCocheConcesionario");
		Entity<String> ent = Entity.entity(modelo, MediaType.APPLICATION_JSON);
		Response response = selectCocheConcesionarioTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	
	public List<Presupuesto> cargarTablaPresupuestos(){
		WebTarget loadPresupuestosTableTarget = loginTarget.path("loadPresupuestosTable");
		GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
        List<Presupuesto> presupuestos = loadPresupuestosTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return presupuestos;
	}
	
	public Response seleccionarPresupuesto(String codigo) {
		WebTarget selectPresupuestoTarget = loginTarget.path("selectPresupuesto");
		Entity<String> ent = Entity.entity(codigo, MediaType.APPLICATION_JSON);
		Response response = selectPresupuestoTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
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
	
	public List<ClienteFidelidad> cargarTablaClientesFidelidad(){
		WebTarget loadClienteFidelidadTableTarget = loginTarget.path("loadClientesFidelidadTable");
		GenericType<List<ClienteFidelidad>> genericType = new GenericType<List<ClienteFidelidad>>() {};
        List<ClienteFidelidad> clientes = loadClienteFidelidadTableTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return clientes;
	}
	
		
	public List<PiezaProveedores> cargarListaPiezasProveedores(){
		WebTarget loadPiezasProveedorListTarget = loginTarget.path("loadPiezasProveedoresList");
		GenericType<List<PiezaProveedores>> genericType = new GenericType<List<PiezaProveedores>>() {};
        List<PiezaProveedores> piezasProveedores = loadPiezasProveedorListTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezasProveedores;
	}
	
	public List<Proveedor> cargarListaProveedores(){
		WebTarget loadPiezasProveedorListTarget = loginTarget.path("loadProveedores");
		GenericType<List<Proveedor>> genericType = new GenericType<List<Proveedor>>() {};
        List<Proveedor> piezasProveedores = loadPiezasProveedorListTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		return piezasProveedores;
	}
	
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

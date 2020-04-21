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

import concesionario.cliente.controller.Controller;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;

//Cambiar el Pom.xml para que luego nos runnee esta aplicaicon
public class ClienteApp {
	
	private Client client;
	
	
	private WebTarget appTarget;
	private WebTarget loginTarget;
	
	public static final String URL_SERVER = "http://localhost:8080/concesionario";
	
	public ClienteApp() {
		
		client = ClientBuilder.newClient();
		
		 appTarget = client.target(URL_SERVER);
		 loginTarget= appTarget.path("loginp");
		
	}
	
	public Response login(Usuario concat) {
		WebTarget inicioTarget = loginTarget.path("inicio");
		Entity<Usuario> entity = Entity.entity(concat, MediaType.APPLICATION_JSON);
		Response response = inicioTarget.request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	
	public Response registrarCoche(CocheConcesionario auto) {
		System.out.println(auto.getMarca());
		WebTarget registroTarget = loginTarget.path("insertCocheConcesionario");
		Entity<CocheConcesionario> entity = Entity.entity(auto, MediaType.APPLICATION_JSON);
		Response response = registroTarget.request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	
	public Response registrarCocheTaller(CocheTaller cocheTaller) {
		System.out.println(cocheTaller.getMarca());
		WebTarget registroTarget = loginTarget.path("insertCocheTaller");
		Entity<CocheTaller> entity = Entity.entity(cocheTaller, MediaType.APPLICATION_JSON);
		Response response = registroTarget.request(MediaType.TEXT_PLAIN).post(entity);

        return response;
	}
	
	public Response registroCliente (Cliente client) {
		WebTarget insertClientTarget = loginTarget.path("insertClient");
		Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response response = insertClientTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroMecanico(Mecanico mecanic) {
		WebTarget insertMecanicTarget = loginTarget.path("insertMecanic");
		Entity<Mecanico> entity = Entity.entity(mecanic, MediaType.APPLICATION_JSON);
		Response response = insertMecanicTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroComercial(Comercial comercial) {
		WebTarget insertComercialTarget = loginTarget.path("insertComercial");
		Entity<Comercial> entity = Entity.entity(comercial, MediaType.APPLICATION_JSON);
		Response response = insertComercialTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroDepartamentoCompras(DepartamentoCompras dep) {
		WebTarget insertDepartamentoComprasTarget = loginTarget.path("insertDepartamentoCompras");
		Entity<DepartamentoCompras> entity = Entity.entity(dep, MediaType.APPLICATION_JSON);
		Response response = insertDepartamentoComprasTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response registroTarifa(Tarifa tarifa) {
		WebTarget insertTarifaTarget = loginTarget.path("insertTarifa");
		Entity<Tarifa> entity = Entity.entity(tarifa, MediaType.APPLICATION_JSON);
		Response response = insertTarifaTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
	}
	
	public Response cambiarContraseniaCliente(Cliente client, String contrasenia) {
		WebTarget deleteClienteTarget = loginTarget.path("deleteClient");
		Entity<Cliente> ent = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response resp = deleteClienteTarget.request(MediaType.TEXT_PLAIN).post(ent);
		
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			client.setContrasenya(contrasenia);
			WebTarget insertClientTarget = loginTarget.path("insertClient");
			Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
			Response response = insertClientTarget.request(MediaType.TEXT_PLAIN).post(entity);
			return response;
		} else {
			return null;
		}
	}
	
	public Response cambiarNicknameCliente(Cliente client, String nickname) {
		WebTarget deleteClienteTarget = loginTarget.path("deleteClient");
		Entity<Cliente> ent = Entity.entity(client, MediaType.APPLICATION_JSON);
		Response resp = deleteClienteTarget.request(MediaType.TEXT_PLAIN).post(ent);
		
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			client.setNickname(nickname);
			WebTarget insertClientTarget = loginTarget.path("insertClient");
			Entity<Cliente> entity = Entity.entity(client, MediaType.APPLICATION_JSON);
			Response response = insertClientTarget.request(MediaType.TEXT_PLAIN).post(entity);
			return response;
		} else {
			return null;
		}
	}
	
	public Response cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		WebTarget deleteClienteTarget = loginTarget.path("deleteCocheTaller");
		String matricula = coche.getMatricula();
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
		Response resp = deleteClienteTarget.request(MediaType.TEXT_PLAIN).post(ent);
		
		if (resp.getStatus() == Status.OK.getStatusCode()) {
			coche.setEstado(estado);
			WebTarget insertClientTarget = loginTarget.path("insertCocheTaller");
			Entity<CocheTaller> entity = Entity.entity(coche, MediaType.APPLICATION_JSON);
			Response response = insertClientTarget.request(MediaType.TEXT_PLAIN).post(entity);
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
		System.out.println(nickname);
		Response response = deleteComercialTarget.request(MediaType.TEXT_PLAIN).post(ent);
		return response;
	}
	
	public Response CocheTallerDelete(String matricula) {
		WebTarget deleteComercialTarget = loginTarget.path("deleteCocheTaller");
		Entity<String> ent = Entity.entity(matricula, MediaType.APPLICATION_JSON);
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
		System.out.println(nickname);
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
	
	public Response regsitroPieza(Pieza pieza) {
		WebTarget insertPiezaTarget = loginTarget.path("insertPiezas");
 		Entity<Pieza> entity = Entity.entity(pieza, MediaType.APPLICATION_JSON);
 		Response response = insertPiezaTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	
	public Response regsitroPresupuesto(Presupuesto presupuesto) {
		WebTarget insertPresupuestoTarget = loginTarget.path("insertPresupuesto");
 		Entity<Presupuesto> entity = Entity.entity(presupuesto, MediaType.APPLICATION_JSON);
 		Response response = insertPresupuestoTarget.request(MediaType.TEXT_PLAIN).post(entity);
 		return response;
	}
	
	public Response registroPiezaUtilizada(Pieza pieza, int unidades) {
		return null;
 		
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
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable(){
	        
	            @Override
	            public void run() {
	                ClienteApp cliente = new ClienteApp();
	                
	                Controller loginController = new Controller(cliente);
	                VentanaLogin vlogin = new VentanaLogin(loginController);
	                vlogin.setVisible(true);
	                
	            }
	        });
	    }

	
	}

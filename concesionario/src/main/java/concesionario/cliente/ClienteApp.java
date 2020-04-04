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

import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.CocheConcesionario;
import concesionario.servidor.datos.Comercial;
import concesionario.servidor.datos.DepartamentoCompras;
import concesionario.servidor.datos.Mecanico;
import concesionario.servidor.datos.Usuario;
import concesionario.servidor.datos.Venta;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;

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
	
	public Response clienteSelect(String nickname) {
		WebTarget selectClienteTarget = loginTarget.path("selectClient");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
		return response;
	}
	
	public List<CocheConcesionario> cargarTablaCochesConcesionario(){
		WebTarget loadTableTarget = loginTarget.path("loadTable");
		
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
	
	public Response registroVenta(Venta venta) {
		WebTarget insertVentaTarget = loginTarget.path("insertVenta");
		Entity<Venta> entity = Entity.entity(venta, MediaType.APPLICATION_JSON);
		Response response = insertVentaTarget.request(MediaType.TEXT_PLAIN).post(entity);
		return response;
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

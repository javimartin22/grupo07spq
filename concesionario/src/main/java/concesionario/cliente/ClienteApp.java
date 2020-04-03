package concesionario.cliente;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.cliente.ventana.VentanaMenuCliente;
import concesionario.cliente.ventana.VentanasRegistroClientes;

//Cambiar el Pom.xml para que luego nos runnee esta aplicaicon
public class ClienteApp {
	
	private static final long serialVersionUID = 1L;
	private Client client;
	
	private VentanaLogin vlogin;
	private VentanaMenuCliente vmc;
	private VentanasRegistroClientes vrc;
	
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
	
	public Response cargarTabla(){
		WebTarget loadTableTarget = loginTarget.path("loadTable");
		Entity<ResultSet> entity = Entity.entity(null, MediaType.APPLICATION_JSON);
		Response response = loadTableTarget.request(MediaType.TEXT_PLAIN).post(entity);
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
	
	public Response clienteSelect(String nickname) {
		WebTarget selectClienteTarget = loginTarget.path("selectClient");
		Entity<String> ent = Entity.entity(nickname, MediaType.APPLICATION_JSON);
		Response response = selectClienteTarget.request(MediaType.APPLICATION_JSON).post(ent);
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

package concesionario.cliente.controller;

import javax.ws.rs.core.Response;

import concesionario.cliente.ClienteApp;
import concesionario.servidor.datos.Usuario;

public class LoginController {
	
	public ClienteApp cliente;
	
	public LoginController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	public Response login (String email, String password) {
			Usuario usu = new Usuario(email,password,0);
			return cliente.login(usu);
		
	}
	

}

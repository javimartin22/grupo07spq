package concesionario.cliente.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Usuario;

public class LoginController {
	public ClienteApp cliente;

	public LoginController(ClienteApp cliente) {
		this.cliente = cliente;
	}

	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public int login(String email, String password) {
		Usuario usu = new Usuario(email,password,0);
		Response response = cliente.login(usu); //estoy aqui
		if(response.getStatus() == Status.OK.getStatusCode()) {
			String str = response.readEntity(String.class);
		    return Integer.parseInt(str);
		} else if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()){
			return 5;
		} else {
			return 6;
		}
		
		
	}
}


//Controller controller = new Controller(loginController.getClienteApp());
//
// }
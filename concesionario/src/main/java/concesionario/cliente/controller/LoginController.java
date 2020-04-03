package concesionario.cliente.controller;

import javax.ws.rs.core.Response;

import concesionario.cliente.ClienteApp;
import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.CocheConcesionario;
import concesionario.servidor.datos.Comercial;
import concesionario.servidor.datos.DepartamentoCompras;
import concesionario.servidor.datos.Mecanico;
import concesionario.servidor.datos.Usuario;

public class LoginController {
	
	public ClienteApp cliente;
	
	public LoginController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	public Response login(String email, String password) {
			Usuario usu = new Usuario(email,password,0);
			return cliente.login(usu);
		
	}
	
	public Response registrarCoche(String marca, String modelo, int precio) {
		CocheConcesionario auto = new CocheConcesionario(marca, modelo, precio);
		System.out.println("llega controller");
		System.out.println(auto.getMarca());
		return cliente.registrarCoche(auto);
	}
	
	public Response registroCliente(Cliente client) {
		return cliente.registroCliente(client);
	}
	
//	public Response cargarTabla () {
//		return cliente.cargarTabla();
//	}
	
	public Response registroMecanico(Mecanico mecanic) {
		return cliente.registroMecanico(mecanic);
	}
	
	public Response registroComercial(Comercial comercial) {
		return cliente.registroComercial(comercial);
	}
	
	public Response registroDepartamentoCompras(DepartamentoCompras dep) {
		return cliente.registroDepartamentoCompras(dep);
	}
	
	public Response cambiarContraseniaCliente(Cliente client, String nickname) {
		return cliente.cambiarContraseniaCliente(client, nickname);
	}
	
}

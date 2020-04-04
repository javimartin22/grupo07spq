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
	
	public Response registrarCoche(CocheConcesionario auto) {
		return cliente.registrarCoche(auto);
	}
	
	public Response registroCliente(Cliente client) {
		return cliente.registroCliente(client);
	}
	
	public Response registroMecanico(Mecanico mecanic) {
		return cliente.registroMecanico(mecanic);
	}
	
	public Response registroComercial(Comercial comercial) {
		return cliente.registroComercial(comercial);
	}
	
	public Response registroDepartamentoCompras(DepartamentoCompras dep) {
		return cliente.registroDepartamentoCompras(dep);
	}
	
	public Response cambiarContraseniaCliente(Cliente client, String contrasenia) {
		return cliente.cambiarContraseniaCliente(client, contrasenia);
	}
	
	public Response cambiarNicknameCliente(Cliente client, String nickname) {
		return cliente.cambiarNicknameCliente(client, nickname);
	}
	
	public Response seleccionarCliente(String nickname) {
		return cliente.clienteSelect(nickname);
	}
	
	public Response cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	
	public Response seleccionarMecanico(String nickname) {
		return cliente.mecanicoSelect(nickname);
	}
	
}

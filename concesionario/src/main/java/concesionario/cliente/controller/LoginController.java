package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import concesionario.cliente.ClienteApp;
import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.CocheConcesionario;
import concesionario.servidor.datos.CocheMatriculado;
import concesionario.servidor.datos.CocheTaller;
import concesionario.servidor.datos.Comercial;
import concesionario.servidor.datos.DepartamentoCompras;
import concesionario.servidor.datos.Empleado;
import concesionario.servidor.datos.Mecanico;
import concesionario.servidor.datos.Pieza;
import concesionario.servidor.datos.Usuario;
import concesionario.servidor.datos.Venta;

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
	
	public Response registrarCocheTaller(CocheTaller cocheTaller) {
		return cliente.registrarCocheTaller(cocheTaller);
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
	
	public List<CocheConcesionario> cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	
	public Response seleccionarMecanico(String nickname) {
		return cliente.mecanicoSelect(nickname);
	}
	
	public Response seleccionarComercial(String nickname) {
		return cliente.comercialSelect(nickname);
	}
	
	public Response seleccionarDepartamentoCompras(String nickname) {
		return cliente.departamentoComprasSelect(nickname);
	}
	
	public Response registrarVenta(Venta venta) {
		return cliente.registroVenta(venta);
	}
	
	public Response registroPieza(Pieza pieza) {
		return cliente.regsitroPieza(pieza);
	}
	
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	
	public Response seleccionarPiezaUtilizada(String codigo) {
		return cliente.piezaUtilizadaSelect(codigo);
	}
	
	public Response registroPiezaUtilizada(Pieza pieza, int unidades) {
		return cliente.registroPiezaUtilizada(pieza, unidades);
	}
	
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	
	public List<CocheMatriculado> cargarCochesMatriculados(){
		return cliente.cargarTablaCochesCocheMatriculados();
	}
	
	public List<Empleado> cargarTablaEmpleado(){
		return cliente.cargarTablaEmpleados();
	}
	
	public Response eliminarMecanico(String nickname) {
		return cliente.mecanicoDelete(nickname);
	}
	
	public Response eliminarComercial(String nickname) {
		return cliente.comercialDelete(nickname);
	}
	
	public Response eliminarDepartamentoCompras(String nickname) {
		return cliente.departamentoComprasDelete(nickname);
	}
	
	public List<CocheTaller> cargarTablaCocheTaller(){
		return cliente.cargarTablaCocheTaller();
	}
	
	public Response deleteCocheTaller(String matricula) {
		return cliente.CocheTallerDelete(matricula);
	}
	
	public List<Venta> cargarTablaVenta(){
		return cliente.cargarTablaVenta();
	}
	
	public Response seleccionarCocheConcesionario(String modelo) {
		return cliente.seleccionarCocheConcesionario(modelo);
	}
}

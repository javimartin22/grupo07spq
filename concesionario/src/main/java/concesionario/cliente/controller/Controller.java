package concesionario.cliente.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import concesionario.cliente.ClienteApp;
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

public class Controller {
	
	public ClienteApp cliente;
	
	public Controller(ClienteApp cliente) {
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
	
	public Response registroTarifa(Tarifa tarifa) {
		return cliente.registroTarifa(tarifa);
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
	
	public Response eliminarTarifa(String idTarifa) {
		return cliente.tarifaDelete(idTarifa);
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
	
	public Response registroPresupuesto(Presupuesto presupuesto) {
		return cliente.regsitroPresupuesto(presupuesto);
	}
	
	public Response seleccionarCocheTaller(String matricula) {
		return cliente.seleccionarCocheTaller(matricula);
	}
	
	public Response cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		return cliente.cambiarEstadoCocheTaller(coche, estado);
	}
	
	public List<Presupuesto> cargarTablaPresupuesto(){
		return cliente.cargarTablaPresupuestos();
	}
	
	public Response seleccionarPresupuesto(String codigo) {
		return cliente.seleccionarPresupuesto(codigo);
	}
	
	public Response filtrarTarifaPrecio(int precio) {
		return cliente.filtrarTarifaPrecio(precio);
	}
	
	public Response filtrarTarifaPrecioMin(int precio) {
		return cliente.filtrarTarifaPrecioMin(precio);
	}
	
	public Response filtrarTarifaHorasMax(int horas) {
		return cliente.filtrarTarifaHorasMax(horas);
	}
	
	public Response filtrarTarifaHorasMin(int horas) {
		return cliente.filtrarTarifaHorasMin(horas);
	}
	
	public List<Tarifa> cargarTablaTarifas(){
		return cliente.cargarTablaTarifas();
	}
	
	public Response filtrarVentaMarca(String marca) {
		return cliente.filtrarVentaMarca(marca);
	}
	
	public Response filtrarVentaModelo(String modelo) {
		return cliente.filtrarVentaModelo(modelo);
	}
	
	public Response filtrarVentaComercial(String comercial) {
		return cliente.filtrarVentaComercial(comercial);
	}
	
	public Response filtrarPresupuestoCodigo(String codigo) {
		return cliente.filtrarPresupuestoCodigo(codigo);
	}
	
	public Response filtrarPresupuestoCliente(String client) {
		return cliente.filtrarPresupuestoCliente(client);
	}
	
	public Response filtrarPresupuestoProblema(String problema) {
		return cliente.filtrarPresupuestoProblema(problema);
	}
	
	public Response filtrarCocheConcesionario(String filtro) {
		return cliente.filtrarCocheConcesionario(filtro);
	}
	
	public Response filtrarCocheMatriculado(String filtro) {
		return cliente.filtrarCocheMatriculado(filtro);
	}
	
	public Response filtrarPiezaMecanico(String filtro) {
		return cliente.filtrarPiezaMecanico(filtro);
	}
	
	public Response filtrarPiezaUtilizadas(String filtro) {
		return cliente.filtrarPiezaUtilizadas(filtro);
	}
}

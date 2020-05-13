package concesionario.cliente.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.CitaTaller;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.CocheMatriculado;
import concesionario.datos.CocheTaller;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.SolicitudCompra;

public class MecanicoController {
	
	public ClienteApp cliente;
	
	public MecanicoController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public boolean registrarCocheTaller(CocheTaller cocheTaller) {
		Response response = cliente.registrarCocheTaller(cocheTaller);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	
	
	public List<HerramientasTaller> cargarHerramientasTaller(){
		return cliente.cargarTablaHerramientasTaller();
	}
	
	
	public List<Herramientas> cargarListaHerramientas(){
		return cliente.cargarListaHerramientas();
	}
	
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	
	public List<CocheMatriculado> cargarCochesMatriculados(){
		return cliente.cargarTablaCochesCocheMatriculados();
	}
	
	public List<CocheTaller> cargarTablaCocheTaller(){
		return cliente.cargarTablaCocheTaller();
	}
	
	public boolean deleteCocheTaller(String matricula) {
		Response response = cliente.CocheTallerDelete(matricula); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroPresupuesto(Presupuesto presupuesto) {
		Response response = cliente.registroPresupuesto(presupuesto); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroSolicitud(SolicitudCompra solicitud) {
		Response response = cliente.registroSolicitud(solicitud); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registroMecanico(Mecanico mecanico) {
		Response response = cliente.registroMecanico(mecanico); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteMecanico(String nickname) {
		Response response = cliente.mecanicoDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public CocheTaller seleccionarCocheTaller(String matricula) {
		Response response = cliente.seleccionarCocheTaller(matricula);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(CocheTaller.class);
		} else {
			return null;
		}
	}
	
	public Mecanico seleccionarMecanico(String nickname) {
		Response response = cliente.mecanicoSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Mecanico.class);
		} else {
			return null;
		}
	}
	
	public boolean cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		Response response = cliente.cambiarEstadoCocheTaller(coche, estado); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Presupuesto> cargarTablaPresupuesto(){
		return cliente.cargarTablaPresupuestos();
	}
	
	public Presupuesto seleccionarPresupuesto(String codigo) {
		Response response = cliente.seleccionarPresupuesto(codigo); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Presupuesto.class);
		} else {
			return null;
		}
	}
	
	public List<Presupuesto> filtrarPresupuestoCodigo(String codigo) {
		Response response_1 = cliente.filtrarPresupuestoCodigo(codigo);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	
	public List<Presupuesto> filtrarPresupuestoCliente(String client) {
		Response response_1 = cliente.filtrarPresupuestoCliente(client);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Presupuesto> filtrarPresupuestoProblema(String problema) {
		Response response_1 = cliente.filtrarPresupuestoProblema(problema);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CocheMatriculado> filtrarCocheMatriculado(String filtro) {
		Response response = cliente.filtrarCocheMatriculado(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheMatriculado>> genericType = new GenericType<List<CocheMatriculado>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	public List<HerramientasTaller> filtrarHerramientaMecanico(String filtro) {
		Response response = cliente.filtrarHerramientaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<HerramientasTaller>> genericType = new GenericType<List<HerramientasTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Pieza> filtrarPiezaMecanico(String filtro) {
		Response response = cliente.filtrarPiezaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CocheTaller> filtrarCocheTaller(String filtro) {
		Response response = cliente.filtrarCocheTaller(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheTaller>> genericType = new GenericType<List<CocheTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	public String parseFecha() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE); 
		return fecha;
	}
	
	public String crearCodigo(List<Presupuesto> presupuestos) {
		String codigo = "";
		if (presupuestos.size() != 0) {
			int numero = presupuestos.size() + 1;
			codigo = "P" + numero;
		} else {
			codigo = "P1";
		}
		return codigo;
	}
	
	public String traducirEstado(int estado) {
		String estad = "";
		switch (estado) {
		case 0:
			estad = "Sin Empezar";
			break;
		case 1:
			estad = "En proceso";
			break;
		case 2:
			estad = "Terminado";
			break;
		}
		return estad;
	}
	
	public String parseFechaPresupuesto() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " - Bilbao"; 
		return fecha;
	}
	
	public List<ClienteFidelidad> cargarClienteFidelidad(){
		return cliente.cargarTablaClientesFidelidad();
	}
	
	public List<SolicitudCompra> cargarSolicitud(){
		return cliente.cargarTablaSolicitudCompra();
	}
	
	
	
	public List<CitaTaller> cargarCitaMecanico(String mecanico) {
		Response response_1 = cliente.cargarCitaMecanico(mecanico);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaTaller>> genericType = new GenericType<List<CitaTaller>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CitaTaller> filtrarCitaMecanico(String filtro) {
		Response response_1 = cliente.filtrarCitaMecanico(filtro);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaTaller>> genericType = new GenericType<List<CitaTaller>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	public boolean registrarHorasMecanico(String horasEmpleado) {
		Response response = cliente.registroHorasEmpleado(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registrarHorasMecanicoTemporal (String horasEmpleado) {
		Response response = cliente.registroHorasEmpleadoTemporal(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public HorasEmpleados seleccionarHorasMecanicoTemporal(String nickname) {
		Response response = cliente.seleccionarHorsEmpleadoTemporal(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	public HorasEmpleados seleccionarHorasMecanico(String nickname) {
		Response response = cliente.seleccionarHorsEmpleado(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	public boolean deleteHorasEmpleados(String nickname) {
		Response response = cliente.horasEmpleadosDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteHorasEmpleadosTemporal(String nickname) {
		Response response = cliente.horasEmpleadosTemporalDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteCitaMecanico(String nickFecha) {
		Response response = cliente.citasMecanicoDelete(nickFecha); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String calcularCodigoSolicitud(List<SolicitudCompra> solicitud) {
		int numero = solicitud.size() + 1;
		return "S" + numero;
	}
}

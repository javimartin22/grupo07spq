package concesionario.cliente.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.ClienteApp;
import concesionario.datos.CitaComercial;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Comercial;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Venta;

public class ComercialController {
	private ClienteApp cliente;
	
	public ComercialController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	public boolean registrarCoche(CocheConcesionario auto) {
		Response response = cliente.registrarCoche(auto);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<CocheConcesionario> cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	
	public boolean registrarVenta(Venta venta) {
		Response response = cliente.registroVenta(venta); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
		return false;
		}
	}
	
	public List<Venta> cargarTablaVenta(){
		return cliente.cargarTablaVenta();
	}
	
	public List<Venta> filtrarVentaMarca(String marca) {
		Response response_1 = cliente.filtrarVentaMarca(marca);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Venta> filtrarVentaModelo(String modelo) {
		Response response_1 = cliente.filtrarVentaModelo(modelo);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<Venta> filtrarVentaComercial(String comercial) {
		Response response_1 = cliente.filtrarVentaComercial(comercial);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CocheConcesionario> filtrarCocheConcesionario(String filtro) {
		Response response = cliente.filtrarCocheConcesionario(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CitaComercial> cargarCitaComercial(String comercial) {
		Response response_1 = cliente.cargarCitaComercial(comercial);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaComercial>> genericType = new GenericType<List<CitaComercial>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public List<CitaComercial> filtrarCitaComercial(String filtro) {
		Response response_1 = cliente.filtrarCitaComercial(filtro);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaComercial>> genericType = new GenericType<List<CitaComercial>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	public String comprobarColor(int c) {
		String color = "";
		switch (c) {
			case 0:
				color = "Rojo";
				break;
			case 1:
				color = "Azul";
				break;
			case 2:
				color = "Plata";
				break;
			case 3:
				color = "Amarillo";
				break;
			case 4:
				color = "Verde";
				break;
			case 5: 
				color = "Blanco";
				break;
			case 6: 
				color = "Negro";
				break;
			case 7:
				color = "Blanco Marfil";
				break;
			case 8: 
				color = "Gris";
				break;
		}
		return color;
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
	
	public boolean registrarHorasComercial(String horasEmpleado) {
		Response response = cliente.registroHorasEmpleado(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean registrarHorasComercialTemporal (String horasEmpleado) {
		Response response = cliente.registroHorasEmpleadoTemporal(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public HorasEmpleados seleccionarHorasComercialTemporal(String nickname) {
		Response response = cliente.seleccionarHorsEmpleadoTemporal(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	public HorasEmpleados seleccionarHorasComercial(String nickname) {
		Response response = cliente.seleccionarHorsEmpleado(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	public Comercial seleccionarComercial(String nickname) {
		Response response = cliente.comercialSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Comercial.class);
		} else {
			return null;
		}
	}
	
	public boolean registroComercial(Comercial comercial) {
		Response response = cliente.registroComercial(comercial); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteComercial(String nickname) {
		Response response = cliente.comercialDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
}

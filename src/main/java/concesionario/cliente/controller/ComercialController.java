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

/**
 * Controller ComercialController (Controller para las clases de Comercial)
 */
public class ComercialController {
	private ClienteApp cliente;
	
	/**
	 * Contructor de la clase ComercialController
	 * @param cliente ClienteApp
	 */
	public ComercialController(ClienteApp cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Metodo para inicializar el ClienteApp
	 * @return cliente ClienteApp
	 */
	public ClienteApp getClienteApp() {
		return this.cliente;
	}
	
	/**
	 * Metodo para registrar el coche en el concesionario
	 * @param auto Objeto CocheConcesioranio el cual se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarCoche(CocheConcesionario auto) {
		Response response = cliente.registrarCoche(auto);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para cargar todos los coches del concesionario que se dispone
	 * @return List<CocheConcesionario> Lista de objetos de tipo CocheConcesionario
	 */
	public List<CocheConcesionario> cargarTablaCochesConcesionario() {
		return cliente.cargarTablaCochesConcesionario();
	}
	
	/**
	 * Metodo para registrar una venta 
	 * @param venta Objeto Venta el cual se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarVenta(Venta venta) {
		Response response = cliente.registroVenta(venta); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
		return false;
		}
	}
	
	/**
	 * Metodo para cargar todas las ventas que se disponen
	 * @return List<Venta> Lista de objetos tipo Venta
	 */
	public List<Venta> cargarTablaVenta(){
		return cliente.cargarTablaVenta();
	}
	
	/**
	 * Metodopara filtrar las ventas por marca
	 * @param marca Filtro por el cual se quiere filtrar las ventas
	 * @return List<Venta> Lista de objetos de tipo Venta
	 */
	public List<Venta> filtrarVentaMarca(String marca) {
		Response response_1 = cliente.filtrarVentaMarca(marca);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar las ventas por modelo
	 * @param modelo Filtro por el cual se quiere filtrar las ventas
	 * @return List<Venta> Lista de objetos de tipo Venta
	 */
	public List<Venta> filtrarVentaModelo(String modelo) {
		Response response_1 = cliente.filtrarVentaModelo(modelo);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar las las ventas segun el comercial 
	 * @param comercial Filtro por el cual se quiere filtrar las ventas
	 * @return List<Venta> Lista de objetos de tipo Venta
	 */
	public List<Venta> filtrarVentaComercial(String comercial) {
		Response response_1 = cliente.filtrarVentaComercial(comercial);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Venta>> genericType = new GenericType<List<Venta>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para la filtrar los coches del concesionario en funcion de un filtro
	 * @param filtro Filtro a partir de distintos parametos: marca, color, cv, precio
	 * @return List<CocheConcesionario> Lista de objetos de tipo CocheConcesionario
	 */
	public List<CocheConcesionario> filtrarCocheConcesionario(String filtro) {
		Response response = cliente.filtrarCocheConcesionario(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para cargar las citas del comercial 
	 * @param comercial Nickname del comercial para cargar sus citas
	 * @return List<CitaComercial> Lista de objetos de tipo CitaComercial
	 */
	public List<CitaComercial> cargarCitaComercial(String comercial) {
		Response response_1 = cliente.cargarCitaComercial(comercial);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaComercial>> genericType = new GenericType<List<CitaComercial>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar las citas del comerfial segun la fecha de la cita
	 * @param filtro Fecha de la cita que se desea para filtrar las citas
	 * @return List<CitaComercial> Lista de objetos de tipo CitaComercial
	 */
	public List<CitaComercial> filtrarCitaComercial(String filtro) {
		Response response_1 = cliente.filtrarCitaComercial(filtro);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaComercial>> genericType = new GenericType<List<CitaComercial>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para comprobar el color 
	 * @param c Numero que se compara para saber el color. Si 0=Rojo, 1=Azul, 2=Plata, 3=Amarillo, 4=Verde, 5=Blanco, 6=Negro, 7=Blanco Marfil y 8=Gris
	 * @return color Devuelve un String con el color 
	 */
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
	
	/**
	 * Metodo para validar el formato de la fecha
	 * @param fecha Fecha que se desea validad
	 * @return boolean Si el formato de la fecha es correcto, devuelve un True, si no es correcto devuelve un False
	 */
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
	
	/**
	 * Metodo para eliminar Objetos de tipo EmpleadoHoras
	 * @param nickname Nickname del empleado que se quiere eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteHorasEmpleados(String nickname) {
		Response response = cliente.horasEmpleadosDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para eliminar las horas de los empleados temporales 
	 * @param nickname Nombre de usuario del empelado temporal que se quiere eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteHorasEmpleadosTemporal(String nickname) {
		Response response = cliente.horasEmpleadosTemporalDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar las horas de los comerciales
	 * @param horasEmpleado Horas del comercial que se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarHorasComercial(String horasEmpleado) {
		Response response = cliente.registroHorasEmpleado(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar las horas de los comerciales temporales
	 * @param horasEmpleado Horas que se desean registrar de los comerciales temporales
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarHorasComercialTemporal (String horasEmpleado) {
		Response response = cliente.registroHorasEmpleadoTemporal(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para obtener las horas de los comerciales temporales
	 * @param nickname Nickname del comercial temporal que se desee obtener sus horas
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public HorasEmpleados seleccionarHorasComercialTemporal(String nickname) {
		Response response = cliente.seleccionarHorsEmpleadoTemporal(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para obtener las horas de los comerciales
	 * @param nickname Nickname del comercial que se desee  obtener sus horas
	 * @return HorasEmpleado Devuelve las horas del Empleado
	 */
	public HorasEmpleados seleccionarHorasComercial(String nickname) {
		Response response = cliente.seleccionarHorsEmpleado(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para obtener comerciales
	 * @param nickname Nickname del comercial que se desee obtener
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public Comercial seleccionarComercial(String nickname) {
		Response response = cliente.comercialSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Comercial.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para registrar los comerciales
	 * @param comercial Comercial que se desee registrar 
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroComercial(Comercial comercial) {
		Response response = cliente.registroComercial(comercial); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para elimiar objetos de tipo Comercial
	 * @param nickname Nickname del comercial que se desee eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteComercial(String nickname) {
		Response response = cliente.comercialDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para eliminar las citas del Comercial
	 * @param nickFecha Nickname y Fecha del comercial que se desee elimarn sus citas 
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteCitaComercial(String nickFecha) {
		Response response = cliente.citasComercialDelete(nickFecha); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
}

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
	
	/**
	 * Contructor de la clase MecanicoController
	 * @param cliente ClienteApp
	 */
	public MecanicoController(ClienteApp cliente) {
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
	 * Metodo para registrar coches en el taller
	 * @param cocheTaller Objeto de tipo CocheTaller el cual se deasea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarCocheTaller(CocheTaller cocheTaller) {
		Response response = cliente.registrarCocheTaller(cocheTaller);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para cargar todas las piezas disponibles 
	 * @return List<Piezas> Lista de objetos de tipo Pieza
	 */
	public List<Pieza> cargarPiezas(){
		return cliente.cargarTablaPiezas();
	}
	
	/**
	 * Metodo para cargar las herramientas que se disponen en el taller
	 * @return List<HerramientaTaller> Lista de objetos de tipo HerramientaTaller
	 */
	public List<HerramientasTaller> cargarHerramientasTaller(){
		return cliente.cargarTablaHerramientasTaller();
	}
	
	/**
	 * Metodo para cargar una lista de herramientas
	 * @return List<Herramientas> Lista de objetos de tipo Herramienta
	 */
	public List<Herramientas> cargarListaHerramientas(){
		return cliente.cargarListaHerramientas();
	}
	
	/**
	 * Metodo para cargar las piezas utilizadas en el taller
	 * @return List<Peiza> Lista de objetos de tipo Pieza
	 */
	public List<Pieza> cargarPiezasUtilizadas(){
		return cliente.cargarTablaPiezasUtilizadas();
	}
	
	/**
	 * Metodo para cargar los coches matriculados 
	 * @return List<CocheMatriculado> Lista de objetos de tipo CocheConcesionario
	 */
	public List<CocheMatriculado> cargarCochesMatriculados(){
		return cliente.cargarTablaCochesCocheMatriculados();
	}
	
	/**
	 * Metodo para cargar los coches del Taller
	 * @return List<CocheTaller> Lista de objetos de tipo CocheTaller
	 */
	public List<CocheTaller> cargarTablaCocheTaller(){
		return cliente.cargarTablaCocheTaller();
	}
	
	/**
	 * Metodo para eliminar los coches del taller
	 * @param matricula Matricula del coche que se desea eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteCocheTaller(String matricula) {
		Response response = cliente.CocheTallerDelete(matricula); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar los presupuestos
	 * @param presupuesto Objeto de tipo Presupuesto que se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroPresupuesto(Presupuesto presupuesto) {
		Response response = cliente.registroPresupuesto(presupuesto); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar una Solicitud de Compra
	 * @param solicitud Objeto de tipo SolicitudCompra que se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroSolicitud(SolicitudCompra solicitud) {
		Response response = cliente.registroSolicitud(solicitud); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar los Mecanicos
	 * @param mecanico Objeto de tipo Mecanico que se desea registrar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registroMecanico(Mecanico mecanico) {
		Response response = cliente.registroMecanico(mecanico); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para eliminarl los Mecanicos
	 * @param nickname Nickname del mecanico que se desea eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteMecanico(String nickname) {
		Response response = cliente.mecanicoDelete(nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para obtener los Coches del Taller segun la Matricula
	 * @param matricula Matricula de coche que se quiere obtener
	 * @return CocheTaller Objeto CocheTaller con la matricula suministrada
	 */
	public CocheTaller seleccionarCocheTaller(String matricula) {
		Response response = cliente.seleccionarCocheTaller(matricula);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(CocheTaller.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para obtener los Mecanicos segun el Nickname
	 * @param nickname Nickname del mecanico que se quiere obtener
	 * @return Mecanico Objeto Mecanico con el nickname suministrado
	 */
	public Mecanico seleccionarMecanico(String nickname) {
		Response response = cliente.mecanicoSelect(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Mecanico.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para cambiar el Estado del Coche en el Taller
	 * @param coche Objeto Coche al que se desea cambiar el estado en el taller
	 * @param estado Nuevo estado al que se desea cambiar el coche
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean cambiarEstadoCocheTaller(CocheTaller coche, int estado) {
		Response response = cliente.cambiarEstadoCocheTaller(coche, estado); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para cargar los presutuestos 
	 * @return List<Presupuesto> Lista de objetos de tipo Presuspuesto
	 */
	public List<Presupuesto> cargarTablaPresupuesto(){
		return cliente.cargarTablaPresupuestos();
	}
	
	/**
	 * Metodo para selecionar los presupuestos segun el codigo
	 * @param codigo Codigo del presupuesto que se quiere obtener
	 * @return Presupuesto Objeto Presupuesto con el codigo suministrado
	 */
	public Presupuesto seleccionarPresupuesto(String codigo) {
		Response response = cliente.seleccionarPresupuesto(codigo); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(Presupuesto.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar los presupuestos segun el codigo
	 * @param codigo Codigo del presupuesto del que se desea fitlrar
	 * @return List<Presupuesto> Lista de objetos de tipo Presupuesto
	 */
	public List<Presupuesto> filtrarPresupuestoCodigo(String codigo) {
		Response response_1 = cliente.filtrarPresupuestoCodigo(codigo);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar los presupuestos segun el cliente
	 * @param client Cliente el cual se desean filtrar sus presupuestos
	 * @return List<Presuspuesto> Lista de objetos de tipo Presupuesto
	 */
	public List<Presupuesto> filtrarPresupuestoCliente(String client) {
		Response response_1 = cliente.filtrarPresupuestoCliente(client);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar los presuspuestos segun el problema
	 * @param problema Problema que se usa para filtrar los presupuestos
	 * @return List<Presuspuesto> Lista de objetos de tipo Presupuesto
	 */
	public List<Presupuesto> filtrarPresupuestoProblema(String problema) {
		Response response_1 = cliente.filtrarPresupuestoProblema(problema);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Presupuesto>> genericType = new GenericType<List<Presupuesto>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar los Coches Matriculados
	 * @param filtro Filtro para los coches matriculados, puede ser segun la matricula, la marca, o por nombre del cliente
	 * @return List<CocheMatriculado> Lista de objetos de tipo CocheMatriculado
	 */
	public List<CocheMatriculado> filtrarCocheMatriculado(String filtro) {
		Response response = cliente.filtrarCocheMatriculado(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheMatriculado>> genericType = new GenericType<List<CocheMatriculado>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar las herramientas del Mecanico
	 * @param filtro Filtro para las herramientas del mecanico, puede ser segun el codigo de la herramienta, segun el nombre, o segun el stock
	 * @return List<HerramientasTaller> Lista de objetos de tipo HerramientasTaller
	 */
	public List<HerramientasTaller> filtrarHerramientaMecanico(String filtro) {
		Response response = cliente.filtrarHerramientaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<HerramientasTaller>> genericType = new GenericType<List<HerramientasTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar las piezas del Mecanico
	 * @param filtro Filtro para las piezas del mecanico, puede ser segun el codigo de la pieza, el nombre o el stock
	 * @return  List<Pieza> Lista de objetos de tipo Pieza
	 */
	public List<Pieza> filtrarPiezaMecanico(String filtro) {
		Response response = cliente.filtrarPiezaMecanico(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<Pieza>> genericType = new GenericType<List<Pieza>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar los coches del taller
	 * @param filtro Filtro para fultrar los coches, puede ser segun la matricula del coches, el mecanico, el cotes o es estado del coche
	 * @return List<CocheTaller> Lista de objetos de tipo CocheTaller
	 */
	public List<CocheTaller> filtrarCocheTaller(String filtro) {
		Response response = cliente.filtrarCocheTaller(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheTaller>> genericType = new GenericType<List<CocheTaller>>() {};
			return response.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	
	/**
	 * Metodo para parsear la fecha
	 * @return fecha String con la fecha en su formato
	 */
	public String parseFecha() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE); 
		return fecha;
	}
	
	/**
	 * Metodo para crear los codigos del presupuesto
	 * @param presupuestos Lista de objetos de tipo Presupuesto
	 * @return codigo String con el codigo generado
	 */
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
	
	/**
	 * Metodo para traducir el estado del coche
	 * @param estado Estado numerido que hay que traducir a un String, estado=0 sin empezar, estado=1 en proceso, estado=2 terminado
	 * @return estad Estado en String 
	 */
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
	
	/**
	 * Metodo para parsear la fecha de los presupuestos
	 * @return fecha Fecha con el formato correcto
	 */
	public String parseFechaPresupuesto() {
		String fecha = "";
		Calendar c = Calendar.getInstance();
		fecha = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " - " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " - Bilbao"; 
		return fecha;
	}
	
	/**
	 * Metodo para cargar la fidelidad de los clientes
	 * @return List<ClienteFidelidad> Lista de objetos de tipo ClienteFidelidad
	 */
	public List<ClienteFidelidad> cargarClienteFidelidad(){
		return cliente.cargarTablaClientesFidelidad();
	}
	
	/**
	 * Metodo para cargar las solicitudes de compra
	 * @return List<SolicitudCompra> Lista de objetos de tipo SolicitudCompra
	 */
	public List<SolicitudCompra> cargarSolicitud(){
		return cliente.cargarTablaSolicitudCompra();
	}
	
	/**
	 * Metodo para cargar las citas de los mecanicos
	 * @param mecanico Nickname del mecanico del que se desea obtener las citas
	 * @return List<CitaTaller> Lista de objetos de tipo CitaTaller
	 */
	public List<CitaTaller> cargarCitaMecanico(String mecanico) {
		Response response_1 = cliente.cargarCitaMecanico(mecanico);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaTaller>> genericType = new GenericType<List<CitaTaller>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para filtrar citas de los mecanicos
	 * @param filtro Filtro compuesto por nickname del mecanico y la fecha que se desea filtrar
	 * @return List<CitaTaller> Lista de objetos de tipo CitaTaller
	 */
	public List<CitaTaller> filtrarCitaMecanico(String filtro) {
		Response response_1 = cliente.filtrarCitaMecanico(filtro);
		if(response_1.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CitaTaller>> genericType = new GenericType<List<CitaTaller>>() {};
			return response_1.readEntity(genericType);
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo para validar el formato de la fecha
	 * @param fecha Fecha que se desea comprar su formato
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
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
	 * Metodo para registrar las horas de los mecanicos
	 * @param horasEmpleado Horas y el nickname del empleado que se quiere registrar las horas
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarHorasMecanico(String horasEmpleado) {
		Response response = cliente.registroHorasEmpleado(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para registrar las horas de los mecanicos temporales
	 * @param horasEmpleado Horas y Nickname del empleado que se desee registrar las horas
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean registrarHorasMecanicoTemporal (String horasEmpleado) {
		Response response = cliente.registroHorasEmpleadoTemporal(horasEmpleado);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo par seleccionar las horas de un mecanico temporal
	 * @param nickname Nickname del mecanico temporal que se desee obtener sus horas
	 * @return HorasEmpleados Objeto HorasEmpleados con el Nickname suministrado
	 */
	public HorasEmpleados seleccionarHorasMecanicoTemporal(String nickname) {
		Response response = cliente.seleccionarHorsEmpleadoTemporal(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para seleccionar las horas de un mecanico
	 * @param nickname Nickname del mecanico que se quiere obtener las horas
	 * @return HorasEmpleados Objeto HorasEmpleados con el Nickname suministrado
	 */
	public HorasEmpleados seleccionarHorasMecanico(String nickname) {
		Response response = cliente.seleccionarHorsEmpleado(nickname);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return response.readEntity(HorasEmpleados.class);
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo para eliminar las horas de los empleados
	 * @param nickname Nickname del empleado que se desee eliminar las horas
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
	 * @param nickname Nickname de los empleados temporales que se desee eliminar las horas
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
	 * Metodo para eliminar las citas de los mcanicos
	 * @param nickFecha Nickname del mecanico junto a la fecha que se desee eliminar
	 * @return boolean Devuelve true si el proceso fue exitoso, falso si no fue posible
	 */
	public boolean deleteCitaMecanico(String nickFecha) {
		Response response = cliente.citasMecanicoDelete(nickFecha); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo para calcular el codigo de solicitud de compra
	 * @param solicitud Lista de objetos de tipo SolicitudCompra
	 * @return String Codigo calculado
	 */
	public String calcularCodigoSolicitud(List<SolicitudCompra> solicitud) {
		int numero = solicitud.size() + 1;
		return "S" + numero;
	}
}

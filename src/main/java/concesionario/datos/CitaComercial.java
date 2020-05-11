package concesionario.datos;


/**
 * Objeto CitaComercial
 */
public class CitaComercial {
	
	private String nombre;
	private String dniCliente;
	private String fecha;
	private String hora;
	private String comercial;
	
	/**
	 * Construcctor Vacio de la clase CitaComercial.
	 */
	public CitaComercial() {
	}

	/**
	 * Constructor de la clase CitaComercial.
	 * @param nombre Nombre del Usuario
	 * @param dniCliente DNI del Usuario
	 * @param fecha Fecha seleccionada
	 * @param hora Hora seleccionada
	 * @param comercial Nombre del Comercial
	 */
	public CitaComercial(String nombre, String dniCliente, String fecha, String hora, String comercial) {
		this.nombre = nombre;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.hora = hora;
		this.comercial = comercial;
	}
	
	/**
	 * Metodo para la obtencion del nombre.
	 * @return String (Nombre)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param String (Nombre)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del DNI.
	 * @return String (DNI)
	 */
	public String getDniCliente() {
		return dniCliente;
	}
	/**
	 * Metodo para la modificacion del DNI.
	 * @param String (DNI)
	 */
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	/**
	 * Metodo para la obtencion de la Fecha.
	 * @return String (Fecha)
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Metodo para la modificacion de la Fecha.
	 * @param String (Fecha)
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * Metodo para la obtencion de la Hora.
	 * @return String (Hora)
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * Metodo para la modificacion de la Hora.
	 * @param String (Hora)
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * Metodo para la obtencion del Nombre del Comercial.
	 * @return String (Nombre Comercial)
	 */
	public String getComercial() {
		return comercial;
	}
	/**
	 * Metodo para la modificacion del Nombre del Comercial.
	 * @param String (Nombre Comercial)
	 */
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
}

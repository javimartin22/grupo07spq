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
	 * @return nombre (Nombre del Cliente)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param nombre (Nombre del Cliente)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del DNI.
	 * @return dni (DNI del Cliente)
	 */
	public String getDniCliente() {
		return dniCliente;
	}
	/**
	 * Metodo para la modificacion del DNI.
	 * @param dniCliente (DNI del Cliente)
	 */
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	/**
	 * Metodo para la obtencion de la Fecha.
	 * @return fecha (Fecha Seleccionada)
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Metodo para la modificacion de la Fecha.
	 * @param fecha (Fecha Seleccionada)
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * Metodo para la obtencion de la Hora.
	 * @return hora (Hora Seleccionada)
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * Metodo para la modificacion de la Hora.
	 * @param hora (Hora Seleccionada)
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * Metodo para la obtencion del Nombre del Comercial.
	 * @return comercial (Nombre Comercial)
	 */
	public String getComercial() {
		return comercial;
	}
	/**
	 * Metodo para la modificacion del Nombre del Comercial.
	 * @param comercial (Nombre Comercial)
	 */
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
}

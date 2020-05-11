package concesionario.datos;

/**
 * Objeto CitaTaller
 */
public class CitaTaller {
	
	private String nombre;
	private String dniCliente;
	private String fecha;
	private String hora;
	private String comercial;
	private String problema;
	
	/**
	 * Construcctor Vacio de la clase CitaTaller.
	 */
	public CitaTaller() {
	}

	/**
	 * Constructor de la clase CitaTaller.
	 * @param nombre
	 * @param dniCliente
	 * @param fecha
	 * @param hora
	 * @param comercial
	 * @param problema
	 */
	public CitaTaller(String nombre, String dniCliente, String fecha, String hora, String comercial, String problema) {
		super();
		this.nombre = nombre;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.hora = hora;
		this.comercial = comercial;
		this.problema = problema;
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
	/**
	 * Metodo para la obtencion del Problema del Cliente
	 * @return String (Problema)
	 */
	public String getProblema() {
		return problema;
	}
	/**
	 * Metodo para la modificacion del Problema del Cliente
	 * @param String (Problema)
	 */
	public void setProblema(String problema) {
		this.problema = problema;
	}
}

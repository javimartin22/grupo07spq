package concesionario.datos;

/**
 * Objeto ClienteFidelidad (Objeto utilizado para obtener la fidelidad de un Cliente de la Base de Datos)
 */
public class ClienteFidelidad  {

	private String dni;
	private int fidelidad;
	
	/**
	 * Constructor vacio de la clase ClienteFidelidad.
	 */
	public ClienteFidelidad() {
	}

	/**
	 * Constructor de la clase ClienteFidelidad.
	 * @param dni (DNI del Cliente)
	 * @param fidelidad (Fidelidad del Cliente)
	 */
	public ClienteFidelidad(String dni, int fidelidad) {
		this.dni = dni;
		this.fidelidad = fidelidad;
	}
	
	/**
	 * Metodo para la obtencion del DNI del Cliente.
	 * @return dni (DNI del Cliente)
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Metodo para la modificacion del DNI del Cliente
	 * @param dni (DNI del Cliente)
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * Metodo para la obtencion de la Fidelidad del Cliente.
	 * @return fidelidad (Fidelidad del Cliente)
	 */
	public int getFidelidad() {
		return fidelidad;
	}
	
	/**
	 * Metodo para la modificacion de la Fidelidad del Cliente
	 * @param fidelidad (Fidelidad del Cliente)
	 */
	public void setFidelidad(int fidelidad) {
		this.fidelidad = fidelidad;
	}	
}
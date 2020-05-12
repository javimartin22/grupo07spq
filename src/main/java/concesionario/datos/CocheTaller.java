package concesionario.datos;

/**
 * Objeto CocheTaller (Objeto para la representacion de los coches que se encuentran en el taller o coches que ya han estado en el taller).
 * @author pablo
 *
 */
public class CocheTaller {
	private String matricula;
	private String marca;
	private String modelo;
	private String mecanico;
	private String dniCliente;
	private double coste; 
	private int estado;
	
	
	/**
	 * Constructor Vacio de la clase CocheTaller.
	 */
	public CocheTaller() {
	}
	
	/**
	 * Constructor de la clase CocheTaller
	 * @param matricula (Matricula del Vehiculo)
	 * @param marca (Marca del Vehiculo)
	 * @param modelo (Modelo del Vehiculo)
	 * @param mecanico (Mecanico Encargardo del Vehiculo)
	 * @param dniCliente (DNI del Dueño del Vehiculo)
	 * @param coste (Coste de los Arreglos del Vehiculo)
	 * @param estado (Estado en el que se encuentra "Sin Empezar", "En Prceso", "Terminado")
	 */
	public CocheTaller(String matricula, String marca, String modelo, String mecanico, String dniCliente, double coste,
			int estado) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.mecanico = mecanico;
		this.dniCliente = dniCliente;
		this.coste = coste;
		this.estado = estado;
	}
	/**
	 * Metodo para obtencion de la Marca del Vehiculo
	 * @return marca (Marca del Vehiculo)
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * Metodo para modificacion de la Marca del Vehiculo
	 * @param marca (Marca del Vehiculo)
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * Metodo para obtencion del Modelo del Vehiculo
	 * @return marca (Modelo del Vehiculo)
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Metodo para modificacion del Modelo del Vehiculo
	 * @param modelo (Modelo del Vehiculo)
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 *Metodo para obtencion de la Matricula del Vehiculo
	 * @return matricula (Matricula del Vehiculo)
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * Metodo para modificacion de la Matricula del Vehiculo
	 * @param matricula (Matricula del Vehiculo)
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 *Metodo para obtencion del Matricula del Vehiculo
	 * @return mecanico (Mecanico del Vehiculo)
	 */
	public String getMecanico() {
		return mecanico;
	}
	/**
	 * Metodo para modificacion del Mecanico del Vehiculo
	 * @param mecanico (Mecanico del Vehiculo)
	 */
	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}
	/**
	 *Metodo para obtencion de la DNI del Dueño del Vehiculo
	 * @return dniCliente (DNI del Dueño del Vehiculo)
	 */
	public String getDniCliente() {
		return dniCliente;
	}
	/**
	 * Metodo para modificacion del DNI del Dueño del Vehiculo
	 * @param dniCliente (DNI del Dueño del Vehiculo)
	 */
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	/**
	 *Metodo para obtencion del Coste de las Reparaciones del Vehiculo
	 * @return coste (Coste de las Reparaciones del Vehiculo)
	 */
	public double getCoste() {
		return coste;
	}
	/**
	 * Metodo para modificacion del Coste de las Reparaciones del Vehiculo
	 * @param coste (Coste de la Reparaciones del Vehiculo)
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}
	/**
	 *Metodo para obtencion del Estado del Vehiculo
	 * @return estado (Estado del Vehiculo)
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * Metodo para modificacion del Estado del Vehiculo
	 * @param estado (Estado del Vehiculo)
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

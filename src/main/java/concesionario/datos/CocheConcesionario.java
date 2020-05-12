package concesionario.datos;

/**
 * Objeto CocheConcesionario (Objeto para la representacion de los coches que se pueden encontrar en el concesinario).
 */
public class CocheConcesionario {

	private String marca;
	private String modelo;
	private int precio;
	private int unidades;
	private int cv;
	private int numPuertas;
	private String color;

	/**
	 * Constructor de la Clase CocheConcesionario.
	 * @param marca (Marca del Vehiculo)
	 * @param modelo (Modelo del Vehiculo)
	 * @param precio (Precio del Vehiculo)
	 * @param cv (Caballos del Vehiculo)
	 * @param numPuertas (Numero de Puertas del Vehiculo)
	 * @param color (Color del Vehiculo)
	 * @param unidades (Unidades Disponibles)
	 */
	public CocheConcesionario(String marca, String modelo, int precio, int cv, int numPuertas, String color, int unidades) {
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.color = color;
		this.cv = cv;
		this.numPuertas = numPuertas;
		this.unidades = unidades;
	}
	
	/**
	 * Constructor vacio de la Clase CocheConcesionario.
	 */
	public CocheConcesionario() {
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
	 * Metodo para obtencion del Precio del Vehiculo
	 * @return precio (Precio del Vehiculo)
	 */
	public int getPrecio() {
		return precio;
	}
	/**
	 * Metodo para modificacion del Precio del Vehiculo
	 * @param precio (Precio del Vehiculo)
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	/**
	 * Metodo para obtencion de las Unidades Disponibles
	 * @return unidades (Unidades Disponibles)
	 */
	public int getUnidades() {
		return unidades;
	}
	/**
	 * Metodo para modificacion de las Unidades Disponibles
	 * @param unidades (Unidades Disponibles)
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	/**
	 * Metodo para obtencion de lo Caballos del Vehiculo
	 * @return cv (Caballos del Vehiculo)
	 */
	public int getCv() {
		return cv;
	}
	/**
	 * Metodo para modificacion del Caballos del Vehiculo
	 * @param cv (Caballos del Vehiculo)
	 */
	public void setCv(int cv) {
		this.cv = cv;
	}
	/**
	 * Metodo para obtencion del Numero de Puertas del Vehiculo
	 * @return numPuertas (Numero de Puertas del Vehiculo)
	 */
	public int getNumPuertas() {
		return numPuertas;
	}
	/**
	 * Metodo para modificacion del Numero de Puertas del Vehiculo
	 * @param numPuertas (Numero de Puertas del Vehiculo)
	 */
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}
	/**
	 * Metodo para obtencion del Color del Vehiculo
	 * @return color (Color del Vehiculo)
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Metodo para modificacion del Color del Vehiculo
	 * @param color (Color del Vehiculo)
	 */
	public void setColor(String color) {
		this.color = color;
	}
}

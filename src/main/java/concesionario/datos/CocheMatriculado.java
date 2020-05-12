package concesionario.datos;

/**
 * Objeto CocheMatriculado (Objeto para la representacion de los coches que han sido vendidos en el Concesionario).
 */
public class CocheMatriculado{
	
	private String marca;
	private String modelo;
	private String matricula;
	private String nombrePropietario;
	private String color;
	private int cv;
	private int numPuertas;
	private int anyoMatriculacion;
	private int revisiones;
	
	/**
	 * Constructor de la Clase CocheMatriculado.
	 * @param marca (Marca del Vehiculo)
	 * @param modelo (Modelo del Vehiculo)
	 * @param matricula (Matricula del Vehiculo)
	 * @param nombrePropietario (Nombre del Propietario del Vehiculo)
	 * @param color (Color del Vehiculo)
	 * @param numPuertas (Numero de Puertas del Vehiculo)
	 * @param anyo_matriculacion (Anio de Matriculacion del Vehiculo)
	 * @param cv (Caballos del Vehiculo)
	 * @param revisiones (Revisiones Realizadas del Vehiculo)
	 */
	public CocheMatriculado(String marca, String modelo, String matricula, String nombrePropietario, String color,
			int numPuertas, int anyo_matriculacion, int cv, int revisiones) {
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.nombrePropietario = nombrePropietario;
		this.color = color;
		this.numPuertas = numPuertas;
		this.anyoMatriculacion = anyo_matriculacion;
		this.revisiones = revisiones;
	}

	/**
	 * Constructor vacio de la Clase CocheMatriculado.
	 */
	public CocheMatriculado() {
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
	 *Metodo para obtencion del Anio de Matriculacion del Vehiculo
	 * @return anyoMatriculacion (Anio de Matriculacion del Vehiculo)
	 */
	public int getAnyoMatriculacion() {
		return anyoMatriculacion;
	}
	/**
	 * Metodo para modificacion del Anio deo Matriculacion del Vehiculo
	 * @param anyoMatriculacion (Anio de Matriculacion del Vehiculo)
	 */
	public void setAnyoMatriculacion(int anyo_matriculacion) {
		this.anyoMatriculacion = anyo_matriculacion;
	}
	/**
	 *Metodo para obtencion de las Revisiones Realizadas del Vehiculo
	 * @return revisiones (Revision Realizadas del Vehiculo)
	 */
	public int getRevisiones() {
		return revisiones;
	}
	/**
	 * Metodo para modificacion de las Revisiones Realizadas del Vehiculo
	 * @param revisiones (Revisiones Realizadas del Vehiculo)
	 */
	public void setRevisiones(int revisiones) {
		this.revisiones = revisiones;
	}
	/**
	 *Metodo para obtencion del Nombre del Propietario del Vehiculo
	 * @return nombrePropietario (Nombre del Propietario del Vehiculo)
	 */
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	/**
	 * Metodo para modificacion del Nombre del Propietario del Vehiculo
	 * @param nombrePropietario (Nombre del Propietario del Vehiculo)
	 */
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
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

package concesionario.datos;

/**
 * Objeto Pieza (Objeto para la gestion de las Piezas que se disponen en el taller).
 */
public class Pieza {
	private String codigo;
	private String nombre;
	private int unidades; 
	private String ubicacion;
	
	/**
	 * Constructor de la clase Pieza.
	 * @param codigo (Codigo de la Pieza)
	 * @param nombre (Nombre de la Pieza)
	 * @param unidades (Unidades Disponibles)
	 * @param ubicacion (Ubicacion de la Pieza)
	 */
	public Pieza(String codigo, String nombre, int unidades, String ubicacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidades = unidades;
		this.ubicacion = ubicacion;
	}
	
	/**
	 * Constructor vacio de la clase Pieza
	 */
	public Pieza() {
	}

	/**
	 * Metodo para la obtencion del Codigo de la Pieza.
	 * @return codigo (Codigo de la Pieza)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo para la modificacion del Codigo de la Pieza.
	 * @param codigo (Codigo de la Pieza)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Metodo para la obtencion del Nombre de la Pieza.
	 * @return nombre (Nombre de la Pieza)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del Nombre de la Pieza.
	 * @param nombre (Nombre de la Pieza)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del Unidades Disponibles.
	 * @return unidades (Unidades Disponibles)
	 */
	public int getUnidades() {
		return unidades;
	}
	/**
	 * Metodo para la modificacion del Unidades Disponibles.
	 * @param unidades (Unidades Disponibles)
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	/**
	 * Metodo para la obtencion del Ubicacion de la Pieza.
	 * @return ubicacion (Ubicacion de la Pieza)
	 */
	public String getUbicacion() {
		return ubicacion;
	}
	/**
	 * Metodo para la modificacion del Ubicacion de la Pieza.
	 * @param ubicacion (Ubicacion de la Pieza)
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}

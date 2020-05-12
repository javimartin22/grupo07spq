package concesionario.datos;

/**
 * Objeto HerramientasTaller (Objeto para la representacion de las herramientas que dispone un mecanico en el taller)
 */
public class HerramientasTaller {
	private String codigo;
	private String nombre;
	private int unidades; 
	private String ubicacion;
	
	/**
	 * Constructor de la clase HerramientasTaller.
	 * @param codigo Codigo  de la herramienta
	 * @param nombre Nombre de la herramienta
	 * @param unidades Unidades de la herramienta
	 * @param ubicacion Ubicacion de la herramienta en el almacen
	 */
	public HerramientasTaller(String codigo, String nombre, int unidades, String ubicacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidades = unidades;
		this.ubicacion = ubicacion;
	}
	/**
	 * Construcctor Vacio de la clase HerramientasTaller.
	 */
	public HerramientasTaller() {
		
	}
	/**
	 * Metodo para la obtencion del codigo de la herramienta.
	 * @return codigo (Codigo de Herramienta)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo para la modificacion del codigo de la herramienta.
	 * @param codigo (Codigo de Herramienta)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Metodo para la obtencion del nombre.
	 * @return nombre (Nombre de Herramienta)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param nombre (Nombre de Herramienta)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion de las unidades de herramientas.
	 * @return unidades (Unidades de Herramientas)
	 */
	public int getUnidades() {
		return unidades;
	}
	/**
	 * Metodo para la modificacion de las unidades de herramientas.
	 * @param unidades (Unidades de Herramientas)
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	/**
	 * Metodo para la obtencion de la ubicacion de la herramienta.
	 * @return ubicacion (Ubicacion de Herramientas)
	 */
	public String getUbicacion() {
		return ubicacion;
	}
	/**
	 * Metodo para la modificacion de la ubicacion de la herramienta.
	 * @param ubicacion (Ubicacion de Herramientas)
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	


}

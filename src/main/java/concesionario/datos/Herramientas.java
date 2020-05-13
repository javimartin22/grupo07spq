package concesionario.datos;


/**
 * Objeto Herramientas (Objeto para la representacion de las herramientas asignadas a cada proveedor)
 */
public class Herramientas {
	private String codigo;
	private String nombre;
	private int tiempo; 
	private String tipo;
	private String codProveedor;
	
	/**
	 * Constructor Vacio de la clase Herramientas.
	 */
	public Herramientas() {
	}
	
	/**
	 * Constructor de la clase Herramientas
	 * @param codigo Codigo de la herramienta
	 * @param nombre Nombre de la herramienta
	 * @param tiempo Tiempo de envio de la herramienta 
	 * @param tipo El tipo de herramienta
	 * @param codProveedor Codigo del proveedor asignada a la herramienta
	 */
	public Herramientas(String codigo, String nombre, int tiempo, String tipo, String codProveedor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.codProveedor = codProveedor;
	}
	/**
	 * Metodo para la obtencion del codigo.
	 * @return codigo (Codigo de Herramienta)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo para la modificacion del codigo.
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
	 * Metodo para la obtencion del tiempo de envio.
	 * @return tiempo (Tiempo de Herramienta)
	 */
	public int getTiempo() {
		return tiempo;
	}
	/**
	 * Metodo para la modificacion del tiempo de envio.
	 * @param tiempo (Tiempo de Herramienta)
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * Metodo para la obtencion del tipo de herramienta.
	 * @return tipo (Tipo de Herramienta)
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Metodo para la modificacion del tipo de herramienta.
	 * @param tipo (Tipo de Herramienta)
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Metodo para la obtencion del codigo del proveedor asignado en la herramienta.
	 * @return codProveedor (Codigo Proveedor para la Herramienta)
	 */
	public String getCodProveedor() {
		return codProveedor;
	}
	/**
	 * Metodo para la modificacion del codigo del proveedor asignado en la herramienta.
	 * @param codProveedor (Codigo Proveedor para la Herramienta)
	 */
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	
	
}

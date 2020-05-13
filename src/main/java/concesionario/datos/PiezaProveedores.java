package concesionario.datos;

/**
 * Objeto PiezaProveedores (Objeto para la representacion de las piezas asignadas a cada proveedor)
 */
public class PiezaProveedores {
	private String codigo;
	private String nombre;
	private int tiempo; 
	private String tipo;
	private String codProveedor;
	
	/**
	 * Constructor de la clase PiezaProveedores
	 * @param codigo Codigo de la pieza
	 * @param nombre Nombre de la pieza
	 * @param tiempo Tiempo de envio de la pieza 
	 * @param tipo El tipo de pieza
	 * @param codProveedor Codigo del proveedor asignada a la pieza
	 */
	public PiezaProveedores(String codigo, String nombre, int tiempo, String tipo, String codProveedor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.codProveedor = codProveedor;
	}
	/**
	 * Constructor Vacio de la clase PiezaProveedores.
	 */
	public PiezaProveedores() {
		
	}
	/**
	 * Metodo para la obtencion del codigo.
	 * @return codigo (Codigo de pieza)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo para la modificacion del codigo.
	 * @param codigo (Codigo de pieza)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Metodo para la obtencion del nombre.
	 * @return nombre (Nombre de pieza)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param nombre (Nombre de pieza)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del tiempo de envio.
	 * @return tiempo (Tiempo de pieza)
	 */
	public int getTiempo() {
		return tiempo;
	}
	/**
	 * Metodo para la modificacion del tiempo de envio.
	 * @param tiempo (Tiempo de pieza)
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * Metodo para la obtencion del tipo de pieza.
	 * @return tipo (Tipo de pieza)
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Metodo para la modificacion del tipo de pieza.
	 * @param tipo (Tipo de pieza)
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Metodo para la obtencion del codigo del proveedor asignado en la pieza.
	 * @return codProveedor (Codigo Proveedor para la pieza)
	 */
	public String getCodProveedor() {
		return codProveedor;
	}
	/**
	 * Metodo para la modificacion del codigo del proveedor asignado en la pieza.
	 * @param codProveedor (Codigo Proveedor para la pieza)
	 */
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	
}

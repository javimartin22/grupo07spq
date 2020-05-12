package concesionario.datos;

/**
 * Objeto ProveedorHerramientas (Objeto para la representacion de las proveedores de herramientas)
 */
public class ProveedorHerramientas {
	private String idProveedor;
	private String nombre;
	private String pais;
	private String tipoHerramientas;
	
	/**
	 * Construcctor Vacio de la clase ProveedorHerramientas.
	 */
	public ProveedorHerramientas() {
	}
	
	/**
	 * Constructor de la clase ProveedorHerramientas.
	 * @param idProveedor Codigo de identificacion del proveedor
	 * @param nombre Nombre del proveedor
	 * @param pais Pais del proveedor
	 * @param tipoHerramientas El tipo de herramienta del proveedor
	 */
	public ProveedorHerramientas(String idProveedor, String nombre, String pais, String tipoHerramientas) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.pais = pais;
		this.tipoHerramientas = tipoHerramientas;
	}
	
	/**
	 * Metodo para la obtencion del codigo de identificacion.
	 * @return idProveedor (ID del Proveedor)
	 */
	public String getIdProveedor() {
		return idProveedor;
	}
	/**
	 * Metodo para la modificacion del codigo de identificacion.
	 * @param idProveedor (ID del Proveedor)
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * Metodo para la obtencion del nombre.
	 * @return nombre (Nombre del Proveedor)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param nombre (Nombre del Proveedor)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del pais.
	 * @return pais (Pais del Proveedor)
	 */
	public String getPais() {
		return pais;
	}
	/**
	 * Metodo para la modificacion del pais.
	 * @param pais (Pais del Proveedor)
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * Metodo para la obtencion del tipo de herramienta.
	 * @return tipoHerramientas (Tipo de herramiemta del Proveedor)
	 */
	public String gettipoHerramientas() {
		return tipoHerramientas;
	}
	/**
	 * Metodo para la modificacion del tipo de herramienta.
	 * @param tipoHerramientas (Tipo de herramiemta del Proveedor)
	 */
	public void settipoHerramientas(String tipoHerramientas) {
		this.tipoHerramientas = tipoHerramientas;
	}
	
	
	
}

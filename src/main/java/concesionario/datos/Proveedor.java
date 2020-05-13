package concesionario.datos;


/**
 * Objeto Proveedor (Objeto para la representacion de las proveedores de piezas)
 */
public class Proveedor {
	private String idProveedor;
	private String nombre;
	private String pais;
	private String tipoPiezas;
	
	/**
	 * Constructor Vacio de la clase Proveedor.
	 */
	public Proveedor() {
	}
	
	/**
	 * Constructor de la clase Proveedor.
	 * @param idProveedor Codigo de identificacion del proveedor
	 * @param nombre Nombre del proveedor
	 * @param pais Pais del proveedor
	 * @param tipoPiezas El tipo de pieza del proveedor
	 */
	public Proveedor(String idProveedor, String nombre, String pais, String tipoPiezas) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.pais = pais;
		this.tipoPiezas = tipoPiezas;
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
	 * Metodo para la obtencion del tipo de piezas.
	 * @return tipoPiezas (Tipo de pieza del Proveedor)
	 */
	public String getTipoPiezas() {
		return tipoPiezas;
	}
	/**
	 * Metodo para la modificacion del tipo de piezas.
	 * @param tipoPiezas (Tipo de pieza del Proveedor)
	 */
	public void setTipoPiezas(String tipoPiezas) {
		this.tipoPiezas = tipoPiezas;
	}
	
	
	
}

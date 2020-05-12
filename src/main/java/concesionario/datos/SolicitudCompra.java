package concesionario.datos;

/**
 * Objeto SolicitudCompra (Objeto para la solicitud de compra de herramientas o piezas).
 */
public class SolicitudCompra {
	
	private String codigo;
	private String nombre;
	private String tipo;
	private int unidades;
	

	/**
	 * Construcctor Vacio de la clase SolicitudCompra.
	 */
	public SolicitudCompra() {
		
	}
	
	/**
	 * Constructor de la clase SolicitudCompra.
	 * @param codigo Codigo de la solicitud de compra
	 * @param nombre Nombre de el articulo solicitado
	 * @param tipo Tipo de solicitud (herramienta o pieza)
	 * @param unidades Unidades solicitadas
	 */
	public SolicitudCompra(String codigo, String nombre, String tipo, int unidades) {
	
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.unidades = unidades;
	}
	
	/**
	 * Metodo para la obtencion del codigo de la solicitud de compra.
	 * @return codigo (Codigo de Solicitud Compra)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * Metodo para la modificacion del codigo de la solicitud de compra.
	 * @param codigo (Codigo de Solicitud Compra)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Metodo para la obtencion del nombre.
	 * @return nombre (Nombre de Solicitud Compra)
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo para la modificacion del nombre.
	 * @param nombre (Nombre de Solicitud Compra)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del tipo de solicitud.
	 * @return tipo (Tipo de Solicitud Compra)
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Metodo para la modificacion del tipo de solicitud.
	 * @param tipo (Tipo de Solicitud Compra)
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Metodo para la obtencion de las unidades solicitadas.
	 * @return unidades (Unidades de Solicitud Compra)
	 */
	public int getUnidades() {
		return unidades;
	}
	/**
	 * Metodo para la modificacion de las unidades solicitadas.
	 * @param unidades (Unidades de Solicitud Compra)
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	

}

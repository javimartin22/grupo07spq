package concesionario.datos;

/**
 * Objeto Venta (Objeto para la gestion de las Ventas que realiza el Comercial).
 */
 public class Venta {
 	private String fecha;
 	private String modelo;
 	private String marca;
 	private String matricula;
 	private String nicknameComercial;
 	private String nombreComprador;

 	/**
 	 * Constructor vacio de la clase Venta.
 	 */
 	public Venta() {
 	}

 	/**
 	 * Constructor de la clase Venta.
 	 * @param fecha (Fecha de la Venta)
 	 * @param modelo (Modelo del vehiculo vendido)
 	 * @param marca (Marca del vehiculo vendido)
 	 * @param matricula (Matricula del vehiculo vendido)
 	 * @param nicknameComercial (Nickname del Comercial de la Venta)
 	 * @param nombreComprador (Nombre del Comprador)
 	 */
 	public Venta(String fecha, String modelo, String marca, String matricula, String nicknameComercial, String nombreComprador) {
 		super();
 		this.fecha = fecha;
 		this.modelo = modelo;
 		this.marca = marca;
 		this.matricula = matricula;
 		this.nicknameComercial = nicknameComercial;
 		this.setNombreComprador(nombreComprador);
 	}
 	
 	/**
 	 * Metodo para la obtencion de la Fecha de la Venta.
 	 * @return fecha (Fecha de la Venta)
 	 */
 	public String getFecha() {
 		return fecha;
 	}
 	/**
 	 * Metodo para la modificacion de la Fecha de la Venta.
 	 * @param fecha (Fecha de la Venta)
 	 */
 	public void setFecha(String fecha) {
 		this.fecha = fecha;
 	}
 	/**
 	 * Metodo para la obtencion del Modelo del Vehiculo vendido.
 	 * @return modelo (Modelo del Vehiculo vendido)
 	 */
 	public String getModelo() {
 		return modelo;
 	}
 	/**
 	 * Metodo para la modificacion del Modelo del Vehiculo vendido.
 	 * @param modelo (Modelo del Vehiculo vendido)
 	 */
 	public void setModelo(String modelo) {
 		this.modelo = modelo;
 	}
 	/**
 	 * Metodo para la obtencion de la Marca del Vehiculo vendido.
 	 * @return marca (Marca del Vehiculo vendido)
 	 */
 	public String getMarca() {
 		return marca;
 	}
 	/**
 	 * Metodo para la modificacion de la Marca del Vehiculo vendido.
 	 * @param marca (Marca del Vehiculo vendido)
 	 */
 	public void setMarca(String marca) {
 		this.marca = marca;
 	}
 	/**
 	 * Metodo para la obtencion de la Matricula del Vehiculo vendido.
 	 * @return matricula (Matricula del Vehiculo vendido)
 	 */
 	public String getMatricula() {
 		return matricula;
 	}
 	/**
 	 * Metodo para la modificacion de la Matricula del Vehiculo vendido.
 	 * @param matricula (Matricula del Vehiculo vendido)
 	 */
 	public void setMatricula(String matricula) {
 		this.matricula = matricula;
 	}
 	/**
 	 * Metodo para la obtencion del Nickname del Comercial de la Venta.
 	 * @return nicknameComercial (Nickname del Comercial de la Venta)
 	 */
 	public String getNicknameComercial() {
 		return nicknameComercial;
 	}
 	/**
 	 * Metodo para la modificacion del Nickname del Comercial de la Venta.
 	 * @param nicknameComercial (Nickname del Comercial de la Venta)
 	 */
 	public void setNicknameComercial(String nicknameComercial) {
 		this.nicknameComercial = nicknameComercial;
 	}
 	/**
 	 * Metodo para la obtencion del Nombre del Comprador.
 	 * @return nombreComercial (Nombre del Comprador)
 	 */ 	
 	public String getNombreComprador() {
 		return nombreComprador;
 	}
 	/**
 	 * Metodo para la modificacion del Nombre del Comprador.
 	 * @param nombreComprador (Nombre del Comprador)
 	 */ 
 	public void setNombreComprador(String nombreComprador) {
 		this.nombreComprador = nombreComprador;
 	}
 }

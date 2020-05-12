package concesionario.datos;

/**
 * Objeto DepartamentoCompras (Obejeto para la representacion de los trabajadores de tipo DepartamentoCompras).
 */
public class DepartamentoCompras {
	private String nickname;
	private String contrasenia;
	private String DNI;
	private String Nombre;
	private String Apellido;
	private String Sexo;
	private String Email;
	private String Ciudad;
	private int CodigoPostal;
	private String Direccion;
	private String NSS;
	private String NumeroCuenta; 
	private int Sueldo;
	private String NumeroTelefono;
	private int Pedidos;
	
	/**
	 * Constructor Vacio de la clase DepartamentoCompras
	 */
	public DepartamentoCompras() {
		
	}

	/**
	 * Constructor de la clase DepartamentoCompras.
	 * @param nickname (Nickname del trabajador del Departamento de Compras)
	 * @param contrasenia (Contrasenia del trabajador del Departamento de Compras)
	 * @param dNI (DNI del trabajador del Departamento de Compras)
	 * @param nombre (Nombre del trabajador del Departamento de Compras)
	 * @param apellido (Apellido del trabajador del Departamento de Compras)
	 * @param sexo (Sexo del trabajador del Departamento de Compras)
	 * @param email (Email del trabajador del Departamento de Compras)
	 * @param ciudad (Ciudad del trabajador del Departamento de Compras)
	 * @param codigoPostal (Codigo Postal del trabajador del Departamento de Compras)
	 * @param direccion (Direccion del trabajador del Departamento de Compras)
	 * @param nSS (Numero Seguridad Social del trabajador del Departamento de Compras)
	 * @param numeroCuenta (Numero de Cuenta del trabajador del Departamento de Compras)
	 * @param sueldo (Sueldo del trabajador del Departamento de Compras)
	 * @param numeroTelefono (Numero de Telefono del trabajador del Departamento de Compras)
	 * @param pedidos (Pedidos Realizados por el trabajador del Departamento de Compras)
	 */
	public DepartamentoCompras(String nickname, String contrasenia, String dNI, String nombre, String apellido,
			String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS,
			String numeroCuenta, int sueldo, String numeroTelefono, int pedidos) {
		this.nickname = nickname;
		this.contrasenia = contrasenia;
		DNI = dNI;
		Nombre = nombre;
		Apellido = apellido;
		Sexo = sexo;
		Email = email;
		Ciudad = ciudad;
		CodigoPostal = codigoPostal;
		Direccion = direccion;
		NSS = nSS;
		NumeroCuenta = numeroCuenta;
		Sueldo = sueldo;
		NumeroTelefono = numeroTelefono;
		Pedidos = pedidos;
	}



	/**
	 * Metodo para la obtencion del Nickname del trabajador del Departamento de Compras.
	 * @return nickname (Nickname del trabajador del Departamento de Compras)
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para la modificacion del Nickname del trabajador del Departamento de Compras.
	 * @param nickname (Nickname del trabajador del Departamento de Compras)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Metodo para la obtencion de la Contrasenia del trabajador del Departamento de Compras.
	 * @return contrasenia (Contrasenia del trabajador del Departamento de Compras)
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * Metodo para la modificacion de la Contrasenia del trabajador del Departamento de Compras.
	 * @param contrasenia (Contrasenia del trabajador del Departamento de Compras)
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**
	 * Metodo para la obtencion del DNI del trabajador del Departamento de Compras.
	 * @return DNI (DNI del trabajador del Departamento de Compras)
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Metodo para la modificacion del DNI del trabajador del Departamento de Compras.
	 * @param dNI (DNI del trabajador del Departamento de Compras)
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * Metodo para la obtencion del Nombre del trabajador del Departamento de Compras.
	 * @return Nombre (Nombre del trabajador del Departamento de Compras)
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Metodo para la modificacion del Nombre del trabajador del Departamento de Compras.
	 * @param nombre (Nombre del trabajador del Departamento de Compras)
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del Apellido del trabajador del Departamento de Compras.
	 * @return Apellido (Apellido del trabajador del Departamento de Compras)
	 */
	public String getApellido() {
		return Apellido;
	}
	/**
	 * Metodo para la modificacion del Apellido del trabajador del Departamento de Compras.
	 * @param apellido (Apellido del trabajador del Departamento de Compras)
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	/**
	 * Metodo para la obtencion del Sexo del trabajador del Departamento de Compras.
	 * @return Sexo (Sexo del trabajador del Departamento de Compras)
	 */
	public String getSexo() {
		return Sexo;
	}
	/**
	 * Metodo para la modificacion del Sexo del trabajador del Departamento de Compras.
	 * @param sexo (Sexo del trabajador del Departamento de Compras)
	 */
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	/**
	 * Metodo para la obtencion del Email del trabajador del Departamento de Compras.
	 * @return Email (Email del trabajador del Departamento de Compras)
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Metodo para la modificacion del Email del trabajador del Departamento de Compras.
	 * @param email (Email del trabajador del Departamento de Compras)
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * Metodo para la obtencion del Ciudad del trabajador del Departamento de Compras.
	 * @return Ciudad (Ciudad del trabajador del Departamento de Compras)
	 */
	public String getCiudad() {
		return Ciudad;
	}
	/**
	 * Metodo para la modificacion del Ciudad del trabajador del Departamento de Compras.
	 * @param ciudad (Ciudad del trabajador del Departamento de Compras)
	 */
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	/**
	 * Metodo para la obtencion del Codigo Postal del trabajador del Departamento de Compras.
	 * @return CodigoPostal (Codigo Postal del trabajador del Departamento de Compras)
	 */
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	/**
	 * Metodo para la modificacion del Codigo Postal del trabajador del Departamento de Compras.
	 * @param codigoPostal (Codigo Postal del trabajador del Departamento de Compras)
	 */
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	/**
	 * Metodo para la obtencion de la Direccion del trabajador del Departamento de Compras.
	 * @return Direccion (Direccion del trabajador del Departamento de Compras)
	 */
	public String getDireccion() {
		return Direccion;
	}
	/**
	 * Metodo para la modificacion de la Direccion del trabajador del Departamento de Compras.
	 * @param direccion (Direccion del trabajador del Departamento de Compras)
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	/**
	 * Metodo para la obtencion del Numero Seguridad Social del trabajador del Departamento de Compras.
	 * @return NSS (Numero Seguridad Social del trabajador del Departamento de Compras)
	 */
	public String getNSS() {
		return NSS;
	}
	/**
	 * Metodo para la modificacion del Numero Seguridad Social del trabajador del Departamento de Compras.
	 * @param nSS (Numero Seguridad Social del trabajador del Departamento de Compras)
	 */
	public void setNSS(String nSS) {
		NSS = nSS;
	}
	/**
	 * Metodo para la obtencion del Numero de Cuenta del trabajador del Departamento de Compras.
	 * @return NumeroCuenta (Numero de Cuenta del trabajador del Departamento de Compras)
	 */
	public String getNumeroCuenta() {
		return NumeroCuenta;
	}
	/**
	 * Metodo para la modificacion del Numero de Cuenta del trabajador del Departamento de Compras.
	 * @param numeroCuenta (Numero de Cuenta del trabajador del Departamento de Compras)
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}
	/**
	 * Metodo para la obtencion del Sueldo del trabajador del Departamento de Compras.
	 * @return Sueldo (Sueldo del trabajador del Departamento de Compras)
	 */
	public int getSueldo() {
		return Sueldo;
	}
	/**
	 * Metodo para la modificacion del Sueldo del trabajador del Departamento de Compras.
	 * @param sueldo (Sueldo del trabajador del Departamento de Compras)
	 */
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}
	/**
	 * Metodo para la obtencion del Numero de Telefono del trabajador del Departamento de Compras.
	 * @return NumeroTelefono (Numero de Telefono del trabajador del Departamento de Compras)
	 */
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	/**
	 * Metodo para la modificacion del Numero de Telefono del trabajador del Departamento de Compras.
	 * @param numeroTelefono (Numero de Telefono del trabajador del Departamento de Compras)
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
	/**
	 * Metodo para la obtencion de los Pedidos Realizados por el trabajador del Departamento de Compras.
	 * @return Pedidos (Pedidos Realizados por el trabajador del Departamento de Compras)
	 */
	public int getPedidos() {
		return Pedidos;
	}
	/**
	 * Metodo para la modificacion de los Pedidos Realizados por el trabajador del Departamento de Compras.
	 * @param pedidos (Pedidos Realizados por el trabajador del Departamento de Compras)
	 */
	public void setPedidos(int pedidos) {
		Pedidos = pedidos;
	}
}

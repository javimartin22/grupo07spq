package concesionario.datos;

/**
 *Objeto Empleado (Objeto para todos los usuarios que trabajen en el taller o concesionario)
 */
public class Empleado {

	private String nickname;
	private String contrasenia;
	private int tipo;
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
	private int TipoEmpleado;

	/**
	 * Constructor Vacio de la clase Empleado
	 */
	public Empleado() {
		
	}
	
	/**
	 * Constructor de la clase Empleado
	 * @param nickname (Nickname del empleado)
	 * @param contrasenia (Contrasenia del empleado)
	 * @param tipo (Tipo de empleado ya que disponemos de mecanicos, comerciales, administrador...)
	 * @param dNI (DNI del empleado)
	 * @param nombre (Nombre del empleado)
	 * @param apellido (Apellido del empleado)
	 * @param sexo (Sexo del empleado)
	 * @param email (Correo electronico del empleado)
	 * @param ciudad (Ciudad  donde vive el empleado)
	 * @param codigoPostal (Codigo postal de la ciudad de donde vive el empleado)
	 * @param direccion (Direccion del empleado)
	 * @param nSS (Numero de Seguridad Social del empleado)
	 * @param numeroCuenta (Numero de Cuenta del empleado)
	 * @param sueldo (Sueldo del empleado)
	 * @param numeroTelefono (Numero de Telefono del empleado)
	 * @param tipoEmpleado (Tipo de empleado ya que disponemos de diferentes tipos de empleados)
	 */
	public Empleado(String nickname, String contrasenia, int tipo, String dNI,
			String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal,
			String direccion, String nSS, String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado) {
		
		this.nickname = nickname;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
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
		TipoEmpleado = tipoEmpleado;
	}

	/**
	 * Metodo para la obtencion del NNickname del empleado.
	 * @return nickname (Nickname del empleado)
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Metodo para la modificacion del Nickname del empleado.
	 * @param nickname (Nickname del empleado)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Metodo para la obtencion de la Contrasenia del empleado.
	 * @return contrasenia (Contrasenia del empleado)
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	
	/**
	 * Metodo para la modificacion de la Contrasenia del empleado.
	 * @param contrasenia (Contrasenia del empleado)
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * Metodo para la obtencion el Tipo de empleado.
	 * @return tipo (Tipo de empleado)
	 */
	public int getTipo() {
		return tipo;
	}
	
	/**
	 * Metodo para la modificacion del Tipo de empleado.
	 * @param tipo (Tipo de empleado)
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo para la obtencion del DNI del empleado
	 * @return DNI (DNI del empleado)
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Metodo para la modificacion del DNI del empleado
	 * @param dNI (DNI del empleado)
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	/**
	 * Metopo para la obtencion del Sexo del empleado
	 * @return sexo (Sexo del empleado)
	 */
	public String getSexo() {
		return Sexo;
	}
	
	/**
	 * Metodo para la modificacion del Sexo del empleado
	 * @param sexo (Sexo del empleado)
	 */
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	
	/**
	 * Metodo para la obtencion del Tipo de empleado
	 * @return TipoEmpleado (Tipo del empleado)
	 */
	public int getTipoEmpleado() {
		return TipoEmpleado;
	}
	
	/**
	 * Metodo para la modificacion del TipoEmpleado 
	 * @param tipoEmpleado (Tipo del empleado)
	 */
	public void setTipoEmpleado(int tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}
	
	/**
	 * Metodo para la obtencion del Nombre del empleado
	 * @return Nombre (Nombre del empleado)
	 */
	public String getNombre() {
		return Nombre;
	}
	
	/**
	 * Metodo para la modificacion del Nombre del empleado
	 * @param nombre (Nombre del empleado)
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * Metodo para la obtencion del Apellido del empleado
	 * @return Aperrido (Apellido del empleado)
	 */
	public String getApellido() {
		return Apellido;
	}

	/**
	 * Metodo para la modificacion del Apellido del empleado
	 * @param apellido (Apellidod el empleado)
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	/**
	 * Metodo para la obtencion del Email del empleado
	 * @return Email (Email del empleado)
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Metodo para la modificacion del Email del empleado
	 * @param email (Email del empleado)
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * Metodo para la obtencion de la Ciudad del empleado
	 * @return Ciudad (Ciudad del empleado)
	 */
	public String getCiudad() {
		return Ciudad;
	}
 
	/**
	 * Metodo para la modificacion de la Ciudad del empleado
	 * @param ciudad (Ciudad del empleado)
	 */
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	/**
	 * Metodo para la obtencion del Codipo Postal del empleado
	 * @return CodigoPostal (Codigo Postal del empleado)
	 */
	public int getCodigoPostal() {
		return CodigoPostal;
	}

	/**
	 * Metodo para la modificacion del Codigo Postal del empleado
	 * @param codigoPostal (Codigo Postal del empleado)
	 */
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	/**
	 * Metodo para la obtencion de la Direccion del empleado
	 * @return Direccion (Direccion del empleado)
	 */
	public String getDireccion() {
		return Direccion;
	}

	/**
	 * Metodo para la modificacion de la Direccion del empleado
	 * @param direccion (Direccion del empleado)
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	/**
	 * Metodo para la obtencion del Numero de Seguridad Social del empleado
	 * @return NSS (Numero de Seguridad Social del empleado)
	 */
	public String getNSS() {
		return NSS;
	}

	/**
	 * Metodo para la modificacion del Numero de Seguridad Social del empleado
	 * @param nSS (Numero de Segurodad Social del empleado)
	 */
	public void setNSS(String nSS) {
		NSS = nSS;
	}

	/**
	 * Metodo para la obtencion del Numero de Cuenta del empleado
	 * @return NumeroCuenta (Numero de Cuenta del empleado)
	 */
	public String getNumeroCuenta() {
		return NumeroCuenta;
	}

	/**
	 * Metodo para la modificacion del Numero de Cuenta del empleado
	 * @param numeroCuenta (Numero de Cuenta del empleado)
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	/**
	 * Metodo para la obtencion del Sueldo del empleado
	 * @return Sueldo (Sueldo del empleado)
	 */
	public int getSueldo() {
		return Sueldo;
	}

	/**
	 * Metodo para la modificacion del Sueldo del empleado
	 * @param sueldo (Sueldo del empleado)
	 */
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}

	/**
	 * Metodo pra la obtencion del Numero de Telefono del empleado
	 * @return NumeroTelefono (Numero de Telefono del empleado)
	 */
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}

	/**
	 * Metodo para la modificacion del Numero de Telefono del empleado
	 * @param numeroTelefono (Numero de Telefono del empleado)
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
}

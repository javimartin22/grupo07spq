package concesionario.datos;

/**
 *Objeto Mecanico (Objeto para la representacion de los Trabajadores de tipo Mecanico).
 */
public class Mecanico{
	
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
	private int horas;
	
	/**
	 * Constructor vacio de la clase Mecanico.
	 */
	public Mecanico() {
		
	}

	/**
	 * Constructor de la clase Mecanico
	 * @param nickname (Nickname del Mecanico)
	 * @param contrasenia (Contrasenia del Mecanico)
	 * @param tipo (Tipo de Empleado de Mecanico)
	 * @param dNI (DNI del Mecanico)
	 * @param nombre (Nombre del Mecanico)
	 * @param apellido (Apellidos del Mecanico)
	 * @param sexo (Sexo del Mecanico)
	 * @param email (Email del Mecanico)
	 * @param ciudad (Ciudad del Mecanico)
	 * @param codigoPostal (Codigo Postal del Mecanico)
	 * @param direccion (Direccion del Mecanico)
	 * @param nSS (Numero Seguridad Social del Mecanico)
	 * @param numeroCuenta (Numero de Cuenta del Mecanico)
	 * @param sueldo (Sueldo del Mecanico)
	 * @param numeroTelefono (Numero de Telefono del Mecanico)
	 * @param horas (Horas Trabajadas del Mecanico)
	 */
	public Mecanico(String nickname, String contrasenia, int tipo, String dNI, String nombre, String apellido,
			String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS,
			String numeroCuenta, int sueldo, String numeroTelefono, int horas) {
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
		this.horas = horas;
	}



	/**
	 * Metodo para la obtencion del Nickname del Mecanico.
	 * @return nickname (Nickname del Mecanico)
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para la modificacion del Nickname del Mecanico.
	 * @param nickname (Nickname del Mecanico)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Metodo para la obtencion de la Contrasenia del Mecanico.
	 * @return contrasenia (Contrasenia del Mecanico)
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * Metodo para la modificacion de la Contrasenia del Mecanico.
	 * @param contrasenia (Contrasenia del Mecanico)
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**
	 * Metodo para la obtencion del DNI del Mecanico.
	 * @return DNI (DNI del Mecanico)
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Metodo para la modificacion del DNI del Mecanico.
	 * @param dNI (DNI del Mecanico)
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * Metodo para la obtencion del Nombre del Mecanico.
	 * @return Nombre (Nombre del Mecanico)
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Metodo para la modificacion del Nombre del Mecanico.
	 * @param nombre (Nombre del Mecanico)
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del Apellido del Mecanico.
	 * @return Apellido (Apellido del Mecanico)
	 */
	public String getApellido() {
		return Apellido;
	}
	/**
	 * Metodo para la modificacion del Apellido del Mecanico.
	 * @param apellido (Apellido del Mecanico)
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	/**
	 * Metodo para la obtencion del Sexo del Mecanico.
	 * @return Sexo (Sexo del Mecanico)
	 */
	public String getSexo() {
		return Sexo;
	}
	/**
	 * Metodo para la modificacion del Sexo del Mecanico.
	 * @param sexo (Sexo del Mecanico)
	 */
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	/**
	 * Metodo para la obtencion del Email del Mecanico.
	 * @return Email (Email del Mecanico)
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Metodo para la modificacion del Email del Mecanico.
	 * @param email (Email del Mecanico)
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * Metodo para la obtencion del Ciudad del Mecanico.
	 * @return Ciudad (Ciudad del Mecanico)
	 */
	public String getCiudad() {
		return Ciudad;
	}
	/**
	 * Metodo para la modificacion del Ciudad del Mecanico.
	 * @param ciudad (Ciudad del Mecanico)
	 */
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	/**
	 * Metodo para la obtencion del Codigo Postal del Mecanico.
	 * @return CodigoPostal (Codigo Postal del Mecanico)
	 */
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	/**
	 * Metodo para la modificacion del Codigo Postal del Mecanico.
	 * @param codigoPostal (Codigo Postal del Mecanico)
	 */
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	/**
	 * Metodo para la obtencion de la Direccion del Mecanico.
	 * @return Direccion (Direccion del Mecanico)
	 */
	public String getDireccion() {
		return Direccion;
	}
	/**
	 * Metodo para la modificacion de la Direccion del Mecanico.
	 * @param direccion (Direccion del Mecanico)
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	/**
	 * Metodo para la obtencion del Numero Seguridad Social del Mecanico.
	 * @return NSS (Numero Seguridad Social del Mecanico)
	 */
	public String getNSS() {
		return NSS;
	}
	/**
	 * Metodo para la modificacion del Numero Seguridad Social del Mecanico.
	 * @param nSS (Numero Seguridad Social del Mecanico)
	 */
	public void setNSS(String nSS) {
		NSS = nSS;
	}
	/**
	 * Metodo para la obtencion del Numero de Cuenta del Mecanico.
	 * @return NumeroCuenta (Numero de Cuenta del Mecanico)
	 */
	public String getNumeroCuenta() {
		return NumeroCuenta;
	}
	/**
	 * Metodo para la modificacion del Numero de Cuenta del Mecanico.
	 * @param numeroCuenta (Numero de Cuenta del Mecanico)
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}
	/**
	 * Metodo para la obtencion del Sueldo del Mecanico.
	 * @return Sueldo (Sueldo del Mecanico)
	 */
	public int getSueldo() {
		return Sueldo;
	}
	/**
	 * Metodo para la modificacion del Sueldo del Mecanico.
	 * @param sueldo (Sueldo del Mecanico)
	 */
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}
	/**
	 * Metodo para la obtencion del Numero de Telefono del Mecanico.
	 * @return NumeroTelefono (Numero de Telefono del Mecanico)
	 */
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	/**
	 * Metodo para la modificacion del Numero de Telefono del Mecanico.
	 * @param numeroTelefono (Numero de Telefono del Mecanico)
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
	/**
	 * Metodo para la obtencion del Horas del Mecanico.
	 * @return horas (Horas del Mecanico)
	 */
	public int getHoras() {
		return horas;
	}
	/**
	 * Metodo para la modificacion del Horas del Mecanico.
	 * @param horas (Horas del Mecanico)
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}
	/**
	 * Metodo para la obtencion del Tipo de Empleado del Mecanico.
	 * @return tipo (Tipo del Empleado del Mecanico)
	 */
	public int getTipo() {
		return tipo;
	}
	/**
	 * Metodo para la modificacion del Tipo de Empleado del Mecanico.
	 * @param tipo (Tipo de Empleado del Mecanico)
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}

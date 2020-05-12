package concesionario.datos;

/**
 * Objeto Comercial (Objeto para la representacion de los Trabajadores de tipo Comercial).
 */
public class Comercial{
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
	private int TipoEmpleado;
	private int CochesVendidos;
	private int ImporteObetenido;
	private int horas;
	
	/**
	 * Constructor vacio de la Clase Comercial.
	 */
	public Comercial() {
		
	}
	
	/**
	 * Constructor de la Clase Comercial
	 * @param nickname (Nickname del Comercial)
	 * @param contrasenia (Contrasenia del Comercial)
	 * @param dNI (DNI del Comercial)
	 * @param nombre (Nombre del Comercial)
	 * @param apellido (Apellidos del Comercial)
	 * @param sexo (Sexo del Comercial)
	 * @param email (Email del Comercial)
	 * @param ciudad (Ciudad del Comercial)
	 * @param codigoPostal (Codigo Postal del Comercial)
	 * @param direccion (Direccion del Comercial)
	 * @param nSS (Numero Seguridad Social del Comercial)
	 * @param numeroCuenta (Numero de Cuenta del Comercial)
	 * @param sueldo (Sueldo del Comercial)
	 * @param numeroTelefono (Numero de Telefono del Comercial)
	 * @param tipoEmpleado (Tipo Empleado del Comercial)
	 * @param cochesVendidos (Numero de Coches Vendidos por el Comercial)
	 * @param importeObetenido (Importe Obtenido por el Comercial)
	 * @param horas (Horas Trabajadas del Comercial)
	 */
	public Comercial(String nickname, String contrasenia, String dNI, String nombre, String apellido,
			String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS,
			String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado, int cochesVendidos,
			int importeObetenido, int horas) {
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
		TipoEmpleado = tipoEmpleado;
		CochesVendidos = cochesVendidos;
		ImporteObetenido = importeObetenido;
		this.horas = horas;
	}
	
	/**
	 * Metodo para la obtencion del Nickname del Comercial.
	 * @return nickname (Nickname del Comercial)
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para la modificacion del Nickname del Comercial.
	 * @param nickname (Nickname del Comercial)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Metodo para la obtencion de la Contrasenia del Comercial.
	 * @return contrasenia (Contrasenia del Comercial)
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * Metodo para la modificacion de la Contrasenia del Comercial.
	 * @param contrasenia (Contrasenia del Comercial)
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**
	 * Metodo para la obtencion del DNI del Comercial.
	 * @return DNI (DNI del Comercial)
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Metodo para la modificacion del DNI del Comercial.
	 * @param dNI (DNI del Comercial)
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * Metodo para la obtencion del Nombre del Comercial.
	 * @return Nombre (Nombre del Comercial)
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Metodo para la modificacion del Nombre del Comercial.
	 * @param nombre (Nombre del Comercial)
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * Metodo para la obtencion del Apellido del Comercial.
	 * @return Apellido (Apellido del Comercial)
	 */
	public String getApellido() {
		return Apellido;
	}
	/**
	 * Metodo para la modificacion del Apellido del Comercial.
	 * @param apellido (Apellido del Comercial)
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	/**
	 * Metodo para la obtencion del Sexo del Comercial.
	 * @return Sexo (Sexo del Comercial)
	 */
	public String getSexo() {
		return Sexo;
	}
	/**
	 * Metodo para la modificacion del Sexo del Comercial.
	 * @param sexo (Sexo del Comercial)
	 */
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	/**
	 * Metodo para la obtencion del Email del Comercial.
	 * @return Email (Email del Comercial)
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Metodo para la modificacion del Email del Comercial.
	 * @param email (Email del Comercial)
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * Metodo para la obtencion del Ciudad del Comercial.
	 * @return Ciudad (Ciudad del Comercial)
	 */
	public String getCiudad() {
		return Ciudad;
	}
	/**
	 * Metodo para la modificacion del Ciudad del Comercial.
	 * @param ciudad (Ciudad del Comercial)
	 */
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	/**
	 * Metodo para la obtencion del Codigo Postal del Comercial.
	 * @return CodigoPostal (Codigo Postal del Comercial)
	 */
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	/**
	 * Metodo para la modificacion del Codigo Postal del Comercial.
	 * @param codigoPostal (Codigo Postal del Comercial)
	 */
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	/**
	 * Metodo para la obtencion de la Direccion del Comercial.
	 * @return Direccion (Direccion del Comercial)
	 */
	public String getDireccion() {
		return Direccion;
	}
	/**
	 * Metodo para la modificacion de la Direccion del Comercial.
	 * @param direccion (Direccion del Comercial)
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	/**
	 * Metodo para la obtencion del Numero Seguridad Social del Comercial.
	 * @return NSS (Numero Seguridad Social del Comercial)
	 */
	public String getNSS() {
		return NSS;
	}
	/**
	 * Metodo para la modificacion del Numero Seguridad Social del Comercial.
	 * @param nSS (Numero Seguridad Social del Comercial)
	 */
	public void setNSS(String nSS) {
		NSS = nSS;
	}
	/**
	 * Metodo para la obtencion del Numero de Cuenta del Comercial.
	 * @return NumeroCuenta (Numero de Cuenta del Comercial)
	 */
	public String getNumeroCuenta() {
		return NumeroCuenta;
	}
	/**
	 * Metodo para la modificacion del Numero de Cuenta del Comercial.
	 * @param numeroCuenta (Numero de Cuenta del Comercial)
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}
	/**
	 * Metodo para la obtencion del Sueldo del Comercial.
	 * @return Sueldo (Sueldo del Comercial)
	 */
	public int getSueldo() {
		return Sueldo;
	}
	/**
	 * Metodo para la modificacion del Sueldo del Comercial.
	 * @param sueldo (Sueldo del Comercial)
	 */
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}
	/**
	 * Metodo para la obtencion del Numero de Telefono del Comercial.
	 * @return NumeroTelefono (Numero de Telefono del Comercial)
	 */
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	/**
	 * Metodo para la modificacion del Numero de Telefono del Comercial.
	 * @param numeroTelefono (Numero de Telefono del Comercial)
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
	/**
	 * Metodo para la obtencion del Tipo de Empleado del Comercial.
	 * @return TipoEmpleado (Tipo de Empleado del Comercial)
	 */
	public int getTipoEmpleado() {
		return TipoEmpleado;
	}/**
	 * Metodo para la modificacion del Tipo de Empleado del Comercial.
	 * @param tipoEmpleado (Tipo de Empleado del Comercial)
	 */
	public void setTipoEmpleado(int tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}
	/**
	 * Metodo para la obtencion del Coches Vendidos por el Comercial.
	 * @return CochesVendidos (Coches Vendidos por el Comercial)
	 */
	public int getCochesVendidos() {
		return CochesVendidos;
	}
	/**
	 * Metodo para la modificacion del Coches Vendidos por el Comercial.
	 * @param cochesVendidos (Coches Vendidos por el Comercial)
	 */
	public void setCochesVendidos(int cochesVendidos) {
		CochesVendidos = cochesVendidos;
	}
	/**
	 * Metodo para la obtencion del Importe Obtenido del Comercial.
	 * @return ImporteObtenido (Importe Obtenido del Comercial)
	 */
	public int getImporteObetenido() {
		return ImporteObetenido;
	}
	/**
	 * Metodo para la modificacion del Importe Obtenido del Comercial.
	 * @param importeObetenido (Importe Obtenido del Comercial)
	 */
	public void setImporteObetenido(int importeObetenido) {
		ImporteObetenido = importeObetenido;
	}
	/**
	 * Metodo para la obtencion del Horas del Comercial.
	 * @return horas (Horas del Comercial)
	 */
	public int getHoras() {
		return horas;
	}
	/**
	 * Metodo para la modificacion del Horas del Comercial.
	 * @param horas (Horas del Comercial)
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}
}

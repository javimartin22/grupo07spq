package concesionario.servidor.datos;

public class Empleado extends Usuario{

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
	
	

	public Empleado(String nickname, String contrasenya, int tipo, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS, String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado) {
		super(nickname, contrasenya, tipo);
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Sexo = sexo;
		this.Email = email;
		this.Ciudad = ciudad;
		this.CodigoPostal = codigoPostal;
		this.Direccion = direccion;
		this.NSS = nSS;
		this.NumeroCuenta = numeroCuenta;
		this.Sueldo = sueldo;
		this.NumeroTelefono = numeroTelefono;
		this.TipoEmpleado = tipoEmpleado;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public int getTipoEmpleado() {
		return TipoEmpleado;
	}

	public void setTipoEmpleado(int tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public int getCodigoPostal() {
		return CodigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getNSS() {
		return NSS;
	}

	public void setNSS(String nSS) {
		NSS = nSS;
	}

	public String getNumeroCuenta() {
		return NumeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	public int getSueldo() {
		return Sueldo;
	}

	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}

	public String getNumeroTelefono() {
		return NumeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
}

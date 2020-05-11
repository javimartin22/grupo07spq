package concesionario.datos;

/**
 *Objeto Cliente
 */
public class Cliente extends Usuario{
	
	private String DNI;
	private String Nombre;
	private String Apellido;
	private String Sexo;
	private String Email;
	private String Ciudad;
	private int CodigoPostal;
	private String Direccion;
	private String numeroTelefono;

	
	/**
	 * Constructor de la clase Cliente.
	 * @param dni
	 * @param nickname
	 * @param tipo
	 * @param contrasenya
	 * @param nombre
	 * @param apellido
	 * @param sexo
	 * @param email
	 * @param ciudad
	 * @param codigoPostal
	 * @param direccion
	 * @param numeroTelefono
	 */
	public Cliente(String dni, String nickname, int tipo, String contrasenya, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String direccion, String numeroTelefono) {
		super(nickname, contrasenya, tipo);
		this.DNI = dni;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Sexo = sexo;
		this.Email = email;
		this.Ciudad = ciudad;
		this.CodigoPostal = codigoPostal;
		this.Direccion = direccion;
		this.numeroTelefono = numeroTelefono;
	}
	
	/**
	 * Constructor vacio de la clase Cliente.
	 */
	public Cliente() {
		
	}



//Getters and Setters:
	
	/**
	 * Metodo para obtencion del nombre del Cliente.
	 * @return String (Nombre Cliente)
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Metodo para obtencion del DNI del Cliente.
	 * @return String (DNI Cliente)
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Metodo para la modificacion del DNI del Cliente.
	 * @param dNI
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	/**
	 * Metodo para la modificacion del Nombre del Cliente.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * Metodo para obtencion del Apellido del Cliente.
	 * @return String (Apellido Cliente)
	 */
	public String getApellido() {
		return Apellido;
	}
	/**
	 * Metodo para la modificacion del Apellido del Cliente.
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	/**
	 * Metodo para obtencion del Apellido del Cliente.
	 * @return String (Email Cliente)
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Metodo para la modificacion del Email del Cliente.
	 * @param email
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * Metodo para obtencion Sexo DNI del Cliente.
	 * @return String (Sexo Cliente)
	 */
	public String getSexo() {
		return Sexo;
	}
	/**
	 * Metodo para la modificacion del Sexo del Cliente.
	 * @param sexo
	 */
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	/**
	 * Metodo para obtencion de la Ciudad del Cliente.
	 * @return String (Ciudad Cliente)
	 */
	public String getCiudad() {
		return Ciudad;
	}
	/**
	 * Metodo para la modificacion de la Ciudad del Cliente.
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	/**
	 * Metodo para obtencion del CodigoPostal del Cliente.
	 * @return Int (CodigoPostal Cliente)
	 */
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	/**
	 * Metodo para la modificacion del CodigoPostal del Cliente.
	 * @param codigoPostal
	 */
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	/**
	 * Metodo para obtencion del Direccion del Cliente.
	 * @return String (Direccion Cliente)
	 */
	public String getDireccion() {
		return Direccion;
	}
	/**
	 * Metodo para la modificacion de la Direccion del Cliente.
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	/**
	 * Metodo para obtencion del Telefono del Cliente.
	 * @return String (Telefono Cliente)
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	/**
	 * Metodo para la modificacion del Telefono del Cliente.
	 * @param telefono
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
}

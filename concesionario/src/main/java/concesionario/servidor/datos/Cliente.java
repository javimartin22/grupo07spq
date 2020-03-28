package concesionario.servidor.datos;


public class Cliente extends Usuario{
	
	private String DNI;
	private String Nombre;
	private String Apellido;
	private String Sexo;
	private String Email;
	private String Ciudad;
	private String CodigoPostal;
	private String Direccion;
	private int numeroTelefono;
	private CocheMatriculado coche;

	
	
	public Cliente(String dni, String nickname, String contrasenya, int tipo, String nombre, String apellido, String sexo, String email, String ciudad, String codigoPostal, String direccion, int numeroTelefono, CocheMatriculado coche) {
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
		this.coche = coche;
	}



//Getters and Setters:
	public String getNombre() {
		return Nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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
	
	public String getSexo() {
		return Sexo;
	}
	
	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return CodigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	public int getNumeroTelefono() {
		return numeroTelefono;
	}


	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}


	public CocheMatriculado getCoche() {
		return coche;
	}


	public void setCoche(CocheMatriculado coche) {
		this.coche = coche;
	}
}

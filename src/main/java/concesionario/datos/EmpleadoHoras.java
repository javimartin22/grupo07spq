package concesionario.datos;

/**
 *Objeto EmpleadoHoras (Objeto para la representacion de la shoras que de cada empleado)
 */
public class EmpleadoHoras {
	private String nickname;
	private String nombre; 
	private String dni;
	private int horas;
	
	/**
	 * Constructot de la clase EmpleadoHoras
	 * @param nickname (Nickname del empleado)
	 * @param nombre (Nombre del empleado)
	 * @param dni (Dni del empleado)
	 * @param horas (Horas del empleado)
	 */
	public EmpleadoHoras(String nickname, String nombre, String dni, int horas) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.dni = dni;
		this.horas = horas;
	}

	/**
	 * Constructor Vacio de la clase EmpleadoHoras
	 */
	public EmpleadoHoras() {
	}

	/**
	 * Metodo para la obtencion del Nickname del empleado
	 * @return nickname (Nickname del empleado)
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Metodo para la modificacion del Nickname del empleado
	 * @param nickname (Nickname del empleado)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Metodo para la obtencion del Nombre del empleado
	 * @return nombre (Nombre del empleado)
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para la modificacion del Nombre del empleado
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para la obtencion del Dni del empleado
	 * @return dni (DNI del empleado)
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Metodo para la modificacion del DNI del empleado
	 * @param dni (DNI del empleado)
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Metodo para la obtencion de Horas del empleado
	 * @return horas (Horas del empleado)
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Metodo para la modificacion de Horas del empleado
	 * @param horas (Horas del empleado)
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	} 
}

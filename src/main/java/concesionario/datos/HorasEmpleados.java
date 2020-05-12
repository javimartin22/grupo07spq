package concesionario.datos;

/**
 * Objeto HorasEmpleado (Objeto para la realizacion de los calculos de las horas que invierte cada empleado en su puesto de trabajo).
 */
public class HorasEmpleados {
	private int horas;
	private int minutos;
	private String nickname;
	
	/**
	 * Constructor de la clase HorasEmpleados.
	 * @param horas (Horas que ha invertido el Empleado)
	 * @param minutos (Minutos que ha invertido el Empleado)
	 * @param nickname (Nickname del Empleado)
	 */
	public HorasEmpleados(int horas, int minutos, String nickname) {
		this.horas = horas;
		this.minutos = minutos;
		this.nickname = nickname;
	}
	/**
	 * Constructor Vacio de la clase HorasEmpleados.
	 */
	public HorasEmpleados() {
	}
	
	/**
	 * Metodo para la obtencion de las Horas que ha invertido el Empleado
	 * @return horas (Horas que ha invertido el Empleado)
	 */
	public int getHoras() {
		return horas;
	}
	/**
	 * Metodo para la Modificacion de las Horas Invertidas por el Empleado.
	 * @param horas (Horas invertidas por el Empleado)
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}
	/**
	 * Metodo para la obtencion de las Minutos que ha invertido el Empleado
	 * @return minutos (Minutos que ha invertido el Empleado)
	 */
	public int getMinutos() {
		return minutos;
	}
	/**
	 * Metodo para la Modificacion de las Minutos Invertidas por el Empleado.
	 * @param minutos (Minutos invertidas por el Empleado)
	 */
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	/**
	 * Metodo para la obtencion de las Nickname del Empleado
	 * @return nickname (Nickname del Empleado)
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para la Modificacion del Nickname Invertidas por el Empleado.
	 * @param nickname (Nickname invertidas por el Empleado)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

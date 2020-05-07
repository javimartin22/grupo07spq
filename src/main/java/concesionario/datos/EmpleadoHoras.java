package concesionario.datos;

public class EmpleadoHoras {
	private String nickname;
	private String nombre; 
	private String dni;
	private int horas;
	
	public EmpleadoHoras(String nickname, String nombre, String dni, int horas) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.dni = dni;
		this.horas = horas;
	}

	public EmpleadoHoras() {
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	} 
}

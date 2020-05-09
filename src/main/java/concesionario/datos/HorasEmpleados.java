package concesionario.datos;

public class HorasEmpleados {
	private int horas;
	private int minutos;
	private String nickname;
	
	public HorasEmpleados(int horas, int minutos, String nickname) {
		this.horas = horas;
		this.minutos = minutos;
		this.nickname = nickname;
	}

	public HorasEmpleados() {
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

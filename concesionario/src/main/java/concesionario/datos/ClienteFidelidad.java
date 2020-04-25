package concesionario.datos;

public class ClienteFidelidad  {

	private String dni;
	private int fidelidad;
	
	public ClienteFidelidad() {
	}

	public ClienteFidelidad(String dni, int fidelidad) {
		this.dni = dni;
		this.fidelidad = fidelidad;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getFidelidad() {
		return fidelidad;
	}
	public void setFidelidad(int fidelidad) {
		this.fidelidad = fidelidad;
	}	
	
}
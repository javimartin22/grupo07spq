package concesionario.datos;

public class ClienteFidelidad extends Cliente {

	private int fidelidad;

	public ClienteFidelidad(String dni, String nickname, int tipo, String contrasenya, String nombre, String apellido,
			String sexo, String email, String ciudad, int codigoPostal, String direccion, String numeroTelefono,
			int fidelidad) {
		super(dni, nickname, tipo, contrasenya, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion,
				numeroTelefono);
		this.fidelidad = fidelidad;
	}
	
	public ClienteFidelidad() {
		
	}

	public int getFidelidad() {
		return fidelidad;
	}

	public void setFidelidad(int fidelidad) {
		this.fidelidad = fidelidad;
	}
	
	
	
}

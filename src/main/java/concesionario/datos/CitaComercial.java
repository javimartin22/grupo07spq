package concesionario.datos;

public class CitaComercial {
	
	private String nombre;
	private String dniCliente;
	private String fecha;
	private String hora;
	
	public CitaComercial() {
		super();
	}

	public CitaComercial(String nombre, String dniCliente, String fecha, String hora) {
		super();
		this.nombre = nombre;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}

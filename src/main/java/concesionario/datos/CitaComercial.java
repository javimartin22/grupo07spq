package concesionario.datos;

public class CitaComercial {
	
	private String nombre;
	private String dniCliente;
	private String fecha;
	private String hora;
	private String comercial;
	
	public CitaComercial() {
	}

	public CitaComercial(String nombre, String dniCliente, String fecha, String hora, String comercial) {
		super();
		this.nombre = nombre;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.hora = hora;
		this.comercial = comercial;
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
	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
}

package concesionario.datos;

public class CitaTaller {
	
	private String nombre;
	private String dniCliente;
	private String fecha;
	private String hora;
	private String comercial;
	private String problema;
	
	public CitaTaller() {
	}

	public CitaTaller(String nombre, String dniCliente, String fecha, String hora, String comercial, String problema) {
		super();
		this.nombre = nombre;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.hora = hora;
		this.comercial = comercial;
		this.problema = problema;
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
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
}

package concesionario.datos;

public class Presupuesto {
	private String dniCliente;
	private String mecanico; 
	private String marca;
	private String modelo;
	private String problema;
	private int numPiezas;
	private String listaPiezas;
	private String observaciones;
	private double precio; 
	private String fecha;
	
	public Presupuesto() {
		
	}

	public Presupuesto(String dniCliente, String mecanico, String marca, String modelo, String problema, int numPiezas,
			String listaPiezas, String observaciones, double precio, String fecha) {
		this.dniCliente = dniCliente;
		this.mecanico = mecanico;
		this.marca = marca;
		this.modelo = modelo;
		this.problema = problema;
		this.numPiezas = numPiezas;
		this.listaPiezas = listaPiezas;
		this.observaciones = observaciones;
		this.precio = precio;
		this.fecha = fecha;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getMecanico() {
		return mecanico;
	}

	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public int getNumPiezas() {
		return numPiezas;
	}

	public void setNumPiezas(int numPiezas) {
		this.numPiezas = numPiezas;
	}

	public String getListaPiezas() {
		return listaPiezas;
	}

	public void setListaPiezas(String listaPiezas) {
		this.listaPiezas = listaPiezas;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
}

package concesionario.datos;

/**
 * Objeto Presupuesto (Objeto para la gestion de los Presupuestos que realiza el Mecanico).
 */
public class Presupuesto {
	private String codigo;
	private String dniCliente;
	private String mecanico; 
	private String marca;
	private String modelo;
	private String problema;
	private int numPiezas;
	private String listaPiezas;
	private String observaciones;
	private int precio; 
	private String fecha;
	
	/**
	 * Constructor vacio de la calse Presupuesto.
	 */
	public Presupuesto() {
		
	}

	/**
	 * Constructor de la Clase Presupuesto.
	 * @param codigo (Codigo del Presupuesto)
	 * @param dniCliente (DNI del Cliente)
	 * @param mecanico (Nombre del Mecanico)
	 * @param marca (Marca del Vehiculo)
	 * @param modelo (Modelo del Vehiculo)
	 * @param problema (Problema del Vehiculo)
	 * @param numPiezas (Numero de Piezas para la Reparacion)
	 * @param listaPiezas (Lista de las Piezas para la Reparacion)
	 * @param observaciones (Comentarios del Problema)
	 * @param precio (Precio de las Reparaciones)
	 * @param fecha (Fecha del Presupuesto)
	 */
	public Presupuesto(String codigo, String dniCliente, String mecanico, String marca, String modelo, String problema, int numPiezas,
			String listaPiezas, String observaciones, int precio, String fecha) {
		this.codigo = codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
}

package concesionario.servidor.datos;

public class CocheTaller {
	private String matricula;
	private String marca;
	private String modelo;
	private String mecanico;
	private String dniCliente;
	private double coste; 
	private int estado;
	
	
	
	public CocheTaller() {
	}
	
	public CocheTaller(String matricula, String marca, String modelo, String mecanico, String dniCliente, double coste,
			int estado) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.mecanico = mecanico;
		this.dniCliente = dniCliente;
		this.coste = coste;
		this.estado = estado;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	public String getMecanico() {
		return mecanico;
	}
	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public double getCoste() {
		return coste;
	}
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

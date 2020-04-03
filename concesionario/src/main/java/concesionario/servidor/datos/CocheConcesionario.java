package concesionario.servidor.datos;

public class CocheConcesionario {

	private String marca;
	private String modelo;
	private int precio;
	private int unidades;
	private int cv;
	private int numPuertas;
	private String color;

	public CocheConcesionario(String marca, String modelo, int precio, int cv, int numPuertas, String color, int unidades) {
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.color = color;
		this.cv = cv;
		this.numPuertas = numPuertas;
		this.unidades = unidades;
	}
	
	public CocheConcesionario() {
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}

	public int getNumPuertas() {
		return numPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

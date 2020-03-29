package concesionario.servidor.datos;

public class Pieza {

	private String Codigo;
	private String Nombre; 
	private int Stock;

	private  Pieza (String codigo, String nombre, int stock) {
		// TODO Auto-generated method stub
		this.Codigo = codigo;
		this.Nombre = nombre;
		this.Stock = stock;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}
}

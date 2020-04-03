package concesionario.servidor.datos;

public class CocheConcesionario extends Coche {

	private int precio;

	public CocheConcesionario(String marca, String modelo, int precio) {
		super(marca, modelo);
		this.precio = precio;
	}
	public CocheConcesionario() {
		super();
	}
	

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	

}

package concesionario.servidor.datos;

public class Pieza {
	private String codigo;
	private String nombre;
	private int unidades; 
	private String ubicacion;
	
	public Pieza(String codigo, String nombre, int unidades, String ubicacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidades = unidades;
		this.ubicacion = ubicacion;
	}
	
	public Pieza() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}

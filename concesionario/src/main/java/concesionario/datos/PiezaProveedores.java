package concesionario.datos;

public class PiezaProveedores {
	private String codigo;
	private String nombre;
	private int tiempo; 
	private String estado;
	
	public PiezaProveedores(String codigo, String nombre, int tiempo, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.estado = estado;
	}
	
	public PiezaProveedores() {
		
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

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}

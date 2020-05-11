package concesionario.datos;

public class SolicitudCompra {
	
	private String codigo;
	private String nombre;
	private String tipo;
	private int unidades;
	
	
	public SolicitudCompra() {
		
	}
	public SolicitudCompra(String codigo, String nombre, String tipo, int unidades) {
	
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.unidades = unidades;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	

}

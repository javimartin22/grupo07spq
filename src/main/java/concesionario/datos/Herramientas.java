package concesionario.datos;



public class Herramientas {
	private String codigo;
	private String nombre;
	private int tiempo; 
	private String tipo;
	private String codProveedor;
	

	public Herramientas() {
	}
	
	
	public Herramientas(String codigo, String nombre, int tiempo, String tipo, String codProveedor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.codProveedor = codProveedor;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	
	
}

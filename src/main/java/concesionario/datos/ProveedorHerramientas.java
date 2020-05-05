package concesionario.datos;

public class ProveedorHerramientas {
	private String idProveedor;
	private String nombre;
	private String pais;
	private String tipoHerramientas;
	
	
	public ProveedorHerramientas() {
	}
	
	public ProveedorHerramientas(String idProveedor, String nombre, String pais, String tipoHerramientas) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.pais = pais;
		this.tipoHerramientas = tipoHerramientas;
	}
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String gettipoHerramientas() {
		return tipoHerramientas;
	}
	public void settipoHerramientas(String tipoHerramientas) {
		this.tipoHerramientas = tipoHerramientas;
	}
	
	
	
}

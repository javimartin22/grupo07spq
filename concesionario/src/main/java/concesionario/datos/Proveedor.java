package concesionario.datos;

public class Proveedor {
	private String idProveedor;
	private String nombre;
	private String pais;
	private String tipoPiezas;
	
	
	public Proveedor() {
	}
	
	public Proveedor(String idProveedor, String nombre, String pais, String tipoPiezas) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.pais = pais;
		this.tipoPiezas = tipoPiezas;
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
	public String getTipoPiezas() {
		return tipoPiezas;
	}
	public void setTipoPiezas(String tipoPiezas) {
		this.tipoPiezas = tipoPiezas;
	}
	
	
	
}

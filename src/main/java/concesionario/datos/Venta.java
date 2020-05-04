package concesionario.datos;

 public class Venta {
 	private String fecha;
 	private String modelo;
 	private String marca;
 	private String matricula;
 	private String nicknameComercial;
 	private String nombreComprador;


 	public Venta() {
 	}

 	public Venta(String fecha, String modelo, String marca, String matricula, String nicknameComercial, String nombreComprador) {
 		super();
 		this.fecha = fecha;
 		this.modelo = modelo;
 		this.marca = marca;
 		this.matricula = matricula;
 		this.nicknameComercial = nicknameComercial;
 		this.setNombreComprador(nombreComprador);
 	}

 	public String getFecha() {
 		return fecha;
 	}

 	public void setFecha(String fecha) {
 		this.fecha = fecha;
 	}

 	public String getModelo() {
 		return modelo;
 	}

 	public void setModelo(String modelo) {
 		this.modelo = modelo;
 	}

 	public String getMarca() {
 		return marca;
 	}

 	public void setMarca(String marca) {
 		this.marca = marca;
 	}

 	public String getMatricula() {
 		return matricula;
 	}

 	public void setMatricula(String matricula) {
 		this.matricula = matricula;
 	}

 	public String getNicknameComercial() {
 		return nicknameComercial;
 	}

 	public void setNicknameComercial(String nicknameComercial) {
 		this.nicknameComercial = nicknameComercial;
 	}

 	public String getNombreComprador() {
 		return nombreComprador;
 	}

 	public void setNombreComprador(String nombreComprador) {
 		this.nombreComprador = nombreComprador;
 	}
 }

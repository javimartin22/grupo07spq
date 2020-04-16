package concesionario.datos;

public class CocheMatriculado{
	
	private String marca;
	private String modelo;
	private String matricula;
	private String nombrePropietario;
	private String color;
	private int cv;
	private int numPuertas;
	private int anyoMatriculacion;
	private int revisiones;
	
	
	public CocheMatriculado(String marca, String modelo, String matricula, String nombrePropietario, String color,
			int numPuertas, int anyo_matriculacion, int cv, int revisiones) {
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.nombrePropietario = nombrePropietario;
		this.color = color;
		this.numPuertas = numPuertas;
		this.anyoMatriculacion = anyo_matriculacion;
		this.revisiones = revisiones;
	}

	public CocheMatriculado() {
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getAnyoMatriculacion() {
		return anyoMatriculacion;
	}

	public void setAnyoMatriculacion(int anyo_matriculacion) {
		this.anyoMatriculacion = anyo_matriculacion;
	}

	public int getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(int revisiones) {
		this.revisiones = revisiones;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNumPuertas() {
		return numPuertas;
	}
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}
	
	

	
}

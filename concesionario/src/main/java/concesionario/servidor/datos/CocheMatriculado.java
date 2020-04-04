package concesionario.servidor.datos;

public class CocheMatriculado{
	
	String marca;
	String modelo;
	String matricula;
	int anyo_matriculacion;
	int revisiones;
	
	public CocheMatriculado(String marca, String modelo, String matricula, int anyo_matriculacion, int revisiones ) {
		this.marca = marca;
		this.modelo= modelo;
		this.matricula = matricula;
		this.anyo_matriculacion = anyo_matriculacion;
		this.revisiones = revisiones;
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

	public int getAnyo_matriculacion() {
		return anyo_matriculacion;
	}

	public void setAnyo_matriculacion(int anyo_matriculacion) {
		this.anyo_matriculacion = anyo_matriculacion;
	}

	public int getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(int revisiones) {
		this.revisiones = revisiones;
	}
	
	

	
}

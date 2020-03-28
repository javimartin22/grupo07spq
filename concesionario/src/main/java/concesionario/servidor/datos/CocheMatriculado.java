package concesionario.servidor.datos;

public class CocheMatriculado {

	private String Marca;
	private String Modelo; 
	private String AnyoMatriculacion; 
	private String Matricula;
	
	public CocheMatriculado() {
	}
	
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	
	public String getAnyoMatriculacion() {
		return AnyoMatriculacion;
	}
	public void setAnyoMatriculacion(String anyoMatriculacion) {
		AnyoMatriculacion = anyoMatriculacion;
	}
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
}

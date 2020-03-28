package concesionario.servidor.datos;
import java.util.ArrayList;

import concesionario.servidor.datos.CocheMatriculado;

public class Mecanico extends Empleado{
	
	private ArrayList<CocheMatriculado> coches;
	private int horas;
	
	
	
	public Mecanico(String nickname, String contrasenya, int tipo, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS, String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado, ArrayList<CocheMatriculado> coches, int horas) {
		super(nickname, contrasenya, tipo, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, nSS, numeroCuenta, sueldo, numeroTelefono, tipoEmpleado);
		this.coches = coches;
		this.horas = horas;
	}
	
	public ArrayList<CocheMatriculado> getCoches() {
		return coches;
	}
	public void setCoches(ArrayList<CocheMatriculado> coches) {
		this.coches = coches;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	
}

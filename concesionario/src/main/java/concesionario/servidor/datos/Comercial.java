package concesionario.servidor.datos;

import java.util.ArrayList;

public class Comercial extends Empleado{
	private int CochesVendidos;
	private int ImporteObetenido;
	private int horas;
	private ArrayList<CocheMatriculado> CochesVendidosLista;
	
	public Comercial(String dni, String nickname, String contrasenya, int tipo, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS, String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado, int cochesVendidos, int importeObetenido, int horas, ArrayList<CocheMatriculado> cochesVendidosLista) {
		super(dni, nickname, contrasenya, tipo, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, nSS, numeroCuenta, sueldo, numeroTelefono, tipoEmpleado);
		CochesVendidos = cochesVendidos;
		ImporteObetenido = importeObetenido;
		this.horas = horas;
		CochesVendidosLista = cochesVendidosLista;
	}
	
	public int getCochesVendidos() {
		return CochesVendidos;
	}
	public void setCochesVendidos(int cochesVendidos) {
		CochesVendidos = cochesVendidos;
	}
	public int getImporteObetenido() {
		return ImporteObetenido;
	}
	public void setImporteObetenido(int importeObetenido) {
		ImporteObetenido = importeObetenido;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public ArrayList<CocheMatriculado> getCochesVendidosLista() {
		return CochesVendidosLista;
	}
	public void setCochesVendidosLista(ArrayList<CocheMatriculado> cochesVendidosLista) {
		CochesVendidosLista = cochesVendidosLista;
	}
	
	
}

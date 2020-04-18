package concesionario.datos;

public class Tarifa {
	private String idTarifa;
	private String nomTarifa;
	private int precioAprox; 
	private int horas_manodeobra;

	public Tarifa() {
		
	}

	public Tarifa(String idTarifa, String nomTarifa, int precioAprox, int horas_manodeobra ) {
		this.idTarifa = idTarifa;
		this.nomTarifa = nomTarifa;
		this.precioAprox = precioAprox;
		this.horas_manodeobra = horas_manodeobra;
	}

	public String getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(String idTarifa) {
		this.idTarifa = idTarifa;
	}

	public String getNomTarifa() {
		return nomTarifa;
	}

	public void setNomTarifa(String nomTarifa) {
		this.nomTarifa = nomTarifa;
	}

	public int getPrecioAprox() {
		return precioAprox;
	}

	public void setPrecioAprox(int precioAprox) {
		this.precioAprox = precioAprox;
	}

	public int getHoras_manodeobra() {
		return horas_manodeobra;
	}

	public void setHoras_manodeobra(int horas_manodeobra) {
		this.horas_manodeobra = horas_manodeobra;
	}



	
}

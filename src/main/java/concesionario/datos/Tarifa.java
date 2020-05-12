package concesionario.datos;

/**
 * Objeto Tarifa (Objeto para la representacion de las tarifas de los servicios del taller establecidos por el gerente).
 * @author javier
 *
 */
public class Tarifa {
	private String idTarifa;
	private String nomTarifa;
	private int precioAprox; 
	private int horas_manodeobra;

	/**
	 * Constructor Vacio de la clase Tarifa.
	 */
	public Tarifa() {
		
	}
	
	/**
	 * Constructor de la clase Tarifa
	 * @param idTarifa (Identificador de la tarifa)
	 * @param nomTarifa (Nombre de la tarifa)
	 * @param precioAprox (Precio aproximado del servicio que indica la tarifa)
	 * @param horas_manodeobra (Estimacion del esfuerzo en horas de mano de obra del servicio de la tarifa)
	 */
	public Tarifa(String idTarifa, String nomTarifa, int precioAprox, int horas_manodeobra ) {
		this.idTarifa = idTarifa;
		this.nomTarifa = nomTarifa;
		this.precioAprox = precioAprox;
		this.horas_manodeobra = horas_manodeobra;
	}

	/**
	 * Metodo para obtencion del identificador de la tarifa
	 * @return idTarifa (Identificador de la tarifa)
	 */
	public String getIdTarifa() {
		return idTarifa;
	}
	/**
	 * Metodo para modificacion del identificador de la tarifa
	 * @param idTarifa (Identificador de la tarifa)
	 */
	public void setIdTarifa(String idTarifa) {
		this.idTarifa = idTarifa;
	}
	/**
	 * Metodo para obtencion del nombre de la tarifa
	 * @return nomTarifa (Nombre de la tarifa)
	 */
	public String getNomTarifa() {
		return nomTarifa;
	}
	/**
	 * Metodo para modificacion del nombre de la tarifa
	 * @param nomTarifa (Nombre de la tarifa)
	 */
	public void setNomTarifa(String nomTarifa) {
		this.nomTarifa = nomTarifa;
	}
	/**
	 * Metodo para obtencion de la estimacion del precio
	 * @return precioAprox (Estimacion del precio de la tarifa)
	 */
	public int getPrecioAprox() {
		return precioAprox;
	}
	/**
	 * Metodo para modificacion de la estimación del precio
	 * @param precioAprox (Estimacion del precio de la tarifa)
	 */
	public void setPrecioAprox(int precioAprox) {
		this.precioAprox = precioAprox;
	}
	/**
	 * Metodo para obtencion de la estimacion del esfuerzo en horas de mano de obra
	 * @return horas_manodeobra (Estimacion del esfuerzo)
	 */
	public int getHoras_manodeobra() {
		return horas_manodeobra;
	}
	/**
	 * Metodo para modificacion de la estimación del esfuerzo en horas de mano de obra
	 * @param horas_manodeobra (Estimacion del esfuerzo)
	 */
	public void setHoras_manodeobra(int horas_manodeobra) {
		this.horas_manodeobra = horas_manodeobra;
	}



	
}

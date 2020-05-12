package concesionario.datos;

/**
 * Objeto Usuario (Objeto utilizado para el inicio de sesion en la aplicacion)
 */
public class Usuario {

	private String nickname;
	private String contrasenya;
	private int tipo;
	
	/**
	 * Constructor de la clase Usuario.
	 * @param nickname (Nickname del Usuario)
	 * @param contrasenya (Contrasenia del Usuario)
	 * @param tipo (Tipo de Usuario)
	 */
	public Usuario(String nickname, String contrasenya, int tipo) {
		this.nickname = nickname;
		this.contrasenya = contrasenya;
		this.tipo = tipo;
	}
	
	/**
	 * Constructor vacio de la clase Usuario.
	 */
	public Usuario() {
		
	}
	 /**
	  * Metodo para la obtencion del Nickname del Usuario.
	  * @return nickname (Nickname del Usuario)
	  */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para la modificacion del Nickname del Usuario.
	 * @param nickname (Nickname del Usuario)
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	  * Metodo para la obtencion de la Contrasenya del Usuario.
	  * @return contrasenya (Contrasenya del Usuario)
	  */
	public String getContrasenya() {
		return contrasenya;
	}
	/**
	 * Metodo para la modificacion de la Contrasenya del Usuario.
	 * @param contrasenya (Contrasenya del Usuario)
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	/**
	  * Metodo para la obtencion del Tipo de Usuario.
	  * @return tipo (Tipo de Usuario)
	  */
	public int getTipo() {
		return tipo;
	}
	/**
	 * Metodo para la modificacion del Tipo del Usuario.
	 * @param tipo (Tipo del Usuario)
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}

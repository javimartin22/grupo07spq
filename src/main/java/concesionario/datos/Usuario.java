package concesionario.datos;

public class Usuario {

	private String nickname;
	private String contrasenya;
	private int tipo;
	
	public Usuario(String nickname, String contrasenya, int tipo) {
		this.nickname = nickname;
		this.contrasenya = contrasenya;
		this.tipo = tipo;
	}
	
	public Usuario() {
		
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}

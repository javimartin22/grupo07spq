package concesionario.cliente.ventana;
import javax.swing.JFrame;

public class VentanaRegistrarCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaRegistrarCliente() {
		this.setTitle("Reg�strate");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480,282);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
	}
	
	
	public static void abrirVentanaRegistrarCliente(String nombre, String contrasenia) {
		VentanaRegistrarCliente ventanaRegistarCliente = new VentanaRegistrarCliente();
		ventanaRegistarCliente.setVisible(true);
		ventanaRegistarCliente.setSize(480,360);
		ventanaRegistarCliente.setLocationRelativeTo(null);
		ventanaRegistarCliente.setVisible(true);
		
	}
	
	
}

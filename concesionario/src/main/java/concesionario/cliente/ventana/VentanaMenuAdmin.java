package concesionario.cliente.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VentanaMenuAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String nickname) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuAdmin ventanaMenuAdmin = new VentanaMenuAdmin(nickname);
					ventanaMenuAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaMenuAdmin(String nickname) {
		this.setTitle("Menu del administrador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,282);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
			JLabel nombreAdmin = new JLabel("Bienvenid@ " + nickname.toUpperCase());
			nombreAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
			nombreAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			nombreAdmin.setBounds(178, 11, 250, 33);
			panel.add(nombreAdmin);
			
			
			JButton buttonAnadirUsuarios = new JButton("Anyadir Empleados");
			buttonAnadirUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String respuesta = JOptionPane.showInputDialog("¿Que tipo de Empleado desea registrar? (M/C/DC)");
					if (respuesta.equals("C") || respuesta.equals("c")) {
						//Llevaria a ventana registrar Comercial.
					} else if (respuesta.equals("M") || respuesta.equals("m")){
						//Llevaria a ventana registrar Mecanico.
					} else if (respuesta.equals("DC") || respuesta.equals("dc") || respuesta.equals("Dc") || respuesta.equals("dC")){
						//Levaria a la ventana registrar DC.
					} else {
						JOptionPane.showMessageDialog(panel, "Caracter no valido");
					}
				} 
			});
			buttonAnadirUsuarios.setBounds(145, 65, 137, 23);
			panel.add(buttonAnadirUsuarios);
			
			JButton buttonVerUsuarios = new JButton("Ver usuarios");
			buttonVerUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			buttonVerUsuarios.setBounds(145, 117, 137, 23);
			panel.add(buttonVerUsuarios);
			
			
			//boton de salir que te lleva a la ventana de VentanaLogin
			JButton buttonSalir = new JButton("Salir");
			buttonSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaLogin ventana = new VentanaLogin();
					ventana.setVisible(true);
					dispose();
				}
			});
			buttonSalir.setBounds(171, 165, 89, 23);
			panel.add(buttonSalir);
			
	}
	
	
}

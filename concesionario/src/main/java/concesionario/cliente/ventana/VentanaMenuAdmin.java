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

import concesionario.cliente.controller.LoginController;

public class VentanaMenuAdmin extends JFrame {

	private LoginController loginController;
	private static final long serialVersionUID = 1L;
	
	public VentanaMenuAdmin(LoginController loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;   //errores
		inicioVentanaMenuAdmin(nickname);
	}
	
	
	public void inicioVentanaMenuAdmin(String nickname) {
		this.setTitle("Menu del administrador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(457,282);
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
						VentanaRegistroComercial vrc = new VentanaRegistroComercial(loginController, nickname);
						vrc.setVisible(true);
						dispose();
					} else if (respuesta.equals("M") || respuesta.equals("m")){
						VentanaRegistroMecanico vrm = new VentanaRegistroMecanico(loginController, nickname);
						vrm.setVisible(true);
						dispose();
					} else if (respuesta.equals("DC") || respuesta.equals("dc") || respuesta.equals("Dc") || respuesta.equals("dC")){
						JOptionPane.showMessageDialog(panel, "Ventana Departamento Compras");
					} else {
						JOptionPane.showMessageDialog(panel, "Caracter no valido");
					}
				} 
			});
			buttonAnadirUsuarios.setBounds(162, 102, 164, 23);
			panel.add(buttonAnadirUsuarios);
			
			JButton buttonVerUsuarios = new JButton("Ver usuarios");
			buttonVerUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verUsuarios(nickname);
				}
			});
			buttonVerUsuarios.setBounds(178, 149, 137, 23);
			panel.add(buttonVerUsuarios);
			
			
			//boton de salir que te lleva a la ventana de VentanaLogin
			JButton buttonSalir = new JButton("Salir");
			buttonSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			buttonSalir.setBounds(204, 196, 89, 23);
			panel.add(buttonSalir);
	}
	
	public void verUsuarios(String nickname) {
		VentanaEmpleados ve = new VentanaEmpleados(loginController, nickname);
    	ve.setVisible(true);
    	dispose();
	}
	
	public void salir() {
		VentanaLogin vlogin = new VentanaLogin(loginController);
		vlogin.setVisible(true);
		dispose();
	}
	
}


package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import concesionario.cliente.controller.Controller;
import concesionario.cliente.ventana.VentanaLogin;

public class VentanaGestionTarifas extends JFrame {

	private Controller loginController;
	private static final long serialVersionUID = 1L;
	
	public VentanaGestionTarifas(Controller loginController, String nickname) {
		this.loginController = loginController;   //errores
		inicioVentanaGestionTarifas(nickname);
	}
	
	
	public void inicioVentanaGestionTarifas(String nickname) {
		this.setTitle("Gestión de las Tarifas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,282);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
			JLabel msgGestion = new JLabel("Gestion de Tarifas");
			msgGestion.setHorizontalAlignment(SwingConstants.RIGHT);
			msgGestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
			msgGestion.setBounds(178, 11, 250, 33);
			panel.add(msgGestion);
			
			
			JButton buttonRegistroTarifa = new JButton("Registrar nueva tarifa");
			buttonRegistroTarifa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaRegistroTarifa vt = new VentanaRegistroTarifa(loginController, nickname);
					vt.setVisible(true);
					dispose();
				} 
			});
			buttonRegistroTarifa.setBounds(129, 66, 164, 23);
			panel.add(buttonRegistroTarifa);
			
			JButton buttonVeryEditTarifas = new JButton("Ver y editar tarifas");
			buttonVeryEditTarifas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//
				}
			});
			buttonVeryEditTarifas.setBounds(145, 117, 137, 23);
			panel.add(buttonVeryEditTarifas);
			
			//boton de volver que te lleva al menu de Admin
			JButton buttonVolver = new JButton("Volver");
			buttonVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volver(nickname);
				}
			});
			buttonVolver.setBounds(171, 172, 89, 23);
			panel.add(buttonVolver);
	}
	
	public void verUsuarios(String nickname) {
		VentanaEmpleados ve = new VentanaEmpleados(loginController, nickname);
    	ve.setVisible(true);
    	dispose();
	}
	
	public void volver(String nickname) {
		VentanaMenuAdmin vmenu = new VentanaMenuAdmin(loginController, nickname);
		vmenu.setVisible(true);
		dispose();
	}
	
}


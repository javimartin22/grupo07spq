package concesionario.cliente.ventana.comercial;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import concesionario.cliente.controller.ComercialController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;

public class VentanaMenuComercial extends JFrame {

	private static final long serialVersionUID = 1L;
	private ComercialController comercialController;
	private JButton buttonRegistrarCoche;
	
	public VentanaMenuComercial(ComercialController comercialController, String nickname) {
		this.comercialController = comercialController;
		initVentanaMenuComercial(nickname);
	}
	
	private void initVentanaMenuComercial(String nickname) {
		this.setTitle("Menu Comercial");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(451,282);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//panel que contiene todo
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
		JLabel nombreMecanico = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombreMecanico.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreMecanico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreMecanico.setBounds(178, 11, 250, 33);
		panel.add(nombreMecanico);
		
		buttonRegistrarCoche = new JButton("Registrar coche");
		buttonRegistrarCoche.setBounds(140, 63, 141, 29);
		panel.add(buttonRegistrarCoche);
		
		
		JButton btnVerVentas = new JButton("Ver Ventas");
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarVentas vvv = new VentanaVisualizarVentas(comercialController, nickname);
				vvv.setVisible(true);
				dispose();
			}
		});
		btnVerVentas.setBounds(154, 103, 117, 29);
		panel.add(btnVerVentas);
		
		JButton btnNewButton = new JButton("Ver Coches");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCochesConcesionario vcc = new VentanaCochesConcesionario(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(154, 144, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController controller = new LoginController(comercialController.getClienteApp());
				VentanaLogin vl = new VentanaLogin(controller);
				vl.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(154, 181, 117, 29);
		panel.add(btnSalir);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarCitas vcc = new VentanaVisualizarCitas(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(164, 219, 89, 23);
		panel.add(btnNewButton_1);
		buttonRegistrarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistrarCocheConcesionario vrcc = new VentanaRegistrarCocheConcesionario(comercialController, nickname);
				vrcc.setVisible(true);
				dispose();
			}
		}); 
	}
}

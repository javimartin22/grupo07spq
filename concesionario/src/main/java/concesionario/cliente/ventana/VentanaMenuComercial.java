package concesionario.cliente.ventana;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.CocheConcesionario;

public class VentanaMenuComercial extends JFrame {

	private static final long serialVersionUID = 1L;
	private LoginController loginController;
	private JButton buttonRegistrarCoche;
	private JComboBox comboMarca;
	private JTextField textModeloCoche;
	private JTextField textPrecio;
	
	public VentanaMenuComercial(LoginController loginController, String nickname) {
		this.loginController = loginController;
		initVentanaMenuComercial(nickname);
	}
	
	private void initVentanaMenuComercial(String nickname) {
		this.setTitle("Menu del comercial");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,282);
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
		
		JLabel labelMarca = new JLabel("Marca");
		labelMarca.setBounds(50, 16, 85, 14);
		panel.add(labelMarca);
		
		comboMarca= new JComboBox();
		comboMarca.addItem("BMW");
		comboMarca.addItem("Renault");
		comboMarca.addItem("Fiat");
		comboMarca.addItem("Mercedes");
		comboMarca.addItem("Ford");
		comboMarca.setBounds(50, 54, 100, 24);
		panel.add(comboMarca);
		
		
		JLabel labelModelo = new JLabel("Modelo");
		labelModelo.setBounds(200, 60, 85, 14);
		panel.add(labelModelo);
		
		
		textModeloCoche = new JTextField();
		textModeloCoche.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textModeloCoche.setBounds(200, 91, 198, 20);
		panel.add(textModeloCoche);
		textModeloCoche.setColumns(10);
		
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(300, 60, 85, 14);
		panel.add(labelPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPrecio.setBounds(300, 91, 198, 20);
		panel.add(textPrecio);
		textPrecio.setColumns(10);
		
		buttonRegistrarCoche = new JButton("Registrar coche");
		buttonRegistrarCoche.setBounds(200, 212, 89, 23);
		panel.add(buttonRegistrarCoche);
		buttonRegistrarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistrarCocheConcesionario vrcc = new VentanaRegistrarCocheConcesionario(loginController, nickname);
				vrcc.setVisible(true);
				dispose();
			}
		}); 
	}
}

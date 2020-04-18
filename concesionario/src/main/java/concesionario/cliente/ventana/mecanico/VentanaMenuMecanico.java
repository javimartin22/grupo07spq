package concesionario.cliente.ventana.mecanico;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import concesionario.cliente.controller.Controller;
import concesionario.cliente.ventana.VentanaLogin;

import javax.swing.JButton;


public class VentanaMenuMecanico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller loginController;
	
	public VentanaMenuMecanico(Controller loginController, String nickname){
		this.loginController = loginController;
		iniciarVentanaMenuMecanico(nickname);
	}
	
	
	public void iniciarVentanaMenuMecanico(String nickname){
		this.setTitle("Menu del mecanico");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,358);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//panel donde van todos los componentes
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
		JLabel nombreMecanico = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombreMecanico.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreMecanico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreMecanico.setBounds(178, 11, 250, 33);
		panel.add(nombreMecanico);
		
		//boton para anadir piezas, si clicas te lleva a una nueva ventana donde puedes realizar esta actividad
		JButton anadirPieza = new JButton("Ver Piezas");
		anadirPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPiezasMecanico vp = new VentanaPiezasMecanico(loginController, nickname);
				vp.setVisible(true);
				dispose();
			}
		});
		anadirPieza.setBounds(124, 71, 201, 23);
		panel.add(anadirPieza);
		
		//boton para poder ver el historial de los vehiculos que han pasado por el taller 
		JButton verHistoial = new JButton("Ver historial de los coches");
		verHistoial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hace falta instanciar la ventana donde se vera el hisorial
				VentanaCochesMatriculadosMecanico vcmm = new VentanaCochesMatriculadosMecanico(loginController, nickname);
				vcmm.setVisible(true);
				dispose();
			}
		});
		verHistoial.setBounds(124, 203, 201, 23);
		panel.add(verHistoial);
		
		//boton para poder registrar los vehiculos que pasan por el taller
		JButton registarVehiculo = new JButton("Registrar vehiculo del taller");
		registarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroCocheTaller vrct = new VentanaRegistroCocheTaller(loginController, nickname);
				vrct.setVisible(true);
				dispose();
			}
		});
		registarVehiculo.setBounds(124, 110, 201, 23);
		panel.add(registarVehiculo);
		
		//boton de salir que te lleva a la ventana de VentanaLogin donde se inicia la sesion
		JButton buttonSalir = new JButton("Salir");
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ventana para iniciar serion
				VentanaLogin vl = new VentanaLogin(loginController);
				vl.setVisible(true);///ee
				dispose();
			}
		});
		buttonSalir.setBounds(172, 295, 89, 23);
		panel.add(buttonSalir);
		
		JButton btnVerCochesTaller = new JButton("Ver Coches Taller");
		btnVerCochesTaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarCochesTaller vvct = new VentanaVisualizarCochesTaller(loginController, nickname);
				vvct.setVisible(true);
				dispose();
			}
		});
		btnVerCochesTaller.setBounds(124, 157, 201, 23);
		panel.add(btnVerCochesTaller);
		
		JButton verHistoial_1 = new JButton("Visualizar Presupuesto");
		verHistoial_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarPresupuestos vvp = new VentanaVisualizarPresupuestos(loginController, nickname);
				vvp.setVisible(true);
				dispose();
			}
		});
		verHistoial_1.setBounds(124, 246, 201, 23);
		panel.add(verHistoial_1);
	}
}

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

import concesionario.cliente.controller.GerenteController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import java.awt.Color;

public class VentanaMenuAdmin extends JFrame {

	private GerenteController gerenteController;
	private static final long serialVersionUID = 1L;
	
	public VentanaMenuAdmin(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;   //errores
		inicioVentanaMenuAdmin(nickname);
	}
	
	
	public void inicioVentanaMenuAdmin(String nickname) {
		this.setTitle("Menu del administrador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(445,311);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
			JLabel nombreAdmin = new JLabel("Bienvenid@ " + nickname.toUpperCase());
			nombreAdmin.setHorizontalAlignment(SwingConstants.CENTER);
			nombreAdmin.setForeground(Color.DARK_GRAY);
			nombreAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
			nombreAdmin.setBounds(199, 11, 230, 33);
			panel.add(nombreAdmin);
			
			
			JButton buttonAnadirUsuarios = new JButton("Anyadir Empleados");
			buttonAnadirUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String respuesta = JOptionPane.showInputDialog("¿Que tipo de Empleado desea registrar? (M/C/DC)");
					if (respuesta.equals("C") || respuesta.equals("c")) {
						VentanaRegistroComercial vrc = new VentanaRegistroComercial(gerenteController, nickname);
						vrc.setVisible(true);
						dispose();
					} else if (respuesta.equals("M") || respuesta.equals("m")){
						VentanaRegistroMecanico vrm = new VentanaRegistroMecanico(gerenteController, nickname);
						vrm.setVisible(true);
						dispose();
					} else if (respuesta.equals("DC") || respuesta.equals("dc") || respuesta.equals("Dc") || respuesta.equals("dC")){
						VentanaRegistroComercial vrc = new VentanaRegistroComercial(gerenteController, nickname);
						vrc.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panel, "Caracter no valido");
					}
				} 
			});
			buttonAnadirUsuarios.setBounds(36, 66, 162, 33);
			panel.add(buttonAnadirUsuarios);
			
			JButton buttonVerUsuarios = new JButton("Ver usuarios");
			buttonVerUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verUsuarios(nickname);
				}
			});
			buttonVerUsuarios.setBounds(237, 66, 162, 33);
			panel.add(buttonVerUsuarios);
			
			JButton buttonGestionTarifas = new JButton("Gestionar tarifas");
			buttonGestionTarifas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarTarifas(nickname);
				}
			});
			buttonGestionTarifas.setBounds(36, 120, 162, 33);
			panel.add(buttonGestionTarifas);
			
			//Boton para acceder a los estudios de Mercado
			JButton buttonEstudioMercado = new JButton("Estudios Mercado");
			buttonEstudioMercado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					estudiosMercado(nickname);
				}
			});
			buttonEstudioMercado.setBounds(138, 182, 162, 33);
			panel.add(buttonEstudioMercado);
			
			
			//boton de salir que te lleva a la ventana de VentanaLogin
			JButton buttonSalir = new JButton("Salir");
			buttonSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			buttonSalir.setBounds(176, 238, 89, 23);
			panel.add(buttonSalir);
			
			JButton btnNewButton = new JButton("Visualizar Horas");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaVisualizarHoras vvh = new VentanaVisualizarHoras(gerenteController, nickname);
					vvh.setVisible(true);
					dispose();
				}
			});
			btnNewButton.setBounds(237, 120, 162, 33);
			panel.add(btnNewButton);
	}
	
	public void verUsuarios(String nickname) {
		VentanaEmpleados ve = new VentanaEmpleados(gerenteController, nickname);
    	ve.setVisible(true);
    	dispose();
	}
	
	public void gestionarTarifas(String nickname) {
		VentanaGestionTarifas vg = new VentanaGestionTarifas(gerenteController, nickname);
    	vg.setVisible(true);
    	dispose();
	}
	
	public void estudiosMercado(String nickname) {
		VentanaGestionEstudioMercado vgem = new VentanaGestionEstudioMercado(gerenteController, nickname);
    	vgem.setVisible(true);
    	dispose();
	}
	
	public void salir() {
		LoginController controller = new LoginController(gerenteController.getClienteApp());
		VentanaLogin vlogin = new VentanaLogin(controller);
		vlogin.setVisible(true);
		dispose();
	}
	
}


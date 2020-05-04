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
			buttonAnadirUsuarios.setBounds(129, 66, 164, 23);
			panel.add(buttonAnadirUsuarios);
			
			JButton buttonVerUsuarios = new JButton("Ver usuarios");
			buttonVerUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verUsuarios(nickname);
				}
			});
			buttonVerUsuarios.setBounds(129, 100, 164, 23);
			panel.add(buttonVerUsuarios);
			
			JButton buttonGestionTarifas = new JButton("Gestionar tarifas");
			buttonGestionTarifas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarTarifas(nickname);
				}
			});
			buttonGestionTarifas.setBounds(129, 134, 164, 23);
			panel.add(buttonGestionTarifas);
			
			
			//boton de salir que te lleva a la ventana de VentanaLogin
			JButton buttonSalir = new JButton("Salir");
			buttonSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			buttonSalir.setBounds(166, 168, 89, 23);
			panel.add(buttonSalir);
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
	
	public void salir() {
		LoginController controller = new LoginController(gerenteController.getClienteApp());
		VentanaLogin vlogin = new VentanaLogin(controller);
		vlogin.setVisible(true);
		dispose();
	}
	
}


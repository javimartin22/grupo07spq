package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.Cliente;

public class VentanaMenuCliente extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonSalir;
	
	private Controller loginController;
	
	
	public VentanaMenuCliente(String nickname, Controller loginController) {
		this.loginController = loginController;
		initVentanaMenuCliente(nickname);
	}
	

	
	private void initVentanaMenuCliente(String nickname) {
		this.setTitle("Menu del cliente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(448,241);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//boton de salir que te lleva a la ventana de VentanaLogin
		buttonSalir = new JButton("Salir");
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin vlogin = new VentanaLogin(loginController);
				vlogin.setVisible(true);
				dispose();
			}
		});
		
		 
		buttonSalir.setBounds(183, 134, 89, 23);
		panel.add(buttonSalir);
		
		JButton btnNewButton = new JButton("Visualizar Catalogo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarCatalogo vvc = new VentanaVisualizarCatalogo(loginController, nickname);
				vvc.setVisible(true);
				dispose();
			}
		});
		
		//Acceder a la visualización de tarifas del taller
		btnNewButton.setBounds(151, 36, 151, 23);
		panel.add(btnNewButton);
		
		JButton btnTarifas= new JButton("Ver tarifas");
		btnTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarTarifas vvc = new VentanaVisualizarTarifas(loginController, nickname);
				vvc.setVisible(true);
				dispose();
			}
		});
		btnTarifas.setBounds(151, 90, 151, 23);
		panel.add(btnTarifas);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSeleccion = new JMenu("Configuración");
		menuBar.add(mnSeleccion);
		
		JMenuItem mntmNicname = new JMenuItem("Cambiar Nickname");
		mntmNicname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Response response = loginController.seleccionarCliente(nickname);
				if (response.getStatus() == Status.OK.getStatusCode()) {
					Cliente client = response.readEntity(Cliente.class);
					cambiarNickname(client);
				} else {
					System.out.println("llega mal");
				}
			}
		});
		mnSeleccion.add(mntmNicname);
		
		JMenuItem mntmContrasenya = new JMenuItem("Cambiar Contraseña");
		mntmContrasenya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Response response = loginController.seleccionarCliente(nickname);
				if (response.getStatus() == Status.OK.getStatusCode()) {
					Cliente client = response.readEntity(Cliente.class);
					cambiarContrasenia(client);
				} else {
					System.out.println("llega mal");
				}
				
			}
		});
		mnSeleccion.add(mntmContrasenya);
	}
	
	
	private void cambiarNickname(Cliente client) {
		String nickname = JOptionPane.showInputDialog("Introduzca el nuevo nickname: ");
		Response response = loginController.cambiarNicknameCliente(client, nickname); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), loginController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Fallo a la hora de registrar.");
			dispose();
		}
	}
	

	
	
	private void cambiarContrasenia(Cliente client) {
		String contrasenia = JOptionPane.showInputDialog("Introduzca la nueva contrasenia: ");
		Response response = loginController.cambiarContraseniaCliente(client, contrasenia); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(this, "Contrasenia cambiada.");
		} else {
			JOptionPane.showMessageDialog(this, "Fallo en el cambio de contrasenia.");
			dispose();
		}
	}
	
	public JButton getButtonSalir() {
		return buttonSalir;
	}


	public void setButtonSalir(JButton buttonSalir) {
		this.buttonSalir = buttonSalir;
	}
}

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

import concesionario.cliente.controller.ClienteController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.Cliente;

public class VentanaMenuCliente extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonSalir;
	
	private ClienteController clienteController;
	
	
	public VentanaMenuCliente(String nickname, ClienteController loginController) {
		this.clienteController = loginController;
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
				LoginController loginController = new LoginController(clienteController.getClienteApp());
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
				VentanaVisualizarCatalogo vvc = new VentanaVisualizarCatalogo(clienteController, nickname);
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
				VentanaVisualizarTarifas vvc = new VentanaVisualizarTarifas(clienteController, nickname);
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
				Cliente client = clienteController.seleccionarCliente(nickname);
				if (client != null) {
					cambiarNickname(client);
				} else {
					JOptionPane.showMessageDialog(panel, "Este usuario no existe.");
				}
			}
		});
		mnSeleccion.add(mntmNicname);
		
		JMenuItem mntmContrasenya = new JMenuItem("Cambiar Contraseña");
		mntmContrasenya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente client = clienteController.seleccionarCliente(nickname);
				if (client != null) {
					cambiarContrasenia(client);
				} else {
					JOptionPane.showMessageDialog(panel, "Este usuario no existe.");
				}
				
			}
		});
		mnSeleccion.add(mntmContrasenya);
	}
	
	private void cambiarNickname(Cliente client) {
		String nickname = JOptionPane.showInputDialog("Introduzca el nuevo nickname: ");
		if (clienteController.cambiarNicknameCliente(client, nickname)) {
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), clienteController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Fallo a la hora de registrar.");
		}
	}

	private void cambiarContrasenia(Cliente client) {
		String contrasenia = JOptionPane.showInputDialog("Introduzca el nuevo contrasenia: ");
		if (clienteController.cambiarContraseniaCliente(client, contrasenia)) {
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), clienteController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Fallo a la hora de registrar.");
		}
	}
	
	public JButton getButtonSalir() {
		return buttonSalir;
	}

	public void setButtonSalir(JButton buttonSalir) {
		this.buttonSalir = buttonSalir;
	}
}

package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
/**
 *VentanaMenuCliente (Ventana menu principal del Cliente)
 */
public class VentanaMenuCliente extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton buttonSalir;
	final Logger logger = LoggerFactory.getLogger(VentanaMenuCliente.class);
	static int iteration = 0;
	private ClienteController clienteController;
	
	/**
	 * Constructor de la VentanaMenuCliente 
	 * @param nickname (Nickname del Cliente)
	 * @param loginController (Objeto LoginController)
	 */
	public VentanaMenuCliente(String nickname, ClienteController loginController) {
		this.clienteController = loginController;
		initVentanaMenuCliente(nickname);
	}

	/**
	 * Creacion de la Frame
	 * @param nickname (Nickname del Cliente).
	 */
	private void initVentanaMenuCliente(String nickname) {
		this.setTitle("Menu del cliente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(396,259);
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
		 
		buttonSalir.setBounds(140, 155, 97, 23);
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
		btnNewButton.setBounds(26, 35, 154, 43);
		panel.add(btnNewButton);
		
		JButton btnTarifas= new JButton("Ver tarifas");
		btnTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarTarifas vvc = new VentanaVisualizarTarifas(clienteController, nickname);
				vvc.setVisible(true);
				dispose();
			}
		});
		btnTarifas.setBounds(26, 89, 154, 43);
		panel.add(btnTarifas);
		
		JButton btnNewButton_1 = new JButton("Cita Concesionario");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSolicitudCitasComercial vscc = new VentanaSolicitudCitasComercial(clienteController, nickname);
				vscc.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(210, 35, 154, 43);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cita Taller");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSolicitudCitaTaller vsct = new VentanaSolicitudCitaTaller(clienteController, nickname);
				vsct.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(210, 89, 154, 43);
		panel.add(btnNewButton_2);
		
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
					logger.error("Este usuario no existe.");
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
					logger.error("Este usuario no existe.");
				}
				
			}
		});
		mnSeleccion.add(mntmContrasenya);
	}
	
	/**
	 * Metodo para la modificacion el Nickname del Cliente
	 * @param client (Objeto Cliente)
	 */
	private void cambiarNickname(Cliente client) {
		String nickname = JOptionPane.showInputDialog("Introduzca el nuevo nickname: ");
		if (clienteController.cambiarNicknameCliente(client, nickname)) {
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), clienteController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			logger.error("Fallo en el registro del nuevo nickname.");
		}
	}
	/**
	 * Metodo para la modificacion de la Contrasenia
	 * @param client (Objeto Cliente)
	 */
	private void cambiarContrasenia(Cliente client) {
		String contrasenia = JOptionPane.showInputDialog("Introduzca el nuevo contrasenia: ");
		if (clienteController.cambiarContraseniaCliente(client, contrasenia)) {
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), clienteController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			logger.error("Fallo en el registro de la nueva contrasenia.");
		}
	}
}

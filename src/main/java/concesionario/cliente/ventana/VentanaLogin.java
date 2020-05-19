package concesionario.cliente.ventana;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ClienteController;
import concesionario.cliente.controller.ComercialController;
import concesionario.cliente.controller.MecanicoController;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.cliente.controller.GerenteController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.cliente.VentanaMenuCliente;
import concesionario.cliente.ventana.cliente.VentanasRegistroClientes;
import concesionario.cliente.ventana.comercial.VentanaMenuComercial;
import concesionario.cliente.ventana.departamentoCompras.VentanaMenuDepartamentoCompras;
import concesionario.cliente.ventana.gerente.VentanaMenuAdmin;
import concesionario.cliente.ventana.mecanico.VentanaMenuMecanico;

/**
* Clase de inicio de Sesion de la aplicacion.
*/
public class VentanaLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombreUsuario;
	private JPasswordField textContrasenya;
	private JButton buttonAceptar;
	final Logger logger = LoggerFactory.getLogger(VentanaLogin.class);
	static int iteration = 0;
	
	/**
	 * Controlador para la clase Login.
	 */
	private LoginController loginController;
	
	/**
	 * Constructor de la clase Login. 
	 * @param loginController (Controlador de la ventana Login)
	 */
	public VentanaLogin(LoginController loginController) {
		this.loginController = loginController;   //errores
		initVentanaLogin();
	}
	
	/**
	 * Create the frame.
	 */
	private void initVentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		
		
		//panel general
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		//label de usuario
		JLabel labelUsuario = new JLabel("Nombre de Usuario:");
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUsuario.setBounds(59, 94, 158, 14);
		panel.add(labelUsuario);
		
		//label de contraseña
		JLabel labelContrasenya = new JLabel("Contraseña:");
		labelContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelContrasenya.setBounds(59, 163, 117, 14);
		panel.add(labelContrasenya);
		
		//caja de texto donde escribir el nombre de usuario
		textNombreUsuario = new JTextField();
		textNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombreUsuario.setBounds(200, 91, 198, 20);
		panel.add(textNombreUsuario);
		textNombreUsuario.setColumns(10);
		
		//caja de texto donde escribir la contraseña
		textContrasenya = new JPasswordField();
		textContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textContrasenya.setBounds(200, 160, 198, 20);
		panel.add(textContrasenya);
		textContrasenya.setColumns(10);


		
		//boton de aceptar para iniciar sesión
		buttonAceptar = new JButton("Aceptar");
		buttonAceptar.setBounds(200, 212, 89, 23);
		panel.add(buttonAceptar);
		
		JLabel lblNewLabel = new JLabel("JMPY SL");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 35));
		lblNewLabel.setBounds(150, 11, 177, 54);
		panel.add(lblNewLabel);
		
		
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textNombreUsuario.getText();
				String password = textContrasenya.getText();
				login(email, password);
			}

		}); 
	}
	
	/**
	 * Metodo para obtener el nickname del usuario. 
	 * @return JTextField
	 */
	public JTextField getTextNombreUsuario() {
		return textNombreUsuario;
	}
	
	/**
	 * Metodo para modificar el nickname del usuario. 
	 * @param textNombreUsuario (JTextFiled - Nombre de usuario)
	 */
	public void setTextNombreUsuario(JTextField textNombreUsuario) {
		this.textNombreUsuario = textNombreUsuario;
	}

	/**
	 * Metodo para obtener la contrasenia del usuario. 
	 * @return JPasswordField
	 */
	public JPasswordField getTextContrasenya() {
		return textContrasenya;
	}
	
	/**
	 * Metodo para modificar la contrasenia del usuario. 
	 * @param textContrasenya (JPassworField - Nombre de usuario)
	 */
	public void setTextContrasenya(JPasswordField textContrasenya) {
		this.textContrasenya = textContrasenya;
	}

	/**
	 * Metodo para vaciar el nickname (JTextFiled) y la contrasenia (JPasswordFiled). 
	 */
	public void vaciarCampos(){
		textNombreUsuario.setText("");
		textContrasenya.setText("");
	}
	
	/**
	* Metodo para el inicio de sesion, en caso de que el usuario sea de tipo 0, iniciara sesion en modo Gerente, en caso de que sea de tipo 1 iniciara sesion en modo mecanico, 
	* si es tipo 2 en Comercial , en caso de ser de tipo 3 en modo usuario (cliente) y por ultimo en caso de ser tipo 4 en modo Departamento de Compras. En caso de que el tipo
	*  sea 5 o 6 eso sera ya que ese usuario no existe o los datos son incorrectos.
	*  @param nickname (nombre de usuario) 
	*  @param password (contrasenia de usuario) 
	*/
	public void login(String nickname, String password) {
		MecanicoController controller = new MecanicoController(loginController.getClienteApp());
		ClienteController clienteController = new ClienteController(loginController.getClienteApp());
		
		int tipoInicio = loginController.login(nickname, password);
		switch (tipoInicio) {
		case 0:
			GerenteController gerenteController = new GerenteController(loginController.getClienteApp());
			VentanaMenuAdmin vma = new VentanaMenuAdmin(gerenteController, nickname);
			vma.setVisible(true);
			dispose();
			break;
		case 1: 
			VentanaMenuMecanico vmm = new VentanaMenuMecanico(controller, nickname);
			vmm.setVisible(true);
			dispose();
			break;
		case 2:
			ComercialController comercialController = new ComercialController(loginController.getClienteApp());
			VentanaMenuComercial vmcom = new VentanaMenuComercial(comercialController, nickname);
			vmcom.setVisible(true);
			dispose();
			break;
		case 3:
			VentanaMenuCliente vmc = new VentanaMenuCliente(nickname, clienteController);
	    	vmc.setVisible(true);
	    	dispose();
			break;
		case 4:
			DepartmentoComprasController departamComprasController = new DepartmentoComprasController(loginController.getClienteApp());
			VentanaMenuDepartamentoCompras vmdc = new VentanaMenuDepartamentoCompras(departamComprasController, nickname);
			vmdc.setVisible(true);
			dispose();
			break;
		case 5:
			JOptionPane.showMessageDialog(this, "Datos incorrectos");
			 logger.error("Datos incorrectos.");
			break;
		case 6: 	
			int respuesta = JOptionPane.showConfirmDialog(this, "Usuario no registrado ¿Desea registrarse?");
			switch (respuesta) {
			case 0:
				VentanasRegistroClientes vrc = new VentanasRegistroClientes(nickname, password, clienteController);
				vrc.setVisible(true);
				dispose();
				break;
			}
			break;
}
	}

}

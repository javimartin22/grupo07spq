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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreUsuario;
	private JPasswordField textContrasenya;
	private JButton buttonAceptar;
	//private Connection con;
	//private Statement st;
	
	private LoginController loginController;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	public VentanaLogin(LoginController loginController) {
		this.loginController = loginController;   //errores
		initVentanaLogin();
	}
	
	
	private void initVentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//con =BD.initBD("Taller");
		//st = BD.usarCrearTablasBD(con);
		
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
	
	public JTextField getTextNombreUsuario() {
		return textNombreUsuario;
	}

	public void setTextNombreUsuario(JTextField textNombreUsuario) {
		this.textNombreUsuario = textNombreUsuario;
	}

	public JPasswordField getTextContrasenya() {
		return textContrasenya;
	}

	public void setTextContrasenya(JPasswordField textContrasenya) {
		this.textContrasenya = textContrasenya;
	}

	public JButton getButtonAceptar() {
		return buttonAceptar;
	}

	public void setButtonAceptar(JButton buttonAceptar) {
		this.buttonAceptar = buttonAceptar;
	}

	
	public void vaciarCampos(){
		textNombreUsuario.setText("");
		textContrasenya.setText("");
	}
	
	public void login(String nickname, String password) {
		Response response = loginController.login(nickname, password); //estoy aqui
		
		if(response.getStatus() == Status.OK.getStatusCode()) {
			String str = response.readEntity(String.class);
	        int tipoInicio = Integer.parseInt(str);
	        switch (tipoInicio) {
			case 0:
				VentanaMenuAdmin vma = new VentanaMenuAdmin(loginController, nickname);
				vma.setVisible(true);
				dispose();
				break;
			case 1: 
				JOptionPane.showMessageDialog(this,"Iniciando sesion como mecanico");
				break;
			case 2:
				VentanaMenuComercial vmcom = new VentanaMenuComercial(loginController, nickname);
				vmcom.setVisible(true);
				dispose();
				break;
			case 3:
				VentanaMenuCliente vmc = new VentanaMenuCliente(nickname, loginController);
	        	vmc.setVisible(true);
	        	dispose();
				break;
			case 4:
			}
		 }else if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()){
	         JOptionPane.showMessageDialog(this, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);

		 } else {
			 int respuesta = JOptionPane.showConfirmDialog(this, "Usuario no registrado ¿Desea registrarse?");
			 switch (respuesta) {
			case 0:
				VentanasRegistroClientes vrc = new VentanasRegistroClientes(nickname, password, loginController);
	        	vrc.setVisible(true);
	        	dispose();
				break;

			default:
				break;
			}
		 }
	}

}

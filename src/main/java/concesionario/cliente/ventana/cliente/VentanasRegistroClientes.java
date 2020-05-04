package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ClienteController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanasRegistroClientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private String dni = "";
	private String nombre = "";
	private String apellido = "";
	private int s = 0;
	private String email = "";
	private String dir = "";
	private String c = "";
	private String numTelefono = "";
	private String ciudad = "";
	private ClienteController clienteController;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarTarifas.class);
	static int iteration = 0;
	
	public VentanasRegistroClientes(String nickname,String contra, ClienteController clienteController) {
		this.clienteController = clienteController;
		initVentanaRegistroCliente(nickname, contra);
	}
	
	private void initVentanaRegistroCliente(String nickname, String contra) {
		setTitle("Registro Clientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 410);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(23, 35, 61, 16);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 66, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 96, 61, 16);
		contentPane.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(23, 127, 61, 16);
		contentPane.add(lblSexo);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(23, 157, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(23, 195, 92, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(23, 233, 61, 16);
		contentPane.add(lblCiudad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(23, 272, 105, 16);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(23, 310, 92, 16);
		contentPane.add(lblNTelefono);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController controller = new LoginController(clienteController.getClienteApp());
				VentanaLogin vlogin = new VentanaLogin(controller);
				vlogin.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(45, 341, 117, 29);
		contentPane.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(159, 30, 209, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(159, 61, 209, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(159, 91, 209, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(159, 152, 209, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(159, 190, 209, 26);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(159, 267, 209, 26);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(159, 305, 209, 26);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(159, 228, 209, 26);
		contentPane.add(textField_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otro"}));
		comboBox.setBounds(159, 123, 117, 27);
		contentPane.add(comboBox);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dni = textField.getText();
				nombre = textField_1.getText();
				apellido = textField_2.getText();
				s = comboBox.getSelectedIndex();
				String sexo = comprobarSexo(s);
				email = textField_3.getText();
				dir = textField_4.getText();
				c = textField_5.getText();
				int codigoPostal = Integer.parseInt(c);
				numTelefono = textField_6.getText();
				ciudad = textField_7.getText();
				Cliente client = new Cliente(dni, nickname, 3, contra, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono);
				registrar(client);
			}
		});
		btnRegistrar.setBounds(228, 341, 117, 29);
		contentPane.add(btnRegistrar);
	}
	
	public String comprobarSexo(int s){
		String sexo = "";
		switch (s) {
		case 0:
			sexo = "Hombre";
			break;
		case 1: 
			sexo = "Mujer";
		case 3: 
			sexo = "Otro";
		}
		return sexo;
	}
	
	public boolean comprobarCampos() {
		boolean bool = false; 
		dni = textField.getText();
		nombre = textField_1.getText();
		apellido = textField_2.getText();
		email = textField_3.getText();
		dir = textField_4.getText();
		c = textField_5.getText();
		numTelefono = textField_6.getText();
		ciudad = textField_7.getText();
		//Comprobamos si todos los campos han sido completados.
		if (!nombre.equals("") && !dni.equals("") && !apellido.equals("") && !email.equals("") && !ciudad.equals("") && !c.equals("") && !dir.equals("") && !numTelefono.equals("")) {
			//Comprobamos la longitud del DNI:
			if (dni.length() == 9 ) {
				//Compobamos la longitud del CodigoPostal
				if (c.length() == 5) {
					if (comprobarTelefono(numTelefono) && numTelefono.length() == 9) {
						if (!email.contains("@")){
							bool = true;
						} else {
							JOptionPane.showMessageDialog(contentPane, "El email debe contener una @");
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "El numero de telefono debe ser correcto."); //En caso de que el CodigoPostal no sea correcto se muestra el mensaje.
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "El Codigo Postal debe tener 5 digitos."); //En caso de que el CodigoPostal no sea correcto se muestra el mensaje.
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "El DNI debe contener 10 digitos xxxxxxxxxL."); //En caso de que el DNI no sea correcto se muestra el mensaje.
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar rellenados.");
		}
		return bool;
	}
	
	public boolean comprobarTelefono (String numTelefono) {
		try {
			Integer.parseInt(numTelefono);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public void registrar(Cliente client) {
		if (clienteController.registroCliente(client)) {
			logger.info("Cliente registrado correctamente.");
			VentanaMenuCliente vmc = new VentanaMenuCliente(client.getNickname(), clienteController);
        	vmc.setVisible(true);
        	dispose();
		} else {
			logger.error("Fallo en el registro del Cliente.");
		}
	}
}

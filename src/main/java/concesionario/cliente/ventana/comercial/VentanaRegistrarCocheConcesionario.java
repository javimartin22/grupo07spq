package concesionario.cliente.ventana.comercial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CocheConcesionario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.Font;

/**
 * Clase para registrar los coches en el concesionario
 */
public class VentanaRegistrarCocheConcesionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField modelo;
	private JTextField marca;
	private JTextField precio;
	private JTextField textField_1;
	final Logger logger = LoggerFactory.getLogger(VentanaRegistrarCocheConcesionario.class);
	static int iteration = 0;
	
	/**
	 * Controlador para la clase ComercialController
	 */
	private ComercialController comercialController; 

	/**
	 * Controller para la clase ComercialController
	 * @param comercialController (Controlador de la ventana VentanaRegistrarCocheComercial)
	 * @param nickname (Nickname del comercial)
	 */ 
	public VentanaRegistrarCocheConcesionario (ComercialController comercialController, String nickname) {
		setTitle("Registro Coche Concesionario");
		setResizable(false);
		this.comercialController = comercialController;
		iniciarVentanaRegistrarCocheConcesionario(nickname);
	}
	
	/**
	 * Create the frame
	 * @param nickname (Nickname del Comercial)
	 */
	public void iniciarVentanaRegistrarCocheConcesionario(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(105, 290, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNombreDelModelo = new JLabel("Nombre del Modelo:");
		lblNombreDelModelo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreDelModelo.setBounds(37, 32, 171, 16);
		contentPane.add(lblNombreDelModelo);
		
		JLabel lblNewLabel = new JLabel("Nombre de la Marca:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(37, 70, 171, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(37, 111, 171, 16);
		contentPane.add(lblNewLabel_1);
		
		modelo = new JTextField();
		modelo.setBounds(220, 27, 199, 26);
		contentPane.add(modelo);
		modelo.setColumns(10);
		
		marca = new JTextField();
		marca.setBounds(220, 65, 199, 26);
		contentPane.add(marca);
		marca.setColumns(10);
		
		precio = new JTextField();
		precio.setBounds(220, 106, 199, 26);
		contentPane.add(precio);
		precio.setColumns(10);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColor.setBounds(37, 150, 61, 16);
		contentPane.add(lblColor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rojo", "Azul ", "Plata", "Amarillo", "Verde", "Blanco", "Negro", "Blanco Marfil", "Gris"}));
		comboBox.setBounds(220, 146, 137, 27);
		contentPane.add(comboBox);
		
		JLabel lblUnidades = new JLabel("Numero de Puertas:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidades.setBounds(37, 187, 148, 16);
		contentPane.add(lblUnidades);
		
		JLabel lblNewLabel_2 = new JLabel("CV:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(37, 222, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Unidades: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(37, 258, 171, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(220, 217, 199, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(220, 252, 76, 26);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(220, 182, 76, 26);
		contentPane.add(spinner_1);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					String ma = marca.getText();
					String mo = modelo.getText();
					int pr = Integer.parseInt(precio.getText());
					int cv = Integer.parseInt(textField_1.getText());
					int numPuertas = Integer.parseInt(spinner_1.getValue().toString());
					int c = comboBox.getSelectedIndex();
					String color = comercialController.comprobarColor(c);
					int unidades = Integer.parseInt(spinner.getValue().toString());
					
					CocheConcesionario coche = new CocheConcesionario(ma, mo, pr, cv, numPuertas, color, unidades);
					registrarCoche(coche, nickname);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar completos");
					logger.error("Todos los campos deben estar correctamente rellenados", iteration++);
				}
				
			}
		});
		btnNewButton.setBounds(259, 290, 117, 29);
		contentPane.add(btnNewButton);
		
	}
	
	/**
	 * Metodo para registrar coches
	 * @param coche Objeto de tipo CocheConcesionario
	 * @param nickname Nickname del comercial
	 */
	public void registrarCoche(CocheConcesionario coche, String nickname) {
		if(comercialController.registrarCoche(coche)) {
			logger.info("El vehiculo ha sido registrado correctamente.");
			VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController, nickname);
			vmc.setVisible(true);
			dispose();
		} else {
			logger.error("Error al registrar el CocheConcesionario.");
		}
	}
	
	/**
	 * Metodo para comprobar los campos 
	 * @return boolean Si los campos no estan vacios, devuelve un True y si no False
	 */
	public boolean comprobarCampos() {
		boolean datos = false;
		if (!textField_1.getText().isEmpty() && !modelo.getText().isEmpty() && !marca.getText().isEmpty() && !precio.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
}

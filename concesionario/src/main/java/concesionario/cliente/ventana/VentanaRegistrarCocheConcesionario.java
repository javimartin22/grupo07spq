package concesionario.cliente.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.CocheConcesionario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class VentanaRegistrarCocheConcesionario extends JFrame {

	private JPanel contentPane;
	private LoginController loginController; 
	private JTextField modelo;
	private JTextField marca;
	private JTextField precio;
	private JTextField textField_1;

	public VentanaRegistrarCocheConcesionario (LoginController loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		iniciarVentanaRegistrarCocheConcesionario(nickname);
	}
	
	public void iniciarVentanaRegistrarCocheConcesionario(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(105, 290, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNombreDelModelo = new JLabel("Nombre del Modelo:");
		lblNombreDelModelo.setBounds(37, 32, 171, 16);
		contentPane.add(lblNombreDelModelo);
		
		JLabel lblNewLabel = new JLabel("Nombre de la Marca:");
		lblNewLabel.setBounds(37, 70, 171, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio: ");
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
		lblColor.setBounds(37, 150, 61, 16);
		contentPane.add(lblColor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rojo", "Azul ", "Plata", "Amarillo", "Verde", "Blanco", "Negro", "Blanco Marfil", "Gris", "Otro"}));
		comboBox.setBounds(220, 146, 137, 27);
		contentPane.add(comboBox);
		
		JLabel lblUnidades = new JLabel("Numero de Puertas:");
		lblUnidades.setBounds(37, 187, 148, 16);
		contentPane.add(lblUnidades);
		
		JLabel lblNewLabel_2 = new JLabel("CV:");
		lblNewLabel_2.setBounds(37, 222, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Unidades: ");
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
				String ma = marca.getText();
				String mo = modelo.getText();
				int pr = Integer.parseInt(precio.getText());
				int cv = Integer.parseInt(textField_1.getText());
				int numPuertas = Integer.parseInt(spinner_1.getValue().toString());
				int c = comboBox.getSelectedIndex();
				String color = comprobarColor(c);
				int unidades = Integer.parseInt(spinner.getValue().toString());
				
				CocheConcesionario coche = new CocheConcesionario(ma, mo, pr, cv, numPuertas, color, unidades);
				registrarCoche(coche, nickname);
			}
		});
		btnNewButton.setBounds(259, 290, 117, 29);
		contentPane.add(btnNewButton);
		
	}
	
	private String comprobarColor(int c) {
		String color = "";
		switch (c) {
			case 0:
				color = "Rojo";
				break;
			case 1:
				color = "Azul";
				break;
			case 2:
				color = "Plata";
				break;
			case 3:
				color = "Amarillo";
				break;
			case 4:
				color = "Verde";
				break;
			case 5: 
				color = "Blanco";
				break;
			case 6: 
				color = "Negro";
				break;
			case 7:
				color = "Blanco Marfil";
				break;
			case 8: 
				color = "Gris";
				break;
			case 9: 
				color = JOptionPane.showInputDialog("¿Que color desea?");
				break;
		}
		return color;
	}
	
	public void registrarCoche(CocheConcesionario coche, String nickname) {
		Response response = loginController.registrarCoche(coche);
		
		if(response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(this, "Coche registrado");
			VentanaMenuComercial vmc = new VentanaMenuComercial(loginController, nickname);
			vmc.setVisible(true);
			dispose();
		}
		 JOptionPane.showMessageDialog(this, "Error al registrar coche", "Error", JOptionPane.ERROR_MESSAGE);
	}
}

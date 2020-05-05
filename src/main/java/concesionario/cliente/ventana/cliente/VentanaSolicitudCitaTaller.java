package concesionario.cliente.ventana.cliente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.ClienteController;
import concesionario.datos.CitaTaller;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class VentanaSolicitudCitaTaller extends JFrame {
	private static final long serialVersionUID = 1L;
	private ClienteController clienteController;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;

	public VentanaSolicitudCitaTaller(ClienteController clienteController, String nickname) {
		setTitle("Solicitud Cita Taller");
		setResizable(false);
		setLocationRelativeTo(null);
		this.clienteController = clienteController;
		ventanaSolicitudCitaTaller(nickname);
	}
	
	public void ventanaSolicitudCitaTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuCliente vmc = new VentanaMenuCliente(nickname, clienteController);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(78, 288, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(44, 56, 108, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(44, 91, 108, 14);
		contentPane.add(lblDni);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(44, 127, 108, 14);
		contentPane.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHora.setBounds(44, 164, 108, 14);
		contentPane.add(lblHora);
		
		JLabel lblMecanico = new JLabel("Mecanico:");
		lblMecanico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMecanico.setBounds(44, 199, 108, 14);
		contentPane.add(lblMecanico);
		
		textField = new JTextField();
		textField.setBounds(172, 53, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(172, 88, 201, 20);
		contentPane.add(textField_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(172, 127, 142, 20);
		contentPane.add(dateChooser);
		
		List<String> horas = crearHoras();
		comboBox = new JComboBox();
		comboBox.setBounds(172, 161, 79, 20);
		for (String hora : horas) {
			comboBox.addItem(hora);
		}
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(172, 196, 166, 20);
		for (String nombre : clienteController.cargarTablaMecanicos()) {
			comboBox_1.addItem(nombre);
		}
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Problema:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(44, 243, 108, 14);
		contentPane.add(lblNewLabel_4_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(172, 240, 201, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("Incribase para solicitar una cita con un Comercial:");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 373, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Solicitar Cita");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comprobarDatos()) {
					if (dateChooser.getDate().getDay() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Los domingos el concesionario se encuentra cerrado.");
					} else {
						int mes = dateChooser.getDate().getMonth() + 1;
						int anyo = dateChooser.getDate().getYear() + 1900;
						String fecha = dateChooser.getDate().getDate() + "-" + mes + "-" + anyo;
						String hora = comboBox.getSelectedItem().toString();
						String mecanico = comboBox_1.getSelectedItem().toString();
						if (comprobarFecha(fecha, hora, mecanico)){
							CitaTaller cita = new CitaTaller(textField.getText(), textField_1.getText(), fecha, hora, mecanico, textField_2.getText());
							registrarCitaTaller(cita);
						} else {
							JOptionPane.showMessageDialog(contentPane, "La hora seleccionada no se encuentra disponible.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Debe rellenar todos los campos.");
				}
			}
		});
		btnNewButton_1.setBounds(237, 288, 116, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public List<String> crearHoras(){
		List<String> horas = new ArrayList<String>();
		for (int i = 0; i <= 2; i++) {
			int a = 10 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		} 
		for (int i = 0; i <= 4; i++) {
			int a = 15 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		}
		return horas;
	}
	
	public boolean comprobarDatos() {
		if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && textField_1.getText().length() == 9 && !textField_2.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean comprobarFecha(String fecha, String hora, String mecanico) {
		String restriccion = fecha + ";" + hora + ";" + mecanico;
		return clienteController.comprobarCitaTaller(restriccion);
	}
	
	public void registrarCitaTaller(CitaTaller citaTaller) {
		if (clienteController.registroCitaTaller(citaTaller)) {
			JOptionPane.showMessageDialog(contentPane, "Registrada");
		}
	}
}

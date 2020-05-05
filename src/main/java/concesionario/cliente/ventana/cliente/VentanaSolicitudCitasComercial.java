package concesionario.cliente.ventana.cliente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import concesionario.cliente.controller.ClienteController;
import concesionario.datos.CitaComercial;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaSolicitudCitasComercial extends JFrame {
	private static final long serialVersionUID = 1L;
	private ClienteController clienteController;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JDateChooser dateChooser;
	private JComboBox comboBox;

	public VentanaSolicitudCitasComercial(ClienteController clienteController, String nickname) {
		this.clienteController = clienteController;
		ventanaSolicitudCitasComercial(nickname);
	}

	/**
	 * Create the frame.
	 */
	public void ventanaSolicitudCitasComercial(String nickname) {
		setTitle("Solicitud Cita Comercial");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		List<String> horas = crearHoras();
		
		JLabel lblNewLabel = new JLabel("Incribase para solicitar una cita con un Comercial:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 21, 373, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuCliente vmc = new VentanaMenuCliente(nickname, clienteController);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(75, 280, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(32, 87, 94, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DNI:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setBounds(32, 118, 94, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fecha:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setBounds(32, 153, 94, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Hora:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_3.setBounds(32, 188, 94, 14);
		contentPane.add(lblNewLabel_1_3);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(193, 153, 166, 20);
		contentPane.add(dateChooser);
		
		textField = new JTextField();
		textField.setBounds(193, 84, 166, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 115, 166, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(193, 185, 79, 20);
		for (String hora : horas) {
			comboBox.addItem(hora);
		}
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(193, 225, 166, 20);
		for (String nombre : clienteController.cargarTablaComercial()) {
			comboBox_1.addItem(nombre);
		}
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("Solicitar Cita");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarDatos()) {
					if (dateChooser.getDate().getDay() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Los domingos el concesionario se encuentra cerrado.");
					} else {
						int mes = dateChooser.getDate().getMonth() + 1;
						int anyo = dateChooser.getDate().getYear() + 1900;
						String fecha = dateChooser.getDate().getDate() + "-" + mes + "-" + anyo;
						String hora = comboBox.getSelectedItem().toString();
						String comercial = comboBox_1.getSelectedItem().toString();
						if (comprobarFecha(fecha, hora, comercial)){
							CitaComercial cita = new CitaComercial(textField.getText(), textField_1.getText(), fecha, hora, comercial);
							registrarCitaComercial(cita);
						} else {
							JOptionPane.showMessageDialog(contentPane, "La hora seleccionada no se encuentra disponible.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Debe rellenar todos los campos.");
				}
			}
		});
		btnNewButton_1.setBounds(207, 280, 132, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Comercial:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(32, 228, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		
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
		for (int i = 0; i <= 3; i++) {
			int a = 16 + i;
			horas.add(a+":00");
			horas.add(a+":15");
			horas.add(a+":30");
			horas.add(a+":45");
		}
		return horas;
	}
	
	public boolean comprobarDatos() {
		if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && textField_1.getText().length() == 9) {
			return true;
		}
		return false;
	}
	
	public boolean comprobarFecha(String fecha, String hora, String comercial) {
		String restriccion = fecha + ";" + hora + ";" + comercial;
		return clienteController.comprobarCitaComercial(restriccion);
	}
	
	public void registrarCitaComercial(CitaComercial citaComercial) {
		if (clienteController.registroCitaComercial(citaComercial)) {
			JOptionPane.showMessageDialog(contentPane, "Registrada");
		}
	}
}

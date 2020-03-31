package concesionario.cliente.ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistoPiezas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistoPiezas frame = new VentanaRegistoPiezas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistoPiezas() {
		setResizable(false);
		setTitle("Registro de Nuevas Piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String ubicaciones [] = {"Alamacen 1 - Estanteria 1", "Almacen 1 - Estanteria 2", "Almacen 1 - Estanteria 3", "Almacen 2 - Estanteria 1", "Almacen 2 - Estanteria 2", "Almacen 2 - Estanteria 3"};
		
		JLabel lblNewLabel = new JLabel("Si desea registrar una nueva pieza rellene los siguintes datos:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblCodigoIdentificativo = new JLabel("Codigo Identificativo:");
		lblCodigoIdentificativo.setBounds(53, 61, 150, 16);
		contentPane.add(lblCodigoIdentificativo);
		
		textField = new JTextField();
		textField.setBounds(231, 56, 228, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Pieza");
		lblNombreDeLa.setBounds(53, 97, 150, 16);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de Piezas:");
		lblNewLabel_1.setBounds(53, 135, 150, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(231, 92, 228, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUbicacionDeLa = new JLabel("Ubicacion de la Pieza");
		lblUbicacionDeLa.setBounds(53, 173, 150, 16);
		contentPane.add(lblUbicacionDeLa);
		
		comboBox = new JComboBox(ubicaciones);
		comboBox.setBounds(231, 168, 228, 27);
		contentPane.add(comboBox);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datos = comprobarDatos();
				if (datos) {
					System.out.println("Codigo: " + textField.getText());
					System.out.println("Nombre: " + textField_1.getText());
					System.out.println("Numero de Piezas:" + textField_2.getText());
					System.out.println("Ubicaccion: " + comboBox.getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar rellenados.");
				}
				
			}
		});
		btnRegistrar.setBounds(265, 207, 117, 29);
		contentPane.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(126, 207, 117, 29);
		contentPane.add(btnCancelar);
		
		textField_2 = new JTextField();
		textField_2.setBounds(231, 130, 228, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
	
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()) {
			datos = true;
		} 
		
		
		return datos;
	}
}

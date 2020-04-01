package concesionario.cliente.ventana;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.servidor.BaseDatos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class VentanaRegistoPiezas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JSpinner spinner;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String nombreMecanico) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistoPiezas frame = new VentanaRegistoPiezas(nombreMecanico);
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
	public VentanaRegistoPiezas(String nombreMecanico) {
		setResizable(false);
		setTitle("Registro de Nuevas Piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 281);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = BD.initBD("Piezas");
		st = BD.usarCrearTablasBD(con);
		
		
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
				boolean bool = comprobarDatos();
				if (bool) {
					//Obtenemos los textos de los textfield y los asignamos a dos variables de tipo String:
					String codigo = textField.getText();
					String nombre = textField_1.getText();
					
						//Obtenemos el numero del combobox y lo convertimos en un String:
						String ubicacion = parseUbicacion(comboBox.getSelectedIndex());
						
						//Obtenemos el String del spinner y lo convertimos en un entero:
						String text = spinner.getValue().toString();
						int unidades = Integer.parseInt(text);
						
						//Preguntamos si desea realizar registrar una nueva pieza:
						int respuesta = JOptionPane.showConfirmDialog(contentPane, "¿Desea registrar una nueva pieza?");
						if (respuesta == 0){ //En caso afirmativo la pieza se registra en la base de datos y se vacian los campos:
							registrarBD(codigo, nombre, unidades, ubicacion);
							vaciarCampos();
						} else if (respuesta == 2){//En caso de pulsar cancel, se cancela el registro de la pieza en la base de datos y continuan todos los datos correctamente
							JOptionPane.showMessageDialog(contentPane, "La pieza no ha sido registrada.");
						} else { //En caso contrario se registra la pieza y se regresa al menu principal:
							registrarBD(codigo, nombre, unidades, ubicacion);
							dispose();
						}
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
		
		spinner = new JSpinner();
		spinner.setBounds(231, 130, 79, 26);
		contentPane.add(spinner);
	}
	
	//Conectar con el servidor para poder hacer el registro en la BD:
	private void registrarBD(String codigo, String nombre, int unidades, String ubicacion) {
		BD.piezasInsert(st, codigo, nombre, unidades, ubicacion);
		BD.piezasUtilizadasInsert(st, codigo, nombre, 0, ubicacion);
	}
	
	//Borrar todos los campos de tipo textfield y inicializar los demas tipos.
	private void vaciarCampos() {
		textField.setText(""); //Borrar contenido.
		textField_1.setText(""); //Borrar contenido.
		comboBox.setSelectedIndex(0); //Poner valor predeterminado, en este caso el que se encuentra en el index 0.
		spinner.setValue(0); //Poner valor prederminado, en este caso 0.
	}
	
	//Traducir la ubicacion del entero obtenido en el ComboBox a String.
	private String parseUbicacion(int ubicacion) {
		String ub = "";
		switch (ubicacion) {
		case 0:
			ub = "Alamacen 1 - Estanteria 1";
			break;
		case 1:
			ub = "Alamacen 1 - Estanteria 2";
			break;
		case 2:
			ub = "Alamacen 1 - Estanteria 3";
			break;
		case 3:
			ub = "Alamacen 2 - Estanteria 1";
			break;
		case 4:
			ub = "Alamacen 2 - Estanteria 2";
			break;
		case 5:
			ub = "Alamacen 2 - Estanteria 3";
			break;
		}
		return ub;
	}
	
	//Comprobar que los campos estan todos rellenados.
	private boolean comprobarDatos() {
		boolean datos = false;
		if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
			datos = true;
		} 
		return datos;
	}
	
	
}
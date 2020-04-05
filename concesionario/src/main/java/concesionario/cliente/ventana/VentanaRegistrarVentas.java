package concesionario.cliente.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Venta;

import javax.swing.JLayeredPane;

public class VentanaRegistrarVentas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private LoginController loginController;
	private JTextField textField;
	private JTextField textField_1;

	public VentanaRegistrarVentas(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaAgregarVentas(nickname);
	}


	/**
	 * Create the frame.
	 */
	public void iniciarVentanaAgregarVentas(String nickname) {

		setResizable(false);
		setTitle("Registrar ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Si desea registrar una venta rellene los siguientes datos:");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarVentas vvv = new VentanaVisualizarVentas(loginController, nickname);
				vvv.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(103, 213, 117, 29);
		contentPane.add(btnCancelar);

		JLabel lblContrasenya = new JLabel("Modelo: ");
		lblContrasenya.setBounds(53, 98, 150, 16);
		contentPane.add(lblContrasenya);

		JLabel lblNickname = new JLabel("Marca: ");
		lblNickname.setBounds(53, 61, 150, 16);
		contentPane.add(lblNickname);

		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(231, 56, 228, 26);
		contentPane.add(textFieldMarca);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarDatos()) {
					Calendar f = Calendar.getInstance();
					int anyo = f.get(Calendar.YEAR);
					int mes = f.get(Calendar.MONTH);
					int dia = f.get(Calendar.DAY_OF_MONTH);
					String fecha = anyo + "-" + mes + "-" + dia;
					Venta venta = new Venta(fecha, textFieldModelo.getText(), textFieldMarca.getText(), textField.getText(), nickname, textField_1.getText());
					registrarDatos(venta);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Debe rellenar todos los campos");
				}
			}
		});
		btnRegistrar.setBounds(277, 213, 117, 29);
		contentPane.add(btnRegistrar);

		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(231, 93, 228, 26);
		contentPane.add(textFieldModelo);
		textFieldModelo.setColumns(10);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(291, 168, 1, 1);
		contentPane.add(layeredPane);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(53, 135, 122, 16);
		contentPane.add(lblMatricula);

		textField = new JTextField();
		textField.setBounds(231, 130, 228, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNombreComprador = new JLabel("Nombre Comprador:");
		lblNombreComprador.setBounds(53, 173, 150, 16);
		contentPane.add(lblNombreComprador);

		textField_1 = new JTextField();
		textField_1.setBounds(231, 168, 228, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}


	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textField_1.getText().isEmpty() && !textFieldMarca.getText().isEmpty() && !textFieldModelo.getText().isEmpty() && !textField.getText().isEmpty()) {
			datos = true;
		} 
		return datos;
	}

	public void registrarDatos(Venta venta) {
		Response response = loginController.registrarVenta(venta); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(contentPane, "Venta registrada correctamente");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Venta no regsitrada");
		}
	}
} 
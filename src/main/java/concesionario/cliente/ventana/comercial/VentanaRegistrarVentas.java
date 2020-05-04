package concesionario.cliente.ventana.comercial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.Venta;

import javax.swing.JLayeredPane;
import java.awt.Font;

public class VentanaRegistrarVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private ComercialController comercialController;
	private JTextField textField;
	private JTextField textField_1;
	final Logger logger = LoggerFactory.getLogger(VentanaRegistrarVentas.class);
	static int iteration = 0;

	public VentanaRegistrarVentas(ComercialController comercialController, String nickname) {
		this.comercialController = comercialController;
		iniciarVentanaAgregarVentas(nickname);
	}

	public void iniciarVentanaAgregarVentas(String nickname) {
		setResizable(false);
		setTitle("Registro Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Si desea registrar una venta rellene los siguientes datos:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarVentas vvv = new VentanaVisualizarVentas(comercialController, nickname);
				vvv.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(103, 213, 117, 29);
		contentPane.add(btnCancelar);

		JLabel lblContrasenya = new JLabel("Modelo: ");
		lblContrasenya.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContrasenya.setBounds(53, 98, 150, 16);
		contentPane.add(lblContrasenya);

		JLabel lblNickname = new JLabel("Marca: ");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 11));
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
					logger.error("Todos los campos deben de estar correctamente rellenados.", iteration++);
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
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(53, 135, 122, 16);
		contentPane.add(lblMatricula);

		textField = new JTextField();
		textField.setBounds(231, 130, 228, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNombreComprador = new JLabel("Nombre Comprador:");
		lblNombreComprador.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		if (comercialController.registrarVenta(venta)) {
			logger.info("Venta registrada correctamente.");
		} else {
			logger.error("La venta no se registro correctamente.");
		}
	}
} 
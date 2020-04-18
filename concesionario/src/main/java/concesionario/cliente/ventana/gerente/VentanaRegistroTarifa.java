package concesionario.cliente.ventana.gerente;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.datos.Mecanico;
import concesionario.datos.Tarifa;


public class VentanaRegistroTarifa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecioAprox;
	private JTextField textFieldHorasManoObra;
	private Controller loginController;

	public VentanaRegistroTarifa(Controller loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaRegistroTarifa(nickname);
	}
	
	public void iniciarVentanaRegistroTarifa(String nickname) {
		setResizable(false);
		setTitle("Registrar Nueva Tarifa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 395);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfo = new JLabel("Rellene los siguientes datos para registrar una nueva tarifa:");
		lblInfo.setBounds(22, 20, 426, 16);
		contentPane.add(lblInfo);
		
		JLabel lblID = new JLabel("ID: ");
		lblID.setBounds(53, 61, 150, 16);
		contentPane.add(lblID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(231, 56, 228, 26);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre Tarifa: ");
		lblNombre.setBounds(53, 99, 150, 16);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(231, 93, 228, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblPrecioAprox = new JLabel("Estimacion Precio: ");
		lblPrecioAprox.setBounds(53, 137, 150, 16);
		contentPane.add(lblPrecioAprox);
		
		textFieldPrecioAprox = new JTextField();
		textFieldPrecioAprox.setBounds(231, 132, 228, 26);
		contentPane.add(textFieldPrecioAprox);
		textFieldPrecioAprox.setColumns(10);
		
		JLabel lblHorasManoObra = new JLabel("Estimacion en horas Mano de Obra: ");
		lblHorasManoObra.setBounds(53, 175, 150, 16);
		contentPane.add(lblHorasManoObra);
		
		textFieldHorasManoObra = new JTextField();
		textFieldHorasManoObra.setBounds(231, 170, 228, 26);
		contentPane.add(textFieldHorasManoObra);
		textFieldHorasManoObra.setColumns(10);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionTarifas ventana = new VentanaGestionTarifas(loginController, nickname);
				ventana.setVisible(true);
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(109, 238, 117, 29);
		contentPane.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datos = comprobarDatos();
				if (datos) {
					String idTarifa = textFieldID.getText();
					String nombre = textFieldNombre.getText();
					int precioAprox = Integer.parseInt(textFieldPrecioAprox.getText());
					int horas_manodeobra = Integer.parseInt(textFieldHorasManoObra.getText());
					
					Tarifa tarifa = new Tarifa(idTarifa, nombre, precioAprox, horas_manodeobra);
					registrarTarifa(tarifa, nickname);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar rellenados.");
				}
			}
		});
		btnRegistrar.setBounds(277, 238, 117, 29);
		contentPane.add(btnRegistrar);
	}
	
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldID.getText().isEmpty() && !textFieldNombre.getText().isEmpty() && !textFieldPrecioAprox.getText().isEmpty() && !textFieldHorasManoObra.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
	
	
	public void vaciarCampos() {
		textFieldID.setText("");
		textFieldNombre.setText("");
		textFieldPrecioAprox.setText("");
		textFieldHorasManoObra.setText("");
	}
	
	public void registrarTarifa(Tarifa tarifa, String nickname){
		Response response = loginController.registroTarifa(tarifa);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			int respuesta = JOptionPane.showConfirmDialog(this, "Tarifa Registrada ¿Desea registrar otra tarifa?");
			switch (respuesta) {
			case 0:
				vaciarCampos();
				break;
			case 1:
				VentanaGestionTarifas ve = new VentanaGestionTarifas(loginController, nickname);
				ve.setVisible(true);
				dispose();
				break;
			case 2: 
				VentanaGestionTarifas ve2 = new VentanaGestionTarifas(loginController, nickname);
				ve2.setVisible(true);
				dispose();
				break;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Fallo a la hora de registrar.");
			dispose();
		}
	}
}

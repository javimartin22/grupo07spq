package concesionario.cliente.ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.CocheConcesionario;
import concesionario.servidor.datos.CocheTaller;
import concesionario.servidor.datos.Mecanico;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class VentanaRegistroCocheTaller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginController loginController; 
	private JTextField txtMatricula;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtCoste;
	private JTextField txtDni;

	public VentanaRegistroCocheTaller (LoginController loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		iniciarVentanaRegistrarCocheTaller(nickname);
	}
	
	private void iniciarVentanaRegistrarCocheTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(105, 290, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(37, 32, 171, 16);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(220, 27, 199, 26);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Nombre de la Marca:");
		lblMarca.setBounds(37, 70, 171, 16);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(220, 65, 199, 26);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Nombre del Modelo: ");
		lblModelo.setBounds(37, 111, 171, 16);
		contentPane.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(220, 106, 199, 26);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblCoste = new JLabel("Coste:");
		lblCoste.setBounds(37, 187, 148, 16);
		contentPane.add(lblCoste);
		
		txtCoste = new JTextField();
		txtCoste.setBounds(220, 182, 199, 26);
		contentPane.add(txtCoste);
		txtCoste.setColumns(10);
		
		
		JLabel lblDni = new JLabel("Dni cliente:");
		lblDni.setBounds(37, 263, 148, 16);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(220, 258, 199, 26);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					String matricula = txtMatricula.getText();
					String marca = txtMarca.getText();
					String modelo = txtModelo.getText();
					String mecanico = nickname;
					String dniCliente = txtDni.getText();
					double coste = Double.parseDouble(txtCoste.getText());
					int estado = 0;   //Estado de inicio de reparacion es el estado 0 
					
					CocheTaller cocheTaller = new CocheTaller(matricula, marca, modelo, mecanico, dniCliente, coste, estado);
					registrarCocheTaller(cocheTaller, nickname);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar completos");
				}
				
			}
		});
		btnNewButton.setBounds(259, 290, 117, 29);
		contentPane.add(btnNewButton);
		
	}
	
	
	public void registrarCocheTaller(CocheTaller cocheTaller, String nickname) {
		Response response = loginController.registrarCocheTaller(cocheTaller);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(this, "Coche taller registrado");
			VentanaMenuMecanico vmc = new VentanaMenuMecanico(loginController, nickname);
			vmc.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "ERROR al regristar el coche taller");
		}
	}
	
	public boolean comprobarCampos() {
		boolean datos = false;
		if (!txtMatricula.getText().isEmpty() && !txtMarca.getText().isEmpty() && !txtModelo.getText().isEmpty() && !txtDni.getText().isEmpty() && !txtCoste.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
}

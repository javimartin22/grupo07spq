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

public class VentanaRegistrarCocheConcesionario extends JFrame {

	private JPanel contentPane;
	private LoginController loginController; 
	private JTextField modelo;
	private JTextField marca;
	private JTextField precio;

	public VentanaRegistrarCocheConcesionario (LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaRegistrarCocheConcesionario(nickname);
	}
	
	public void iniciarVentanaRegistrarCocheConcesionario(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = marca.getText();
				String mo = modelo.getText();
				int pr = Integer.parseInt(precio.getText());
				CocheConcesionario coche = new CocheConcesionario(ma, mo, pr);
				registrarCoche(coche, nickname);
			}
		});
		btnNewButton.setBounds(260, 229, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(104, 229, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNombreDelModelo = new JLabel("Nombre del Modelo:");
		lblNombreDelModelo.setBounds(37, 45, 171, 16);
		contentPane.add(lblNombreDelModelo);
		
		JLabel lblNewLabel = new JLabel("Nombre de la Marca:");
		lblNewLabel.setBounds(37, 94, 171, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio: ");
		lblNewLabel_1.setBounds(37, 147, 171, 16);
		contentPane.add(lblNewLabel_1);
		
		modelo = new JTextField();
		modelo.setBounds(220, 40, 199, 26);
		contentPane.add(modelo);
		modelo.setColumns(10);
		
		marca = new JTextField();
		marca.setBounds(220, 89, 199, 26);
		contentPane.add(marca);
		marca.setColumns(10);
		
		precio = new JTextField();
		precio.setBounds(220, 142, 199, 26);
		contentPane.add(precio);
		precio.setColumns(10);
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

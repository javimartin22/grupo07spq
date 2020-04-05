package concesionario.cliente.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Comercial;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaInformacionComercial extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginController loginController;
	
	
	public VentanaInformacionComercial(LoginController loginController, Comercial comercial, String nickname) {
		this.loginController = loginController;
		initVentanaInformacionComercial(comercial, nickname);
	}
	/**
	 * Create the frame.
	 */
	private void initVentanaInformacionComercial(Comercial comercial, String nickname) {
		setTitle("Informacion Comercial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(28, 23, 129, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(28, 60, 129, 16);
		contentPane.add(lblApellido);
		
		JLabel lblNickname = new JLabel("Nickname: ");
		lblNickname.setBounds(28, 98, 129, 16);
		contentPane.add(lblNickname);
		
		JLabel lblDni = new JLabel("DNI:  ");
		lblDni.setBounds(28, 137, 129, 16);
		contentPane.add(lblDni);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 175, 310, 16);
		contentPane.add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 210, 129, 16);
		contentPane.add(lblEmail);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(28, 248, 129, 16);
		contentPane.add(lblCiudad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(28, 287, 129, 16);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 325, 129, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(28, 365, 129, 16);
		contentPane.add(lblNTelefono);
		
		JLabel lblNss = new JLabel("NSS:");
		lblNss.setBounds(28, 404, 129, 16);
		contentPane.add(lblNss);
		
		JLabel lblNCuenta = new JLabel("Nº Cuenta: ");
		lblNCuenta.setBounds(28, 441, 129, 16);
		contentPane.add(lblNCuenta);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(28, 477, 129, 16);
		contentPane.add(lblSueldo);
		
		JLabel lblCoches_Vendidos = new JLabel("Coches vendidos:");
		lblCoches_Vendidos.setBounds(28, 514, 129, 16);
		contentPane.add(lblCoches_Vendidos);
		
		JLabel lblImporte_Obtenido = new JLabel("Importe obtenido");
		lblImporte_Obtenido.setBounds(28, 551, 129, 16);
		contentPane.add(lblImporte_Obtenido);
		
		JLabel lblHoras = new JLabel("Horas trabajadas");
		lblHoras.setBounds(28, 588, 129, 16);
		contentPane.add(lblHoras);
		
		JLabel lblNewLabel = new JLabel(comercial.getNombre());
		lblNewLabel.setBounds(199, 23, 212, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(comercial.getApellido());
		lblNewLabel_1.setBounds(199, 60, 212, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(comercial.getNickname());
		lblNewLabel_1_1.setBounds(199, 98, 212, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel(comercial.getDNI());
		lblNewLabel_1_2.setBounds(199, 137, 212, 16);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel(comercial.getSexo());
		lblNewLabel_1_3.setBounds(199, 175, 212, 16);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel(comercial.getEmail());
		lblNewLabel_1_4.setBounds(199, 210, 212, 16);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel(comercial.getCiudad());
		lblNewLabel_1_5.setBounds(199, 248, 212, 16);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel(""+comercial.getCodigoPostal());
		lblNewLabel_1_6.setBounds(199, 287, 212, 16);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel(comercial.getDireccion());
		lblNewLabel_1_7.setBounds(199, 325, 212, 16);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel(comercial.getNumeroTelefono());
		lblNewLabel_1_8.setBounds(199, 365, 212, 16);
		contentPane.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel(comercial.getNSS());
		lblNewLabel_1_9.setBounds(199, 404, 212, 16);
		contentPane.add(lblNewLabel_1_9);
		
		JLabel lblNewLabel_1_10 = new JLabel(comercial.getNumeroCuenta());
		lblNewLabel_1_10.setBounds(199, 441, 212, 16);
		contentPane.add(lblNewLabel_1_10);
		
		JLabel lblNewLabel_1_11 = new JLabel("" + comercial.getSueldo());
		lblNewLabel_1_11.setBounds(199, 477, 212, 16);
		contentPane.add(lblNewLabel_1_11);
		
		JLabel lblNewLabel_1_12 = new JLabel("" + comercial.getCochesVendidos());
		lblNewLabel_1_12.setBounds(199, 514, 212, 16);
		contentPane.add(lblNewLabel_1_12);
		
		JLabel lblNewLabel_1_13 = new JLabel("" + comercial.getImporteObetenido());
		lblNewLabel_1_13.setBounds(199, 551, 212, 16);
		contentPane.add(lblNewLabel_1_13);
		
		JLabel lblNewLabel_1_14 = new JLabel("" + comercial.getHoras());
		lblNewLabel_1_14.setBounds(199, 588, 212, 16);
		contentPane.add(lblNewLabel_1_14);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(155, 623, 117, 29);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEmpleados vempl = new VentanaEmpleados(loginController, nickname);
				vempl.setVisible(true);
				dispose();
			}
		});
		

	}
}

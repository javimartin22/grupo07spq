package concesionario.cliente.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaInformacionMecanico extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInformacionMecanico frame = new VentanaInformacionMecanico();
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
	public VentanaInformacionMecanico() {
		setTitle("Informacion Mecanico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(28, 23, 310, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(28, 60, 310, 16);
		contentPane.add(lblApellido);
		
		JLabel lblNickname = new JLabel("Nickname: ");
		lblNickname.setBounds(28, 98, 310, 16);
		contentPane.add(lblNickname);
		
		JLabel lblSueldo = new JLabel("DNI:  ");
		lblSueldo.setBounds(28, 137, 310, 16);
		contentPane.add(lblSueldo);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 175, 310, 16);
		contentPane.add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 210, 310, 16);
		contentPane.add(lblEmail);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(28, 248, 310, 16);
		contentPane.add(lblCiudad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(28, 287, 310, 16);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 325, 310, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(28, 365, 310, 16);
		contentPane.add(lblNTelefono);
		
		JLabel lblNss = new JLabel("NSS:");
		lblNss.setBounds(28, 404, 310, 16);
		contentPane.add(lblNss);
		
		JLabel lblNCuenta = new JLabel("Nº Cuenta: ");
		lblNCuenta.setBounds(28, 441, 310, 16);
		contentPane.add(lblNCuenta);
		
		JLabel lblSueldo_1 = new JLabel("Sueldo:");
		lblSueldo_1.setBounds(28, 477, 310, 16);
		contentPane.add(lblSueldo_1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(136, 505, 117, 29);
		contentPane.add(btnSalir);
	}
}

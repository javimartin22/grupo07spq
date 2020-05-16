package concesionario.cliente.ventana.gerente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Mecanico;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * VentanaInformacionMecanico (Ventana para la visualizacion de la informacion del Mecanico).
 */
public class VentanaInformacionMecanico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblApellido;
	private JLabel lblNickname;
	private JLabel lblSueldo;
	private JLabel lblSexo;
	private JLabel lblEmail;
	private JLabel lblCiudad;
	private JLabel lblCodigoPostal;
	private JLabel lblDireccion;
	private JLabel lblNTelefono;
	private JLabel lblNss;
	private JLabel lblNCuenta;
	private JLabel lblSueldo_1;
	private JButton btnSalir;
	private GerenteController gerenteController;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	
	/**
	 * Constructor de la VentanaInformacionMecanico.
	 * @param gerenteController (Controlador de las Ventanas de Gerente)
	 * @param nickname (Nickname del Gerente)
	 * @param mecanic (Objetco Mecanico)
	 */
	public VentanaInformacionMecanico(GerenteController gerenteController, String nickname, Mecanico mecanic) {
	this.gerenteController = gerenteController;
	iniciarVentanaInformacionMecanico(nickname, mecanic);
	}
	/**
	 * Inicializador del JFrame de la VentanaInformacionMecanico
	 * @param nickname (Nickname del Mecanico)
	 * @param mecanic (Objeto tipo Mecanico)
	 */
	public void iniciarVentanaInformacionMecanico(String nickname, Mecanico mecanic) {
		setTitle("Informacion Mecanico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 586);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nombre: "  );
		lblNewLabel.setBounds(28, 23, 110, 16);
		contentPane.add(lblNewLabel);
		
		 lblApellido = new JLabel("Apellido:" + mecanic.getApellido());
		lblApellido.setBounds(28, 60, 110, 16);
		contentPane.add(lblApellido);
		
		lblNickname = new JLabel("Nickname:" + mecanic.getNickname());
		lblNickname.setBounds(28, 98, 110, 16);
		contentPane.add(lblNickname);
		
		lblSueldo = new JLabel("DNI:" + mecanic.getDNI());
		lblSueldo.setBounds(28, 137, 110, 16);
		contentPane.add(lblSueldo);
		
		lblSexo = new JLabel("Sexo:"  );
		lblSexo.setBounds(28, 175, 110, 16);
		contentPane.add(lblSexo);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 210, 110, 16);
		contentPane.add(lblEmail);
		
		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(28, 248, 110, 16);
		contentPane.add(lblCiudad);
		
		lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(28, 287, 110, 16);
		contentPane.add(lblCodigoPostal);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 325, 110, 16);
		contentPane.add(lblDireccion);
		
		lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(28, 365, 110, 16);
		contentPane.add(lblNTelefono);
		
		lblNss = new JLabel("NSS:");
		lblNss.setBounds(28, 404, 110, 16);
		contentPane.add(lblNss);
		
		lblNCuenta = new JLabel("Nº Cuenta: ");
		lblNCuenta.setBounds(28, 441, 110, 16);
		contentPane.add(lblNCuenta);
		
		lblSueldo_1 = new JLabel("Sueldo:");
		lblSueldo_1.setBounds(28, 477, 110, 16);
		contentPane.add(lblSueldo_1);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			VentanaEmpleados ve = new VentanaEmpleados(gerenteController, nickname);
			ve.setVisible(true);
			dispose();
			}
		});
		btnSalir.setBounds(136, 505, 117, 29);
		contentPane.add(btnSalir);
		
		lblNewLabel_1 = new JLabel(mecanic.getNombre());
		lblNewLabel_1.setBounds(176, 23, 183, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(mecanic.getApellido());
		lblNewLabel_2.setBounds(176, 60, 183, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(mecanic.getNickname());
		lblNewLabel_3.setBounds(176, 98, 183, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(mecanic.getDNI());
		lblNewLabel_4.setBounds(176, 137, 183, 16);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel(mecanic.getSexo());
		lblNewLabel_5.setBounds(176, 175, 183, 16);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel(mecanic.getEmail());
		lblNewLabel_6.setBounds(176, 210, 183, 16);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel(mecanic.getCiudad());
		lblNewLabel_7.setBounds(176, 248, 183, 16);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel(mecanic.getDireccion());
		lblNewLabel_8.setBounds(176, 287, 183, 16);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("" + mecanic.getCodigoPostal());
		lblNewLabel_9.setBounds(176, 325, 183, 16);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel(mecanic.getNumeroTelefono());
		lblNewLabel_10.setBounds(176, 365, 183, 16);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel(mecanic.getNSS());
		lblNewLabel_11.setBounds(176, 404, 183, 16);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("" + mecanic.getNumeroCuenta());
		lblNewLabel_12.setBounds(176, 441, 183, 16);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("" + mecanic.getSueldo());
		lblNewLabel_13.setBounds(176, 477, 183, 16);
		contentPane.add(lblNewLabel_13);
	}
}

package concesionario.cliente.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.DepartamentoCompras;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaInformacionDepartamentoCompras extends JFrame {

	private JPanel contentPane;
	private LoginController controller;
	private DepartamentoCompras depcompras;
	
	public void VentanaInformacionDepartamentoCompras(LoginController loginController, DepartamentoCompras depcompras) {
		this.controller = loginController;
		this.depcompras = depcompras;
		initVentanaInformacionDepartamentoCompras();
	}
	/**
	 * Create the frame.
	 */
	private void initVentanaInformacionDepartamentoCompras() {
		setTitle("Informacion Departamento Compras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(28, 23, 310, 16);
		contentPane.add(lblNombre);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombre.setBounds(350, 23, 150, 20);
		contentPane.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(28, 60, 310, 16);
		contentPane.add(lblApellido);
		
		JTextField txtApellido= new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtApellido.setBounds(350, 60, 150, 20);
		contentPane.add(txtApellido);
		
		JLabel lblNickname = new JLabel("Nickname: ");
		lblNickname.setBounds(28, 98, 310, 16);
		contentPane.add(lblNickname);
		
		JTextField txtNickname= new JTextField();
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNickname.setBounds(350, 98, 150, 20);
		contentPane.add(txtNickname);
		
		JLabel lblDni = new JLabel("DNI:  ");
		lblDni.setBounds(28, 137, 310, 16);
		contentPane.add(lblDni);
		
		JTextField txtDni= new JTextField();
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDni.setBounds(350, 137, 150, 20);
		contentPane.add(txtDni);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 175, 310, 16);
		contentPane.add(lblSexo);
		
		JTextField txtSexo= new JTextField();
		txtSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSexo.setBounds(350, 175, 150, 20);
		contentPane.add(txtSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 210, 310, 16);
		contentPane.add(lblEmail);
		
		JTextField txtEmail= new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(350, 210, 150, 20);
		contentPane.add(txtEmail);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(28, 248, 310, 16);
		contentPane.add(lblCiudad);
		
		JTextField txtCiudad= new JTextField();
		txtCiudad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCiudad.setBounds(350, 248, 150, 20);
		contentPane.add(txtCiudad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(28, 287, 310, 16);
		contentPane.add(lblCodigoPostal);
		
		JTextField txtCodigoPostal= new JTextField();
		txtCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodigoPostal.setBounds(350, 287, 150, 20);
		contentPane.add(txtCodigoPostal);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 325, 310, 16);
		contentPane.add(lblDireccion);
		
		JTextField txtDireccion= new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDireccion.setBounds(350, 325, 150, 20);
		contentPane.add(txtDireccion);
		
		JLabel lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(28, 365, 310, 16);
		contentPane.add(lblNTelefono);
		
		JTextField txtTelefono= new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTelefono.setBounds(350, 365, 150, 20);
		contentPane.add(txtTelefono);
		
		JLabel lblNss = new JLabel("NSS:");
		lblNss.setBounds(28, 404, 310, 16);
		contentPane.add(lblNss);
		
		JTextField txtNss= new JTextField();
		txtNss.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNss.setBounds(350, 404, 150, 20);
		contentPane.add(txtNss);
		
		JLabel lblNCuenta = new JLabel("Nº Cuenta: ");
		lblNCuenta.setBounds(28, 441, 310, 16);
		contentPane.add(lblNCuenta);
		
		JTextField txtNcuenta= new JTextField();
		txtNcuenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNcuenta.setBounds(350, 441, 150, 20);
		contentPane.add(txtNcuenta);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(28, 477, 310, 16);
		contentPane.add(lblSueldo);
		
		JTextField txtSueldo= new JTextField();
		txtSueldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSueldo.setBounds(350, 477, 150, 20);
		contentPane.add(txtSueldo);
		
		JLabel lblPedidos = new JLabel("Pedidos:");
		lblPedidos.setBounds(28, 514, 310, 16);
		contentPane.add(lblPedidos);
		
		JTextField txtPedidos= new JTextField();
		txtPedidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPedidos.setBounds(350, 514, 150, 20);
		contentPane.add(txtPedidos);
		
	
		//Añadir valores
		txtNombre.setText(depcompras.getNombre());
		txtApellido.setText(depcompras.getApellido());
		txtNickname.setText(depcompras.getNickname());
		txtDni.setText(depcompras.getDNI());
		txtSexo.setText(depcompras.getSexo());
		txtEmail.setText(depcompras.getEmail());
		txtCiudad.setText(depcompras.getCiudad());
		txtCodigoPostal.setText(Integer.toString(depcompras.getCodigoPostal()));
		txtDireccion.setText(depcompras.getDireccion());
		txtTelefono.setText(depcompras.getNumeroTelefono());
		txtNss.setText(depcompras.getNSS());
		txtNcuenta.setText(depcompras.getNumeroCuenta());
		txtSueldo.setText(Integer.toString(depcompras.getSueldo()));
		txtPedidos.setText(Integer.toString(depcompras.getPedidos()));
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(136, 505, 117, 29);
		contentPane.add(btnSalir);
		
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//VentanaEmpleados vempl = new VentanaEmpleados(loginController, nickname)
				//vempl.setVisible(true);
				//dispose();
			}
		});
		

	}
}

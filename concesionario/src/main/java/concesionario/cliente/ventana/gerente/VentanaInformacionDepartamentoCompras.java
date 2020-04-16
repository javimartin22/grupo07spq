package concesionario.cliente.ventana.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.Controller;
import concesionario.datos.DepartamentoCompras;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaInformacionDepartamentoCompras extends JFrame {
	
	private static final long serialVersionUID = 1L;
 
	private JPanel contentPane;
	private Controller loginController;
	
	public VentanaInformacionDepartamentoCompras(Controller loginController, DepartamentoCompras departamentoCompras, String nickname) {
		this.loginController = loginController;
		initVentanaInformacionDepartamentoCompras(departamentoCompras, nickname);
	}
	
	private void initVentanaInformacionDepartamentoCompras(DepartamentoCompras departamentoCompras, String nickname) {
		setTitle("Informacion Departamento Compras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 624);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(28, 23, 104, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(28, 60, 104, 16);
		contentPane.add(lblApellido);
		
		JLabel lblNickname = new JLabel("Nickname: ");
		lblNickname.setBounds(28, 98, 104, 16);
		contentPane.add(lblNickname);
		
		JLabel lblDni = new JLabel("DNI:  ");
		lblDni.setBounds(28, 137, 104, 16);
		contentPane.add(lblDni);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 175, 104, 16);
		contentPane.add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(28, 210, 104, 16);
		contentPane.add(lblEmail);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(28, 248, 104, 16);
		contentPane.add(lblCiudad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(28, 287, 104, 16);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 325, 104, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblNTelefono = new JLabel("Nº Telefono:");
		lblNTelefono.setBounds(28, 365, 104, 16);
		contentPane.add(lblNTelefono);
		
		JLabel lblNss = new JLabel("NSS:");
		lblNss.setBounds(28, 404, 104, 16);
		contentPane.add(lblNss);
		
		JLabel lblNCuenta = new JLabel("Nº Cuenta: ");
		lblNCuenta.setBounds(28, 441, 104, 16);
		contentPane.add(lblNCuenta);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(28, 477, 104, 16);
		contentPane.add(lblSueldo);
		
		JLabel lblPedidos = new JLabel("Pedidos:");
		lblPedidos.setBounds(28, 514, 104, 16);
		contentPane.add(lblPedidos);
		
		JLabel lblNewLabel = new JLabel(departamentoCompras.getNombre());
		lblNewLabel.setBounds(177, 23, 219, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(departamentoCompras.getApellido());
		lblNewLabel_1.setBounds(177, 60, 219, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(departamentoCompras.getNickname());
		lblNewLabel_2.setBounds(177, 98, 219, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(departamentoCompras.getDNI());
		lblNewLabel_3.setBounds(177, 137, 219, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(departamentoCompras.getSexo());
		lblNewLabel_4.setBounds(177, 175, 219, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(departamentoCompras.getEmail());
		lblNewLabel_5.setBounds(177, 210, 219, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(departamentoCompras.getCiudad());
		lblNewLabel_6.setBounds(177, 248, 219, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("" + departamentoCompras.getCodigoPostal());
		lblNewLabel_7.setBounds(177, 287, 219, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(departamentoCompras.getDireccion());
		lblNewLabel_8.setBounds(177, 325, 219, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(departamentoCompras.getNumeroTelefono());
		lblNewLabel_9.setBounds(177, 365, 219, 16);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(departamentoCompras.getNSS());
		lblNewLabel_10.setBounds(177, 404, 219, 16);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(departamentoCompras.getNumeroCuenta());
		lblNewLabel_11.setBounds(177, 441, 219, 16);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("" + departamentoCompras.getSueldo());
		lblNewLabel_12.setBounds(177, 477, 219, 16);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("" + departamentoCompras.getPedidos());
		lblNewLabel_13.setBounds(177, 514, 219, 16);
		contentPane.add(lblNewLabel_13);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(153, 552, 117, 29);
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

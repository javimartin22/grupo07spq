package concesionario.cliente.ventana.departamentoCompras;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.ventana.departamentoCompras.VentanaComprarHerramientas;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class VentanaMenuDepartamentoCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DepartmentoComprasController departmentoComprasController;
	
	public VentanaMenuDepartamentoCompras(DepartmentoComprasController departamentoComprasController, String nickname) {
		setTitle("Menu departamento compras");
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaMenuDepartamentoCompras(nickname);
	}

	
	public void iniciarVentanaMenuDepartamentoCompras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 264);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombre.setBounds(178, 11, 250, 33);
		contentPane.add(nombre);
		
		JButton btnNewButton = new JButton("Piezas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasUtilizadas vpu = new VentanaPiezasUtilizadas(departmentoComprasController, nickname);
				vpu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(158, 87, 132, 29);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController controller = new LoginController(departmentoComprasController.getClienteApp());
				VentanaLogin vl = new VentanaLogin(controller);
				vl.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(173, 207, 117, 29);
		contentPane.add(btnSalir);
		
		JButton btnComprar = new JButton("Comprar Piezas");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComprarPiezas vcp = new VentanaComprarPiezas(departmentoComprasController, nickname);
				vcp.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBounds(158, 148, 132, 29);
		contentPane.add(btnComprar);
		
		JButton btnComprarHerramientas = new JButton("Comprar herramientas");
		btnComprarHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComprarHerramientas vch = new VentanaComprarHerramientas(departmentoComprasController, nickname);
				vch.setVisible(true);
				dispose();
			}
		});
		btnComprarHerramientas.setBounds(144, 179, 177, 29);
		contentPane.add(btnComprarHerramientas);
		
		JLabel lblDepartamentoDeCompras = new JLabel("DEPARTAMENTO DE COMPRAS");
		lblDepartamentoDeCompras.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblDepartamentoDeCompras.setBounds(63, 59, 349, 16);
		contentPane.add(lblDepartamentoDeCompras);
		
		JButton btnHerramientas = new JButton("Herramientas");
		btnHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHerramientasTaller vht = new VentanaHerramientasTaller(departmentoComprasController, nickname);
				vht.setVisible(true);
				dispose();
			}
		});
		btnHerramientas.setBounds(158, 116, 132, 29);
		contentPane.add(btnHerramientas);
	}
}

package concesionario.cliente.ventana.departamentoCompras;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * VentanaMenuDepartamentoCompras (Ventana de Menu de opciones para el Departamento de Compras).
 */
public class VentanaMenuDepartamentoCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DepartmentoComprasController departmentoComprasController;
	
	/**
	 * Constructor de la VentanaMenuDepartamentoCompras
	 * @param departamentoComprasController (Controlador de las ventanas para la clase DepartamentoCompras)
	 * @param nickname (Nickname del departamento de compras)
	 */
	public VentanaMenuDepartamentoCompras(DepartmentoComprasController departamentoComprasController, String nickname) {
		setResizable(false);
		setTitle("Menu Departamento Compras");
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaMenuDepartamentoCompras(nickname);
	}

	/**
	 * Inicializador del JFrame de la VentanaMenuDepartamentoCompras
	 * @param nickname (Nickname del departamento de compras)
	 */
	public void iniciarVentanaMenuDepartamentoCompras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 288);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombre.setBounds(223, 8, 250, 33);
		contentPane.add(nombre);
		
		JButton btnNewButton = new JButton("Piezas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasUtilizadas vpu = new VentanaPiezasUtilizadas(departmentoComprasController, nickname);
				vpu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(24, 52, 181, 33);
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
		btnSalir.setBounds(175, 207, 117, 29);
		contentPane.add(btnSalir);
		
		JButton btnComprar = new JButton("Comprar Piezas");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComprarPiezas vcp = new VentanaComprarPiezas(departmentoComprasController, nickname);
				vcp.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBounds(24, 109, 181, 33);
		contentPane.add(btnComprar);
		
		JButton btnComprarHerramientas = new JButton("Comprar Herramientas");
		btnComprarHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComprarHerramientas vch = new VentanaComprarHerramientas(departmentoComprasController, nickname);
				vch.setVisible(true);
				dispose();
			}
		});
		btnComprarHerramientas.setBounds(254, 110, 181, 31);
		contentPane.add(btnComprarHerramientas);
		
		JButton btnHerramientas = new JButton("Herramientas");
		btnHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHerramientasTaller vht = new VentanaHerramientasTaller(departmentoComprasController, nickname);
				vht.setVisible(true);
				dispose();
			}
		});
		btnHerramientas.setBounds(254, 52, 181, 33);
		contentPane.add(btnHerramientas);
		
		JButton btnComprar_1 = new JButton("Solicitud Compras");
		btnComprar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarSolicitudCompra vvsc = new VentanaVisualizarSolicitudCompra(departmentoComprasController, nickname);
				vvsc.setVisible(true);
				dispose();
			}
		});
		btnComprar_1.setBounds(142, 163, 181, 33);
		contentPane.add(btnComprar_1);
	}
}

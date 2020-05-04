package concesionario.cliente.ventana.departamentoCompras;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuDepartamentoCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DepartmentoComprasController departmentoComprasController;
	
	public VentanaMenuDepartamentoCompras(DepartmentoComprasController departamentoComprasController, String nickname) {
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaMenuDepartamentoCompras(nickname);
	}

	
	public void iniciarVentanaMenuDepartamentoCompras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Piezas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasUtilizadas vpu = new VentanaPiezasUtilizadas(departmentoComprasController, nickname);
				vpu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(158, 75, 132, 29);
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
		btnSalir.setBounds(168, 146, 117, 29);
		contentPane.add(btnSalir);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComprarPiezas vcp = new VentanaComprarPiezas(departmentoComprasController, nickname);
				vcp.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBounds(168, 105, 117, 29);
		contentPane.add(btnComprar);
	}
}

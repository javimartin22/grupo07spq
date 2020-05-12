package concesionario.cliente.ventana.comercial;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import concesionario.cliente.controller.ComercialController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.Comercial;
import concesionario.datos.HorasEmpleados;

public class VentanaMenuComercial extends JFrame {

	private static final long serialVersionUID = 1L;
	private ComercialController comercialController;
	private JButton buttonRegistrarCoche;
	
	public VentanaMenuComercial(ComercialController comercialController, String nickname) {
		this.comercialController = comercialController;
		initVentanaMenuComercial(nickname);
	}
	
	private void initVentanaMenuComercial(String nickname) {
		this.setTitle("Menu Comercial");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(385,276);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//panel que contiene todo
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
		JLabel nombreMecanico = new JLabel("Bienvenid@ " + nickname);
		nombreMecanico.setHorizontalAlignment(SwingConstants.CENTER);
		nombreMecanico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreMecanico.setBounds(146, 11, 228, 33);
		panel.add(nombreMecanico);
		
		buttonRegistrarCoche = new JButton("Registrar coche");
		buttonRegistrarCoche.setBounds(28, 55, 141, 33);
		panel.add(buttonRegistrarCoche);
		
		
		JButton btnVerVentas = new JButton("Ver Ventas");
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarVentas vvv = new VentanaVisualizarVentas(comercialController, nickname);
				vvv.setVisible(true);
				dispose();
			}
		});
		btnVerVentas.setBounds(210, 55, 141, 33);
		panel.add(btnVerVentas);
		
		JButton btnNewButton = new JButton("Ver Coches");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCochesConcesionario vcc = new VentanaCochesConcesionario(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(28, 106, 141, 33);
		panel.add(btnNewButton);
		 
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController controller = new LoginController(comercialController.getClienteApp());
				VentanaLogin vl = new VentanaLogin(controller);
				vl.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(146, 209, 93, 23);
		panel.add(btnSalir);
		
		JButton btnNewButton_1 = new JButton("Visualizar citas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarCitas vcc = new VentanaVisualizarCitas(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(210, 106, 141, 33);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Entrada Trabajo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date fecha = new Date();
				int hora = fecha.getHours();
				int min = fecha.getMinutes();
				cargarHoras(hora, min, nickname);
			}
		});
		btnNewButton_2.setBounds(28, 157, 141, 33);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Salida Trabajo");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = new Date();
				int h = fecha.getHours();
				int m = fecha.getMinutes();
				calcularHorasTrabajadas(h, m, nickname);
			}
		});
		btnNewButton_2_1.setBounds(210, 157, 141, 33);
		panel.add(btnNewButton_2_1);
		buttonRegistrarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistrarCocheConcesionario vrcc = new VentanaRegistrarCocheConcesionario(comercialController, nickname);
				vrcc.setVisible(true);
				dispose();
			}
		}); 
	}
	
	public void cargarHoras(int hora, int min, String nickname) {
		comercialController.deleteHorasEmpleadosTemporal(nickname);
		String horasEmpleado = nickname + "-" + hora + "-" + min;
		if (comercialController.registrarHorasComercialTemporal(horasEmpleado)) {
			JOptionPane.showMessageDialog(this, hora + ":" + min);
		}
	}
	
	public void calcularHorasTrabajadas(int h, int m, String nickname) {
		HorasEmpleados horaEmpleadosTemporal = comercialController.seleccionarHorasComercialTemporal(nickname);
		HorasEmpleados horaEmpleados = comercialController.seleccionarHorasComercial(nickname);
		if (horaEmpleados == null) {
			int hora = h - horaEmpleadosTemporal.getHoras();
			int min = m - horaEmpleadosTemporal.getMinutos();
			String horasEmpleado = nickname + "-" + hora + "-" + min;
			comercialController.registrarHorasComercial(horasEmpleado);
		} else {
			int hora = h - horaEmpleadosTemporal.getHoras() + horaEmpleados.getHoras();
			int min = m - horaEmpleadosTemporal.getMinutos() + horaEmpleados.getMinutos();
			comercialController.deleteHorasEmpleados(nickname);
			if (hora > 0) {
				String horasEmpleado = nickname + "-" + 0 + "-" + min;
				if (comercialController.registrarHorasComercial(horasEmpleado)) {
					Comercial comercial = comercialController.seleccionarComercial(nickname);
					int horasTotales = comercial.getHoras() + hora;
					comercialController.deleteComercial(nickname);
					comercial.setHoras(horasTotales);
					comercialController.registroComercial(comercial);
					JOptionPane.showMessageDialog(this, 0 + ":" + min);
				}
			} else {
				String horasEmpleado = nickname + "-" + hora + "-" + min;
				if (comercialController.registrarHorasComercial(horasEmpleado)) {
					JOptionPane.showMessageDialog(this, hora + ":" + min);
				}
			}
		}
	}
}

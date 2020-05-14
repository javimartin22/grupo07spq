package concesionario.cliente.ventana.mecanico;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.ws.rs.DELETE;

import concesionario.cliente.controller.MecanicoController;
import concesionario.cliente.controller.LoginController;
import concesionario.cliente.ventana.VentanaLogin;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Mecanico;

import java.util.Date;

import javax.swing.JButton;


/**
 * Interfaz grafica VentanaMenuMecanico (Permite visualizar el menu de un mecanico)
 */
public class VentanaMenuMecanico extends JFrame {

	private static final long serialVersionUID = 1L;
	private MecanicoController mecanicoController;
	/**
	 * Constructor de la VentanaMenuMecanico
	 * @param loginController (Controlador de las ventanas para la clase Mecanico)
	 * @param nickname (Nickname del mecanico)
	 */
	public VentanaMenuMecanico(MecanicoController loginController, String nickname){
		this.mecanicoController = loginController;
		iniciarVentanaMenuMecanico(nickname);
	}
	
	/**
	 * Inicializador del JFrame de la VentanaMenuMecanico
	 * @param nickname (Nickname del mecanico)
	 */
	public void iniciarVentanaMenuMecanico(String nickname){
		this.setTitle("Menu del mecanico");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(499,411);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		 
		//panel donde van todos los componentes
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
		JLabel nombreMecanico = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombreMecanico.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreMecanico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreMecanico.setBounds(178, 11, 250, 33);
		panel.add(nombreMecanico);
		
		//boton para anadir piezas, si clicas te lleva a una nueva ventana donde puedes realizar esta actividad
		JButton anadirPieza = new JButton("Ver Piezas");
		anadirPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPiezasMecanico vp = new VentanaPiezasMecanico(mecanicoController, nickname);
				vp.setVisible(true);
				dispose();
			}
		});
		anadirPieza.setBounds(21, 76, 201, 33);
		panel.add(anadirPieza);
		
		//boton para poder ver el historial de los vehiculos que han pasado por el taller 
		JButton verHistoial = new JButton("Ver historial de los coches");
		verHistoial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hace falta instanciar la ventana donde se vera el hisorial
				VentanaCochesMatriculadosMecanico vcmm = new VentanaCochesMatriculadosMecanico(mecanicoController, nickname);
				vcmm.setVisible(true);
				dispose();
			}
		});
		verHistoial.setBounds(264, 179, 201, 33);
		panel.add(verHistoial);
		
		//boton para poder registrar los vehiculos que pasan por el taller
		JButton registarVehiculo = new JButton("Registrar vehiculo del taller");
		registarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroCocheTaller vrct = new VentanaRegistroCocheTaller(mecanicoController, nickname);
				vrct.setVisible(true);
				dispose();
			}
		});
		registarVehiculo.setBounds(21, 130, 201, 33);
		panel.add(registarVehiculo);
		
		//boton de salir que te lleva a la ventana de VentanaLogin donde se inicia la sesion
		JButton buttonSalir = new JButton("Salir");
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ventana para iniciar serion
				LoginController loginController = new LoginController(mecanicoController.getClienteApp());
				VentanaLogin vl = new VentanaLogin(loginController);
				vl.setVisible(true);///ee
				dispose();
			}
		});
		buttonSalir.setBounds(200, 333, 89, 23);
		panel.add(buttonSalir);
		
		JButton btnVerCochesTaller = new JButton("Ver Coches Taller");
		btnVerCochesTaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarCochesTaller vvct = new VentanaVisualizarCochesTaller(mecanicoController, nickname);
				vvct.setVisible(true);
				dispose();
			}
		});
		btnVerCochesTaller.setBounds(264, 130, 201, 33);
		panel.add(btnVerCochesTaller);
		
		JButton verHistoial_1 = new JButton("Visualizar Presupuesto");
		verHistoial_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarPresupuestos vvp = new VentanaVisualizarPresupuestos(mecanicoController, nickname);
				vvp.setVisible(true);
				dispose();
			}
		});
		verHistoial_1.setBounds(21, 179, 201, 33);
		panel.add(verHistoial_1);
		
		JButton verHistoial_1_1 = new JButton("Visualizar Fidelidad");
		verHistoial_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarFidelidad vvf = new VentanaVisualizarFidelidad(mecanicoController, nickname);
				vvf.setVisible(true);
				dispose();
			}
		});
		verHistoial_1_1.setBounds(21, 235, 201, 33);
		panel.add(verHistoial_1_1);
		
		JButton btnverHerramientas = new JButton("Ver Herramientas");
		btnverHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHerramientasMecanico vhm = new VentanaHerramientasMecanico(mecanicoController, nickname);
				vhm.setVisible(true);
				dispose();
				
			}
		});
		btnverHerramientas.setBounds(264, 76, 201, 33);
		panel.add(btnverHerramientas);
		
		JButton verHistoial_1_1_1 = new JButton("Visualizar Citas");
		verHistoial_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaVisualizarCitasMecanico vvcm = new VentanaVisualizarCitasMecanico(mecanicoController, nickname);
				vvcm.setVisible(true);
				dispose();
			}
		});
		verHistoial_1_1_1.setBounds(264, 235, 201, 33);
		panel.add(verHistoial_1_1_1);
		
		JButton verHistoial_1_1_2 = new JButton("Entrada Trabajo");
		verHistoial_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = new Date();
				int hora = fecha.getHours();
				int min = fecha.getMinutes();
				cargarHoras(hora, min, nickname);
			}
		});
		verHistoial_1_1_2.setBounds(21, 289, 201, 33);
		panel.add(verHistoial_1_1_2);
		
		JButton verHistoial_1_1_3 = new JButton("Salida Trabajo");
		verHistoial_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = new Date();
				int h = fecha.getHours();
				int m = fecha.getMinutes();
				calcularHorasTrabajadas(h, m, nickname);
			}
		});
		verHistoial_1_1_3.setBounds(264, 289, 201, 33);
		panel.add(verHistoial_1_1_3);
	}
	/**
	 * Registra las horas de un mecanico
	 * @param hora Horas
	 * @param min  Minutos
	 * @param nickname Nickname del mecanico
	 */
	public void cargarHoras(int hora, int min, String nickname) {
		mecanicoController.deleteHorasEmpleadosTemporal(nickname);
		String horasEmpleado = nickname + "-" + hora + "-" + min;
		if (mecanicoController.registrarHorasMecanicoTemporal(horasEmpleado)) {
			JOptionPane.showMessageDialog(this, hora + ":" + min);
		}
	}
	/**
	 * Calcula las horas trabajas de un mecanico
	 * @param h Horas
	 * @param m  Minutos
	 * @param nickname Nickname del mecanico
	 */
	public void calcularHorasTrabajadas(int h, int m, String nickname) {
		HorasEmpleados horaEmpleadosTemporal = mecanicoController.seleccionarHorasMecanicoTemporal(nickname);
		HorasEmpleados horaEmpleados = mecanicoController.seleccionarHorasMecanico(nickname);
		if (horaEmpleados == null) {
			int hora = h - horaEmpleadosTemporal.getHoras();
			int min = m - horaEmpleadosTemporal.getMinutos();
			String horasEmpleado = nickname + "-" + hora + "-" + min;
			mecanicoController.registrarHorasMecanico(horasEmpleado);
		} else {
			int hora = h - horaEmpleadosTemporal.getHoras() + horaEmpleados.getHoras();
			int min = m - horaEmpleadosTemporal.getMinutos() + horaEmpleados.getMinutos();
			mecanicoController.deleteHorasEmpleados(nickname);
			if (hora > 0) {
				String horasEmpleado = nickname + "-" + 0 + "-" + min;
				if (mecanicoController.registrarHorasMecanico(horasEmpleado)) {
					Mecanico mecanico = mecanicoController.seleccionarMecanico(nickname);
					int horasTotales = mecanico.getHoras() + hora;
					mecanicoController.deleteMecanico(nickname);
					mecanico.setHoras(horasTotales);
					mecanicoController.registroMecanico(mecanico);
					JOptionPane.showMessageDialog(this, 0 + ":" + min);
				}
			} else {
				String horasEmpleado = nickname + "-" + hora + "-" + min;
				if (mecanicoController.registrarHorasMecanico(horasEmpleado)) {
					JOptionPane.showMessageDialog(this, hora + ":" + min);
				}
			}
		}
	}
}

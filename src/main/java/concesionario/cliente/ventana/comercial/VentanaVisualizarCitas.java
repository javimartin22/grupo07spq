package concesionario.cliente.ventana.comercial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CitaComercial;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Clase para visualizar las citas de un usuario de tipo comercial.
 */
public class VentanaVisualizarCitas extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable table1;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarCitas.class);
	static int iteration = 0;
	
	/**
	 * Controlador para la clase ComercialController.
	 */
	private ComercialController comercialController;
	
	/**
	 * Constructor de la clase VentanaVisualizarCitas.
	 * @param comercialcontroller (Controlador de la ventana VentanaVisualizarCitas)
	 * @param nickname (nombre de usuario)
	 */
	public VentanaVisualizarCitas(ComercialController comercialcontroller,String nickname) {
		this.comercialController = comercialcontroller;
		iniciarVentanaVisualizarCitas(nickname);
	}
	
	/**
	 * Create the frame.
	 * @param nickname (Nombre de usuario del comercial que inicia sesión.)
	 */
	public void iniciarVentanaVisualizarCitas(String nickname) {
		setResizable(false);
		setTitle("Citas Concesionario");
		setBounds(100, 100, 992, 408);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		//scroller
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 18, 945, 274);
		getContentPane().add(scrollPane);
		
		//tabla
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		
		//menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Opcion de filtrar por fecha
		JMenu mnNewMenu = new JMenu("Filtrar por fecha");
		menuBar.add(mnNewMenu);
		
		//item del filtrar por fecha (fecha de hoy)
		JMenuItem mntmNewMenuItem = new JMenuItem("Citas hoy");
		mntmNewMenuItem.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate()) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";				
				cargarTablaFiltro(table1, nickname, fechaCompleta);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		//iten  del filtro por fecha (fecha de mañana)
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Citas mañana");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate() + 1) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";				
				cargarTablaFiltro(table1, nickname, fechaCompleta);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		//item del filtro por fecha (fecha metida a mano)
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Otro día");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String fecha;
				 fecha = JOptionPane.showInputDialog("¿Qué fecha quieres? (ejem. dd-mm-yyyy)");
				 
				 if (comercialController.validarFecha(fecha)) {
					 cargarTablaFiltro(table1, nickname, fecha);
				} else {
					fecha = JOptionPane.showInputDialog("Por favor, escribe la fecha en el siguiente formato (ejem. dd-mm-yyyy)");
				}	 
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		//boton para cargar la tabla
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate()-1) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";
				String nickFecha = nickname + ";" + fechaCompleta;
				comercialController.deleteCitaComercial(nickFecha);
				cargarTabla(table1, nickname);
			}
		});
		
		btnNewButton.setBounds(848, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		//boton para volver a la ventana VentanaMenuComercial
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController,  nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		
		btnVolve.setBounds(708, 303, 117, 29);
		getContentPane().add(btnVolve);
	}
	
	/**
	 * Metodo para cargar la tabla de citas.
	 * @param table
	 * @param comercial (nickname del comercial)
	 */
	private void cargarTabla(JTable table, String comercial) {
		List<CitaComercial> comerciales = comercialController.cargarCitaComercial(comercial);
		String[] columnNames = { "Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial"};

		if (!comercial.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);

			for (CitaComercial c : comerciales) {
				Object[] o = new Object[5];
				o[0] = c.getNombre();
				o[1] = c.getDniCliente();
				o[2] = c.getFecha();
				o[3] = c.getHora();
				o[4] = c.getComercial();
				model.addRow(o);
			} 
		} else {
			logger.error("No hay citas.");
		}
	}
	
	/**
	 * Metodo para cargar la tabla segun el filtro por fecha seleccionado en el menu.
	 * @param table
	 * @param comercial (nickname del comercial)
	 * @param fecha	(fecha para filtrar)
	 */
	public void cargarTablaFiltro(JTable table, String comercial, String fecha) {
		String filtro = comercial + ";" + fecha;
		List<CitaComercial> comerciales = comercialController.filtrarCitaComercial(filtro);
		String[] columnNames = { "Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial"};
		if (!fecha.isEmpty() && !comercial.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);

			for (CitaComercial c : comerciales) {
				Object[] o = new Object[5];
				o[0] = c.getNombre();
				o[1] = c.getDniCliente();
				o[2] = c.getFecha();
				o[3] = c.getHora();
				o[4] = c.getComercial();
				model.addRow(o);
			}
		} else {
			logger.error("No hay citas este día.");
		}
	} 
	
	
}

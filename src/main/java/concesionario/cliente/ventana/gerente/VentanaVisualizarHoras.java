package concesionario.cliente.ventana.gerente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.EmpleadoHoras;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * VentanaVisualizarHoras (Ventana para la visualizacion de las horas que realizan los empleados).
 */
public class VentanaVisualizarHoras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private GerenteController gerenteController;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarHoras.class);
	static int iteration = 0;

	/**
	 * Constructor de la Clase VentanaVisualizarHoras.
	 * @param gerenteController (Controlador de las ventanas de Gerente
	 * @param nickname (Nickname del Gerente)
	 */
	public VentanaVisualizarHoras(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;
		ventanaVisualizarHoras(nickname);
	}
	
	/**
	 * Create the frame.
	 * @param nickname (Nickname del Gerente)
	 */
	public void ventanaVisualizarHoras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 409);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Horas Max");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int horas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca las horas maximas que desea:"));
				cargarTablaHorasTodosFiltroHoras(table, 2, horas);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Horas Min");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int horas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca las horas minimas que desea:"));
				cargarTablaHorasTodosFiltroHoras(table, 1, horas);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 568, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		cargarTablaHorasTodos(table);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuAdmin vma = new VentanaMenuAdmin(gerenteController, nickname);
				vma.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(100, 318, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(326, 321, 117, 20);
		comboBox.addItem("Comercial");
		comboBox.addItem("Mecnico");
		contentPane.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("Cargar Tabla");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTablaHoras(table, comboBox.getSelectedIndex());
			}
		});
		btnNewButton_2.setBounds(453, 318, 125, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Ver Graficos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<EmpleadoHoras> empleados = gerenteController.cargarEmpleadoHoras(comboBox.getSelectedIndex());
				VentanaGraficosHoras vgh = new VentanaGraficosHoras("","", empleados, gerenteController, nickname);
				vgh.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(199, 318, 117, 23);
		contentPane.add(btnNewButton_1);
	}
	
	/**
	 * Metodo para cargar las horas filtrado por los tipos de empleados.
	 * @param table (Tabla de la ventana)
	 * @param tipo (Tipo de empleado)
	 */
	public void cargarTablaHoras(JTable table, int tipo) {
		List<EmpleadoHoras> empleados = gerenteController.cargarEmpleadoHoras(tipo);
		
		String[] columnNames = {"Nickname", "Nombre", "DNI", "Horas"};
		
		if (!empleados.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (EmpleadoHoras e : empleados) {
				   Object[] o = new Object[7];
				   o[0] = e.getNickname();
				   o[1] = e.getNombre();
				   o[2] = e.getDni();
				   o[3] = e.getHoras();
				   model.addRow(o);
				 }
		} else {
			logger.error("No hay ningun empleado.");
		}
	}
	
	/**
	 * Metodo para cargar la tabla con todas las horas.
	 * @param table (Tabla de la ventana)
	 */
	public void cargarTablaHorasTodos(JTable table) {
		List<EmpleadoHoras> empleadosComercial = gerenteController.cargarEmpleadoHoras(0);
		
		String[] columnNames = {"Nickname", "Nombre", "DNI", "Horas", "Tipo Empleado"};
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(columnNames);
		if (!empleadosComercial.isEmpty()) {
			   for (EmpleadoHoras e : empleadosComercial) {
				   Object[] o = new Object[7];
				   o[0] = e.getNickname();
				   o[1] = e.getNombre();
				   o[2] = e.getDni();
				   o[3] = e.getHoras();
				   o[4] = "Comercial";
				   model.addRow(o);
				 }
		} else {
			logger.error("No hay ningun empleado.");
		}
		
		List<EmpleadoHoras> empleadosMecanico = gerenteController.cargarEmpleadoHoras(1);
		
		if (!empleadosMecanico.isEmpty()) {
			   for (EmpleadoHoras e : empleadosMecanico) {
				   Object[] o = new Object[7];
				   o[0] = e.getNickname();
				   o[1] = e.getNombre();
				   o[2] = e.getDni();
				   o[3] = e.getHoras();
				   o[4] = "Mecanico";
				   model.addRow(o);
				 }
		} else {
			logger.error("No hay ningun empleado.");
		}
	}
	
	/**
	 * Metodo para cargar la tabla con los filtros indicados.
	 * @param table (Tabla de la ventana)
	 * @param tipo (Tipo de filtrado que se va a hacer)
	 * @param horas (Hora con la que se va a realizar el filtrado)
	 */
	public void cargarTablaHorasTodosFiltroHoras(JTable table, int tipo, int horas) {
		List<EmpleadoHoras> empleadosComercial = gerenteController.cargarEmpleadoHoras(0);
		
		String[] columnNames = {"Nickname", "Nombre", "DNI", "Horas", "Tipo Empleado"};
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(columnNames);
		if (!empleadosComercial.isEmpty()) {
			   for (EmpleadoHoras e : empleadosComercial) {
				   switch (tipo) {
				   		case 1:
				   			if (e.getHoras() >= horas) {
								Object[] o = new Object[7];
								o[0] = e.getNickname();
								o[1] = e.getNombre();
								o[2] = e.getDni();
								o[3] = e.getHoras();
								o[4] = "Comercial";
								model.addRow(o);
				   				}
				   				break;
						case 2: 
							if (e.getHoras() <= horas) {
								Object[] o = new Object[7];
								o[0] = e.getNickname();
								o[1] = e.getNombre();
								o[2] = e.getDni();
								o[3] = e.getHoras();
								o[4] = "Comercial";
								model.addRow(o);
							}
							break;
					default:
						Object[] o = new Object[7];
						o[0] = e.getNickname();
						o[1] = e.getNombre();
						o[2] = e.getDni();
						o[3] = e.getHoras();
						o[4] = "Comercial";
						model.addRow(o);
						break;
					}
			   }
		} else {
			logger.error("No hay ningun empleado.");
		}
		
		List<EmpleadoHoras> empleadosMecanico = gerenteController.cargarEmpleadoHoras(1);
		
		if (!empleadosMecanico.isEmpty()) {
			for (EmpleadoHoras e : empleadosMecanico) {
				switch (tipo) {
					case 1:
						if (e.getHoras() >= horas) {
							Object[] o = new Object[7];
							o[0] = e.getNickname();
							o[1] = e.getNombre();
							o[2] = e.getDni();
							o[3] = e.getHoras();
							o[4] = "Comercial";
							model.addRow(o);
						}
						break;
					case 2: 
						if (e.getHoras() <= horas) {
							Object[] o = new Object[7];
							o[0] = e.getNickname();
							o[1] = e.getNombre();
							o[2] = e.getDni();
							o[3] = e.getHoras();
							o[4] = "Comercial";
							model.addRow(o);
						}
						break;
					default:
						Object[] o = new Object[7];
						o[0] = e.getNickname();
						o[1] = e.getNombre();
						o[2] = e.getDni();
						o[3] = e.getHoras();
						o[4] = "Comercial";
						model.addRow(o);
						break;
					}
			}
		} else {
			logger.error("No hay ningun empleado.");
		}
	}
}

package concesionario.cliente.ventana.gerente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.EmpleadoHoras;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaVisualizarHoras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private GerenteController gerenteController;

	public VentanaVisualizarHoras(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;
		ventanaVisualizarHoras(nickname);
	}
	
	public void ventanaVisualizarHoras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 391);
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
//			logger.error("No hay ningun empleado.");
		}
	}
	
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
//			logger.error("No hay ningun empleado.");
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
//			logger.error("No hay ningun empleado.");
		}
	}
}

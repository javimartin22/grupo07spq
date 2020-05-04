package concesionario.cliente.ventana.mecanico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.CocheMatriculado;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Font;
import java.awt.Color;

public class VentanaCochesMatriculadosMecanico extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private MecanicoController mecanicoController;
	final Logger logger = LoggerFactory.getLogger(VentanaCochesMatriculadosMecanico.class);
	static int iteration = 0;
	
	public VentanaCochesMatriculadosMecanico(MecanicoController loginController, String nickname) {
		setResizable(false);
		this.mecanicoController = loginController;
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Matricula");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String matricula = JOptionPane.showInputDialog("Introduzca la matricula deseada:");
			cargarTablaFiltro(table, 0, matricula);
			}
		});
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Marca");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = JOptionPane.showInputDialog("Introduzca la marca que desea:");
				cargarTablaFiltro(table, 1, marca);
			}
		});
		mntmNewMenuItem_1.setForeground(Color.BLUE);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Nombre Propietario");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Introduzca el nombre del Cliente que desea:");
				cargarTablaFiltro(table, 2, nombre);
			}
		});
		mntmNewMenuItem_2.setForeground(Color.BLUE);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		iniciarVentanaCochesMatriculadosMecanico(nickname);
	}
	
	private void iniciarVentanaCochesMatriculadosMecanico(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 1020, 406);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Coches Matriculados");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmc = new VentanaMenuMecanico(mecanicoController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(711, 316, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 984, 294);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnVer.setBounds(854, 316, 112, 29);
		getContentPane().add(btnVer);
		
	}
	
	public void cargarTabla(JTable table) {
		List<CocheMatriculado> cochesMatric = mecanicoController.cargarCochesMatriculados();
		
		String[] columnNames = {"Matricula", "Marca", "Modelo", "Anio Matriculacion", "Revisiones", "CV", "Nombre Propietario", "Nº Puertas", "Color"};
		
		if (!cochesMatric.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheMatriculado cm : cochesMatric) {
				   Object[] o = new Object[9];
				   o[0] = cm.getMatricula();
				   o[1] = cm.getMarca();
				   o[2] = cm.getModelo();
				   o[3] = cm.getAnyoMatriculacion();
				   o[4] = cm.getRevisiones();
				   o[5] = cm.getCv();
				   o[6] = cm.getNombrePropietario();
				   o[7] = cm.getNumPuertas();
				   o[8] = cm.getColor();
				   model.addRow(o);
				 }
		} else {
			logger.error("No llega correctamente el CocheMatriculado.");
		}
	}
	
	public void cargarTablaFiltro(JTable table, int tipo, String restriccion) {
		String filtro = restriccion + "-" + tipo;
		List<CocheMatriculado> cochesMatric = mecanicoController.filtrarCocheMatriculado(filtro);
		
		String[] columnNames = {"Matricula", "Marca", "Modelo", "Anio Matriculacion", "Revisiones", "CV", "Nombre Propietario", "Nº Puertas", "Color"};
		
		if (!cochesMatric.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheMatriculado cm : cochesMatric) {
				   Object[] o = new Object[9];
				   o[0] = cm.getMatricula();
				   o[1] = cm.getMarca();
				   o[2] = cm.getModelo();
				   o[3] = cm.getAnyoMatriculacion();
				   o[4] = cm.getRevisiones();
				   o[5] = cm.getCv();
				   o[6] = cm.getNombrePropietario();
				   o[7] = cm.getNumPuertas();
				   o[8] = cm.getColor();
				   model.addRow(o);
				 }
		} else {
			logger.error("No llega correctamente el CocheMatriculado con este filtrado.");
		}
	}
}

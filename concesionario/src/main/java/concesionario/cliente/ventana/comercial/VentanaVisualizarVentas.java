package concesionario.cliente.ventana.comercial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ComercialController;
import concesionario.cliente.ventana.cliente.VentanaVisualizarTarifas;
import concesionario.datos.Venta;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

public class VentanaVisualizarVentas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ComercialController comercialController;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarTarifas.class);
	static int iteration = 0;
	
	public VentanaVisualizarVentas(ComercialController comercialController, String nickname) {
		this.comercialController = comercialController;
		iniciarVentanaVisualizarVentas(nickname);
	}

	public void iniciarVentanaVisualizarVentas(String nickname) {
		setResizable(false);
		setAutoRequestFocus(false);
		setBounds(100, 100, 1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Visualizacion Ventas");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnVolver = new JButton("Agregar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrarVentas vrv = new VentanaRegistrarVentas(comercialController, nickname);
				vrv.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(867, 303, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 980, 274);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearTabla(table);
			}
		});
		btnNewButton.setBounds(740, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolve.setBounds(613, 303, 117, 29);
		getContentPane().add(btnVolve);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnFiltro = new JMenu("Filtro");
		menuBar_1.add(mnFiltro);
		mnFiltro.setBackground(Color.LIGHT_GRAY);
		
		JMenuItem mntmModelo = new JMenuItem("Modelo");
		mntmModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nombre = JOptionPane.showInputDialog("Introduzca un modelo: ");
			    cargarTablaRestriccion(table, 1, nombre);
			}
		});
		
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nombre = JOptionPane.showInputDialog("Introduzca una marca: ");
			    cargarTablaRestriccion(table, 0, nombre);
			}
		});
		mnFiltro.add(mntmMarca);
		mnFiltro.add(mntmModelo);
		
		JMenuItem mntmComercial = new JMenuItem("Comercial");
		mntmComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Introduzca un nombre: ");
				cargarTablaRestriccion(table, 2, nombre);
			}
		});
		mnFiltro.add(mntmComercial);
	}
	
	public void resetearTabla(JTable tabla) {
		List<Venta> ventas = comercialController.cargarTablaVenta();
		
		String[] columnNames = {"Fecha", "Matricula", "Marca", "Modelo", "Comercial", "DNI Cliente"};
		
		if (!ventas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Venta v : ventas) {
				Object[] o = new Object[7];
				o[0] = v.getFecha();
				o[1] = v.getMatricula();
				o[2] = v.getMarca();
				o[3] = v.getModelo();
				o[4] = v.getNicknameComercial();
				o[5] = v.getNombreComprador();
				model.addRow(o);
			}
		} else {
			logger.error("Las ventas no llegan correctamente.");
		}
	}
	
	public void cargarTablaRestriccion (JTable table, int tipo, String restriccion) {
		List<Venta> ventas = new ArrayList<Venta>();
		switch (tipo) {
		case 0:
			if(!comercialController.filtrarVentaMarca(restriccion).isEmpty()) {
				ventas = comercialController.filtrarVentaMarca(restriccion);
			}else {
				JOptionPane.showMessageDialog(this, "No hay ninguna venta con esa marca.");
				logger.info("No existe ningun venta con la marca: " + restriccion);
			}
			break;
		case 1: 
			if(!comercialController.filtrarVentaModelo(restriccion).isEmpty()) {
				ventas = comercialController.filtrarVentaModelo(restriccion);
			}else {
				JOptionPane.showMessageDialog(this, "No hay ninguna venta con este modelo.");
				logger.info("No existe ninguna venta con el modelo:" + restriccion);
			}
			break;
		case 2: 
			if(!comercialController.filtrarVentaComercial(restriccion).isEmpty()) {
				ventas = comercialController.filtrarVentaComercial(restriccion);
			}else {
				JOptionPane.showMessageDialog(this, "No hay ninguna venta con este comercial.");
				logger.info("No existe ninguna venta con realizada por el comercial: " + restriccion);
			}
			break;
		}
		String[] columnNames = {"Fecha", "Matricula", "Marca", "Modelo", "Comercial", "DNI Cliente"};
		
		if (!ventas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Venta v : ventas) {
				Object[] o = new Object[7];
				o[0] = v.getFecha();
				o[1] = v.getMatricula();
				o[2] = v.getMarca();
				o[3] = v.getModelo();
				o[4] = v.getNicknameComercial();
				o[5] = v.getNombreComprador();
				model.addRow(o);
			}
		} else {
			logger.error("Las ventas no llegan correctamente.");
		}
	}
}

package concesionario.cliente.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.CocheTaller;
import concesionario.servidor.datos.Venta;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

public class VentanaVisualizarVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LoginController loginController;
	
	public VentanaVisualizarVentas(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaVisualizarVentas(nickname);
	}

	/**
	 * Create the frame.
	 */
	public void iniciarVentanaVisualizarVentas(String nickname) {
		setResizable(false);

		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventas");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnVolver = new JButton("Agregar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrarVentas vrv = new VentanaRegistrarVentas(loginController, nickname);
				vrv.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(624, 304, 117, 29);
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
		btnNewButton.setBounds(458, 304, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(loginController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolve.setBounds(294, 304, 117, 29);
		getContentPane().add(btnVolve);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnFiltro = new JMenu("Filtro");
		menuBar_1.add(mnFiltro);
		mnFiltro.setBackground(Color.LIGHT_GRAY);
		
		JMenuItem mntmModelo = new JMenuItem("Modelo");
		mntmModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearTabla(table);
			    String nombre = JOptionPane.showInputDialog("Introduzca un modelo: ");
			    nombre =  nombre.toUpperCase();
			   // cargarModelo(table, st,nombre);
			}
		});
		
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearTabla(table);
			    String nombre = JOptionPane.showInputDialog("Introduzca una marca: ");
			    nombre = nombre.toUpperCase();
			  //  cargarMarca(table,st,nombre);
			}
		});
		mnFiltro.add(mntmMarca);
		mnFiltro.add(mntmModelo);
		
		
		
		JMenuItem mntmComercial = new JMenuItem("Comercial");
		mntmComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearTabla(table);
				String nombre = JOptionPane.showInputDialog("Introduzca un nombre: ");
			   nombre =  nombre.toUpperCase();
			    //cargarComercial(table, st, nombre);
			}
		});
		mnFiltro.add(mntmComercial);
	}
	
	public void resetearTabla(JTable tabla) {
		List<Venta> ventas = loginController.cargarTablaVenta();
		
		String[] columnNames = {"Fecha", "Matricula", "Marca", "Modelo", "Comercial", "DNI Cliente"};
		
		if (!ventas.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   tabla.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (Venta v : ventas) {
				   Object[] o = new Object[7];
				   o[0] = v.getFecha();
				   o[1] = v.getMatricula();
				   o[2] = v.getModelo();
				   o[3] = v.getModelo();
				   o[4] = v.getNicknameComercial();
				   o[5] = v.getNombreComprador();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
	}
}

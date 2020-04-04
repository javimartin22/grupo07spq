package concesionario.cliente.ventana;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.LoginController;

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
		setTitle("Ventas");
		getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Agregar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VentanaAgregarVentas vav = new VentanaAgregarVentas(loginController, nickname);
				//vav.setVisible(true);
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
				//cargarTabla(table, st);
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
	
//	//Metodo general para obtener los datos de una base de datos y añadirlos a una tabla
	private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    // Nombre de las columnas:
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
	    // Datos de la tabla:
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
	    return new DefaultTableModel(data, columnNames);
	}
	
//	private static void cargarTabla(JTable tabla, Statement st) {
//		ResultSet rst = BD.ventasTodosSelect(st);
//		try {
//			tabla.setModel(buildTableModel(rst));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//	}
//	
//	private static void cargarMarca(JTable tabla, Statement st, String marca) {
//		ResultSet rst = BD.ventasMarcaSelect(st, marca);
//		try {
//			tabla.setModel(buildTableModel(rst));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//	}
//	
//	private static void cargarModelo(JTable tabla, Statement st, String modelo) {
//		ResultSet rst = BD.ventasModeloSelect(st, modelo);
//		try {
//			tabla.setModel(buildTableModel(rst));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
//		
//	private static void cargarComercial(JTable tabla, Statement st, String nickname) {
//		ResultSet rst = BD.ventasComercialSelect(st, nickname);
//		try {
//			tabla.setModel(buildTableModel(rst));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//	}
	
	private static void resetearTabla(JTable tabla) {
		 DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
	       tb.setRowCount(0);
	}
}



import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Statement st;
	private Connection con;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleados frame = new VentanaEmpleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEmpleados() {
		setAutoRequestFocus(false);
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		setBounds(100, 100, 992, 360);
		setTitle("Gestión de empleados");
		getContentPane().setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(847, 299, 117, 29);
		getContentPane().add(btnEliminar);
		
		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(316, 299, 112, 29);
		getContentPane().add(btnVer);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table, st);
			}
		});
		btnCargar.setBounds(145, 299, 117, 29);
		getContentPane().add(btnCargar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(16, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 980, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mecanico", "Comercial", "Departamento Compras"}));
		comboBox.setBounds(485, 300, 168, 27);
		getContentPane().add(comboBox);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipo = comboBox.getSelectedIndex();
				registrar(tipo);
			}
		});
		btnAgregar.setBounds(689, 299, 112, 29);
		getContentPane().add(btnAgregar);
		
	}
	

	//Metodo general para obtener los datos de una base de datos y añadirlos a una tabla
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
		
		private static void cargarTabla(JTable tabla, Statement st) {
			ResultSet rst = BD.empleadosTodasSelect(st);
			try {
				tabla.setModel(buildTableModel(rst));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		private void registrar(int tipo) {
			switch (tipo) {
			case 0:
				VentanaRegistroMecanico ventana = new VentanaRegistroMecanico();
				ventana.setVisible(true);
				dispose();
				break;
			case 1: 
				VentanaRegistroComercial ventanaComercial = new VentanaRegistroComercial();
				ventanaComercial.setVisible(true);
				dispose();		
				break;
			case 2:
				VentanaRegistroCompras ventanaCompras = new VentanaRegistroCompras();
				ventanaCompras.setVisible(true);
				dispose();		
				break;
			}
			
			
		}
}

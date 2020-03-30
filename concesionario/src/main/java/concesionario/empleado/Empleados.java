import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Empleados extends JFrame {

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
					Empleados frame = new Empleados();
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
	public Empleados() {
		con = BD.initBD("piezas");
		st = BD.usarCrearTablasBD(con);
		
		setTitle("Gestión de empleados");
		getContentPane().setLayout(null);
		
			
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.piezasInsert(st, "C1", "Amortiguador", 20, "Zona 1");
			}
		});
		
		btnAgregar.setBounds(367, 299, 112, 29);
		getContentPane().add(btnAgregar);
				
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(515, 299, 117, 29);
		getContentPane().add(btnEliminar);
		
		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(108, 299, 112, 29);
		getContentPane().add(btnVer);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table, st);
			}
		});
		btnCargar.setBounds(-11, 299, 117, 29);
		getContentPane().add(btnCargar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(232, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 6, 677, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		
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
			ResultSet rst = BD.piezasTodasSelect(st);
			try {
				tabla.setModel(buildTableModel(rst));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

}

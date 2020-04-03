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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.servidor.BaseDatos.BD;
import concesionario.servidor.datos.CocheConcesionario;

public class VentanaCochesCliente extends JFrame {
		
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Connection con;
	private Statement st;
	
	public static void main(String nickname) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCochesCliente ventanaCochesCliente = new VentanaCochesCliente("Pablo");
					ventanaCochesCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaCochesCliente(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setTitle("Gesti�n de empleados");
		getContentPane().setLayout(null);
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VentanaMenuCliente vmc = new VentanaMenuCliente();
				//vmc.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(849, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 980, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		cargarTabla(table, st);
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String modelo = (String) table.getModel().getValueAt(fila, 0);
				CocheConcesionario coche = BD.cocheConcesionarioSelect(st, modelo);
				JOptionPane.showMessageDialog(contentPane, coche.getPrecio());
			}
		});
		btnVer.setBounds(446, 299, 112, 29);
		getContentPane().add(btnVer);
		
	}
	
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
	
	public void cargarTabla(JTable table, java.sql.Statement st) {
		ResultSet rst = BD.cochesTodosSelect(st);
		try {
			table.setModel(buildTableModel(rst));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

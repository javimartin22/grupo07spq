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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.CocheConcesionario;
import concesionario.servidor.datos.CocheMatriculado;

public class VentanaCochesMatriculadosMecanico extends JFrame {
		
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private LoginController loginController;
	
	public VentanaCochesMatriculadosMecanico(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaCochesMatriculadosMecanico(nickname);
	}
	
	private void iniciarVentanaCochesMatriculadosMecanico(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setTitle("Coches Matriculados");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmc = new VentanaMenuMecanico(loginController, nickname);
				vmc.setVisible(true);
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
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
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
	
	public void cargarTabla(JTable table) {
		List<CocheMatriculado> cochesMatric = loginController.cargarCochesMatriculados();
		
		String[] columnNames = {"Matricula","Marca", "Modelo", "Anio Matriculacion", "Revisiones", "Nombre Propietario", "Nº Puertas", "Color"};
		
		if (!cochesMatric.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheMatriculado cm : cochesMatric) {
				   Object[] o = new Object[5];
				   o[0] = cm.getMarca();
				   o[1] = cm.getModelo();
				   o[2] = cm.getMatricula();
				   o[3] = cm.getAnyoMatriculacion();
				   o[4] = cm.getRevisiones();
				   o[5] = cm.getCv();
				   o[6] = cm.getNombrePropietario();
				   o[7] = cm.getNumPuertas();
				   o[8] = cm.getColor();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
		
	}
}

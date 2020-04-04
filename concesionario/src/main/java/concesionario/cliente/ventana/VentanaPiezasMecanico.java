package concesionario.cliente.ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.LoginController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class VentanaPiezasMecanico extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private LoginController loginController;
	
	public VentanaPiezasMecanico(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaPiezas(nickname);
	}
	
	public void iniciarVentanaPiezas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 100, 560, 334);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 20, 522, 235);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(loginController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(162, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Piezas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cargarTabla(tabla, st);
			}
		});
		btnCargarPiezas.setBounds(291, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnCogerPiezas = new JButton("Coger Piezas");
		btnCogerPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tabla.getSelectedRow();
				String codigo = (String) tabla.getModel().getValueAt(fila, 0);
//				cogerPieza(codigo);
			}
		});
		btnCogerPiezas.setBounds(421, 267, 117, 29);
		contentPane.add(btnCogerPiezas);
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
		
//		private static void cargarTabla(JTable tabla, Statement st) {
//			ResultSet rst = BD.piezasTodasSelect(st);
//			try {
//				tabla.setModel(buildTableModel(rst));
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//		
//		private void cogerPieza(String codigo) {
//			Pieza pieza = BD.piezaSelect(st, codigo);
//			Pieza piezaUtiluzada = BD.piezaUtilizadaSelect(st, codigo);
//			BD.piezaDelete(st, codigo);
//			BD.piezaUtilizadaDelete(st, codigo);
//			int unidadesUtilizadas = piezaUtiluzada.getUnidades() + 1;
//			int unidades = pieza.getUnidades() - 1;
//			if (unidades >= 0) {
//				BD.piezasInsert(st, pieza.getCodigo(), pieza.getNombre(), unidades, pieza.getUbicacion());
//				BD.piezasUtilizadasInsert(st, codigo, piezaUtiluzada.getNombre(), unidadesUtilizadas, piezaUtiluzada.getUbicacion());
//				cargarTabla(tabla, st);
//			} else {
//				JOptionPane.showMessageDialog(contentPane, "No quedan piezas");
//			}
//		}
}










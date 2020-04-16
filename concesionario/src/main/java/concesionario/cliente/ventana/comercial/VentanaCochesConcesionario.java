package concesionario.cliente.ventana.comercial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheConcesionario;

public class VentanaCochesConcesionario extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Controller loginController;
	
	public VentanaCochesConcesionario(Controller loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		iniciarVentanaCochesConcesionario(nickname);
	}
	
	public void iniciarVentanaCochesConcesionario(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestion de empleados");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(loginController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(600, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 29, 980, 263);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton(" Ver Info");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String marca = (String) table.getModel().getValueAt(fila, 1);
				String modelo = (String) table.getModel().getValueAt(fila, 0);
				String color = (String) table.getModel().getValueAt(fila, 5);
				int precio = (int) table.getModel().getValueAt(fila, 2);
				int cv = (int) table.getModel().getValueAt(fila, 3);
				int unidades = (int) table.getModel().getValueAt(fila, 6);
				int numPuertas = (int) table.getModel().getValueAt(fila, 4);
				CocheConcesionario coche = new CocheConcesionario(marca, modelo, precio, cv, numPuertas, color, unidades);
				VentanaDetallesCoche vdc = new VentanaDetallesCoche(loginController, coche, nickname);
				vdc.setVisible(true);
				dispose();
			}
		});
		btnVer.setBounds(858, 299, 112, 29);
		getContentPane().add(btnVer);
		
		JButton btnNewButton = new JButton("Cargar Coches");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnNewButton.setBounds(729, 299, 117, 29);
		getContentPane().add(btnNewButton);
		
	}
	
	public void cargarTabla(JTable table) {
		List<CocheConcesionario> coches = loginController.cargarTablaCochesConcesionario();
		
		String[] columnNames = {"MARCA",
		        "MODELO",
		        "PRECIO",
		        "CV",
		        "NUMPUERTAS",
		        "COLOR",
		        "UNIDADES"};
		
		if (!coches.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheConcesionario c : coches) {
				   Object[] o = new Object[7];
				   o[0] = c.getMarca();
				   o[1] = c.getModelo();
				   o[2] = c.getPrecio();
				   o[3] = c.getCv();
				   o[4] = c.getNumPuertas();
				   o[5] = c.getColor();
				   o[6] = c.getUnidades();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
	}
}

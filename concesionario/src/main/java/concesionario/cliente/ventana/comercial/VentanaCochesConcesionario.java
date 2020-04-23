package concesionario.cliente.ventana.comercial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheConcesionario;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Marca");
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marca = JOptionPane.showInputDialog("Introduzca la marca que le interesa:");
				cargarTablaFiltros(table, 0, marca);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Color");
		mntmNewMenuItem_1.setForeground(Color.BLUE);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String color = JOptionPane.showInputDialog("Introduzca el color que desee:");
				cargarTablaFiltros(table, 1, color);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("CV");
		mntmNewMenuItem_2.setForeground(Color.BLUE);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cv = JOptionPane.showInputDialog("Introduzca los CV que desee:");
				cargarTablaFiltros(table, 2, cv);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Precio Max");
		mntmNewMenuItem_3.setForeground(Color.BLUE);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el precio maximo:"));
				cargarTablaFiltros(table, 3, precio + "");
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_3);
		iniciarVentanaCochesConcesionario(nickname);
	}
	
	public void iniciarVentanaCochesConcesionario(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 390);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo Comercial");
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
		btnVolver.setBounds(578, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 966, 263);
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
		btnNewButton.setBounds(705, 299, 141, 29);
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
	
	public void cargarTablaFiltros(JTable table, int tipo, String restriccion) {
		List<CocheConcesionario> coches = new ArrayList<CocheConcesionario>();
		
		String filtro = restriccion + "-" + tipo;
		
		Response response = loginController.filtrarCocheConcesionario(filtro);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			GenericType<List<CocheConcesionario>> genericType = new GenericType<List<CocheConcesionario>>() {};
			coches = response.readEntity(genericType);
		}else {
			JOptionPane.showMessageDialog(this, "No hay ningun con ese codigo.");
		}
		
		String[] columnNames = {"Marca", "Modelo", "CV", "Precio", "Unidades"};
		
		if (!coches.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   for (CocheConcesionario e : coches) {
				   Object[] o = new Object[5];
				   o[0] = e.getMarca();
				   o[1] = e.getModelo();
				   o[2] = e.getCv();
				   o[3] = e.getPrecio();
				   o[4] = e.getUnidades();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
	}
}

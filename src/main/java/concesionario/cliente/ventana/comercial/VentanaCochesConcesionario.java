package concesionario.cliente.ventana.comercial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CocheConcesionario;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 *Clase para visualizar los coches del concesionario
 */
public class VentanaCochesConcesionario extends JFrame {
		
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ComercialController comercialController;
	final Logger logger = LoggerFactory.getLogger(VentanaCochesConcesionario.class);
	static int iteration = 0;
	
	/**
	 * Contructor y frame de la clase VentanaCocheConcesionario
	 * @param comercialController (Controlador de la ventana VentanaCocheConcesionario)
	 * @param nickname (nombre de usuario del comercial)
	 */
	public VentanaCochesConcesionario(ComercialController comercialController, String nickname) {
		setResizable(false);
		this.comercialController = comercialController;
		
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
		setBounds(100, 100, 1004, 406);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo Coches Concesionario Comercial");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController, nickname);
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
				VentanaDetallesCoche vdc = new VentanaDetallesCoche(comercialController, coche, nickname);
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
	
	/**
	 * Metodo para cargar la tabla de coches del concerionario
	 * @param table (Tabla)
	 */
	public void cargarTabla(JTable table) {
		List<CocheConcesionario> coches = comercialController.cargarTablaCochesConcesionario();
		
		String[] columnNames = {"MARCA", "MODELO", "PRECIO", "CV", "NUMPUERTAS", "COLOR", "UNIDADES"};
		
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
			logger.error("No llegan correctamente los CocheConcesionario.");
		}
	}
	
	/**
	 * Metodo para filtrar la tabla de coches del concesionario
	 * @param table (Tabla)
	 * @param tipo (Tipo de de filtro)
	 * @param restriccion (String del filtro que se desea aplcicar (Color, CV, Marca o precio maximo))
	 */
	public void cargarTablaFiltros(JTable table, int tipo, String restriccion) {
		String filtro = restriccion + "-" + tipo;
		List<CocheConcesionario> coches = comercialController.filtrarCocheConcesionario(filtro);
		
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
			logger.error("Fallo en el filtrado de los CocheConcesionario.");
		}
	}
}

package concesionario.cliente.ventana.departamentoCompras;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.Pieza;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;

/**
 * VentanaPiezasUtilizadas (Ventana para la visualizacion de las piezas que han sido utilizadas).
 */
public class VentanaPiezasUtilizadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DepartmentoComprasController departamentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaPiezasUtilizadas.class);
	static int iteration = 0;

	/**
	 * Constructor de la VentanaPiezasUtilizadas.
	 * @param departmentoComprasController (Controlador de las Ventanas de Departamento de Compras)
	 * @param nickname (Nickname del departamento de compras)
	 */
	public VentanaPiezasUtilizadas(DepartmentoComprasController departmentoComprasController, String nickname) {
		setTitle("Disposicion de Piezas");
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaPiezasUtilizadas(nickname);
	}

	/**
	 * Inicializador del JFrame de la VentanaPiezasUtilizadas
	 * @param nickname (Nickname del departamento de compras)
	 */
	public void  iniciarVentanaPiezasUtilizadas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 371);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Unidades Max");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String unidadesMax = JOptionPane.showInputDialog("Introduzca el numero de unidades minimas que desea (Entero):");
				cargarTablaFiltro(tabla, 0, unidadesMax);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Unidades Min");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String unidadesMin = JOptionPane.showInputDialog("Introduzca el numero de unidades minimas que desea (Entero):");
				cargarTablaFiltro(tabla, 1, unidadesMin);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Codigo");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Introduzca el codigo de la pieza deseada:");
				cargarTablaFiltro(tabla, 2, codigo);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 20, 675, 235);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuDepartamentoCompras vmdc = new VentanaMenuDepartamentoCompras(departamentoComprasController, nickname);
				vmdc.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(294, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Todas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(421, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnNewButton_1 = new JButton("Cargar Utilizadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaUtilizadas(tabla);
			}
		});
		btnNewButton_1.setBounds(548, 266, 143, 29);
		contentPane.add(btnNewButton_1);
	}
	
	/**
	 * Metodo para mostrar la tabla de todas las Piezas.
	 * @param tabla (Tabla de la ventana).
	 */		
	public void cargarTabla(JTable tabla) {
		List<Pieza> piezas = departamentoComprasController.cargarPiezas();
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Pieza p : piezas) {
				Object[] o = new Object[7];
				o[0] = p.getCodigo();
				o[1] = p.getNombre();
				o[2] = p.getUnidades();
				o[3] = p.getUbicacion();
				model.addRow(o);
			}
		} else {
			logger.error("No hay piezas.");
		}
	}
		
	/**
	 * Metodo para mostrar la tabla de las Piezas que han sido utilizadas.
	 * @param table (Tabla de la ventana).
	 */	
	public void cargarTablaUtilizadas(JTable table) {
		List<Pieza> piezas = departamentoComprasController.cargarPiezasUtilizadas();
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Pieza p : piezas) {
				Object[] o = new Object[7];
				o[0] = p.getCodigo();
				o[1] = p.getNombre();
				o[2] = p.getUnidades();
				o[3] = p.getUbicacion();
				model.addRow(o);
			}
		} else {
			logger.error("No hay piezas utilizadas.");
		}
	}
	
	/**
	 * Metodo para mostrar la tabla de las Piezas Utilizadas a partir de un filtro
	 * @param table (Tabla de la ventana).
	 * @param tipo (Tipo de pieza)
	 * @param restriccion (Filtro de busqueda: codigo, nombre, unidades, ubicacion)
	 */	
	public void cargarTablaFiltro(JTable table, int tipo, String restriccion) {
		
		
		String filtro = restriccion + "-" + tipo;
		List<Pieza> piezas = departamentoComprasController.filtrarPiezaUtilizadas(filtro);
		
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Pieza p : piezas) {
				Object[] o = new Object[7];
				o[0] = p.getCodigo();
				o[1] = p.getNombre();
				o[2] = p.getUnidades();
				o[3] = p.getUbicacion();
				model.addRow(o);
			}
		} else {
			logger.error("No hay piezas utilizadas con ese filtro.");
		}
	}
	
}

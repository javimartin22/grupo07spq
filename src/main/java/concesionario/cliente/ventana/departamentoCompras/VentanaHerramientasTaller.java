package concesionario.cliente.ventana.departamentoCompras;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.HerramientasTaller;

/**
 * Clase para visualizar las piezas del taller
 */
public class VentanaHerramientasTaller extends JFrame {
	


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	/**
	 * Controlador para la clase DepartamentoComprasController
	 */
	private DepartmentoComprasController departamentoComprasController;

	/**
	 * Constructor para la clase VentanaHerramietasTaller
	 * @param departmentoComprasController (Controlador de la ventana VentanaHerramientasTaller)
	 * @param nickname (Nickname del usuario de departamento de compras)
	 */
	public VentanaHerramientasTaller(DepartmentoComprasController departmentoComprasController, String nickname) {
		setTitle("Disposicion de Herramientas");
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaHerramientasTaller(nickname);
	}

	/**
	 * Create the frame
	 * @param nickname (Nickname del usuario de departamento de compras)
	 */
	public void  iniciarVentanaHerramientasTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 380);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("Codigo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String codigo = JOptionPane.showInputDialog("Introduzca el codigo de la herramienta que desee:");
				cargarTablaFiltro(tabla, 0, codigo);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nombre");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Introduzca el nombre de la herramienta que desee:");
				cargarTablaFiltro(tabla, 1, nombre);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Unidades");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int unidades = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el numero de unidades que desea:"));
				String restriccion = unidades +"";
				cargarTablaFiltro(tabla, 2, restriccion);
			}
		
		});
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.PLAIN, 12));
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
		btnRegresar.setBounds(439, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Todas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(574, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
	}
	
	/**
	 * Metodo para cargar las herramientas	
	 * @param tabla Tabla
	 */
	public void cargarTabla(JTable tabla) {
		List<HerramientasTaller> herramientas = departamentoComprasController.cargarHerramientas();
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!herramientas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (HerramientasTaller p : herramientas) {
				Object[] o = new Object[7];
				o[0] = p.getCodigo();
				o[1] = p.getNombre();
				o[2] = p.getUnidades();
				o[3] = p.getUbicacion();
				model.addRow(o);
			}
		} else {
		}
	}
		

	/**
	 * Metodo para filtrar las herramientas por codigo, nombre o unidad
	 * @param table Tabla
	 * @param tipo Int para saber de que tipo de filtro se trata
	 * @param filtro Filtro que se desea aplicar para filtrar la tabla
	 */
	public void cargarTablaFiltro(JTable table, int tipo, String filtro) {
		String restriccion = filtro + "-" + tipo;
		List<HerramientasTaller> herramientas = departamentoComprasController.filtrarHerramientaMecanico(restriccion);
		
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!herramientas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			if (tipo == 0) {
				for (HerramientasTaller h : herramientas) {
					if (h.getCodigo().equals(filtro)) {
						Object[] o = new Object[7];
						o[0] = h.getCodigo();
						o[1] = h.getNombre();
						o[2] = h.getUnidades();
						o[3] = h.getUbicacion();
						model.addRow(o);
					}

				}
				
				

			}
			if (tipo == 1) {
				for (HerramientasTaller h : herramientas) {
					if (h.getNombre().equals(filtro)) {
						Object[] o = new Object[7];
						o[0] = h.getCodigo();
						o[1] = h.getNombre();
						o[2] = h.getUnidades();
						o[3] = h.getUbicacion();
						model.addRow(o);
					}

				}
				
				

			}
			
			if(tipo==2) {
				int filtroInt = Integer.parseInt(filtro);

				for (HerramientasTaller h : herramientas) {
					if (h.getUnidades()==filtroInt) {
						Object[] o = new Object[7];
						o[0] = h.getCodigo();
						o[1] = h.getNombre();
						o[2] = h.getUnidades();
						o[3] = h.getUbicacion();
						model.addRow(o);
					}
					else {
						JOptionPane.showInputDialog("Introduzca el numero de unidades que desea:");
					}

				}
				
			}
		
		} 
		
		else {
			if (tipo == 0) {
				JOptionPane.showMessageDialog(null, "Codigo de herramienta no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
			}
			if (tipo == 1) {
				JOptionPane.showMessageDialog(null, "Nombre de herramienta no encontrado", "Error", JOptionPane.WARNING_MESSAGE);

			}

			if (tipo == 2) {
				JOptionPane.showMessageDialog(null, "Herramienta con ese numero de unidades no encontrado", "Error", JOptionPane.WARNING_MESSAGE);

			}
		}
	}
	


}

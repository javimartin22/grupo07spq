package concesionario.cliente.ventana.mecanico;

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
import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.HerramientasTaller;

/**
 * Interfaz grafica VentanaHerramientasMecanico (Permite visualizar las herramientas disponibles en el taller)
 */

public class VentanaHerramientasMecanico extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private MecanicoController mecanicoController;

	/**
	 * Constructor de la VentanaHerramientasMecanico
	 * @param VentanaHerramientasMecanico (Controlador de las ventanas para la clase Mecanico)
	 * @param nickname (Nickname del mecanico)
	 */
	public VentanaHerramientasMecanico(MecanicoController VentanaHerramientasMecanico, String nickname) {
		setResizable(false);
		setTitle("Herramientas Taller");
		this.mecanicoController = VentanaHerramientasMecanico;
		iniciarVentanaHerramientas(nickname);
	}
	
	/**
	 * Inicializador del JFrame de la VentanaHerramientasMecanico
	 * @param nickname (Nickname del mecanico)
	 */
	public void iniciarVentanaHerramientas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 100, 560, 362);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/**
		 * Menu para filtrar busquedas
		 */
		JMenu mnNewMenu = new JMenu("Filtros");
		menuBar.add(mnNewMenu);
		
		/*
		 * Filtros
		 */
		
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
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Scrollpane para la tabla
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 522, 235);
		contentPane.add(scrollPane);
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(mecanicoController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(72, 257, 117, 29);
		contentPane.add(btnRegresar);
		
		/*
		 * Boton que carga las herramientas del taller disponibles desde la BD
		 */
		JButton btnCargarHerramientas = new JButton("Cargar Herramientas");
		btnCargarHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarHerramientas.setBounds(199, 257, 180, 29);
		contentPane.add(btnCargarHerramientas);
		
		/*
		 * Acceso a la interfaz grafica para solicitar una compra de herramientas o piezas
		 */
		JButton btnNewButton = new JButton("Solicitar Compra");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSolicitudCompra vsc = new VentanaSolicitudCompra(mecanicoController, nickname);
				vsc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(389, 257, 143, 29);
		contentPane.add(btnNewButton);
		
		
	}
	
	/**
	 * Carga los datos de la BD y los introduce en una JTable
	 * @param table Tabla de HerramientasMecanico
	 */
		public void cargarTabla(JTable table) {
			List<HerramientasTaller> herramientas = mecanicoController.cargarHerramientasTaller();
			String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
			
			if (!herramientas.isEmpty()) {
				 DefaultTableModel model = new DefaultTableModel();
				   table.setModel(model);
				   model.setColumnIdentifiers(columnNames);
				   
				   for (HerramientasTaller h : herramientas) {
					   Object[] o = new Object[7];
					   o[0] = h.getCodigo();
					   o[1] = h.getNombre();
					   o[2] = h.getUnidades();
					   o[3] = h.getUbicacion();
					   model.addRow(o);
					 }
			} 
		}
		
	/**
	 * Aplica los filtros de busqueda y los introduce en la tabla.
	 * @param table Tabla de HerramientasMecanico
	 * @param tipo  Identificacion del tipo de filtro
	 * @param filtro Busqueda del usuario
	 */
	
	public void cargarTablaFiltro(JTable table, int tipo, String filtro) {
		String restriccion = filtro + "-" + tipo;
		List<HerramientasTaller> herramientas = mecanicoController.filtrarHerramientaMecanico(restriccion);
		
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
	}
}

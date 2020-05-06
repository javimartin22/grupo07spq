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


public class VentanaHerramientasMecanico extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private MecanicoController mecanicoController;

	public VentanaHerramientasMecanico(MecanicoController mecanicoController, String nickname) {
		setResizable(false);
		setTitle("Herramientas Taller");
		this.mecanicoController = mecanicoController;
		iniciarVentanaHerramientas(nickname);
	}
	
	public void iniciarVentanaHerramientas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 100, 560, 353);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
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
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnRegresar.setBounds(96, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarHerramientas = new JButton("Cargar Herramientas");
		btnCargarHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarHerramientas.setBounds(259, 267, 180, 29);
		contentPane.add(btnCargarHerramientas);
		
		
	}
	
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
	
	public void cargarTablaFiltro(JTable table, int tipo, String filtro) {
		String restriccion = filtro + "-" + tipo;
		List<HerramientasTaller> herramientas = mecanicoController.filtrarHerramientaMecanico(restriccion);
		
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!herramientas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			if (tipo == 0) {
				for (HerramientasTaller p : herramientas) {
					if (p.getCodigo().equals(filtro)) {
						Object[] o = new Object[7];
						o[0] = p.getCodigo();
						o[1] = p.getNombre();
						o[2] = p.getUnidades();
						o[3] = p.getUbicacion();
						model.addRow(o);
					}

				}
				
				

			}
			if (tipo == 1) {
				for (HerramientasTaller p : herramientas) {
					if (p.getNombre().equals(filtro)) {
						Object[] o = new Object[7];
						o[0] = p.getCodigo();
						o[1] = p.getNombre();
						o[2] = p.getUnidades();
						o[3] = p.getUbicacion();
						model.addRow(o);
					}

				}
				
				

			}
			
			else {
				int filtroInt = Integer.parseInt(filtro);

				for (HerramientasTaller p : herramientas) {
					if (p.getUnidades()<= filtroInt) {
						Object[] o = new Object[7];
						o[0] = p.getCodigo();
						o[1] = p.getNombre();
						o[2] = p.getUnidades();
						o[3] = p.getUbicacion();
						model.addRow(o);
					}

				}
				
			}
		
		} 
	}
}
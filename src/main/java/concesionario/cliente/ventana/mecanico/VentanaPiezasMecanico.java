package concesionario.cliente.ventana.mecanico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.Pieza;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaPiezasMecanico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private MecanicoController mecanicoController;
	final Logger logger = LoggerFactory.getLogger(VentanaPiezasMecanico.class);
	static int iteration = 0;
	
	public VentanaPiezasMecanico(MecanicoController mecanicoController, String nickname) {
		setResizable(false);
		this.mecanicoController = mecanicoController;
		iniciarVentanaPiezas(nickname);
	}
	
	public void iniciarVentanaPiezas(String nickname) {
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
				String codigo = JOptionPane.showInputDialog("Introdzca el codigo de la pieza que desee:");
				cargarTablaFiltro(tabla, 0, codigo);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nombre");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Introdzca el nombre de la pieza que desee:");
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
		btnRegresar.setBounds(161, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Piezas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(288, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnCogerPiezas = new JButton("Coger Piezas");
		btnCogerPiezas.setBounds(415, 267, 117, 29);
		contentPane.add(btnCogerPiezas);
	}
	
		public void cargarTabla(JTable table) {
			List<Pieza> piezas = mecanicoController.cargarPiezas();
			String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
			
			if (!piezas.isEmpty()) {
				 DefaultTableModel model = new DefaultTableModel();
				   table.setModel(model);
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
				logger.error("No llegan correctamente las piezas.");
			}
		}
		
	public void cargarTablaFiltro(JTable table, int tipo, String filtro) {
		String restriccion = filtro + "-" + tipo;
		List<Pieza> piezas = mecanicoController.filtrarPiezaMecanico(restriccion);
		
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
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
			logger.error("No llegan correctamente las piezas con este filtro.");
		}
	}
}










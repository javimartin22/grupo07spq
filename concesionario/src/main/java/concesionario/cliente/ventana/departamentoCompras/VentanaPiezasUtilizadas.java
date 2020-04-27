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


public class VentanaPiezasUtilizadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DepartmentoComprasController departamentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaPiezasUtilizadas.class);
	static int iteration = 0;

	public VentanaPiezasUtilizadas(DepartmentoComprasController departmentoComprasController, String nickname) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaPiezasUtilizadas(nickname);
	}

	
	public void  iniciarVentanaPiezasUtilizadas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 361);
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
		btnRegresar.setBounds(16, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Todas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(145, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnNewButton = new JButton("Añadir Unidades");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = (String) tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
				if (departamentoComprasController.seleccionarPiezaUtilizada(codigo) != null) {
					anyadirUnidades(departamentoComprasController.seleccionarPiezaUtilizada(codigo));
					logger.info("Unidades añdidas correctamente.");
				} else {
					logger.error("Fallo a la hora de añadir nuevas unidades.");
					JOptionPane.showMessageDialog(contentPane, "La pieza seleccionada no existe.");;
				} 
			}
		});
		btnNewButton.setBounds(558, 267, 133, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cargar Utilizadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaUtilizadas(tabla);
			}
		});
		btnNewButton_1.setBounds(274, 267, 143, 29);
		contentPane.add(btnNewButton_1);
	}
	
		
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
		
	private void anyadirUnidades(Pieza pieza) {
		String u = JOptionPane.showInputDialog("¿Cuantas unidades desea suministrar?");
		int unidades = Integer.parseInt(u);
		if (departamentoComprasController.registroPiezaUtilizada(pieza, unidades)) {
			logger.info("Unidades añadidas correctamente.");
			JOptionPane.showMessageDialog(contentPane, "Unidades anyadidas correctamente");
		} else {
			logger.error("No se han añadido correctamente las unidades.");
		}
	}
		
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

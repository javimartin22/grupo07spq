package concesionario.cliente.ventana.mecanico;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.CocheTaller;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;

/**
 *VentanaVisualizarCatalogo (Ventana para la visualizacion de los coches que se encuentran en el taller)
 */
public class VentanaVisualizarCochesTaller extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MecanicoController mecanicoController;
	private JTable table_1;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarCochesTaller.class);
	static int iteration = 0;
	
	/**
	 * Constructor de la VentanaVisualizarCochesTaller
	 * @param mecanicoController (Objeto MecanicoController).
	 * @param nickname (Nickname del Mecanico).
	 */
	public VentanaVisualizarCochesTaller(MecanicoController mecanicoController, String nickname){
		setTitle("Visualizar Coches Taller");
		setResizable(false);
		this.mecanicoController = mecanicoController;
		iniciarVentanaVisualizarCochesTaller(nickname);
	}
	
	/**
	 * Creacion del Frame de la VentanaVisualizarCochesTaller.
	 * @param nickname (Nickname del Mecanico).
	 */
	public void iniciarVentanaVisualizarCochesTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 434);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setForeground(Color.BLUE);
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Matricula ");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String matricula = JOptionPane.showInputDialog("Introduzca la matricula del vehiculo que desea:");
				cargarTablaFiltro(table_1, 0, matricula);
			}
		});
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Estado del Vehiculo");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado = JOptionPane.showInputDialog("Introduzca el estado del vehiculo que desea (Sin empezar -> 0; En Proceso -> 1; Terminado -> 2):");
				cargarTablaFiltro(table_1, 3, estado);
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mecanico");
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 12));
		mntmNewMenuItem_2.setForeground(Color.BLUE);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mecanico = JOptionPane.showInputDialog("Introduzca el mecanico que desea:");
				cargarTablaFiltro(table_1, 1, mecanico);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Coste Maximo");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String costeMaximo = JOptionPane.showInputDialog("Introduzca el coste maximo que desea (Entero):");
				cargarTablaFiltro(table_1, 2, costeMaximo);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Dialog", Font.BOLD, 12));
		mntmNewMenuItem_3.setForeground(Color.BLUE);
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_1.setForeground(Color.BLUE);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 18, 765, 294);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table_1);
			}
		});
		btnCargar.setBounds(377, 323, 117, 29);
		contentPane.add(btnCargar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(mecanicoController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(250, 323, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(contentPane, "¿Esta seguro de que quiere eliminarlo?");
				switch (resp) {
				case 0:
					int fila = table_1.getSelectedRow();
					String estado = (String) table_1.getModel().getValueAt(fila, 6);
					if (estado.equals("Terminado")) {
						eliminarCocheTaller((String) table_1.getModel().getValueAt(fila, 0));
					} else {
						JOptionPane.showMessageDialog(contentPane, "El coche no se ha finalizado.");
					}
					break;
				case 1:
					JOptionPane.showMessageDialog(contentPane, "No se eliminara.");
					break;
				case 2:
					JOptionPane.showMessageDialog(contentPane, "No se eliminara.");
					break;
				}	
			}
		});
		btnEliminar.setBounds(664, 323, 117, 29);
		contentPane.add(btnEliminar);
		
		JButton btnNewButton_1 = new JButton("Cambiar Estado");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table_1.getSelectedRow();
				String matricula = (String) table_1.getModel().getValueAt(fila, 0);
				cambiarEstado(matricula);
			}
		});
		btnNewButton_1.setBounds(504, 323, 150, 29);
		contentPane.add(btnNewButton_1);
		
		
	}
	
	/**
	 * Metodo para mostrar la tabla de los coches del taller.
	 * @param table (Tabla de la ventana).
	 */
	public void cargarTabla(JTable table) {
		List<CocheTaller> coches = mecanicoController.cargarTablaCocheTaller();
		
		String[] columnNames = {"Marticula", "Marca", "Modelo", "DNI Cliente", "Mecanico", "Coste", "Estado"};
		
		if (!coches.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheTaller c : coches) {
				   Object[] o = new Object[7];
				   o[0] = c.getMatricula();
				   o[1] = c.getMarca();
				   o[2] = c.getModelo();
				   o[3] = c.getDniCliente();
				   o[4] = c.getMecanico();
				   o[5] = c.getCoste() + "€";
				   o[6] = mecanicoController.traducirEstado(c.getEstado());
				   model.addRow(o);
				 }
		} else {
			logger.error("No llegan correctamente los CocheTaller");
		}
	}
	/**
	 * Metodo para eliminar de la BD un coche que sale del taller.
	 * @param matricula (Matricula del coche)
	 */
	public void eliminarCocheTaller(String matricula) {
		if (mecanicoController.deleteCocheTaller(matricula)) {
			JOptionPane.showMessageDialog(contentPane, "El coche ha salido del taller.");
			logger.info("El coche ha salido del taller.");
		} else {
			logger.error("Error al eliminar el CocheTaller");
		}
	}
	
	
	/**
	 * Metodo para cambiar el estado de revision de un vehiculo en el taller.
	 * @param matricula (Matricula del coche)
	 */
	public void cambiarEstado(String matricula) {
		CocheTaller coche = mecanicoController.seleccionarCocheTaller(matricula);
		if (coche != null) {
			int estado = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el estado del vehiculo (0: sin comenzar; 1: en proceso; 2: terminado)"));
			if (mecanicoController.cambiarEstadoCocheTaller(coche, estado)) {
				logger.info("El estado ha sido modificado.");
			} else {
				logger.error("Error a la hora de moficicar el estado.");
			}
		} else {
			logger.error("El coche seleccionado no existe.");
		}
	}
	/**
	 * Metodo para mostrar la tabla de los Coches del Taller teniendo en cuenta unos criterios.
	 * @param table (Tabla de la ventana)
	 * @param tipo (Tipo Filtrado)
	 * @param restriccion (Filtro para la busqueda)
	 */
	public void cargarTablaFiltro(JTable table, int tipo, String restriccion) {
		
		String filtro = restriccion + "-" + tipo; 
		List<CocheTaller> coches = mecanicoController.filtrarCocheTaller(filtro);
		String[] columnNames = {"Marticula", "Marca", "Modelo", "DNI Cliente", "Mecanico", "Coste", "Estado"};
		
		if (!coches.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheTaller c : coches) {
				   Object[] o = new Object[7];
				   o[0] = c.getMatricula();
				   o[1] = c.getMarca();
				   o[2] = c.getModelo();
				   o[3] = c.getDniCliente();
				   o[4] = c.getMecanico();
				   o[5] = c.getCoste() + "€";
				   o[6] = mecanicoController.traducirEstado(c.getEstado());
				   model.addRow(o);
				 }
		} else {
			logger.error("No se dispone ningun vehiculo con ese filtrado.");
		}
	}
}

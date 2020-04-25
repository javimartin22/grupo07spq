package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.ClienteController;
import concesionario.datos.CocheConcesionario;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;

public class VentanaVisualizarCatalogo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClienteController clienteController;
	private JTable table;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarTarifas.class);
	static int iteration = 0;

	public VentanaVisualizarCatalogo(ClienteController clienteController, String nickname) {
		setTitle("Catalogo");
		setResizable(false);
		this.clienteController = clienteController;
		ventanaVisualizarCatalogo(nickname);
	}

	/**
	 * Create the frame.
	 */
	public void ventanaVisualizarCatalogo(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 361);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setForeground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Marca");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem_3.setForeground(Color.BLUE);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String marca = JOptionPane.showInputDialog("Introduzca la marca que le interesa:");
				cargarTablaFiltros(table, 0, marca);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Color");
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String color = JOptionPane.showInputDialog("Introduzca el color que desee:");
				cargarTablaFiltros(table, 1, color);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("CV");
		mntmNewMenuItem_1.setForeground(Color.BLUE);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cv = JOptionPane.showInputDialog("Introduzca los CV que desee:");
				cargarTablaFiltros(table, 2, cv);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Precio Max");
		mntmNewMenuItem_2.setForeground(Color.BLUE);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el precio maximo:"));
				cargarTablaFiltros(table, 3, precio + "");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 637, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cargar Catalogo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
				
			}
		});
		btnNewButton.setBounds(364, 268, 131, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuCliente ventanaMenuCliente = new VentanaMenuCliente(nickname, clienteController);
				ventanaMenuCliente.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(258, 268, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Visualizar Info");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String modelo = (String) table.getModel().getValueAt(fila, 1);
				verInfo(modelo, nickname);
			}
		});
		btnNewButton_2.setBounds(515, 268, 121, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
	
	public void cargarTabla(JTable table) {
		List<CocheConcesionario> coches = clienteController.cargarTablaCochesConcesionario();
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
			logger.error("No llegan correctamente los vehiculos.");
		}
	}
	
	
	
	public void verInfo(String modelo, String nickname) {
		if (clienteController.seleccionarCocheConcesionario(modelo) != null) {
			VentanaInformacionCocheConcesionario vicc = new VentanaInformacionCocheConcesionario(clienteController, nickname, clienteController.seleccionarCocheConcesionario(modelo));
			vicc.setVisible(true);
			dispose();
		} else {
			logger.error("No llega correctamente el vehiculo.");
		}
	}
	
	public void cargarTablaFiltros(JTable table, int tipo, String restriccion) {
		String filtro = restriccion + "-" + tipo;
		List<CocheConcesionario> coches = clienteController.filtrarCocheConcesionario(filtro);
		
		if(!coches.isEmpty()) {
			String[] columnNames = {"Marca", "Modelo", "CV", "Precio", "Unidades"};

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
		}else {
			logger.error("Fallo en el filtrado de vehiculos.");
		}
	}
}

package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import concesionario.cliente.controller.ClienteController;
import concesionario.datos.Tarifa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class VentanaVisualizarTarifas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClienteController clienteController;
	private JTable table;
	private DefaultTableModel model;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarTarifas.class);
	static int iteration = 0;

	public VentanaVisualizarTarifas(ClienteController clienteController, String nickname) {
		setTitle("Catalogo de Tarifas");
		setResizable(false);
		this.clienteController = clienteController;
		ventanaVisualizarTarifas(nickname);
	}

	/**
	 * Create the frame.
	 */
	public void ventanaVisualizarTarifas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 706, 365);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Precio max");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Introduzca cantidad");
				if(isNumeric(respuesta)) {
					int resp = Integer.parseInt(respuesta);
					List<Tarifa> tarifas = clienteController.filtrarTarifaPrecio(resp);
					if(!tarifas.isEmpty()) {
						
						String[] columnNames = {"Id", "Nombre", "Precio Aproximado", "Mano de obra(h)"};
							  model = new DefaultTableModel();
							   table.setModel(model);
							   model.setColumnIdentifiers(columnNames);
							   for (Tarifa t : tarifas) {
								   Object[] o = new Object[5];
								   o[0] = t.getIdTarifa();
								   o[1] = t.getNomTarifa();
								   o[2] = t.getPrecioAprox();
								   o[3] = t.getHoras_manodeobra();
								   model.addRow(o);
							   }
					} else {
						logger.error("No llega ninguna tarifa.");
					}
					
				}else {
					JOptionPane.showInputDialog("No ha introducido un numero");
					
				}
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Precio minimo");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Introduzca cantidad");
				if(isNumeric(respuesta)) {
					int resp = Integer.parseInt(respuesta);
					List<Tarifa> tarifas = clienteController.filtrarTarifaPrecioMin(resp);
					if(!tarifas.isEmpty()) {
						String[] columnNames = {"Id", "Nombre", "Precio Aproximado", "Mano de obra(h)"};
							  model = new DefaultTableModel();
							   table.setModel(model);
							   model.setColumnIdentifiers(columnNames);
							   for (Tarifa t : tarifas) {
								   Object[] o = new Object[5];
								   o[0] = t.getIdTarifa();
								   o[1] = t.getNomTarifa();
								   o[2] = t.getPrecioAprox();
								   o[3] = t.getHoras_manodeobra();
								   model.addRow(o);
								 }
					}
					
				}else {
					JOptionPane.showInputDialog("No ha introducido un numero");
				}
				
			}
		});
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Horas mano de obra Max");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Introduzca cantidad");
				if(isNumeric(respuesta)) {
					int horas = Integer.parseInt(respuesta);
					List<Tarifa> tarifas = clienteController.filtrarTarifaHorasMax(horas);
					if(!tarifas.isEmpty()) {
						String[] columnNames = {"Id", "Nombre", "Precio Aproximado", "Mano de obra(h)"};
							  model = new DefaultTableModel();
							   table.setModel(model);
							   model.setColumnIdentifiers(columnNames);
							   for (Tarifa t : tarifas) {
								   Object[] o = new Object[5];
								   o[0] = t.getIdTarifa();
								   o[1] = t.getNomTarifa();
								   o[2] = t.getPrecioAprox();
								   o[3] = t.getHoras_manodeobra();
								   model.addRow(o);
								 }
					}
					
				}else {
					JOptionPane.showInputDialog("No ha introducido un numero");
				}
				
			}
		});
		
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Horas mano de obra Min");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Introduzca cantidad");
				if(isNumeric(respuesta)) {
					int horas = Integer.parseInt(respuesta);
					List<Tarifa> tarifas = clienteController.filtrarTarifaHorasMin(horas);
					if(!tarifas.isEmpty()) {
						String[] columnNames = {"Id", "Nombre", "Precio Aproximado", "Mano de obra(h)"};
						
							  model = new DefaultTableModel();
							   table.setModel(model);
							   model.setColumnIdentifiers(columnNames);
							   for (Tarifa t : tarifas) {
								   Object[] o = new Object[5];
								   o[0] = t.getIdTarifa();
								   o[1] = t.getNomTarifa();
								   o[2] = t.getPrecioAprox();
								   o[3] = t.getHoras_manodeobra();
								   model.addRow(o);
								 }
						
					} else {
						JOptionPane.showMessageDialog(contentPane, "No hay ninguna tarifa asi.");
					}
					
				}else {
					JOptionPane.showInputDialog("No ha introducido un numero");
				}
				
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(mntmNewMenuItem_2);
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 663, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVerTarifas = new JButton("Visualizar todas las tarifas");
		btnVerTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
				
			}
		});
		btnVerTarifas.setBounds(469, 268, 204, 23);
		contentPane.add(btnVerTarifas);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuCliente ventanaMenuCliente = new VentanaMenuCliente(nickname, clienteController);
				ventanaMenuCliente.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(370, 268, 89, 23);
		contentPane.add(btnNewButton_1);
				
	}
	//Cargar las tarifas desde la BD
	public void cargarTabla(JTable table) {
		List<Tarifa> tarifas = clienteController.cargarTablaTarifas();
		String[] columnNames = {"Id", "Nombre", "Precio Aproximado", "Mano de obra(h)"};
		if (!tarifas.isEmpty()) {
			  model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   for (Tarifa t : tarifas) {
				   Object[] o = new Object[5];
				   o[0] = t.getIdTarifa();
				   o[1] = t.getNomTarifa();
				   o[2] = t.getPrecioAprox();
				   o[3] = t.getHoras_manodeobra();
				   model.addRow(o);
				 }
		} else {
			System.out.println("Llegan  mal las tarifas");
		}
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}

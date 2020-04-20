package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.Tarifa;

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
	private Controller loginController;
	private JTable table;
	private DefaultTableModel model;

	public VentanaVisualizarTarifas(Controller loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		ventanaVisualizarTarifas(loginController, nickname);
	}

	/**
	 * Create the frame.
	 */
	public void ventanaVisualizarTarifas(Controller loginController, String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 361);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Precio menor que");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Introduzca cantidad");
				if(isNumeric(respuesta)) {
					System.out.println("entra en isnumeric");
					int resp = Integer.parseInt(respuesta);
					Response response = loginController.filtrarTarifaPrecio(resp);
					if(response.getStatus() == Status.OK.getStatusCode()) {
						GenericType<List<Tarifa>> genericType = new GenericType<List<Tarifa>>() {};
						List<Tarifa> tarifas = response.readEntity(genericType);
						
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
						}
					}
					
				}else {
					JOptionPane.showInputDialog("No ha introducido un numero");
				}
				
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Marca");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Precio Max");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("CV");
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 637, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVerTarifas = new JButton("Visualizar todas las tarifas");
		btnVerTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
				
			}
		});
		btnVerTarifas.setBounds(364, 268, 131, 23);
		contentPane.add(btnVerTarifas);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuCliente ventanaMenuCliente = new VentanaMenuCliente(nickname, loginController);
				ventanaMenuCliente.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(258, 268, 89, 23);
		contentPane.add(btnNewButton_1);
				
	}
	//Cargar las tarifas desde la BD
	public void cargarTabla(JTable table) {
		List<Tarifa> tarifas = loginController.cargarTablaTarifas();
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

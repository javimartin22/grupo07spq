package concesionario.cliente.ventana.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
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

public class VentanaVisualizarCatalogo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller loginController;
	private JTable table;

	public VentanaVisualizarCatalogo(Controller loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		ventanaVisualizarCatalogo(loginController, nickname);
	}

	/**
	 * Create the frame.
	 */
	public void ventanaVisualizarCatalogo(Controller loginController, String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 361);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Color");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				VentanaMenuCliente ventanaMenuCliente = new VentanaMenuCliente(nickname, loginController);
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
		List<CocheConcesionario> coches = loginController.cargarTablaCochesConcesionario();
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
			System.out.println("llega mal");
		}
	}
	
	
	
	public void verInfo(String modelo, String nickname) {
		Response response = loginController.seleccionarCocheConcesionario(modelo);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			CocheConcesionario coche = response.readEntity(CocheConcesionario.class);
			VentanaInformacionCocheConcesionario vicc = new VentanaInformacionCocheConcesionario(loginController, nickname, coche);
			vicc.setVisible(true);
			dispose();
		} else {
			System.out.println("llega mal");
		}
	}
}

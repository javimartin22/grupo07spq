package concesionario.cliente.ventana.comercial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CitaComercial;

public class VentanaVisualizarCitas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table1;
	private ComercialController comercialController;
	public VentanaVisualizarCitas(ComercialController comercialcontroller,String nickname) {
		this.comercialController = comercialcontroller;
		iniciarVentanaVisualizarCitas(nickname);
	}
	
	/**
	 * Create the frame
	 * 
	 * @return
	 */
	public void iniciarVentanaVisualizarCitas(String nickname) {
		setResizable(false);
		setTitle("Fidelidad de los clientes:");
		setBounds(100, 100, 992, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 980, 274);
		getContentPane().add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);

		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table1, nickname);
			}
		});
		
		btnNewButton.setBounds(451, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController,  nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		
		btnVolve.setBounds(324, 303, 117, 29);
		getContentPane().add(btnVolve);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFiltrar.setBounds(578, 303, 117, 29);
		getContentPane().add(btnFiltrar);
	}
	
	private void cargarTabla(JTable table, String comercial) {
		List<CitaComercial> comerciales = comercialController.cargarCitaComercial(comercial);
		String[] columnNames = { "Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial"};

		if (!comercial.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);

			for (CitaComercial c : comerciales) {
				Object[] o = new Object[5];
				o[0] = c.getNombre();
				o[1] = c.getDniCliente();
				o[2] = c.getFecha();
				o[3] = c.getHora();
				o[4] = c.getComercial();
				model.addRow(o);
			}
		} else {
			//logger.error("No hay citas.");
		}

	}
}

package concesionario.cliente.ventana.mecanico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.ClienteFidelidad;

public class VentanaVisualizarFidelidad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MecanicoController mecanicoController;
	private JTable table1;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarFidelidad.class);
	static int iteration = 0;

	public VentanaVisualizarFidelidad(MecanicoController mecanicoController, String nickname) {
		this.mecanicoController = mecanicoController;
		iniciarVentanaVisualizarFidelidad(nickname);
	}

	/**
	 * Create the frame
	 * 
	 * @return
	 */

	public void iniciarVentanaVisualizarFidelidad(String nickname) {
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
				cargarTabla(table1);
			}
		});
		btnNewButton.setBounds(565, 303, 117, 29);
		getContentPane().add(btnNewButton);

		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(mecanicoController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnVolve.setBounds(294, 304, 117, 29);
		getContentPane().add(btnVolve);
	}

	private void cargarTabla(JTable table) {
		List<ClienteFidelidad> cliente = mecanicoController.cargarClienteFidelidad();
		String[] columnNames = { "DNI", "Veces en el taller" };

		if (!cliente.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);

			for (ClienteFidelidad c : cliente) {
				Object[] o = new Object[2];
				o[0] = c.getDni();
				o[1] = c.getFidelidad();
				model.addRow(o);
			}
		} else {
			logger.error("No hay clientes.");
		}

	}

}
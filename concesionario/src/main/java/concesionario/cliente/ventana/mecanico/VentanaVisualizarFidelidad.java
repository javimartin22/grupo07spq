package concesionario.cliente.ventana.mecanico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import concesionario.cliente.controller.MecanicoController;

public class VentanaVisualizarFidelidad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MecanicoController mecanicoController;
	private JTable table1;
	
	public VentanaVisualizarFidelidad(MecanicoController mecanicoController, String nickname) {
		this.mecanicoController = mecanicoController;
		iniciarVentanaVisualizarFidelidad(nickname);
	}
	
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
				//cargarTabla(table1);
			}
		});
		btnNewButton.setBounds(565, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * metodo para volcer a pa ppagina anterior
				 */
			}
		});
		btnVolve.setBounds(294, 304, 117, 29);
		getContentPane().add(btnVolve);
	}
	
	/*private void cargarTabla(JTable table) {
		List<ClienteFidelidad> cliente = loginController.cargarClientesFieles();
		String[] columnNames = {"DNI", "Veces en el taller"};
		
		if (!cliente.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (ClienteFidelidad c : cliente) {
				   Object[] o = new Object[2];
				   o[0] = c.getDni();
				   o[1] = c.getVeces();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
		
	}*/
	
}

package concesionario.cliente.ventana.mecanico;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheTaller;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaVisualizarCochesTaller extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller loginController;
	private JTable table_1;
	
	
	public VentanaVisualizarCochesTaller(Controller loginController, String nickname){
		this.loginController = loginController;
		iniciarVentanaVisualizarCochesTaller(nickname);
	}
	
	public void iniciarVentanaVisualizarCochesTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 400);
		setLocationRelativeTo(null);
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
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(loginController, nickname);
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
		btnNewButton_1.setBounds(504, 323, 150, 27);
		contentPane.add(btnNewButton_1);
		
		
	}
	
	private void cargarTabla(JTable table) {
		List<CocheTaller> coches = loginController.cargarTablaCocheTaller();
		
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
				   o[6] = traducirEstado(c.getEstado());
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
	}
	
	public void eliminarCocheTaller(String matricula) {
		Response response = loginController.deleteCocheTaller(matricula); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(contentPane, "El coche ha salido del taller.");
		} else {
			System.out.println("fallo");
		}
	}
	
	public String traducirEstado(int estado) {
		String estad = "";
		switch (estado) {
		case 0:
			estad = "Sin Empezar";
			break;
		case 1:
			estad = "En proceso";
			break;
		case 2:
			estad = "Terminado";
			break;
		}
		return estad;
	}
	
	public void cambiarEstado(String matricula) {
		Response response = loginController.seleccionarCocheTaller(matricula);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			CocheTaller coche = response.readEntity(CocheTaller.class);
			int estado = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el estado del vehiculo (0: sin comenzar; 1: en proceso; 2: terminado)"));
			Response response1 = loginController.cambiarEstadoCocheTaller(coche, estado); //estoy aqui
			if (response1.getStatus() == Status.OK.getStatusCode()) {
				JOptionPane.showMessageDialog(contentPane, "El estado ha sido modificado.");
			} else {
				System.out.println("fallo");
			}
		} else {
			System.out.println("llega mal");
		}
	}
}

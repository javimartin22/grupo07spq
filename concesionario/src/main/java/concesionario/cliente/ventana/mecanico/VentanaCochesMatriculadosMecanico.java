package concesionario.cliente.ventana.mecanico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheMatriculado;

public class VentanaCochesMatriculadosMecanico extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Controller loginController;
	
	public VentanaCochesMatriculadosMecanico(Controller loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaCochesMatriculadosMecanico(nickname);
	}
	
	private void iniciarVentanaCochesMatriculadosMecanico(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Coches Matriculados");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmc = new VentanaMenuMecanico(loginController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(709, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 980, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnVer.setBounds(838, 299, 112, 29);
		getContentPane().add(btnVer);
		
	}
	
	public void cargarTabla(JTable table) {
		System.out.println("HOLA");
		List<CocheMatriculado> cochesMatric = loginController.cargarCochesMatriculados();
		
		String[] columnNames = {"Matricula", "Marca", "Modelo", "Anio Matriculacion", "Revisiones", "CV", "Nombre Propietario", "Nº Puertas", "Color"};
		
		if (!cochesMatric.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheMatriculado cm : cochesMatric) {
				   Object[] o = new Object[9];
				   o[0] = cm.getMatricula();
				   o[1] = cm.getMarca();
				   o[2] = cm.getModelo();
				   o[3] = cm.getAnyoMatriculacion();
				   o[4] = cm.getRevisiones();
				   o[5] = cm.getCv();
				   o[6] = cm.getNombrePropietario();
				   o[7] = cm.getNumPuertas();
				   o[8] = cm.getColor();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
		
	}
}

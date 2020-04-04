package concesionario.cliente.ventana;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Empleado;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaEmpleados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private LoginController loginController;
	
	public VentanaEmpleados(LoginController loginController, String nickname){
		this.loginController = loginController;
		iniciarVentanaEmpleados(nickname);
	}
	
	public void  iniciarVentanaEmpleados(String nickname) {
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setTitle("Gestión de empleados");
		getContentPane().setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(847, 299, 117, 29);
		getContentPane().add(btnEliminar);
		
		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(316, 299, 112, 29);
		getContentPane().add(btnVer);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnCargar.setBounds(145, 299, 117, 29);
		getContentPane().add(btnCargar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuAdmin vma = new VentanaMenuAdmin(loginController, nickname);
				vma.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(16, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 980, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mecanico", "Comercial", "Departamento Compras"}));
		comboBox.setBounds(485, 300, 168, 27);
		getContentPane().add(comboBox);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipo = comboBox.getSelectedIndex();
				registrar(tipo, nickname);
			}
		});
		btnAgregar.setBounds(689, 299, 112, 29);
		getContentPane().add(btnAgregar);
		
	}
	

		
	public void cargarTabla(JTable table) {
		List<Empleado> empleados = loginController.cargarTablaEmpleado();
		
		String[] columnNames = {"Nickname", "Nombre", "Apellido", "DNI", "Tipo Empleado"};
		
		if (!empleados.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (Empleado e : empleados) {
				   Object[] o = new Object[7];
				   o[0] = e.getNickname();
				   o[1] = e.getNombre();
				   o[2] = e.getApellido();
				   o[3] = e.getDNI();
				   o[4] = cambioTipo(e.getTipoEmpleado());
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
	}
		
	private void registrar(int tipo, String nickname) {
		switch (tipo) {
		case 0:
			VentanaRegistroMecanico ventanaRegistrarMecanico = new VentanaRegistroMecanico(loginController, nickname);
			ventanaRegistrarMecanico.setVisible(true);
			dispose();
			break;
		case 1: 
			VentanaRegistroComercial ventanaComercial = new VentanaRegistroComercial(loginController, nickname);
			ventanaComercial.setVisible(true);
			dispose();		
			break;
		case 2:
			VentanaRegistroDepartamentoCompras ventanaCompras = new VentanaRegistroDepartamentoCompras(loginController, nickname);
			ventanaCompras.setVisible(true);
			dispose();		
			break;
		}
	}
	
	public String cambioTipo(int tipoEmpleado) {
		String tipo = "";
		switch (tipoEmpleado) {
		case 0:
			tipo = "Mecanico";
			break;
		case 1: 
			tipo = "Comercial";
			break;
		case 2:
			tipo = "Departamento Compras";
			break;
		}
		return tipo;
	}
}

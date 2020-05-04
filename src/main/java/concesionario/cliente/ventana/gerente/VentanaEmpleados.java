package concesionario.cliente.ventana.gerente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Empleado;

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
	private GerenteController gerenteController;
	final Logger logger = LoggerFactory.getLogger(VentanaEmpleados.class);
	static int iteration = 0;
	
	public VentanaEmpleados(GerenteController gerenteController, String nickname){
		this.gerenteController = gerenteController;
		iniciarVentanaEmpleados(nickname);
	}
	
	public void  iniciarVentanaEmpleados(String nickname) {
		setAutoRequestFocus(false);
		setBounds(100, 100, 1010, 376);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Gestión de empleados");
		getContentPane().setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String tipo = (String) table.getModel().getValueAt(fila, 4);
				String nombre = (String) table.getModel().getValueAt(fila, 0);
				eliminarEmpleado(tipo, nickname, nombre);
			}
		});
		btnEliminar.setBounds(847, 299, 117, 29);
		getContentPane().add(btnEliminar);
		
		JButton btnVer = new JButton("Ver Info");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String tipo = (String) table.getModel().getValueAt(fila, 4);
				String nombre = (String) table.getModel().getValueAt(fila, 0);
				verInfo(tipo, nickname, nombre);
			}
		});
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
				VentanaMenuAdmin vma = new VentanaMenuAdmin(gerenteController, nickname);
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
		List<Empleado> empleados = gerenteController.cargarTablaEmpleado();
		
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
				   o[4] = gerenteController.cambioTipo(e.getTipoEmpleado());
				   model.addRow(o);
				 }
		} else {
			logger.error("No hay ningun empleado.");
		}
	}
		
	private void registrar(int tipo, String nickname) {
		switch (tipo) {
		case 0:
			VentanaRegistroMecanico ventanaRegistrarMecanico = new VentanaRegistroMecanico(gerenteController, nickname);
			ventanaRegistrarMecanico.setVisible(true);
			dispose();
			break;
		case 1: 
			VentanaRegistroComercial ventanaComercial = new VentanaRegistroComercial(gerenteController, nickname);
			ventanaComercial.setVisible(true);
			dispose();		
			break;
		case 2:
			VentanaRegistroDepartamentoCompras ventanaCompras = new VentanaRegistroDepartamentoCompras(gerenteController, nickname);
			ventanaCompras.setVisible(true);
			dispose();		
			break;
		}
	}
	
	public void verInfo(String tipo, String nickname, String nombre) {
		switch (tipo) {
		case "Mecanico":
			if (gerenteController.seleccionarMecanico(nombre) != null) {
				VentanaInformacionMecanico vim = new VentanaInformacionMecanico(gerenteController, nickname, gerenteController.seleccionarMecanico(nombre));
				vim.setVisible(true);
				dispose();
			} else {
				logger.error("El mecanico seleccionado no existe.");
			}
			break;
		case "Comercial":
			if (gerenteController.seleccionarComercial(nombre) != null) {
				VentanaInformacionComercial vic = new VentanaInformacionComercial(gerenteController, gerenteController.seleccionarComercial(nombre), nickname);
				vic.setVisible(true);
				dispose();
			} else {
				logger.error("El comercial seleccionado no existe.");
			}
			break;
		case "Departamento Compras":
			if (gerenteController.seleccionarDepartamentoCompras(nombre) != null) {
				VentanaInformacionDepartamentoCompras vidc = new VentanaInformacionDepartamentoCompras(gerenteController, gerenteController.seleccionarDepartamentoCompras(nombre), nickname);
				vidc.setVisible(true);
				dispose();
			} else {
				logger.error("El comercial seleccionado no existe.");
			}
			break;
		}
	}
	
	public void eliminarEmpleado(String tipo, String nickname, String nombre) {
		switch (tipo) {
		case "Mecanico":
			if (gerenteController.eliminarMecanico(nombre)) {
				JOptionPane.showMessageDialog(this, "Mecanico elimindado");
			} else {
				logger.error("El mecanico seleccionado no existe.");
			}
			break;
		case "Comercial":
			if (gerenteController.eliminarComercial(nombre)) {
				JOptionPane.showMessageDialog(this, "Comercial elimindado");
			} else {
				logger.error("El comercial seleccionado no existe.");
			}
			break;
		case "Departamento Compras":
			if (gerenteController.eliminarDepartamentoCompras(nombre)) {
				JOptionPane.showMessageDialog(this, "Departamento Compras elimindado");
			} else {
				logger.error("El empleado del Departameto de Compras seleccionado no existe.");
			}
			break;
		}
	}
}

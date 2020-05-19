package concesionario.cliente.ventana.departamentoCompras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.SolicitudCompra;

/**
 * VentanaVisualizarSolicitud (Ventana para visualizar las Solicitudes de Compra).
 */
public class VentanaVisualizarSolicitudCompra extends JFrame {

	


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DepartmentoComprasController departamentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarSolicitudCompra.class);
	static int iteration = 0;

	/**
	 * Constructor de la VentanaVisualizarSolicitudCompra
	 * @param departmentoComprasController (Controlador de las Ventanas de Departamento de Compras)
	 * @param nickname (Nickname del departamento de compras)
	 */
	public VentanaVisualizarSolicitudCompra(DepartmentoComprasController departmentoComprasController, String nickname) {
		setTitle("Solicitudes de compra");
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaHerramientasTaller(nickname);
	}

	/**
	 * Inicializador del JFrame de la VentanaVisualizarSolicitudCompra
	 * @param nickname (Nickname del departamento de compras)
	 */
	public void  iniciarVentanaHerramientasTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 361);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 20, 675, 235);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuDepartamentoCompras vmdc = new VentanaMenuDepartamentoCompras(departamentoComprasController, nickname);
				vmdc.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(320, 278, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Todas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(447, 278, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resp = JOptionPane.showConfirmDialog(contentPane, "¿Esta seguro de que quiere eliminarlo?");
				switch (resp) {
				case 0:
					int fila = tabla.getSelectedRow();
					eliminarSolicitud((String) tabla.getModel().getValueAt(fila, 0));
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
		btnEliminar.setBounds(574, 278, 117, 29);
		contentPane.add(btnEliminar);
	}
	
	/**
	 * Metodo para mostrar la tabla completa de las Solicitudes de Compra
	 * @param tabla (Tabla de la ventana).
	 */	
	public void cargarTabla(JTable tabla) {
		List<SolicitudCompra> solicitud = departamentoComprasController.cargarSolicitud();
		String[] columnNames = {"Codigo", "Nombre", "Tipo", "Unidades"};
		
		if (!solicitud.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (SolicitudCompra p : solicitud) {
				Object[] o = new Object[7];
				o[0] = p.getCodigo();
				o[1] = p.getNombre();
				o[2] = p.getTipo();
				o[3] = p.getUnidades();
				model.addRow(o);
			}
		} else {
		}
	}
		

	/**
	 * Metodo para eliminar una Solicitud de Compra de la BD
	 * @param sol (Identificador de la solicitud de compra).
	 */
	public void eliminarSolicitud(String sol) {
		if (departamentoComprasController.deleteSolicitud(sol)) {
			JOptionPane.showMessageDialog(contentPane, "Solicitud eliminada");
			logger.info("La solicitud ha sido eliminada");
		} else {
			logger.error("Error al eliminar la solicitud");
		}
	}
	

	




}

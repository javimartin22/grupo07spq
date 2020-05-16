package concesionario.cliente.ventana.gerente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Tarifa;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
/**
* Clase para la visualizacion y edicion de las tarifas
*/
public class VentanaVeryEditarTarifas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GerenteController gerenteController;
	private JTable table;
	private DefaultTableModel model;
	final Logger logger = LoggerFactory.getLogger(VentanaVeryEditarTarifas.class);
	static int iteration = 0;

	/**
	 * Constructor de la clase VentanaVeryEditarTarifas.
	 * @param gerenteController (Controlador de las clases del Gerente
	 * @param nickname (Nickname del Gerente)
	 */
	public VentanaVeryEditarTarifas(GerenteController gerenteController, String nickname) {
		setResizable(false);
		this.gerenteController = gerenteController;
		inicializarVeryEditarTarifas(nickname);
	}

	/**
	 * Inicializar el JFrame de la Ventana VeryEditarTarifas
	 */
	public void inicializarVeryEditarTarifas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 339);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 637, 242);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			   private static final long serialVersionUID = 1L;
		       public boolean isCellEditable(int row, int column) {                
		    	   if(column == 0) {
		    		   return false;
		    	   } else {
		    		   return true;
		    	   }
		       };
		};
		scrollPane.setViewportView(table);
		
		JButton btnVerTarifas = new JButton("Visualizar Tarifas");
		btnVerTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
				
			}
		});
		btnVerTarifas.setBounds(380, 268, 137, 23);
		contentPane.add(btnVerTarifas);
		
		JButton btnEditar= new JButton("Editar tarifa");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
					String idTarifa = table.getModel().getValueAt(row, 0).toString();
					String nomTarifa = table.getModel().getValueAt(row, 1).toString();
					int precioAprox = Integer.parseInt(table.getModel().getValueAt(row, 2).toString());
					int horas_manodeobra= Integer.parseInt(table.getModel().getValueAt(row, 3).toString());
					
					Tarifa editada = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
					//Eliminar tarifa previa (No se puede modificar el ID, esta bloqueado)
					if (gerenteController.eliminarTarifa(idTarifa)) {
						if(gerenteController.registroTarifa(editada)) {
							logger.info("Tarifa editada correctamente.");
							JOptionPane.showMessageDialog(null, "Tarifa editada correctamente");
						}
					}else {
						logger.error("Ha habido un error al editar la tarifa");
					}
			}
		});
		
		btnEditar.setBounds(527, 268, 120, 23);
		contentPane.add(btnEditar);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuAdmin ventanaMenuAdmin = new VentanaMenuAdmin(gerenteController, nickname);
				ventanaMenuAdmin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(281, 268, 89, 23);
		contentPane.add(btnNewButton_1);
				
	}
	
	//Cargar las tarifas desde la BD
	/**
	* Metodo para cargar la tabla de todas las tarifas que se reciben del servidor
	* @param table JTable en la que se cargan las columnas y datos de las tarifas
	*/
	public void cargarTabla(JTable table) {
		List<Tarifa> tarifas = gerenteController.cargarTablaTarifas();
		String[] columnNames = {"Id", "Nombre", "Precio Medio", "Horas Mano de Obra"};
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
			logger.error("Llegan  mal las tarifas");
		}
	}
}

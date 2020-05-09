package concesionario.cliente.ventana.mecanico;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.CitaTaller;

public class VentanaVisualizarCitasMecanico extends JFrame {
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table1;
	private MecanicoController mecanicoController;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarCitasMecanico.class);
	static int iteration = 0;
	
	public VentanaVisualizarCitasMecanico(MecanicoController mecanicocontroller, String nickname) {
		this.mecanicoController = mecanicocontroller;
		iniciarVentanaVisualizarCitasMecanico(nickname);
	}

	/**
	 * Create the frame
	 * 
	 * @return
	 */
	
	private void iniciarVentanaVisualizarCitasMecanico(String nickname) {
		setResizable(false);
		setTitle("Citas:");
		setBounds(100, 100, 992, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 980, 274);
		getContentPane().add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtrar por fecha");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Citas hoy");
		mntmNewMenuItem.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate()) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";				
				cargarTablaFiltro(table1, nickname, fechaCompleta);
				logger.info("Citas de hoy cargadas.");
			}
		}); 
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Citas mañana");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate() + 1) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";				
				cargarTablaFiltro(table1, nickname, fechaCompleta);
				logger.info("Citas de manaña cargadas.");
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Otro día");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String fecha;
				 fecha = JOptionPane.showInputDialog("¿Qué fecha quieres? (ejem. dd-mm-yyyy)");
				 
				 if (mecanicoController.validarFecha(fecha)) {
					 cargarTablaFiltro(table1, nickname, fecha);
					 logger.info("Citas de " + fecha + "cargadas");
				} else {
					fecha = JOptionPane.showInputDialog("Por favor, escribe la fecha en el siguiente formato (ejem. dd-mm-yyyy)");
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate()-1) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";
				String nickFecha = nickname + ";" + fechaCompleta;
				mecanicoController.deleteCitaMecanico(nickFecha);
				cargarTabla(table1, nickname);
				logger.info("Tabla cargada");
			}
		});
		
		btnNewButton.setBounds(557, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(mecanicoController,  nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		
		btnVolve.setBounds(339, 303, 117, 29);
		getContentPane().add(btnVolve);
	}
	
	public void cargarTabla(JTable table, String mecanico) {
		List<CitaTaller> mecanicos = mecanicoController.cargarCitaMecanico(mecanico);
		String[] columnNames = {"Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial", "Problema"};
		
		if (!mecanico.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for(CitaTaller c : mecanicos) {
				Object[] o = new Object[6];
				o[0] = c.getNombre();
				o[1] = c.getDniCliente();
				o[2] = c.getFecha();
				o[3] = c.getHora();
				o[4] = c.getComercial();
				o[5] = c.getProblema();
				model.addRow(o);
			}
		} else {
			logger.error("No hay citas");
		}
		
	}
	
	public void cargarTablaFiltro(JTable table, String mecanico, String fecha) {
		String filtro = mecanico + ";" + fecha;
		List<CitaTaller> mecanicos = mecanicoController.filtrarCitaMecanico(filtro);
		String[] columnNames = {"Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial", "Problema"};
		if (!fecha.isEmpty() && !mecanico.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);

			for(CitaTaller c : mecanicos) {
				Object[] o = new Object[6];
				o[0] = c.getNombre();
				o[1] = c.getDniCliente();
				o[2] = c.getFecha();
				o[3] = c.getHora();
				o[4] = c.getComercial();
				o[5] = c.getProblema();
				model.addRow(o);
			}
		} else {
			logger.error("No hay citas este día.");
		}
	} 
	
	
	
	
}
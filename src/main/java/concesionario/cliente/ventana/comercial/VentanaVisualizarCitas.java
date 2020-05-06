package concesionario.cliente.ventana.comercial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CitaComercial;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Citas mañana");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String fechaCompleta =  Integer.toString(date.getDate() + 1) +"-"+ Integer.toString(date.getMonth() + 1) +"-"+ Integer.toString(1900 + date.getYear())+"";				
				cargarTablaFiltro(table1, nickname, fechaCompleta);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Otro día");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String fecha;
				 fecha = JOptionPane.showInputDialog("¿Qué fecha quieres? (ejem. dd-mm-yyyy)");
				 
				 if (validarFecha(fecha)) {
					 cargarTablaFiltro(table1, nickname, fecha);
				} else {
					fecha = JOptionPane.showInputDialog("Por favor, escribe la fecha en el siguiente formato (ejem. dd-mm-yyyy)");
				}
				 
				 
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table1, nickname);
			}
		});
		
		btnNewButton.setBounds(557, 303, 117, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnVolve = new JButton("Volver");
		btnVolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(comercialController,  nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		
		btnVolve.setBounds(339, 303, 117, 29);
		getContentPane().add(btnVolve);
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
	
	public void cargarTablaFiltro(JTable table, String comercial, String fecha) {
		String filtro = comercial + ";" + fecha;
		List<CitaComercial> comerciales = comercialController.filtrarCitaComercial(filtro);
		String[] columnNames = { "Nombre", "Dni del Cliente", "Fecha", "Hora", "Comercial"};
		if (!fecha.isEmpty() && !comercial.isEmpty()) {
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
			//logger.error("No hay citas este día.");
		}
	} 
	
	public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

package concesionario.cliente.ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Pieza;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaPiezasMecanico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private LoginController loginController;
	
	public VentanaPiezasMecanico(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaPiezas(nickname);
	}
	
	public void iniciarVentanaPiezas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 100, 560, 334);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 20, 522, 235);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(loginController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(162, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Piezas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(291, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnCogerPiezas = new JButton("Coger Piezas");
		btnCogerPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCogerPiezas.setBounds(421, 267, 117, 29);
		contentPane.add(btnCogerPiezas);
	}
	
		public void cargarTabla(JTable table) {
			List<Pieza> piezas = loginController.cargarPiezas();
			String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
			
			if (!piezas.isEmpty()) {
				 DefaultTableModel model = new DefaultTableModel();
				   table.setModel(model);
				   model.setColumnIdentifiers(columnNames);
				   
				   for (Pieza p : piezas) {
					   Object[] o = new Object[7];
					   o[0] = p.getCodigo();
					   o[1] = p.getNombre();
					   o[2] = p.getUnidades();
					   o[3] = p.getUbicacion();
					   model.addRow(o);
					 }
			} else {
				System.out.println("llega mal");
			}
		}
		
}










package concesionario.cliente.ventana;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Pieza;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


public class VentanaPiezasUtilizadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private LoginController loginController;

	public VentanaPiezasUtilizadas(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaPiezasUtilizadas(nickname);
	}

	
	public void  iniciarVentanaPiezasUtilizadas(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 334);
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
				VentanaMenuDepartamentoCompras vmdc = new VentanaMenuDepartamentoCompras(loginController, nickname);
				vmdc.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(16, 267, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnCargarPiezas = new JButton("Cargar Todas");
		btnCargarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(tabla);
			}
		});
		btnCargarPiezas.setBounds(145, 267, 117, 29);
		contentPane.add(btnCargarPiezas);
		
		JButton btnNewButton = new JButton("Añadir Unidades");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = (String) tabla.getModel().getValueAt(tabla.getSelectedRow(), 0);
				Response response = loginController.seleccionarPiezaUtilizada(codigo);
				if (response.getStatus() == Status.OK.getStatusCode()) {
					Pieza pieza = response.readEntity(Pieza.class);
					anyadirUnidades(pieza);
				} else {
					System.out.println("llega mal");
				} 
			}
		});
		btnNewButton.setBounds(558, 267, 133, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cargar Utilizadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaUtilizadas(tabla);
			}
		});
		btnNewButton_1.setBounds(274, 267, 143, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistoPiezas vrp = new VentanaRegistoPiezas(loginController, nickname);
				vrp.setVisible(true);
				dispose();
			}
		});
		btnRegistrar.setBounds(429, 267, 117, 29);
		contentPane.add(btnRegistrar);
	}
	
		
	public void cargarTabla(JTable tabla) {
		List<Pieza> piezas = loginController.cargarPiezas();
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
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
		
	private void anyadirUnidades(Pieza pieza) {
		String u = JOptionPane.showInputDialog("¿Cuantas unidades desea suministrar?");
		int unidades = Integer.parseInt(u);
		Response response = loginController.registroPiezaUtilizada(pieza, unidades); 
		if (response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(contentPane, "Unidades anyadidas correctamente");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Error al anyadir unidades.");
		}
	}
		
	public void cargarTablaUtilizadas(JTable table) {
		List<Pieza> piezas = loginController.cargarPiezasUtilizadas();
		String[] columnNames = {"Codigo", "Nombre", "Unidades", "Ubicacion"};
		
		if (!piezas.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			tabla.setModel(model);
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

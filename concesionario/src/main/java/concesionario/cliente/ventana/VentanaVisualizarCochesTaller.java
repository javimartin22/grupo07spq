package concesionario.cliente.ventana;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.LoginController;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVisualizarCochesTaller extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginController loginController;
	private JTable table_1;
	
	
	public VentanaVisualizarCochesTaller(LoginController loginController, String nickname){
		this.loginController = loginController;
		iniciarVentanaVisualizarCochesTaller(nickname);
	}
	
	public void iniciarVentanaVisualizarCochesTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 18, 620, 294);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table_1);
			}
		});
		btnCargar.setBounds(504, 324, 117, 29);
		contentPane.add(btnCargar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(362, 324, 117, 29);
		contentPane.add(btnRegresar);
		
		
	}
	
	private void cargarTabla(JTable table) {
		
	}
}

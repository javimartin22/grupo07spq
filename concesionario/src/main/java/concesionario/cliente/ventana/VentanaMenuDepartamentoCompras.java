package concesionario.cliente.ventana;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.LoginController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuDepartamentoCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginController loginController;
	
	public VentanaMenuDepartamentoCompras(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaMenuDepartamentoCompras(nickname);
	}

	
	public void iniciarVentanaMenuDepartamentoCompras(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Piezas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasUtilizadas vpu = new VentanaPiezasUtilizadas(loginController, nickname);
				vpu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(158, 75, 132, 29);
		contentPane.add(btnNewButton);
	}

}

package concesionario.ventana;
import javax.swing.JFrame;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JButton;


//para que hayan cambios para el commit
public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNombreUsuario;
	private JTextField textContrasenya;
	
	public VentanaLogin() {
		this.setTitle("Iniciar sesión");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480,360);
		this.setResizable(true);
		
		//panel general
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		//label de usuario
		JLabel labelUsuario = new JLabel("Nombre de Usuario:");
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUsuario.setBounds(59, 94, 158, 14);
		panel.add(labelUsuario);
		
		//label de contraseña
		JLabel labelContrasenya = new JLabel("Contraseña:");
		labelContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelContrasenya.setBounds(59, 163, 117, 14);
		panel.add(labelContrasenya);
		
		//caja de texto donde escribir el nombre de usuario
		textNombreUsuario = new JTextField();
		textNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombreUsuario.setBounds(200, 160, 198, 20);
		panel.add(textNombreUsuario);
		textNombreUsuario.setColumns(10);
		
		//caja de texto donde escribir la contraseña
		textContrasenya = new JTextField();
		textContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textContrasenya.setBounds(200, 93, 198, 20);
		panel.add(textContrasenya);
		textContrasenya.setColumns(10);

		//para que también se pueda hacer con el enter
		textContrasenya.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int tecla = e.getKeyCode();
				if(tecla == 10 ) {
					iniciarSesion();
				}
			}
		});
		
		//boton de aceptar para iniciar sesión
		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.setBounds(175, 244, 89, 23);
		panel.add(buttonAceptar);
		
		
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//añadirla nueva ventana
			}
		});
		
	}
	
	private void iniciarSesion() {
		
	}
	
	
	
	public static void abrirVentanaLogin() {
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
		ventanaLogin.setSize(480,360);
		ventanaLogin.setLocationRelativeTo(null);
		ventanaLogin.setVisible(true);
	}
}
package concesionario.cliente.ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import concesionario.servidor.BaseDatos.BD;
import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.Usuario;

import java.awt.Color;

public class VentanaMenuCliente extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonSalir;
	
	
	
	public static void main(String nickname) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuCliente ventanaMenuCliente = new VentanaMenuCliente(nickname);
					ventanaMenuCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaMenuCliente(String nickname) {
		this.setTitle("Menu del cliente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480,282);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//boton de salir que te lleva a la ventana de VentanaLogin
		buttonSalir = new JButton("Salir");
//		buttonSalir.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				VentanaLogin ventana = new VentanaLogin();
//				ventana.setVisible(true);
//				dispose();
//			}
//		});
		 buttonSalir = new JButton("Salir");
		 /*
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin ventana = new VentanaLogin();
				ventana.setVisible(true);
				dispose();
			}
		});
		*/
		 
		buttonSalir.setBounds(220, 209, 89, 23);
		panel.add(buttonSalir);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSeleccion = new JMenu("Configuración");
		menuBar.add(mnSeleccion);
		
		JMenuItem mntmNicname = new JMenuItem("Cambiar Nickname");
		mntmNicname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cambiarNickname(nickname);
				
			}
		});
		mnSeleccion.add(mntmNicname);
		
		JMenuItem mntmContrasenya = new JMenuItem("Cambiar Contraseña");
		mntmContrasenya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cambiarContrasenya(nickname);
			}
		});
		mnSeleccion.add(mntmContrasenya);
	}
	
	
//	private void cambiarNickname(String nickname) {
//		Usuario user = BD.usuarioSelect(st, nickname);
//		Cliente client = BD.clienteSelect(st, nickname);
//		String nombre = JOptionPane.showInputDialog("Introduzca el nuevo nickname:");
//		BD.clientesDelete(st, client.getDNI());
//		BD.usuariosDelete(st, nickname);
//		BD.clientesInsert(st, client.getDNI(), nombre, client.getContrasenya(), client.getNombre(), client.getApellido(), client.getSexo(), client.getEmail(), client.getCiudad(), client.getCodigoPostal(), client.getDireccion(), client.getNumeroTelefono());
//		BD.usuariosInsert(st, nombre, user.getContrasenya(), 3);
//	}
//	
//	private void cambiarContrasenya(String nickname) {
//		Usuario user = BD.usuarioSelect(st, nickname);
//		Cliente client = BD.clienteSelect(st, nickname);
//		String contrasenya = JOptionPane.showInputDialog("Introduzca la nueva contrasenya:");
//		BD.clientesDelete(st, client.getDNI());
//		BD.usuariosDelete(st, nickname);
//		BD.clientesInsert(st, client.getDNI(), client.getNickname(), contrasenya, client.getNombre(), client.getApellido(), client.getSexo(), client.getEmail(), client.getCiudad(), client.getCodigoPostal(), client.getDireccion(), client.getNumeroTelefono());
//		BD.usuariosInsert(st, user.getNickname(), contrasenya, 3);
//	}
	
	
	private void cambiarContrasenya(String nickname) {
//		Usuario user = BD.usuarioSelect(st, nickname);
//		Cliente client = BD.clienteSelect(st, nickname);
//		String contrasenya = JOptionPane.showInputDialog("Introduzca la nueva contrasenya:");
//		BD.clientesDelete(st, client.getDNI());
//		BD.usuariosDelete(st, nickname);
//		BD.clientesInsert(st, client.getDNI(), client.getNickname(), contrasenya, client.getNombre(), client.getApellido(), client.getSexo(), client.getEmail(), client.getCiudad(), client.getCodigoPostal(), client.getDireccion(), client.getNumeroTelefono());
//		BD.usuariosInsert(st, user.getNickname(), contrasenya, 3);
	}
	
	public JButton getButtonSalir() {
		return buttonSalir;
	}


	public void setButtonSalir(JButton buttonSalir) {
		this.buttonSalir = buttonSalir;
	}

}

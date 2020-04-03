package concesionario.cliente.ventana;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//import concesionario.cliente.ventana.VentanaLogin;

import javax.swing.JButton;


public class VentanaMenuMecanico extends JFrame {

	private Connection con;
	private Statement st;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String nickname) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuMecanico ventanaMenuMecanico = new VentanaMenuMecanico(nickname);
					ventanaMenuMecanico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaMenuMecanico(String nickname){
		this.setTitle("Menu del mecanico");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,282);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//con = BD.initBD("Taller");
		//st = BD.usarCrearTablasBD(con);
		
		//panel donde van todos los componentes
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//label de arriba a la derecha que solo pone el nombre del cliente
		JLabel nombreMecanico = new JLabel("Bienvenid@ " + nickname.toUpperCase());
		nombreMecanico.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreMecanico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreMecanico.setBounds(178, 11, 250, 33);
		panel.add(nombreMecanico);
		
		//boton para anadir piezas, si clicas te lleva a una nueva ventana donde puedes realizar esta actividad
		JButton anadirPieza = new JButton("Ver Piezas");
		anadirPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ventana donde se registran las piezas utilizadas
				//VentanaPiezas ventanaPiezas = new VentanaPiezas(nickname);
				//ventanaPiezas.setVisible(true);
				dispose();
			}
		});
		anadirPieza.setBounds(104, 71, 201, 23);
		panel.add(anadirPieza);
		
		//boton para poder ver el historial de los vehiculos que han pasado por el taller 
		JButton verHistoial = new JButton("Ver historial de los coches");
		verHistoial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hace falta instanciar la ventana donde se vera el hisorial
			}
		});
		verHistoial.setBounds(104, 161, 201, 23);
		panel.add(verHistoial);
		
		//boton para poder registrar los vehiculos que pasan por el taller
		JButton registarVehiculo = new JButton("Registrar vehiculo del taller");
		registarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hace falta instanciar la ventana donde se puedan registrar los vehiculos 
			}
		});
		registarVehiculo.setBounds(104, 118, 201, 23);
		panel.add(registarVehiculo);
		
		//boton de salir que te lleva a la ventana de VentanaLogin donde se inicia la sesion
		JButton buttonSalir = new JButton("Salir");
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ventana para iniciar serion
				//VentanaLogin ventana = new VentanaLogin();
				//ventana.setVisible(true);
				dispose();
			}
		});
		buttonSalir.setBounds(162, 208, 89, 23);
		panel.add(buttonSalir);
		
		//Menu para cambiar el nickane o la contrasenia
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSeleccion = new JMenu("Configuración");
		menuBar.add(mnSeleccion);
		
		
		JMenuItem mntmNicname = new JMenuItem("Cambiar Nickname");
		mntmNicname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamada a metodo para cambiar el nicname
				cambiarNickname(nickname);
				
			}
		});
		mnSeleccion.add(mntmNicname);
		
		JMenuItem mntmContrasenya = new JMenuItem("Cambiar Contraseña");
		mntmContrasenya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamada a metodo para cambiar la contrasenia
				cambiarContrasenya(nickname);
			}
		});
		mnSeleccion.add(mntmContrasenya);
		
	}
	
	//metodo para cambiar el nickname
	private void cambiarNickname(String nickname) {
		//se le pasa un nickname donde se busca al mecanico en la bd de las tablas usuarios, mecanicos y empleados
		/*Usuario user = BD.usuarioSelect(st, nickname);
		Mecanico mecanico = BD.mecanicoSelect(st, nickname);
		Empleado empleado = BD.empleadoSelect(st, nickname);
		//si el nickname existe, se le muestra un JOptionPane donde introduce el nuevo nickname
		if (mecanico != null) {
			String nombre = JOptionPane.showInputDialog("Introduzca el nuevo nickname:");
			BD.mecanicosDelete(st, mecanico.getNickname());
			BD.usuariosDelete(st, user.getNickname());
			BD.empleadosDelete(st, nickname);
			BD.mecanicosInsert(st, mecanico.getDNI(), nombre, mecanico.getContrasenya(), mecanico.getNombre(), mecanico.getApellido(), mecanico.getSexo(), mecanico.getEmail(), mecanico.getCiudad(), mecanico.getCodigoPostal(), mecanico.getDireccion(), mecanico.getNumeroTelefono(), mecanico.getNSS(), mecanico.getNumeroCuenta(), mecanico.getSueldo(), mecanico.getHoras(), mecanico.getCoches());
			BD.usuariosInsert(st, nombre, user.getContrasenya(), 3);
			BD.empleadosInsert(st, empleado.getDNI(), nombre, empleado.getContrasenya(), empleado.getNombre(), empleado.getApellido(), empleado.getSexo(), empleado.getEmail(), empleado.getCiudad(), empleado.getCodigoPostal(), empleado.getDireccion(), empleado.getNumeroTelefono(), empleado.getNSS(), empleado.getNumeroCuenta(), empleado.getSueldo(), empleado.getTipoEmpleado());
			
		} else {
			JOptionPane.showMessageDialog(this, "Cambio de nickname no permitido.");
		}*/
	}
	
	//metodo para cambiar la contrasenia
	private void cambiarContrasenya(String nickname) {
		//se le pasa un nickname donde se busca al mecanico en la bd de las tablas usuarios, mecanicos y empleados
		/*Usuario user = BD.usuarioSelect(st, nickname);
		Mecanico mecanico = BD.mecanicoSelect(st, nickname);
		Empleado empleado = BD.empleadoSelect(st, nickname);
		//si el nickname existe, se le muestra un JOptionPane donde intriduce la nueva contrasenia
		if (mecanico != null) {
			String contra = JOptionPane.showInputDialog("Introduzca la nueva Contrasenya:");
			BD.mecanicosDelete(st, mecanico.getNickname());
			BD.usuariosDelete(st, user.getNickname());
			BD.empleadosDelete(st, nickname);
			BD.mecanicosInsert(st, mecanico.getDNI(), nickname, contra, mecanico.getNombre(), mecanico.getApellido(), mecanico.getSexo(), mecanico.getEmail(), mecanico.getCiudad(), mecanico.getCodigoPostal(), mecanico.getDireccion(), mecanico.getNumeroTelefono(), mecanico.getNSS(), mecanico.getNumeroCuenta(), mecanico.getSueldo(), mecanico.getHoras(), mecanico.getCoches());
			BD.usuariosInsert(st, nickname, contra, 3);
			BD.empleadosInsert(st, empleado.getDNI(), nickname, contra, empleado.getNombre(), empleado.getApellido(), empleado.getSexo(), empleado.getEmail(), empleado.getCiudad(), empleado.getCodigoPostal(), empleado.getDireccion(), empleado.getNumeroTelefono(), empleado.getNSS(), empleado.getNumeroCuenta(), empleado.getSueldo(), empleado.getTipoEmpleado());
		} else {
			JOptionPane.showMessageDialog(this, "Cambio de contrasenya no permitido.");
		}*/
	}
}

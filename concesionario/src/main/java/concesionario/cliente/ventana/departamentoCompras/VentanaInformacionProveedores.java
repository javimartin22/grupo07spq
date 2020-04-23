package DepCompras;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaInformacionProveedores extends JFrame {

	private JPanel contentPane;
	private Statement st;
	private Connection con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInformacionProveedores frame = new VentanaInformacionProveedores(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInformacionProveedores(int tipo) {
		setTitle("Informacion proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		con = BD.initBD("Proveedores");
		st = BD.usarCrearTablasBD(con);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 32, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblnomProv = new JLabel();
		lblnomProv.setBounds(169, 32, 61, 16);
		contentPane.add(lblnomProv);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(6, 71, 61, 16);
		contentPane.add(lblPais);
		
		JLabel lblpaisProv = new JLabel();
		lblpaisProv.setBounds(169, 71, 156, 16);
		contentPane.add(lblpaisProv);
		
		JLabel lblTiposDePieza = new JLabel("Tipos de piezas");
		lblTiposDePieza.setBounds(6, 115, 102, 16);
		contentPane.add(lblTiposDePieza);
		
		JLabel lbltiposProv = new JLabel();
		lbltiposProv.setBounds(169, 115, 276, 16);
		contentPane.add(lbltiposProv);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSalir.setBounds(129, 154, 117, 29);
		contentPane.add(btnSalir);
		
		if(tipo ==0) {
			try {
				String nombre = BD.proveedoresSelect(st, "pr1").getString(2);
				String pais = BD.proveedoresSelect(st, "pr1").getString(3);
				String tipoPiezas = BD.proveedoresSelect(st, "pr1").getString(4);
				lblnomProv.setText(nombre);
				lblpaisProv.setText(pais);
				lbltiposProv.setText(tipoPiezas);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(tipo==1) {
			try {
				String nombre = BD.proveedoresSelect(st, "pr2").getString(2);
				String pais = BD.proveedoresSelect(st, "pr2").getString(3);
				String tipoPiezas = BD.proveedoresSelect(st, "pr2").getString(4);
				lblnomProv.setText(nombre);
				lblpaisProv.setText(pais);
				lbltiposProv.setText(tipoPiezas);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(tipo==2) {
			try {
				String nombre = BD.proveedoresSelect(st, "pr3").getString(2);
				String pais = BD.proveedoresSelect(st, "pr3").getString(3);
				String tipoPiezas = BD.proveedoresSelect(st, "pr3").getString(4);
				lblnomProv.setText(nombre);
				lblpaisProv.setText(pais);
				lbltiposProv.setText(tipoPiezas);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if (tipo == 3) {
			try {
				String nombre = BD.proveedoresSelect(st, "pr4").getString(2);
				String pais = BD.proveedoresSelect(st, "pr4").getString(3);
				String tipoPiezas = BD.proveedoresSelect(st, "pr4").getString(4);
				lblnomProv.setText(nombre);
				lblpaisProv.setText(pais);
				lbltiposProv.setText(tipoPiezas);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (tipo == 4) {
			try {
				String nombre = BD.proveedoresSelect(st, "pr5").getString(2);
				String pais = BD.proveedoresSelect(st, "pr5").getString(3);
				String tipoPiezas = BD.proveedoresSelect(st, "pr5").getString(4);
				lblnomProv.setText(nombre);
				lblpaisProv.setText(pais);
				lbltiposProv.setText(tipoPiezas);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}

}

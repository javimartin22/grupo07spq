package concesionario.cliente.ventana.departamentoCompras;



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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.DepartmentoComprasController;

public class VentanaInformacionProveedores extends JFrame {

	private JPanel contentPane;
	private DepartmentoComprasController departamentoComprasController;
	
	
	
	public VentanaInformacionProveedores(DepartmentoComprasController departmentoComprasController, int tipo) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaInformacionProveedores(tipo);
	}
	
	

	/**
	 * Create the frame.
	 */
	public void  iniciarVentanaInformacionProveedores(int tipo) {
		setTitle("Informacion proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		con = BD.initBD("Proveedores");
//		st = BD.usarCrearTablasBD(con);
		
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
		
		btnSalir.setBounds(169, 154, 117, 29);
		contentPane.add(btnSalir);

		
		
	}
}

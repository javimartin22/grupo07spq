package concesionario.cliente.ventana.comercial;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import concesionario.cliente.controller.ComercialController;
import concesionario.datos.CocheConcesionario;

import java.awt.Color;

public class VentanaDetallesCoche extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComercialController comercialController;
	
	public VentanaDetallesCoche(ComercialController comercialController, CocheConcesionario coche, String nickname) {
		setTitle("Informacion del Vehiculo");
		this.comercialController = comercialController;
		iniciarVentanaDetallesCoche(coche, nickname);
	}

	public void iniciarVentanaDetallesCoche(CocheConcesionario coche, String nickname) {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		setBounds(100, 100, 366, 411);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		panel.setLayout(null);
		
		//label para el modelo
		JLabel labelModelo = new JLabel("Modelo: " + coche.getModelo().toUpperCase());
		labelModelo.setHorizontalAlignment(SwingConstants.LEFT);
		labelModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelModelo.setBounds(46, 41, 200, 27);
		panel.add(labelModelo);

		//label para la marca
		JLabel labelMarca = new JLabel("Marca: " + coche.getMarca().toUpperCase());
		labelMarca.setHorizontalAlignment(SwingConstants.LEFT);
		labelMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelMarca.setBounds(46, 80, 200, 27);
		panel.add(labelMarca);
		
		//label para el color
		JLabel labelColor = new JLabel("Color: " + coche.getColor().toUpperCase());
		labelColor.setHorizontalAlignment(SwingConstants.LEFT);
		labelColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelColor.setBounds(46, 119, 200, 27);
		panel.add(labelColor);	

		//label para el cv
		JLabel labelCv = new JLabel("CV: " + coche.getCv());
		labelCv.setHorizontalAlignment(SwingConstants.LEFT);
		labelCv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelCv.setBounds(46, 158, 200, 27);
		panel.add(labelCv);	
		
		//label para el numero de puertas
		JLabel labelnpuert = new JLabel("Numero de puertas: " + coche.getNumPuertas());
		labelnpuert.setHorizontalAlignment(SwingConstants.LEFT);
		labelnpuert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelnpuert.setBounds(46, 197, 200, 27);
		panel.add(labelnpuert);	
		
		//label para las unidades
		JLabel labelUnidades = new JLabel("Unidades: " + coche.getUnidades());
		labelUnidades.setHorizontalAlignment(SwingConstants.LEFT);
		labelUnidades.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUnidades.setBounds(46, 236, 200, 27);
		panel.add(labelUnidades);	
		
		//label para el precio
		JLabel labelPrecio = new JLabel("Precio: " + coche.getPrecio());
		labelPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPrecio.setBounds(46, 275, 200, 27);
		panel.add(labelPrecio);	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCochesConcesionario vcc = new VentanaCochesConcesionario(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		panel.add(btnVolver);
		
		JLabel lblInformacionDelCoche = new JLabel("Informacion del coche seleccionado: ");
		lblInformacionDelCoche.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformacionDelCoche.setForeground(Color.RED);
		lblInformacionDelCoche.setBounds(23, 13, 276, 16);
		panel.add(lblInformacionDelCoche);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCochesConcesionario vcc = new VentanaCochesConcesionario(comercialController, nickname);
				vcc.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(117, 326, 117, 29);
		panel.add(btnRegresar);
	}
}

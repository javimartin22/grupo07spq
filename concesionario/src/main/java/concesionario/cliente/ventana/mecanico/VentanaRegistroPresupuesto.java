package concesionario.cliente.ventana.mecanico;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.ClienteFidelidad;
import concesionario.datos.Presupuesto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;

public class VentanaRegistroPresupuesto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MecanicoController mecanicoController;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public VentanaRegistroPresupuesto(MecanicoController mecanicoController, String nickname, int precio) {
		setResizable(false);
		this.mecanicoController = mecanicoController;
		ventanaPresupuesto(nickname, precio);
	}

	
	public void ventanaPresupuesto(String nickname, int cost) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarPresupuestos vvp = new VentanaVisualizarPresupuestos(mecanicoController, nickname);
				vvp.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(95, 390, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("DNI Cliente:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(24, 32, 99, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMecanico = new JLabel("Mecanico:");
		lblMecanico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMecanico.setBounds(24, 66, 119, 14);
		contentPane.add(lblMecanico);
		
		JLabel lblMarcaDelVehiculo = new JLabel("Marca del Vehiculo:");
		lblMarcaDelVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarcaDelVehiculo.setBounds(24, 103, 119, 14);
		contentPane.add(lblMarcaDelVehiculo);
		
		JLabel lblModeloDelVehiculo = new JLabel("Modelo del Vehiculo:");
		lblModeloDelVehiculo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModeloDelVehiculo.setBounds(24, 144, 135, 14);
		contentPane.add(lblModeloDelVehiculo);
		
		JLabel lblProblema = new JLabel("Problema:");
		lblProblema.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProblema.setBounds(24, 178, 119, 14);
		contentPane.add(lblProblema);
		
		JLabel lblNumeroDePiezas = new JLabel("Numero de Piezas:");
		lblNumeroDePiezas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroDePiezas.setBounds(24, 215, 119, 14);
		contentPane.add(lblNumeroDePiezas);
		
		JLabel lblObservaviones = new JLabel("Observaviones:");
		lblObservaviones.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservaviones.setBounds(24, 285, 119, 14);
		contentPane.add(lblObservaviones);
		
		JTextPane textPane = new JTextPane();
		textPane.setToolTipText("");
		textPane.setBounds(180, 285, 239, 79);
		contentPane.add(textPane);
		
		textField = new JTextField();
		textField.setBounds(180, 29, 239, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 100, 239, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 141, 239, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(180, 175, 239, 20);
		contentPane.add(textField_4);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(180, 212, 71, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel(nickname);
		lblNewLabel_1.setBounds(180, 66, 239, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecio.setBounds(24, 253, 119, 14);
		contentPane.add(lblPrecio);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(180, 251, 71, 20);
		spinner_1.setValue(cost);
		contentPane.add(spinner_1);
		
		
		JButton btnNewButton_1 = new JButton("Crear Presupuesto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Presupuesto> presupuestos = mecanicoController.cargarTablaPresupuesto();
				double descuento =  presupuestoDescuento(textField.getText());
				String codigo = mecanicoController.crearCodigo(presupuestos);
				String dniCliente = textField.getText();
				String mecanico = nickname;
				String marca = textField_2.getText();
				String modelo = textField_3.getText();
				String problema = textField_4.getText();
				int numPiezas = Integer.parseInt(spinner.getValue().toString());
				String listaPiezas = crearPiezasString(numPiezas);
				String observaciones = textPane.getText();
				int precio = (int) ((Integer.parseInt(spinner_1.getValue().toString())) * descuento);
				String fecha = mecanicoController.parseFecha();
				Presupuesto presupuesto = new Presupuesto(codigo, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);
				registroPresupuesto(presupuesto);
				VentanaVisualizarPresupuestos vvp = new VentanaVisualizarPresupuestos(mecanicoController, nickname);
				vvp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(231, 390, 144, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public void registroPresupuesto(Presupuesto presupuesto) {
		if (mecanicoController.registroPresupuesto(presupuesto)) {
			JOptionPane.showMessageDialog(contentPane, "Presupuesto registrado correctamente");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Presupuesto no regsitrada");
		}
	}
	
	public double presupuestoDescuento(String dni) {
		double descuento = 1.0;
		List<ClienteFidelidad> cleinteFidelidad = mecanicoController.cargarClienteFidelidad();
		for (ClienteFidelidad c : cleinteFidelidad) {
			if (c.getDni().equals(dni)) {
				if (c.getFidelidad() <= 5) {
					descuento  = 1.0;
				} else if (c.getFidelidad() >= 6 && c.getFidelidad() <=10) {
					descuento = 0.90;
				} else if (c.getFidelidad() > 10) {
					descuento = 0.80;
				} 
			} 
		}
		return descuento;
	}
	
	public String crearPiezasString(int numPiezas) {
		String listaPiezas = "";
		ArrayList<String> piezas = new ArrayList<String>();
		for (int i = 0; i < numPiezas; i++) {
			int j = i + 1;
			String pieza = JOptionPane.showInputDialog("Introduzca la pieza " + j + ":");
			piezas.add(pieza);
		}
		
		for (int i = 0; i < piezas.size(); i++) {
			if (i == 0) {
				listaPiezas = piezas.get(i);
			} else {
				listaPiezas = listaPiezas + ", " + piezas.get(i);
			}
		}
		return listaPiezas;
	}
}

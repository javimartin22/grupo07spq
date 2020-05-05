package concesionario.cliente.ventana.gerente;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Tarifa;
import concesionario.datos.Venta;


public class VentanaNuevoEstudioMercado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecioAprox;
	private JTextField textFieldHorasManoObra;
	private GerenteController gerenteController;
	final Logger logger = LoggerFactory.getLogger(VentanaNuevoEstudioMercado.class);
	static int iteration = 0;

	public VentanaNuevoEstudioMercado(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;
		iniciarVentanaNuevoEstudioMercado(nickname);
	}
	
	@Override
	public String toString() {
		return "VentanaRegistroTarifa [contentPane=" + contentPane + ", textFieldNombre=" + textFieldNombre
				+ ", textFieldPrecioAprox=" + textFieldPrecioAprox + ", textFieldHorasManoObra="
				+ textFieldHorasManoObra + ", gerenteController=" + gerenteController + "]";
	}

	public void iniciarVentanaNuevoEstudioMercado(String nickname) {
		setResizable(false);
		setTitle("Estudio Mercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 278);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfo = new JLabel("Estudios de Mercado Predeterminados");
		lblInfo.setBounds(22, 20, 426, 16);
		contentPane.add(lblInfo);
		
		
		JButton btnMarca = new JButton("Ventas por Marca");
		btnMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Venta> ventas = gerenteController.cargarTablaVenta();
				VentanaEstudioMercado vem = new VentanaEstudioMercado("Ventas por Marca", ventas);
				vem.setVisible(true);
			}
		});
		btnMarca.setBounds(100, 50, 117, 29);
		contentPane.add(btnMarca);
		
		JButton btnComerciales = new JButton("Ventas por Comerciales");
		btnComerciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VentanaEstudioMercado vem = new VentanaEstudioMercado("Ventas por Comerciales");
				//vem.setVisible(true);
			}
		});
		btnComerciales.setBounds(100, 90, 117, 29);
		contentPane.add(btnComerciales);
		
		JButton btnMes = new JButton("Ventas por Mes");
		btnMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VentanaEstudioMercado vem = new VentanaEstudioMercado("Ventas por Mes");
				//vem.setVisible(true);
			}
		});
		btnMes.setBounds(100, 130, 117, 29);
		contentPane.add(btnMes);
		
		
		JButton btnModeloxMarca = new JButton("Modelos por Marca");
		btnMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VentanaEstudioMercado vem = new VentanaEstudioMercado("ComboBox Marca");
				//vem.setVisible(true);
			}
		});
		btnMes.setBounds(100, 168, 117, 29);
		contentPane.add(btnMes);
		
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionEstudioMercado vgem = new VentanaGestionEstudioMercado(gerenteController, nickname);
				vgem.setVisible(true);
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(110, 197, 117, 29);
		contentPane.add(btnCancelar);
	}
	
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldNombre.getText().isEmpty() && !textFieldPrecioAprox.getText().isEmpty() && !textFieldHorasManoObra.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
	
	
	public void vaciarCampos() {
		textFieldNombre.setText("");
		textFieldPrecioAprox.setText("");
		textFieldHorasManoObra.setText("");
	}
	
	public void registrarTarifa(Tarifa tarifa, String nickname){
		if (gerenteController.registroTarifa(tarifa)) {
			logger.info("Tarifa registrada correctamente.");
			int respuesta = JOptionPane.showConfirmDialog(this, "Tarifa Registrada ¿Desea registrar otra tarifa?");
			switch (respuesta) {
			case 0:
				vaciarCampos();
				break;
			case 1:
				VentanaGestionTarifas ve = new VentanaGestionTarifas(gerenteController, nickname);
				ve.setVisible(true);
				dispose();
				break;
			case 2: 
				VentanaGestionTarifas ve2 = new VentanaGestionTarifas(gerenteController, nickname);
				ve2.setVisible(true);
				dispose();
				break;
			}
		} else {
			logger.error("Las tarifas no llegan correctamente.");
		}
	}
	
	public String crearID () {
		List<Tarifa> tarifas = gerenteController.cargarTablaTarifas();
		int numero = tarifas.size() + 1;
		String ID  = "TA-" + numero;
		return ID;
	}
	
}

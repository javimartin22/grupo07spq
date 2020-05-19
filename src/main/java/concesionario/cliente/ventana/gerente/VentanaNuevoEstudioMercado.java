package concesionario.cliente.ventana.gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.Font;
import java.awt.Color;
/**
* Ventana para la seleccion de los distintos Estudios de Mercado
*/
public class VentanaNuevoEstudioMercado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecioAprox;
	private JTextField textFieldHorasManoObra;
	/**
	 * Controlador para la clase Gerente.
	 */
	private GerenteController gerenteController;
	
	final Logger logger = LoggerFactory.getLogger(VentanaNuevoEstudioMercado.class);
	static int iteration = 0;
	/**
	 * Constructor de la clase NuevoEstudioMercado
	 * @param gerenteController (Controlador de la ventana para la clase Gerente)
	 * @param nickname (Nickname del gerente)
	 */
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
	/**
	 * Inicializador del JFrame de la ventana NuevoEstudioMercado
	 * @param nickname (Nickname del gerente)
	 */
	public void iniciarVentanaNuevoEstudioMercado(String nickname) {
		setResizable(false);
		setTitle("Estudios Mercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 259);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfo = new JLabel("Estudios de Mercado Predeterminados:");
		lblInfo.setForeground(Color.DARK_GRAY);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInfo.setBounds(10, 11, 294, 29);
		contentPane.add(lblInfo);
		
		
		JButton btnMarca = new JButton("Ventas por Marca");
		btnMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Venta> ventas = gerenteController.cargarTablaVenta();
				VentanaEstudioMercado vem = new VentanaEstudioMercado("Ventas por Marca", ventas, gerenteController, nickname);
				vem.setVisible(true);
				dispose();
			}
		});
		btnMarca.setBounds(236, 60, 193, 37);
		contentPane.add(btnMarca);
		
		JButton btnComerciales = new JButton("Ventas por Comerciales");
		btnComerciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Venta> ventas = gerenteController.cargarTablaVenta();
				VentanaEstudioComerciales vec = new VentanaEstudioComerciales("Ventas por mensualidad", "Ventas por Mes", ventas, gerenteController, nickname);
				vec.setVisible(true);
				dispose();
			}
		});
		btnComerciales.setBounds(20, 60, 193, 37);
		contentPane.add(btnComerciales);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuAdmin vgem = new VentanaMenuAdmin(gerenteController, nickname);
				vgem.setVisible(true);
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(168, 179, 117, 29);
		contentPane.add(btnCancelar);
		
		JButton btnVentasPorMes = new JButton("Ventas por Mes");
		btnVentasPorMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Venta> ventas = gerenteController.cargarTablaVenta();
				VentanaEstudioMes vem = new VentanaEstudioMes("Ventas por mensualidad", "Ventas por Mes", ventas, gerenteController, nickname);
				vem.setVisible(true);
				dispose();
			}
		});
		btnVentasPorMes.setBounds(130, 119, 193, 37);
		contentPane.add(btnVentasPorMes);
	}
	/**
	 * Metodo para comprar que los datos no esten vacios
	 */
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldNombre.getText().isEmpty() && !textFieldPrecioAprox.getText().isEmpty() && !textFieldHorasManoObra.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
	
	/**
	 * Metodo para vaciar los campos o textfields
	 */
	public void vaciarCampos() {
		textFieldNombre.setText("");
		textFieldPrecioAprox.setText("");
		textFieldHorasManoObra.setText("");
	}
	
	/**
	 * Metodo para registrar una nueva Tarifa.
	 * @param tarifa (Objeto Tarifa)
	 * @param nickname (Nickname del Gerente)
	 */
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
	
	/**
	 * Metodo para la creacion de un codigo identificativo.
	 * @return ID (Codigo Identificativo)
	 */
	public String crearID () {
		List<Tarifa> tarifas = gerenteController.cargarTablaTarifas();
		int numero = tarifas.size() + 1;
		String ID  = "TA-" + numero;
		return ID;
	}
	
}

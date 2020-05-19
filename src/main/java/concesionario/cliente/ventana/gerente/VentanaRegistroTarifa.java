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
import java.awt.Font;
import java.awt.Color;

/**
* Clase para el registro de tarifas
*/
public class VentanaRegistroTarifa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecioAprox;
	private JTextField textFieldHorasManoObra;
	/**
	 * Controlador para la clase Gerente.
	 */
	private GerenteController gerenteController;
	
	final Logger logger = LoggerFactory.getLogger(VentanaRegistroTarifa.class);
	static int iteration = 0;
	/**
	 * Constructor de la clase RegistroTarifa 
	 * @param gerenteController (Controlador de la ventana Gerente)
	 */
	public VentanaRegistroTarifa(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;
		iniciarVentanaRegistroTarifa(nickname);
	}
	
	@Override
	public String toString() {
		return "VentanaRegistroTarifa [contentPane=" + contentPane + ", textFieldNombre=" + textFieldNombre
				+ ", textFieldPrecioAprox=" + textFieldPrecioAprox + ", textFieldHorasManoObra="
				+ textFieldHorasManoObra + ", gerenteController=" + gerenteController + "]";
	}
	/**
	 * Inicializador de la ventana RegistroTarifa
	 * @param nickname (Nickname del Gerente)
	 */
	public void iniciarVentanaRegistroTarifa(String nickname) {
		setResizable(false);
		setTitle("Registrar Nueva Tarifa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 278);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfo = new JLabel("Rellene los siguientes datos para registrar una nueva tarifa:");
		lblInfo.setForeground(Color.RED);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(10, 11, 426, 16);
		contentPane.add(lblInfo);
		
		JLabel lblNombre = new JLabel("Nombre Tarifa: ");
		lblNombre.setBounds(22, 52, 181, 16);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(251, 47, 228, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblPrecioAprox = new JLabel("Estimacion Precio: ");
		lblPrecioAprox.setBounds(22, 95, 181, 16);
		contentPane.add(lblPrecioAprox);
		
		textFieldPrecioAprox = new JTextField();
		textFieldPrecioAprox.setBounds(251, 90, 228, 26);
		contentPane.add(textFieldPrecioAprox);
		textFieldPrecioAprox.setColumns(10);
		
		JLabel lblHorasManoObra = new JLabel("Estimacion de Horas de Trabajo: ");
		lblHorasManoObra.setBounds(22, 141, 199, 16);
		contentPane.add(lblHorasManoObra);
		
		textFieldHorasManoObra = new JTextField();
		textFieldHorasManoObra.setBounds(251, 136, 228, 26);
		contentPane.add(textFieldHorasManoObra);
		textFieldHorasManoObra.setColumns(10);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionTarifas ventana = new VentanaGestionTarifas(gerenteController, nickname);
				ventana.setVisible(true);
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(110, 188, 117, 29);
		contentPane.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datos = comprobarDatos();
				if (datos) {
					String idTarifa = crearID();
					String nombre = textFieldNombre.getText();
					int precioAprox = Integer.parseInt(textFieldPrecioAprox.getText());
					int horas_manodeobra = Integer.parseInt(textFieldHorasManoObra.getText());
					
					Tarifa tarifa = new Tarifa(idTarifa, nombre, precioAprox, horas_manodeobra);
					registrarTarifa(tarifa, nickname);
				} else {
					logger.error("Todos los campos deben estar rellenados.");
				}
			}
		});
		btnRegistrar.setBounds(281, 188, 117, 29);
		contentPane.add(btnRegistrar);
	}
	/**
	 * Metodo para comprobar que los campos no esten vacios
	 */
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldNombre.getText().isEmpty() && !textFieldPrecioAprox.getText().isEmpty() && !textFieldHorasManoObra.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
	
	/**
	 * Metodo para vaciar campos
	 */
	public void vaciarCampos() {
		textFieldNombre.setText("");
		textFieldPrecioAprox.setText("");
		textFieldHorasManoObra.setText("");
	}
	/**
	 * Metodo para registrar una tarifa
	 * @param tarifa (Tarifa a registrar)
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
	 * Metodo para generar los identificadores de las tarifas
	 */
	public String crearID () {
		List<Tarifa> tarifas = gerenteController.cargarTablaTarifas();
		int numero = tarifas.size() + 1;
		String ID  = "TA-" + numero;
		return ID;
	}
	
}

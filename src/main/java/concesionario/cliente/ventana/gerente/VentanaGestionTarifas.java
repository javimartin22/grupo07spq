package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.GerenteController;
/**
* Clase de Gestion de las Tarifas
*/
public class VentanaGestionTarifas extends JFrame {
	/**
	 * Controlador para la clase Gerente
	 */
	private GerenteController gerenteController;
	
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(VentanaGestionTarifas.class);
	static int iteration = 0;
	
	/**
	 * Constructor de la clase GestionTarifa
	 * @param gerenteController (Controlador de la ventana para la clase Gerente)
	 * @param nickname del gerente
	 */
	public VentanaGestionTarifas(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;   //errores
		inicioVentanaGestionTarifas(nickname);
	}
	
	/**
	 * Inicializador del JFrame VentanaGestionTarifas 
	 * @param nickname del gerente
	 */
	public void inicioVentanaGestionTarifas(String nickname) {
		this.setTitle("Gestión de las Tarifas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(434,181);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
			
			
			JButton buttonRegistroTarifa = new JButton("Registrar nueva tarifa");
			buttonRegistroTarifa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaRegistroTarifa vt = new VentanaRegistroTarifa(gerenteController, nickname);
					vt.setVisible(true);
					dispose();
				} 
			});
			buttonRegistroTarifa.setBounds(28, 39, 164, 37);
			panel.add(buttonRegistroTarifa);
			
			JButton buttonVeryEditTarifas = new JButton("Ver y editar tarifas");
			buttonVeryEditTarifas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaVeryEditarTarifas vtf = new VentanaVeryEditarTarifas(gerenteController, nickname);
					vtf.setVisible(true);
					dispose();
				}
			});
			buttonVeryEditTarifas.setBounds(230, 39, 164, 37);
			panel.add(buttonVeryEditTarifas);
			
			//boton de volver que te lleva al menu de Admin
			JButton buttonVolver = new JButton("Volver");
			buttonVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volver(nickname);
				}
			});
			buttonVolver.setBounds(170, 98, 89, 23);
			panel.add(buttonVolver);
	}
	/**
	 * Metodo para ver todos los usuarios y/o empleados
	 * @param nickname del gerente
	 */
	public void verUsuarios(String nickname) {
		VentanaEmpleados ve = new VentanaEmpleados(gerenteController, nickname);
    	ve.setVisible(true);
    	dispose();
	}
	/**
	 * Metodo para regresar a la ventana anterior (Menu admin) 
	 * @param nickname del gerente
	 */
	public void volver(String nickname) {
		VentanaMenuAdmin vmenu = new VentanaMenuAdmin(gerenteController, nickname);
		vmenu.setVisible(true);
		dispose();
	}
	
}


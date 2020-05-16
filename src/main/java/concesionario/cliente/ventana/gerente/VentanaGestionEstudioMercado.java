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
* Clase de Seleccion de los Estudios de Mercado
*/
public class VentanaGestionEstudioMercado extends JFrame {
	/**
	 * Controlador para la clase Gerente
	 */
	private GerenteController gerenteController;
	
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(VentanaGestionEstudioMercado.class);
	static int iteration = 0;
	
	/**
	 * Constructor de la clase GestionEstudioMercado 
	 * @param gerenteController (Controlador de la ventana para la clase Gerente)
	 * @param nickname del gerente
	 */
	public VentanaGestionEstudioMercado(GerenteController gerenteController, String nickname) {
		this.gerenteController = gerenteController;   //errores
		inicioVentanaGestionEstudioMercado(nickname);
	}
	
	/**
	 * Inicializador del JFrame GestionEstudioMercado 
	 * @param nickname del gerente
	 */
	public void inicioVentanaGestionEstudioMercado(String nickname) {
		this.setTitle("Estudios de Mercado");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(421,181);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
			
			
			JButton buttonRegistroEstudio = new JButton("Realizar nuevo estudio");
			buttonRegistroEstudio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaNuevoEstudioMercado vnem = new VentanaNuevoEstudioMercado(gerenteController, nickname);
					vnem.setVisible(true);
					dispose();
				} 
			});
			buttonRegistroEstudio.setBounds(25, 43, 164, 33);
			panel.add(buttonRegistroEstudio);
			
			JButton buttonVeryEditTarifas = new JButton("Ver y editar tarifas");
			buttonVeryEditTarifas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaVeryEditarTarifas vtf = new VentanaVeryEditarTarifas(gerenteController, nickname);
					vtf.setVisible(true);
					dispose();
				}
			});
			buttonVeryEditTarifas.setBounds(219, 43, 164, 33);
			panel.add(buttonVeryEditTarifas);
			
			//boton de volver que te lleva al menu de Admin
			JButton buttonVolver = new JButton("Volver");
			buttonVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volver(nickname);
				}
			});
			buttonVolver.setBounds(162, 98, 89, 23);
			panel.add(buttonVolver);
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

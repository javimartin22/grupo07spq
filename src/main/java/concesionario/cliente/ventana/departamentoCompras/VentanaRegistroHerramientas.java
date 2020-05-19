package concesionario.cliente.ventana.departamentoCompras;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.Herramientas;
import concesionario.datos.HerramientasTaller;


/**
 * VentanaRegistroHerramientas (Ventana para Registrar Herramientas en la BD).
 */
public class VentanaRegistroHerramientas extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private DepartmentoComprasController departmentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaRegistroHerramientas.class);
	static int iteration = 0;

	/**
	 * Constructor de la VentanaRegistroHerramientas
	 * @param departamentoComprasController (Controlador de las Ventanas de Departamento de Compras)
	 * @param herramienta (Objeto tipo Herramienta)
	 * @param cantidad (Unidades)
	 * @param nickname (Nickname del departamento de compras)
	 */
	public VentanaRegistroHerramientas(DepartmentoComprasController departamentoComprasController, Herramientas herramienta, int cantidad, String nickname){
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaRegistoHerramientas(herramienta, cantidad, nickname);
	}
	
	/**
	 * Inicializador del JFrame de la VentanaRegistroHerramientas
	 * @param herramienta (Objeto de tipo Herramienta)
	 * @param cantidad (Unidades)
	 * @param nickname (Nickname del departamento de compras)
	 */
	public void iniciarVentanaRegistoHerramientas(Herramientas herramienta, int cantidad, String nickname) {
		setResizable(false);
		setTitle("Registro de Nuevas Herramientas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		String ubicaciones [] = {"Alamacen 1 - Estanteria 1", "Almacen 1 - Estanteria 2", "Almacen 1 - Estanteria 3", "Almacen 2 - Estanteria 1", "Almacen 2 - Estanteria 2", "Almacen 2 - Estanteria 3"};
		
		JLabel lblNewLabel = new JLabel("Indique donde se almacenara la herramienta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Herramienta:");
		lblNombreDeLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreDeLa.setBounds(53, 83, 166, 16);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de Herramientas:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(53, 122, 150, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblUbicacionDeLa = new JLabel("Ubicacion de la Herramienta:");
		lblUbicacionDeLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUbicacionDeLa.setBounds(53, 166, 202, 16);
		contentPane.add(lblUbicacionDeLa);
		
		comboBox = new JComboBox(ubicaciones);
		comboBox.setBounds(265, 161, 228, 27);
		contentPane.add(comboBox);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ubicacion = departmentoComprasController.parseUbicacion(comboBox.getSelectedIndex());
				HerramientasTaller herramientaTaller = new HerramientasTaller(obtenerCodigo(), herramienta.getNombre(), cantidad, ubicacion);
				registrarBD(herramientaTaller);
			}
		});
		btnRegistrar.setBounds(265, 207, 117, 29);
		contentPane.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHerramientasTaller vht = new VentanaHerramientasTaller(departmentoComprasController, nickname);
				vht.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(126, 207, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel(herramienta.getNombre());
		lblNewLabel_2.setBounds(265, 85, 217, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(cantidad + "");
		lblNewLabel_3.setBounds(266, 124, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblCodigoIdentificativo = new JLabel("Codigo Identificativo:");
		lblCodigoIdentificativo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoIdentificativo.setBounds(53, 47, 150, 16);
		contentPane.add(lblCodigoIdentificativo);
		
		JLabel lblNewLabel_2_1 = new JLabel(obtenerCodigo());
		lblNewLabel_2_1.setBounds(265, 48, 217, 14);
		contentPane.add(lblNewLabel_2_1);
	}
	
	/**
	 * Metodo para registrar una herramienta en la BD
	 * @param herramienta (Objeto de tipo HerramientasTaller)
	 */
		private void registrarBD(HerramientasTaller herramienta) {
			if (departmentoComprasController.registroHerramienta(herramienta)) {
				logger.info("Herramienta añadida correctamente.");
			} else {
				logger.error("Error al guardar la herramienta en la BD.");
			}
		}
	
		/**
		 * Metodo para obtener el codigo de las herramientas
		 * @return String Codigo de las herramientas
		 */
	public String obtenerCodigo() {
		List<HerramientasTaller> herramientas = departmentoComprasController.cargarHerramientas();
		return departmentoComprasController.calcularCodigoHerramienta(herramientas);
	}

	

}

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



public class VentanaRegistroHerramientas extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private DepartmentoComprasController departmentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaRegistroHerramientas.class);
	static int iteration = 0;

	public VentanaRegistroHerramientas(DepartmentoComprasController departamentoComprasController, Herramientas herramienta, int cantidad, String nickname){
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaRegistoHerramientas(herramienta, cantidad, nickname);
	}
	
	public void iniciarVentanaRegistoHerramientas(Herramientas herramienta, int cantidad, String nickname) {
		setResizable(false);
		setTitle("Registro de Nuevas Herramientas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 281);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String ubicaciones [] = {"Alamacen 1 - Estanteria 1", "Almacen 1 - Estanteria 2", "Almacen 1 - Estanteria 3", "Almacen 2 - Estanteria 1", "Almacen 2 - Estanteria 2", "Almacen 2 - Estanteria 3"};
		
		JLabel lblNewLabel = new JLabel("Indique donde se almacenara la herramienta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.BLACK);
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
		
		JLabel lblUbicacionDeLa = new JLabel("Ubicacion de la Pieza:");
		lblUbicacionDeLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUbicacionDeLa.setBounds(53, 166, 150, 16);
		contentPane.add(lblUbicacionDeLa);
		
		comboBox = new JComboBox(ubicaciones);
		comboBox.setBounds(233, 161, 228, 27);
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
		lblNewLabel_2.setBounds(231, 84, 217, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(cantidad + "");
		lblNewLabel_3.setBounds(232, 123, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblCodigoIdentificativo = new JLabel("Codigo Identificativo:");
		lblCodigoIdentificativo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoIdentificativo.setBounds(53, 47, 150, 16);
		contentPane.add(lblCodigoIdentificativo);
		
		JLabel lblNewLabel_2_1 = new JLabel(obtenerCodigo());
		lblNewLabel_2_1.setBounds(231, 47, 217, 14);
		contentPane.add(lblNewLabel_2_1);
	}
	

		private void registrarBD(HerramientasTaller herramienta) {
			if (departmentoComprasController.registroHerramienta(herramienta)) {
				logger.info("Herramienta añadida correctamente.");
			} else {
				logger.error("Error al guardar la herramienta en la BD.");
			}
		}
	
	public String obtenerCodigo() {
		List<HerramientasTaller> herramientas = departmentoComprasController.cargarHerramientas();
		return departmentoComprasController.calcularCodigoHerramienta(herramientas);
	}

	

}
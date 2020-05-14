package concesionario.cliente.ventana.mecanico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.Presupuesto;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Interfaz grafica VentanaInformacionPresupuesto (Permite visualizar informacion acerca del presupuesto)
 */
public class VentanaInformacionPresupuesto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MecanicoController mecanicoController;

	/**
	 * Constructor de la VentanaInformacionPresupuesto
	 * @param mecanicoController (Controlador de las ventanas para la clase Mecanico)
	 * @param nickname (Nickname del mecanico)
	 * @param presupuesto (Objeto presupuesto)
	 */
	public VentanaInformacionPresupuesto(MecanicoController mecanicoController, String nickname, Presupuesto presupuesto) {
		setResizable(false);
		this.mecanicoController = mecanicoController;
		ventanaInformacionPresupuesto(nickname, presupuesto);
	}
	
	/**
	 * Inicializador del JFrame de la ventanaInformacionPresupuesto
	 * @param nickname (Nickname del mecanico)
	 * @param presupuesto (Objeto presupuesto)
	 */
	void ventanaInformacionPresupuesto(String nickname, Presupuesto presupuesto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Label informacion presupuesto
		 */
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 25, 148, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDniCliente = new JLabel("DNI Cliente:");
		lblDniCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDniCliente.setBounds(10, 59, 148, 14);
		contentPane.add(lblDniCliente);
		
		JLabel lblNombreMecanico = new JLabel("Nombre Mecanico:");
		lblNombreMecanico.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreMecanico.setBounds(10, 94, 148, 14);
		contentPane.add(lblNombreMecanico);
		
		JLabel lblNewLabel_1_1 = new JLabel("Marca Vehiculo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 128, 148, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblModeloVehiculo = new JLabel("Modelo Vehiculo:");
		lblModeloVehiculo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModeloVehiculo.setBounds(10, 167, 148, 14);
		contentPane.add(lblModeloVehiculo);
		
		JLabel lblNewLabel_1_2 = new JLabel("Problema:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(10, 201, 148, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Numero Piezas: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(10, 236, 148, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Precio: ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 270, 148, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Observaciones:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 295, 148, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel(presupuesto.getCodigo());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(179, 25, 148, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDniCliente_1 = new JLabel(presupuesto.getDniCliente());
		lblDniCliente_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDniCliente_1.setBounds(179, 59, 148, 14);
		contentPane.add(lblDniCliente_1);
		
		JLabel lblNombreMecanico_1 = new JLabel(presupuesto.getMecanico());
		lblNombreMecanico_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreMecanico_1.setBounds(179, 94, 148, 14);
		contentPane.add(lblNombreMecanico_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel(presupuesto.getMarca());
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(179, 128, 148, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblModeloVehiculo_1 = new JLabel(presupuesto.getModelo());
		lblModeloVehiculo_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModeloVehiculo_1.setBounds(179, 167, 148, 14);
		contentPane.add(lblModeloVehiculo_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel(presupuesto.getProblema());
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(179, 201, 148, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel(presupuesto.getNumPiezas() + "");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_2.setBounds(179, 236, 148, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel(presupuesto.getPrecio() + "");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_2.setBounds(179, 270, 148, 14);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		/*
		 * Area de texto donde se meustra la informacion
		 */
		JTextArea textArea = new JTextArea(presupuesto.getObservaciones());
		textArea.setEditable(false);
		textArea.setBounds(20, 320, 392, 71);
		contentPane.add(textArea);
		
		/*
		 * Boton para regresar a la VentanaVisualizarPresupuestos
		 */
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarPresupuestos vvp = new VentanaVisualizarPresupuestos(mecanicoController, nickname);
				vvp.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(172, 411, 89, 23);
		contentPane.add(btnNewButton);
	}
}

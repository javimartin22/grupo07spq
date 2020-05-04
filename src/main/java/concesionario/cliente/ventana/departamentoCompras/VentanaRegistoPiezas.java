package concesionario.cliente.ventana.departamentoCompras;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.Pieza;
import concesionario.datos.PiezaProveedores;

import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaRegistoPiezas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private DepartmentoComprasController departmentoComprasController;
	final Logger logger = LoggerFactory.getLogger(VentanaRegistoPiezas.class);
	static int iteration = 0;
	
	public VentanaRegistoPiezas(DepartmentoComprasController departamentoComprasController, PiezaProveedores piezaProveedor, int cantidad, String nickname){
		this.departmentoComprasController = departamentoComprasController;
		iniciarVentanaRegistoPiezas(piezaProveedor, cantidad, nickname);
	}
	
	public void iniciarVentanaRegistoPiezas(PiezaProveedores piezaProveedor, int cantidad, String nombreMecanico) {
		setResizable(false);
		setTitle("Registro de Nuevas Piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 281);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String ubicaciones [] = {"Alamacen 1 - Estanteria 1", "Almacen 1 - Estanteria 2", "Almacen 1 - Estanteria 3", "Almacen 2 - Estanteria 1", "Almacen 2 - Estanteria 2", "Almacen 2 - Estanteria 3"};
		
		JLabel lblNewLabel = new JLabel("Indique donde se almacenara la pieza:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Pieza:");
		lblNombreDeLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreDeLa.setBounds(53, 83, 150, 16);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de Piezas:");
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
				Pieza pieza = new Pieza(obtenerCodigo(), piezaProveedor.getNombre(), cantidad, ubicacion);
				registrarBD(pieza);
			}
		});
		btnRegistrar.setBounds(265, 207, 117, 29);
		contentPane.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPiezasUtilizadas vpu = new VentanaPiezasUtilizadas(departmentoComprasController, nombreMecanico);
				vpu.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(126, 207, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel(piezaProveedor.getNombre());
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
	
	//Conectar con el servidor para poder hacer el registro en la BD:
	private void registrarBD(Pieza pieza) {
		if (departmentoComprasController.registroPieza(pieza)) {
			logger.info("Pieza añadida correctamente.");
		} else {
			logger.error("Error al guardar la pieza en la BD.");
		}
	}
	
	public String obtenerCodigo() {
		List<Pieza> piezas = departmentoComprasController.cargarPiezas();
		return departmentoComprasController.carlcularCodigo(piezas);
	}
}
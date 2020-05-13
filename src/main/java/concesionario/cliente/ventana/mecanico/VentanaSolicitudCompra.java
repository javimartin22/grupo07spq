package concesionario.cliente.ventana.mecanico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.Herramientas;
import concesionario.datos.Pieza;
import concesionario.datos.SolicitudCompra;
import javax.swing.DefaultComboBoxModel;


/**
 * Interfaz grafica VentanaSolicitudCompra (Permite realizar una solicitud de compra de piezas o herramientas)
 */
public class VentanaSolicitudCompra extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private MecanicoController mecanicoController;
	final Logger logger = LoggerFactory.getLogger(VentanaSolicitudCompra.class);
	static int iteration = 0;

	/**
	 * Constructor de la VentanaSolicitudCompra
	 * @param mecanicoController (Controlador de las ventanas para la clase Mecanico)
	 * @param nickname (Nickname del mecanico)
	 */
	public VentanaSolicitudCompra(MecanicoController mecanicoController, String nickname) {
		setResizable(false);
		this.mecanicoController = mecanicoController;
		iniciarVentanaSolicitudCompra(nickname);
	}
	/**
	 * Inicializador del JFrame de la VentanaSolicitudCompra
	 * @param nickname (Nickname del mecanico)
	 */
	public void iniciarVentanaSolicitudCompra(String nickname) {
		setTitle("Solicitar compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 272);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rellene los siguientes datos para solicitar una compra: ");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		/*
		 * Boton de regreso a la interfaz grafica VentanaHerramientasMecanico
		 */
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaHerramientasMecanico vmdc = new VentanaHerramientasMecanico(mecanicoController, nickname);
				vmdc.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(312, 204, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblProveedor = new JLabel("Tipo: ");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setForeground(new Color(0, 0, 0));
		lblProveedor.setBounds(22, 68, 71, 16);
		contentPane.add(lblProveedor);
		
		JLabel lblArticulo = new JLabel();
		lblArticulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArticulo.setForeground(new Color(0, 0, 0));
		lblArticulo.setBounds(22, 107, 81, 16);
		contentPane.add(lblArticulo);
		
		JComboBox comboBoxHerramienta = new JComboBox();
		comboBoxHerramienta.setBounds(155, 102, 177, 27);
		contentPane.add(comboBoxHerramienta);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidades.setForeground(new Color(0, 0, 0));
		lblUnidades.setBounds(22, 147, 61, 16);
		contentPane.add(lblUnidades);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Herramienta", "Pieza"}));
		comboBox.setBounds(155, 63, 177, 27);
		contentPane.add(comboBox);
		
		/*
		 * Boton para cargar las herramientas o piezas disponibles a solicitar
		 */
		JButton btnVerHerramientas = new JButton("Ver");
		btnVerHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
					comboBoxHerramienta.removeAllItems();

					for (String nom : crearHerramientas()) {
						comboBoxHerramienta.addItem(nom);
					}
				}
				
				if(comboBox.getSelectedIndex()==1) {
					comboBoxHerramienta.removeAllItems();
					for (String nom : crearPiezas()) {
						comboBoxHerramienta.addItem(nom);
					}
				}
				
			}
		});
		btnVerHerramientas.setBounds(396, 62, 138, 29);
		contentPane.add(btnVerHerramientas);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(155, 141, 44, 26);
		contentPane.add(spinner);
		
		/*
		 * Boton para solicitar una compra al departamento de compras.
		 */
		
		JButton btnComprar = new JButton("Solicitar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, " Solicitud enviada al dep compras");
				String nombre = comboBoxHerramienta.getSelectedItem().toString();
				String tipo = comboBox.getSelectedItem().toString();
				Integer unidades = (Integer)spinner.getValue();
				SolicitudCompra sol = new SolicitudCompra(obtenerCodigo(),tipo,nombre,unidades);
				registrarBD(sol);
			}
		});
		btnComprar.setBounds(135, 204, 117, 29);
		contentPane.add(btnComprar);
		
		JLabel lblPrecio = new JLabel("");
		lblPrecio.setBounds(155, 219, 177, 16);
		contentPane.add(lblPrecio);
		
		
		
}
	
	
	/*
	 * Carga las herramientas disponibles para la solicitud en la BD
	 */
	public List<Herramientas> cargarListaHerramientas(){
		return mecanicoController.cargarListaHerramientas();
	}
	
	/*
	 * Añade las herramientas disponibles para la solicitud en un arraylist 
	 */
	public ArrayList<String> crearHerramientas(){
		
		List<Herramientas> herramientas = mecanicoController.cargarListaHerramientas();
		
		ArrayList<String> lista = new ArrayList<String>();
		for (Herramientas h : herramientas) {
			lista.add(h.getNombre());
		}
		return lista;
	}
	
	/*
	 * Carga las piezas disponibles para la solicitud en la BD
	 */
	public ArrayList<String> crearPiezas(){
		
		List<Pieza> piezas = mecanicoController.cargarPiezas();
		
		ArrayList<String> lista = new ArrayList<String>();
		for (Pieza p : piezas) {
			lista.add(p.getNombre());
		}
		return lista;
	}
	
	/*
	 * Inserta la solicitud de compra en la BD
	 * @param solicitud Solicitud de compra
	 */

	private void registrarBD(SolicitudCompra solicitud) {
		if (mecanicoController.registroSolicitud(solicitud)) {
			logger.info("Solicitud añadida correctamente.");
		} else {
			logger.error("Error al guardar la solicitud en la BD.");
		}
	}

	/**
	 * Genera un codigo a cada solicitud de compra.
	 */
	public String obtenerCodigo() {
		List<SolicitudCompra> solicitud = mecanicoController.cargarSolicitud();
		return mecanicoController.calcularCodigoSolicitud(solicitud);
	}

	
	
	

}

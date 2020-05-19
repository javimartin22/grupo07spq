package concesionario.cliente.ventana.departamentoCompras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.Proveedor;
import java.awt.Font;
import java.awt.Color;

/**
 * VentanaInformacionProveedores (Ventana para la visualizacion de la informacion de los Proveedores).
 */
public class VentanaInformacionProveedores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DepartmentoComprasController departamentoComprasController;

	/**
	 * Constructor de la VentanaInformacionProveedores.
	 * @param departmentoComprasController (Controlador de las Ventanas de Departamento Compras)
	 * @param proveedor (Objeto de tipo Proveedor)
	 * @param nickname (Nickname del DepartamentoCompras)
	 */
	public VentanaInformacionProveedores(DepartmentoComprasController departmentoComprasController,Proveedor proveedor, String nickname) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaInformacionProveedores(proveedor,nickname);
	}
	
	/**
	 * Inicializador del JFrame de la ventanaInformacionProveedores
	 * @param proveedor (Objeto tipo Proveedor)
	 * @param nickname (Nickname del DepartamentoCompras)
	 */
	public void  iniciarVentanaInformacionProveedores(Proveedor proveedor, String nickname) {
		setTitle("Informacion Proveedores Piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNombre.setBounds(24, 44, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblnomProv = new JLabel(proveedor.getNombre());
		lblnomProv.setBounds(201, 45, 156, 16);
		contentPane.add(lblnomProv);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPais.setBounds(24, 83, 61, 16);
		contentPane.add(lblPais);
		
		JLabel lblpaisProv = new JLabel(proveedor.getPais());
		lblpaisProv.setBounds(201, 84, 156, 16);
		contentPane.add(lblpaisProv);
		
		JLabel lblTiposDePieza = new JLabel("Tipos de piezas:");
		lblTiposDePieza.setFont(new Font("Dialog", Font.BOLD, 11));
		lblTiposDePieza.setBounds(24, 127, 135, 16);
		contentPane.add(lblTiposDePieza);
		
		JLabel lbltiposProv = new JLabel(proveedor.getTipoPiezas());
		lbltiposProv.setBounds(201, 128, 187, 16);
		contentPane.add(lbltiposProv);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VentanaComprarPiezas vcp = new VentanaComprarPiezas(departamentoComprasController, nickname);
				vcp.setVisible(true);
				dispose();
			}
		});
		
		btnSalir.setBounds(152, 173, 117, 29);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Informacion de los Proveedores de Piezas:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 7, 378, 14);
		contentPane.add(lblNewLabel);
	}
}

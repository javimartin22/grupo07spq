package concesionario.cliente.ventana.departamentoCompras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.ProveedorHerramientas;

import java.awt.Font;

public class VentanaInformacionProveedoresHerramientas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DepartmentoComprasController departamentoComprasController;
	
	public VentanaInformacionProveedoresHerramientas(DepartmentoComprasController departmentoComprasController,ProveedorHerramientas proveedor, String nickname) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaInformacionProveedoresHerramientas(proveedor,nickname);
	}

	public void  iniciarVentanaInformacionProveedoresHerramientas(ProveedorHerramientas proveedor, String nickname) {
		setTitle("Informacion proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNombre.setBounds(6, 32, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblnomProv = new JLabel(proveedor.getNombre());
		lblnomProv.setBounds(203, 33, 156, 16);
		contentPane.add(lblnomProv);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPais.setBounds(6, 71, 61, 16);
		contentPane.add(lblPais);
		
		JLabel lblpaisProv = new JLabel(proveedor.getPais());
		lblpaisProv.setBounds(203, 72, 156, 16);
		contentPane.add(lblpaisProv);
		
		JLabel lblTiposDePieza = new JLabel("Tipos de herramientas:");
		lblTiposDePieza.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTiposDePieza.setBounds(6, 115, 156, 16);
		contentPane.add(lblTiposDePieza);
		
		JLabel lbltiposProv = new JLabel(proveedor.gettipoHerramientas());
		lbltiposProv.setBounds(203, 116, 156, 16);
		contentPane.add(lbltiposProv);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VentanaComprarHerramientas vcp = new VentanaComprarHerramientas(departamentoComprasController, nickname);
				vcp.setVisible(true);
				dispose();
			}
		});
		
		btnSalir.setBounds(145, 162, 117, 29);
		contentPane.add(btnSalir);
	}
}

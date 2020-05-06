package concesionario.cliente.ventana.departamentoCompras;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.Herramientas;
import concesionario.datos.ProveedorHerramientas;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;

public class VentanaComprarHerramientas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private DepartmentoComprasController departamentoComprasController;

	public VentanaComprarHerramientas(DepartmentoComprasController departmentoComprasController, String nickname) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaComprarHerramientas(nickname);
	}

	public void iniciarVentanaComprarHerramientas(String nickname) {
		setTitle("Comprar herramientas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 367);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rellene los siguientes datos para realizar un pedido: ");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuDepartamentoCompras vmdc = new VentanaMenuDepartamentoCompras(departamentoComprasController, nickname);
				vmdc.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(396, 266, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setForeground(new Color(0, 0, 0));
		lblProveedor.setBounds(22, 68, 71, 16);
		contentPane.add(lblProveedor);
		
		JComboBox comboBoxProveedor = new JComboBox();
		for (String pr : crearProveedores()) {
			comboBoxProveedor.addItem(pr.toString());
		}
	
		comboBoxProveedor.setBounds(155, 63, 177, 27);
		contentPane.add(comboBoxProveedor);
		
		JLabel lblHerramienta = new JLabel("Herramienta:");
		lblHerramienta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHerramienta.setForeground(new Color(0, 0, 0));
		lblHerramienta.setBounds(22, 107, 81, 16);
		contentPane.add(lblHerramienta);
		
		JComboBox comboBoxHerramienta = new JComboBox();
		comboBoxHerramienta.setBounds(155, 102, 177, 27);
		contentPane.add(comboBoxHerramienta);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidades.setForeground(new Color(0, 0, 0));
		lblUnidades.setBounds(22, 147, 61, 16);
		contentPane.add(lblUnidades);
		
		JButton btnVerHerramientas = new JButton("Ver herramientas");
		btnVerHerramientas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxHerramienta.removeAllItems();
				String nombre = comboBoxProveedor.getSelectedItem().toString();
				for (String nom : crearHerramientasProveedores(nombre)) {
					comboBoxHerramienta.addItem(nom);
				}
			}
		});

		JButton btnInfo = new JButton("Ver informacion");
		btnInfo.setBounds(23, 266, 135, 29);
		contentPane.add(btnInfo);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = comboBoxProveedor.getSelectedItem().toString();
				VentanaInformacionProveedoresHerramientas viph = new VentanaInformacionProveedoresHerramientas(departamentoComprasController, obtenerProveedor(nombre), nickname);
				viph.setVisible(true);
				dispose();
			}
		});
		btnVerHerramientas.setBounds(396, 62, 138, 29);
		contentPane.add(btnVerHerramientas);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(155, 141, 44, 26);
		contentPane.add(spinner);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Herramientas herramienta = obtenerHerramientaProveedor(comboBoxHerramienta.getSelectedItem().toString());
					Integer unidades = (Integer)spinner.getValue();		
					realizarCompra(herramienta, unidades, nickname);
					
			}
		});
		btnComprar.setBounds(215, 266, 117, 29);
		contentPane.add(btnComprar);
		
		JLabel lblPrecioTotal = new JLabel("Tiempo:");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrecioTotal.setForeground(new Color(0, 0, 0));
		lblPrecioTotal.setBounds(22, 219, 81, 16);
		contentPane.add(lblPrecioTotal);
		
		JLabel lblPrecio = new JLabel("");
		lblPrecio.setBounds(155, 219, 177, 16);
		contentPane.add(lblPrecio);
		
		JButton btnVerPrecio = new JButton("Ver Tiempo");
		btnVerPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = comboBoxHerramienta.getSelectedItem().toString();
				int tiempo = calcularTiempo(nombre);
				if (tiempo > -1) {
					lblPrecio.setText(tiempo +" dias");
				}
			}
		});
		btnVerPrecio.setBounds(396, 213, 117, 29);
		contentPane.add(btnVerPrecio);
		
		
}
	
	public int calcularTiempo (String nombre) {
		int tiempo = 0;
		List<Herramientas> lista = departamentoComprasController.cargarListaHerramientas();
		for (Herramientas piezaProveedores : lista) {
			if (piezaProveedores.getNombre().equals(nombre)) {
				tiempo = piezaProveedores.getTiempo();
			} 
		}
		return tiempo;
	}
	
	public List<ProveedorHerramientas> cargarListaProveedoresHerramientas() {
		return departamentoComprasController.cargarListaProveedoresHerramientas();
	}
	
	public List<Herramientas> cargarListaHerramientas(){
		return departamentoComprasController.cargarListaHerramientas();
	}
	
	public ArrayList<String> crearProveedores() {
		List<ProveedorHerramientas> proveedores = cargarListaProveedoresHerramientas();
		ArrayList<String> nombres = new ArrayList<String>();
		for (ProveedorHerramientas proveedor : proveedores) {
			nombres.add(proveedor.getNombre());
		}
		return nombres;
	}
	
	public ArrayList<String> crearHerramientasProveedores(String nombre){
		List<ProveedorHerramientas> proveedores = departamentoComprasController.cargarListaProveedoresHerramientas();
		List<Herramientas> herramientas = departamentoComprasController.cargarListaHerramientas();
		String codigo = "";
		for (ProveedorHerramientas proveedor : proveedores) {
			if (proveedor.getNombre().equals(nombre)) {
				codigo = proveedor.getIdProveedor();
			}
		}
		ArrayList<String> lista = new ArrayList<String>();
		for (Herramientas piezaProveedores : herramientas) {
			if (piezaProveedores.getCodProveedor().equals(codigo)) {
				lista.add(piezaProveedores.getNombre());
			}
		}
		return lista;
	}
	
	public ProveedorHerramientas obtenerProveedor(String nombre) {
		List<ProveedorHerramientas> proveedores = cargarListaProveedoresHerramientas();
		for (ProveedorHerramientas prov : proveedores) {
			if (prov.getNombre().equals(nombre)) {
				return prov;
			}
		}
		return null;
	}
	
	public Herramientas obtenerHerramientaProveedor(String nombre) {
		List<Herramientas> herramientas = cargarListaHerramientas();
		for (Herramientas herramienta : herramientas) {
			if (herramienta.getNombre().equals(nombre)) {
				return herramienta;
			}
		}
		return null;
	}
	
	public void realizarCompra(Herramientas herramientaProv, int unidades, String nickname) {
				VentanaRegistroHerramientas vrp = new VentanaRegistroHerramientas(departamentoComprasController,herramientaProv, unidades, nickname);
				vrp.setVisible(true);
				dispose();
	}
}
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Proveedor;

import java.awt.Color;
import java.awt.Font;

public class VentanaComprarPiezas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private JTextField textFieldUnidades;
	private DepartmentoComprasController departamentoComprasController;

	public VentanaComprarPiezas(DepartmentoComprasController departmentoComprasController, String nickname) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaComprarPiezas(nickname);
	}

	public void iniciarVentanaComprarPiezas(String nickname) {
		setTitle("Comprar piezas");
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
		
		JLabel lblPieza = new JLabel("Pieza:");
		lblPieza.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPieza.setForeground(new Color(0, 0, 0));
		lblPieza.setBounds(22, 107, 52, 16);
		contentPane.add(lblPieza);
		
		JComboBox comboBoxPieza = new JComboBox();
		comboBoxPieza.setBounds(155, 102, 177, 27);
		contentPane.add(comboBoxPieza);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnidades.setForeground(new Color(0, 0, 0));
		lblUnidades.setBounds(22, 147, 61, 16);
		contentPane.add(lblUnidades);
		
		textFieldUnidades = new JTextField();
		textFieldUnidades.setBounds(155, 142, 177, 26);
		contentPane.add(textFieldUnidades);
		textFieldUnidades.setColumns(10);
		
		JButton btnVerPiezas = new JButton("Ver piezas");
		btnVerPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = comboBoxProveedor.getSelectedItem().toString();
				for (String nom : crearPiezasProveedores(nombre)) {
					comboBoxPieza.addItem(nom);
				}
			}
		});

		JButton btnInfo = new JButton("Ver informacion");
		btnInfo.setBounds(23, 266, 135, 29);
		contentPane.add(btnInfo);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = comboBoxProveedor.getSelectedItem().toString();
				VentanaInformacionProveedores vip = new VentanaInformacionProveedores(departamentoComprasController, obtenerProveedor(nombre), nickname);
				vip.setVisible(true);
				dispose();
			}
		});
		btnVerPiezas.setBounds(396, 62, 117, 29);
		contentPane.add(btnVerPiezas);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					PiezaProveedores pieza = obtenerPiezaProveedor(comboBoxPieza.getSelectedItem().toString());
					int unidades = Integer.parseInt(textFieldUnidades.getText());
					realizarCompra(pieza, unidades, nickname);
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
				String nombre = comboBoxPieza.getSelectedItem().toString();
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
		List<PiezaProveedores> lista = departamentoComprasController.cargarListaPiezasProveedores();
		for (PiezaProveedores piezaProveedores : lista) {
			if (piezaProveedores.getNombre().equals(nombre)) {
				tiempo = piezaProveedores.getTiempo();
			} 
		}
		return tiempo;
	}
	
	public List<Proveedor> cargarListaProveedores() {
		return departamentoComprasController.cargarListaProveedores();
	}
	
	public List<PiezaProveedores> cargarListaPiezaProveedores(){
		return departamentoComprasController.cargarListaPiezasProveedores();
	}
	
	public ArrayList<String> crearProveedores() {
		List<Proveedor> proveedores = cargarListaProveedores();
		ArrayList<String> nombres = new ArrayList<String>();
		for (Proveedor proveedor : proveedores) {
			nombres.add(proveedor.getNombre());
		}
		return nombres;
	}
	
	public ArrayList<String> crearPiezasProveedores(String nombre){
		List<Proveedor> proveedores = departamentoComprasController.cargarListaProveedores();
		List<PiezaProveedores> piezas = departamentoComprasController.cargarListaPiezasProveedores();
		String codigo = "";
		for (Proveedor proveedor : proveedores) {
			if (proveedor.getNombre().equals(nombre)) {
				codigo = proveedor.getIdProveedor();
			}
		}
		ArrayList<String> lista = new ArrayList<String>();
		for (PiezaProveedores piezaProveedores : piezas) {
			if (piezaProveedores.getCodProveedor().equals(codigo)) {
				lista.add(piezaProveedores.getNombre());
			}
		}
		return lista;
	}
	
	public Proveedor obtenerProveedor(String nombre) {
		List<Proveedor> proveedores = cargarListaProveedores();
		for (Proveedor prov : proveedores) {
			if (prov.getNombre().equals(nombre)) {
				return prov;
			}
		}
		return null;
	}
	
	public PiezaProveedores obtenerPiezaProveedor(String nombre) {
		List<PiezaProveedores> piezas = cargarListaPiezaProveedores();
		for (PiezaProveedores pieza : piezas) {
			if (pieza.getNombre().equals(nombre)) {
				return pieza;
			}
		}
		return null;
	}
	
	public void realizarCompra(PiezaProveedores piezaProveedor, int unidades, String nickname) {
				VentanaRegistoPiezas vrp = new VentanaRegistoPiezas(departamentoComprasController,piezaProveedor, unidades, nickname);
				vrp.setVisible(true);
				dispose();
	}
}
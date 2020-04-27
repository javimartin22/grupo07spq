package concesionario.cliente.ventana.departamentoCompras;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionario.cliente.controller.DepartmentoComprasController;
import concesionario.datos.PiezaProveedores;
import concesionario.datos.Proveedor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;

public class VentanaComprarPiezas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private JTextField textFieldUnidades;
	private DepartmentoComprasController departamentoComprasController;


	public VentanaComprarPiezas(DepartmentoComprasController departmentoComprasController) {
		setResizable(false);
		this.departamentoComprasController = departmentoComprasController;
		iniciarVentanaComprarPiezas();
	}

	/**
	 * Create the frame.
	 */
	public void iniciarVentanaComprarPiezas() {
		setTitle("Comprar piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 367);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFiltrar = new JMenu("Filtro");
		menuBar.add(mnFiltrar);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rellene los siguientes datos para realizar un pedido: ");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(396, 266, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProveedor.setForeground(new Color(0, 0, 0));
		lblProveedor.setBounds(22, 68, 71, 16);
		contentPane.add(lblProveedor);
		
		ArrayList<String> proveedores = new ArrayList<>();
		proveedores.add("BOSCH");
		proveedores.add("CASTROL");
		proveedores.add("MONROE");
		proveedores.add("BREMBO");
		proveedores.add("CONTITECH");
		
		JComboBox comboBoxProveedor = new JComboBox();
		for (String pr : proveedores) {
			comboBoxProveedor.addItem(pr.toString());
		}
	
		ArrayList<String> tiposBosch = new ArrayList<>();
		tiposBosch.add("Frenos");
		tiposBosch.add("Filtros");
		tiposBosch.add("Motor");
	
		
		ArrayList<String> tiposCastrol = new ArrayList<>();
		tiposCastrol.add("Amortiguacion");
		tiposCastrol.add("Aceites y liquidos");
		
		ArrayList<String> tiposMonroe = new ArrayList<>();
		tiposMonroe.add("Suspension y brazos");
		tiposMonroe.add("Carroceria");
		
		ArrayList<String> tiposBrembo = new ArrayList<>();
		tiposBrembo.add("Direccion");
		tiposBrembo.add("Sistema electrico");

		
		ArrayList<String> tiposContitech = new ArrayList<>();
		tiposContitech.add("Limpieza de cristales");
		
		ArrayList<String> piezasBosch = new ArrayList<>();
		piezasBosch.add("Pastillas de freno");
		piezasBosch.add("Discos de freno");
		piezasBosch.add("Kit reparacion frenos");
		piezasBosch.add("Filtro de aceite");
		piezasBosch.add("Filtro de aire");
		piezasBosch.add("Filtro de combustible");
		piezasBosch.add("Taco de motor");
		piezasBosch.add("Polea polv");
		piezasBosch.add("Tapon de carter");
	
		
		ArrayList<String> piezasCastrol = new ArrayList<>();
		piezasCastrol.add("Amortiguadores");
		piezasCastrol.add("Copela");
		piezasCastrol.add("Guardapolvos");
		piezasCastrol.add("Aceite de motor");
		piezasCastrol.add("Aceite de transmision");
		piezasCastrol.add("Liquido de frenos");
		
		ArrayList<String> piezasMonroe = new ArrayList<>();
		piezasMonroe.add("Buje de rueda");
		piezasMonroe.add("Silentblock");
		piezasMonroe.add("Bieletas");
		piezasMonroe.add("Amortiguadores maletero");
		piezasMonroe.add("Retrovisor");
		piezasMonroe.add("Piloto trasero");
		
		ArrayList<String> piezasBrembo = new ArrayList<>();
		piezasBrembo.add("Rotula");
		piezasBrembo.add("Rotula axial");
		piezasBrembo.add("Barra");
		piezasBrembo.add("Alternador");
		piezasBrembo.add("Motor de arranque");
		piezasBrembo.add("Regulador del alternador");

		
		ArrayList<String> piezasContitech = new ArrayList<>();
		piezasContitech.add("Escobillas");
		piezasContitech.add("Bomba");
		piezasContitech.add("Detergente");

		
		comboBoxProveedor.setBounds(155, 63, 177, 27);
		contentPane.add(comboBoxProveedor);
		
		JLabel lblPieza = new JLabel("Pieza");
		lblPieza.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPieza.setForeground(new Color(0, 0, 0));
		lblPieza.setBounds(22, 107, 52, 16);
		contentPane.add(lblPieza);
		
		JComboBox comboBoxPieza = new JComboBox();
		comboBoxPieza.setBounds(155, 102, 177, 27);
		contentPane.add(comboBoxPieza);
		
		JLabel lblUnidades = new JLabel("Unidades");
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

//				if((comboBoxProveedor.getSelectedIndex()==0)) {
//					comboBoxPieza.removeAllItems();
//					mnFiltrar.removeAll();
//					JMenuItem mntmFrenos = new JMenuItem("Frenos");
//					mnFiltrar.add(mntmFrenos);
//					
//					mntmFrenos.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Pastillas de Freno", "Discos de freno", "Kit reparacion frenos"}));
//						}
//					});
//										
//					
//					JMenuItem mntmFiltros= new JMenuItem("Filtros");
//					mnFiltrar.add(mntmFiltros);
//					mntmFiltros.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Filtro de aceite", "Filtro de aire", "Filtro de combustible"}));
//						}
//					});
//					
//					JMenuItem mntmMotor = new JMenuItem("Motor");
//					mnFiltrar.add(mntmMotor);
//					
//					mntmMotor.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Taco de motor", "Correa poly v", "Tapon de carter"}));
//						}
//					});
//					
//					
//					for (String pr : piezasBosch) {
//						
//						comboBoxPieza.addItem(pr.toString());
//					}
////				
//
//				}
//				
//				if((comboBoxProveedor.getSelectedIndex()==1)) {
//					comboBoxPieza.removeAllItems();
//					mnFiltrar.removeAll();
//					JMenuItem mntmAmort= new JMenuItem("Amortiguacion");
//					mnFiltrar.add(mntmAmort);
//					mntmAmort.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Amortiguadores", "Copela", "Guardapolvos"}));
//						}
//					});
//					
//					JMenuItem mntmAceite = new JMenuItem("Aceites y liquidos");
//					mnFiltrar.add(mntmAceite);
//					
//					mntmAceite.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Aceite de motor", "Aceite de transmision", "Liquido de frenos"}));
//						}
//					});
//					
//					for (String pr : piezasCastrol) {
//						comboBoxPieza.addItem(pr.toString());
//					}
//				}
//				
//				if((comboBoxProveedor.getSelectedIndex()==2)) {
//					comboBoxPieza.removeAllItems();
//
//					mnFiltrar.removeAll();
//					JMenuItem mntmSuspension= new JMenuItem("Suspension y brazos");
//					mnFiltrar.add(mntmSuspension);
//					mntmSuspension.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Buje de rueda", "Silentblock", "Bieletas"}));
//						}
//					});
//					
//					JMenuItem mntmCarroceria = new JMenuItem("Carroceria");
//					mnFiltrar.add(mntmCarroceria);
//					
//					mntmCarroceria.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Amortiguadores maletero", "Retrovisor", "Piloto trasero"}));
//						}
//					});
//					
//					for (String pr : piezasMonroe) {
//						comboBoxPieza.addItem(pr.toString());
//					}
//				}
//				
//				if((comboBoxProveedor.getSelectedIndex()==3)) {
//					comboBoxPieza.removeAllItems();
//					mnFiltrar.removeAll();
//					JMenuItem mntmDireccion= new JMenuItem("Direccion");
//					mnFiltrar.add(mntmDireccion);
//					mntmDireccion.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Rotula", "Rotula axial", "Barra"}));
//						}
//					});
//					
//					JMenuItem mntmSistElect = new JMenuItem("Sistema electrico");
//					mnFiltrar.add(mntmSistElect);
//					
//					mntmSistElect.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {comboBoxPieza.setModel(new DefaultComboBoxModel(new String[] {"Alternador", "Motor de arranque", "Regulador de alternador"}));
//						}
//					});
//					
//					for (String pr : piezasBrembo) {
//						comboBoxPieza.addItem(pr.toString());
//					}
//				
//				}
//				
//				if((comboBoxProveedor.getSelectedIndex()==4)) {
//					comboBoxPieza.removeAllItems();
//					mnFiltrar.removeAll();
//					for (String pr : piezasContitech) {
//						comboBoxPieza.addItem(pr.toString());
//					}				
//				}
//				
			}
			
			
		});
		

		JButton btnInfo = new JButton("Ver informacion");
		btnInfo.setBounds(23, 266, 135, 29);
		contentPane.add(btnInfo);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxProveedor.getSelectedIndex()==0) {
					VentanaInformacionProveedores vib = new VentanaInformacionProveedores(departamentoComprasController,0);
					vib.setVisible(true);
					vib.setResizable(false);
					
				}
				
				if(comboBoxProveedor.getSelectedIndex()==1) {
					VentanaInformacionProveedores vic = new VentanaInformacionProveedores(departamentoComprasController,1);
					vic.setVisible(true);
					vic.setResizable(false);
					
				}
				
				if(comboBoxProveedor.getSelectedIndex()==2) {
					VentanaInformacionProveedores vim = new VentanaInformacionProveedores(departamentoComprasController,2);
					vim.setVisible(true);
					vim.setResizable(false);
					
				}
				
				if(comboBoxProveedor.getSelectedIndex()==3) {
					VentanaInformacionProveedores vibr = new VentanaInformacionProveedores(departamentoComprasController,3);
					vibr.setVisible(true);
					vibr.setResizable(false);
					
				}
				
				if(comboBoxProveedor.getSelectedIndex()==4) {
					VentanaInformacionProveedores vico = new VentanaInformacionProveedores(departamentoComprasController,4);
					vico.setVisible(true);
					vico.setResizable(false);
					
				}

			}
		});
		btnVerPiezas.setBounds(396, 62, 117, 29);
		contentPane.add(btnVerPiezas);
		
		/*
		 * Comprobamos compra
		 */
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setEnabled(false);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldUnidades.getText().isEmpty() || comboBoxPieza.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Error , rellene todos los campos.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Compra realizada");
				}
				
			}
		});
		btnComprar.setBounds(215, 266, 117, 29);
		contentPane.add(btnComprar);
		
		JLabel lblPrecioTotal = new JLabel("Precio");
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrecioTotal.setForeground(new Color(0, 0, 0));
		lblPrecioTotal.setBounds(22, 219, 81, 16);
		contentPane.add(lblPrecioTotal);
		
		JLabel lblPrecio = new JLabel("");
		lblPrecio.setBounds(155, 219, 177, 16);
		contentPane.add(lblPrecio);
		
		JButton btnVerPrecio = new JButton("Ver precio");
		btnVerPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldUnidades.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error, rellene todos los campos.");
				}
				else {
					btnComprar.setEnabled(true);
				}
				String unidades  = textFieldUnidades.getText();
				int uds = Integer.parseInt(unidades);
				String piezas = comboBoxPieza.getSelectedItem().toString();
				
				int precio = calcularPrecio(piezas,uds);
				String precioFinal = String.valueOf(precio);
				
				lblPrecio.setText(precioFinal +" €");
			}
		});
		btnVerPrecio.setBounds(396, 213, 117, 29);
		contentPane.add(btnVerPrecio);
	
		
		
		
		
		
				
			}
	
	private static int calcularPrecio (String piezas, int unidades) {
		int precio = 0;
		int precioPorUnidad;
		if(piezas.equals("Pastillas de freno")) {
			precioPorUnidad =  15;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Discos de freno")) {
			precioPorUnidad =  9;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Kit reparacion frenos")) {
			precioPorUnidad =  6;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Filtro de aceite")) {
			precioPorUnidad =  4;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Filtro de aire")) {
			precioPorUnidad =  6;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Filtro de combustible")) {
			precioPorUnidad =  8;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Taco de motor")) {
			precioPorUnidad =  28;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Polea polv")) {
			precioPorUnidad =  39;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Tapon de carter")) {
			precioPorUnidad =  2;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Amortiguadores")) {
			precioPorUnidad =  28;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Copela")) {
			precioPorUnidad =  10;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Guardapolvos")) {
			precioPorUnidad =  9;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Aceite de motor")) {
			precioPorUnidad =  5;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Aceite de transmision")) {
			precioPorUnidad =  9;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Liquido de frenos")) {
			precioPorUnidad =  2;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Buje de rueda")) {
			precioPorUnidad =  24;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Silentblock")) {
			precioPorUnidad =  2;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Bieletas")) {
			precioPorUnidad =  8;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Amortiguadores maletero")) {
			precioPorUnidad =  6;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Retrovisor")) {
			precioPorUnidad =  21;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Piloto trasero")) {
			precioPorUnidad =  27;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Rotula")) {
			precioPorUnidad =  8;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Rotula axial")) {
			precioPorUnidad =  10;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Barra")) {
			precioPorUnidad =  6;
			precio = precioPorUnidad * unidades;
		}
		if(piezas.equals("Alternador")) {
			precioPorUnidad =  30;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Motor de arranque")) {
			precioPorUnidad =  80;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Regulador del alternador")) {
			precioPorUnidad =  18;
			precio = precioPorUnidad * unidades;
		}
				
		if(piezas.equals("Escobillas")) {
			precioPorUnidad =  2;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Bomba")) {
			precioPorUnidad =  3;
			precio = precioPorUnidad * unidades;
		}
		
		if(piezas.equals("Detergente")) {
			precioPorUnidad =  2;
			precio = precioPorUnidad * unidades;
		}

		
		return precio;
	}
	
	public List<Proveedor> cargarListaProveedores() {
		System.out.println("Hola");
		return departamentoComprasController.cargarListaProveedores();
	}
	
	public List<PiezaProveedores> cargarListaPiezaProveedores(){
		for (PiezaProveedores pieza : departamentoComprasController.cargarListaPiezasProveedores()) {
			System.out.println(pieza.getCodigo());
			
		}
		return departamentoComprasController.cargarListaPiezasProveedores();
	}
}



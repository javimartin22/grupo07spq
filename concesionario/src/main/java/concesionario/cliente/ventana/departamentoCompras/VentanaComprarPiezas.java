//package concesionario.cliente.ventana.departamentoCompras;
//
//
//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Vector;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//
//public class VentanaComprarPiezas extends JFrame {
//
//	private JPanel contentPane;	
//	private JTextField textFieldUnidades;
//
//
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaComprarPiezas frame = new VentanaComprarPiezas();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public VentanaComprarPiezas() {
//		setResizable(false);
//		setTitle("Comprar piezas");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 392, 282);
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		JMenu mnFiltrar = new JMenu("Filtro");
//		menuBar.add(mnFiltrar);
//		
//		
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblNewLabel = new JLabel("Rellene los siguientes datos para realizar un pedido: ");
//		lblNewLabel.setBounds(22, 20, 426, 16);
//		contentPane.add(lblNewLabel);
//		
//		
//		
//		
//		JButton btnComprar = new JButton("Comprar");
//		btnComprar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Compra realizada");
//			}
//		});
//		btnComprar.setBounds(155, 191, 117, 29);
//		contentPane.add(btnComprar);
//		
//		JButton btnCancelar = new JButton("Cancelar");
//		btnCancelar.setBounds(269, 191, 117, 29);
//		contentPane.add(btnCancelar);
//		
//		JLabel lblProveedor = new JLabel("Proveedor");
//		lblProveedor.setBounds(22, 68, 71, 16);
//		contentPane.add(lblProveedor);
//		
//		ArrayList<String> proveedores = new ArrayList<>();
//		proveedores.add("BOSCH");
//		proveedores.add("CASTROL");
//		proveedores.add("MONROE");
//		proveedores.add("BREMBO");
//		proveedores.add("CONTITECH");
//		
//		
//
//		
//		
//		JComboBox comboBoxProveedor = new JComboBox();
//		for (String pr : proveedores) {
//			comboBoxProveedor.addItem(pr.toString());
//		}
//		
////		ArrayList<String> tipos = new ArrayList<>();
////		tipos.add("Frenos");
////		tipos.add("Filtros");
////		tipos.add("Motor");
////		tipos.add("Amortiguacion");
////		tipos.add("Aceites y liquidos");
////		tipos.add("Suspension y brazos");
////		tipos.add("Carroceria");
////		tipos.add("Direccion");
////		tipos.add("Sistema electrico");
////		tipos.add("Limpieza de cristales");
//		
//	
//		ArrayList<String> tiposBosch = new ArrayList<>();
//		tiposBosch.add("Frenos");
//		tiposBosch.add("Filtros");
//		tiposBosch.add("Motor");
//	
//		
//		ArrayList<String> tiposCastrol = new ArrayList<>();
//		tiposCastrol.add("Amortiguacion");
//		tiposCastrol.add("Aceites y liquidos");
//		
//		ArrayList<String> tiposMonroe = new ArrayList<>();
//		tiposMonroe.add("Suspension y brazos");
//		tiposMonroe.add("Carroceria");
//		
//		ArrayList<String> tiposBrembo = new ArrayList<>();
//		tiposBrembo.add("Direccion");
//		tiposBrembo.add("Sistema electrico");
//
//		
//		ArrayList<String> tiposContitech = new ArrayList<>();
//		tiposContitech.add("Limpieza de cristales");
//		
//		ArrayList<String> piezasBosch = new ArrayList<>();
//		piezasBosch.add("Pastillas de freno");
//		piezasBosch.add("Discos de freno");
//		piezasBosch.add("Kit reparacion frenos");
//		piezasBosch.add("Filtro de aceite");
//		piezasBosch.add("Filtro de aire");
//		piezasBosch.add("Filtro de combustible");
//		piezasBosch.add("Taco de motor");
//		piezasBosch.add("Polea polv");
//		piezasBosch.add("Tapon de carter");
//	
//		
//		ArrayList<String> piezasCastrol = new ArrayList<>();
//		piezasCastrol.add("Amortiguadores");
//		piezasCastrol.add("Copela");
//		piezasCastrol.add("Guardapolvos");
//		piezasCastrol.add("Aceite de motor");
//		piezasCastrol.add("Aceite de transmision");
//		piezasCastrol.add("Liquido de frenos");
//		
//		ArrayList<String> piezasMonroe = new ArrayList<>();
//		piezasMonroe.add("Buje de rueda");
//		piezasMonroe.add("Silentblock");
//		piezasMonroe.add("Bieletas");
//		piezasMonroe.add("Amortiguadores maletero");
//		piezasMonroe.add("Retrovisor");
//		piezasMonroe.add("Piloto trasero");
//		
//		ArrayList<String> piezasBrembo = new ArrayList<>();
//		piezasBrembo.add("Rotula");
//		piezasBrembo.add("Rotula axial");
//		piezasBrembo.add("Barra");
//		piezasBrembo.add("Alternador");
//		piezasBrembo.add("Motor de arranque");
//		piezasBrembo.add("Regulador del alternador");
//
//		
//		ArrayList<String> piezasContitech = new ArrayList<>();
//		piezasContitech.add("Escobillas");
//		piezasContitech.add("Bomba");
//		piezasContitech.add("Detergente");
//
//		
//		comboBoxProveedor.setBounds(95, 64, 177, 27);
//		contentPane.add(comboBoxProveedor);
//		
//		JLabel lblPieza = new JLabel("Pieza");
//		lblPieza.setBounds(22, 107, 52, 16);
//		contentPane.add(lblPieza);
//		
//		JComboBox comboBoxPieza = new JComboBox();
//		comboBoxPieza.setBounds(95, 103, 177, 27);
//		contentPane.add(comboBoxPieza);
//		
//		JLabel lblUnidades = new JLabel("Unidades");
//		lblUnidades.setBounds(22, 147, 61, 16);
//		contentPane.add(lblUnidades);
//		
//		textFieldUnidades = new JTextField();
//		textFieldUnidades.setBounds(95, 142, 177, 26);
//		contentPane.add(textFieldUnidades);
//		textFieldUnidades.setColumns(10);
//		
//		JButton btnVerPiezas = new JButton("Ver piezas");
//		btnVerPiezas.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
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
//				
//				
//			}
//			
//			
//		});
//		
//
//		JButton btnInfo = new JButton("Ver informacion");
//		btnInfo.setBounds(22, 191, 135, 29);
//		contentPane.add(btnInfo);
//		btnInfo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(comboBoxProveedor.getSelectedIndex()==0) {
//					VentanaInformacionProveedores vib = new VentanaInformacionProveedores(0);
//					vib.setVisible(true);
//					vib.setResizable(false);
//					
//				}
//				
//				if(comboBoxProveedor.getSelectedIndex()==1) {
//					VentanaInformacionProveedores vic = new VentanaInformacionProveedores(1);
//					vic.setVisible(true);
//					vic.setResizable(false);
//					
//				}
//				
//				if(comboBoxProveedor.getSelectedIndex()==2) {
//					VentanaInformacionProveedores vim = new VentanaInformacionProveedores(2);
//					vim.setVisible(true);
//					vim.setResizable(false);
//					
//				}
//				
//				if(comboBoxProveedor.getSelectedIndex()==3) {
//					VentanaInformacionProveedores vibr = new VentanaInformacionProveedores(3);
//					vibr.setVisible(true);
//					vibr.setResizable(false);
//					
//				}
//				
//				if(comboBoxProveedor.getSelectedIndex()==4) {
//					VentanaInformacionProveedores vico = new VentanaInformacionProveedores(4);
//					vico.setVisible(true);
//					vico.setResizable(false);
//					
//				}
//
//			}
//		});
//		btnVerPiezas.setBounds(269, 63, 117, 29);
//		contentPane.add(btnVerPiezas);
//		
//	
//		
//		
//		
//		
//		
//				
//			}
//	
//	
//	
//	}
//
//

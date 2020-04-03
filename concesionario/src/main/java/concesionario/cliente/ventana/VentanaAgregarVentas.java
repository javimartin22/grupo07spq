package concesionario.cliente.ventana;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javax.swing.JLayeredPane;

public class VentanaAgregarVentas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private LoginController loginController;

	public VentanaAgregarVentas(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaAgregarVentas(nickname);
	}


	/**
	 * Create the frame.
	 */
	public void iniciarVentanaAgregarVentas(String nickname) {

		setResizable(false);
		setTitle("Registrar ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Si desea registrar una venta rellene los siguientes datos:");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(231, 56, 228, 26);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarVentas vvv = new VentanaVisualizarVentas(loginController, nickname);
				ventana.setVisible(true);
				dispose();
				
				
			}
		});
		btnCancelar.setBounds(101, 186, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblContrasenya = new JLabel("Modelo: ");
		lblContrasenya.setBounds(53, 135, 150, 16);
		contentPane.add(lblContrasenya);
		
		JLabel lblNickname = new JLabel("Marca: ");
		lblNickname.setBounds(53, 97, 150, 16);
		contentPane.add(lblNickname);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(231, 93, 228, 26);
		contentPane.add(textFieldMarca);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	boolean datos = comprobarDatos();
			//	if (datos) {
					String marca = textFieldMarca.getText();
					marca = marca.toUpperCase();
					String modelo = textFieldModelo.getText();
					modelo = modelo.toUpperCase();
					String dniComprador = textFieldDNI.getText();	
					//ArrayList<String > array = cargarCodigo(st);
					//String codVentas =  generarCodigo(array);
					//String codVentas = JOptionPane.showInputDialog("Introduzca cod ventas: ");
					String comercial = JOptionPane.showInputDialog("Introduzca comercial: ");
					comercial = comercial.toUpperCase();
					//BD.cochesVendidodsInsert(st,codVentas , comercial, dniComprador, marca, modelo);
				
//					VentanaVisualizarVentas ventana = new VentanaVisualizarVentas();
//					ventana.setVisible(true);
//					dispose();
			//	} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar rellenados.");
				}
		//	}
		});
		btnRegistrar.setBounds(275, 186, 117, 29);
		contentPane.add(btnRegistrar);
		
		JLabel lblDNIComprador = new JLabel("DNIComprador:");
		lblDNIComprador.setBounds(53, 61, 117, 16);
		contentPane.add(lblDNIComprador);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(231, 130, 228, 26);
		contentPane.add(textFieldModelo);
		textFieldModelo.setColumns(10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(291, 168, 1, 1);
		contentPane.add(layeredPane);
	}
	
	
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldDNI.getText().isEmpty() && !textFieldMarca.getText().isEmpty() && !textFieldModelo.getText().isEmpty()) {
			datos = true;
		} 
		return datos;
	}
//	
//	private static ArrayList<String> cargarCodigo(Statement st)  {
//		ResultSet rst = BD.ventasTodosSelect(st);
//		 ArrayList<String> array = new ArrayList<>();
//		try {
//			while (rst.next()) {
//			   String s = rst.getString("codigoVenta");
//			   array.add(s);
//			    System.out.println(s);
//		}} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return array;
//	}	
//		
	private static String generarCodigo (ArrayList<String> array) {
		String s="V";
		int num = 0;
		String cod = s+num;
		if(array.isEmpty()) {
			cod = "V0";
		}
		else {
			for(int i=0;i<array.size();i++) {
				if(cod.equals(array.get(i))) {
					num = num +1;
					cod = s+num;
				}
				else {
					cod = s+num;
					
				}
			}
		}
		return cod;
	}
}
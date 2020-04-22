package concesionario.cliente.ventana.departamentoCompras;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaInformacionContitech extends JFrame{


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInformacionContitech frame = new VentanaInformacionContitech();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInformacionContitech() {
		setTitle("Informacion proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 32, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblBosch = new JLabel("Contitech");
		lblBosch.setBounds(169, 32, 61, 16);
		contentPane.add(lblBosch);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(6, 71, 61, 16);
		contentPane.add(lblPais);
		
		JLabel lblAlemania = new JLabel("Alemania");
		lblAlemania.setBounds(169, 71, 61, 16);
		contentPane.add(lblAlemania);
		
		JLabel lblTiposDePieza = new JLabel("Tipos de pieza");
		lblTiposDePieza.setBounds(6, 115, 102, 16);
		contentPane.add(lblTiposDePieza);
		
		JLabel lblNewLabel = new JLabel("Limpieza de cristales");
		lblNewLabel.setBounds(169, 115, 141, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSalir.setBounds(129, 154, 117, 29);
		contentPane.add(btnSalir);
	}
	


}

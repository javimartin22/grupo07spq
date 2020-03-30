import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEmpleado extends JFrame {
	private JTextField textField;
	DefaultListModel modelo;
	
	public VentanaEmpleado() {
		setTitle("Gestión de empleados");
		getContentPane().setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 agregarLista();
				
			}
		});
		btnAgregar.setBounds(171, 243, 117, 29);
		getContentPane().add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(299, 243, 117, 29);
		getContentPane().add(btnEliminar);
		
		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(42, 243, 117, 29);
		getContentPane().add(btnVer);
		
		JList listaEmpleados = new JList();
		
		modelo = new DefaultListModel<>();
		listaEmpleados.setModel(modelo);
		listaEmpleados.setBackground(Color.WHITE);
		listaEmpleados.setBounds(42, 217, 374, -142);
		getContentPane().add(listaEmpleados);
		
		textField = new JTextField();
		textField.setBounds(103, 22, 297, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(19, 27, 61, 16);
		getContentPane().add(lblNombre);
		
		listaEmpleados.setVisible(true);
	}
	
	public void agregarLista() {
		String valor = textField.getText();
		modelo.addElement(valor);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaEmpleado ventanaempleado = new VentanaEmpleado();
		ventanaempleado.setVisible(true);
		ventanaempleado.setSize(480,360);
		ventanaempleado.setLocationRelativeTo(null);
		ventanaempleado.setVisible(true);
		ventanaempleado.setResizable(false);

	}
}

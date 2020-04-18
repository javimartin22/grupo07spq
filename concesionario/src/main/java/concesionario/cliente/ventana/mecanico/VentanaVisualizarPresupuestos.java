package concesionario.cliente.ventana.mecanico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import concesionario.cliente.controller.Controller;
import concesionario.datos.Presupuesto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaVisualizarPresupuestos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	private JTable table;
	
	public VentanaVisualizarPresupuestos(Controller controller, String nickname) {
		this.controller = controller;
		ventanaVisualizarPresupuestos(nickname);
	}
	
	public void ventanaVisualizarPresupuestos(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 797, 273);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String codigo = (String) table.getModel().getValueAt(fila, 1);
				try {
					Response response = controller.seleccionarPresupuesto(codigo); //estoy aqui
					if (response.getStatus() == Status.OK.getStatusCode()) {
						Presupuesto presupuesto = response.readEntity(Presupuesto.class);
						generarDoc(presupuesto);
					} else {
						System.out.println("fallo");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(552, 305, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Regresar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(controller, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(191, 305, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cargar Tabla");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnNewButton_3.setBounds(290, 305, 113, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Registrar Nuevo");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroPresupuesto vrp = new VentanaRegistroPresupuesto(controller, nickname, 1);
				vrp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(413, 305, 129, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Ver Presupuesto");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String codigo = (String) table.getModel().getValueAt(fila, 1);
				verPresupuesto(codigo, nickname);
			}
		});
		btnNewButton_5.setBounds(651, 305, 145, 23);
		contentPane.add(btnNewButton_5);
	}
	
	public void cargarTabla(JTable table) {
		List<Presupuesto> presupuestos = controller.cargarTablaPresupuesto();
		String[] columnNames = {"Fecha", "Codigo", "DNI Cliente", "Nom Mecanico", "Marca Coche", "Modelo Coche"};
		
		if (!presupuestos.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.setColumnIdentifiers(columnNames);
			
			for (Presupuesto p : presupuestos) {
				Object[] o = new Object[7];
				o[1] = p.getCodigo();
				o[0] = p.getFecha();
				o[2] = p.getDniCliente();
				o[3] = p.getMecanico();
				o[4] = p.getMarca();
				o[5] = p.getModelo();
				model.addRow(o);
			}
		} else {
			System.out.println("llega mal");
		}
	}
	
	public void verPresupuesto(String codigo, String nickname) {
		Response response = controller.seleccionarPresupuesto(codigo); //estoy aqui
		if (response.getStatus() == Status.OK.getStatusCode()) {
			Presupuesto presupuesto = response.readEntity(Presupuesto.class);
			VentanaInformacionPresupuesto vip = new VentanaInformacionPresupuesto(controller, nickname, presupuesto);
			vip.setVisible(true);
			dispose();
		} else {
			System.out.println("fallo");
		}
	}
	
	public void generarDoc(Presupuesto presupuesto) throws IOException {
		double precioConIVA = presupuesto.getPrecio() + (presupuesto.getPrecio() * 0.21);
		 try (PDDocument document = new PDDocument()) {
			 PDPage page = new PDPage(PDRectangle.A4);
			 document.addPage(page);

			 PDPageContentStream contentStream = new PDPageContentStream(document, page);

			 // Text
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 32);
			 contentStream.newLineAtOffset( 175, page.getMediaBox().getHeight() - 50);
			 contentStream.showText("Presupuesto Taller");
			 contentStream.endText();
			 
			 //Text 2:
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ROMAN, 13);
			 contentStream.newLineAtOffset(25, 750);;
			 contentStream.showText("A continuacion podemos ver el presupuesto de los arreglos necesarios del vehiculo " + presupuesto.getDniCliente() + ":");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 725);;
			 contentStream.showText("- Codigo Presupuesto: " + presupuesto.getCodigo());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 705);;
			 contentStream.showText("- DNI del Cliente:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 690);;
			 contentStream.showText(presupuesto.getDniCliente());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 670);;
			 contentStream.showText("- Marca del Vehiculo:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 655);;
			 contentStream.showText(presupuesto.getMarca());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 635);;
			 contentStream.showText("- Modelo del Vehiculo:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 620);;
			 contentStream.showText(presupuesto.getModelo());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 600);;
			 contentStream.showText("- Problema:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 585);;
			 contentStream.showText(presupuesto.getProblema());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 565);;
			 contentStream.showText("- Piezas:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 550);;
			 contentStream.showText(presupuesto.getListaPiezas());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(35, 530);;
			 contentStream.showText("- Observaciones:");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 11);
			 contentStream.newLineAtOffset(45, 515);;
			 contentStream.showText(presupuesto.getObservaciones());
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(450, 100);;
			 contentStream.showText("Precio sin IVA:  " + presupuesto.getPrecio() + "€");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			 contentStream.newLineAtOffset(450, 80);;
			 contentStream.showText("Precio con IVA:  " + precioConIVA + "€");
			 contentStream.endText();
			 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 8);
			 contentStream.newLineAtOffset(125, 20);;
			 contentStream.showText("Este presupuesto debe sera aprobado por el cliente y cumple todas las normas de proteccion de datos");
			 contentStream.endText();
			 
			
			 
			 contentStream.close();
			 
			 document.save("Presupuestos/" + presupuesto.getCodigo() + ".pdf");
		 }
	}
}

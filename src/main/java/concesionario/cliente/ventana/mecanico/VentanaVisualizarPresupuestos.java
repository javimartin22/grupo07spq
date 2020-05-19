package concesionario.cliente.ventana.mecanico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import concesionario.cliente.controller.MecanicoController;
import concesionario.datos.Presupuesto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 *VentanaVisualizarPresupuestos (Ventana para la visualizacion de los Presupuestos)
 */
public class VentanaVisualizarPresupuestos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MecanicoController mecanicoController;
	private JTable table;
	final Logger logger = LoggerFactory.getLogger(VentanaVisualizarPresupuestos.class);
	static int iteration = 0;
	
	/**
	 * Constructor de la VentanaVisualizarPresupuestos.
	 * @param controller (Objeto MecanicoController).
	 * @param nickname (Nickname del Mecanico).
	 */
	public VentanaVisualizarPresupuestos(MecanicoController controller, String nickname) {
		setTitle("Visualizar Presupuesto");
		this.mecanicoController = controller;
		ventanaVisualizarPresupuestos(nickname);
	}
	
	/**
	 * Creacion del Frame de la VentanaVisualizarPresupuestos.
	 * @param nickname (Nickname del Mecanico).
	 */
	public void ventanaVisualizarPresupuestos(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 411);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Filtro");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Codigo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigo = JOptionPane.showInputDialog("Introduzca el codigo que desea buscar:");
				cargarTablaResctricciones(table, 0, codigo);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cliente");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = JOptionPane.showInputDialog("Introduzca el DNI del cliente que desea buscar:");
				cargarTablaResctricciones(table, 1, nombre);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Problema");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String problema = JOptionPane.showInputDialog("Introduzca el problema que desea buscar:");
				cargarTablaResctricciones(table, 2, problema);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
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
					if (mecanicoController.seleccionarPresupuesto(codigo) != null) {
						generarDoc(mecanicoController.seleccionarPresupuesto(codigo));
						logger.info("Creado presupuesto en documento.");
					} else {
						logger.error("Error al crear documento del presupuesto.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(563, 305, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Regresar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(mecanicoController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(202, 305, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cargar Tabla");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnNewButton_3.setBounds(301, 305, 113, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Registrar Nuevo");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistroPresupuesto vrp = new VentanaRegistroPresupuesto(mecanicoController, nickname, 1);
				vrp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(424, 305, 129, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Ver Presupuesto");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String codigo = (String) table.getModel().getValueAt(fila, 1);
				verPresupuesto(codigo, nickname);
			}
		});
		btnNewButton_5.setBounds(662, 305, 145, 23);
		contentPane.add(btnNewButton_5);
	}
	/**
	 * Metodo para mostrar la tabla de los Presupuestos completa.
	 * @param table (Tabla de la ventana).
	 */
	public void cargarTabla(JTable table) {
		List<Presupuesto> presupuestos = mecanicoController.cargarTablaPresupuesto();
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
			logger.error("No llegan correctamente los Presupuestos.");
		}
	}
	
	/**
	 * Metodo para la visualizacion de la informacion de un presupuesto
	 * @param codigo (Identificador unico del presupuesto)
	 * @param nickname (Nickname del Mecanico)
	 */
	public void verPresupuesto(String codigo, String nickname) {
		if (mecanicoController.seleccionarPresupuesto(codigo) != null) {
			VentanaInformacionPresupuesto vip = new VentanaInformacionPresupuesto(mecanicoController, nickname, mecanicoController.seleccionarPresupuesto(codigo));
			vip.setVisible(true);
			dispose();
		} else {
			logger.error("No llega correctamente el presupuesto.");
		}
	}
	/**
	 * Metodo para la generacion de un PDF a partir de un presupuesto.
	 * @param presupuesto (Presupuesto a partir del cual se hara el documento)
	 */
	public void generarDoc(Presupuesto presupuesto) throws IOException {
		double precioConIVA = presupuesto.getPrecio() + (presupuesto.getPrecio() * 0.21);
		String fecha = mecanicoController.parseFechaPresupuesto();
		 try (PDDocument document = new PDDocument()) {
			 PDPage page = new PDPage(PDRectangle.A4);
			 document.addPage(page);

			 PDPageContentStream contentStream = new PDPageContentStream(document, page);

			 // Fecha:
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ITALIC, 10);
			 contentStream.newLineAtOffset( 475, page.getMediaBox().getHeight() - 20);
			 contentStream.showText(fecha);
			 contentStream.endText();
			 
			 //Titulo:
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 32);
			 contentStream.newLineAtOffset( 175, page.getMediaBox().getHeight() - 60);
			 contentStream.showText("Presupuesto Taller");
			 contentStream.endText();
			 
			 //Texto Explicativo: 
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_ROMAN, 13);
			 contentStream.newLineAtOffset(25, 750);;
			 contentStream.showText("A continuacion podemos ver el presupuesto de los arreglos necesarios del vehiculo de " + presupuesto.getDniCliente() + ":");
			 contentStream.endText();
			 
			 //Texto Normal:
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
			 
			 //Precios:
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
			 
			 //Compromiso de proteccion de datos:
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 8);
			 contentStream.newLineAtOffset(125, 20);;
			 contentStream.showText("Este presupuesto debe ser aprobado por el cliente y cumple todas las normas de proteccion de datos");
			 contentStream.endText();
			 
			 //Nombre de la Empresa:
			 contentStream.beginText();
			 contentStream.setFont(PDType1Font.TIMES_BOLD, 15);
			 contentStream.newLineAtOffset(260, 50);;
			 contentStream.showText("Taller JMPY SL");
			 contentStream.endText();
			 
			 contentStream.close();
			 
			 document.save("Presupuestos/Presupusto-" + presupuesto.getCodigo() + ".pdf");
			 logger.info("DocumentoCreado");
		 }
	}
	
	
	/**
	 * Metodo para mostrar la tabla de los Presupuestos completa teniendo en cuenta unos criterios.
	 * @param table (Tabla de la ventana)
	 * @param tipo (Tipo Filtrado)
	 * @param restriccion (Filtro para la busqueda)
	 */
	public void cargarTablaResctricciones(JTable table, int tipo, String restriccion) {
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		switch (tipo) {
		case 0:
			if(!mecanicoController.filtrarPresupuestoCodigo(restriccion).isEmpty()) {
				presupuestos = mecanicoController.filtrarPresupuestoCodigo(restriccion);
			}else {
				logger.error("No hay ningun con ese codigo.");
			}
			break;
		case 1:
			if(!mecanicoController.filtrarPresupuestoCliente(restriccion).isEmpty()) {
				presupuestos = mecanicoController.filtrarPresupuestoCliente(restriccion);
			}else {
				logger.error("No hay ningun presupuesto para ese cliente.");
			}
			break;
		case 2:
			if(!mecanicoController.filtrarPresupuestoProblema(restriccion).isEmpty()) {
				presupuestos = mecanicoController.filtrarPresupuestoProblema(restriccion);
			}else {
				logger.error("No hay ningun presupuesto con ese problema.");
			}
			break;
		}
		
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
			logger.error("No hay ningun presupuesto con ese filtro");
		}
	}
}

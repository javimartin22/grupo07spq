package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;

import concesionario.cliente.controller.GerenteController;
import concesionario.datos.EmpleadoHoras;
/**
* Clase para el visualizar el grafico de las horas de trabajo por cada empleado
*/
public class VentanaGraficosHoras extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase VentanaGraficosHoras.
	 * @param applicationTitle (Titulo del estudio)
	 * @param chartTitle (Titulo del grafico)
	 * @param empleados (Lista de todos los empleados)
	 * @param gerenteController (Controlador de la ventana para la clase Gerente)
	 * @param nickname (Nickname del gerente)
	 */
public VentanaGraficosHoras( String applicationTitle , String chartTitle, List<EmpleadoHoras> empleados, GerenteController gerenteController, String nickname) {
      super( applicationTitle );
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 510, 304);
      
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "",            
         "Num de Ventas",            
         createDataset(empleados),          
         PlotOrientation.VERTICAL,           
         true, true, false);
      
      	JButton buttonVeryEditTarifas = new JButton("Volver");
		buttonVeryEditTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVisualizarHoras vvh = new VentanaVisualizarHoras(gerenteController, nickname);
				vvh.setVisible(true);
				dispose();
			}
		});
		buttonVeryEditTarifas.setBounds(110, 110, 100, 23);
		
		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		this.add(chartPanel,BorderLayout.CENTER);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    panel.add(buttonVeryEditTarifas);
	    this.add(panel, BorderLayout.SOUTH);
   }

/**
 * Creacion del Dataset a partir de ArrayList de empleados
 * @param empleados (Todas los empleados)
 */
   private CategoryDataset createDataset(List<EmpleadoHoras> empleados ) {
      final String categoria = "Ventas Realizadas";
      
      HashMap<String, Integer> empleadosHoras = new HashMap<String, Integer>();
      for (EmpleadoHoras empleado : empleados) {
    	  empleadosHoras.put(empleado.getNombre(), empleado.getHoras());
      }
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      for(Map.Entry<String, Integer> entry : empleadosHoras.entrySet()) {
    	  dataset.addValue( entry.getValue() , categoria , entry.getKey() );
      }
      return dataset; 
   }
}
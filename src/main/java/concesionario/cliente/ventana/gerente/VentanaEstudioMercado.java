package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Venta;
/**
* Clase para el Estudio de Mercado Grafico de las Marcas
*/
public class VentanaEstudioMercado extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase EstudioComerciales
	 * @param title (Titulo del estudio)
	 * @param ventas (Todas las ventas realizadas por los comerciales)
	 * @param gerenteController (Controlador de la ventana para la clase Gerente)
	 * @param nickname (Nickname del gerente)
	 */
   public VentanaEstudioMercado(String title, List<Venta> ventas, GerenteController gerenteController, String nickname ) {
      super( title ); 
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 510, 278);
      
      JButton buttonVeryEditTarifas = new JButton("Volver");
		buttonVeryEditTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaNuevoEstudioMercado vmenu = new VentanaNuevoEstudioMercado(gerenteController, nickname);
				vmenu.setVisible(true);
				dispose();
			}
		});
		buttonVeryEditTarifas.setBounds(110, 110, 100, 23);
		
		
		JButton buttonGuardar = new JButton("Guardar");
		buttonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int width = 640;    /* Width of the image */
			      Date fecha = new Date();
			      long timeMilli = fecha.getTime();
			      
			      int height = 480;   /* Height of the image */ 
			      File PieChart = new File( "Imagenes/"+timeMilli+".jpeg" ); 
			      try {
					ChartUtilities.saveChartAsJPEG( PieChart , createChart(createDataset(ventas), title ), width , height );
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonGuardar.setBounds(20, 110, 100, 23);
     
		  this.add((createDemoPanel(ventas, title, buttonVeryEditTarifas)),BorderLayout.CENTER);
	      JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	      panel.add(buttonVeryEditTarifas);
	      panel.add(buttonGuardar);
	      this.add(panel, BorderLayout.SOUTH);
     
      
      
   }
   /**
  	 * Creacion del Dataset a partir de ArrayList de ventas
  	 * @param ventas (Todas las ventas realizadas por los comerciales)
  	 */
   private static PieDataset createDataset(List<Venta> ventas) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      List<String> marcas_anyadidas = new ArrayList<String>();
      boolean esta;
      
      for(Venta v:ventas) {
    	  esta = false;
    	  for(String s: marcas_anyadidas) {
    		  if(v.getMarca().equals(s)) {
    			  esta = true;
    			  break;
    		  }
    	  }
    	  if(esta) {
    		  double cuenta = dataset.getValue(v.getMarca()).doubleValue();
    		  dataset.setValue(v.getMarca(), cuenta + 1);
    	  }else {
    		  dataset.setValue(v.getMarca(), new Double ( 1 ));
        	  marcas_anyadidas.add(v.getMarca());
    	  }
    	 
      }
      return dataset;         
   }
   /**
  	 * Creacion del Grafico a partir del dataset
  	 * @param dataset (PieDataset con todos las ventas organizadas para el grafico)
  	 * @param title (Titulo del grafico)
  	 */
   private static JFreeChart createChart( PieDataset dataset, String title ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         title,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   /**
 	 * Creacion del ChartPanel con el grafico
 	 * @param ventas (Todas las ventas realizadas por los comerciales)
 	 * @param title (Titulo del grafico)
 	 * @param jbutton (Jbutton del panel)
 	 */
   public static JPanel createDemoPanel(List<Venta> ventas, String title, JButton jbutton) {
      JFreeChart chart = createChart(createDataset(ventas), title );  
      ChartPanel chartpanel = new ChartPanel( chart ); 
      return chartpanel;
   }

//   public static void main( String[ ] args ) {
//	  List<Venta> ventas = new ArrayList<Venta>();
//	  GerenteController gerentec = new GerenteController(new ClienteApp());
//	  ventas.add(new Venta("fecha", "modelo", "marca1", "0444KKK", "nickcomercial", "nombreComprador"));
//      VentanaEstudioMercado demo = new VentanaEstudioMercado( "Title", ventas, gerentec, "ejemplo");  
//      demo.setSize( 560 , 367 );    
//      RefineryUtilities.centerFrameOnScreen( demo );    
//      demo.setVisible( true ); 
//   }
}
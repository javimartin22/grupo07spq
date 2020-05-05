package concesionario.cliente.ventana.gerente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import concesionario.datos.Venta;
 
public class VentanaEstudioMercado extends ApplicationFrame {
   
   public VentanaEstudioMercado(String title, List<Venta> ventas ) {
      super( title ); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 510, 278);
      setContentPane(createDemoPanel(ventas, title));
   }
   
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
   
   private static JFreeChart createChart( PieDataset dataset, String title ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         title,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel(List<Venta> ventas, String title ) {
      JFreeChart chart = createChart(createDataset(ventas), title );  
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
	  List<Venta> ventas = new ArrayList<Venta>();
	  ventas.add(new Venta("fecha", "modelo", "marca1", "0444KKK", "nickcomercial", "nombreComprador"));
      VentanaEstudioMercado demo = new VentanaEstudioMercado( "Title", ventas);  
      demo.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
   }
}
package concesionario.cliente.ventana.gerente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import concesionario.cliente.ClienteApp;
import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Venta; 

public class VentanaEstudioMes extends ApplicationFrame {
   
   public VentanaEstudioMes( String applicationTitle , String chartTitle, List<Venta> ventas) {
      super( applicationTitle );
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 510, 278);
      
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "",            
         "Num de Ventas",            
         createDataset(ventas),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset(List<Venta> ventas ) {
      final String categoria = "Ventas Realizadas";
      
      List<Integer> meses = new ArrayList<Integer>();
      boolean esta;
      
      System.out.println("Hola");
      
      for(Venta v: ventas) {
    	  esta = false;
    	  String fecha = v.getFecha();
    	  String[] parts = fecha.split("-");
    	  String mes = parts[2];
    	  System.out.println(mes);
    	  meses.add(Integer.parseInt(mes));
      }
      //cuantos de cada directamente?
      Map<Integer, Integer> countMap = new HashMap<>();

      for (Integer item: meses) {

          if (countMap.containsKey(item))
              countMap.put(item, countMap.get(item) + 1);
          else
              countMap.put(item, 1);
      }
      
      System.out.println(Arrays.asList(countMap));
      
      //Pasar todo a meses con letra
      Map<String, Integer> ventasmes = new HashMap<>();
      
      for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
    	  switch(entry.getKey()) {
    	   case 1 :
    	      ventasmes.put("Enero", entry.getValue());
    	      break; 
    	   case 2 :
    		   ventasmes.put("Febrero", entry.getValue());
    	      break;
    	   case 3 :
    		   ventasmes.put("Marzo", entry.getValue());
     	      break;
    	   case 4 :
    		   ventasmes.put("Abril", entry.getValue());
     	      break;
    	   case 5 :
    		   ventasmes.put("Mayo", entry.getValue());
     	      break;
    	   case 6 :
    		   ventasmes.put("Junio", entry.getValue());
     	      break;
    	   case 7 :
    		   ventasmes.put("Julio", entry.getValue());
     	      break;
    	   case 8 :
    		   ventasmes.put("Agosto", entry.getValue());
     	      break;
    	   case 9 :
    		   ventasmes.put("Septiembre", entry.getValue());
      	      break;
    	   case 10 :
    		   ventasmes.put("Octubre", entry.getValue());
      	      break;
    	   case 11 :
    		   ventasmes.put("Noviembre", entry.getValue());
      	      break;
    	   case 12 :
    		   ventasmes.put("Diciembre", entry.getValue());
       	      break; 
    	}
    	  
    	}
      System.out.println(Arrays.asList(ventasmes));
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      for(Map.Entry<String, Integer> entry : ventasmes.entrySet()) {
    	  dataset.addValue( entry.getValue() , categoria , entry.getKey() );
      }
      return dataset; 
   }
   
   public static void main( String[ ] args ) {
	   List<Venta> ventas = new ArrayList<Venta>();
		  GerenteController gerentec = new GerenteController(new ClienteApp());
		  ventas.add(new Venta("fecha", "modelo", "marca1", "0444KKK", "nickcomercial", "nombreComprador"));
      VentanaEstudioMes chart = new VentanaEstudioMes("Car Usage Statistics", "Which car do you like?", ventas);
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
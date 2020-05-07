package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
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
   
   public VentanaEstudioMes( String applicationTitle , String chartTitle, List<Venta> ventas, GerenteController gerenteController, String nickname) {
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
      this.add(chartPanel, BorderLayout.CENTER);
      
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
			      File BarChart = new File( "Imagenes/"+timeMilli+".jpeg" ); 
			      try {
					ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonGuardar.setBounds(20, 110, 100, 23);
      
      JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      panel.add(buttonVeryEditTarifas);
      panel.add(buttonGuardar);
      this.add(panel, BorderLayout.SOUTH);
   }
   
   private CategoryDataset createDataset(List<Venta> ventas ) {
      final String categoria = "Ventas Realizadas";
      
      List<Integer> meses = new ArrayList<Integer>();
      boolean esta;
      
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
      
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
    	  String valor = "";
    	  
    	  switch(entry.getKey()) {
   	   case 1 :
   		valor = "Enero";
   	      break; 
   	   case 2 :
   		valor = "Febrero";
   	      break;
   	   case 3 :
   		valor = "Marzo";
    	      break;
   	   case 4 :
   		valor = "Abril";
    	      break;
   	   case 5 :
   		   valor = "Mayo";
    	      break;
   	   case 6 :
   		   valor = "Junio";
    	      break;
   	   case 7 :
   		   valor = "Julio";
    	      break;
   	   case 8 :
   		valor = "Agosto";
    	      break;
   	   case 9 :
   		valor = "Septiembre";
     	      break;
   	   case 10 :
   		valor = "Octubre";
     	      break;
   	   case 11 :
   		valor = "Noviembre";
     	      break;
   	   case 12 :
   		valor = "Diciembre";
      	      break; 
   	}
    	  
    	  dataset.addValue( entry.getValue() , categoria , valor );
      }
      return dataset; 
   }
   
   public static void main( String[ ] args ) {
	   List<Venta> ventas = new ArrayList<Venta>();
		  GerenteController gerentec = new GerenteController(new ClienteApp());
		  ventas.add(new Venta("fecha", "modelo", "marca1", "0444KKK", "nickcomercial", "nombreComprador"));
      VentanaEstudioMes chart = new VentanaEstudioMes("Car Usage Statistics", "Which car do you like?", ventas, gerentec, "nickname");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
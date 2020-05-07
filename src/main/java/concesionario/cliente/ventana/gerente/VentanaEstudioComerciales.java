package concesionario.cliente.ventana.gerente;

import java.awt.BorderLayout;
import java.awt.Color;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import concesionario.cliente.ClienteApp;
import concesionario.cliente.controller.GerenteController;
import concesionario.datos.Venta; 

public class VentanaEstudioComerciales extends ApplicationFrame {
   
   public VentanaEstudioComerciales( String applicationTitle , String chartTitle, List<Venta> ventas, GerenteController gerenteController, String nickname) {
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
      
      CategoryPlot cplot = (CategoryPlot)barChart.getPlot();
      BarRenderer renderer = (BarRenderer) cplot.getRenderer();
      renderer.setSeriesPaint(0, Color.blue);
      
      
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
      
      List<String> comerciales = new ArrayList<String>();
      boolean esta;
      
      for(Venta v: ventas) {
    	  esta = false;
    	  String comercial = v.getNicknameComercial();
    	  System.out.println(comercial);
    	  comerciales.add(comercial);
      }
      //cuantos de cada directamente?
      Map<String, Integer> countMap = new HashMap<>();

      for (String item: comerciales) {

          if (countMap.containsKey(item))
              countMap.put(item, countMap.get(item) + 1);
          else
              countMap.put(item, 1);
      }
      
      System.out.println(Arrays.asList(countMap));
      
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      for(Map.Entry<String, Integer> entry : countMap.entrySet()) {
    	  dataset.addValue( entry.getValue() , categoria , entry.getKey() );
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
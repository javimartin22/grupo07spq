package concesionario.cliente;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;


import concesionario.servidor.datos.Usuario;
import concesionario.cliente.ventana.VentanaLogin;

//Cambiar el Pom.xml para que luego nos runnee esta aplicaicon
public class ClienteApp {
	
	private static final long serialVersionUID = 1L;
	private Client client;
	private VentanaLogin vlogin;
	
	public static final String URL_SERVER = "http://localhost:8080/concesionario";
	
	public ClienteApp() {
		
		client = ClientBuilder.newClient();
		WebTarget appTarget = client.target(URL_SERVER);
		WebTarget loginTarget= appTarget.path("loginp");
		
		vlogin = new VentanaLogin();
		vlogin.setVisible(true);
		
		vlogin.getButtonAceptar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = vlogin.getTextNombreUsuario().getText();
				String password = vlogin.getTextContrasenya().getText();
				String concat= nombre + " " + password;
				
				//Usuario nuevo = new Usuario()
				
				Entity<String> entity = Entity.entity(concat, MediaType.TEXT_PLAIN);
	            Response response = loginTarget.request(MediaType.TEXT_PLAIN).post(entity);
	            
	            if(response.getStatus() == Status.OK.getStatusCode()) {
	            	   JOptionPane.showMessageDialog(vlogin, "Datos correctos");
	               }else {
	            	   JOptionPane.showMessageDialog(vlogin, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
	               }
				
				
			}
		});
		
	}
	

	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable(){
	        
	            @Override
	            public void run() {
	                new ClienteApp();
	            }
	        });
	    }
	}

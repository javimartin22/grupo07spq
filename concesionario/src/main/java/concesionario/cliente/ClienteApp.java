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
import concesionario.cliente.ventana.VentanaMenuCliente;

//Cambiar el Pom.xml para que luego nos runnee esta aplicaicon
public class ClienteApp {
	
	private static final long serialVersionUID = 1L;
	private Client client;
	
	private VentanaLogin vlogin;
	private VentanaMenuCliente vmc;
	
	
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
 				Usuario concat= new Usuario(nombre, password, 0);

 				//Usuario nuevo = new Usuario()

 				Entity<Usuario> entity = Entity.entity(concat, MediaType.APPLICATION_JSON);
 				Response response = loginTarget.request(MediaType.TEXT_PLAIN).post(entity);

 	            if(response.getStatus() == Status.OK.getStatusCode()) {
 	            	String str = response.readEntity(String.class);
 	            	JOptionPane.showMessageDialog(vlogin, "Datos correctos, el tipo es " +str);
 	            	if(str.equals("3")) {
 	            		vmc = new VentanaMenuCliente(nombre);
 	            		vmc.setVisible(true);
 	            		vlogin.dispose();
 	            	}
 	            	
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

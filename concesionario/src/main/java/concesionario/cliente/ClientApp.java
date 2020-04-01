package concesionario.cliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
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

public class ClientApp extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Client client;
    
    public ClientApp() {
    	client = ClientBuilder.newClient();
    	WebTarget appTarget = client.target("http://localhost:8080/concesionario"); //ponerlo final o en properties
    	WebTarget loginTarget= appTarget.path("login");
    	
    	
        setSize(620, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton getUsersButton = new JButton("Get Users");
        JPanel buttonsPanel = new JPanel();

	JButton deleteUserButton = new JButton("Delete User");

        buttonsPanel.add(getUsersButton);
	buttonsPanel.add(deleteUserButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
        JList<Usuario> userList = new JList<>(userListModel);

        JScrollPane listScrollPane = new JScrollPane(userList);
        add(listScrollPane, BorderLayout.WEST);

        getUsersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {    //invoca get usuarios
                WebTarget alluserTarget = loginTarget.path("all");
                GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {};
                List<Usuario> usuarios = alluserTarget.request(MediaType.APPLICATION_JSON).get(genericType);
                
                userListModel.clear();
                for(Usuario usuario:usuarios) {
                	userListModel.addElement(usuario);
                }
                
            }
            
        });

        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.EAST);

        JButton addUserButton = new JButton("Add user");
        rightPanel.add(addUserButton);

        final JTextField codeTextField = new JTextField("", 2);
        final JTextField nameTextField = new JTextField("", 10);
        final JTextField surnameTextField = new JTextField("", 10);

        rightPanel.add(codeTextField);
        rightPanel.add(nameTextField);
        rightPanel.add(surnameTextField);

        addUserButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               Usuario nuevo = new Usuario(nameTextField.getText(),surnameTextField.getText(),Integer.parseInt(codeTextField.getText()));
               
               Entity<Usuario> entity = Entity.entity(nuevo, MediaType.APPLICATION_JSON);
               Response response = loginTarget.request(MediaType.TEXT_PLAIN).post(entity);
               System.out.println(response.getEntity());
               //como sacar entidad de la respuesta para el sacar el dato/objeto ? mirar ejemplo o preguntar
            }
        });

	deleteUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               WebTarget deleteTarget = loginTarget.path(codeTextField.getText());
               Response response = deleteTarget.request().delete();  //nada porque no recibe datos
               
               if(response.getStatus() == Status.OK.getStatusCode()) {
            	   JOptionPane.showMessageDialog(ClientApp.this, "Usuario correctamente eliminado");
               }else {
            	   JOptionPane.showMessageDialog(ClientApp.this, "Error eliminando usuario","Error", JOptionPane.ERROR_MESSAGE);
               }
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new ClientApp();
            }
        });
    }
}
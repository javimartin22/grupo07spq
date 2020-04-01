package concesionario.servidor;

import concesionario.servidor.BaseDatos.BD;
import concesionario.servidor.datos.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("loginp")
public class LoginResources {
	
	private Connection con;
	private Statement st;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUser() {
		Usuario usu= new Usuario("Una","Prueba",0);
		return usu;
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Una","Loko",2));
		usuarios.add(new Usuario("Dos","Prue",3));
		
		return usuarios;
		
	}
	//Por ahora solo funciona este metodo
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.TEXT_PLAIN)
	public Response anadirUsuario(String concat) {
		System.out.println("ha llegado server");
		System.out.println(concat);
		String arr[] = concat.split(" ", 2);
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Usuario nuevo = BD.usuarioSelect(st, "user");
		System.out.println(nuevo.getContrasenya());

		String username = arr[0];   //Nombre de usuario
		String pass= arr[1];     // Password
		
		
		if(username.equals("usu") && pass.equals("password")) {
			
			System.out.println("Usuario correcto");
			return Response.status(Response.Status.OK).build();
		}else {
			System.out.println("Datos incorrectos");
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
	
	@DELETE
	@Path("{code}")
	public Response deleteUser (@PathParam("code") int codigo) {
		if(codigo==10) {
			System.out.println("borrando usuario");
			return Response.status(Response.Status.OK).build();
		}else {
			System.out.println("usuario no encontrado");
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}




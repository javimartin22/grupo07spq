package concesionario.servidor;

import concesionario.servidor.datos.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//resources  o

@Path("login")
public class UsuariosResource {
	
	
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String anadirUsuario(Usuario usu) {
		System.out.println("Usuario" +usu.getNickname()+ "anadido");
		return "Usuario anadido correctamente";
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




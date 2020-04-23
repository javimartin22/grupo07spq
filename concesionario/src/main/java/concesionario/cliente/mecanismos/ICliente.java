package concesionario.cliente.mecanismos;

import javax.ws.rs.core.Response;
import concesionario.datos.Usuario;

public interface ICliente {

	public Response login(Usuario usu);
}

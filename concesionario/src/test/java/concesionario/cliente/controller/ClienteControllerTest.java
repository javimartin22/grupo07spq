package concesionario.cliente.controller;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import concesionario.cliente.ClienteApp;
import concesionario.datos.Cliente;
import concesionario.datos.Tarifa;


	@RunWith(MockitoJUnitRunner.class)
	public class ClienteControllerTest {

		public ClienteController clienteController;
		
		@Mock
		ClienteApp cliente;
		
		@Before
		public void setUp() {
			clienteController = new ClienteController(cliente);
		}
		
		@Test
		public void testFiltrarTarifaHorasMin() {
			List<Tarifa> tarifas= new ArrayList<Tarifa>();
			tarifas.add(new Tarifa("T1", "Cambio Aceite", 50, 1));
			tarifas.add(new Tarifa("T2", "Frenos", 50, 1));
			
			Response response = Mockito.mock(Response.class);
			Mockito.when(response.getStatus()).thenReturn(200);
			
			Mockito.when(response.readEntity(Mockito.any(GenericType.class))).thenAnswer(x ->tarifas);
			
			when(cliente.filtrarTarifaHorasMin(any(Integer.class))).thenReturn(response);
			
			List<Tarifa> tarif_result = clienteController.filtrarTarifaHorasMin(10);
			for(int i =0; i<tarifas.size(); i++) {
				assertTrue(tarif_result.get(i).getIdTarifa().equals(tarifas.get(i).getIdTarifa()));
			}
		}

}

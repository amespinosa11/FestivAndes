package Rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Fachada.Master;
import Vos.Cliente;
import Vos.CompaniaTeatro;
import Vos.Espectaculo;
import Vos.Funcion;
import Vos.MensajeDevolucion;
import Vos.Sitio;
import Vos.Usuario;

@Path("administradorServices")
public class AdministradorServices 
{
	@Context
	private ServletContext context;

	private String getPath() 
	{
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	
	/*
	 * Requerimiento RF2 Registrar cliente
	 */
	@POST
	@Path("/administrador/agregarCliente")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCliente(Cliente pCliente)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarCliente(pCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pCliente).build();
	}
	
	/*
	 * Requerimiento RF3 Registrar compañia de teatro
	 */
	@POST
	@Path("/administrador/compania")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCompania(CompaniaTeatro pCompania)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarCompania(pCompania);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pCompania).build();
	}
	
	/*
	 * Requerimiento RF4 Registrar espectaculo
	 */
	@POST
	@Path("/administrador/espectaculo")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarEspectaculo(Espectaculo pEspectaculo)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarEspectaculo(pEspectaculo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pEspectaculo).build();
	}
	
	/*
	 * Requerimiento RF5 Registrar sitio
	 */
	@POST
	@Path("/administrador/sitio")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarSitio(Sitio pSitio)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarSitio(pSitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pSitio).build();
	}
	
	/*
	 * Requerimiento RF6 Programar funcion
	 */
	@POST
	@Path("/administrador/funcion")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response programarFuncion(Funcion pFuncion)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.programarFuncion(pFuncion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pFuncion).build();
	}
	
	/*
	 * Requerimiento RF14 cancelar funcion
	 */
	@DELETE
	@Path("/administrador/cancelarFuncion/{idFuncion: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelarFuncion(@PathParam("idFuncion") Integer idFuncion)
	{
		ArrayList<MensajeDevolucion> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			//mens = mas.cancelarFuncion(idFuncion);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	

}

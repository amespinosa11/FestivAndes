package Rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Fachada.Master;
import Vos.Funcion;
import Vos.Preferencia;

@Path("operarioServices")
public class OperarioServices 
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
	 * Requerimiento RF9 Registrar realizacion funcion de un espectaculo
	 */
	@PUT
	@Path("/operario/funcion/{idFuncion: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarRealizacionFuncion(@PathParam("idFuncion") Integer idFuncion)
	{
		Master mas = Master.darInstancia(getPath());
		Funcion fun;
		try 
		{
			fun = mas.registrarRealizacionFuncion(idFuncion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(fun).build();
	}
	
	
}

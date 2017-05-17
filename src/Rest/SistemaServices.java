package Rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
//import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Vos.Funcion;
import Vos.Reporte;
import Vos.Sitio;
import Vos.Usuario;
import sun.security.util.ManifestEntryVerifier;

import Fachada.Master;


@Path("sistemaServices")
public class SistemaServices
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
	 * Requerimiento RF1 Registrar usuario
	 */
	@POST
	@Path("/usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarUsuario(Usuario pUsuario)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarUsuario(pUsuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pUsuario).build();
	}
	
	/*
	 * Requerimiento RFC1 CONSULTAR LAS FUNCIONES DE ESPECTÁCULOS PROGRAMADOS EN FESTIVANDES
	 */
	@GET
	@Path("/usuario/darFunciones/{idEspectaculo: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarFuncionesEspectaculos(@PathParam("idEspectaculo") Integer idEspectaculo)
	{
		Master mas = Master.darInstancia(getPath());
		ArrayList<Funcion> funcs = null;
		try
		{
			funcs = mas.consultarFuncionesEspectaculos(idEspectaculo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcs).build();
	}
	
	/*
	 * Requerimiento RFC2 CONSULTAR UN SITIO
	 */
	@GET
	@Path("/usuario/darSitio/{idSitio: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarSitio(@PathParam("idSitio") Integer idSitio)
	{
		Master mas = Master.darInstancia(getPath());
		Sitio s=null;
		try
		{
			s = mas.consultarSitio(idSitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	/*
	 * Requerimiento RFC3 GENERAR UN REPORTE DE UNA FUNCION por localidad
	 */
	@GET
	@Path("/usuario/darReporteFuncion/{idFuncion: \\d+}/{idLocalidad: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response generarReporteFuncionLocalidad(@PathParam("idFuncion") Integer idFuncion, @PathParam("idLocalidad") Integer idLocalidad)
	{
		Master mas = Master.darInstancia(getPath());
		Reporte reporte;
		try 
		{
			reporte = mas.generarReporteFuncionLocalidad(idFuncion, idLocalidad);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reporte).build();
	}
	
	/*
	 * Requerimiento RFC3 GENERAR UN REPORTE DE UNA FUNCION por localidad
	 */
	@GET
	@Path("/usuario/darReporteFuncion/{idFuncion: \\d+}/{idLocalidad: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response generarReporteFuncionTipo(@PathParam("idFuncion") Integer idFuncion, @PathParam("idLocalidad") Integer idLocalidad)
	{
		Master mas = Master.darInstancia(getPath());
		Reporte reporte;
		try 
		{
			reporte = mas.generarReporteFuncionLocalidad(idFuncion, idLocalidad);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reporte).build();
	}
	
	/*
	 * Requerimiento RFC3 GENERAR UN REPORTE DE UNA FUNCION por usuario
	 */
	@GET
	@Path("/usuario/darReporteFuncion/{idFuncion: \\d+}/{tipo: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response generarReporteFuncionUsuario(@PathParam("idFuncion") Integer idFuncion, @PathParam("tipo") Integer tipo)
	{
		Master mas = Master.darInstancia(getPath());
		Reporte reporte;
		try 
		{
			reporte = mas.generarReporteFuncionUsuario(idFuncion, tipo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reporte).build();
	}
	
	/*
	 * Requerimiento RFC4 GENERAR UN REPORTE DE ESPECTACULO por sitio
	 */
	@GET
	@Path("/usuario/darReporteEspectaculoSitio/{idEspectaculo: \\d+}/{idSitio: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response generarReporteEspectaculoSitio(@PathParam("idEspectaculo") Integer idEspectaculo, @PathParam("idSitio") Integer idSitio)
	{
		Master mas = Master.darInstancia(getPath());
		Reporte reporte;
		try 
		{
			reporte = mas.generarReporteEspectaculoSitio(idEspectaculo, idSitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reporte).build();
	}
	
	/*
	 * Requerimiento RFC4 GENERAR UN REPORTE DE ESPECTACULO por funcion
	 */
	@GET
	@Path("/usuario/darReporteEspectaculoFuncion/{idEspectaculo: \\d+}/{idFuncion: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response generarReporteEspectaculoFuncion(@PathParam("idEspectaculo") Integer idEspectaculo, @PathParam("idFuncion") Integer idFuncion)
	{
		Master mas = Master.darInstancia(getPath());
		Reporte reporte;
		try 
		{
			reporte = mas.generarReporteEspectaculoFuncion(idEspectaculo, idFuncion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reporte).build();
	}
	
	

	
}

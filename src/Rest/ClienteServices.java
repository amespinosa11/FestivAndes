package Rest;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Fachada.Master;
import Vos.Boleta;
import Vos.Cliente;
import Vos.ConsultaRF11;
import Vos.ConsultaRF9;
import Vos.ConsultaRFC5;
import Vos.DevolverRF11;
import Vos.Funcion;
import Vos.InfoCompraAbonamiento;
import Vos.InfoCompraBoleta;
import Vos.MensajeDevolucion;
import Vos.Preferencia;
import Vos.ReporteAsistencia;
import Vos.ReporteCompania;
import Vos.ReporteRFC5;

@Path("clienteServices")
public class ClienteServices
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
	 * Requerimiento RF7 Registrar preferencia cliente
	 */
	@POST
	@Path("/cliente/preferencia/{idCliente: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarPreferencia(Preferencia pPreferencia,@PathParam("idCliente") Integer idCliente)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarPreferencia(pPreferencia,idCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pPreferencia).build();
	}
	
	/*
	 * Requerimiento RF8 Registrar compra de boleta
	 */
	@POST
	@Path("/cliente/compraBoleta")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registraCompraBoleta(Boleta pBoleta)
	{
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mas.registrarCompraBoleta(pBoleta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pBoleta).build();
	}
	
	/*
	 * Requerimiento RF10 Registrar compra de boleta
	 */
	@POST
	@Path("/cliente/compraBoletas/{idCliente: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response comprarBoletas(@PathParam("idCliente") Integer idCliente,InfoCompraBoleta info)
	{
		ArrayList<Boleta> boletas = null;
		//Boleta n = new Boleta(100, 10.0, 1, 1, 1, 1, 1);
		//boletas.add(n);
		Master mas = Master.darInstancia(getPath());
		try 
		{
			boletas = mas.comprarBoletas(idCliente, info);
			System.out.println(boletas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(boletas).build();
	}
	
	/*
	 * Requerimiento RF11 Registrar compra de abonamiento
	 */
	@POST
	@Path("/cliente/compraAbonamiento/{idCliente: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarAbonamiento(@PathParam("idCliente") Integer idCliente,InfoCompraAbonamiento info)
	{
		ArrayList<Boleta> boletas = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			boletas = mas.registraAbonamiento(idCliente, info);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(boletas).build();
	}
	
	/*
	 * Requerimiento RF12 Registrar compra de boleta
	 */
	@DELETE
	@Path("/cliente/devolverBoleta/{idCliente: \\d+}/{idBoleta: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response devolverBoleta(@PathParam("idCliente") Integer idCliente,@PathParam("idBoleta") Integer idBoleta)
	{
		MensajeDevolucion mens = new MensajeDevolucion("Ya llegaste aca ana");
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.devolverBoleta(idCliente, idBoleta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RF13 Devolver abonamiento
	 */
	@DELETE
	@Path("/cliente/devolverAbono/{idCliente: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response devolverAbonamiento(@PathParam("idCliente") Integer idCliente)
	{
		ArrayList<MensajeDevolucion> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.devolverAbonamiento(idCliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	
	/*
	 * Requerimiento RF14 cancelar funcion
	 */
	@DELETE
	@Path("/administrador/cancelarFuncion/{idAdministrador: \\d+}/{idFuncion: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelarFuncion(@PathParam("idAdministrador") Integer idAdministrador,@PathParam("idFuncion") Integer idFuncion)
	{
		ArrayList<MensajeDevolucion> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.cancelarFuncion(idAdministrador,idFuncion);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RFC7 CONSULTAR ASISTENCIA Cliente
	 */
	@GET
	@Path("/usuario/darAsistencia/{idCliente: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarAsist(@PathParam("idCliente") Integer idCliente)
	{
		Master mas = Master.darInstancia(getPath());
		ArrayList<ReporteAsistencia> funcs = null; 
		try 
		{
			funcs = mas.consultarAsistencia(idCliente);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcs).build();
	}
	
	/*
	 * Requerimiento RFC8 Consulta compania
	 */
	@GET
	@Path("/usuario/consultarCompania/{idUsuario: \\d+}/{idCompania: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response informacionCompania(@PathParam("idUsuario") Integer idUsuario,@PathParam("idCompania") Integer idCompania)
	{
		ArrayList<ReporteCompania> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.informacionCompania(idUsuario, idCompania);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RFC9 CONSULTAR ASISTENCIA
	 */
	@POST
	@Path("/usuario/consultarAsis/{nomCompania: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consultarAsistenciaRFC9(@PathParam("nomCompania") int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		ArrayList<Cliente> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.consultarAsistenciaRFC9(nomCompania,consulta);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RFC10 CONSULTAR ASISTENCIA V2
	 */
	@POST
	@Path("/usuario/consultarAsisNo/{nomCompania: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response asistenciaClientesRFC10(@PathParam("nomCompania") int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		ArrayList<Cliente> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.asistenciaClientesRFC10(nomCompania,consulta);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	

	/*
	 * Requerimiento RFC11 CONSULTAR COMPRA DE BOLETAS
	 */
	@POST
	@Path("/gerente/consultarBoletas/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consultarCompraBoletas(@PathParam("id") int id,ConsultaRF11 consulta) throws Exception
	{
		ArrayList<DevolverRF11> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.consultarCompraBoletas(id,consulta);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RFC12 CONSULTAR LOS BUENOS CLIENTES
	 */
	@GET
	@Path("/gerente/consultarBuenosClientes/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consultarBuenosClientes(@PathParam("id") int id)throws Exception
	{
		ArrayList<Cliente> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.consultarBuenosClientes(id);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();
	}
	
	/*
	 * Requerimiento RFC5 CONSULTAR RENTABILIDAD DE UNA COMPAÑÍA (BONO)
	 */
	@POST
	@Path("/rentabilidadCompania/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rentabilidadCompania(@PathParam("id") int id,ConsultaRFC5 consulta) throws Exception
	{
		ArrayList<ReporteRFC5> mens = null;
		Master mas = Master.darInstancia(getPath());
		try 
		{
			mens = mas.rentabilidadCompania(id, consulta);
			System.out.println(mens);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(mens).build();

	}
	
	
	
}

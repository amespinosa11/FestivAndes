package Fachada;

import java.util.ArrayList;

import Dao.BoletasDao;
import Dao.CompaniaDao;
import Dao.ConsultaDao;
import Dao.FuncionDao;
import Fachada.Master;
import Vos.Boleta;
import Vos.Cliente;
import Vos.CompaniaTeatro;
import Vos.ConsultaRF11;
import Vos.ConsultaRF9;
import Vos.DevolverRF11;
import Vos.Espectaculo;
import Vos.Funcion;
import Vos.InfoCompraAbonamiento;
import Vos.InfoCompraBoleta;
import Vos.MensajeDevolucion;
import Vos.Preferencia;
import Vos.Reporte;
import Vos.ReporteAsistencia;
import Vos.ReporteCompania;
import Vos.Sitio;
import Vos.Usuario;



public class Master 
{
	private static Master instacia;
	
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	private static String connectionDataPath;

	private ConsultaDao consultaDao;
	
	private BoletasDao boletasDao;
	
	private FuncionDao funcionDao;
	
	private CompaniaDao companiaDao;

	public static Master darInstancia(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		instacia = instacia == null ? new Master() : instacia;
		return instacia;
	}

	private Master() 
	{
		consultaDao = new ConsultaDao(connectionDataPath);
		boletasDao = new BoletasDao(connectionDataPath);
		funcionDao = new FuncionDao(connectionDataPath);
		companiaDao = new CompaniaDao(connectionDataPath);
	}
	
	///////////////////////////////////////////////////////////////
	//   					  REQUERIMIENTOS 				   	//
	//////////////////////////////////////////////////////////////
	
	
	/*
	 *  Requerimiento RF1 Registrar usuario
	 */
	public void registrarUsuario(Usuario pUsuario) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarUsuario(pUsuario);
	}
	
	/*
	 * Requerimiento RF2 Registrar cliente
	 */
	public void registrarCliente(Cliente pCliente) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarCliente(pCliente);;
	}
	
	/*
	 * Requerimiento RF3 Registrar compañia de teatro
	 */
	public void registrarCompania(CompaniaTeatro pCompania) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarCompaniaTeatro(pCompania);
		
	}
	
	/*
	 * Requerimiento RF4 Registrar espectaculo
	 */
	public void registrarEspectaculo(Espectaculo pEspectaculo) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarEspectaculo(pEspectaculo);
	}
	
	/*
	 * Requerimiento RF5 Registrar sitio
	 */
	public void registrarSitio(Sitio pSitio) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarSitio(pSitio);
	}
	
	/*
	 * Requerimiento RF6 Programar funcion
	 */
	public void programarFuncion(Funcion pFuncion) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.programarFuncion(pFuncion);
	}
	
	/*
	 * Requerimiento RF7 Registrar preferencia cliente
	 */
	public void registrarPreferencia(Preferencia pPreferencia,Integer pIdCliente) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarPreferencia(pPreferencia,pIdCliente);
	}
	
	
	/*
	 * Requerimiento RF8 Registrar compra de boleta
	 */
	public void registrarCompraBoleta(Boleta pBoleta) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		consultaDao.registrarCompraBoleta(pBoleta);
	}
	
	/*
	 * Requerimiento RF9 Registrar realizacion funcion de un espectaculo
	 */
	public Funcion registrarRealizacionFuncion(Integer pIdFuncion) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.registrarRealizacionFuncion(pIdFuncion);
	}

	/*
	 * Requerimiento RFC1 CONSULTAR FUNCIONES según espectaculo.
	 */
	public ArrayList<Funcion> consultarFuncionesEspectaculos(Integer idEsp) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.darFuncionesEspectaculos(idEsp);
	}
	
	/*
	 * Requerimiento RFC2 CONSULTAR SITIO
	 * Debe dar toda la información relevante sobre el sítio.
	 */
	public Sitio consultarSitio(Integer idSitio) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.darSitio(idSitio);
	}

	/*
	 * Requerimiento RFC3.1 GENERAR UN REPORTE DE UNA FUNCION.
	 * Debe discriminar por localidad.
	 */
	public Reporte generarReporteFuncionLocalidad(Integer idFuncion, Integer idLocalidad) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.generarReporteFuncionLocalidad(idFuncion, idLocalidad);
	}
	
	/*
	 * Requerimiento RFC3.2 GENERAR UN REPORTE DE UNA FUNCION.
	 * Debe discriminar por usuario.
	 */
	public Reporte generarReporteFuncionUsuario(Integer idFuncion, Integer tipo) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.generarReporteFuncionUsuario(idFuncion, tipo);
	}
	
	/*
	 * Requerimiento RFC4.1 GENERAR UN REPORTE DE UN ESPECTACULO.
	 * Debe discriminar por sitio.
	 */
	public Reporte generarReporteEspectaculoSitio(Integer idEsp, Integer idSit) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.generarReporteEspectaculoSitio(idEsp, idSit);
	}
	
	/*
	 * Requerimiento RFC4.1 GENERAR UN REPORTE DE UN ESPECTACULO.
	 * Debe discriminar por funcion.
	 */
	public Reporte generarReporteEspectaculoFuncion(Integer idEsp, Integer idFun) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.generarReporteEspectaculoFuncion(idEsp, idFun);
	}
	
	/*
	 * Requerimiento RF10 
	 */
	public ArrayList<Boleta> comprarBoletas(int idCliente, InfoCompraBoleta pInfo) throws Exception
	{
		boletasDao = boletasDao == null ? new BoletasDao(connectionDataPath) : boletasDao;
		return boletasDao.comprarBoletas(idCliente, pInfo);
	}
	
	/*
	 * Requerimiento RF11 
	 */
	public ArrayList<Boleta> registraAbonamiento(int idCliente, InfoCompraAbonamiento pInfo) throws Exception
	{
		boletasDao = boletasDao == null ? new BoletasDao(connectionDataPath) : boletasDao;
		return boletasDao.registrarAbonamiento(idCliente, pInfo);
	}
	
	/*
	 * Requerimiento RF12 
	 */
	public MensajeDevolucion devolverBoleta(int idCliente,int idBoleta) throws Exception
	{
		boletasDao = boletasDao == null ? new BoletasDao(connectionDataPath) : boletasDao;
		return boletasDao.devolverBoleta(idCliente, idBoleta);
	}
	
	/*
	 * Requerimiento RF13 
	 */
	public ArrayList<MensajeDevolucion> devolverAbonamiento(int idCliente) throws Exception
	{
		boletasDao = boletasDao == null ? new BoletasDao(connectionDataPath) : boletasDao;
		return boletasDao.devolverAbonamiento(idCliente);
	} 
	
	/*
	 * Requerimiento RF14 
	 */
	public ArrayList<MensajeDevolucion> cancelarFuncion(int idAdministrador,int idFuncion) throws Exception
	{
		funcionDao = funcionDao == null ? new FuncionDao(connectionDataPath) : funcionDao;
		return funcionDao.cancelarFuncion(idAdministrador,idFuncion);
	}
	
	/*
	 * Requerimiento RFC7
	 */
	public ArrayList<ReporteAsistencia> consultarAsistencia(int idCliente) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.consultarAsistencia(idCliente);
	}
	
	
	/*
	 * Requerimiento RFC8 
	 */
	
	public ArrayList<ReporteCompania> informacionCompania(int idUsuario,int idCompania) throws Exception
	{
		companiaDao = companiaDao == null ? new CompaniaDao(connectionDataPath) : companiaDao;
		return companiaDao.informacionCompania(idUsuario, idCompania);
	}
	
	/*
	 * Requerimiento RFC9 CONSULTAR ASISTENCIA
	 */
	public ArrayList<Cliente> consultarAsistenciaRFC9(int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.consultarAsistenciaRFC9(nomCompania,consulta);
	}
	
	/*
	 * Requerimiento RFC10 CONSULTAR ASISTENCIA V2
	 */
	public ArrayList<Cliente> asistenciaClientesRFC10(int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.asistenciaClientesRFC10(nomCompania,consulta);
	}
	
	/*
	 * Requerimiento RFC11 CONSULTAR COMPRA DE BOLETAS
	 */
	public ArrayList<DevolverRF11> consultarCompraBoletas(int idGerente, ConsultaRF11 consuulta) throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.consultarCompraBoletas(idGerente,consuulta);
	}
	
	/*
	 * Requerimiento RFC12 CONSULTAR LOS BUENOS CLIENTES
	 */
	public ArrayList<Cliente> consultarBuenosClientes(int idGerente)throws Exception
	{
		consultaDao = consultaDao == null ? new ConsultaDao(connectionDataPath) : consultaDao;
		return consultaDao.consultarBuenosClientes(idGerente);
	}
	
}

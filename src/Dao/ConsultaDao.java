package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import Vos.Boleta;
import Vos.Ciudad;
import Vos.Cliente;
import Vos.CompaniaTeatro;
import Vos.ConsultaFuncion;
import Vos.ConsultaRF11;
import Vos.ConsultaRF9;
import Vos.ConsultaRFC5;
import Vos.DevolverRF11;
import Vos.Espectaculo;
import Vos.Funcion;
import Vos.Localidad;
import Vos.Preferencia;
import Vos.Reporte;
import Vos.ReporteAsistencia;
import Vos.ReporteCompania;
import Vos.ReporteRFC5;
import Vos.Sitio;
import Vos.Usuario;

public class ConsultaDao 
{
	private Connection conexion;

	private String user;

	private String password;

	private String url;

	private String driver;
	
	//private Connection conn;

	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public ConsultaDao(String conectionData) 
	{
		initConnectionData(conectionData);
	}
	
	//public void setConn(Connection con){
		//this.conn = con;
	//}
	
	public Connection getcon()
	{
		return conexion;
	}


	private void initConnectionData(String conectionData) 
	{
		try {
			File arch = new File(conectionData);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void establecerConexion() throws SQLException
	{
		System.out.println("Connecting to: " + url + " With user: " + user);
		conexion = DriverManager.getConnection(url, user, password);
	}

	public void closeConnection(Connection connection) throws SQLException
	{
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			System.err.println("SQLException in closing Connection:");
			exception.printStackTrace();
			throw exception;
		}
	}
	
	
	///////////////////////////////////////////////////////////////
	//   					  REQUERIMIENTOS 				   	//
	//////////////////////////////////////////////////////////////
	
	
	/*
	 * Requerimiento RF1 Registrar usuario
	 */
	
	public void registrarUsuario(Usuario pUsuario) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			System.out.println(pUsuario);
			establecerConexion();
			String sql = "INSERT INTO USUARIOS (IDUSUARIO,NOMBRE,APELLIDO, CORREO,IDENTIFICACION,ROL)"
					+ "VALUES("+ pUsuario.getIdUsuario()+",'"+ pUsuario.getNombre() +"','"+pUsuario.getApellido() +
					"','" + pUsuario.getCorreo() + "'," + pUsuario.getIdentificacion()
					+",'" + pUsuario.getRol() +"')";

			System.out.println(sql);
			
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF2 Registrar cliente
	 */
	public void registrarCliente(Cliente pCliente) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			System.out.println(pCliente);
			establecerConexion();
			String sql = "INSERT INTO CLIENTES (IDCLIENTE,NOMBRE,APELLIDO, CORREO,IDENTIFICACION)"
					+ "VALUES("+ pCliente.getIdCliente() +",'"+ pCliente.getNombre() +"','"+pCliente.getApellido() +
					"','" + pCliente.getCorreo() + "'," + pCliente.getIdentificacion()
					  +")";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF3 Registrar compañia de teatro
	 */
	public void registrarCompaniaTeatro(CompaniaTeatro pCompania) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			System.out.println(pCompania);
			establecerConexion();
			String sql = "INSERT INTO COMPANIASTEATRO (IDCOMPANIA,NOMBRE,PAISORIGEN)"
					+ "VALUES("+ pCompania.getIdCompania() +",'"+ pCompania.getNombre() +"','"+ 
					pCompania.getPaisOrigen() + "')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF4 Registrar espectaculo
	 */
	public void registrarEspectaculo(Espectaculo pEspectaculo) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			System.out.println(pEspectaculo);
			establecerConexion();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date fecha = pEspectaculo.getFechaRealizacion();
			String fechaAInsertar = formatter.format(fecha);
			String sql = "INSERT INTO ESPECTACULOS (IDESPECTACULO,NOMBRE,DURACION,COSTOREALIZACION,IDIOMA,FECHAREALIZACION,PUBLICOOBJETIVO)"
					+ "VALUES("+ pEspectaculo.getIdEspectaculo() +",'"+ pEspectaculo.getNombre() +"',"+ 
					pEspectaculo.getDuracion() + "," + pEspectaculo.getCostoRealizacion() +
					",'"+ pEspectaculo.getIdioma() + "'," + ", TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') ,"
					+ ",'" + pEspectaculo.getPublicoObjetivo() +"')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF5 Registrar sitio
	 */
	public void registrarSitio(Sitio pSitio) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			System.out.println(pSitio);
			establecerConexion();
			String sql = "INSERT INTO SITIOS (IDSITIO,NOMBRE,CAPACIDAD,TIPOSILLETERIA,TIPOPROTECCION)"
					+ "VALUES(";
			sql += pSitio.getIdSitio() +",'";
			sql += pSitio.getNombre() + "',";
			sql += pSitio.getCapacidad() + ",'";
			sql += pSitio.getTipoSilleteria() + "','";
			sql += pSitio.getTipoProteccion() + "')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF6 Programar funcion
	 */
	public void programarFuncion(Funcion pFuncion) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			System.out.println(pFuncion);
			establecerConexion();
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date fecha = pFuncion.getFecha();

			String fechaAInsertar = formatter.format(fecha);
			String sql = "INSERT INTO FUNCIONES (IDFUNCION,FECHA,COSTO,ESTADO)"
					+ "VALUES(";
			sql += pFuncion.getIdFuncion() +",'";
			sql += "TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') ,";
			sql += pFuncion.getCosto() + ",'";
			sql += pFuncion.getEstado() + "','";
			//sql += pFuncion.getRealizacion() + "')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF7 Registrar preferencia cliente
	 */
	public void registrarPreferencia(Preferencia pPreferencia,Integer pIdCliente) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			System.out.println(pPreferencia);
			establecerConexion();
			String sql = "INSERT INTO PREFERENCIAS (IDPREFERENCIA,NOMBRECATEGORIA,NOMBRESITIO)"
					+ "VALUES(";
			sql += pPreferencia.getIdPreferencia() +",'";
			sql += pPreferencia.getNombreCategoria() + "',";
			sql += pPreferencia.getNombreSitio() + ")";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO PREFIERE (IDPREFERENCIA,IDCLIENTE)"
					+ "VALUES(";
			sql += pPreferencia.getIdPreferencia() + ",";
			sql += pIdCliente + ")";
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF8 Registrar compra de boleta
	 */
	public void registrarCompraBoleta(Boleta pBoleta)throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			System.out.println(pBoleta);
			establecerConexion();
			String sql = "SELECT * FROM SILLA WHERE IDSILLA="+pBoleta.getIdSilla()+")";
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			String estado = rs.getString("ESTADO");
			if(estado.equals("OCUPADA"))
			{
				System.err.println("La silla se encuentra ocupada.No se puede realizar compra.");
			}
			else
			{
				sql = "INSERT INTO BOLETAS (IDBOLETA,COSTO,IDSILLA,IDCLIENTE,REGISTRADO,IDLOCALIDAD)"
						+ "VALUES(";
				sql += pBoleta.getIdBoleta() + ",";
				sql += pBoleta.getCosto() + ",";
				sql += pBoleta.getIdSilla() + ",";
				sql += pBoleta.getIdCliente() + ",'";
				//sql += pBoleta.getRegistrado() +"',";
				sql += pBoleta.getIdLocalidad() + ")";

				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
			}
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			//throw e;
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	/*
	 * Requerimiento RF9 Registrar realizacion funcion de un espectaculo
	 */
	public Funcion registrarRealizacionFuncion(Integer idFuncion) throws Exception
	{

		PreparedStatement prepStmt = null;
		Funcion fun = new Funcion();
		try 
		{
			establecerConexion();
			String sql = "SELECT * FROM FUNCIONES WHERE IDFUNCION="+idFuncion+")";
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			
			String estado = rs.getString("ESTADO");
			Date fecha = rs.getDate("FECHA");
			Date fechaActual = new Date();
			if(estado.equals("CONFIRMADO") && (fecha.compareTo(fechaActual)==0))
			{
				sql = "INSERT INTO FUNCIONES (REALIZACION)"
						+ "VALUES(";
				sql += "'REALIZADO')";
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
			}
			else
			{
				sql = "INSERT INTO FUNCIONES (REALIZACION)"
						+ "VALUES(";
				sql += "'NO REALIZADO')";
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
			}

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		return fun;
	}
	
	
	/*
	 * Requerimiento RFC1 CONSULTAR LAS FUNCIONES DE ESPECTÁCULOS PROGRAMADOS EN FESTIVANDES
	 */
	public ArrayList<Funcion> darFuncionesEspectaculos(Integer pIdEsp) throws Exception
	{
		PreparedStatement prepStmt = null;
		try 
		{
			establecerConexion();
			String sql = "SELECT * FROM FUNCIONES WHERE ESP_IDESP="+pIdEsp;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			ArrayList<Funcion> funciones = new ArrayList<Funcion>();
			while(rs.next())
			{
				Funcion func = new Funcion (rs.getInt("IDFUNCION"),rs.getDate("FECHA"),rs.getInt("COSTO"),rs.getString("ESTADO"));
				
				funciones.add(func);
			}
			return funciones;
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	
	}
	
	/*
	 * Requerimiento RFC2 CONSULTAR UN SITIO
	 */
	public Sitio darSitio(Integer pId) throws Exception
	{
		PreparedStatement prepStmt = null;
		try 
		{
			establecerConexion();
			String sql = "SELECT * FROM (((SITIOS INNER JOIN FUNCIONES ON SITIOS.IDSITIO=FUNCIONES.SITIO_IDS) INNER JOIN ESPECTACULOS ON FUNCIONES.ESP_IDESP=ESPECTACULOS.IDESPECTACULO) INNER JOIN (LOCALIDADES INNER JOIN CUENTACON ON CUENTACON.LOC_IDLO=LOCALIDADES.IDLOCALIDAD) ON CUENTACON.SIT_IDS=SITIOS.IDSITIO) WHERE IDSITIO="+pId+"ORDER BY FUNCIONES.IDFUNCION ASC";
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();
			ArrayList<Funcion> funciones = new ArrayList<Funcion>();
			while(rs.next())
			{
				Localidad loc = new Localidad(rs.getInt("IDLOCALIDAD"),rs.getString("NOMBRE_2"),rs.getInt("CAPACIDAD_1"));
				localidades.add(loc);
				Funcion func = new Funcion(rs.getInt("IDFUNCION"),rs.getDate("FECHA"),rs.getInt("COSTO"),rs.getString("ESTADO"));
				funciones.add(func);
			}
			int idCid = rs.getInt("CI_IDC");
			sql = "SELECT * FROM CIUDADES WHERE IDCIUDAD="+idCid;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs2 = prepStmt.executeQuery();
			Sitio s = new Sitio (rs.getInt("IDSITIO"),rs.getString("NOMBRE"),rs.getInt("CAPACIDAD"),rs.getString("TIPOSILLETERIA"),rs.getString("TIPOPROTECCION"));
			while(rs2.next())
			{
				Ciudad c = new Ciudad(rs2.getInt("IDCIUDAD"), rs2.getString("NOMBRE"));
				s.setCiudad(c);
				s.setFunciones(funciones);
				s.setLocalidades(localidades);
			}
			return s;
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		
	}
	
	/*
	 * Requerimiento RFC3 GENERAR UN REPORTE DE UNA FUNCION.
	 * Debe discriminar por localidad.
	 */
	public Reporte generarReporteFuncionLocalidad(Integer pIdFuncion, Integer pLocalidad) throws Exception
	{
		PreparedStatement prepStmt = null;
		ArrayList<Reporte> reportes = new ArrayList<Reporte>();
		Reporte reporte = new Reporte();
		try 
		{
			establecerConexion();
			String sql = "SELECT COUNT(*) AS NUM,SUM(COSTO) AS GANANCIAS FROM (SELECT * FROM BOLETAS INNER JOIN SILLAS ON BOLETAS.SI_NSI=SILLAS.IDSILLA) WHERE FU_IDF ="+pIdFuncion+" AND LOC_IDL="+pLocalidad;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			
			Integer cantidad = 0;
			Double ganacias = (double)0;
			while(rs.next())
			{
					cantidad += rs.getInt("NUM");
					ganacias += rs.getDouble("GANANCIAS");
			}
			
			reporte = new Reporte(cantidad,ganacias);
			

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		return reporte;
		
	}
	
	/*
	 * Requerimiento RFC3 GENERAR UN REPORTE DE UNA FUNCION.
	 * Debe discriminar por usuarios registrados.
	 */
	public Reporte generarReporteFuncionUsuario(Integer pIdFuncion, Integer pTipo) throws Exception
	{
		PreparedStatement prepStmt = null;
		//ArrayList<Reporte> reportes = new ArrayList<Reporte>();
		Reporte reporte = null;
		String sql;
		try 
		{
			establecerConexion();
			if (pTipo==1)
			{
				sql = "SELECT COUNT(*) AS NUM,SUM(COSTO) AS GANANCIAS FROM (SELECT * FROM BOLETAS INNER JOIN COMPRA ON BOLETAS.IDBOLETA=COMPRA.BOL_IDBOL) INNER JOIN (SELECT CLIENTES.IDCLIENTE FROM CLIENTES INNER JOIN USUARIOS ON CLIENTES.IDENTIFICACION=USUARIOS.IDENTIFICACION) ON CL_IDCL=IDCLIENTE WHERE FU_IDF ="+pIdFuncion;
			}
			else
			{
				sql = "SELECT COUNT(*) AS NUM,SUM(COSTO) AS GANANCIAS FROM (SELECT * FROM BOLETAS INNER JOIN COMPRA ON BOLETAS.IDBOLETA=COMPRA.BOL_IDBOL) INNER JOIN (SELECT CLIENTES.IDCLIENTE FROM CLIENTES INNER JOIN USUARIOS ON NOT CLIENTES.IDENTIFICACION=USUARIOS.IDENTIFICACION) ON CL_IDCL=IDCLIENTE WHERE FU_IDF ="+pIdFuncion; 
			}
			
			
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			Integer cantidad = 0;
			Double ganacias = (double)0;
			while(rs.next())
			{
					cantidad += rs.getInt("NUM");
					ganacias += rs.getDouble("GANANCIAS");
				
			}
			reporte = new Reporte(cantidad,ganacias);
			

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		return reporte;
		
	}
	
	/*
	 * Requerimiento RFC4 GENERAR UN REPORTE DE ESPECTACULO por sitio
	 */
	public Reporte generarReporteEspectaculoSitio(Integer pIdEspectaculo, Integer pIdSitio) throws SQLException
	{
		PreparedStatement prepStmt = null;
		//ArrayList<Reporte> reportes = null;
		Reporte reporte = null;
		try 
		{
			establecerConexion();
			String sql = "SELECT COUNT(*) AS NUM,SUM(COSTO) AS GANANCIAS FROM (SELECT FUNCIONES.IDFUNCION, FUNCIONES.SITIO_IDS, FUNCIONES.ESP_IDESP FROM FUNCIONES) INNER JOIN BOLETAS ON BOLETAS.FU_IDF=IDFUNCION WHERE SITIO_IDS ="+pIdSitio+"AND ESP_IDESP="+pIdEspectaculo;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			Integer cantidad = 0;
			Double ganacias = (double)0;
			while (rs.next()) 
			{
				cantidad += rs.getInt("NUM");
				ganacias += rs.getDouble("GANANCIAS");
			}
			reporte = new Reporte(cantidad,ganacias);

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		return reporte;		
	}
	
	/*
	 * Requerimiento RFC4 GENERAR UN REPORTE DE ESPECTAULO por funcion
	 */
	public Reporte generarReporteEspectaculoFuncion(Integer pIdEspectaculo, Integer pIdFuncion) throws SQLException
	{
		PreparedStatement prepStmt = null;
		//ArrayList<Reporte> reportes = null;
		Reporte reporte = null;
		try 
		{
			establecerConexion();
			String sql = "SELECT COUNT(*) AS NUM,SUM(COSTO) AS GANANCIAS FROM (SELECT FUNCIONES.IDFUNCION, FUNCIONES.SITIO_IDS, FUNCIONES.ESP_IDESP FROM FUNCIONES) INNER JOIN BOLETAS ON BOLETAS.FU_IDF=IDFUNCION WHERE IDFUNCION ="+pIdFuncion+" AND ESP_IDESP="+pIdEspectaculo;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			Integer cantidad = 0;
			Double ganacias = (double) 0;
			while (rs.next()) 
			{
				cantidad += rs.getInt("NUM");
				ganacias += rs.getDouble("GANANCIAS");
				
				
			}
			reporte = new Reporte(cantidad, ganacias);

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		return reporte;		
	}
	
	/*
	 * Requerimiento RFC5 CONSULTAR RENTABILIDAD DE UNA COMPAÑÍA (BONO)
	 */
	public ArrayList<ReporteRFC5> rentabilidadCompania(Integer idUsuario,ConsultaRFC5 consulta) throws Exception
	{
		ArrayList<ReporteRFC5> devolver = new ArrayList<ReporteRFC5>();
		
		PreparedStatement prepStmt = null;
		
		
		try 
		{
			establecerConexion();
			//Verifica si es un usuario
			String sql = "SELECT * FROM USUARIOS WHERE IDUSUARIO="+idUsuario;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			String rol = "NADA";
			
			//Valores a devolver//
			int idSitio = 0;
			int idEspectaculo = 0;
			String categoria = "";
			int numBoletasVendidas = 0;
			int cantAsistentes =0;
			int proporcionAsistencia = 0;
			int valorTotalFacturado =0;
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date fecha = consulta.getFechaInicial();
			String fechaAInsertar = formatter.format(fecha);
		
			Date fecha2 = consulta.getFechaFinal();
			String fechaAInsertar2 = formatter.format(fecha2);
		
			
			while(rs.next())
			{
				//Asigna el rol
				rol = rs.getString("ROL");
				System.out.println(rol);
			}
			
			//Si es administrador 
			if(rol.equals("ADMINISTRADOR"))
			{	
				
				
				sql = "SELECT SITIOS.IDSITIO,ESPECTACULOS.IDESPECTACULO,CATEGORIAS.NOMBRE, SITIOS.CAPACIDAD,BOLETAS.COSTO,COUNT(BOLETAS.IDBOLETA) AS CANTBOL";
				sql += " FROM SITIOS INNER JOIN FUNCIONES ON SITIOS.IDSITIO = FUNCIONES.SITIO_IDS";
				sql += " INNER JOIN ESPECTACULOS ON ESPECTACULOS.IDESPECTACULO = FUNCIONES.ESP_IDESP";
				sql += " INNER JOIN BOLETAS ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION";
				sql += " INNER JOIN CLASIFICACION ON CLASIFICACION.ESP_IDESP = ESPECTACULOS.IDESPECTACULO";
				sql += " INNER JOIN CATEGORIAS ON CATEGORIAS.IDCATEGORIA = CLASIFICACION.CAT_IDCAT";
				sql += " WHERE FUNCIONES.FECHA BETWEEN TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+ fechaAInsertar2 + "' , 'YYYY-MM-DD HH24:MI:SS')";
				sql += " group by SITIOS.IDSITIO, ESPECTACULOS.IDESPECTACULO, CATEGORIAS.NOMBRE, SITIOS.CAPACIDAD, BOLETAS.COSTO";
						
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs2 = prepStmt.executeQuery();
				
				while(rs2.next())
				{
					idSitio = rs2.getInt("IDSITIO");
					idEspectaculo = rs2.getInt("IDESPECTACULO");
					categoria = rs2.getString("NOMBRE");
					numBoletasVendidas = rs2.getInt("CANTBOL");
					cantAsistentes = numBoletasVendidas;
					proporcionAsistencia = (rs2.getInt("CAPACIDAD")/numBoletasVendidas);
					valorTotalFacturado =numBoletasVendidas*rs2.getInt("COSTO");
					
					ReporteRFC5 nuevo = new ReporteRFC5(idSitio, idEspectaculo, categoria, numBoletasVendidas, cantAsistentes, proporcionAsistencia, valorTotalFacturado);
					devolver.add(nuevo);
					
				}
				
									}
		
			//Si es de la compañia 
			if(rol.equals("COMPANIATEATRO"))
			{
				sql = "SELECT SITIOS.IDSITIO,ESPECTACULOS.IDESPECTACULO,CATEGORIAS.NOMBRE, SITIOS.CAPACIDAD,BOLETAS.COSTO,COUNT(BOLETAS.IDBOLETA) AS CANTBOL";
				sql += " FROM SITIOS INNER JOIN FUNCIONES ON SITIOS.IDSITIO = FUNCIONES.SITIO_IDS";
				sql += " INNER JOIN ESPECTACULOS ON ESPECTACULOS.IDESPECTACULO = FUNCIONES.ESP_IDESP";
				sql += " INNER JOIN BOLETAS ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION";
				sql += " INNER JOIN CLASIFICACION ON CLASIFICACION.ESP_IDESP = ESPECTACULOS.IDESPECTACULO";
				sql += " INNER JOIN CATEGORIAS ON CATEGORIAS.IDCATEGORIA = CLASIFICACION.CAT_IDCAT";
				sql += " WHERE FUNCIONES.FECHA BETWEEN TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+ fechaAInsertar2 + "' , 'YYYY-MM-DD HH24:MI:SS')";
				sql += " group by SITIOS.IDSITIO, ESPECTACULOS.IDESPECTACULO, CATEGORIAS.NOMBRE, SITIOS.CAPACIDAD, BOLETAS.COSTO";
						
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs3 = prepStmt.executeQuery();
				
				while(rs3.next())
				{
					idSitio = rs3.getInt("IDSITIO");
					idEspectaculo = rs3.getInt("IDESPECTACULO");
					categoria = rs3.getString("NOMBRE");
					numBoletasVendidas = rs3.getInt("CANTBOL");
					cantAsistentes = numBoletasVendidas;
					proporcionAsistencia = (rs3.getInt("CAPACIDAD")/numBoletasVendidas);
					valorTotalFacturado =numBoletasVendidas*rs3.getInt("COSTO");
					
					ReporteRFC5 nuevo = new ReporteRFC5(idSitio, idEspectaculo, categoria, numBoletasVendidas, cantAsistentes, proporcionAsistencia, valorTotalFacturado);
					devolver.add(nuevo);
					
				}
			}
			
			

		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}

		
		return devolver;
	}
	
	/*
	 * Requerimiento RFC6 OBTENER LOS DATOS DE LOS ESPECTÁCULOS MÁS POPULARES (BONO)
	 */
	
	/*
	 * Requerimiento RFC7 CONSULTAR ASISTENCIA AL FESTIVAL DE UN CLIENTE REGISTRADO
	 */
	public ArrayList<ReporteAsistencia> consultarAsistencia(int idCliente) throws Exception
	{
		ArrayList<ReporteAsistencia> funciones = new ArrayList<ReporteAsistencia>();
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			//Verifica que el cliente este registrado
			String sql = "SELECT * FROM USUARIOS WHERE IDUSUARIO="+idCliente;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			String rol = "NADA";
			
			//Datos de la función a devolver
			int idFuncion = 0;
			int costo = 0;
			String estado = "";
			Date fecha = new Date();
			String es = "";
			int numClientes = 0;
			
			while(rs.next())
			{
				rol= rs.getString("ROL");
			}
			if(rol.equals("ADMINISTRADOR"))
			{
				sql = "SELECT DISTINCT CLIENTES.IDCLIENTE FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US";
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs3 = prepStmt.executeQuery();
				
				while(rs3.next())
				{
					numClientes++;
					
				}
				
				System.out.println(numClientes);
				for(int i=0; i<numClientes;i++ )
				{
					sql = "SELECT DISTINCT FUNCIONES.IDFUNCION, FUNCIONES.COSTO, FUNCIONES.ESTADO, FUNCIONES.FECHA";
					sql += " FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US INNER JOIN FUNCIONES ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION";
					sql += " WHERE IDCLIENTE = " + idCliente;
					
					prepStmt = conexion.prepareStatement(sql);
					ResultSet rs4 = prepStmt.executeQuery();
					Date fechaActual = new Date();
					while(rs4.next())
					{
						idFuncion = rs4.getInt("IDFUNCION");
						costo = rs4.getInt("COSTO");
						estado = rs4.getString("ESTADO");
						fecha = rs4.getDate("FECHA");
						
						if(fecha.getTime()>fechaActual.getTime())
						{
							es="Finalizado";
						}
						else if(fecha.getTime()<fechaActual.getTime())
						{
							es="Prevista";
						}
						else
						{
							es="En curso";
						}
					}
					ReporteAsistencia func = new ReporteAsistencia(idFuncion, fecha, costo, estado, es,idCliente);
					funciones.add(func);
					idCliente++;
				}
			}
			else
			{
				sql = "SELECT DISTINCT FUNCIONES.IDFUNCION, FUNCIONES.COSTO, FUNCIONES.ESTADO, FUNCIONES.FECHA";
				sql += " FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US INNER JOIN FUNCIONES ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION";
				sql += " WHERE IDCLIENTE = " + idCliente;
				
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs2 = prepStmt.executeQuery();
				Date fechaActual = new Date();
				while(rs2.next())
				{
					idFuncion = rs2.getInt("IDFUNCION");
					costo = rs2.getInt("COSTO");
					estado = rs2.getString("ESTADO");
					fecha = rs2.getDate("FECHA");
					
					if(fecha.getTime()>fechaActual.getTime())
					{
						es="Finalizado";
					}
					else if(fecha.getTime()<fechaActual.getTime())
					{
						es="Prevista";
					}
					else
					{
						es="En curso";
					}
				}
				ReporteAsistencia func = new ReporteAsistencia(idFuncion, fecha, costo, estado, es,idCliente);
				funciones.add(func);
			}
			
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		
		return funciones;
	}
	
	
	/*
	 * Requerimiento RFC9 CONSULTAR ASISTENCIA
	 */
	public ArrayList<Cliente> consultarAsistenciaRFC9(int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		Date fechaActual = new Date();
		ArrayList<Cliente> devolver = new ArrayList<Cliente>();
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
		
			Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date fecha = consulta.getFechaInicial();
			String fechaAInsertar = formatter.format(fecha);
		
			Date fecha2 = consulta.getFechaFinal();
			String fechaAInsertar2 = formatter.format(fecha2);
			
			String sql = "SELECT DISTINCT CLIENTES.IDCLIENTE,CLIENTES.NOMBRE,CLIENTES.APELLIDO,CLIENTES.CORREO,CLIENTES.IDENTIFICACION,COUNT(*)";
			sql += " FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US";
			sql += " INNER JOIN FUNCIONES ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION INNER JOIN ESPECTACULOS ON FUNCIONES.ESP_IDESP = ESPECTACULOS.IDESPECTACULO";
			sql += " INNER JOIN OFRECE ON ESPECTACULOS.IDESPECTACULO = OFRECE.ESP_IDESP INNER JOIN COMPANIASTEATRO ON OFRECE.COTE_IDCOMP = COMPANIASTEATRO.IDCOMPANIA";
			sql += " WHERE COMPANIASTEATRO.IDCOMPANIA ='"+nomCompania+"' AND FUNCIONES.FECHA BETWEEN TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+ fechaAInsertar2 + "' , 'YYYY-MM-DD HH24:MI:SS')";
			sql += " GROUP BY CLIENTES.NOMBRE, CLIENTES.IDCLIENTE, CLIENTES.APELLIDO, CLIENTES.CORREO, CLIENTES.IDENTIFICACION";
			sql += " ORDER BY " + consulta.getOrdenado1() + " " + consulta.getOrdenado2();
			prepStmt = conexion.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = prepStmt.executeQuery();
			
			int id = 0;
			String nombre = "";
			String apellido = "";
			String correo = "";
			int identificacion = 0;
			while(rs.next())
			{
				id = rs.getInt("IDCLIENTE");
				nombre = rs.getString("NOMBRE");
				apellido = rs.getString("APELLIDO");
				correo = rs.getString("CORREO");
				identificacion = rs.getInt("IDENTIFICACION");
				Cliente nuevo = new Cliente(id, nombre, apellido, correo, identificacion);
				devolver.add(nuevo);
			}
		
			
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		Date fechaFutura = new Date();
		long resta = fechaFutura.getTime()-fechaActual.getTime();
		File archivo = new File("./RF9");
		System.out.println(archivo.getAbsolutePath());
		PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
		writer.println("-------------------------------------");
		writer.println("--Fecha : "+ fechaFutura.getDate() + " -----");
		writer.println("---Tiempo : "+ resta + " ms------");
		writer.close();
		return devolver;
	}
	
	/*
	 * Requerimiento RFC10 CONSULTAR ASISTENCIA V2
	 */
	public ArrayList<Cliente> asistenciaClientesRFC10(int nomCompania,ConsultaRF9 consulta) throws Exception
	{
		Date fechaActual = new Date();
		ArrayList<Cliente> devolver = new ArrayList<Cliente>();
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			//Verifica que el cliente este registrado
			
			Date fecha = consulta.getFechaInicial();
			String fechaAInsertar = formatter.format(fecha);
		
			Date fecha2 = consulta.getFechaFinal();
			String fechaAInsertar2 = formatter.format(fecha2);
			
			String sql = "SELECT DISTINCT CLIENTES.IDCLIENTE,CLIENTES.NOMBRE,CLIENTES.APELLIDO,CLIENTES.CORREO,CLIENTES.IDENTIFICACION,COUNT(*) ";
			sql += " FROM CLIENTES";
			sql += " WHERE CLIENTES.IDCLIENTE NOT IN (SELECT DISTINCT CLIENTES.IDCLIENTE FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US";
			sql += " INNER JOIN FUNCIONES ON BOLETAS.FU_IDF = FUNCIONES.IDFUNCION INNER JOIN ESPECTACULOS ON FUNCIONES.ESP_IDESP = ESPECTACULOS.IDESPECTACULO";
			sql += " INNER JOIN OFRECE ON ESPECTACULOS.IDESPECTACULO = OFRECE.ESP_IDESP INNER JOIN COMPANIASTEATRO ON OFRECE.COTE_IDCOMP = COMPANIASTEATRO.IDCOMPANIA";
			sql += " WHERE COMPANIASTEATRO.IDCOMPANIA =1 AND FUNCIONES.FECHA BETWEEN TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+ fechaAInsertar2 + "' , 'YYYY-MM-DD HH24:MI:SS') )";
			sql += " GROUP BY CLIENTES.IDCLIENTE, CLIENTES.NOMBRE, CLIENTES.APELLIDO, CLIENTES.CORREO, CLIENTES.IDENTIFICACION";
			sql += " ORDER BY " + consulta.getOrdenado1() + " " + consulta.getOrdenado2();
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			
			int id = 0;
			String nombre = "";
			String apellido = "";
			String correo = "";
			int identificacion = 0;
			while(rs.next())
			{
				id = rs.getInt("IDCLIENTE");
				nombre = rs.getString("NOMBRE");
				apellido = rs.getString("APELLIDO");
				correo = rs.getString("CORREO");
				identificacion = rs.getInt("IDENTIFICACION");
				Cliente nuevo = new Cliente(id, nombre, apellido, correo, identificacion);
				devolver.add(nuevo);
			}
		
			
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		Date fechaFutura = new Date();
		long resta = fechaFutura.getTime()-fechaActual.getTime();
		File archivo = new File("./RF10");
		System.out.println(archivo.getAbsolutePath());
		PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
		writer.println("-------------------------------------");
		writer.println("--Fecha : "+ fechaFutura.getDate() + " -----");
		writer.println("---Tiempo : "+ resta + " ms------");
		writer.close();
		return devolver;
	}
	
	/*
	 * Requerimiento RFC11 CONSULTAR COMPRA DE BOLETAS
	 */
	public ArrayList<DevolverRF11> consultarCompraBoletas(int idGerente, ConsultaRF11 consuulta) throws Exception
	{
		Date fechaActual = new Date();
		ArrayList<DevolverRF11> devolver = new ArrayList<DevolverRF11>();
		
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date fecha = consuulta.getFechaInicial();
			String fechaAInsertar = formatter.format(fecha);
		
			Date fecha2 = consuulta.getFechaFinal();
			String fechaAInsertar2 = formatter.format(fecha2);
			
			String sql = "SELECT * FROM USUARIOS WHERE IDUSUARIO="+idGerente;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs2 = prepStmt.executeQuery();
			String rol = "NADA";
			while(rs2.next())
			{
				rol = rs2.getString("ROL");
			}
			if(rol.equalsIgnoreCase("Gerente"))
			{
				sql = "SELECT ESPECTACULOS.NOMBRE, FUNCIONES.FECHA, FUNCIONES.SITIO_IDS, COUNT(BOLETAS.IDBOLETA) ";
				sql += " FROM BOLETAS INNER JOIN CLIENTES ON BOLETAS.ID_US = CLIENTES.IDCLIENTE INNER JOIN FUNCIONES ON FUNCIONES.IDFUNCION = BOLETAS.FU_IDF";
				sql += " INNER JOIN ESPECTACULOS ON FUNCIONES.ESP_IDESP = ESPECTACULOS.IDESPECTACULO INNER JOIN OFRECECARACTERISTICA ON FUNCIONES.SITIO_IDS = OFRECECARACTERISTICA.SI_IDS";
				sql += " INNER JOIN CARACTERISTICAS ON OFRECECARACTERISTICA.CA_IDCA = CARACTERISTICAS.IDCARACTERISTICA ";
				sql += " WHERE FUNCIONES.FECHA BETWEEN TO_DATE('"+ fechaAInsertar + "' , 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+ fechaAInsertar2 + "' , 'YYYY-MM-DD HH24:MI:SS')";
				sql += " AND BOLETAS.LO_IFLO = " + consuulta.getIdLocalidad() +" AND CARACTERISTICAS.IDCARACTERISTICA = " + consuulta.getIdCaracteristica();
				sql += " group by ESPECTACULOS.NOMBRE, FUNCIONES.FECHA, FUNCIONES.SITIO_IDS"; 
				
				
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				String nombre = "";
				Date fecha3 = new Date();
				int idSitio = 0;
				int cantidadBoletas = 0;
				
				while(rs.next())
				{
					nombre = rs.getString("NOMBRE");
					fecha3 = rs.getDate("FECHA");
					idSitio = rs.getInt("SITIO_IDS");
					cantidadBoletas = rs.getInt("COUNT(BOLETAS.IDBOLETA)");
					DevolverRF11 nuevo = new DevolverRF11(nombre, fecha3, idSitio, cantidadBoletas);
					devolver.add(nuevo);
				}
			}
						
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		Date fechaFutura = new Date();
		long resta = fechaFutura.getTime()-fechaActual.getTime();
		File archivo = new File("./RF11");
		System.out.println(archivo.getAbsolutePath());
		PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
		writer.println("-------------------------------------");
		writer.println("--Fecha : "+ fechaFutura.getDate() + " -----");
		writer.println("---Tiempo : "+ resta + " ms------");
		writer.close();
		return devolver;
	}
	
	/*
	 * Requerimiento RFC12 CONSULTAR LOS BUENOS CLIENTES
	 */
	public ArrayList<Cliente> consultarBuenosClientes(int idGerente)throws Exception
	{
		Date fechaActual = new Date();
		ArrayList<Cliente> devolver = new ArrayList<Cliente>();
		
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			String sql = "SELECT * FROM USUARIOS WHERE IDUSUARIO="+idGerente;
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs2 = prepStmt.executeQuery();
			String rol = "NADA";
			while(rs2.next())
			{
				rol = rs2.getString("ROL");
			}
			if(rol.equalsIgnoreCase("Gerente"))
			{
				sql = "SELECT DISTINCT CLIENTES.IDCLIENTE, CLIENTES.NOMBRE,CLIENTES.APELLIDO,CLIENTES.CORREO,CLIENTES.IDENTIFICACION , COUNT(CLIENTES.IDCLIENTE) FROM CLIENTES INNER JOIN BOLETAS ON CLIENTES.IDCLIENTE = BOLETAS.ID_US ";
				sql += " WHERE BOLETAS.LO_IFLO = 100 GROUP BY CLIENTES.IDCLIENTE, CLIENTES.NOMBRE, CLIENTES.APELLIDO, CLIENTES.CORREO, CLIENTES.IDENTIFICACION";
				sql += " HAVING COUNT (*) >2 ";
				
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				int id = 0;
				String nombre = "";
				String apellido = "";
				String correo = "";
				int identificacion = 0;
				while(rs.next())
				{
					id = rs.getInt("IDCLIENTE");
					nombre = rs.getString("NOMBRE");
					apellido = rs.getString("APELLIDO");
					correo = rs.getString("CORREO");
					identificacion = rs.getInt("IDENTIFICACION");
					Cliente nuevo = new Cliente(id, nombre, apellido, correo, identificacion);
					devolver.add(nuevo);
				}
			
			}
			
			
			
		} catch (SQLException e) {
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			throw e;
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					System.err.println("SQLException in closing Stmt:");
					exception.printStackTrace();
					throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		Date fechaFutura = new Date();
		long resta = fechaFutura.getTime()-fechaActual.getTime();
		File archivo = new File("./RF12");
		System.out.println(archivo.getAbsolutePath());
		PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
		writer.println("-------------------------------------");
		writer.println("--Fecha : "+ fechaFutura.getDate() + " -----");
		writer.println("---Tiempo : "+ resta + " ms------");
		writer.close();
		return devolver;
	}
	
	

}

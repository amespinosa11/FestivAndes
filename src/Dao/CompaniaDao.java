package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import Vos.CompaniaTeatro;
import Vos.ReporteCompania;

public class CompaniaDao 
{
	private Connection conexion;

	private String user;

	private String password;

	private String url;

	private String driver;
	
	//private Connection conn;

	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public CompaniaDao(String conectionData) 
	{
		initConnectionData(conectionData);
	}
	
	//public void setConn(Connection con){
		//this.conn = con;
	//}


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
	 * Requerimiento RFC8 Consultar compania
	 */
	public ArrayList<ReporteCompania> informacionCompania(int idUsuario,int idCompania) throws Exception
	{
		ArrayList<ReporteCompania> devolver = new ArrayList<ReporteCompania>();
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
			int idCom = 0;
			String nomComapnia = "";
			String pais = "";
			String nombreEspectaculo = "";
			int gananciasTaquilla = 0;
			int capacidadTotal = 0;
			int capacidadOcupacionActual = 0;
			
			while(rs.next())
			{
				//Asigna el rol
				rol = rs.getString("ROL");
				System.out.println(rol);
			}
			
			//Si es administrador 
			if(rol.equals("ADMINISTRADOR"))
			{	
					sql = "SELECT DISTINCT FUNCIONES.IDFUNCION,COMPANIASTEATRO.IDCOMPANIA, COMPANIASTEATRO.NOMBRE AS NOMBRECOMPANIA, COMPANIASTEATRO.PAISORIGEN,ESPECTACULOS.NOMBRE AS NOMBREESPECTACULO,BOLETAS.COSTO, SITIOS.CAPACIDAD ";
					sql += "FROM COMPANIASTEATRO INNER JOIN OFRECE ON COMPANIASTEATRO.IDCOMPANIA = OFRECE.COTE_IDCOMP INNER JOIN ESPECTACULOS ON ESPECTACULOS.IDESPECTACULO = OFRECE.ESP_IDESP ";
					sql +=" INNER JOIN FUNCIONES ON ESPECTACULOS.IDESPECTACULO = FUNCIONES.ESP_IDESP INNER JOIN BOLETAS ON BOLETAS.FU_IDF=FUNCIONES.IDFUNCION ";
					sql += " INNER JOIN SITIOS ON SITIOS.IDSITIO = FUNCIONES.SITIO_IDS ";

					prepStmt = conexion.prepareStatement(sql);
					ResultSet rs3 = prepStmt.executeQuery();
					while(rs3.next())
					{
						idCom = rs3.getInt("IDCOMPANIA");
						nomComapnia = rs3.getString("NOMBRECOMPANIA");
						pais = rs3.getString("PAISORIGEN");
						nombreEspectaculo = rs3.getString("NOMBREESPECTACULO");
						gananciasTaquilla += rs3.getInt("COSTO") ;
						capacidadTotal = rs3.getInt("CAPACIDAD");
						capacidadOcupacionActual ++;
						ReporteCompania reporte = new ReporteCompania(idCom, nomComapnia, pais, nombreEspectaculo, gananciasTaquilla, capacidadTotal, capacidadOcupacionActual);
						devolver.add(reporte);
						
				}
					
				}
		
			//Si es de la compañia 
			if(rol.equals("COMPANIATEATRO"))
			{
				sql = "SELECT COMPANIASTEATRO.IDCOMPANIA, COMPANIASTEATRO.NOMBRE AS NOMBRECOMPANIA, COMPANIASTEATRO.PAISORIGEN,ESPECTACULOS.NOMBRE AS NOMBREESPECTACULO,BOLETAS.COSTO, SITIOS.CAPACIDAD ";
				sql += "FROM COMPANIASTEATRO INNER JOIN OFRECE ON COMPANIASTEATRO.IDCOMPANIA = OFRECE.COTE_IDCOMP INNER JOIN ESPECTACULOS ON ESPECTACULOS.IDESPECTACULO = OFRECE.ESP_IDESP ";
				sql +=" INNER JOIN FUNCIONES ON ESPECTACULOS.IDESPECTACULO = FUNCIONES.ESP_IDESP INNER JOIN BOLETAS ON BOLETAS.FU_IDF=FUNCIONES.IDFUNCION ";
				sql += " INNER JOIN SITIOS ON SITIOS.IDSITIO = FUNCIONES.SITIO_IDS WHERE COMPANIASTEATRO.IDCOMPANIA = "+ idCompania;

				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs2 = prepStmt.executeQuery();
				while(rs2.next())
				{
					idCom = idCompania;
					nomComapnia = rs2.getString("NOMBRECOMPANIA");
					pais = rs2.getString("PAISORIGEN");
					nombreEspectaculo = rs2.getString("NOMBREESPECTACULO");
					gananciasTaquilla += rs2.getInt("COSTO") ;
					capacidadTotal = rs2.getInt("CAPACIDAD");
					capacidadOcupacionActual ++;
					
				}
				
				ReporteCompania reporte = new ReporteCompania(idCom, nomComapnia, pais, nombreEspectaculo, gananciasTaquilla, capacidadTotal, capacidadOcupacionActual);
				devolver.add(reporte);

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

	
	
}

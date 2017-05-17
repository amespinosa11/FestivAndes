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
import java.util.Date;
import java.util.Properties;

import Vos.MensajeDevolucion;

public class FuncionDao 
{
	private Connection conexion;

	private String user;

	private String password;

	private String url;

	private String driver;
	
	//private Connection conn;

	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public FuncionDao(String conectionData) 
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
	 * Requerimiento RF14 Cancelar funcion
	 */
	public ArrayList<MensajeDevolucion> cancelarFuncion(int idAdministrador,int idFuncion) throws Exception
	{
		ArrayList<MensajeDevolucion> mens = new ArrayList<MensajeDevolucion>();
		
		//MensajeDevolucion devolver = new MensajeDevolucion("No se pudo cancelar la función");
		//mens.add(devolver);
		PreparedStatement prepStmt = null;
		
		
		try 
		{
			establecerConexion();
			boolean siEsAdmin = false;
			String sql = "SELECT * FROM USUARIOS WHERE IDUSUARIO= "+idAdministrador+ " AND ROL = 'ADMINISTRADOR'";
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next())
			{
				siEsAdmin = true;
				
			}
			
			//Verifica que el cliente este registrado
			
			if(siEsAdmin)
			{
				sql = "SELECT * FROM FUNCIONES INNER JOIN BOLETAS ON BOLETAS.FU_IDF=FUNCIONES.IDFUNCION  WHERE IDFUNCION ="+idFuncion;
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				while(rs.next())
				{
					
					int idBoleta = rs.getInt("IDBOLETA");
					int idCliente = rs.getInt("ID_US");
					Date fechaActual = new Date();
					Date fechaFuncion = rs.getDate("FECHA");
					
					//Verifica la fecha
					if(fechaFuncion.after(fechaActual))
					{
						//Cancela la función
						sql = "UPDATE FUNCIONES SET ESTADO='Cancelado' WHERE IDFUNCION="+idFuncion;
						prepStmt = conexion.prepareStatement(sql);
						prepStmt.execute();
						
						//closeConnection(this.conexion);
						
						//establecerConexion();
						
						boolean siEsCliente = false;
						sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE="+  idCliente;
						System.out.println(sql);
						prepStmt = conexion.prepareStatement(sql);
						ResultSet rs2 = prepStmt.executeQuery();
						while(rs2.next())
						{
							siEsCliente = true;
						}
						if(siEsCliente)
						{
							//Hace devolucion de las boletas
							sql="DELETE FROM BOLETAS WHERE IDBOLETA="+idBoleta;
							prepStmt = conexion.prepareStatement(sql);
							prepStmt.executeUpdate();
							
							MensajeDevolucion mensaje = new MensajeDevolucion("Devolución existosa. Dirijase a la oficina más cercana a recoger su dinero.");
							mens.add(mensaje);
						}
						else
						{
							MensajeDevolucion mensaje = new MensajeDevolucion("No se pudo hacer la devolución.");
						}
						
					}
					
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
		return mens;
	}
	
	

}

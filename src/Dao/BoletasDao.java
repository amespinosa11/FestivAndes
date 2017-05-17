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

import Vos.Boleta;
import Vos.InfoCompraAbonamiento;
import Vos.InfoCompraBoleta;
import Vos.MensajeDevolucion;
import Vos.Reporte;

public class BoletasDao 
{
	private Connection conexion;

	private String user;

	private String password;

	private String url;

	private String driver;
	
	//private Connection conn;

	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public BoletasDao(String conectionData) 
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
	 * Requerimiento RF10 Compra multiple de boletas
	 */
	public ArrayList<Boleta> comprarBoletas(int idCliente, InfoCompraBoleta pInfo) throws Exception
	{
		PreparedStatement prepStmt = null;
		ArrayList<Boleta> devolver = new ArrayList<Boleta>();
		
		
		try 
		{
			establecerConexion();
			
			boolean siEsCliente = false;
			String sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE="+  idCliente;
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next())
			{
				siEsCliente = true;
			}
			
			if(siEsCliente)
			{
				
				sql = "SELECT * FROM SILLAS WHERE ESTADO='Desocupada' AND LOC_IDL ="+  pInfo.getNomLocalidad()+" AND FUN_IDFUNC="+ pInfo.getIdFuncion();
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				int numeroBoletas = pInfo.getNumBoletas();
				int inicio = 0;
				int i = 0;
				int numFila = 0;
				int numSilla = 0;
				while(rs.next())
				{
					numFila = rs.getInt("NUMEROFILA");
					numSilla = rs.getInt("NUMEROSILLA");
					//Boleta porDefecto = new Boleta(inicio+1000, 15.0, 100, "Normal", idCliente, 1, 1, 1);
					
					if(inicio==0)
					{
						inicio=numSilla;
						i++;
					}
					else
					{
						if(inicio+i == numSilla&&i!=numeroBoletas)
						{
							i++;
						}
						else
						{
							inicio = 0;
							i = 0;
						}
					}
					
					
				}
				
				for(int j = 0; j<numeroBoletas;j++)
				{
					int si = numeroBoletas-inicio;
					int x= numSilla -si;
					int id = inicio+1045;
					Boleta nueva = new Boleta(inicio+1045, 30000.00, numSilla-si, "NORMAL",idCliente, numFila, pInfo.getIdFuncion(), pInfo.getNomLocalidad());
					devolver.add(nueva);
					sql = "INSERT INTO BOLETAS VALUES("+id+","+ 30000+","+pInfo.getIdFuncion()+","+x+","+numFila+","+pInfo.getNomLocalidad()+",'NORMAL',"+idCliente+")";
					System.out.println(sql);
					prepStmt = conexion.prepareStatement(sql);	
					prepStmt.execute();
					
					sql = "UPDATE SILLAS SET ESTADO='Ocupada' WHERE NUMEROSILLA="+x+"AND NUMEROFILA="+numFila+" AND FUN_IDFUNC="+ pInfo.getIdFuncion();
					prepStmt = conexion.prepareStatement(sql);
					prepStmt.execute(); 
					inicio++;
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
	 * Requerimiento RF11 Registra abonamiento
	 */
		public ArrayList<Boleta> registrarAbonamiento(int idCliente, InfoCompraAbonamiento pInfoCompra) throws Exception {
		
		ArrayList<Boleta> devolver = new ArrayList<Boleta>();
		PreparedStatement prepStmt = null;
		try 
		{
			establecerConexion();
			
			boolean siEsCliente = false;
			String sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE="+  idCliente;
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			while(rs1.next())
			{
				siEsCliente = true;
			}
			
			if(siEsCliente)
			{
				int idLocalidad = pInfoCompra.getIdLocalidad();
				int idFuncion = 0;
				int id = 0;
				for(int i = 0; i<pInfoCompra.getIdsFunciones().size();i++)
				{
					sql = "SELECT * FROM BOLETAS";
					System.out.println(sql);
					prepStmt = conexion.prepareStatement(sql);
					ResultSet rs3 = prepStmt.executeQuery();
					while(rs3.next())
					{
						id++;
					}
					
					idFuncion = pInfoCompra.getIdsFunciones().get(i);
					sql = "SELECT * FROM SILLAS WHERE ESTADO='Desocupada' AND LOC_IDL ="+  idLocalidad +" AND FUN_IDFUNC="+ idFuncion;
					System.out.println(sql);
					prepStmt = conexion.prepareStatement(sql);
					ResultSet rs2 = prepStmt.executeQuery();
					
					int silla = 0;
					int fila = 0;
					
				
					while(rs2.next() )
					{
						//System.out.println("ENTRA");
						silla = rs2.getInt("NUMEROFILA");
						fila = rs2.getInt("NUMEROFILA");
						
						
					}
					
					
					int costo = 30000*idLocalidad;
					Boleta nueva = new Boleta(id, 30000.00*idLocalidad, silla, "ABONO",idCliente, fila, idFuncion, idLocalidad);
					devolver.add(nueva);
					
					sql = "INSERT INTO BOLETAS VALUES("+id+","+ costo+","+idFuncion+","+silla+","+fila+","+idLocalidad+",'ABONO',"+idCliente+")";
					System.out.println(sql);
					prepStmt = conexion.prepareStatement(sql);	
					prepStmt.execute();
					
					sql = "UPDATE SILLAS SET ESTADO='Ocupada' WHERE NUMEROSILLA="+silla+"AND NUMEROFILA="+fila+" AND FUN_IDFUNC="+ idFuncion;
					prepStmt = conexion.prepareStatement(sql);
					System.out.println(sql);
					prepStmt.execute(); 
				
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
	 * Requerimiento RF12 Devolver boletas
	 */
	public MensajeDevolucion devolverBoleta(int idCliente,int idBoleta) throws Exception
	{
		MensajeDevolucion mensaje = new MensajeDevolucion("No se pudo realizar la devolución");
		
		PreparedStatement prepStmt = null;
		
		
		try 
		{
			establecerConexion();
			//Verifica que el cliente este registrado
			boolean siEsCliente = false;
			String sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE="+  idCliente;
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			
			while(rs1.next())
			{
				siEsCliente = true;
			}
			
			//Si esta registrado procede a buscar la boleta
			if(siEsCliente)
			{
				sql = "SELECT * FROM BOLETAS INNER JOIN FUNCIONES ON BOLETAS.FU_IDF=FUNCIONES.IDFUNCION WHERE IDBOLETA="+idBoleta; 
				
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				
					int numSilla = 0;
					int numFila = 0;
					int idFuncion = 0;
					Date fechaFuncion = new Date(2016,06,12);
					boolean cumpleFecha = false;
					while(rs.next())
					{
						numSilla = rs.getInt("SI_NSI");
						numFila = rs.getInt("SI_NFI");
						idFuncion = rs.getInt("IDFUNCION");
						Date fechaActual = new Date();
						fechaFuncion = rs.getDate("FECHA");
						long dif =fechaFuncion.getTime()-fechaActual.getTime();
						Date n = new Date(dif);
						String formato="dd";
						SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
						int dia =Integer.parseInt(dateFormat.format(n));
						System.out.println();
						if(dia>=5)
						{
							cumpleFecha = true;
						}
					}
					
					if(cumpleFecha)
					{
						
						
						//closeConnection(this.conexion);

						//establecerConexion();

						//Elimina la boleta
						sql="DELETE FROM BOLETAS WHERE IDBOLETA="+idBoleta;
						prepStmt = conexion.prepareStatement(sql);
						prepStmt.executeUpdate();
						
						closeConnection(this.conexion);
						
						establecerConexion();
						
						//Actualiza el estado de la silla
						sql = "UPDATE SILLAS SET ESTADO='Desocupada' WHERE NUMEROSILLA="+numSilla+" AND NUMEROFILA="+numFila+" AND FUN_IDFUNC="+ idFuncion;
						prepStmt = conexion.prepareStatement(sql);
						prepStmt.execute();
						mensaje = new MensajeDevolucion("Devolución exitosa.Dirijase a la oficina más cercana a recoger su dinero");
						
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
		
		return mensaje;
	}
	
	/*
	 * Requerimiento RF13 Devolver abonamiento
	 */
	public ArrayList<MensajeDevolucion> devolverAbonamiento(int idCliente) throws Exception
	{
		ArrayList<MensajeDevolucion> mens = new ArrayList<MensajeDevolucion>();
		//MensajeDevolucion mensaje = new MensajeDevolucion("No se pudo realizar la devolución");
		
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			
			//Verifica que el cliente este registrado
			boolean siEsCliente = false;
			String sql = "SELECT * FROM CLIENTES WHERE IDCLIENTE="+  idCliente;
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs1 = prepStmt.executeQuery();
			
			while(rs1.next())
			{
				siEsCliente = true;
			}
			
			//Si esta registrado procede a buscar la boleta
			if(siEsCliente)
			{
				sql = "SELECT * FROM BOLETAS INNER JOIN FUNCIONES ON BOLETAS.FU_IDF=FUNCIONES.IDFUNCION WHERE TIPO = 'ABONO'"; 
				
				prepStmt = conexion.prepareStatement(sql);
				ResultSet rs = prepStmt.executeQuery();
				
				
					int numSilla = 0;
					int numFila = 0;
					int idBoleta = 0;
					int idFuncion = 0;
					Date fechaFuncion = new Date(2016,06,12);
					boolean cumpleFecha = false;
					while(rs.next())
					{
						numSilla = rs.getInt("SI_NSI");
						numFila = rs.getInt("SI_NFI");
						idBoleta = rs.getInt("IDBOLETA");
						idFuncion = rs.getInt("IDFUNCION");
						
						Date fechaActual = new Date();
						fechaFuncion = rs.getDate("FECHA");
						long dif =fechaFuncion.getTime()-fechaActual.getTime();
						Date n = new Date(dif);
						String formato="dd";
						SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
						int dia =Integer.parseInt(dateFormat.format(n));
						System.out.println(dia);
						if(dia>=21)
						{
							System.out.println("ACA");
							sql="DELETE FROM BOLETAS WHERE IDBOLETA="+idBoleta;
							prepStmt = conexion.prepareStatement(sql);
							System.out.println(sql);
							prepStmt.executeUpdate();
							
							//closeConnection(this.conexion);
							
							//establecerConexion();
							
							//Actualiza el estado de la silla
							sql = "UPDATE SILLAS SET ESTADO='Desocupada' WHERE NUMEROSILLA="+numSilla+" AND NUMEROFILA="+numFila+" AND FUN_IDFUNC="+ idFuncion;
							prepStmt = conexion.prepareStatement(sql);
							prepStmt.execute();
							MensajeDevolucion mensaje = new MensajeDevolucion("Devolución exitosa.Dirijase a la oficina más cercana a recoger su dinero");
							mens.add(mensaje);
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



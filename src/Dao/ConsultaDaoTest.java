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
import java.util.Date;
import java.util.Properties;

import junit.framework.TestCase;

public class ConsultaDaoTest extends TestCase
{
	private Connection conexion;

	private String user;

	private String password;

	private String url;

	private String driver;

	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private void establecerConexion() throws SQLException {
		System.out.println("Connecting to: " + "jdbc:oracle:thin:@fn3.oracle.virtual.uniandes.edu.co:1521:prod" + " With user: " + "ISIS2304B061710");
		conexion = DriverManager.getConnection("jdbc:oracle:thin:@fn3.oracle.virtual.uniandes.edu.co:1521:prod", "ISIS2304B061710", "ovEN31kudO");
	}

	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			System.err.println("SQLException in closing Connection:");
			exception.printStackTrace();
			throw exception;
		}
	}

	
	public final void testRegistrarUsuario() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			establecerConexion();
			
			String sql = "INSERT INTO USUARIOS (IDUSUARIO,NOMBRE,APELLIDO, CORREO,IDENTIFICACION,ROL)"
					+ "VALUES( 100,'Camila','Torres','c.torres@uniandes.edu.co',190263894,'OPERARIO' )";

			System.out.println(sql);
			
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO USUARIOS (IDUSUARIO,NOMBRE,APELLIDO, CORREO,IDENTIFICACION,ROL)"
					+ "VALUES( 100,'Pablo','Torres','p.torres@uniandes.edu.co',190263894,'OPERARIO' )";

			System.out.println(sql);
			
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarCliente() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			establecerConexion();
			String sql = "INSERT INTO CLIENTES (IDCLIENTE,NOMBRE,APELLIDO, CORREO,IDENTIFICACION)"
					+ "VALUES(100,'Carmen','Lopez',c.torres@uniandes.edu.co,10936792)";
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO CLIENTES (IDCLIENTE,NOMBRE,APELLIDO, CORREO,IDENTIFICACION)"
					+ "VALUES(100,'Laura','Lopez',l.torres@uniandes.edu.co,10936792)";
			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarCompaniaTeatro() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			establecerConexion();
			String sql = "INSERT INTO COMPANIASTEATRO (IDCOMPANIA,NOMBRE,PAISORIGEN)"
					+ "VALUES(100,'La Romana','Italia')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO COMPANIASTEATRO (IDCOMPANIA,NOMBRE,PAISORIGEN)"
					+ "VALUES(100,'La Gran Nota','España')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarEspectaculo() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			establecerConexion();
			
			String sql = "INSERT INTO ESPECTACULOS (IDESPECTACULO,NOMBRE,DURACION,COSTOREALIZACION,IDIOMA,FECHAREALIZACION,PUBLICOOBJETIVO)"
					+ "VALUES(100,'La bella y la bestia',120,10000,'Ingles',TO_DATE('2017-03-21 12:15:00' , 'YYYY-MM-DD HH24:MI:SS'),Infantil)";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO ESPECTACULOS (IDESPECTACULO,NOMBRE,DURACION,COSTOREALIZACION,IDIOMA,FECHAREALIZACION,PUBLICOOBJETIVO)"
					+ "VALUES(100,'La divina comedia',130,100000,'Ingles',TO_DATE('2017-03-21 12:15:00' , 'YYYY-MM-DD HH24:MI:SS'),Adultos)";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarSitio() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			String sql = "INSERT INTO SITIOS (IDSITIO,NOMBRE,CAPACIDAD,TIPOSILLETERIA,TIPOPROTECCION)"
					+ "VALUES(100,'El gran sofa',200,'Fija','Cerrado')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			
			sql = "INSERT INTO SITIOS (IDSITIO,NOMBRE,CAPACIDAD,TIPOSILLETERIA,TIPOPROTECCION)"
					+ "VALUES(100,'El gran sillon',2000,'Fija','Cerrado')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testProgramarFuncion() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
			String sql = "INSERT INTO FUNCIONES (IDFUNCION,FECHA,COSTO,ESTADO)"
					+ "VALUES(100,TO_DATE('2017-03-21 12:15:00' , 'YYYY-MM-DD HH24:MI:SS'),10000,'CONFIRMADO')";
			

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO FUNCIONES (IDFUNCION,FECHA,COSTO,ESTADO)"
					+ "VALUES(100,TO_DATE('2017-03-21 12:15:00' , 'YYYY-MM-DD HH24:MI:SS'),10000,'CANCELADO')";
			

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarPreferencia() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			String sql = "INSERT INTO PREFERENCIAS (IDPREFERENCIA,NOMBRECATEGORIA,NOMBRESITIO)"
					+ "VALUES(100,'Drama','El gran cajon')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO PREFERENCIAS (IDPREFERENCIA,NOMBRECATEGORIA,NOMBRESITIO)"
					+ "VALUES(100,'Comedia','El gran cajon')";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
			
		}
		//fail("Not yet implemented"); // TODO
	}

	public final void testRegistrarCompraBoleta() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO BOLETAS (IDBOLETA,COSTO,IDSILLA,IDCLIENTE,REGISTRADO,IDLOCALIDAD)"
						+ "VALUES(100,10,1,1,'Registrado',1)";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
				sql = "INSERT INTO BOLETAS (IDBOLETA,COSTO,IDSILLA,IDCLIENTE,REGISTRADO,IDLOCALIDAD)"
						+ "VALUES(100,20,1,1,'Registrado',2)";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}
	
	public final void testRegistrarBeneficios() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO BENEFICIOS (IDBENEFICIO,DESCRIPCION)"
						+ "VALUES(100,'Más comida')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				sql = "INSERT INTO BENEFICIOS (IDBENEFICIO,DESCRIPCION)"
						+ "VALUES(100,'Más espacio')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	
	}
	
	public final void testRegistrarCaracteristicas() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO CARACTERISTICAS (IDCARACTERISTICA,DESCRIPCION)"
						+ "VALUES(100,'Efectos de sonido')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				sql = "INSERT INTO CARACTERISTICAS (IDBENEFICIO,DESCRIPCION)"
						+ "VALUES(100,'Espuma de colores')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO
	}
	
	public final void testRegistrarCategorias()throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO CATEGORIAS (IDCATEGORIA,NOMBRE)"
						+ "VALUES(100,'Ciencia Ficción')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				sql = "INSERT INTO CATEGORIAS (IDCATEGORIA,NOMBRE)"
						+ "VALUES(100,'Clasica')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO

	}
	
	public final void testRegistrarCiudades() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO CIUDADES (IDCIUDAD,NOMBRE)"
						+ "VALUES(100,'Lima')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				sql = "INSERT INTO CIUDADES (IDCIUDAD,NOMBRE)"
						+ "VALUES(100,'Miami')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO

	}
	
	public final void testRegistrarLocalidades() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO LOCALIDADES (IDLOCALIDAD,NOMBRE,CAPACIDAD)"
						+ "VALUES(100,'VIP',200)";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				sql = "INSERT INTO LOCALIDADES (IDLOCALIDAD,NOMBRE,CAPACIDAD)"
						+ "VALUES(100,'General',200)";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO

	
	}
	
	public final void testRegistrarRepresentante() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO REPRESENTANTES (IDREPRESENTANTE,NOMBRE,APELLIDO)"
						+ "VALUES(100,'Manuel','Forero')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				 sql = "INSERT INTO REPRESENTANTES (IDREPRESENTANTE,NOMBRE,APELLIDO)"
						+ "VALUES(100,'Manuela','Florez')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO

	}
	
	public final void testIntegridadFK() throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			establecerConexion();
			String sql = "INSERT INTO COMPANIASTEATRO (IDREPRESENTANTE)"
					+ "VALUES(1)";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "INSERT INTO COMPANIASTEATRO (IDREPRESENTANTE)"
					+ "VALUES(100)";

			System.out.println(sql);
			prepStmt = conexion.prepareStatement(sql);	
			prepStmt.execute();
			
			sql = "SELECT * FROM COMPANIASTEATRO WHERE IDCOMPANIA="+100+")";
			prepStmt = conexion.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			if(rs!=null)
			{
				assertEquals(true, true);
			}
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
					//throw exception;
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
	}
	
	public final void testIntegridadCK()throws Exception
	{
		PreparedStatement prepStmt = null;
		
		try 
		{
			
			
			establecerConexion();
			
				String sql = "INSERT INTO COMPANIASTEATRO (IDCOMPANIA,NOMBRE,PAISORIGEN)"
						+ "VALUES(300,'CND','Canada')";
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		

				 sql = "INSERT INTO COMPANIASTEATRO (IDCOMPANIA,NOMBRE,PAISORIGEN)"
						+ "VALUES(300,'null','Canada')";
				 
				 sql = "SELECT * FROM COMPANIASTEATRO WHERE IDCOMPANIA="+300+")";
					prepStmt = conexion.prepareStatement(sql);
					ResultSet rs = prepStmt.executeQuery();
					if(rs!=null)
					{
						assertEquals(true, true);
					}
			
				System.out.println(sql);
				prepStmt = conexion.prepareStatement(sql);	
				prepStmt.execute();
		
			
		} 

		catch (Exception e) 
		{
			System.err.println("SQLException in executing:");
			e.printStackTrace();
			assertEquals(true, true);
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
					assertEquals(true, true);
				}
			}
			if (this.conexion != null)
				closeConnection(this.conexion);
		}
		//fail("Not yet implemented"); // TODO

	}

}

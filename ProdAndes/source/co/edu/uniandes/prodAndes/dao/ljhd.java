package co.edu.uniandes.prodAndes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ljhd {

	public void inicializar(String path)
	{
		try
		{
			cadenaConexion = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
			usuario = "ISIS2304051510";
			clave = "dmariafifth";
			final String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
		
		/**
		 * conexion con la base de datos
		 */
		public static Connection conexion;

		/**
		 * nombre del usuario para conectarse a la base de datos.
		 */
		private static String usuario;

		/**
		 * clave de conexión a la base de datos.
		 */
		private static String clave;

		/**
		 * URL al cual se debe conectar para acceder a la base de datos.
		 */
		private static String cadenaConexion;

		/**
		 * constructor de la clase. No inicializa ningun atributo.
		 */
		public ljhd() 
		{		
			inicializar("");
		}
		
		public void hacerSelect(){
			String sql = "Select * from producto where codigo=1 for update";
			PreparedStatement state= null;
			try {
				establecerConexion(cadenaConexion, usuario, clave);
//				conexion.setAutoCommit(false);
				state = conexion.prepareStatement(sql);
				
				state.executeQuery();
				System.out.println("busco");
				state.close();
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void hacerUpdate(){
			String sql2 = "update producto set costo=1200 where codigo=1";
			PreparedStatement state1 = null;
			try {
				establecerConexion(cadenaConexion, usuario, clave);
				conexion.setAutoCommit(false);
				state1 = conexion.prepareStatement(sql2);;
								
				state1.executeUpdate();
				conexion.commit();
				System.out.println("update");
				state1.close();
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			ljhd cosa = new ljhd();
			cosa.hacerSelect();
			for (int i = 0; i < 10000000; i++) {
				for (int j = 0; j < 1000; j++) {
					for (int j2 = 0; j2 < 10; j2++) {
						
					}
				}
			}
			cosa.hacerUpdate();
		}
		
		/**
		 * Método que se encarga de crear la conexión con el Driver Manager
		 * a partir de los parametros recibidos.
		 * @param url direccion url de la base de datos a la cual se desea conectar
		 * @param usuario nombre del usuario que se va a conectar a la base de datos
		 * @param clave clave de acceso a la base de datos
		 * @throws SQLException si ocurre un error generando la conexión con la base de datos.
		 */
		public static void establecerConexion(String url, String usuario, String clave) throws SQLException
		{
			try
			{
				conexion = DriverManager.getConnection(url,usuario,clave);
			}
			catch( SQLException exception )
			{
				throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi—n." );
			}
		}

		/**
		 *Cierra la conexión activa a la base de datos. Además, con=null.
		 * @param con objeto de conexión a la base de datos
		 * @throws SistemaCinesException Si se presentan errores de conexión
		 */
		public void closeConnection(Connection connection) throws Exception {        
			try {
				connection.close();
				connection = null;
			} catch (SQLException exception) {
				throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
			}
		} 
}

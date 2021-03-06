/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 2.0 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 *
 * Ejercicio: ProdAndes
 * Autor: Jairo Bautissta y Nelson S�nchez - 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.prodAndes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import co.edu.uniandes.prodAndes.vos.Administrador;
import co.edu.uniandes.prodAndes.vos.Cliente;
import co.edu.uniandes.prodAndes.vos.EstacionProduccion;
import co.edu.uniandes.prodAndes.vos.EstadoPedidoValue;
import co.edu.uniandes.prodAndes.vos.EtapaProduccion;
import co.edu.uniandes.prodAndes.vos.Material;
import co.edu.uniandes.prodAndes.vos.Operario;
import co.edu.uniandes.prodAndes.vos.Pedido;
import co.edu.uniandes.prodAndes.vos.PedidoMaterial;
import co.edu.uniandes.prodAndes.vos.Producto;
import co.edu.uniandes.prodAndes.vos.Proveedor;

/**
 * Clase ConsultaDAO, encargada de hacer las consultas b�sicas para el cliente
 */
public class ConsultaDAO {

	//----------------------------------------------------
	// Constantes TODO
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexi�n.
	 */
//	private static final String ARCHIVO_CONEXION = "../WebContent/conexion.properties";

	private static final String CONSULTA_PRODUCTO = "Producto";

//	private static final String CONSULTA_MATERIAL = "Material";
//
//	private static final String CONSULTA_ETAPA_PROD = "Etapa Produccion";

	public static final String TIPO_MATERIAL_MATERIA_PRIMA = "Materia Prima";

	public static final String TIPO_MATERIAL_COMPONENTE = "Componente";

	public static final String PEDIDO_ESTADO_LISTO = "listo";

	public static final String PEDIDO_ESTADO_EN_PRODUCCION = "enProduccion";

	public static final String PEDIDO_ESTADO_EN_ESPERA = "enEspera";

	//----------------------------------------------------
	// Atributos TODO
	//----------------------------------------------------
	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;

	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuario;

	/**
	 * clave de conexi�n a la base de datos.
	 */
	private String clave;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;
	
	//-------------------------------------------------
	// Constructor TODO
	//-------------------------------------------------

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO() 
	{		
		inicializar("");
	}

	//-------------------------------------------------
	// M�todos TODO
	//-------------------------------------------------

	/**
	 * Obtiene ls datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
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
	 * M�todo que se encarga de crear la conexi�n con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexi�n con la base de datos.
	 */
	private void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		try
		{
			conexion = DriverManager.getConnection(url,usuario,clave);
		}
		catch( SQLException exception )
		{
			throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi�n." );
		}
	}

	/**
	 *Cierra la conexi�n activa a la base de datos. Adem�s, con=null.
	 * @param con objeto de conexi�n a la base de datos
	 * @throws SistemaCinesException Si se presentan errores de conexi�n
	 */
	public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexi�n.");
		}
	} 

	// ---------------------------------------------------
	// M�todos asociados a los casos de uso: Consulta
	// ---------------------------------------------------

	//FIXME
	/**
	 * 
	 * @param codigo
	 * @param etapa
	 * @throws Exception
	 */
	public void registrarEjecucionEtapaDeProduccion(int codigo, int etapa) throws Exception{
		PreparedStatement statement = null;		
		Savepoint sp = null;
		try {
			String selectQuery = "select for update cantidad from producto where estado="+etapa+"codigo="+codigo+" for update";
			establecerConexion(cadenaConexion, usuario, clave);
			sp = conexion.setSavepoint();
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);

			ResultSet rs = statement.executeQuery();
			int cantidad;
			rs.next();
			cantidad = rs.getInt("cantidad");
			String updateIncQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo+" estado="+etapa+1;
			statement.close();
			statement = conexion.prepareStatement(updateIncQuery);
			statement.executeUpdate();
			String updateDecQuery = "update producto set cantidad=0 where codigo="+codigo+" estado="+etapa;
			statement.close();
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();
			conexion.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conexion.rollback(sp);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
	}

	//TODO
	/**
	 * 
	 * @param cantidad
	 * @param codigo
	 * @throws Exception
	 */
	public void registrarLlegadaDeInsumos(double cantidad, int codigo) throws Exception{
		PreparedStatement statement = null;		
		Savepoint sp = null;
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			sp = conexion.setSavepoint();
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String updateDecQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo;
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();
			conexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conexion.releaseSavepoint(sp);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param tipo
	 * @param inventario
	 * @param etapa
	 * @param fechaEntrega
	 * @param fechaSolicitud
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	public ArrayList consultarExistenciaDe(String tipo, String inventario, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception{
		ArrayList resultado = new ArrayList();
		if(tipo.equals("Producto")){
			resultado = consultarExistenciasDeProducto(inventario, fechaEntrega, fechaSolicitud, ordenes, grupos);
		}
		else if(tipo.equals("Materia Prima")){
			resultado = consultarExistenciasDeMateriaPrima(tipo, inventario, ordenes, grupos);
		}
		else if(tipo.equals("Componente")){
			resultado = consultarExistenciasDeComponente(tipo, inventario, ordenes, grupos);
		}
		return resultado;
	}

	/**
	 * 
	 * @param tipo
	 * @param inventario
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Material> consultarExistenciasDeMateriaPrima(String tipo, String inventario, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement= null;

		ArrayList<Material> materiales = new ArrayList<Material>();

		String selectingQuery = "Select cantidad, nombre, unidad from Materiales where tipo='"+tipo+"' ";
		if(inventario != null){selectingQuery += " AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		Iterator<String> iteraGrupos = grupos.iterator();
		String agrupamiento = "";
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += " group by "+agrupamiento+" ";
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				ordenamiento += orden + ",";
			}
			else{
				ordenamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += " order by "+ordenamiento+" ";
		}
		try {

			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectingQuery);
			statement.setQueryTimeout(5);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Material mate = new Material();
				String nombre = rs.getString("nombre");
				double cantidad = rs.getDouble("cantidad");
				String unidad = rs.getString("unidad");


				mate.setNombre(nombre);
				mate.setCantidad(cantidad);
				mate.setUnidad(unidad);
				materiales.add(mate);

			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}		
		return materiales;
	}

	/**
	 * 
	 * @param tipo
	 * @param inventario
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Material> consultarExistenciasDeComponente(String tipo, String inventario, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement= null;

		ArrayList<Material> materiales = new ArrayList<Material>();

		String selectingQuery = "Select cantidad, nombre, unidad from Materiales where tipo='"+tipo+"' ";
		Iterator<String> iteraGrupos = grupos.iterator();
		String agrupamiento = "";

		if(inventario != null){selectingQuery += " AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += " group by "+agrupamiento+" ";
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				ordenamiento += orden + ",";
			}
			else{
				ordenamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += " order by "+ordenamiento+" ";
		}

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectingQuery);
			statement.setQueryTimeout(5);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Material mate = new Material();
				String nombre = rs.getString("nombre");
				double cantidad = rs.getDouble("cantidad");


				mate.setNombre(nombre);
				mate.setCantidad(cantidad);
				materiales.add(mate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}		
		return materiales;
	}

	/**
	 * 
	 * @param inventario
	 * @param etapa
	 * @param fechaEntrega
	 * @param fechaSolicitud
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Producto> consultarExistenciasDeProducto(String inventario, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String selectingQuery = "Select cantidad, nombre from PRODUCTO where";
		Iterator<String> iteraGrupos = grupos.iterator();
		String agrupamiento = "";
		if(inventario != null){selectingQuery += " AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		if(fechaEntrega != null){selectingQuery += " AND fechaEntrega='"+fechaEntrega+"' ";}
		if(fechaSolicitud != null){selectingQuery += " AND fechaSolicitud='"+fechaSolicitud+"' ";}
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += "group by "+agrupamiento+" ";
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				ordenamiento += orden + ",";
			}
			else{
				ordenamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += "order by "+ordenamiento+" ";
		}

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectingQuery);
			statement.setQueryTimeout(5);

			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Producto produ = new Producto();
				String nombre = rs.getString("nombre");
				int cantidad = rs.getInt("cantidad");


				produ.setNombre(nombre);
				produ.setCantidadDisponible(cantidad);
				productos.add(produ);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
		return productos;
	}



	/**
	 * 
	 * @param pedido
	 * @throws Exception
	 */
	public void registrarEntregaPedido(String pedido) throws Exception{
		PreparedStatement statement = null;		
		Savepoint sp = null;
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			String selectQuery = "select codigoProducto from PEDIDO where codigo="+pedido;
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			double producto = 0;
			if(rs.next()){
				producto = rs.getDouble("codigoProducto");
			}
			statement.close();
			String updateQueryPedi = "update pedido set estado='Finalizado' where estado='Listo' and codigo="+pedido;
			statement = conexion.prepareStatement(updateQueryPedi);
			statement.executeUpdate();
			statement.close();
			String updateQueryProd = "update producto set cantidad=0 where estado=0 and codigoProducto="+producto;
			statement = conexion.prepareStatement(updateQueryProd);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			conexion.rollback(sp);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
	}

	//------------------------------------------------
	// Metodos para fachada
	//------------------------------------------------

	/**
	 * 
	 * @param usuario
	 * @param correo
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public Object inicioSesion(String usuarion, String correo, String pass) throws Exception{
		Object usuarioIniciado = null;
		PreparedStatement statement = null;
		String selectQueryUsuario = "select * from USUARIO";

		if(!usuarion.isEmpty()){ selectQueryUsuario+=" where login='"+usuarion+"'";}
		else if(correo!=null){ selectQueryUsuario+=" where direccionElectronica='"+correo+"'";}
		selectQueryUsuario+="";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQueryUsuario);
			statement.setQueryTimeout(5);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				String clave = rs.getString("clave");
				if(!clave.equals(pass)) return null;
				String ciudad = rs.getString("ciudad");
				String codigoPostal = rs.getString("codigoPostal");
				int documentoId = rs.getInt("documentoId");
				String nacionalidad = rs.getString("nacionalidad");
				String telefono = rs.getNString("telefono");
				int tipoDocumento = rs.getInt("tipoDocumento");

				String selectQueryAdmin = "select * from administrador";
				if(!usuarion.isEmpty()){ selectQueryAdmin+=" where login='"+usuarion+"'";}
				else if(!correo.isEmpty()){ selectQueryAdmin+=" where direccionElectronica='"+correo+"'";}
				statement.close();
				statement = conexion.prepareStatement(selectQueryAdmin);
				statement.setQueryTimeout(5);
				ResultSet rsAdmin = statement.executeQuery();

				if(rsAdmin.next()){
					String nombre = rsAdmin.getString("nombre");
					int codigo = rsAdmin.getInt("codigo");
					String loginAdmin = rsAdmin.getString("login");
					String direccionElectronicaAdmin = rsAdmin.getString("direccionElectronica");

					Administrador admin = new Administrador();
					admin.setNacionalidad(nacionalidad);
					admin.setCiudad(ciudad);
					admin.setCodigoPostal(codigoPostal);
					admin.setDireccionElectronica(direccionElectronicaAdmin);
					admin.setDocumentotold(documentoId);
					admin.setLogin(loginAdmin);
					admin.setTelefono(telefono);
					admin.setTipoDocumento(tipoDocumento);
					admin.setNombre(nombre);
					admin.setCodigo(codigo);
					usuarioIniciado = admin;
				}
				else{
					String selectQueryCliente = "select * from cliente";
					if(!usuarion.isEmpty()){ selectQueryCliente+=" where login='"+usuarion+"'";}
					else if(!correo.isEmpty()){ selectQueryCliente+=" where direccionElectronica='"+correo+"'";}
					statement = conexion.prepareStatement(selectQueryCliente);
					statement.setQueryTimeout(5);
					ResultSet rsCliente = statement.executeQuery();

					if(rsCliente.next()){
						int codigo = rsCliente.getInt("codigo");
						String nombreLegal = rsCliente.getString("nombreLegal");
						int idLegal = rsCliente.getInt("idLegal");
						int tipoIDLegal = rsCliente.getInt("tipoIDLegal");
						String registroSINV = rsCliente.getString("registroSINV");
						String logincliente = rsCliente.getString("login");
						String direccionElectronicaAdmin = rsCliente.getString("direccionElectronica");

						Cliente cliente = new Cliente();
						cliente.setNacionalidad(nacionalidad);
						cliente.setCiudad(ciudad);
						cliente.setCodigoPostal(codigoPostal);
						cliente.setDireccionElectronica(direccionElectronicaAdmin);
						cliente.setDocumentotold(documentoId);
						cliente.setLogin(logincliente);
						cliente.setTelefono(telefono);
						cliente.setTipoDocumento(tipoDocumento);
						cliente.setCodigo(codigo);
						cliente.setNombreLegal(nombreLegal);
						cliente.setIdLegal(idLegal);
						cliente.setTipoIdLegal(tipoIDLegal);
						cliente.setResgistroSINV(registroSINV);
						usuarioIniciado = cliente;
					}
					else{
						String selectQueryProveedor = "select * from proveedor";
						if(!usuarion.isEmpty()){ selectQueryProveedor+=" where login='"+usuarion+"'";}
						else if(!correo.isEmpty()){ selectQueryProveedor+=" where direccionElectronica='"+correo+"'";}
						statement = conexion.prepareStatement(selectQueryProveedor);
						statement.setQueryTimeout(5);
						ResultSet rsProvee = statement.executeQuery();

						if(rsProvee.next()){
							int codigo = rsProvee.getInt("codigo");
							int tipoIdLegal = rsProvee.getInt("tipoIdLegal");
							int identificacionLegal = rsProvee.getInt("identificacionLegal");
							String nombreLegal = rsProvee.getString("nombreLegal");
							String loginProv = rsProvee.getString("login");
							String direccionElectronicaProvee = rsProvee.getString("direccionElectronica");

							Proveedor prov = new Proveedor();
							prov.setCiudad(ciudad);
							prov.setCodigo(codigo);
							prov.setCodigoPostal(codigoPostal);
							prov.setDireccionElectronica(direccionElectronicaProvee);
							prov.setDocumentotold(documentoId);
							prov.setIdentificacion(identificacionLegal);
							prov.setLogin(loginProv);
							prov.setNacionalidad(nacionalidad);
							prov.setNombreLegal(nombreLegal);
							prov.setTelefono(telefono);
							prov.setTipoDocumento(tipoDocumento);
							prov.setTipoIdLegal(tipoIdLegal);

							usuarioIniciado = prov;

						}
						else{
							String selectQueryOperario = "select * from operario";
							if(!usuarion.isEmpty()){ selectQueryOperario+=" where login='"+usuarion+"'";}
							else if(!correo.isEmpty()){ selectQueryOperario+=" where direccionElectronica='"+correo+"'";}
							statement = conexion.prepareStatement(selectQueryOperario);
							statement.setQueryTimeout(5);
							ResultSet rsOp = statement.executeQuery();

							if(rsOp.next()){
								int codigo = rsOp.getInt("codigo");
								String nombre = rsOp.getString("nombre");
								String cargo = rsOp.getString("cargo");
								String loginop = rsOp.getString("login");
								String direccionElectronicaop = rsOp.getString("direccionElectronica");

								Operario op = new Operario();
								op.setCiudad(ciudad);
								op.setCodigo(codigo);
								op.setCodigoPostal(codigoPostal);
								op.setDireccionElectronica(direccionElectronicaop);
								op.setDocumentotold(documentoId);
								op.setLogin(loginop);
								op.setNacionalidad(nacionalidad);
								op.setTelefono(telefono);
								op.setTipoDocumento(tipoDocumento);
								op.setCargo(cargo);
								op.setNombre(nombre);

								usuarioIniciado = op;

							}
						}
					}
				}
			}
			else{
				return usuarioIniciado;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(selectQueryUsuario);
			throw new Exception ("No pudo encontrar al usuario");
		}
		finally{
			if(statement != null){
				try{
					statement.close();
				}catch(SQLException e)
				{
					throw new Exception ("No pudo cerrar la conexi�n");
				}

			}
			closeConnection(conexion);
		}
		return usuarioIniciado;
	}

	/**
	 * 
	 * @param idProceso
	 * @param loginCLiente
	 * @param cantidad
	 * @param fechaEspera
	 * @return
	 * @throws Exception
	 */
	public boolean registrarPedidoProducto(Long idProducto, String loginCLiente, int cantidad, Date fechaEspera) throws Exception
	{

		boolean fallo = false; 


		PreparedStatement pSRequeridosNum = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			//Rectifica si hay cantidad suficiente


			PreparedStatement  prcantidadDisponible = conexion.prepareStatement("SELECT cantidad from Producto where Producto.codigo="+idProducto+" for update");
			prcantidadDisponible.setQueryTimeout(5);
			ResultSet rscantidadDisponible = prcantidadDisponible.executeQuery();
			prcantidadDisponible.close();

			int cantidadDisponible=0;
			if(rscantidadDisponible.next())
				cantidadDisponible = rscantidadDisponible.getInt("cantidad");

			if(cantidadDisponible>=cantidad)
			{
				//actualiza cantidad disponeble

				int nuevo = cantidadDisponible-cantidad;			
				PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+nuevo+" where Proceso.codigoProducto="+idProducto);
				//				psaactualizarDisponibles1.setQueryTimeout(5);
				psaactualizarDisponibles1.executeUpdate();
				psaactualizarDisponibles1.close();


				//Codigo del admin y crea pedido
				pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador for update");
				pSRequeridosNum.setQueryTimeout(5);
				ResultSet admin = pSRequeridosNum.executeQuery();
				int adminID =0;
				if(admin.next())
					adminID=admin.getInt("codigo");

				pSRequeridosNum =conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
						+ "values (incremento_id_Pedido.NextVal,'"+PEDIDO_ESTADO_LISTO+"',"+cantidad+", NOW(),"+fechaEspera+","+idProducto+","+adminID+",'"+loginCLiente+"' )");
				pSRequeridosNum.executeUpdate();
				conexion.commit();

			}
			else
			{

				//establece si se puede reservar o no

				//Primero obtiene la cantidad de material que requiere un producto


				pSRequeridosNum =conexion.prepareStatement("Create View consulta as (SELECT * FROM PROCESO, ETAPA, ETAPAPRODUCCION, ESTACIONPRODUCCION, REQUIERE "
						+ "where Proceso.codigoProducto="+idProducto+" and etapa.codigoProceso=proceso.codigo and etapa.codigoEtapa=etapaProduccion.codigo "
						+ " and etapaProduccion.codigo=estacionProduccion.codigoEtapa and requiere.codigoEstacion=estacionProduccion.codigo) ");
				pSRequeridosNum.executeUpdate();

				pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from consulta");



				ResultSet rsRequeridos = pSRequeridosNum.executeQuery();

				int cantidadRequerido = 0;

				if(rsRequeridos.next())
					cantidadRequerido=rsRequeridos.getInt("cuenta");

				pSRequeridosNum = conexion.prepareStatement("Create View matDisp as (SELECT * FROM consulta INNER JOIN Materiales mat ON consulta.codigoMaterial= mat.codigo where consulta.cantidad*"+(cantidad-cantidadDisponible)+" <= mat.cantidad )");
				pSRequeridosNum.executeUpdate();

				pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from matDisp");
				pSRequeridosNum.executeUpdate();

				ResultSet rsDisponibleMat = pSRequeridosNum.executeQuery();

				int cantidadDisponibleMat = 0;

				if(rsDisponibleMat.next())
					cantidadDisponibleMat=rsDisponibleMat.getInt("cuenta");

				if(cantidadDisponibleMat==cantidadRequerido)
				{
					//Actualiza los productos si se puede fabricar

					PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+0+" where Producto.Codigo="+idProducto);

					psaactualizarDisponibles1.executeUpdate();

					psaactualizarDisponibles1.close();


					pSRequeridosNum.close();
					pSRequeridosNum = conexion.prepareStatement("select * from matDisp");
					ResultSet materialesReservar = pSRequeridosNum.executeQuery();

					PreparedStatement actualizar=null;

					while(materialesReservar.next()){

						String cod = materialesReservar.getString("mat.codigo");
						int resta = materialesReservar.getInt("consulta.cantidad")*cantidad;

						actualizar=conexion.prepareStatement("update Material set cantidad=cantidad-"+resta+" where codigo="+cod);	
						actualizar.executeUpdate();
					}

					if(actualizar!=null)
						actualizar.close();
					//Codigo del admin y crea pedido
					pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
					pSRequeridosNum.setQueryTimeout(5);
					ResultSet admin = pSRequeridosNum.executeQuery();
					int adminID =0;
					if(admin.next())
						adminID=admin.getInt("codigo");

					pSRequeridosNum = conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
							+ "values (incremento_id_Pedido.NextVal,'"+PEDIDO_ESTADO_EN_PRODUCCION+"',"+cantidad+", NOW(),"+fechaEspera+","+idProducto+","+adminID+",'"+loginCLiente+"' )");
					pSRequeridosNum.executeUpdate();


					conexion.commit();
				}
				else
				{
					//Deja el pedido en pendiente
					//Codigo del admin y crea pedido
					pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
					ResultSet admin = pSRequeridosNum.executeQuery();
					int adminID =0;
					if(admin.next())
						adminID=admin.getInt("codigo");

					pSRequeridosNum = conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
							+ "values (incremento_id_Pedido.NextVal,'"+PEDIDO_ESTADO_EN_ESPERA+"',"+cantidad+", NOW(),"+fechaEspera+","+idProducto+","+adminID+",'"+loginCLiente+"' )");
					pSRequeridosNum.executeUpdate();


				}

			}



		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			fallo = true;
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");

		}finally 
		{

			if (pSRequeridosNum != null) 
			{
				try {
					pSRequeridosNum.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
		return fallo?false:true;
	}

	/**
	 * 
	 * @param idMaterial
	 * @param tipoPedido
	 * @param tipo
	 * @param volumen
	 * @param fechas
	 * @param costo
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	public Object[] consultarMaterial(Long idMaterial, String tipoPedido, String tipo, Integer[] volumen, Date[] fechas, Double[] costo, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
	{
		if(tipoPedido.equals(CONSULTA_PRODUCTO))
			return consultarMaterialProducto(idMaterial, fechas, costo, ordenes, grupos);
		else
			return consultarMaterialMaterial(idMaterial, tipoPedido, tipo, volumen, ordenes, grupos);

	}

	/**
	 * 
	 * @param idProducto
	 * @param fechas
	 * @param costo
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	private Object[] consultarMaterialProducto(Long idProducto, Date[] fechas, Double[] costo, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
	{
		PreparedStatement prepStmt = null;
		Producto producto = new Producto();
		ArrayList<EtapaProduccion> etapasProduc = new ArrayList<EtapaProduccion>();
		ArrayList<Material> materiales = new ArrayList<Material>();
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			establecerConexion(cadenaConexion, usuario, clave);


			String sentencia ="SELECT * from Producto where Producto.codigo="+idProducto;

			if(costo!=null)
				sentencia = sentencia + " and costo between "+costo[0]+" and "+costo[1]+" ";

			Iterator<String> iteraGrupos = grupos.iterator();
			String agrupamiento = "";
			while(iteraGrupos.hasNext()){
				String grupo = iteraGrupos.next();
				if (iteraGrupos.hasNext()) {
					agrupamiento += grupo + ", ";
				}
				else{
					agrupamiento += grupo;
				}
			}
			if(!agrupamiento.isEmpty()){
				sentencia += " group by "+agrupamiento+" ";
			}
			Iterator<String> iteraOrdenes= ordenes.iterator();
			String ordenamiento = "";
			while(iteraOrdenes.hasNext()){
				String orden = iteraOrdenes.next();
				if (iteraOrdenes.hasNext()) {
					ordenamiento += orden + ",";
				}
				else{
					ordenamiento += orden;
				}
			}
			if(!ordenamiento.isEmpty()){
				sentencia += " order by "+ordenamiento+" ";
			}


			prepStmt = conexion.prepareStatement(sentencia);

			ResultSet rsProducto = prepStmt.executeQuery();

			prepStmt = conexion.prepareStatement("select sum(cantidadproducto) as cantidadEnProduccion form etapaProduccion where codigoProducto="+idProducto);

			ResultSet rsProducto1 = prepStmt.executeQuery();


			while(rsProducto.next())
			{
				if(rsProducto1.next())
					producto = new Producto(rsProducto.getLong("codigo"), rsProducto.getString("nombre"), rsProducto.getInt("cantidad"), rsProducto1.getInt("cantidadEnProduccion"), rsProducto.getString("descripcion"), rsProducto.getDouble("costo"), rsProducto.getInt("numEtapas"));
				else
					producto = new Producto(rsProducto.getLong("codigo"), rsProducto.getString("nombre"), rsProducto.getInt("cantidad"), 0, rsProducto.getString("descripcion"), rsProducto.getDouble("costo"), rsProducto.getInt("numEtapas"));


			}

			prepStmt = conexion.prepareStatement("SELECT * from Proceso, etapa, etapaProduccion etaProd "
					+ "where Proceso.codigoProducto="+idProducto+" and etapa.codigoProceso=proceso.codigo and etapa.codigoEtapa=etaProd.codigo");

			ResultSet rsEtapaProd = prepStmt.executeQuery();

			while(rsEtapaProd.next())
			{
				etapasProduc.add(new EtapaProduccion(rsEtapaProd.getLong("etaProd.codigo"), rsEtapaProd.getInt("etaProd.etapa"), rsEtapaProd.getString("etaProd.nombre"), rsEtapaProd.getDate("etaProd.fechaInicio"), rsEtapaProd.getDate("etaProd.fechaFin"), rsEtapaProd.getLong("etaProd.tiempoEjecucion"), rsEtapaProd.getString("etaProd.descripcion")));

				prepStmt = conexion.prepareStatement("SELECT * FROM (ETAPAPRODUCCION, ESTACIONPRODUCCION, REQUIERE "
						+ "where "+rsEtapaProd.getLong("etaProd.codigo")+"=etapaProduccion.codigo "
						+ " and etapaProduccion.codigo=estacionProduccion.codigoEtapa and requiere.codigoEstacion=estacionProduccion.codigo) consulta"
						+ "INNER JOIN Materiales mat ON consulta.codigoMaterial= mat.codigo");

				ResultSet rsMateriales = prepStmt.executeQuery();

				while(rsMateriales.next())
				{
					materiales.add(new Material(rsMateriales.getDouble("cantidad"), rsMateriales.getLong("codigo"), rsMateriales.getString("tipo"), rsMateriales.getString("unidad"), rsMateriales.getString("nombre"), rsMateriales.getDate("ultimoAbastecimiento")));


				}


			}


			prepStmt = conexion.prepareStatement("SELECT * FROM PEDIDO where PEDIDO.codigoProducto="+idProducto);

			ResultSet rsPedido = prepStmt.executeQuery();

			while(rsPedido.next())
			{
				pedidos.add(new Pedido(rsPedido.getLong("codigo"), rsPedido.getInt("estado"), rsPedido.getLong("number"), rsPedido.getDate("fechaPedido"), rsPedido.getDate("fechaEsperada"), rsPedido.getDate("fechaEntrega")));


			}



		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}	
		return new Object[]{producto,etapasProduc,materiales,pedidos};
	}

	/**
	 * 
	 * @param idMaterial
	 * @param tipoPedido
	 * @param tipo
	 * @param volumen
	 * @param ordenes
	 * @param grupos
	 * @return
	 * @throws Exception
	 */
	private Object[] consultarMaterialMaterial(Long idMaterial, String tipoPedido, String tipo, Integer[] volumen, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
	{
		PreparedStatement prepStmt = null;
		Material material = new Material();
		ArrayList<EtapaProduccion> etapasProduc = new ArrayList<EtapaProduccion>();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ArrayList<PedidoMaterial> pedidosMaterial = new ArrayList<PedidoMaterial>();

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String sentencia = "SELECT * from Material where codigo="+idMaterial+"";
			if(tipo!=null)
				sentencia  = sentencia+" tipo="+tipo;
			if(volumen!=null)
				sentencia = sentencia + " and cantidad between "+volumen[0]+" and "+volumen[1];

			Iterator<String> iteraGrupos = grupos.iterator();
			String agrupamiento = "";
			while(iteraGrupos.hasNext()){
				String grupo = iteraGrupos.next();
				if (iteraGrupos.hasNext()) {
					agrupamiento += grupo + ", ";
				}
				else{
					agrupamiento += grupo;
				}
			}
			if(!agrupamiento.isEmpty()){
				sentencia += "group by "+agrupamiento;
			}
			Iterator<String> iteraOrdenes= ordenes.iterator();
			String ordenamiento = "";
			while(iteraOrdenes.hasNext()){
				String orden = iteraOrdenes.next();
				if (iteraOrdenes.hasNext()) {
					ordenamiento += orden + ",";
				}
				else{
					ordenamiento += orden;
				}
			}
			if(!ordenamiento.isEmpty()){
				sentencia += "order by "+ordenamiento;
			}



			prepStmt = conexion.prepareStatement(sentencia);

			ResultSet rsMaterial = prepStmt.executeQuery();


			while(rsMaterial.next())
			{
				material = new Material(rsMaterial.getDouble("cantidad"), rsMaterial.getLong("codigo"), rsMaterial.getString("tipo"), rsMaterial.getString("unidad"), rsMaterial.getString("nombre"), rsMaterial.getDate("ultimoAbastecimiento"));


			}


			prepStmt = conexion.prepareStatement("SELECT * FROM (Materiales, ESTACIONPRODUCCION, REQUIERE "
					+ "where "+idMaterial+"=Materiales.codigo "
					+ " and requiere.codigoMaterial= Materiales.codigo and requiere.codigoEstacion=estacionProduccion.codigo) consulta"
					+ "INNER JOIN ETAPAPRODUCCION etaProd ON consulta.codigoEtapa= etapaProd.codigo");



			ResultSet rsEtapaProd = prepStmt.executeQuery();

			while(rsEtapaProd.next())
			{
				etapasProduc.add(new EtapaProduccion(rsEtapaProd.getLong("etaProd.codigo"), rsEtapaProd.getInt("etaProd.etapa"), rsEtapaProd.getString("etaProd.nombre"), rsEtapaProd.getDate("etaProd.fechaInicio"), rsEtapaProd.getDate("etaProd.fechaFin"), rsEtapaProd.getLong("etaProd.tiempoEjecucion"), rsEtapaProd.getString("etaProd.descripcion")));

				prepStmt = conexion.prepareStatement("SELECT * etapaProduccion ep inner join producto pr "
						+ "on pr.codigo=ep.codigoProducto and pr.codigo="+rsEtapaProd.getLong("etaProd.codigo"));


				ResultSet rsProductos = prepStmt.executeQuery();

				while(rsProductos.next())
				{
					productos.add(new Producto(rsProductos.getLong("pr.codigo"), rsProductos.getString("pr.nombre"), rsProductos.getInt("pr.cantidad"), 0, rsProductos.getString("pr.descripcion"), rsProductos.getDouble("pr.costo"), rsProductos.getInt("pr.numEtapas")));


				}


			}


			prepStmt = conexion.prepareStatement("SELECT * FROM PEDIDOMATERIAL where PEDIDOMATERIAL.codioMaterial="+idMaterial);

			ResultSet rsPedido = prepStmt.executeQuery();

			while(rsPedido.next())
			{
				pedidosMaterial.add(new PedidoMaterial(rsPedido.getLong("codigo"), rsPedido.getInt("cantidadPedida"), rsPedido.getDate("fechaPedido"), rsPedido.getDate("fechaEsperada"), rsPedido.getDouble("costo")));


			}



		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}	
		return new Object[]{material,etapasProduc,productos,pedidosMaterial};
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EtapaProduccion> seleccionarTodosLasEtapa() throws Exception{
		PreparedStatement prepStmt = null;
		ArrayList<EtapaProduccion> rta = new ArrayList<EtapaProduccion>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			String selectQuery = "select * from etapaproduccion";
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			prepStmt = conexion.prepareStatement(selectQuery);
			prepStmt.setQueryTimeout(5);
			ResultSet rs = prepStmt.executeQuery();


			while(rs.next()){
				String nombre = rs.getString("nombre");
				int codigo = rs.getInt("codigoEtapa");
				EtapaProduccion ep = new EtapaProduccion();
				ep.setNombre(nombre);
				ep.setCodigo(codigo);
				rta.add(ep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally {
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}	

		return rta;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Producto> darTodosProductosCodigoNombre() throws Exception
	{
		PreparedStatement prepStmt = null;
		ArrayList<Producto> resp = new ArrayList<Producto>();

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String sentencia = "SELECT codigo, nombre from Producto";
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			prepStmt = conexion.prepareStatement(sentencia);
			//			prepStmt.setQueryTimeout(5);

			ResultSet rsProducto = prepStmt.executeQuery();


			while(rsProducto.next())
			{
				Producto producto = new Producto();
				producto.setCodigo(rsProducto.getLong("codigo"));
				producto.setNombre(rsProducto.getString("nombre"));

				resp.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
		return resp;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Material> darTodosMaterialesCodigoNombreTipo() throws Exception
	{
		PreparedStatement prepStmt = null;
		ArrayList<Material> resp = new ArrayList<Material>();

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String sentencia = "SELECT codigo, nombre, tipo from Material order by tipo";

			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			prepStmt = conexion.prepareStatement(sentencia);
			//			prepStmt.setQueryTimeout(5);

			ResultSet rsMaterial = prepStmt.executeQuery();


			while(rsMaterial.next())
			{
				Material material = new Material();
				material.setCodigo(rsMaterial.getLong("codigo"));
				material.setNombre(rsMaterial.getString("nombre"));
				material.setTipo(rsMaterial.getString("tipo"));

				resp.add(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("metodo1");
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
				}
			}
			closeConnection(conexion);
		}
		return resp;
	}

	/**
	 * 
	 * @param login
	 * @param direccionElectronica
	 * @param pass
	 * @param idcli
	 * @param selTipoId
	 * @param ciudad
	 * @param nacionalidad
	 * @param departamento
	 * @param direccionFisica
	 * @param telefno
	 * @param codPostal
	 * @throws Exception
	 */
	public void registrarUsuario(String login, String direccionElectronica,	String pass, String idcli, String selTipoId, String ciudad, String nacionalidad, String departamento, String direccionFisica, String telefno, String codPostal) throws Exception {
		PreparedStatement stament = null;
		String insertQuery = "insert into usuario ('login','direccionelectronica','clave','documentoid','tipodocumento','nacionalidad','ciudad','departamento','direccionfisica','codigopostal','telefono') values ('"+login+"','"+direccionElectronica+"','"+pass+"','"+idcli+"','"+selTipoId+"','"+nacionalidad+"','"+ciudad+"','"+departamento+"','"+direccionFisica+"','"+codPostal+"','"+telefno+"')";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
			conexion.commit();
		}catch(SQLException e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception("Este usuario ya existe, o alguno de sus valores es invalido.");
		}finally{
			if (stament != null){
				try {
					stament.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param login
	 * @param direccionElectronica
	 * @param id
	 * @param nombrelegal
	 * @param sinv
	 * @param tipoIdLegal
	 * @throws Exception
	 */
	public void registrarCliente(String login, String direccionElectronica, String id, String nombrelegal, String sinv, String tipoIdLegal) throws Exception {
		PreparedStatement stament = null;
		String insertQuery = "insert into cliente ('login','direccionelectronica','nombrelegal','idlegal', 'tipoidlegal','registrosinv','codigo') values ('"+login+"','"+direccionElectronica+"','"+nombrelegal+"','"+id+"','"+tipoIdLegal+"','"+sinv+"')";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
			conexion.commit();
		}catch(SQLException e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception("Este usuario ya existe, o alguno de sus valores es invalido.");
		}finally{
			if (stament != null){
				try {
					stament.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param login
	 * @param direccionElectronica
	 * @param cargo
	 * @param nombre
	 * @throws Exception
	 */
	public void registrarOperario(String login, String direccionElectronica, String cargo, String nombre) throws Exception {
		PreparedStatement stament = null;
		String insertQuery = "insert into operario ('login','direccionElectronica','nombre','cargo', codigo) values ('"+login+"','"+direccionElectronica+"','"+nombre+"','"+cargo+"',incremento_id_operario.NextVal) ";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
			conexion.commit();
		}catch(SQLException e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception("Este usuario ya existe, o alguno de sus valores es invalido.");
		}finally{
			if (stament != null){
				try {
					stament.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param login
	 * @param direccionElectronica
	 * @param nombrelegal
	 * @param id
	 * @param tipoIdLegal
	 * @return
	 * @throws Exception
	 */
	public int registrarProveedor(String login, String direccionElectronica, String nombrelegal, String id, String tipoIdLegal) throws Exception {
		PreparedStatement stament = null;
		int codigo = -1;
		String insertQuery = "insert into proveedor ('login','direccionElectronica','nombrelegal', identificacionlegal, tipoidlegal,codigo) values ('"+login+"','"+direccionElectronica+"','"+nombrelegal+"',"+id+", "+tipoIdLegal+",incremento_id_proveedor.NextVal) ";
		ArrayList<String> select = new ArrayList<String>();
		select.add("codigo");
		ArrayList<String> where = new ArrayList<String>();
		where.add("login='"+login+"'");
		String selectQuery = generateQuery(select, "proveedor", where, new ArrayList<String>(), new ArrayList<String>());
		try{
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			establecerConexion(cadenaConexion, usuario, clave);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
			conexion.commit();

			stament.close();
			stament = conexion.prepareStatement(selectQuery);
			ResultSet rs = stament.executeQuery();
			if(rs.next()){
				codigo = rs.getInt("codigo");
			}
			return codigo;
		}catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Este usuario ya existe, o alguno de sus valores es invalido.");
		}finally{
			if (stament != null){
				try {
					stament.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param codigoProv
	 * @param selMate
	 * @param cantidad
	 * @param tiempo
	 * @throws Exception
	 */
	public void registrarMaterialProvisto(int codigoProv, String selMate, String cantidad, String tiempo) throws Exception {
		PreparedStatement statement = null;
		String insertQuery = "insert into suministro (maximacantidad,tiempoentrega,codigoproveedor,codigomaterial) values ("+cantidad+","+tiempo+")";
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(insertQuery);
			statement.executeUpdate();
			conexion.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception ("Este material ya existe");
		}
		finally{
			if (statement != null){
				try {
					statement.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
	}

	/**
	 * 
	 * @param nombre
	 * @param unidad
	 * @param cantidad
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public int registrarMaterial(String nombre, String unidad, String cantidad, String tipo) throws Exception {
		int codigo = 0;
		PreparedStatement statement = null;
		String insertQuery = "insert into material (codigo,nombre,unidad,tipo,cantidad,ultimoabastecimiento) values (incremento_id_material.NextVal,'"+nombre+"','"+unidad+"','"+tipo+"',"+cantidad+", now())";
		String selectQuery = "select codigo from material where nombre='"+nombre+"'";
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(insertQuery);
			statement.executeUpdate();
			statement.close();
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			if(rs.next());
			codigo = rs.getInt("codigo");
			conexion.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception ("Este material ya existe");
		}
		finally{
			if (statement != null){
				try {
					statement.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return codigo;
	}

	/**
	 * 
	 * @param nombre
	 * @param etapa
	 * @param cantidad
	 * @param tiempo
	 * @return
	 * @throws Exception
	 */
	public int registrarEstacion(String nombre, String etapa, String cantidad, String tiempo) throws Exception {
		int codigo = -1;
		PreparedStatement statement = null;
		String insertQuery = "insert into estacionproduccion (codigo,nombre,cantidad,tiempo,codigoetapa) values (incremento_id_esproduccion.NextVal,'"+nombre+"',"+cantidad+","+tiempo+", "+etapa+")";
		String selectQuery = "select codigo from estacionproduccion where nombre='"+nombre+"'";
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(insertQuery);
			statement.executeUpdate();
			statement.close();
			conexion.commit();
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				codigo = rs.getInt("codigo");}
		}
		catch(Exception e){
			e.printStackTrace();
			conexion.rollback();
			throw new Exception ("Este material ya existe");
		}
		finally{
			if (statement != null){
				try {
					statement.close();
				} catch (Exception e2) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return codigo;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EtapaProduccion> consultarTodasLasEtapas() throws Exception {
		PreparedStatement statement = null;
		ArrayList<EtapaProduccion> respuesta = new ArrayList<EtapaProduccion>();
		String selectQuery = "select codigo, nombre from etapaproduccion";
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				EtapaProduccion ep = new EtapaProduccion();
				ep.setCodigo(rs.getLong("codigo"));
				ep.setNombre(rs.getString("nombre"));
				respuesta.add(ep);
			}
		} catch (Exception e) {
			throw new Exception ("Problema al consultar a la base de datos");
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return respuesta;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Cliente> consultarClientes() throws Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		PreparedStatement state = null;
		ArrayList<String> select = new ArrayList<String>();
		select.add("*");

		String selectQuery = generateQuery(select, "CLIENTE", new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			state = conexion.prepareStatement(selectQuery);
			ResultSet rs = state.executeQuery();
			rs.next();
			while(!rs.isAfterLast()){
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getLong("codigo"));
				cliente.setNombreLegal(rs.getString("nombrelegal"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return clientes;

	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EstacionProduccion> darEstacionProduccionesConEtapas() throws Exception
	{
		ArrayList<EstacionProduccion> resp = new ArrayList<EstacionProduccion>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			//Toma todas las otras estaciones de produccion con su cuenta de lista de espera respectiva

			ArrayList<String> select = new ArrayList<String>();
			ArrayList<String> group = new ArrayList<String>();
			ArrayList<String> order = new ArrayList<String>();
			select.add("e.CODIGO");
			select.add("e.TIEMPO");
			select.add("e.CAPACIDAD");
			select.add("e.ESTADO");
			select.add("e.CODIGOETAPA");
			select.add("e.NOMBRE");
			select.add("j.CUENTA");
			String tablaOtrasEstaciones = "ESTACIONPRODUCCION e JOIN (SELECT ESTACIONPRODUCCION.CODIGO, COUNT (ETAPAPRODUCCION.CODIGO) AS CUENTA  FROM (ESTACIONPRODUCCION LEFT OUTER JOIN ETAPAPRODUCCION ON ESTACIONPRODUCCION.CODIGO=ETAPAPRODUCCION.ENESPERADE) GROUP BY ESTACIONPRODUCCION.CODIGO  ORDER BY CUENTA DESC) j ON e.CODIGO=j.CODIGO";
			String estacionesDeProduccionOpcionales = generateQuery(select, tablaOtrasEstaciones, new ArrayList<String>(), order, group);
			System.out.println(estacionesDeProduccionOpcionales);

			PreparedStatement statement = conexion.prepareStatement(estacionesDeProduccionOpcionales);
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				EstacionProduccion est = new EstacionProduccion();
				est.setCodigo(rs.getLong("CODIGO"));
				est.setTiempo(rs.getLong("TIEMPO"));
				est.setCapacidad(rs.getLong("CAPACIDAD"));
				est.setEstado(rs.getString("ESTADO"));
				est.setCodigoEtapaActual(rs.getLong("CODIGOETAPA"));
				est.setNombreEstacion(rs.getString("NOMBRE"));
				est.setNumEtapaProduccion(rs.getLong("CUENTA"));

				//Agrega el nombre de la etapa actual (si esta existe)

				if((Long)est.getCodigoEtapaActual()!=null)
				{
					ArrayList<String> select2 = new ArrayList<String>();
					select2.add("ETAPAPRODUCCION.NOMBRE");
					ArrayList<String> where2 = new ArrayList<String>();
					where2.add("ETAPAPRODUCCION.CODIGO="+est.getCodigoEtapaActual());
					String nombreEtapaActual = this.generateQuery(select2, "ETAPAPRODUCCION", where2, new ArrayList<String>(), new ArrayList<String>());
					PreparedStatement statement2 = conexion.prepareStatement(nombreEtapaActual);
					ResultSet rs1 = statement2.executeQuery();

					while(rs1.next())
					{
						est.setNombreEtapaActual(rs1.getString("NOMBRE"));
					}
					statement2.close();
				}
				else
				{
					est.setNombreEtapaActual("Sin asignar");
				}

				resp.add(est);
			}

			statement.close();
			closeConnection(conexion);

		} catch (Exception e) {
			e.printStackTrace();
			closeConnection(conexion);
			throw new Exception("No se pudo recuperar la informacion de las Estaciones de Procuccion");

		}

		return resp;
	}

	

	public ArrayList<Proveedor> darProveedores() throws Exception {
		ArrayList<Proveedor> clientes = new ArrayList<Proveedor>();

		PreparedStatement state = null;
		ArrayList<String> select = new ArrayList<String>();
		select.add("*");

		String selectQuery = generateQuery(select, "CLIENTE", new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			state = conexion.prepareStatement(selectQuery);
			ResultSet rs = state.executeQuery();
			rs.next();
			while(!rs.isAfterLast()){
				Proveedor cliente = new Proveedor();
				cliente.setCodigo(rs.getLong("codigo"));
				cliente.setNombreLegal(rs.getString("nombrelegal"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return clientes;
	}

	public ArrayList<EstacionProduccion> darEstaciones() throws Exception {
		ArrayList<EstacionProduccion> clientes = new ArrayList<EstacionProduccion>();

		PreparedStatement state = null;
		ArrayList<String> select = new ArrayList<String>();
		select.add("*");

		String selectQuery = generateQuery(select, "estacionproduccion", new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			state = conexion.prepareStatement(selectQuery);
			ResultSet rs = state.executeQuery();
			rs.next();
			while(!rs.isAfterLast()){
				EstacionProduccion cliente = new EstacionProduccion();
				cliente.setCodigo(rs.getLong("codigo"));
				cliente.setNombreEstacion(rs.getString("nombre"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return clientes;
	}
	
	//---------------------------------------------------------------
	// Iteraci�n 2 TODO
	//---------------------------------------------------------------

		//-----------------------------------------------------------
		// Requerimientos funcionales de consulta TODO
		//-----------------------------------------------------------	
	
		//-----------------------------------------------------------
		// Requerimientos funcionales TODO
		//-----------------------------------------------------------


	//---------------------------------------------------------------
	// Iteraci�n 3 TODO
	//---------------------------------------------------------------

		//-----------------------------------------------------------
		// Requerimientos funcionales de consulta TODO
		//-----------------------------------------------------------

	/**
	 * Muestra la informaci�n de los pedidos y su estado actual. 
	 * Esta consulta puede ser filtrada por diferentes conceptos y se espera como resultado: 
	 * cliente, productos, cantidades, costos, fechas, materiales requeridos para satisfacer el pedido, .... 
	 * Esta operaci�n es realizada por el encargado de ProdAndes
	 * @param cliente
	 * @param productos
	 * @param cantidadMinima
	 * @param cantidadMaxima
	 * @param costoMin
	 * @param costoMax
	 * @param fechaMin
	 * @param fechaMax
	 * @param materiales
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EstadoPedidoValue> consultarEstadoPedidos(String cliente, String productos, String cantidadMinima, String cantidadMaxima, String costoMin, String costoMax, String fechaMin, String fechaMax, String materiales) throws Exception{
		ArrayList<EstadoPedidoValue> pedidos = new ArrayList<EstadoPedidoValue>();
		PreparedStatement statement = null;

		ArrayList<String> select = new ArrayList<String>();
		select.add("pedido.codigo as codigopedido");
		select.add("cliente.codigo as codigocliente");
		select.add("cliente.nombrelegal as cliente");
		select.add("cantidad as cantidad");
		select.add("fechapedido as fechap");
		select.add("fechaEntrega as fechae");
		select.add("fechaEsperada as fechaes");
		select.add("nombre as producto");
		select.add("costopedido as costo");
		select.add("tipo as tipo");
		select.add("codigomaterial as codMat");
		select.add("nombrematerial");
		select.add("estado");
		select.add("codproducto");

		ArrayList<String> where = new ArrayList<String>();

		if(!productos.isEmpty()){where.add("codproducto="+productos);}
		if(!materiales.isEmpty()){where.add("codigomaterial="+materiales);}

		if(!cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad between "+cantidadMinima+" and "+cantidadMaxima);}
		else if(cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad > "+cantidadMinima);}
		else if(!cantidadMinima.isEmpty() && cantidadMaxima.isEmpty()){where.add("cantidad < "+cantidadMaxima);}

		if(!fechaMin.isEmpty() && !fechaMax.isEmpty()){where.add("fechapedido between TO_DATE('"+fechaMin+"', 'DD-MM-YYYY') and TO_DATE('"+fechaMax+"', 'DD-MM-YYYY')");}
		else if(!fechaMin.isEmpty() && fechaMax.isEmpty()){where.add("fechapedido > TO_DATE('"+fechaMin+"', 'DD-MM-YYYY')");}
		else if(fechaMin.isEmpty() && !fechaMax.isEmpty()){where.add("fechapedido < TO_DATE('"+fechaMin+"', 'DD-MM-YYYY')");}

		if(!costoMin.isEmpty() && !costoMax.isEmpty()){where.add("costopedido between "+costoMin+" and "+costoMax);}
		else if(costoMin.isEmpty() && !costoMax.isEmpty()){where.add("costopedido > "+costoMin);}
		else if(!costoMin.isEmpty() && costoMax.isEmpty()){where.add("costopedido < "+costoMax);}

		ArrayList<String> order = new ArrayList<String>();
		order.add("codigopedido");

		String tabla = "(select pedido.cantidad*producto.COSTO as costopedido, pedido.codigo from pedido   inner join producto  on producto.CODIGO=pedido.CODIGOPRODUCTO) c inner join PEDIDO on c.codigo=PEDIDO.CODIGO inner join (select codigomaterial, nombrematerial, tipo,  codigoproducto as codproducto, nombre from (select codigomaterial, nombrematerial, tipo, codigoproducto from (select codigomaterial, codigoetapa, nombrematerial, tipo from (select codigomaterial, codigoestacion, nombre as nombrematerial, tipo from requiere  inner join material  on material.CODIGO=requiere.CODIGOMATERIAL) inner join estacionproduccion on estacionproduccion.codigo=codigoestacion) inner join ETAPAPRODUCCION on ETAPAPRODUCCION.CODIGO=codigoetapa) inner join producto on codigoproducto=producto.codigo) on PEDIDO.CODIGO=codproducto inner join cliente on cliente.CODIGO=PEDIDO.CODIGOCLIENTE";

		String selectQuery = generateQuery(select, tabla, where, order, new ArrayList<String>());
		System.out.println(selectQuery);
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();

			rs.next();
			while(!rs.isAfterLast()){

				EstadoPedidoValue vos = new EstadoPedidoValue();
				vos.setCliente(rs.getString("cliente"));
				vos.setCodigo(rs.getString("codigopedido"));
				vos.setCodigoCliente(rs.getString("codigocliente"));
				vos.setCantidad(rs.getString("cantidad"));
				vos.setFechap(rs.getString("fechap"));
				vos.setFechaes(rs.getString("fechaes"));
				vos.setCosto(rs.getString("costo"));
				vos.setFechae(rs.getString("fechae"));
				vos.setNombreProducto(rs.getString("producto"));
				vos.setEstado(rs.getString("estado"));

				String codped = rs.getString("codigopedido");
				ArrayList<Material> mats = new ArrayList<Material>();
				while(!rs.isAfterLast() && rs.getString("codigopedido").equals(codped)){

					Material mat = new Material();
					mat.setNombre(rs.getString("nombrematerial"));
					mat.setTipo(rs.getString("tipo"));
					mat.setCodigo(rs.getLong("codMat"));

					mats.add(mat);

					rs.next();

				}

				vos.setMateriales(mats);
				pedidos.add(vos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return pedidos;
	}

	/**
	 * RFC6. CONSULTAR CLIENTES 
	 * Muestra  la  informaci�n  de  los clientes. 
	 * Esta  consulta  puede  ser  filtrada  por  diferentes  conceptos  y  se  espera  como resultado: 
	 * informaci�n general, los pedidos,.... Esta operaci�n es realizada por el encargado de ProdAndes.
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Cliente> consultarClientes(String producto, String estado, String cantidadMinima, String cantidadMaxima, String fechaMinima, String fechaMaxima ) throws Exception{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		PreparedStatement statement = null;
		String tabla = "pedido inner join cliente on cliente.codigo=pedido.codigocliente "
				+ "inner join usuario on cliente.LOGIN=usuario.LOGIN "
				+ "inner join producto on producto.codigo=pedido.CODIGOPRODUCTO";

		ArrayList<String> select = new ArrayList<String>();

		select.add("cliente.direccionelectronica as correo");
		select.add("ciudad");
		select.add("codigopostal as zip");
		select.add("documentoid");
		select.add("cliente.login");
		select.add("telefono");
		select.add("tipodocumento");
		select.add("departamento");
		select.add("direccionfisica as dir");
		select.add("fechapedido");
		select.add("fechaesperada");
		select.add("fechaentrega");
		select.add("pedido.codigo as codpedido");
		select.add("estado");
		select.add("registrosinv");
		select.add("nombrelegal");
		select.add("tipoidlegal");
		select.add("producto.codigo as codproducto");
		select.add("producto.nombre as producto");
		select.add("pedido.cantidad");
		select.add("cliente.idlegal");
		select.add("nacionalidad");
		select.add("cliente.codigo as codcliente");

		ArrayList<String> where = new ArrayList<String>();
		if(!producto.isEmpty()){where.add("producto='"+producto+"'");}

		if(!cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad between "+cantidadMinima+" and "+cantidadMaxima);}
		else if(cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad < "+cantidadMinima);}
		else if(!cantidadMinima.isEmpty() && cantidadMaxima.isEmpty()){where.add("cantidad > "+cantidadMaxima);}

		if(!fechaMinima.isEmpty() && !fechaMaxima.isEmpty()){where.add("fechapedido between TO_DATE('"+fechaMinima+"', 'DD-MM-YYYY') and TO_DATE('"+fechaMaxima+"', 'DD-MM-YYYY')");}
		else if(!fechaMinima.isEmpty() && fechaMaxima.isEmpty()){where.add("fechapedido > TO_DATE('"+fechaMinima+"', 'DD-MM-YYYY')");}
		else if(fechaMinima.isEmpty() && !fechaMaxima.isEmpty()){where.add("fechapedido < TO_DATE('"+fechaMaxima+"', 'DD-MM-YYYY')");}

		ArrayList<String> order = new ArrayList<String>();
		order.add("cliente.codigo");

		String selectQuery = generateQuery(select, tabla, where, order, new ArrayList<String>());
		try{
			System.out.println(selectQuery);


			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();

			rs.next();
			while(!rs.isAfterLast()){
				Cliente vos = new Cliente();

				vos.setCiudad(rs.getString("ciudad"));
				vos.setCodigo(rs.getLong("codcliente"));
				vos.setDepartamento(rs.getString("departamento"));
				vos.setDireccionElectronica(rs.getString("correo"));
				vos.setDocumentotold(rs.getLong("documentoid"));
				vos.setIdLegal(rs.getLong("idlegal"));
				vos.setLogin(rs.getString("login"));
				vos.setNacionalidad(rs.getString("nacionalidad"));
				vos.setNombreLegal(rs.getString("nombrelegal"));
				vos.setResgistroSINV(rs.getString("registrosinv"));
				vos.setTelefono(rs.getString("telefono"));
				vos.setTipoDocumento(rs.getInt("tipodocumento"));
				vos.setTipoIdLegal(rs.getInt("tipoidlegal"));

				String codcli = rs.getString("codcliente");
				ArrayList<Pedido> peds = new ArrayList<Pedido>();
				while(!rs.isAfterLast()&&rs.getString("codcliente").equals(codcli)){
					Pedido ped = new Pedido();
					ped.setCantidad(rs.getInt("cantidad"));
					ped.setEstado(rs.getInt("estado"));
					ped.setFechaEntrega(rs.getDate("fechapedido"));
					ped.setFechaEsperada(rs.getDate("fechaesperada"));
					ped.setFechaPedido(rs.getDate("fechapedido"));
					ped.setCodigo(rs.getLong("codpedido"));
					Producto prd = new Producto();
					prd.setNombre(rs.getString("producto"));
					prd.setCodigo(rs.getLong("codproducto"));
					ped.setProducto(prd);
					peds.add(ped);
					rs.next();
				}
				vos.setPedidos(peds);
				clientes.add(vos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return clientes;
	}

	/**
	 * RFC7. CONSULTAR PROVEEDORES 
	 * Muestra la informaci�n de los proveedores. 
	 * Esta consulta puede ser filtrada por diferentes conceptos y se espera como resultado: 
	 * informaci�n general, los materiales que provee, los productos que dependen de �l, pedidos pendientes, ....
	 * Esta operaci�n es realizada por el encargado de ProdAndes.
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Proveedor> consultarProveedores(String proveedor, String minCantidadEntrega, String maxCantidadEntrega, String minTiempo, String maxTiempo, String producto, String minCosto, String maxCosto, String minCantidad, String maxCantidad) throws Exception{
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		PreparedStatement statement = null;

		String tabla = "(select nommaterial, tipo, MAXIMACANTIDAD, tiempoentrega, login, codprod, "
				+ "codprovee, codmat, tipoidlegal, identificacionlegal, nombrelegal, costo, "
				+ "cantidadpedida, fechaesperada, fechapedido from (select material.nombre as "
				+ "nommaterial, tipo, MAXIMACANTIDAD, tiempoentrega, login, proveedor.CODIGO as codprovee, "
				+ "material.codigo as codmat, tipoidlegal, identificacionlegal, nombrelegal, codprod "
				+ "from material inner join suministro on suministro.CODIGOMATERIAL=material.CODIGO "
				+ "inner join proveedor on proveedor.CODIGO=suministro.CODIGOPROVEEDOR) "
				+ "inner join PEDIDOMATERIAL on PEDIDOMATERIAL.CODIGOPROVEEDOR=codprovee) "
				+ "inner join (select nombre, codprod, codestacion, codigomaterial "
				+ "from (select nombre, codprod, codigo as codestacion "
				+ "from (select producto.nombre, producto.codigo as codprod, etapaproduccion.CODIGO as codetapa "
				+ "from producto inner join etapaproduccion on producto.CODIGO=etapaproduccion.CODIGOPRODUCTO) "
				+ "inner join ESTACIONPRODUCCION on ESTACIONPRODUCCION.CODIGOETAPA=codetapa) "
				+ "inner join requiere on requiere.codigoestacion=codestacion) on codmat=codigomaterial ";

		ArrayList<String> select = new ArrayList<String>();
		select.add("*");

		ArrayList<String> where = new ArrayList<String>();
		if(!proveedor.isEmpty()){where.add("codprovee="+proveedor+"");}

		if(!minCantidadEntrega.isEmpty() && !maxCantidadEntrega.isEmpty()){where.add("maximacantidad between "+minCantidadEntrega+" and "+maxCantidadEntrega);}
		else if(minCantidadEntrega.isEmpty() && !maxCantidadEntrega.isEmpty()){where.add("maximacantidad < "+maxCantidadEntrega);}
		else if(!minCantidadEntrega.isEmpty() && maxCantidadEntrega.isEmpty()){where.add("maximacantidad > "+minCantidadEntrega);}

		if(!minTiempo.isEmpty() && !maxTiempo.isEmpty()){where.add("tiempoentrega between "+minTiempo+" and "+maxTiempo);}
		else if(minTiempo.isEmpty() && !maxTiempo.isEmpty()){where.add("tiempoentrega < "+maxTiempo);}
		else if(!minTiempo.isEmpty() && maxTiempo.isEmpty()){where.add("tiempoentrega > "+minTiempo);}

		if(!minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("cantidadpedida between "+minCantidad+" and "+maxCantidad);}
		else if(minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("cantidadpedida < "+maxCantidad);}
		else if(!minCantidad.isEmpty() && maxCantidad.isEmpty()){where.add("cantidadpedida > "+minCantidad);}

		if(!minCosto.isEmpty() && !maxCosto.isEmpty()){where.add("costo between "+minCosto+" and "+maxCosto);}
		else if(minCosto.isEmpty() && !maxCosto.isEmpty()){where.add("costo < "+maxCosto);}
		else if(!minCosto.isEmpty() && maxCosto.isEmpty()){where.add("costo > "+minCosto);}

		if(!producto.isEmpty()){where.add("codprod="+producto);}

		ArrayList<String> order = new ArrayList<String>();
		order.add("codproveedor");
		order.add("codprod");

		String selectQuery = generateQueryForUpdate(select, tabla, where, order, new ArrayList<String>());
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			statement.setQueryTimeout(1000);
			ResultSet rs = statement.executeQuery();

			while(rs.next()){
				Proveedor vos = new Proveedor();
				vos.setCodigo(rs.getLong("codproveedor"));
				proveedores.add(vos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return proveedores;
	}

		//----------------------------------------------------------
		// Requerimientos funcionales TODO
		//----------------------------------------------------------
	
	/**
	 * RF13 - CANCELAR PEDIDO DE PRODUCTOS 
	 * Cancela la operaci�n anterior, 
	 * teniendo en cuenta todas las restricciones de coherencia correspondientes. 
	 * Esta operaci�n es realizada por un usuario cliente de la empresa que realiz� el pedido que se quiere cancelar.
	 */
	@Deprecated
	public void cancelarPedido(){
		//TODO
		//FIXME
	}

	/**
	 * RF17 - REPORTAR CAMBIO DE ESTADO DE UNA ESTACI�N DE PRODUCCI�N
	 * Dada una estaci�n de producci�n y el cambio de estado deseado, 
	 * se modifica la informaci�n existente para reflejar ese cambio.
	 * Si  la  estaci�n  estaba  activa y  se  quiere  desactivar,  
	 * todas  las  etapas  de  producci�n  que  le  estaban  
	 * asignadas deben ser asignadas a las otras estaciones de producci�n de manera 
	 * que el trabajo total quede lo m�s uniformemente repartido  entre  las  estaciones activas  
	 * (esta  operaci�n se  llama  balanceo  de  carga).  
	 * De  la  misma  manera,  cuando  una estaci�n de producci�n se activa, 
	 * se le asignan algunas de las etapas que ya est�n vigentes en el sistema, 
	 * nuevamente tratando que la distribuci�n sea uniforme. 
	 * Esta operaci�n es realizada por un usuario gerente de producci�n de la empresa.
	 * @param idEstacionProduccion
	 * @return
	 * @throws Exception
	 */
	public boolean cambiarEstadoEstacionProduccion(String idEstacionProduccion) throws Exception 
	{
		boolean funciono = false;

		ArrayList<String> where = new ArrayList<String>();
		where.add("CODIGO="+idEstacionProduccion);
		ArrayList<String> select = new ArrayList<String>();
		select.add("CODIGO");
		select.add("CODIGOETAPA");
		select.add("ESTADO");
		String selectQuery = generateQuery(select, "ESTACIONPRODUCCION", where, new ArrayList<String>(), new ArrayList<String>());
		PreparedStatement statement = null;
		EstacionProduccion estacionPrincipal = new EstacionProduccion();

		try {

			//Obtiene la estacion

			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();

			if(rs.next()){
				estacionPrincipal.setCodigo(rs.getLong("CODIGO"));
				estacionPrincipal.setEstado(rs.getString("ESTADO"));
				estacionPrincipal.setCodigoEtapaActual(rs.getLong("CODIGOETAPA"));
			}
			
			if(estacionPrincipal.getEstado().equals(EstacionProduccion.ESTADO_ACTIVO))
			{
				//EN caso en que este activo y se desee desactivar la estacion de produccion

				//Toma todas las etapas de produccion que hay que reasignar
				select = new ArrayList<String>();
				where = new ArrayList<String>();
				select.add("CODIGO");
				where.add("ETAPAPRODUCCION.CODIGO="+estacionPrincipal.getCodigoEtapaActual()+" OR ETAPAPRODUCCION.ENESPERADE="+estacionPrincipal.getCodigo());

				String etapaProduccionEnEspera = generateQuery(select, "ETAPAPRODUCCION", where, new ArrayList<String>(), new ArrayList<String>());

				statement.close();
				statement = conexion.prepareStatement(etapaProduccionEnEspera);
				ResultSet rs1 = statement.executeQuery();

				ArrayList<EtapaProduccion> etapas = new ArrayList<EtapaProduccion>();
				while(rs1.next())
				{
					EtapaProduccion ep = new EtapaProduccion();
					ep.setCodigo(rs1.getLong("CODIGO"));
					etapas.add(ep);
				}
				
				//Toma todas las otras estaciones de produccion que estan activas en orden 
				//ascendente segun el numero de etapas que tengan asignaddos

				select = new ArrayList<String>();
				select.add("ESTACIONPRODUCCION.CODIGO");
				select.add("COUNT (ETAPAPRODUCCION.CODIGO) AS CUENTA ");
				where = new ArrayList<String>();
				where.add("ESTACIONPRODUCCION.ESTADO='"+EstacionProduccion.ESTADO_ACTIVO+"'");
				where.add("ESTACIONPRODUCCION.CODIGO != "+estacionPrincipal.getCodigo());
				ArrayList<String> group = new ArrayList<String>();
				group.add("ESTACIONPRODUCCION.CODIGO");
				ArrayList<String> order = new ArrayList<String>();
				order.add("CUENTA ASC");
				String tablaOtrasEstaciones = "ESTACIONPRODUCCION JOIN ETAPAPRODUCCION ON ESTACIONPRODUCCION.CODIGO=ETAPAPRODUCCION.ENESPERADE";

				String estacionesDeProduccionOpcionales = generateQuery(select, tablaOtrasEstaciones, where, order, group);

				statement.close();
				statement = conexion.prepareStatement(estacionesDeProduccionOpcionales);
				ResultSet rs2 = statement.executeQuery();
				ArrayList<EstacionProduccion> estacionesOpcionalesOrdenadas = new ArrayList<EstacionProduccion>();
				while(rs2.next())
				{
					EstacionProduccion est = new EstacionProduccion();
					est.setCodigo(rs2.getLong("CODIGO"));
					est.setNumEtapaProduccion(rs2.getLong("CUENTA"));
					estacionesOpcionalesOrdenadas.add(est);
				}

				//Verifica que exista alguna extaci�n, si no, cierra la conexi�n y retorna false

				if(estacionesOpcionalesOrdenadas.size()==0)
				{
					return funciono;
				}

				//Reasigna las etapas de producci�n seg�n el balanceo

				int contadorEstacion = 0;

				for(int i=0;i<etapas.size();i++)
				{
					EstacionProduccion est = estacionesOpcionalesOrdenadas.get(contadorEstacion);
					est.setNumEtapaProduccion(est.getNumEtapaProduccion()+1);
					ArrayList<String> columns = new ArrayList<String>();
					ArrayList<String> values = new ArrayList<String>();
					where = new ArrayList<String>();
					columns.add("ENESPERADE");
					values.add(est.getCodigo()+"");
					where.add("ETAPAPRODUCCION.CODIGO="+etapas.get(i).getCodigo());
					String agregaEtapa = generateUpdate("ETAPAPRODUCCION", columns, values, where);

					statement.close();
					statement = conexion.prepareStatement(agregaEtapa);
					statement.executeUpdate();

					//Actualiza el numEtapas
					est.setNumEtapaProduccion(est.getNumEtapaProduccion()+1);

					if(contadorEstacion<estacionesOpcionalesOrdenadas.size()-1)
					{
						if(est.getNumEtapaProduccion()>estacionesOpcionalesOrdenadas.get(contadorEstacion+1).getNumEtapaProduccion())
							contadorEstacion++;
					}

				}

				//Actualiza el estado de la estacion de produccion a inactivo

				ArrayList<String> columns = new ArrayList<String>();
				ArrayList<String> values = new ArrayList<String>();
				where = new ArrayList<String>();
				columns.add("ESTADO");
				columns.add("CODIGOETAPA");
				values.add("'"+EstacionProduccion.ESTADO_INACTIVO+"'");
				values.add("NULL");
				where.add("ESTACIONPRODUCCION.CODIGO="+estacionPrincipal.getCodigo());
				String actualizarEstacion = generateUpdate("ESTACIONPRODUCCION", columns, values, where);
				statement.close();
				statement = conexion.prepareStatement(actualizarEstacion);
				statement.executeUpdate();

				funciono=true;
			}
			else
			{
				//EN caso en que este inactivo y se desee activar la estacion de produccion

				//Toma todas las otras estaciones de produccion que estan activas en orden 
				//descendente segun el numero de etapas que tengan asignaddos

				select = new ArrayList<String>();
				where = new ArrayList<String>();
				ArrayList<String> group = new ArrayList<String>();
				ArrayList<String> order = new ArrayList<String>();
				select.add("ESTACIONPRODUCCION.CODIGO");
				select.add("COUNT (ETAPAPRODUCCION.CODIGO) AS CUENTA ");
				where.add("ESTACIONPRODUCCION.ESTADO='"+EstacionProduccion.ESTADO_ACTIVO+"'");
				where.add("ESTACIONPRODUCCION.CODIGO != "+estacionPrincipal.getCodigo());
				group.add("ESTACIONPRODUCCION.CODIGO");
				order.add("CUENTA DESC");
				String tablaOtrasEstaciones = "ESTACIONPRODUCCION JOIN ETAPAPRODUCCION ON ESTACIONPRODUCCION.CODIGO=ETAPAPRODUCCION.ENESPERADE";
				String estacionesDeProduccionOpcionales = generateQuery(select, tablaOtrasEstaciones, where, order, group);
				System.out.println(estacionesDeProduccionOpcionales);

				statement.close();
				statement = conexion.prepareStatement(estacionesDeProduccionOpcionales);
				ResultSet rs2 = statement.executeQuery();
				ArrayList<EstacionProduccion> estacionesOpcionalesOrdenadas = new ArrayList<EstacionProduccion>();

				while(rs2.next())
				{
					EstacionProduccion est = new EstacionProduccion();
					est.setCodigo(rs2.getLong("CODIGO"));
					est.setNumEtapaProduccion(rs2.getLong("CUENTA"));
					estacionesOpcionalesOrdenadas.add(est);
				}

				//Obtiene el promedio de las estaciones

				Long avg = (long) 0;
				for(int i=0;i<estacionesOpcionalesOrdenadas.size();i++)
					avg+=estacionesOpcionalesOrdenadas.get(i).getNumEtapaProduccion();
				avg = avg / estacionesOpcionalesOrdenadas.size();


				//Cambia las etapas de producci�n seg�n el balanceo deceado
				long contador = (long) 0;
				int contadorEstacion = 0;
				long elElegido = -1;
				while(contador<avg&&contadorEstacion<estacionesOpcionalesOrdenadas.size())
				{
					EstacionProduccion est = estacionesOpcionalesOrdenadas.get(contadorEstacion);
					boolean salida = false;
					//Obtiene todas las etapas asociadas
					select = new ArrayList<String>();
					where = new ArrayList<String>();
					select.add("CODIGO");
					where.add("ETAPAPRODUCCION.ENESPERADE="+est.getCodigo());

					String etapaProduccionEnEspera = generateQuery(select, "ETAPAPRODUCCION", where, new ArrayList<String>(), new ArrayList<String>());

					statement.close();
					statement = conexion.prepareStatement(etapaProduccionEnEspera);
					ResultSet rs1 = statement.executeQuery();

					//ArrayList<EtapaProduccion> etapas = new ArrayList<EtapaProduccion>();
					//Itera en las etapas hasta lograr el balanceo
					while(rs1.next()&&est.getNumEtapaProduccion()>0&&contador<avg&&!salida)
					{
						EtapaProduccion ep = new EtapaProduccion();
						ep.setCodigo(rs1.getLong("CODIGO"));

						//Actualiza la nueva etapa de produccion

						ArrayList<String> columns = new ArrayList<String>();
						ArrayList<String> values = new ArrayList<String>();
						where = new ArrayList<String>();
						columns.add("ENESPERADE");
						values.add(estacionPrincipal.getCodigo()+"");
						where.add("ETAPAPRODUCCION.CODIGO="+ep.getCodigo());
						String agregaEtapa = generateUpdate("ETAPAPRODUCCION", columns, values, where);

						//						statement.close();
						PreparedStatement statement2 = conexion.prepareStatement(agregaEtapa);
						statement2.executeUpdate();
						conexion.commit();
						//actializa indices y contadores
						est.setNumEtapaProduccion(est.getNumEtapaProduccion()-1);
						contador++;
						elElegido = (elElegido==-1) ? ep.getCodigo(): elElegido;
						if(contadorEstacion<estacionesOpcionalesOrdenadas.size()-1)
						{
							if(est.getNumEtapaProduccion()<estacionesOpcionalesOrdenadas.get(contadorEstacion+1).getNumEtapaProduccion())
							{	
								contadorEstacion++;
								salida=true;
							}
						}
					}

				}
				//Finaliza el proceso
				ArrayList<String> columns = new ArrayList<String>();
				ArrayList<String> values = new ArrayList<String>();
				where = new ArrayList<String>();
				columns.add("ESTADO");
				columns.add("CODIGOETAPA");
				values.add("'"+EstacionProduccion.ESTADO_ACTIVO+"'");
				if(elElegido==-1){values.add("NULL");}else{values.add(""+elElegido);}
				where.add("ESTACIONPRODUCCION.CODIGO="+estacionPrincipal.getCodigo());
				String actualizarEstacion = generateUpdate("ESTACIONPRODUCCION", columns, values, where);
				statement.close();
				statement = conexion.prepareStatement(actualizarEstacion);
				statement.executeUpdate();
				if(elElegido!=-1){
					columns = new ArrayList<String>();
					values = new ArrayList<String>();
					where= new ArrayList<String>();
					columns.add("ENESPERADE");
					values.add("NULL");
					where.add("ETAPAPRODUCCION.CODIGO="+elElegido);
					String actualizarEtapa = generateUpdate("ETAPAPRODUCCION", columns, values, where);
					statement.close();
					statement = conexion.prepareStatement(actualizarEtapa);
					statement.executeUpdate();
				}
				funciono=true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
				statement.close();
				closeConnection(conexion);
				throw new Exception ("No pudo hacer rollback");
			}
		}
		finally
		{
			if(funciono)
			{
				conexion.commit();
				statement.close();
				closeConnection(conexion);		
				System.out.println("Funciono!!!!!!");
			}
			else
			{
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					statement.close();
					closeConnection(conexion);
					throw new Exception ("No pudo hacer rollback");
				}
			}
		}
		statement.close();
		closeConnection(conexion);
		System.out.println("termino");
		return funciono;

	}

	//--------------------------------------------------------------
	// Iteraci�n 4 TODO
	//--------------------------------------------------------------

		//----------------------------------------------------------
		// Requeimientos Funcinoales de Consulta 8 TODO
		//----------------------------------------------------------

	/**
	 * RFC8.CONSULTAR EJECUCI�N DE ETAPAS DE PRODUCCI�N 1
	 * Mostrar las etapas de producci�n ejecutadas en un rango de tiempo (dado por el usuario), 
	 * que correspondan a un criterio de b�s queda asociado con la ejecuci�n de dicha etapa 
	 * (material, tipo de material, pedido, cantidades, ...), tambi�n dado por el usuario.
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idMaterial
	 * @param tipoMaterial
	 * @param idPedido
	 * @param cantidades
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<EtapaProduccion> consultarEjecucionEtapasProduccionV1(String fechaInicio, String fechaFin, String idMaterial, String tipoMaterial, String idPedido, String minCantidad, String maxCantidad, int pagina) throws Exception
	{
		PreparedStatement statement = null;
		ArrayList<EtapaProduccion> resp = new ArrayList<EtapaProduccion>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			String tabla = "EtapaProduccion eta"; 

			ArrayList<String> select = new ArrayList<String>();
			select.add("eta.*");

			ArrayList<String> where = new ArrayList<String>();
			if(!idMaterial.isEmpty()){
				where.add("m.codigo="+idMaterial);
				tabla += ", EstacionProduccion, Requiere, material";
				where.add("EstacionProduccion.CodigoEtapa=eta.Codigo");
				where.add("EstacionProduccion.Codigo=Requiere.CodigoEstacion");
				where.add("Material.Codigo=Requiere.CodigoMaterial");
			}
			if(!idPedido.isEmpty()){
				where.add("p.codigo="+idPedido);
				tabla += "PEDIDO, Producto";
				tabla = "PEDIDO, Producto, "+tabla;
				where.add("PEDIDO.CodigoProducto=Producto.Codigo");
				where.add("Producto.Codigo= eta.CODIGOPRODUCTO ");
			}
			if(!minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("eta.cantidad between "+minCantidad+" and "+maxCantidad);}
			else if(!minCantidad.isEmpty() && maxCantidad.isEmpty()){where.add("eta.cantidad < "+maxCantidad);}
			else if(minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("eta.cantidad > "+minCantidad);}
			
			where.add("fechainicio between to_date('"+fechaInicio+"','YYYY/mm/dd') and to_date('"+fechaFin+"','YYYY/mm/dd')");

			String selectingQuery = generateQuery(select, tabla, where, new ArrayList<String>(), new ArrayList<String>());	
			
			String paginada = pagination(selectingQuery, pagina, 25);
System.out.println(paginada);
			statement = conexion.prepareStatement(paginada);
			ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				EtapaProduccion temp = new EtapaProduccion();
				temp.setCodigo(resultados.getLong("codigo"));
				temp.setEtapa(resultados.getInt("etapa"));
				temp.setNombre(resultados.getString("nombre"));
				temp.setFechaInicio(resultados.getDate("fechaInicio"));
				temp.setFechaFin(resultados.getDate("fechaFin"));
				temp.setTiempoEjecuacion(resultados.getLong("tiempoEjecucion"));
				temp.setDescripcion(resultados.getString("descripcion"));
				resp.add(temp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return resp;
	}

	/**
	 * RFC9. CONSULTAR EJECUCI�N DE ETAPAS DE PRODUCCI�N 2
	 * Mostrar las etapas de producci�n ejecutadas en un rango de tiempo (dado por el usuario), 
	 * que NO correspondan a un criterio de b�squeda asociado con la ejecuci�n de dicha etapa
	 * (material, tipo de material, pedido, cantidades, ...), tambi�n dado por el usuario.
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idMaterial
	 * @param tipoMaterial
	 * @param idPedido
	 * @param cantidades
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<EtapaProduccion> consultarEjecucionEtapasProduccionV2(String fechaInicio, String fechaFin, String idMaterial, String tipoMaterial, String idPedido, String minCantidad, String maxCantidad, int pagina) throws Exception
	{
		ArrayList<EtapaProduccion> resp = new ArrayList<EtapaProduccion>();
		PreparedStatement statement = null;
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			String tabla = "";

			ArrayList<String> select = new ArrayList<String>();
			select.add("eta.*");

			ArrayList<String> where = new ArrayList<String>();
			if(!idMaterial.isEmpty()){
				where.add("m.codigo="+idMaterial);
				tabla += ", EstacionProduccion, Requiere, material";
				where.add("EstacionProduccion.CodigoEtapa=eta.Codigo");
				where.add("EstacionProduccion.Codigo=Requiere.CodigoEstacion");
				where.add("Material.Codigo=Requiere.CodigoMaterial");
			}
			if(!idPedido.isEmpty()){
				where.add("p.codigo="+idPedido);
				tabla += "PEDIDO, Producto";
				tabla = "PEDIDO, Producto, "+tabla;
				where.add("PEDIDO.CodigoProducto=Producto.Codigo");
				where.add("Producto.Codigo= eta.CODIGOPRODUCTO ");
				//				if(!minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("cantidad between "+minCantidad+" and "+maxCantidad);}
				//				else if(!minCantidad.isEmpty() && maxCantidad.isEmpty()){where.add("cantidad < "+maxCantidad);}
				//				else if(minCantidad.isEmpty() && !maxCantidad.isEmpty()){where.add("cantidad > "+minCantidad);}
			}
			
			where.add("fechainicio between "+fechaInicio+" and "+fechaFin);

			String selectingQuery = generateQuery(select, tabla, where, new ArrayList<String>(), new ArrayList<String>());
			String negSelectingQuery = "select eta2.* from (select * from ETAPAPRODUCCION) eta2 MINUS ("+selectingQuery+")";

			String paginada = pagination(negSelectingQuery, pagina, 25);
			statement = conexion.prepareStatement(paginada);
			System.out.println(paginada);
			ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				EtapaProduccion temp = new EtapaProduccion();
				temp.setCodigo(resultados.getLong("codigo"));
				temp.setEtapa(resultados.getInt("etapa"));
				temp.setNombre(resultados.getString("nombre"));
				temp.setFechaInicio(resultados.getDate("fechaInicio"));
				temp.setFechaFin(resultados.getDate("fechaFin"));
				temp.setTiempoEjecuacion(resultados.getLong("tiempoEjecucion"));
				temp.setDescripcion(resultados.getString("descripcion"));
				resp.add(temp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return resp;
	}

	/**
	 * RFC10. CONSULTAR PEDIDOS 2 
	 * Mostrar todos los pedidos que usan materiales de un tipo X con costo valor mayor a Y.
	 * Los valores de X y Y  son dados por el usuario.
	 * @param tipoMaterial
	 * @param costo
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<Pedido> consultarPedidosV2(String tipoMaterial, String costo, int pagina) throws Exception
	{
		PreparedStatement statement = null;
		ArrayList<Pedido> resp = new ArrayList<Pedido>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			ArrayList<String> select = new ArrayList<String>();
			select.add("ped.*");
			String tabla = "PEDIDO ped, Producto, EtapaProduccion, EstacionProduccion, Requiere, Material";
			
			ArrayList<String> where = new ArrayList<String>();
			where.add("ped.CodigoProducto=Producto.Codigo");
			where.add("Producto.Codigo=EtapaProduccion.CodigoProducto");
			where.add("EstacionProduccion.CodigoEtapa=EtapaProduccion.Codigo");
			where.add("EstacionProduccion.Codigo=Requiere.CodigoEstacion");
			where.add("Material.Codigo=Requiere.CodigoMaterial");
			where.add("Material.Tipo='"+tipoMaterial+"'");
			where.add("Producto.Costo>"+costo);
			
			String solicitudPedidos = generateQuery(select, tabla, where, new ArrayList<String>(), new ArrayList<String>());
			
			String paginada = pagination(solicitudPedidos, pagina, 25);
			
			statement = conexion.prepareStatement(paginada);
			ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				Pedido temp = new Pedido();
				temp.setCodigo(resultados.getLong("codigo"));
				temp.setEstado(resultados.getInt("estado"));
				temp.setCantidad(resultados.getLong("cantidad"));
				temp.setFechaPedido(resultados.getDate("fechaPedido"));
				temp.setFechaEsperada(resultados.getDate("fechaEsperada"));
				temp.setFechaEntrega(resultados.getDate("fechaEntrega"));
				Producto tempProd = new Producto();
				tempProd.setCodigo(resultados.getLong("codigoProducto"));
				Administrador tempAdmin = new Administrador();
				tempAdmin.setCodigo(resultados.getLong("codigoEmpresa"));
				Cliente tempClien = new Cliente();
				tempClien.setCodigo(resultados.getLong("codigoCliente"));
				temp.setProducto(tempProd);
				temp.setAdmin(tempAdmin);
				temp.setCliente(tempClien);
				resp.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return resp;

	}

	/**
	 * RFC11. CONSULTAR MATERIAL 2
	 * Dado el identificador de un material, 
	 * mostrar la informaci�n de los pedidos en los que ha estado involucrado,
	 * incluyendo los que est�n en etapa de producci�n. 
	 * Para los pedidos no activos actualmente debe especificarse el rango de fechas en los que estuvo activo.
	 * @param idMaterial
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<Pedido> consultarMaterialesV2ParaPedidos(String idMaterial, int pagina) throws Exception
	{
		PreparedStatement statement = null;
		ArrayList<Pedido> resp = new ArrayList<Pedido>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			ArrayList<String> select = new ArrayList<String>();
			select.add("ped.*");
			String tabla = "PEDIDO ped, Producto, EtapaProduccion, EstacionProduccion, Requiere";
			
			ArrayList<String> where = new ArrayList<String>();
			where.add("ped.CodigoProducto=Producto.Codigo");
			where.add("Producto.Codigo=EtapaProduccion.CodigoProducto");
			where.add("EstacionProduccion.CodigoEtapa=EtapaProduccion.Codigo");
			where.add("EstacionProduccion.Codigo=Requiere.CodigoEstacion");
			where.add(idMaterial+"=Requiere.CodigoMaterial");
			String solicitudPedidos = generateQuery(select, tabla, where, new ArrayList<String>(), new ArrayList<String>());
			
			String paginada = pagination(solicitudPedidos, pagina, 25);
			
			statement = conexion.prepareStatement(paginada);
			ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				Pedido temp = new Pedido();
				temp.setCodigo(resultados.getLong("Codigo"));
				temp.setEstado(resultados.getInt("Estado"));
				temp.setCantidad(resultados.getLong("Cantidad"));
				temp.setFechaPedido(resultados.getDate("FechaPedido"));
				temp.setFechaEsperada(resultados.getDate("FechaEsperada"));
				temp.setFechaEntrega(resultados.getDate("FechaEntrega"));
				Producto tempProd = new Producto();
				tempProd.setCodigo(resultados.getLong("CodigoProducto"));
				Administrador tempAdmin = new Administrador();
				tempAdmin.setCodigo(resultados.getLong("CodigoEmpresa"));
				Cliente tempClien = new Cliente();
				tempClien.setCodigo(resultados.getLong("CodigoCliente"));
				temp.setProducto(tempProd);
				temp.setAdmin(tempAdmin);
				temp.setCliente(tempClien);
				resp.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new Exception ("Error cerrando");
				}
			}
			closeConnection(conexion);
		}
		return resp;

	}
	
	//--------------------------------------------------------------
	// Requerimientos iteraci�n 5 TODO
	//--------------------------------------------------------------

		//--------------------------------------------------------------
		// Requerimientos funcionales TODO
		//--------------------------------------------------------------

	//public 
	
		//--------------------------------------------------------------
		// Requerimientos funcionales de consulta TODO
		//--------------------------------------------------------------

	//--------------------------------------------------------------
	// Generadores TODO
	//--------------------------------------------------------------

	/**
	 * Genera una cadena de texto que representa una sentencia SQL para seleccionar informaci�n.
	 * @param select
	 * @param tabla
	 * @param where
	 * @param order
	 * @param group
	 * @return
	 */
	private String generateQuery(ArrayList<String> select, String tabla, ArrayList<String> where, ArrayList<String> order, ArrayList<String> group){
		String query = "SELECT ";
		// Lista los atributos del registro que van a ser seleccionados
		Iterator<String> iteraSelect = select.iterator();
		while(iteraSelect.hasNext()){
			String act = iteraSelect.next();
			if(iteraSelect.hasNext()){
				query += act+", ";
			}
			else{
				query += act;
			}
		}

		// Indica de que tabla va a sacar los registros
		query += " FROM "+tabla+" ";

		// Lista las condiciones del por las que se va a seleccionar
		if(!where.isEmpty()){
			query += " WHERE ";
			Iterator<String> iteraWhere = where.iterator();
			while(iteraWhere.hasNext()){
				String act = iteraWhere.next();
				if(iteraWhere.hasNext()){
					query += act+"AND ";
				}
				else{
					query += act;
				}
			}
		}

		// Lista por que atributos se va a agrupar
		if(!group.isEmpty()){
			query += " GROUP BY ";
			Iterator<String> iteraGroup = group.iterator();
			while(iteraGroup.hasNext()){
				String act = iteraGroup.next();
				if(iteraGroup.hasNext()){
					query += act+", ";
				}
				else{
					query += act;
				}
			}
		}

		// Lista por que atributos se ordenara
		if(!order.isEmpty()){
			query += " ORDER BY ";
			Iterator<String> iteraOrder = order.iterator();
			while(iteraOrder.hasNext()){
				String act = iteraOrder.next();
				if(iteraOrder.hasNext()){
					query += act+", ";
				}
				else{
					query += act;
				}
			}
		}
		return query;
	}

	/**
	 * Genera una cadena de texto que representa una sentencia SQL para seleccionar informaci�n, para update.
	 * @param select
	 * @param tabla
	 * @param where
	 * @param order
	 * @param group
	 * @return
	 */
	private String generateQueryForUpdate(ArrayList<String> select, String tabla, ArrayList<String> where, ArrayList<String> order, ArrayList<String> group){
		String query = "SELECT ";
		// Lista los atributos del registro que van a ser seleccionados
		Iterator<String> iteraSelect = select.iterator();
		while(iteraSelect.hasNext()){
			String act = iteraSelect.next();
			if(iteraSelect.hasNext()){
				query += act+", ";
			}
			else{
				query += act;
			}
		}

		// Indica de que tabla va a sacar los registros
		query += " FROM "+tabla+" ";

		// Lista las condiciones del por las que se va a seleccionar
		if(!where.isEmpty()){
			query += " WHERE ";
			Iterator<String> iteraWhere = where.iterator();
			while(iteraWhere.hasNext()){
				String act = iteraWhere.next();
				if(iteraWhere.hasNext()){
					query += act+"AND ";
				}
				else{
					query += act;
				}
			}
		}

		// Lista por que atributos se va a agrupar
		if(!group.isEmpty()){
			query += " GROUP BY ";
			Iterator<String> iteraGroup = group.iterator();
			while(iteraGroup.hasNext()){
				String act = iteraGroup.next();
				if(iteraGroup.hasNext()){
					query += act+", ";
				}
				else{
					query += act;
				}
			}
		}

		// Lista por que atributos se ordenara
		if(!order.isEmpty()){
			query += " ORDER BY ";
			Iterator<String> iteraOrder = order.iterator();
			while(iteraOrder.hasNext()){
				String act = iteraOrder.next();
				if(iteraOrder.hasNext()){
					query += act+", ";
				}
				else{
					query += act;
				}
			}
		}

		//Indica que es para actualizar

		query += " FOR UPDATE";
		return query;
	}

	/**
	 * Genera una cadena de texto que representa una sentencia SQL de update
	 * @param tabla
	 * @param columns
	 * @param values
	 * @param where
	 * @return
	 */
	private String generateUpdate(String tabla, ArrayList<String> columns, ArrayList<String> values , ArrayList<String> where){
		String query = "UPDATE ";

		// Indica de que tabla que se va a actualizar
		query += tabla+" SET ";

		// Lista los atributos del registro que van a ser seleccionados
		Iterator<String> iteraColumns = columns.iterator();
		Iterator<String> iteraValues = values.iterator();
		while(iteraColumns.hasNext()){
			while(iteraValues.hasNext())
			{
				String col = iteraColumns.next();
				String val = iteraValues.next();
				if(iteraColumns.hasNext()&&iteraValues.hasNext()){
					query += col+" = "+val+", ";
				}
				else{
					query += col+" = "+val;
				}
			}

		}

		// Lista las condiciones del por las que se va a actualizar
		if(!where.isEmpty()){
			query += " WHERE ";
			Iterator<String> iteraWhere = where.iterator();
			while(iteraWhere.hasNext()){
				String act = iteraWhere.next();
				if(iteraWhere.hasNext()){
					query += act+"AND ";
				}
				else{
					query += act;
				}
			}
		}

		return query;
	}

	/**
	 * 
	 * @param tabla
	 * @param columns
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unused")
	private String generateInsert(String tabla, ArrayList<String> columns, ArrayList<String> values){
		String query = "INSERT INTO ";

		// Indica de que tabla que se va a insertar
		query += tabla+" ( ";

		// Lista las columnas en las que se va a insertar
		Iterator<String> iteraColumns = columns.iterator();
		while(iteraColumns.hasNext()){
			String act = iteraColumns.next();
			if(iteraColumns.hasNext()){
				query += act+", ";
			}
			else{
				query += act+" ) ";
			}
		}

		// Lista los atributos que se van a insertar
		query += " VALUES ( ";

		Iterator<String> iteraValues = values.iterator();
		while(iteraValues.hasNext()){
			String act = iteraValues.next();
			if(iteraValues.hasNext()){
				query += act+", ";
			}
			else{
				query += act+" ) ";
			}
		}
		return query;
	}
	
	private String pagination(String sentencia, int pagina, int cantidad){
		return "select * from ( select  /*+FIRST_ROWS("+cantidad+") */a.*, rownum rnum from ("+sentencia+" order by codigo) a where rownum <= "+(pagina)*cantidad+") where rnum >="+(((pagina-1)*cantidad)+1);
	}
	
	//---------------------------------------------------------------------
	// Consultas auxiliares TODO
	//---------------------------------------------------------------------
	
}
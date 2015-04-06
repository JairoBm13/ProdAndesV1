/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 1-Marzo-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.prodAndes.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import co.edu.uniandes.prodAndes.vos.Administrador;
import co.edu.uniandes.prodAndes.vos.Cliente;
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
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexi�n.
	 */
	private static final String ARCHIVO_CONEXION = "../WebContent/conexion.properties";

	private static final String CONSULTA_PRODUCTO = "Producto";

	private static final String CONSULTA_MATERIAL = "Material";

	private static final String CONSULTA_ETAPA_PROD = "Etapa Produccion";
	
	public static final String TIPO_MATERIAL_MATERIA_PRIMA = "Materia Prima";
	
	public static final String TIPO_MATERIAL_COMPONENTE = "Componente";
	
	public static final String PEDIDO_ESTADO_LISTO = "listo";
	
	public static final String PEDIDO_ESTADO_EN_PRODUCCION = "enProduccion";

	public static final String PEDIDO_ESTADO_EN_ESPERA = "enEspera";
	
	//----------------------------------------------------
	//Atributos
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

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO() 
	{		
		inicializar("");
	}

	// -------------------------------------------------
	// M�todos
	// -------------------------------------------------

	/**
	 * obtiene ls datos necesarios para establecer una conexion
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

		try {
			String selectQuery = "select cantidad from producto where estado="+etapa+"codigo="+codigo;
			establecerConexion(cadenaConexion, usuario, clave);
			statement = conexion.prepareStatement(selectQuery);

			ResultSet rs = statement.executeQuery();
			int cantidad;
			rs.next();
			cantidad = rs.getInt("cantidad");
			String updateIncQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo+" estado="+etapa+1;
			statement = conexion.prepareStatement(updateIncQuery);
			statement.executeUpdate();
			String updateDecQuery = "update producto set cantidad=0 where codigo="+codigo+" estado="+etapa;
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();

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

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String updateDecQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo;
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();
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
			statement = conexion.prepareStatement(selectingQuery);

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
			statement = conexion.prepareStatement(selectingQuery);

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
		String selectingQuery = "Select cantidad, nombre from PRODUCTO ";
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
			statement = conexion.prepareStatement(selectingQuery);

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

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String selectQuery = "select codigoProducto from PEDIDO where codigo="+pedido;
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			double producto = 0;
			if(rs.next()){
				producto = rs.getDouble("codigoProducto");
			}
			String updateQueryPedi = "update pedido set estado='Finalizado' where estado='Listo' and codigo="+pedido;
			statement = conexion.prepareStatement(updateQueryPedi);
			statement.executeUpdate();
			String updateQueryProd = "update producto set cantidad=0 where estado=0 and codigoProducto="+producto;
			statement = conexion.prepareStatement(updateQueryProd);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
			statement = conexion.prepareStatement(selectQueryUsuario);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				String clave = rs.getString("clave");
				if(!clave.equals(pass)) return null;
				String ciudad = rs.getString("ciudad");
				String codigoPostal = rs.getString("codigoPostal");
				String direccion = rs.getString("direccionElectronica");
				int documentoId = rs.getInt("documentoId");
				String nacionalidad = rs.getString("nacionalidad");
				String login = rs.getString("login");
				String telefono = rs.getNString("telefono");
				int tipoDocumento = rs.getInt("tipoDocumento");

				String selectQueryAdmin = "select * from administrador";
				if(!usuarion.isEmpty()){ selectQueryAdmin+=" where login='"+usuarion+"'";}
				else if(!correo.isEmpty()){ selectQueryAdmin+=" where direccionElectronica='"+correo+"'";}
				statement = conexion.prepareStatement(selectQueryAdmin);
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

			//Rectifica si hay cantidad suficiente


			PreparedStatement  prcantidadDisponible = conexion.prepareStatement("SELECT cantidad from Producto where Producto.codigo="+idProducto);
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
				psaactualizarDisponibles1.executeUpdate();
				psaactualizarDisponibles1.close();
				
				
				//Codigo del admin y crea pedido
				pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
				ResultSet admin = pSRequeridosNum.executeQuery();
				int adminID =0;
				if(admin.next())
					adminID=admin.getInt("codigo");

				pSRequeridosNum =conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
						+ "values (incremento_id_Pedido.NextVal,'"+PEDIDO_ESTADO_LISTO+"',"+cantidad+", NOW(),"+fechaEspera+","+idProducto+","+adminID+",'"+loginCLiente+"' )");
				pSRequeridosNum.executeUpdate();

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
					ResultSet admin = pSRequeridosNum.executeQuery();
					int adminID =0;
					if(admin.next())
						adminID=admin.getInt("codigo");

					pSRequeridosNum = conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
							+ "values (incremento_id_Pedido.NextVal,'"+PEDIDO_ESTADO_EN_PRODUCCION+"',"+cantidad+", NOW(),"+fechaEspera+","+idProducto+","+adminID+",'"+loginCLiente+"' )");
					pSRequeridosNum.executeUpdate();



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
			prepStmt = conexion.prepareStatement(selectQuery);
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
	    		
	    		
	    		prepStmt = conexion.prepareStatement(sentencia);
	    		
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
	    		
	    		
	    		prepStmt = conexion.prepareStatement(sentencia);
	    		
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

	public void registrarUsuario(String login, String direccionElectronica,
			String pass, String idcli, String selTipoId, String ciudad,
			String nacionalidad, String departamento, String direccionFisica,
			String telefno, String codPostal) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stament = null;
		String insertQuery = "insert into usuario ('login','direccionelectronica','clave','documentoid','tipodocumento','nacionalidad','ciudad','departamento','direccionfisica','codigopostal','telefono') values ('"+login+"','"+direccionElectronica+"','"+pass+"','"+idcli+"','"+selTipoId+"','"+nacionalidad+"','"+ciudad+"','"+departamento+"','"+direccionFisica+"','"+codPostal+"','"+telefno+"')";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
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

	public void registrarCliente(String login, String direccionElectronica, String id, String nombrelegal, String sinv, String tipoIdLegal) throws Exception {
		PreparedStatement stament = null;
		String insertQuery = "insert into cliente ('login','direccionelectronica','nombrelegal','idlegal', 'tipoidlegal','registrosinv','codigo') values ('"+login+"','"+direccionElectronica+"','"+nombrelegal+"','"+id+"','"+tipoIdLegal+"','"+sinv+"')";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
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

	public void registrarOperario(String login, String direccionElectronica, String cargo, String nombre) throws Exception {
		PreparedStatement stament = null;
		String insertQuery = "insert into operario ('login','direccionElectronica','nombre','cargo', codigo) values ('"+login+"','"+direccionElectronica+"','"+nombre+"','"+cargo+"',incremento_id_operario.NextVal) ";
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			stament = conexion.prepareStatement(insertQuery);
			stament.executeUpdate();
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

	public int registrarProveedor(String login, String direccionElectronica,
			String nombrelegal, String id, String tipoIdLegal, String cantidad,
			String selMate, String tiempo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void registrarMaterialProvisto(int codigoProv, String selMate,
			String cantidad, String tiempo) {
		// TODO Auto-generated method stub
		
	}
}

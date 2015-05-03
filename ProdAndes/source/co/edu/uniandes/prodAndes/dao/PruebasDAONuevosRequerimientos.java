package co.edu.uniandes.prodAndes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import co.edu.uniandes.prodAndes.vos.Administrador;
import co.edu.uniandes.prodAndes.vos.Cliente;
import co.edu.uniandes.prodAndes.vos.EstacionProduccion;
import co.edu.uniandes.prodAndes.vos.EstadoPedidoValue;
import co.edu.uniandes.prodAndes.vos.EtapaProduccion;
import co.edu.uniandes.prodAndes.vos.Material;
import co.edu.uniandes.prodAndes.vos.Pedido;
import co.edu.uniandes.prodAndes.vos.Producto;
import co.edu.uniandes.prodAndes.vos.ProveedorValue;

public class PruebasDAONuevosRequerimientos {

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
	public PruebasDAONuevosRequerimientos() 
	{		
		inicializar("");
	}

	public void hacerSelect(){
		String sql = "Select codigo from producto where codigo=1 for update";
		PreparedStatement state= null;
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			//				conexion.setAutoCommit(false);
			state = conexion.prepareStatement(sql);
			state.setQueryTimeout(1);
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
		PruebasDAONuevosRequerimientos cosa = new PruebasDAONuevosRequerimientos();
		try {
			ConsultaDAO consultaDao = new ConsultaDAO();
			consultaDao.consultarPedidosV2("hola", 1);
			consultaDao.consultarMaterialesV2ParaPedidos("1");
			consultaDao.consultarEjecucionEtapasProduccionV1(new Date(0, 0, 0), new Date(115, 2, 17), null, null, "", null);
			consultaDao.consultarEjecucionEtapasProduccionV1(new Date(0, 0, 0), new Date(115, 2, 17), "1", "Materia Prima", "1", new Long[]{(long) 0,(long) 1000000});
			consultaDao.consultarEjecucionEtapasProduccionV2(new Date(0, 0, 0), new Date(115, 2, 17), null, null, "", null);
			consultaDao.consultarEjecucionEtapasProduccionV2(new Date(0, 0, 0), new Date(115, 2, 17), "1", "Materia Prima", "1", new Long[]{(long) 0,(long) 1000000});
			
//			cosa.cambiarEstadoEstacionProduccion("2");
//			cosa.hacerSelect();
//			ArrayList<EstadoPedidoValue> meh = cosa.consultarEstadoPedidos("", new ArrayList<String>(), "", "", "", "", "", "", new ArrayList<String>());
//			for (int i = 0; i < meh.size(); i++) {
//				for (int j = 0; j < meh.get(i).getMateriales().size(); j++) {
//					System.out.println(meh.get(i).getMateriales().get(j).getCodigo());
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ArrayList<EstadoPedidoValue> consultarEstadoPedidos(String cliente, ArrayList<String> productos, String cantidadMinima, String cantidadMaxima, String costoMin, String costoMax, String fechaMin, String fechaMax, ArrayList<String> materiales) throws Exception{
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

		ArrayList<String> where = new ArrayList<String>();
		if(!cliente.isEmpty()){where.add(cliente);}

		if(!cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad between "+cantidadMinima+" and "+cantidadMaxima);}
		else if(!cantidadMinima.isEmpty() && cantidadMaxima.isEmpty()){where.add("cantidad > "+cantidadMinima);}
		else if(cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad < "+cantidadMaxima);}

		if(!fechaMin.isEmpty() && !fechaMax.isEmpty()){where.add("fechapedido between TO_DATE('"+fechaMin+"', 'DD-MM-YYYY') and TO_DATE('"+fechaMax+"', 'DD-MM-YYYY')");}
		else if(!fechaMin.isEmpty() && fechaMax.isEmpty()){where.add("fechapedido > TO_DATE('"+fechaMin+"', 'DD-MM-YYYY')");}
		else if(fechaMin.isEmpty() && !fechaMax.isEmpty()){where.add("fechapedido < TO_DATE('"+fechaMin+"', 'DD-MM-YYYY')");}

		if(!costoMin.isEmpty() && !costoMax.isEmpty()){where.add("costopedido between "+costoMin+" and "+costoMax);}
		else if(!costoMin.isEmpty() && costoMax.isEmpty()){where.add("costopedido > "+costoMin);}
		else if(costoMin.isEmpty() && !costoMax.isEmpty()){where.add("costopedido < "+costoMax);}

		ArrayList<String> order = new ArrayList<String>();
		order.add("codigopedido");

		//		String costos = "(select pedido.cantidad*producto.COSTO as costopedido from pedido inner join producto on producto.CODIGO=pedido.CODIGOPRODUCTO)";
		String tabla = "(select pedido.cantidad*producto.COSTO as costopedido, pedido.codigo from pedido   inner join producto  on producto.CODIGO=pedido.CODIGOPRODUCTO) c inner join PEDIDO on c.codigo=PEDIDO.CODIGO inner join (select codigomaterial, nombrematerial, tipo,  codigoproducto as codproducto, nombre from (select codigomaterial, nombrematerial, tipo, codigoproducto from (select codigomaterial, codigoetapa, nombrematerial, tipo from (select codigomaterial, codigoestacion, nombre as nombrematerial, tipo from requiere  inner join material  on material.CODIGO=requiere.CODIGOMATERIAL) inner join estacionproduccion on estacionproduccion.codigo=codigoestacion) inner join ETAPAPRODUCCION on ETAPAPRODUCCION.CODIGO=codigoetapa) inner join producto on codigoproducto=producto.codigo) on PEDIDO.CODIGO=codproducto inner join cliente on cliente.CODIGO=PEDIDO.CODIGOCLIENTE";
		
		String selectQuery = generateQuery(select, tabla, where, order, new ArrayList<String>());
		System.out.println(selectQuery);
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			statement.setQueryTimeout(5);
			ResultSet rs = statement.executeQuery();

			while(rs.next()){

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
			// TODO: handle exception
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
		if(producto.isEmpty()){where.add("producto='"+producto+"'");}
		
		if(!cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad between "+cantidadMinima+" and "+cantidadMaxima);}
		else if(!cantidadMinima.isEmpty() && cantidadMaxima.isEmpty()){where.add("cantidad > "+cantidadMinima);}
		else if(cantidadMinima.isEmpty() && !cantidadMaxima.isEmpty()){where.add("cantidad < "+cantidadMaxima);}
		
		if(!fechaMinima.isEmpty() && !fechaMaxima.isEmpty()){where.add("fechapedido between TO_DATE('"+fechaMinima+"', 'DD-MM-YYYY') and TO_DATE('"+fechaMaxima+"', 'DD-MM-YYYY')");}
		else if(!fechaMinima.isEmpty() && fechaMaxima.isEmpty()){where.add("fechapedido > TO_DATE('"+fechaMinima+"', 'DD-MM-YYYY')");}
		else if(fechaMinima.isEmpty() && !fechaMaxima.isEmpty()){where.add("fechapedido < TO_DATE('"+fechaMaxima+"', 'DD-MM-YYYY')");}
		
		ArrayList<String> order = new ArrayList<String>();
		order.add("cliente.codigo");
		
		String selectQuery = generateQuery(select, tabla, where, order, new ArrayList<String>());
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				Cliente vos = new Cliente();
			
				vos.setCiudad(rs.getString("ciudad"));
				vos.setCodigo(rs.getLong("codigocliente"));
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
				vos.setTipoIdLegal(rs.getInt("tipodilegal"));
				
				String codcli = rs.getString("codigo.cliente");
				ArrayList<Pedido> peds = new ArrayList<Pedido>();
				while(rs.getString("codigo.cliente").equals(codcli)){
					Pedido ped = new Pedido();
					ped.setCantidad(rs.getInt("cantidad"));
					ped.setEstado(rs.getInt("estado"));
					ped.setFechaEntrega(rs.getDate("fechapedido"));
					ped.setFechaEsperada(rs.getDate("fechaesperada"));
					ped.setFechaPedido(rs.getDate("fechapedido"));
					Producto prd = new Producto();
					prd.setNombre(rs.getString("producto"));
					prd.setCodigo(rs.getLong("codproducto"));
					ped.setProducto(prd);
					peds.add(ped);
					rs.next();
				}
				vos.setPedidos(peds);;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	
	public ArrayList<ProveedorValue> consultarProveedores() throws Exception{
		ArrayList<ProveedorValue> proveedores = new ArrayList<ProveedorValue>();
		PreparedStatement statement = null;
		
		String tabla = "(select nommaterial, tipo, MAXIMACANTIDAD, tiempoentrega, login, "
				+ "codprovee, codmat, tipoidlegal, identificacionlegal, nombrelegal, costo, "
				+ "cantidadpedida, fechaesperada, fechapedido from (select material.nombre as "
				+ "nommaterial, tipo, MAXIMACANTIDAD, tiempoentrega, login, proveedor.CODIGO as codprovee, "
				+ "material.codigo as codmat, tipoidlegal, identificacionlegal, nombrelegal "
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
		
		ArrayList<String> order = new ArrayList<String>();
		order.add("codproveedor");
		order.add("codprod");
		
		String selectQuery = generateQuery(select, tabla, where, order, new ArrayList<String>());
		try{
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = conexion.prepareStatement(selectQuery);
			statement.setQueryTimeout(1000);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				
			}
		} catch (Exception e) {
			// TODO: handle exception
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
//		selectQuery += " of CODIGO, CODIGOETAPA, ESTADO";
		PreparedStatement statement = null;
		EstacionProduccion estacionPrincipal = new EstacionProduccion();
		
		try {
			
			//Obtiene la estacion
			
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement = conexion.prepareStatement(selectQuery);
			System.out.println(selectQuery);
			
			ResultSet rs = statement.executeQuery();

			if(rs.next()){
				estacionPrincipal.setCodigo(rs.getLong("CODIGO"));
				estacionPrincipal.setEstado(rs.getString("ESTADO"));
				estacionPrincipal.setCodigoEtapaActual(rs.getLong("CODIGOETAPA"));
			}
//			System.out.println("encontro la 2");
			//Se valida el estado y dependiendo de eso se elege que accion tomar
			
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
				System.out.println(etapas.size() + " numero etapas");
				
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
				System.out.println(estacionesDeProduccionOpcionales);
				while(rs2.next())
				{
					System.out.println("llego aca");
					EstacionProduccion est = new EstacionProduccion();
					est.setCodigo(rs2.getLong("CODIGO"));
					est.setNumEtapaProduccion(rs2.getLong("CUENTA"));
					estacionesOpcionalesOrdenadas.add(est);
				}
				
				//Verifica que exista alguna extación, si no, cierra la conexión y retorna false
				
				if(estacionesOpcionalesOrdenadas.size()==0)
				{
//					statement.close();
//					closeConnection(conexion);
					return funciono;
				}
				
				
				//Reasigna las etapas de producción según el balanceo
				
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
				
				
				//Cambia las etapas de producción según el balanceo deceado
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
	// Generadores TODO
	//--------------------------------------------------------------

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

	
	private String generateQueryForUpdate2(ArrayList<String> select, String tabla, ArrayList<String> where, ArrayList<String> order, ArrayList<String> group){
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
		if(!where.isEmpty()){
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

	//FIXME
	private String generateDelete(){
		return "";
	}
	
	public ArrayList<Pedido> consultarPedidosV2(String tipoMaterial, long costo)
	{
		ArrayList<Pedido> resp = new ArrayList<Pedido>();
		try {
			establecerConexion(cadenaConexion, usuario, clave);
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
			System.out.println(solicitudPedidos);
			
			PreparedStatement statement = conexion.prepareStatement(solicitudPedidos);
			ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				Pedido temp = new Pedido();
				temp.setCodigo(resultados.getLong("ped.Codigo"));
				temp.setEstado(resultados.getInt("ped.Estado"));
				temp.setCantidad(resultados.getLong("ped.Cantidad"));
				temp.setFechaPedido(resultados.getDate("ped.FechaPedido"));
				temp.setFechaEsperada(resultados.getDate("ped.FechaEsperada"));
				temp.setFechaEntrega(resultados.getDate("ped.FechaEntrega"));
				Producto tempProd = new Producto();
				tempProd.setCodigo(resultados.getLong("ped.CodigoProducto"));
				Administrador tempAdmin = new Administrador();
				tempAdmin.setCodigo(resultados.getLong("ped.CodigoEmpresa"));
				Cliente tempClien = new Cliente();
				tempClien.setCodigo(resultados.getLong("ped.CodigoCliente"));
				temp.setProducto(tempProd);
				temp.setAdmin(tempAdmin);
				temp.setCliente(tempClien);
				resp.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
		
	}
}

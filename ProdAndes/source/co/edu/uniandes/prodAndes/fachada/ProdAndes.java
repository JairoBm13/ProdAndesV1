package co.edu.uniandes.prodAndes.fachada;

import java.util.ArrayList;

import co.edu.uniandes.prodAndes.dao.ConsultaDAO;
import co.edu.uniandes.prodAndes.vos.Administrador;
import co.edu.uniandes.prodAndes.vos.Cliente;
import co.edu.uniandes.prodAndes.vos.EstadoPedidoValue;
import co.edu.uniandes.prodAndes.vos.EtapaProduccion;
import co.edu.uniandes.prodAndes.vos.Material;
import co.edu.uniandes.prodAndes.vos.Operario;
import co.edu.uniandes.prodAndes.vos.Producto;
import co.edu.uniandes.prodAndes.vos.Proveedor;
import co.edu.uniandes.prodAndes.vos.ProveedorValue;
import co.edu.uniandes.prodAndes.vos.Usuario;

/**
 * Clase VideoAndes, que representa la fachada de comunicación entre
 * la interfaz y la conexión con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ProdAndes 
{

	private final static String ADMIN="sysadmin";
	/**
	 * Conexión con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;

	private int tipoUsuario;

	/**
	 * @return the tipoUsuario
	 */
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the adminVal
	 */
	public Administrador getAdminVal() {
		return adminVal;
	}

	/**
	 * @param adminVal the adminVal to set
	 */
	public void setAdminVal(Administrador adminVal) {
		this.adminVal = adminVal;
	}

	/**
	 * @return the clienteVal
	 */
	public Cliente getClienteVal() {
		return clienteVal;
	}

	/**
	 * @param clienteVal the clienteVal to set
	 */
	public void setClienteVal(Cliente clienteVal) {
		this.clienteVal = clienteVal;
	}

	/**
	 * @return the proveedorVal
	 */
	public Proveedor getProveedorVal() {
		return proveedorVal;
	}

	/**
	 * @param proveedorVal the proveedorVal to set
	 */
	public void setProveedorVal(Proveedor proveedorVal) {
		this.proveedorVal = proveedorVal;
	}

	/**
	 * @return the operarioVal
	 */
	public Operario getOperarioVal() {
		return operarioVal;
	}

	/**
	 * @param operarioVal the operarioVal to set
	 */
	public void setOperarioVal(Operario operarioVal) {
		this.operarioVal = operarioVal;
	}

	private Administrador adminVal;

	private Cliente clienteVal;

	private Proveedor proveedorVal;

	private Operario operarioVal;

	// -----------------------------------------------------------------
	// Singleton
	// -----------------------------------------------------------------


	/**
	 * Instancia única de la clase
	 */
	private static ProdAndes instancia;

	/**
	 * Devuelve la instancia única de la clase
	 * @return Instancia única de la clase
	 */
	public static ProdAndes darInstancia( )
	{
		if( instancia == null )
		{
			instancia = new ProdAndes( );
		}
		return instancia;
	}

	/**
	 * contructor de la clase. Inicializa el atributo dao.
	 */
	private ProdAndes()
	{
		dao = new ConsultaDAO();
		tipoUsuario = 0;
	}

	/**
	 * inicializa el dao, dándole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}

	// ---------------------------------------------------
	// Métodos asociados autenticación
	// ---------------------------------------------------

	/**
	 * 
	 * @param usu
	 * @param correo
	 * @param contrasenia
	 * @return
	 */
	public boolean login(String usu, String correo, String contrasenia){
		Object usuario = null;
		try {
			usuario = dao.inicioSesion(usu, correo, contrasenia);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(usuario==null) return false;
		else if(usuario.getClass().getSimpleName().equals("Administrador")){
			tipoUsuario=1;
			adminVal = (Administrador) usuario;
		}
		else if(usuario.getClass().getSimpleName().equals("Proveedor")){
			tipoUsuario=2;
			proveedorVal = (Proveedor) usuario;
		}
		else if(usuario.getClass().getSimpleName().equals("Cliente")){
			tipoUsuario=3;
			clienteVal = (Cliente) usuario;
		}
		else if(usuario.getClass().getSimpleName().equals("Operario")){
			tipoUsuario=4;
			operarioVal = (Operario) usuario;
		}
		return true;
	}

	/**
	 * 
	 */
	public void logout(){
		tipoUsuario=0;
		adminVal=null;
		clienteVal=null;
		operarioVal=null;
		proveedorVal=null;
	}

	//-------------------------------------------------------
	// Metodos asociados al registro de información
	//-------------------------------------------------------

	/**
	 * 
	 * @param codigo
	 * @param etapa
	 * @throws Exception
	 */
	public void registrarEjecucionEtapa(int codigo, int etapa) throws Exception{
		dao.registrarEjecucionEtapaDeProduccion(codigo, etapa);
	}

	/**
	 * 
	 * @param cantidad
	 * @param codigo
	 * @throws Exception
	 */
	public void registrarInsumos(double cantidad, int codigo) throws Exception {
		dao.registrarLlegadaDeInsumos(cantidad, codigo);
	}

	/**
	 * 
	 * @param pedido
	 * @throws Exception
	 */
	public void registrarPedidoCompletado(String pedido) throws Exception{
		dao.registrarEntregaPedido(pedido);
	}

	// ---------------------------------------------------
	// Métodos asociados a los casos de uso: Consulta
	// ---------------------------------------------------

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
	public ArrayList consultarExistencia(String tipo, String inventario, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception{
		return dao.consultarExistenciaDe(tipo, inventario, fechaEntrega, fechaSolicitud, ordenes, grupos);
	}

	public ArrayList consultarEtapaProduccion() throws Exception{
		return dao.seleccionarTodosLasEtapa();
	}

	public ArrayList<Material> darTodosMaterialesCodigoNombreTipo()
	{
		try {
			return dao.darTodosMaterialesCodigoNombreTipo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Material>();
		}
	}

	public ArrayList<Producto> darTodosProductosCodigoNombre()
	{
		try {
			return dao.darTodosProductosCodigoNombre();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Producto>();
		}
	}

	public void registrarCliente(String login, String pass, String idcli,
			String selTipoId, String nombre, String nacionalidad,
			String direccionElectronica, String ciudad, String departamento,
			String direccionFisica, String telefno, String codPostal,
			String nombrelegal, String id, String sinv, String tipoIdLegal) throws Exception {
		dao.registrarUsuario(login, direccionElectronica, pass, idcli, selTipoId, ciudad, nacionalidad, departamento, direccionFisica, telefno, codPostal);
		dao.registrarCliente(login, direccionElectronica, id, nombrelegal, sinv, tipoIdLegal);

	}

	public void registrarOperario(String login, String pass, String idcli,
			String selTipoId, String nombre, String nacionalidad,
			String direccionElectronica, String ciudad, String departamento,
			String direccionFisica, String telefno, String codPostal,
			String nombreo, String cargo) throws Exception {
		dao.registrarUsuario(login, direccionElectronica, pass, idcli, selTipoId, ciudad, nacionalidad, departamento, direccionFisica, telefno, codPostal);
		dao.registrarOperario(login, direccionElectronica, cargo, nombre);
	}

	public void registrarProveedor(String login, String pass, String idcli,
			String selTipoId, String nombre, String nacionalidad,
			String direccionElectronica, String ciudad, String departamento,
			String direccionFisica, String telefno, String codPostal,
			String nombrelegal, String id, String tipoIdLegal, String cantidad,
			String selMate, String tiempo) throws Exception {
		dao.registrarUsuario(login, direccionElectronica, pass, idcli, selTipoId, ciudad, nacionalidad, departamento, direccionFisica, telefno, codPostal);
		int codigoProv = dao.registrarProveedor(login, direccionElectronica, nombrelegal, id, tipoIdLegal);
		dao.registrarMaterialProvisto(codigoProv,selMate, cantidad, tiempo);
	}

	public void registrarMaterial(String nombre, String unidad,
			String cantidad, String tipo) throws Exception {
		dao.registrarMaterial(nombre, unidad, cantidad, tipo);		
	}

	public void registrarEstacion(String nombre, String etapa, String cantidad,
			String tiempo) throws Exception{
		dao.registrarEstacion(nombre, etapa, cantidad, tiempo);
	}

	public ArrayList<EtapaProduccion> consultarTodasLAsEtapas() throws Exception {
		return dao.consultarTodasLasEtapas();

	}

	/**
	 * 
	 * @param proveedor
	 * @param minCantidadEntrega
	 * @param maxCantidadEntrega
	 * @param minTiempo
	 * @param maxTiempo
	 * @param producto
	 * @param minCosto
	 * @param maxCosto
	 * @param minCantidad
	 * @param maxCantidad
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ProveedorValue> consultarProveedores(String proveedor, String minCantidadEntrega, String maxCantidadEntrega, String minTiempo, String maxTiempo, String producto, String minCosto, String maxCosto, String minCantidad, String maxCantidad) throws Exception
	{
		return dao.consultarProveedores(proveedor, minCantidadEntrega, maxCantidadEntrega, minTiempo, maxTiempo, producto, minCosto, maxCosto, minCantidad, maxCantidad);
	}

	/**
	 * 
	 * @param producto
	 * @param estado
	 * @param cantidadMinima
	 * @param cantidadMaxima
	 * @param fechaMinima
	 * @param fechaMaxima
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Cliente> consultarClientes(String producto, String estado, String cantidadMinima, String cantidadMaxima, String fechaMinima, String fechaMaxima ) throws Exception
	{
		return dao.consultarClientes(producto, estado, cantidadMinima, cantidadMaxima, fechaMinima, fechaMaxima);
	}

	/**
	 * 
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
	public ArrayList<EstadoPedidoValue> consultarEstadoPedidos(String cliente, ArrayList<String> productos, String cantidadMinima, String cantidadMaxima, String costoMin, String costoMax, String fechaMin, String fechaMax, ArrayList<String> materiales) throws Exception
	{
		return dao.consultarEstadoPedidos(cliente, productos, cantidadMinima, cantidadMaxima, costoMin, costoMax, fechaMin, fechaMax, materiales);
	}

	/**
	 * 
	 * @param idEstacionProduccion
	 * @return
	 * @throws Exception
	 */
	public boolean cambiarEstadoEstacionProduccion(String idEstacionProduccion) throws Exception
	{
		return dao.cambiarEstadoEstacionProduccion(idEstacionProduccion);
	}
}

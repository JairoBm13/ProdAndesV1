package co.edu.uniandes.prodAndes.vos;

import java.util.ArrayList;

public class EstadoPedidoValue {

	private String cliente;
	
	private String codigo;
	
	private String codigoCliente;
	
	private ArrayList<Producto> productos; 
	
	private String cantidad;
	
	private String costo;
	
	private String fechae;
	
	private String fechaes;
	
	private String fechap;
	
	private ArrayList<Material> materiales;
	
	private String nombreProducto;
	
	private String estado;
	
	public EstadoPedidoValue(){
		
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the productos
	 */
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the costo
	 */
	public String getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(String costo) {
		this.costo = costo;
	}

	/**
	 * @return the fechae
	 */
	public String getFechae() {
		return fechae;
	}

	/**
	 * @param fechae the fechae to set
	 */
	public void setFechae(String fechae) {
		this.fechae = fechae;
	}

	/**
	 * @return the fechaes
	 */
	public String getFechaes() {
		return fechaes;
	}

	/**
	 * @param fechaes the fechaes to set
	 */
	public void setFechaes(String fechaes) {
		this.fechaes = fechaes;
	}

	/**
	 * @return the fechap
	 */
	public String getFechap() {
		return fechap;
	}

	/**
	 * @param fechap the fechap to set
	 */
	public void setFechap(String fechap) {
		this.fechap = fechap;
	}

	/**
	 * @return the materiales
	 */
	public ArrayList<Material> getMateriales() {
		return materiales;
	}

	/**
	 * @param materiales the materiales to set
	 */
	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}

	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}

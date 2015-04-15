package co.edu.uniandes.prodAndes.vos;

import java.util.ArrayList;

public class EstacionProduccion {

	private long capacidad;
	
	private long codigo;
	
	private long tiempo;
	
	private long numEtapaProduccion;
	
	private ArrayList<Operario> operarios;
	
	private EtapaProduccion etapaProduccion;
	
	private ArrayList<EstacionProduccion> requiere;
	
	private long codigoEtapaActual;
	
	private String estado;
	
	public static String ESTADO_ACTIVO = "Activa";
	
	public static String ESTADO_INACTIVO = "Activa";
	
	public EstacionProduccion()
	{
		operarios = new ArrayList<Operario>();
		requiere = new ArrayList<EstacionProduccion>();
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	public ArrayList<Operario> getOperarios() {
		return operarios;
	}

	public void setOperarios(ArrayList<Operario> operarios) {
		this.operarios = operarios;
	}

	public EtapaProduccion getEtapaProduccion() {
		return etapaProduccion;
	}

	public void setEtapaProduccion(EtapaProduccion etapaProduccion) {
		this.etapaProduccion = etapaProduccion;
	}

	public ArrayList<EstacionProduccion> getRequiere() {
		return requiere;
	}

	public void setRequiere(ArrayList<EstacionProduccion> requiere) {
		this.requiere = requiere;
	}

	public long getNumEtapaProduccion() {
		return numEtapaProduccion;
	}

	public void setNumEtapaProduccion(long numEtapaProduccion) {
		this.numEtapaProduccion = numEtapaProduccion;
	}

	public long getCodigoEtapaActual() {
		return codigoEtapaActual;
	}

	public void setCodigoEtapaActual(long codigoEtapaActual) {
		this.codigoEtapaActual = codigoEtapaActual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	
	
	
}

package co.edu.uniandes.prodAndes.vos;

public class Etapa {

	private Proceso proceso;
	
	private EtapaProduccion etapaProduccion;
	
	public Etapa()
	{
		
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public EtapaProduccion getEtapaProduccion() {
		return etapaProduccion;
	}

	public void setEtapaProduccion(EtapaProduccion etapaProduccion) {
		this.etapaProduccion = etapaProduccion;
	}
	
	
}

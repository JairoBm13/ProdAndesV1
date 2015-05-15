
package co.edu.uniandes.prodAndes.dao;

public class PruebasDAONuevosRequerimientos {

	public static void main(String[] args) {
		try {
			ConsultaDAO consultaDao = new ConsultaDAO();
			consultaDao.consultarPedidosV2("Componente", "1500", 1);
			consultaDao.consultarMaterialesV2ParaPedidos("1",1);
			consultaDao.consultarEjecucionEtapasProduccionV1("2000/05/20", "2015/05/20", "", "", "", "", "", 1);
			consultaDao.consultarEjecucionEtapasProduccionV1("2000/05/20", "2015/05/20", "1", "Materia Prima", "1", "", "", 1);
			consultaDao.consultarEjecucionEtapasProduccionV2("2000/05/20", "20015/05/20", "", "" , "", "", "",1);
			consultaDao.consultarEjecucionEtapasProduccionV2("2000/05/20", "2015/05/20", "1", "Materia Prima", "1", "", "",1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}


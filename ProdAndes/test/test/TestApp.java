package test;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.sun.security.ntlm.Client;

import co.edu.uniandes.prodAndes.dao.ConsultaDAO;
import co.edu.uniandes.prodAndes.vos.Cliente;
import co.edu.uniandes.prodAndes.vos.EstacionProduccion;
import co.edu.uniandes.prodAndes.vos.EstadoPedidoValue;
import co.edu.uniandes.prodAndes.vos.Proveedor;
import co.edu.uniandes.prodAndes.vos.ProveedorValue;
import junit.framework.TestCase;

public class TestApp extends TestCase
{
	private TestDao dao;
	
	public TestApp()
	{
		dao = new TestDao();
		dao.inicializar();
	}
	
	public void testAgregarPK()
	{
		try 
		{
			dao.eliminarMaterial(10020);
			dao.agregarMaterialSinEliminar(1, 10020, "Materia Prima", "libras", "matera", new Date(System.currentTimeMillis()));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		
		try 
		{
			dao.agregarMaterialPrueba(20, 10020, "Componente", "libras", "litio", new Date(System.currentTimeMillis()));
			fail("Creo uno que no debia");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		
		try 
		{
			dao.eliminarMaterial(10020);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No pudo eliminar");
		}
	}
	
	public void testAgregarFK()
	{
		try 
		{
			dao.eliminarProceso(10020, 100000);
			dao.eliminarProducto(100000);
			dao.agregarProductoSinEliminar(100000, "TV", 10, "TV de prueba", 10,  5);
			dao.agregarProcesoSinEliminacion(10020, "proce1", 10, "prueba1", 100000,0);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		try 
		{
			dao.agregarProcesoSinEliminacion(10022, "proce1", 10, "prueba1", 150000,0);
			//fail("No debio crear este");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		try 
		{
			dao.eliminarProducto(100000);
			//dao.eliminarProceso(10020, 100000);
			//fail("Elimino un producto que no debia");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		try 
		{
			dao.eliminarProceso(10020, 100000);
			dao.eliminarProducto(100000);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No elimino las pruebas que debia eliminar");
		}
		
	}
	
	public void testAgregarCK()
	{
		try 
		{
			dao.eliminarMaterial(10020);
			dao.agregarMaterialPrueba(1, 10020, "Materia Prima", "libras", "matera", new Date(1,1,1));
			//TO_DATE('2015-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		
		try 
		{
			dao.agregarMaterialPrueba(-5, 10020, "lalalala", "libras", "litio", new Date(1,1,1));
			fail("Creo uno que no debia");
		}
		catch (SQLIntegrityConstraintViolationException e) 
		{
			//e.printStackTrace();
			
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
	}
	
	public void testNuevosRequerimientos()
	{
		try {
			ConsultaDAO elDao = new ConsultaDAO();
			
			elDao.cambiarEstadoEstacionProduccion("2");
//			elDao.cambiarEstadoEstacionProduccion("2");	
			
			System.out.println("Funciono cambiar estado de una estacion");
				ArrayList<EstadoPedidoValue> meh = elDao.consultarEstadoPedidos("","", "", "", "", "", "", "", "");
				for (int i = 0; i < meh.size(); i++) {
					for (int j = 0; j < meh.get(i).getMateriales().size(); j++) {
						System.out.println("Pedido de "+meh.get(i).getNombreProducto()+": .................material codigo: "+meh.get(i).getMateriales().get(j).getCodigo()+" de nombre: "+meh.get(i).getMateriales().get(j).getNombre());
					}
				}
				
			ArrayList<Proveedor> proveedores = elDao.consultarProveedores("", "", "", "", "", "", "", "", "", "");
			for(int i = 0; i< proveedores.size();i++)
			{
				System.out.println("Proveedor");
			}
			
			ArrayList<Cliente> clientes = elDao.consultarClientes("", "", "", "", "", "");
			for(int i=0; i<clientes.size();i++)
			{
				System.out.println("Entro for clientes");
				for(int j = 0;j<clientes.get(i).getPedidos().size();j++)
				{
					System.out.println("El cliente de nombre: "+clientes.get(i).getLogin()+" realizo los pedidos de codigo : "+clientes.get(i).getPedidos().get(j).getCodigo()+" cuyo nombre de producto es: "+clientes.get(i).getPedidos().get(j).getProducto().getNombre());
				}
			}
			
			ArrayList<EstacionProduccion> estaciones = elDao.darEstacionProduccionesConEtapas();
			for(int i = 0; i<estaciones.size();i++)
			{
				System.out.println("La estacion de codigo: "+estaciones.get(i).getCodigo()+" de nombre "+estaciones.get(i).getNombreEstacion()+" tiene "+estaciones.get(i).getNumEtapaProduccion()+" etapas en espera");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			fail("No funcionaron bien los nuevos requerimientos");
		}
	}
}

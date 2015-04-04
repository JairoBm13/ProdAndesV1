package co.edu.uniandes.prodAndes.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import co.edu.uniandes.prodAndes.dao.ConsultaDAO;
import co.edu.uniandes.prodAndes.fachada.ProdAndes;
import co.edu.uniandes.prodAndes.vos.Material;
import co.edu.uniandes.prodAndes.vos.Producto;


public class ServletConsultasGenerales extends ServletTemplate
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Calculadora para realizar las operaciones
     */
	
	private char dobleComilla = '"';
	
	private static final String direccionConsulta = "respuestaConsulta.html";
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------   

    /**
     * Formatea un valor numérico para presentar en la interfaz <br>
     * @param valor - El valor numérico a ser formateado
     * @return Retorna una cadena de caracteres con el valor formateado con puntos y signos.
     */
    private String formatearValor( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return " Consultas generales - ProdAndes";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter( );

        respuesta.write("  <from method=\"post\" action= \" "+direccionConsulta+" \">       ");
    	
    	respuesta.write("                    <div class=\"container\" style=\"background-color: white;border:3px solid\">");
    	respuesta.write("                   <font color=\"black\"> ");
    	respuesta.write("                   <table>");
    	respuesta.write("                   <tr>");
    	respuesta.write("                   <td>Consulta material</td>");
    	respuesta.write("                   </tr>");
    	respuesta.write("                   <tr>");
    	respuesta.write("                   ");
    	respuesta.write("                   <table width=\"100%\" border=\"0\" cellspacing=\"5\">");
    	respuesta.write("          <tr>");
    	respuesta.write("            <td><table width=\"100%\" border=\"0\" cellspacing=\"5\">");
    	respuesta.write("              <tr>");
    	respuesta.write("                <td ><label>Por identificador:</label></td>");
    	respuesta.write("                <td >");
    	respuesta.write("                  <input type=\"text\" name=\"materialID\" id=\"materialID\" /></td>");
    	respuesta.write("                  ");
    	respuesta.write("                  <td ><label>Seleccione:</label></td>");
    	respuesta.write("                  <td>");
    	respuesta.write("                  ");
    	respuesta.write("                  <select name=\"seleccionMaterialID\" id=\"seleccionMaterialID\">");
    	respuesta.write("   <option selected value=\"0\"> Elige una opción </option>");
    	
    	ProdAndes fachada = ProdAndes.darInstancia();
    	ArrayList<Material> materiales = fachada.darTodosMaterialesCodigoNombreTipo();
    	boolean cambio = false;
    	for(int i = 0; i<materiales.size();i++)
    	{
    		Material material = materiales.get(i);
    		if(!cambio)
    		{
    			if(material.getTipo().equals(ConsultaDAO.TIPO_MATERIAL_COMPONENTE))
    			{
    				if(i==0)
    					respuesta.write("       <optgroup label=\" "+ConsultaDAO.TIPO_MATERIAL_COMPONENTE+" \"> ");
    				respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
    			}
    			else
    			{
    				if(i>0)
    					respuesta.write("   </optgroup> ");
    				respuesta.write("       <optgroup label=\" "+ConsultaDAO.TIPO_MATERIAL_MATERIA_PRIMA+" \"> ");
    				respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
    				cambio=true;
    			}
    		}
    		else
    		{
    			respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
    		}
    		
    		if(i==materiales.size())
    			respuesta.write("   </optgroup> ");
    	}
    	
    	respuesta.write("</select>");
    	respuesta.write("                  ");
    	respuesta.write("                  </td>");
    	respuesta.write("                <td ><input type=\"submit\" name=\"botonMaterial\" id=\"botonMaterial\" value=\"Consultar\" /></td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("                   <td>Consulta Producto</td>");
    	respuesta.write("                   </tr>");
    	respuesta.write("                   <tr>");
    	respuesta.write("                   ");
    	respuesta.write("                   <table width=\"100%\" border=\"0\" cellspacing=\"5\">");
    	respuesta.write("          <tr>");
    	respuesta.write("            <td><table width=\"100%\" border=\"0\" cellspacing=\"5\">");
    	respuesta.write("              <tr>");
    	respuesta.write("                <td ><label>Por identificador:</label></td>");
    	respuesta.write("                <td >");
    	respuesta.write("                  <input type=\"text\" name=\"productoID\" id=\"productoID\" /></td>");
    	respuesta.write("                  ");
    	respuesta.write("                  <td ><label>Seleccione:</label></td>");
    	respuesta.write("                  <td>");
    	respuesta.write("                  ");
    	respuesta.write("                  <select name=\"seleccionProductoID\" id=\"seleccionProductoID\">");
    	respuesta.write("   <option selected value=\"0\"> Elige una opción </option>");
    	
    	ArrayList<Producto> productos = fachada.darTodosProductosCodigoNombre();
    	for(int i=0; i<productos.size();i++)
    	{
    		if(i==0)
    			respuesta.write("       <optgroup label=\"Productos\"> ");
    		Producto p = productos.get(i);
    		respuesta.write("       <option value=\""+p.getCodigo()+"\">"+p.getNombre()+"</option> ");
    		if(i==productos.size())
    			respuesta.write("   </optgroup> ");
    	}
    	
    	respuesta.write("</select>");
    	respuesta.write("                  ");
    	respuesta.write("                  </td>");
    	respuesta.write("                <td ><input type=\"submit\" name=\"botonProducto\" id=\"botonProducto\" value=\"Consultar\" /></td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Filtros</td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Rango Volumen:</td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Rango Inicio:</td>");
    	respuesta.write("              <td ><input type=\"text\" name=\"rangoInicio\" id=\"rangoInicio\" /></td>");
    	respuesta.write("              <td> Rango Fin:</td>");
    	respuesta.write("              <td ><input type=\"text\" name=\"volumenFin\" id=\"volumenFin\" /></td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Tipo Material:</td>");
    	respuesta.write("              <td ><input type=\"text\" name=\"tipoMaterial\" id=\"tipoMaterial\" /></td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Rango de Fecha:</td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td>Fecha Inicio:</td>     ");
    	respuesta.write("              <td><input type=\"date\" name=\"fechaInicio\" id=\"fechaInicio\"></td>     ");
    	respuesta.write("              <td>Fecha Fin:</td>     ");
    	respuesta.write("              <td><input type=\"date\" name=\"fechaFin\" id=\"fechaFin\"></td>     ");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Rango Precio:</td>");
    	respuesta.write("              </tr>");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              <tr>");
    	respuesta.write("              <td> Rango Inicio:</td>");
    	respuesta.write("              <td ><input type=\"text\" name=\"precioInicio\" id=\"precioInicio\" /></td>");
    	respuesta.write("              <td> Rango Fin:</td>");
    	respuesta.write("              <td ><input type=\"text\" name=\"precioFin\" id=\"precioFin\" /></td>");
    	respuesta.write("              </tr>");

    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("              ");
    	respuesta.write("            </table>");
    	respuesta.write("                   ");
    	respuesta.write("                   </tr>");
    	respuesta.write("                   ");
    	respuesta.write("                   ");
    	respuesta.write("                   </table>");
    	respuesta.write("                   </font>");
    	respuesta.write("                    </div>");
    	respuesta.write("                </div>");
    	respuesta.write("            </div>");
    	respuesta.write("");
    	respuesta.write("        </div>");
    	
    	respuesta.write("     </form>");
	}
}

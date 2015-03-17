package co.edu.uniandes.N1_I1.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.*;
import java.util.ArrayList;

import co.edu.uniandes.N1_I1.dao.ConsultaDAO;
import co.edu.uniandes.N1_I1.fachada.ProdAndes;
import co.edu.uniandes.N1_I1.vos.Material;
import co.edu.uniandes.N1_I1.vos.Producto;


public class CopyOfConsultasGenerales2 extends HttpServlet
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Calculadora para realizar las operaciones
     */
	
	private char dobleComilla = '"';
	
	private static final String direccionConsulta = "respuestaConsulta.html";
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicialización del Servlet
     */
    public void init( ) throws ServletException
    {

    }

    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarSolicitud( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    	// Envía a la respuesta el encabezado del template
		imprimirEncabezado( response );

		//Trabaja y envia el resultado
		imprimirResultadoRegistro(response);

		// Envía a la respuesta la parte inferior del template
		imprimirFooter( response);
    }

    
    
    /**
     * Imprime el resultado de la operación
     * @param operacion Operación realizada
     * @param v1 Valor 1 de la operación
     * @param v2 Valor 2 de la operación
     * @param resultado Resultado de la operación
     * @param response Respuesta al cliente
     * @throws IOException Excepción al obtener el flujo de escritura para escribir al cliente
     */
    private void imprimirResultadoRegistro( HttpServletResponse response) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
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
    
    
    /**
     * Imprime el encabezado con el diseño de la página
     * @param response Respuesta
     * @throws IOException Excepción al imprimir en el resultado
     */
    private void imprimirEncabezado( HttpServletResponse response ) throws IOException
    {
    	 //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el header
        respuesta.write("<!DOCTYPE html>");
        respuesta.write("<html lang=\"en\">");
        respuesta.write("");
        respuesta.write("<head>");
        respuesta.write("");
        respuesta.write("    <meta charset=\"utf-8\">");
        respuesta.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        respuesta.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        respuesta.write("    <meta name=\"description\" content=\"\">");
        respuesta.write("    <meta name=\"author\" content=\"\">");
        respuesta.write("");
        respuesta.write("    <title>Landing Page - Start Bootstrap Theme</title>");
        respuesta.write("");
        respuesta.write("    <!-- Bootstrap Core CSS -->");
        respuesta.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        respuesta.write("");
        respuesta.write("    <!-- Custom CSS -->");
        respuesta.write("    <link href=\"css/landing-page.css\" rel=\"stylesheet\">");
        respuesta.write("");
        respuesta.write("    <!-- Custom Fonts -->");
        respuesta.write("    <link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
        respuesta.write("    <link href=\"http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
        respuesta.write("");
        respuesta.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->");
        respuesta.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->");
        respuesta.write("    <!--[if lt IE 9]>");
        respuesta.write("        <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>");
        respuesta.write("        <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>");
        respuesta.write("    <![endif]-->");
        respuesta.write("");
        respuesta.write("</head>");
        respuesta.write("");
        respuesta.write("<body>");
        respuesta.write("");
        respuesta.write("    <!-- Navigation -->");
        respuesta.write("    <nav class=\"navbar navbar-default navbar-fixed-top topnav\" role=\"navigation\">");
        respuesta.write("        <div class=\"container topnav\">");
        respuesta.write("            <!-- Brand and toggle get grouped for better mobile display -->");
        respuesta.write("            <div class=\"navbar-header\">");
        respuesta.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
        respuesta.write("                    <span class=\"sr-only\">Toggle navigation</span>");
        respuesta.write("                    <span class=\"icon-bar\"></span>");
        respuesta.write("                    <span class=\"icon-bar\"></span>");
        respuesta.write("                    <span class=\"icon-bar\"></span>");
        respuesta.write("                </button>");
        respuesta.write("                <a class=\"navbar-brand topnav\" href=\"#\">Start Bootstrap</a>");
        respuesta.write("            </div>");
        respuesta.write("            <!-- Collect the nav links, forms, and other content for toggling -->");
        respuesta.write("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
        respuesta.write("                <ul class=\"nav navbar-nav navbar-right\">");
        respuesta.write("                    <li>");
        respuesta.write("                        <a href=\"#about\">About</a>");
        respuesta.write("                    </li>");
        respuesta.write("                    <li>");
        respuesta.write("                        <a href=\"#services\">Services</a>");
        respuesta.write("                    </li>");
        respuesta.write("                    <li>");
        respuesta.write("                        <a href=\"#contact\">Contact</a>");
        respuesta.write("                    </li>");
        respuesta.write("                </ul>");
        respuesta.write("            </div>");
        respuesta.write("            <!-- /.navbar-collapse -->");
        respuesta.write("        </div>");
        respuesta.write("        <!-- /.container -->");
        respuesta.write("    </nav>");
        respuesta.write("");
        respuesta.write("");
        respuesta.write("    <!-- Header -->");
        respuesta.write("    <a name=\"about\"></a>");
        respuesta.write("    <div class=\"intro-header\">");
        respuesta.write("        <div class=\"container\">");
        respuesta.write("");
        respuesta.write("            <div class=\"row\">");
        respuesta.write("                <div class=\"col-lg-12\">");
        respuesta.write("                    <div class=\"intro-message\">");
        respuesta.write("");
        respuesta.write("                        <h1>ProdAndes para Jugos</h1>");
        respuesta.write("                        ");
        respuesta.write("                        ");
        respuesta.write("                    </div>");
        
        
        
        
        
    }

    /**
     * Imprime la parte inferior del diseño de la página
     * @param response Respuesta
     * @param tieneResultado Especifica si debe incluir el resultado
     * @param resultado Resultado de la operación anterior
     * @throws IOException Excepción al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response) throws IOException
    {
    	// Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.write("        <!-- /.container -->");
        respuesta.write("");
        respuesta.write("    </div>");
        respuesta.write("    <!-- /.intro-header -->");
        respuesta.write("");
        respuesta.write("    <!-- Page Content -->");
        respuesta.write("");
        respuesta.write("	<a  name=\"services\"></a>");
        respuesta.write("");
        respuesta.write("");
        respuesta.write("	<a  name=\"contact\"></a");
        respuesta.write("");
        respuesta.write("    ><!-- Footer -->");
        respuesta.write("    <footer>");
        respuesta.write("        <div class=\"container\">");
        respuesta.write("            <div class=\"row\">");
        respuesta.write("                <div class=\"col-lg-12\">");
        respuesta.write("                    <ul class=\"list-inline\">");
        respuesta.write("                        <li>");
        respuesta.write("                            <a href=\"#\">inicio</a>");
        respuesta.write("                        </li>");
        respuesta.write("                    </ul>");
        respuesta.write("                    <p class=\"copyright text-muted small\">Copyright &copy; ProdAndes 2015. Todos los derechos reservados.</p>");
        respuesta.write("                </div>");
        respuesta.write("            </div>");
        respuesta.write("        </div>");
        respuesta.write("    </footer>");
        respuesta.write("");
        respuesta.write("    <!-- jQuery -->");
        respuesta.write("    <script src=\"js/jquery.js\"></script>");
        respuesta.write("");
        respuesta.write("    <!-- Bootstrap Core JavaScript -->");
        respuesta.write("    <script src=\"js/bootstrap.min.js\"></script>");
        respuesta.write("");
        respuesta.write("</body>");
        respuesta.write("");
        respuesta.write("</html>");
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param mensaje Mensaje del error
     */
    private void imprimirMensajeError( HttpServletResponse response ) throws IOException
    {
    	
    	// Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );
    	
        respuesta.println( "<!DOCTYPE html PUBLIC "+dobleComilla+"-//W3C//DTD XHTML 1.0 Transitional//EN"+dobleComilla+" "+ dobleComilla +"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"+dobleComilla+">" );
        respuesta.println( "<html xmlns="+dobleComilla+"http://www.w3.org/1999/xhtml"+dobleComilla+">" );
        respuesta.println( "<head>" );
        respuesta.println( "<meta http-equiv="+dobleComilla+"Content-Type"+dobleComilla+" content="+dobleComilla+"text/html; charset=utf-8"+dobleComilla+" />" );
        respuesta.println("<title>CupiFlights - Error</title>");
        respuesta.println("<style type="+dobleComilla+"text/css"+dobleComilla+">");
        respuesta.println("body {");
        respuesta.println("	background-image: url(imagenes/16cupi02.png);");
        respuesta.println("}");
        respuesta.println("</style>");
        respuesta.println("</head>");
        respuesta.println("<body topmargin="+dobleComilla+"+dobleComilla+"+dobleComilla+"0"+dobleComilla+">");
        respuesta.println("<table width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("<tr>");
        respuesta.println(" <td height="+dobleComilla+"20%"+dobleComilla+"><img src="+dobleComilla+"imagenes/16cupi01.png"+dobleComilla+" width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"268"+dobleComilla+" /></td>");
        respuesta.println("</tr>");
        respuesta.println("<tr>");
        respuesta.println("<td height="+dobleComilla+"80%"+dobleComilla+"><table width="+dobleComilla+"100%"+dobleComilla+" height="+dobleComilla+"513%"+dobleComilla+" border="+dobleComilla+"2"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("      <tr>");
        respuesta.println("     <td>Error al ingresar los datos, por favor vuelva a la pagina principal :(</td>");
        respuesta.println("      </tr>");
        respuesta.println("      <tr>");
        respuesta.println("  <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println(" <tr>");
        respuesta.println("     <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("   <tr>");
        //respuesta.println("   <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        	respuesta.println(" "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"indexUsuario.htm"+dobleComilla+">"+"  <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        respuesta.println("      <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonVolver"+dobleComilla+" id="+dobleComilla+"botonVolver"+dobleComilla+" value="+dobleComilla+"Pagina Principal"+dobleComilla+" />");
        //respuesta.println("             </div></td>");
        respuesta.println("             </div></td></form>");
        respuesta.println("        </tr>");
        respuesta.println("      </table></td>");
        respuesta.println("     </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("      </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("  </tr>");
        respuesta.println("</table>");
        respuesta.println("</body>");
        respuesta.println("</html>");
    	
    }

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
}

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ServletTemplate.java,v 1.0 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 1-Marzo-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.N1_I1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.N1_I1.fachada.ProdAndes;
import co.edu.uniandes.N1_I1.vos.Material;
import co.edu.uniandes.N1_I1.vos.Producto;


/**
 * Clase abstacta que implementa un Servlet.
 */
public abstract class ServletConsultasGenerales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String direccionConsulta = "respuestaConsulta.html";
       
    /**
     * constructor de la clase. Llama al constructor de 
     * su padre.
     */
    public ServletConsultasGenerales() {
        super();

    }

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los métodos get. Invoca el método procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepción de error al escribir la respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		procesarPedido(request, response);
	}

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los métodos post. Invoca el método procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepción de error al escribir la respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		procesarPedido(request, response);
	}
	
	/**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta del servlet
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    	//TODO Si hay otras fachadas, ellas tambien deben inicializar la ruta.
    	ProdAndes.darInstancia().inicializarRuta(this.getServletContext().getRealPath(((HttpServletRequest) this.getServletContext()).getContextPath()));
        //
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Escribe la cabecera de la página web
     * @param request pedido del cliente
     * @param response respuesta del servlet
     * @throws IOException Excepción de error al recibir la respuesta
     */
	private void imprimirHeader(HttpServletRequest request,HttpServletResponse response) throws IOException
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

	private void imprimirFooter(HttpServletResponse response) throws IOException
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
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operación nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param exception Excepción de error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Información:<br>" );
        exception.printStackTrace( respuesta );
        respuesta.println( "</p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operación nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
    }

    /**
     * Imprime un mensaje de éxito
     * @param respuesta Respuesta al cliente
     * @param titulo Título del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
    {
//    	respuesta.write("  <from method=\"post\" action= \" "+direccionConsulta+" \">       ");
//    	
//    	respuesta.write("                    <div class=\"container\" style=\"background-color: white;border:3px solid\">");
//    	respuesta.write("                   <font color=\"black\"> ");
//    	respuesta.write("                   <table>");
//    	respuesta.write("                   <tr>");
//    	respuesta.write("                   <td>Consulta material</td>");
//    	respuesta.write("                   </tr>");
//    	respuesta.write("                   <tr>");
//    	respuesta.write("                   ");
//    	respuesta.write("                   <table width=\"100%\" border=\"0\" cellspacing=\"5\">");
//    	respuesta.write("          <tr>");
//    	respuesta.write("            <td><table width=\"100%\" border=\"0\" cellspacing=\"5\">");
//    	respuesta.write("              <tr>");
//    	respuesta.write("                <td ><label>Por identificador:</label></td>");
//    	respuesta.write("                <td >");
//    	respuesta.write("                  <input type=\"text\" name=\"materialID\" id=\"materialID\" /></td>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  <td ><label>Seleccione:</label></td>");
//    	respuesta.write("                  <td>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  <select name=\"seleccionMaterialID\" id=\"seleccionMaterialID\">");
//    	respuesta.write("   <option selected value=\"0\"> Elige una opción </option>");
//    	
//    	ProdAndes_ConConexion fachada = ProdAndes_ConConexion.darInstancia();
//    	ArrayList<Material> materiales = fachada.darTodosMaterialesCodigoNombreTipo();
//    	boolean cambio = false;
//    	for(int i = 0; i<materiales.size();i++)
//    	{
//    		Material material = materiales.get(i);
//    		if(!cambio)
//    		{
//    			if(material.getTipo().equals(ConsultaDAO_SoloConexion.TIPO_MATERIAL_COMPONENTE))
//    			{
//    				if(i==0)
//    					respuesta.write("       <optgroup label=\" "+ConsultaDAO_SoloConexion.TIPO_MATERIAL_COMPONENTE+" \"> ");
//    				respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
//    			}
//    			else
//    			{
//    				if(i>0)
//    					respuesta.write("   </optgroup> ");
//    				respuesta.write("       <optgroup label=\" "+ConsultaDAO_SoloConexion.TIPO_MATERIAL_MATERIA_PRIMA+" \"> ");
//    				respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
//    				cambio=true;
//    			}
//    		}
//    		else
//    		{
//    			respuesta.write("       <option value=\""+material.getCodigo()+"\">"+material.getNombre()+"</option> ");
//    		}
//    		
//    		if(i==materiales.size())
//    			respuesta.write("   </optgroup> ");
//    	}
//    	
//    	respuesta.write("</select>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  </td>");
//    	respuesta.write("                <td ><input type=\"submit\" name=\"botonMaterial\" id=\"botonMaterial\" value=\"Consultar\" /></td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("                   <td>Consulta Producto</td>");
//    	respuesta.write("                   </tr>");
//    	respuesta.write("                   <tr>");
//    	respuesta.write("                   ");
//    	respuesta.write("                   <table width=\"100%\" border=\"0\" cellspacing=\"5\">");
//    	respuesta.write("          <tr>");
//    	respuesta.write("            <td><table width=\"100%\" border=\"0\" cellspacing=\"5\">");
//    	respuesta.write("              <tr>");
//    	respuesta.write("                <td ><label>Por identificador:</label></td>");
//    	respuesta.write("                <td >");
//    	respuesta.write("                  <input type=\"text\" name=\"productoID\" id=\"productoID\" /></td>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  <td ><label>Seleccione:</label></td>");
//    	respuesta.write("                  <td>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  <select name=\"seleccionProductoID\" id=\"seleccionProductoID\">");
//    	respuesta.write("   <option selected value=\"0\"> Elige una opción </option>");
//    	
//    	ArrayList<Producto> productos = fachada.darTodosProductosCodigoNombre();
//    	for(int i=0; i<productos.size();i++)
//    	{
//    		if(i==0)
//    			respuesta.write("       <optgroup label=\"Productos\"> ");
//    		Producto p = productos.get(i);
//    		respuesta.write("       <option value=\""+p.getCodigo()+"\">"+p.getNombre()+"</option> ");
//    		if(i==productos.size())
//    			respuesta.write("   </optgroup> ");
//    	}
//    	
//    	respuesta.write("</select>");
//    	respuesta.write("                  ");
//    	respuesta.write("                  </td>");
//    	respuesta.write("                <td ><input type=\"submit\" name=\"botonProducto\" id=\"botonProducto\" value=\"Consultar\" /></td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Filtros</td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Rango Volumen:</td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Rango Inicio:</td>");
//    	respuesta.write("              <td ><input type=\"text\" name=\"rangoInicio\" id=\"rangoInicio\" /></td>");
//    	respuesta.write("              <td> Rango Fin:</td>");
//    	respuesta.write("              <td ><input type=\"text\" name=\"volumenFin\" id=\"volumenFin\" /></td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Tipo Material:</td>");
//    	respuesta.write("              <td ><input type=\"text\" name=\"tipoMaterial\" id=\"tipoMaterial\" /></td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Rango de Fecha:</td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td>Fecha Inicio:</td>     ");
//    	respuesta.write("              <td><input type=\"date\" name=\"fechaInicio\" id=\"fechaInicio\"></td>     ");
//    	respuesta.write("              <td>Fecha Fin:</td>     ");
//    	respuesta.write("              <td><input type=\"date\" name=\"fechaFin\" id=\"fechaFin\"></td>     ");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Rango Precio:</td>");
//    	respuesta.write("              </tr>");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              <tr>");
//    	respuesta.write("              <td> Rango Inicio:</td>");
//    	respuesta.write("              <td ><input type=\"text\" name=\"precioInicio\" id=\"precioInicio\" /></td>");
//    	respuesta.write("              <td> Rango Fin:</td>");
//    	respuesta.write("              <td ><input type=\"text\" name=\"precioFin\" id=\"precioFin\" /></td>");
//    	respuesta.write("              </tr>");
//
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("              ");
//    	respuesta.write("            </table>");
//    	respuesta.write("                   ");
//    	respuesta.write("                   </tr>");
//    	respuesta.write("                   ");
//    	respuesta.write("                   ");
//    	respuesta.write("                   </table>");
//    	respuesta.write("                   </font>");
//    	respuesta.write("                    </div>");
//    	respuesta.write("                </div>");
//    	respuesta.write("            </div>");
//    	respuesta.write("");
//    	respuesta.write("        </div>");
//    	
//    	respuesta.write("     </form>");
    }

	
	// -----------------------------------------------------------------
    // Métodos Abstractos
    // -----------------------------------------------------------------

    /**
     * Devuelve el título de la página para el Header
     * @param request Pedido del cliente
     * @return Título de la página para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}

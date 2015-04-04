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
package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.fachada.ProdAndes;


/**
 * Clase abstacta que implementa un Servlet.
 */
public abstract class ServletTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected ProdAndes fachada;
    /**
     * constructor de la clase. Llama al constructor de 
     * su padre.
     */
    public ServletTemplate() {
        super();
        
    }
    
    public void init(){
    	fachada = ProdAndes.darInstancia();
    	fachada.inicializarRuta("");
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
        respuesta.println("<!doctype html>");
        respuesta.println("<!--[if IE 8 ]><html class=\"ie ie8\" lang=\"en\"> <![endif]-->");
        respuesta.println("<!--[if (gte IE 9)|!(IE)]><html lang=\"en\" class=\"no-js\"> <![endif]-->");
        respuesta.println("<html lang=\"en\">");
        respuesta.println("");
        respuesta.println("<head>");
        respuesta.println("");
        respuesta.println("    <!-- Basic -->");
        respuesta.println("    <title>Margo | Home</title>");
        respuesta.println("");
        respuesta.println("    <!-- Define Charset -->");
        respuesta.println("    <meta charset=\"utf-8\">");
        respuesta.println("");
        respuesta.println("    <!-- Responsive Metatag -->");
        respuesta.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">");
        respuesta.println("");
        respuesta.println("    <!-- Page Description and Author -->");
        respuesta.println("    <meta name=\"description\" content=\"Margo - Responsive HTML5 Template\">");
        respuesta.println("    <meta name=\"author\" content=\"iThemesLab\">");
        respuesta.println("");
        respuesta.println("    <!-- Bootstrap CSS  -->");
        respuesta.println("    <link rel=\"stylesheet\" href=\"asset/css/bootstrap.min.css\" type=\"text/css\" media=\"screen\">");
        respuesta.println("");
        respuesta.println("    <!-- Font Awesome CSS -->");
        respuesta.println("    <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\" type=\"text/css\" media=\"screen\">");
        respuesta.println("");
        respuesta.println("    <!-- Margo CSS Styles  -->");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\">");
        respuesta.println("");
        respuesta.println("    <!-- Responsive CSS Styles  -->");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\" media=\"screen\">");
        respuesta.println("");
        respuesta.println("    <!-- Css3 Transitions Styles  -->");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/animate.css\" media=\"screen\">");
        respuesta.println("");
        respuesta.println("    <!-- Color CSS Styles  -->");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/red.css\" title=\"red\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/jade.css\" title=\"jade\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/green.css\" title=\"green\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/blue.css\" title=\"blue\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/beige.css\" title=\"beige\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/cyan.css\" title=\"cyan\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/orange.css\" title=\"orange\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/peach.css\" title=\"peach\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/pink.css\" title=\"pink\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/purple.css\" title=\"purple\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/sky-blue.css\" title=\"sky-blue\" media=\"screen\" />");
        respuesta.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/colors/yellow.css\" title=\"yellow\" media=\"screen\" />");
        respuesta.println("");
        respuesta.println("");
        respuesta.println("    <!-- Margo JS  -->");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery-2.1.1.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.migrate.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/modernizrr.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"asset/js/bootstrap.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.fitvids.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/owl.carousel.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/nivo-lightbox.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.isotope.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.appear.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/count-to.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.textillate.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.lettering.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.easypiechart.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.nicescroll.min.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/jquery.parallax.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/mediaelement-and-player.js\"></script>");
        respuesta.println("    <script type=\"text/javascript\" src=\"js/script.js\"></script>");
        respuesta.println("");
        respuesta.println("    <!--[if IE 8]><script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script><![endif]-->");
        respuesta.println("    <!--[if lt IE 9]><script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script><![endif]-->");
        respuesta.println("");
        respuesta.println("</head>");
        respuesta.println("");
        respuesta.println("<body>");
        respuesta.println("");
        respuesta.println("    <!-- Full Body Container -->");
        respuesta.println("    <div id=\"container\">");
        respuesta.println("        ");
        respuesta.println("        ");
        respuesta.println("        <!-- Start Header Section --> ");
        respuesta.println("        <div class=\"hidden-header\"></div>");
        respuesta.println("        <header class=\"clearfix\">");
        respuesta.println("            ");
        respuesta.println("            <!-- Start Top Bar -->");
        respuesta.println("            <div class=\"top-bar\">");
        respuesta.println("                <div class=\"container\">");
        respuesta.println("                    <div class=\"row\">");
        respuesta.println("                        <div class=\"col-md-7\">");
        respuesta.println("                            <!-- Start Contact Info -->");
        respuesta.println("                            <ul class=\"contact-details\">");
        respuesta.println("                                <li><i class=\"fa fa-map-marker\"></i>Bogot&aacute;, CO");
        respuesta.println("                                </li>");
        respuesta.println("                            </ul>");
        respuesta.println("                            <!-- End Contact Info -->");
        respuesta.println("                        </div><!-- .col-md-6 -->");
        respuesta.println("                        <div class=\"col-md-5\">");
        respuesta.println("                            <!-- Start Social Links -->");
        respuesta.println("                            <ul class=\"social-list\">");
        respuesta.println("                                <li>");
        respuesta.println("                                	<a class=\"github itl-tooltip\" data-placement=\"bottom\" title=\"Githun\" href=\"\"><i class=\"fa fa-github-alt\">");
        respuesta.println("                                   </i></a>");
        respuesta.println("                                   </li>");
        respuesta.println("                                <li>");
        respuesta.println("                                    <a class=\"twitter itl-tooltip\" data-placement=\"bottom\" title=\"Twitter\" href=\"#\"><i class=\"fa fa-twitter\"></i></a>");
        respuesta.println("                                </li>");
        respuesta.println("                                <li>");
        respuesta.println("                                    <a class=\"google itl-tooltip\" data-placement=\"bottom\" title=\"Google Plus\" href=\"#\"><i class=\"fa fa-google-plus\"></i></a>");
        respuesta.println("                                </li>");
        respuesta.println("                            </ul>");
        respuesta.println("                            <!-- End Social Links -->");
        respuesta.println("                        </div><!-- .col-md-6 -->");
        respuesta.println("                    </div><!-- .row -->");
        respuesta.println("                </div><!-- .container -->");
        respuesta.println("            </div><!-- .top-bar -->");
        respuesta.println("            <!-- End Top Bar -->");
        respuesta.println("            ");
        respuesta.println("            ");
        respuesta.println("            <!-- Start  Logo & Naviagtion  -->");
        respuesta.println("            <div class=\"navbar navbar-default navbar-top\">");
        respuesta.println("                <div class=\"container\">");
        respuesta.println("                    <div class=\"navbar-header\">");
        respuesta.println("                        <!-- Stat Toggle Nav Link For Mobiles -->");
        respuesta.println("                        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">");
        respuesta.println("                            <i class=\"fa fa-bars\"></i>");
        respuesta.println("                        </button>");
        respuesta.println("                        <!-- End Toggle Nav Link For Mobiles -->");
        respuesta.println("                        <a class=\"navbar-brand\" href=\"index.html\">");
        respuesta.println("                            <!--<img alt=\"\" src=\"images/margo.png\">-->");
        respuesta.println("                            ProAndes");
        respuesta.println("                        </a>");
        respuesta.println("                    </div>");
        respuesta.println("                    <div class=\"navbar-collapse collapse\">");
        respuesta.println("                        <!-- Start Navigation List -->");
        respuesta.println("                        <ul class=\"nav navbar-nav navbar-right\">");
        respuesta.println("                            <li>");
        respuesta.println("                                <a class=\"active\" href=\"index.html\">Home</a>");
        respuesta.println("                                <ul class=\"dropdown\">");
        respuesta.println("                                <!-- Para hacer dropdowns -->                               ");
        respuesta.println("                                </ul>");
        respuesta.println("                            </li>");
        respuesta.println("                            <li>");
        respuesta.println("                                <a href=\"about.html\">Pages</a>");
        respuesta.println("                                <ul class=\"dropdown\">");
        respuesta.println("                                    <li><a href=\"about.html\">About</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"services.html\">Services</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"right-sidebar.html\">Right Sidebar</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"left-sidebar.html\">Left Sidebar</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"404.html\">404 Page</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                </ul>");
        respuesta.println("                            </li>");
        respuesta.println("                            <li>");
        respuesta.println("                                <a href=\"#\">Shortcodes</a>");
        respuesta.println("                                <ul class=\"dropdown\">");
        respuesta.println("                                    <li><a href=\"tabs.html\">Tabs</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"buttons.html\">Buttons</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"action-box.html\">Action Box</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"testimonials.html\">Testimonials</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"latest-posts.html\">Latest Posts</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"latest-projects.html\">Latest Projects</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"pricing.html\">Pricing Tables</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"animated-graphs.html\">Animated Graphs</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"accordion-toggles.html\">Accordion & Toggles</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                </ul>");
        respuesta.println("                            </li>");
        respuesta.println("                            <li>");
        respuesta.println("                                <a href=\"portfolio-3.html\">Portfolio</a>");
        respuesta.println("                                <ul class=\"dropdown\">");
        respuesta.println("                                    <li><a href=\"portfolio-2.html\">2 Columns</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"portfolio-3.html\">3 Columns</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"portfolio-4.html\">4 Columns</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"single-project.html\">Single Project</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                </ul>");
        respuesta.println("                            </li>");
        respuesta.println("                            <li>");
        respuesta.println("                                <a href=\"blog.html\">Blog</a>");
        respuesta.println("                                <ul class=\"dropdown\">");
        respuesta.println("                                    <li><a href=\"blog.html\">Blog - right Sidebar</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"blog-left-sidebar.html\">Blog - Left Sidebar</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                    <li><a href=\"single-post.html\">Blog Single Post</a>");
        respuesta.println("                                    </li>");
        respuesta.println("                                </ul>");
        respuesta.println("                            </li>");
        respuesta.println("                            <li><a href=\"contact.html\">Contact</a>");
        respuesta.println("                            </li>");
        respuesta.println("                        </ul>");
        respuesta.println("                        <!-- End Navigation List -->");
        respuesta.println("                    </div>");
        respuesta.println("                </div>");
        respuesta.println("            </div>");
        respuesta.println("            <!-- End Header Logo & Naviagtion -->");
        respuesta.println("            ");
        respuesta.println("        </header> ");
        respuesta.println("        <!-- End Header Section -->");

		
	}

	private void imprimirFooter(HttpServletResponse response) throws IOException
	{
		
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.print("<!-- Start Footer Section -->");
        respuesta.println("<footer>");
        respuesta.println("<!-- Start Copyright -->");
        respuesta.println("                <div class=\"copyright-section\">");
        respuesta.println("                    <div class=\"row\">");
        respuesta.println("                        <div class=\"col-md-6\">");
        respuesta.println("                            <p>&copy; 2014 ProdAndes -  Todos los derechos Reservados <a href=\"https://github.com/JairoBm13/ProdAndesV1\">ProdAnes</a> </p>");
        respuesta.println("                        </div><!-- .col-md-6 -->");
        respuesta.println("                        <div class=\"col-md-6\">");
        respuesta.println("                            <ul class=\"footer-nav\">");
        respuesta.println("                                <li><a href=\"je.bautista10@uniandes.edu.co\">Contacto</a>");
        respuesta.println("                                </li>");
        respuesta.println("                            </ul>");
        respuesta.println("                        </div><!-- .col-md-6 -->");
        respuesta.println("                    </div><!-- .row -->");
        respuesta.println("                </div>");
        respuesta.println("                <!-- End Copyright -->");
        respuesta.println("");
        respuesta.println("            </div>");
        respuesta.println("        </footer>");
        respuesta.println("        <!-- End Footer Section -->");
        respuesta.println("        ");
        respuesta.println("        ");
        respuesta.println("    </div>");
        respuesta.println("    <!-- End Full Body Container -->");
        respuesta.println("");
        respuesta.println("    <!-- Go To Top Link -->");
        respuesta.println("    <a href=\"#\" class=\"back-to-top\"><i class=\"fa fa-angle-up\"></i></a>");
        respuesta.println("");
        respuesta.println("    <div id=\"loader\">");
        respuesta.println("        <div class=\"spinner\">");
        respuesta.println("            <div class=\"dot1\"></div>");
        respuesta.println("            <div class=\"dot2\"></div>");
        respuesta.println("        </div>");
        respuesta.println("    </div>");
        respuesta.println("");
        respuesta.println("    ");
        respuesta.println("");
        respuesta.println("</body>");
        respuesta.println("");
        respuesta.println("</html>");	
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
        respuesta.println( "                      <p class=\"ok\"><b>Operación exitosa:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
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

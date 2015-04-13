package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRegistroEtapa extends ServletTemplate{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter  respuesta = response.getWriter();
		String registrado = request.getParameter("registrado");
		int usuario = fachada.getTipoUsuario();
		if(usuario==1 || usuario==3 && fachada.getOperarioVal().isGerente()){
			if(registrado==null){
				respuesta.println("<!-- Start Page Banner -->");
				respuesta.println("		<div class=\"page-banner\" style=\"padding:40px 0; background: url(images/slide-02-bg.jpg) center #f9f9f9;\">");
				respuesta.println("			<div class=\"container\">");
				respuesta.println("				<div class=\"row\">");
				respuesta.println("					<div class=\"col-md-6\">");
				respuesta.println("						<h2>Registro de cliente</h2>");
				respuesta.println("						<p>Registro de un cliente, interesado en adquirir productos de la empresa</p>");
				respuesta.println("					</div>");
				respuesta.println("					<div class=\"col-md-6\">");
				respuesta.println("						<ul class=\"breadcrumbs\">");
				respuesta.println("							<li><a href=\"index.html\">Inicio</a></li>");
				respuesta.println("							<li><a href=\"menu.html\">Menu</a></li>");
				respuesta.println("                            <li>Registro de cliente</li>");
				respuesta.println("						</ul>");
				respuesta.println("					</div>");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("		</div>");
				respuesta.println("		<!-- End Page Banner -->");
				respuesta.println("<!-- Start Content -->");
				respuesta.println("		<div id=\"content\">");
				respuesta.println("			<div class=\"container\">");
				respuesta.println("				<div class=\"page-content\">");
				respuesta.println("					");
				respuesta.println("					");
				respuesta.println("					<div class=\"row\">");
				respuesta.println("						");
				respuesta.println("						<div class=\"col-md-7\">");
				respuesta.println("							");
				respuesta.println("							<!-- Classic Heading -->");
				respuesta.println("							<h4 class=\"classic-title\"><span>Registro de cliente</span></h4>");
				respuesta.println("							");
				respuesta.println("							<!-- Some Text -->");
				respuesta.println("							");
				respuesta.println("							<p>                             ");
				respuesta.println("<form action=\"registroEtapa.html\" method=\"post\" id=\"formRegistroEtapa\">");
				respuesta.println("                                	<input type=\"hidden\" name=\"registrado\" value=\"registrado\">");
				respuesta.println("                                    <label for=\"nombre\">Nombre:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"text\" name=\"nombre\" id=\"nombre\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"etapa\">Etapa:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"number\" name=\"etapa\" id=\"etapa\" class=\"form-control\" step=\"1\" min=\"1\"required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"fechaInic\">Fecha Inicio:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"date\" name=\"fechaInic\" id=\"fechaInic\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"fechaFin\">Fecha fin: </label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"date\" name=\"fechaFin\" id=\"fechaFin\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"tiempo\">Tiempo de Ejecuci&Oacute;n:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"number\" name=\"tiempo\" id=\"tiempo\" step=\"0.01\" min=\"0\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"descripcion\">Descripci&Oacute;n: </label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"text\" name=\"descripcion\" id=\"descripcion\" class=\"form-control\"  maxlength=\"50\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"producto\">Producto: </label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<select name=\"producto\" id=\"producto\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <!-- cosas -->");
				respuesta.println("                                    </select>");
				respuesta.println("                                    <label for=\"cantidad\">Cantidad de producto: </label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"number\" name=\"cantidad\" id=\"cantidad\" class=\"form-control\" required step=\"1\" min=\"0\">");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                </form>");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("						<div class=\"col-md-5\"> ");
				respuesta.println("							</div>");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<div class=\"row\">");
				respuesta.println("						");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>					");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("		</div>");
				respuesta.println("		<!-- End content -->");
			}
			else{
				String nombre = request.getParameter("nombre");
				String etapa = request.getParameter("selEtapa");
				String cantidad = request.getParameter("cantidad");
				String tiempo = request.getParameter("tiempo");
				try {
					fachada.registrarEstacion(nombre, etapa, cantidad, tiempo);
					respuesta.println("<!-- Start Page Banner -->");
					respuesta.println("		<div class=\"page-banner\" style=\"padding:40px 0; background: url(images/slide-02-bg.jpg) center #f9f9f9;\">");
					respuesta.println("			<div class=\"container\">");
					respuesta.println("				<div class=\"row\">");
					respuesta.println("					<div class=\"col-md-6\">");
					respuesta.println("						<h2>Registro de cliente</h2>");
					respuesta.println("						<p>Registro de un cliente, interesado en adquirir productos de la empresa</p>");
					respuesta.println("					</div>");
					respuesta.println("					<div class=\"col-md-6\">");
					respuesta.println("						<ul class=\"breadcrumbs\">");
					respuesta.println("							<li><a href=\"index.html\">Inicio</a></li>");
					respuesta.println("							<li><a href=\"menul.html\">Menu</a></li>");
					respuesta.println("                            <li>Registro de cliente</li>");
					respuesta.println("						</ul>");
					respuesta.println("					</div>");
					respuesta.println("				</div>");
					respuesta.println("			</div>");
					respuesta.println("		</div>");
					respuesta.println("		<!-- End Page Banner -->");
					respuesta.println("");
					respuesta.println("<!-- Start Content -->");
					respuesta.println("		<div id=\"content\">");
					respuesta.println("			<div class=\"container\">");
					respuesta.println("				<div class=\"page-content\">");
					respuesta.println("					");
					respuesta.println("					");
					respuesta.println("						");
					respuesta.println("						<div class=\"col-md-7\">");
					respuesta.println("							");
					respuesta.println("							<!-- Classic Heading -->");
					respuesta.println("							<h4 class=\"classic-title\"><span>Registro de usuario</span></h4>");
					respuesta.println("							");
					respuesta.println("							<!-- Some Text -->");
					respuesta.println("							El cliente fue registrado satisfactoriamente");
					respuesta.println("										");
					respuesta.println("						<div class=\"col-md-5\"> ");
					respuesta.println("							");
					respuesta.println("				</div>");
					respuesta.println("						</div>");
					respuesta.println("				");
					respuesta.println("					");
					respuesta.println("					");
					respuesta.println("				</div>");
					respuesta.println("			</div>");
					respuesta.println("		</div>");
					respuesta.println("		<!-- End content -->");

				} catch (Exception e) {
					//TODO

					respuesta.println("<!-- Start Content -->");
					respuesta.println("		<div id=\"content\">");
					respuesta.println("			<div class=\"container\">");
					respuesta.println("				<div class=\"page-content\">");
					respuesta.println("					");
					respuesta.println("					");
					respuesta.println("					<div class=\"error-page\">");
					respuesta.println("						<h1>505</h1>");
					respuesta.println("						<h3>Hubo un problema</h3>");
					respuesta.println("						<p>No se pudo registrar el usuario</p>");
					respuesta.println("						<div class=\"text-center\"><a href=\"index.html\" class=\"btn-system btn-small\">A inicio</a></div>");
					respuesta.println("					</div>");
					respuesta.println("					");
					respuesta.println("");
					respuesta.println("				</div>");
					respuesta.println("			</div>");
					respuesta.println("		</div>");
					respuesta.println("		<!-- End Content -->");
					e.printStackTrace();
				}
			}
		}
		else if(usuario==0){
			respuesta.println("<!-- Start Content -->");
			respuesta.println("		<div id=\"content\">");
			respuesta.println("			<div class=\"container\">");
			respuesta.println("				<div class=\"page-content\">");
			respuesta.println("					");
			respuesta.println("					");
			respuesta.println("					<div class=\"error-page\">");
			respuesta.println("						<h1>403</h1>");
			respuesta.println("						<h3>Hubo un problema</h3>");
			respuesta.println("						<p>No tienes los permisos para realizar esta acci&oacute;n, autentiquese por favor</p>");
			respuesta.println("						<div class=\"text-center\"><a href=\"login.html\" class=\"btn-system btn-small\">A inicio</a></div>");
			respuesta.println("					</div>");
			respuesta.println("					");
			respuesta.println("");
			respuesta.println("				</div>");
			respuesta.println("			</div>");
			respuesta.println("		</div>");
			respuesta.println("		<!-- End Content -->");

		}
		else{
			respuesta.println("<!-- Start Content -->");
			respuesta.println("		<div id=\"content\">");
			respuesta.println("			<div class=\"container\">");
			respuesta.println("				<div class=\"page-content\">");
			respuesta.println("					");
			respuesta.println("					");
			respuesta.println("					<div class=\"error-page\">");
			respuesta.println("						<h1>403</h1>");
			respuesta.println("						<h3>Hubo un problema</h3>");
			respuesta.println("						<p>No tienes los permisos para realizar esta acci&oacute;n</p>");
			respuesta.println("						<div class=\"text-center\"><a href=\"menu.html\" class=\"btn-system btn-small\">A inicio</a></div>");
			respuesta.println("					</div>");
			respuesta.println("					");
			respuesta.println("");
			respuesta.println("				</div>");
			respuesta.println("			</div>");
			respuesta.println("		</div>");
			respuesta.println("		<!-- End Content -->");
		}
	}

	private String escribirProductos(){
		return "";
	}
}

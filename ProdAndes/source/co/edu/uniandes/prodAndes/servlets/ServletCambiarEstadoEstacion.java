package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.vos.EstacionProduccion;

public class ServletCambiarEstadoEstacion extends ServletTemplate{

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
		if(usuario==1){
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
				respuesta.println("                            	<form action=\"registroCliente.html\" method=\"post\" id=\"formRegistroCliente\">");
				respuesta.println("                                    <input type=\"hidden\" name=\"registrado\" value=\"registrado\">");
				respuesta.println("                                    <label for=\"login\">Estacion:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<select name=\"login\" id=\"login\" class=\"form-control\" required>");
				respuesta.println(imprimirEstaciones());
				respuesta.println("                                      </select>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("");
				respuesta.println("                                    <input type=\"submit\" value=\"Registrar\">");
				respuesta.println("                             ");
				respuesta.println("                                </form>");
				respuesta.println("						</div>");
				respuesta.println(imprimirTabla());
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
				String login = request.getParameter("login");
				try {
					fachada.cambiarEstadoEstacionProduccion(login);
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
					respuesta.println("                            	<form action=\"registroCliente.html\" method=\"post\" id=\"formRegistroCliente\">");
					respuesta.println("                                    <input type=\"hidden\" name=\"registrado\" value=\"registrado\">");
					respuesta.println("                                    <label for=\"login\">Estacion:</label>");
					respuesta.println("                                    <div class=\"input-group\">");
					respuesta.println("                                    	<select name=\"login\" id=\"login\" class=\"form-control\" required>");
					respuesta.println(imprimirEstaciones());
					respuesta.println("                                      </select>");
					respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
					respuesta.println("                   					</div>");
					respuesta.println("");
					respuesta.println("                                    <input type=\"submit\" value=\"Registrar\">");
					respuesta.println("                             ");
					respuesta.println("                                </form>");
					respuesta.println("						</div>");
					respuesta.println(imprimirTabla());
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
			respuesta.println("						<p>No tienes los permisos para realizar esta acci&oacute;nr</p>");
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

	private String imprimirEstaciones() {
		String option = "";
		ArrayList<EstacionProduccion> estaciones;
		try {
			estaciones = fachada.darEstaciones();
			for (int i = 0; i < estaciones.size(); i++) {
				EstacionProduccion actual = estaciones.get(i);
				option += "<option value\""+actual.getCodigo()+"\">"+actual.getNombreEstacion()+"</option>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return option;
	}
	
	private String imprimirTabla(){
		String tabla = "<table>";
		tabla += "<tr><td>Nombre</td><td>Codigo</td><td>Capacidad</td><td>Estado</td><td>Codigo etapa actual</td><td>Etapa actual</td><td>En espera</td></tr>";
		ArrayList<EstacionProduccion> estaciones;
		try {
			estaciones = fachada.darEstacionesConEtapa();
			for (int i = 0; i < estaciones.size(); i++) {
				EstacionProduccion act = estaciones.get(i);
				tabla += "<tr>"
						+"<td>"+act.getNombreEstacion()+"</td>"
						+"<td>"+act.getCodigo()+"</td>"
						+"<td>"+act.getCapacidad()+"</td>"
						+"<td>"+act.getEstado()+"</td>"
						+"<td>"+act.getCodigoEtapaActual() == null ? "Sin etapa": act.getNombreEtapaActual()+"</td>"
								+"<td>"+act.getNombreEtapaActual() == null ? "Sin etapa": act.getNombreEtapaActual()+"</td>"
										+"<td>"+act.getNumEtapaProduccion()+"</td>"
										+ "</tr>";
			}
			tabla += "</tabla>";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tabla;
	}
}

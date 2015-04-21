package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.vos.Operario;

public class ServletConsultarEstadoPedidos extends ServletTemplate{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Consultar estado pedidos | ProdAndes";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter respuesta = response.getWriter();
		String registrado = request.getParameter("registrado");
		int usuario = fachada.getTipoUsuario();
		Operario user = fachada.getOperarioVal();
		if(usuario==1 || (usuario==3 && user.isGerente())){
			if(registrado == null){
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
				respuesta.println("							<h4 class=\"classic-title\"><span>Registro de usuario</span></h4>");
				respuesta.println("							");
				respuesta.println("							<!-- Some Text -->");
				respuesta.println("							");
				respuesta.println("");
				respuesta.println("                                <form  action=\"consultarEstadoPedido.html\">");
				respuesta.println("                                <input type=\"hidden\" name=\"registrado\" value=\"registrado\"><br>");
				respuesta.println("                                <label for=\"selpedido\">Pedidos:</label>           <div class=\"input-group\">");
				respuesta.println("                                <select id=\"selpedido\"  name=\"selpedido\" class=\"form-control\" required><br>");
				respuesta.println("<!--pedidos-->                                </select>        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("<label for=\"selcliente\">Clientes:</label>           <div class=\"input-group\">");
				respuesta.println("                                   	<select id=\"selpedido\"  name=\"selpedido\" class=\"form-control\" required>");
				respuesta.println("<!--clientes-->                                </select>        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("<label for=\"selproducto\">Producto:</label>           <div class=\"input-group\">");
				respuesta.println("                                   	<select id=\"selproducto\"  name=\"selproducto\" class=\"form-control\" required>");
				respuesta.println("<!--clientes-->                                </select>        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("<label for=\"selmateriales\">Materiales:</label>           <div class=\"input-group\">");
				respuesta.println("                                   	<select id=\"selmateriales\"  name=\"selmateriales\" class=\"form-control\" required>");
				respuesta.println("<!--clientes-->                                </select>        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("<label for=\"cantprodmin\">Cantidad mínima de producto:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"1\" required id=\"cantprodmin\" name=\"cantprodmin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"cantpromax\">Cantidad máxima de producto:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"1\" required id=\"cantprodmax\" name=\"cantprodmax\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"cantmatmin\">Cantidad mínima de material:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"0.01\" required id=\"cantmatmin\" name=\"cantmatmin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"cantmatmax\">Cantidad máxima de material:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"0.01\" required id=\"cantmatmax\" name=\"cantmatmax\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"datemin\">Fecha mínima:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"date\" min=\"2015-01-01\" required id=\"datemin\" name=\"datemin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"datemax\">Fecha máxima:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"date\" min=\"2015-01-01\" required id=\"datemax\" name=\"datemax\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<input type=\"submit\" value=\"Consultar\">");
				respuesta.println("                                </form>				");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("						<div class=\"col-md-5\"> ");
				respuesta.println("							");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<div class=\"row>	");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("		</div>");
				respuesta.println("<!-- End content -->");
			}
			else{
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
				respuesta.println("							<h4 class=\"classic-title\"><span>Registro de materia prima</span></h4>");
				respuesta.println("							");
				respuesta.println("							<!-- Some Text -->");
				respuesta.println("							");
				respuesta.println("                               Materia prima registrada exitosamente");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("						<div class=\"col-md-5\"> ");
				respuesta.println("							");
				respuesta.println("						</div>");
				respuesta.println("						");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<div class=\"row\">");
				respuesta.println("						");
				respuesta.println("						");
				respuesta.println("						");
				respuesta.println("						");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>					");
				respuesta.println("");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("		</div>");
				respuesta.println("		<!-- End content -->");
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
}

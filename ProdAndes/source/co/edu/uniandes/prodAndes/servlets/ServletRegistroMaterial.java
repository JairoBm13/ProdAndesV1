package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.vos.Operario;
import co.edu.uniandes.prodAndes.vos.Proveedor;

public class ServletRegistroMaterial extends ServletTemplate{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Registro de materiales | ProdAndes";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		// TODO Auto-generated method stub
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
				respuesta.println("							<h4 class=\"classic-title\"><span>Registro de materia prima</span></h4>");
				respuesta.println("							");
				respuesta.println("							<!-- Some Text -->");
				respuesta.println("							");
				respuesta.println("							<p>                                                             ");
				respuesta.println("                                <form action=\"registroMaterial.html\" method=\"post\" id=\"formRegistroCliente\">");
				respuesta.println("                                	<input type=\"hidden\" name=\"registrado\" value=\"registrado\">");
				respuesta.println("                                    <label for=\"nombreMaterial\">Material:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"text\" name=\"nombreMaterial\" id=\"nombreMaterial\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"unidad\">Unidad de medida:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"text\" name=\"unidad\" id=\"unidad\" class=\"form-control\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <label for=\"cantidad\">Cantidad:</label>");
				respuesta.println("                                    <div class=\"input-group\">");
				respuesta.println("                                    	<input type=\"number\" id=\"cantidad\" name=\"cantidad\" min=\"0\" step=\"0.0001\" required>");
				respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("                   					</div>");
				respuesta.println("                                    <input type=\"submit\" value=\"Registrar Material\">");
				respuesta.println("                                </form>		");
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

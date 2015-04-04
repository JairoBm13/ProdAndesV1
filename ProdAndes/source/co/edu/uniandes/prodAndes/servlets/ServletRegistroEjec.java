package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRegistroEjec extends ServletTemplate {

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

		PrintWriter respuesta = response.getWriter();
		respuesta.println("    <h3>Registrar ejecución <small> Registrar la ejecución de una etapa de producción.</small></h3>");
		respuesta.println("</div>");
		respuesta.println("");
		respuesta.println("<!-- Registration form - START -->");
		respuesta.println("<div class=\"container\">");
		respuesta.println("    <div class=\"row\">");
		respuesta.println("        <form role=\"form\">");
		respuesta.println("            <div class=\"col-lg-6\">");
		respuesta.println("                <div class=\"well well-sm\"><strong><span class=\"glyphicon glyphicon-asterisk\"></span> Campo obligatorio</strong></div>");
		respuesta.println("                <div class=\"form-group\">");
		respuesta.println("                    <label for=\"etapa\">Seleccione la etapa</label>");
		respuesta.println("                    <div class=\"input-group\">");
		respuesta.println("                        <select name='etapa'>");
		respuesta.println("                        ;rssddsfsd");
		respuesta.println("                        </select>");
		respuesta.println("                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
		respuesta.println("                    </div>");
		respuesta.println("                </div>");
		respuesta.println("                <div class=\"form-group\">");
		respuesta.println("                    <label for=\"InputEtapa\">Ingrese la etapa</label>");
		respuesta.println("                    <div class=\"input-group\">");
		respuesta.println("                        <input type=\"email\" class=\"form-control\" id=\"InputEmailFirst\" name=\"InputEmail\" placeholder=\"Enter Email\" required>");
		respuesta.println("                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
		respuesta.println("                    </div>");
		respuesta.println("                </div>");
		respuesta.println("                <input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Submit\" class=\"btn btn-info pull-right\">");
		respuesta.println("            </div>");
		respuesta.println("        </form>");
		respuesta.println("    </div>");
		respuesta.println("</div>");
		respuesta.println("<!-- Registration form - END -->");
		
	}

}

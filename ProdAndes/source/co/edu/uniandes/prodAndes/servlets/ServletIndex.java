package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.fachada.ProdAndes;

public class ServletIndex extends ServletTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(){
		fachada = ProdAndes.darInstancia();
	}
	
	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "ProdAndes Inicio";
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
		respuesta.println("<P><a href=\"login.html\">login</p>");
//		respuesta.println("<form action=\"prueba.html\">");
//		respuesta.println("<select id=\"cosas\" name=\"cosas\" multiple>");
//		respuesta.println("<option value=\"cosa1\">cosa1</option>");
//		respuesta.println("<option value=\"cosa2\">cosa2</option>");
//		respuesta.println("</select>");
//		respuesta.println("<input type=\"submit\" value=\"enviar\">");
//		respuesta.println("</form>");
		

	}

}

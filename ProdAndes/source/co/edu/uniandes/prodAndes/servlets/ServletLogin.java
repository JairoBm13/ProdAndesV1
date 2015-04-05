package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogin extends ServletTemplate {

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Ingreso de usuario | ProdAndes";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		int vaLog = fachada.getTipoUsuario();
		String login = request.getParameter("login");
		
		if(login==null){

			if(vaLog==0){
				out.println("<div class=\"container\">");
				out.println("		<br><br><br>");
				out.println("    <div class=\"row colored\">");
				out.println("        <div id=\"contentdiv\" class=\"contcustom\">");
				out.println("            <span class=\"fa fa-spinner bigicon\"></span>");
				out.println("            <h2>Login</h2>");
				out.println("            <div><form method=\"post\">");
				out.println("                <input id=\"username\" type=\"text\" placeholder=\"e-mail\" name=\"login\">");
				out.println("                <input id=\"password\" type=\"password\" placeholder=\"clave\" name=\"pass\">");
				out.println("				<button type=\"submit\" class=\"btn btn-default\" >Login</button>");
				out.println("				</form>");
				out.println("            </div>");
				out.println("        </div>");
				out.println("    </div>");
				out.println("</div>");

			}

			else{
				out.println("	<div class=\"well well-lg\">");
				out.println("		<div class=\"container\">");
				out.println("		<br><br><br>");
				out.println("			<div class=\"panel panel-info\">");
				out.println("				<div class=\"panel-heading\">Usted ya se encuentra logeado en el sistema ");
				out.println("				(Ir al <a href=\"portal.htm\">portal de servicios.</a>)</div>");
				out.println("				</div>");
				out.println("		</div>");
				out.println("	</div>");
				out.println("</div>");
			}
		}

		else{

			String pass = request.getParameter("pass");

				try{
					boolean log = fachada.login(login, "", pass);
					if(log)
						escribirSuccess(out);
					else
						escribirFail(out);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			
		}
	}
	
	/**
	 * Informa al usuario que el login fue exitoso
	 * @param out
	 */
	public void escribirSuccess(PrintWriter out){

		out.println("	<div class=\"well well-lg\">");
		out.println("		<div class=\"container\">");
		out.println("		<br><br><br>");
		out.println("			<div class=\"panel panel-info\">");
		out.println("				<div class=\"panel-heading\">Ha ingresado exitosamente en la pagina, ahora puede hacer uso de nuestro ");
		out.println("				<a href=\"portal.htm\">portal de servicios.</a>)</div>");
		out.println("				</div>");
		out.println("		</div>");
		out.println("	</div>");
		out.println("</div>");
	}

	/**
	 * Informa al admin que el login fue exitoso
	 * @param out
	 */
	public void escribirSuccessAdmin(PrintWriter out){

		out.println("	<div class=\"well well-lg\">");
		out.println("		<div class=\"container\">");
		out.println("		<br><br><br>");
		out.println("			<div class=\"panel panel-info\">");
		out.println("				<div class=\"panel-heading\">Bienvenido Admin, ahora puede hacer uso del");
		out.println("				<a href=\"portal.htm\">portal de servicios.</a>)</div>");
		out.println("				</div>");
		out.println("		</div>");
		out.println("	</div>");
		out.println("</div>");
	}

	/**
	 * Informa al usuario que el login fracaso
	 * @param out
	 */
	public void escribirFail(PrintWriter out){

		out.println("<div class=\"container\">");
		out.println("		<br><br><br>");
		out.println("    <div class=\"row colored\">");
		out.println("        <div id=\"contentdiv\" class=\"contcustom\">");
		out.println("            <span class=\"fa fa-spinner bigicon\"></span>");
		out.println("            <h2>Login</h2>");
		out.println("            <div><form>");
		out.println("            <font color=\"red\"> El correo y/o clave son incorrectos, intentelo de nuevo </font>");	
		out.println("                <input id=\"username\" type=\"text\" placeholder=\"e-mail\" name=\"login\">");
		out.println("                <input id=\"password\" type=\"password\" placeholder=\"clave\" name=\"pass\">");
		out.println("				<button type=\"submit\" class=\"btn btn-default\" >Login</button>");
		out.println("				</form>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("    </div>");
		out.println("</div>");
	}
}

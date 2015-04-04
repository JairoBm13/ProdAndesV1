package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRegistroCliente extends ServletTemplate{

	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "ProdAndes | Registro de cliente";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter  respuesta = response.getWriter();
		String registrado = request.getParameter("registrado");
		System.out.println(registrado);
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
			respuesta.println("                                    <label for=\"login\">Nombre de usuario:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"text\" name=\"login\" id=\"login\" class=\"form-control\" required>");
			respuesta.println("                                        <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"pass\">Contrase&ntilde;a:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"password\" name=\"pass\" id=\"pass\" class=\"form-control\" required>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"cli\">N&uacute;mero id: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"text\" name=\"idcli\" id=\"idcli\" class=\"form-control\" required>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"selTipoId\">Tipo de documento:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<select name=\"selTipoId\" id=\"selTipoId\" class=\"form-control\" required><br>");
			respuesta.println("                                    		<option value=\"cedula\">Cedula</option>");
			respuesta.println("                                    		<option value=\"c. extranjeria\">Cedula de extranjeria</option>");
			respuesta.println("                                    		<option value=\"NIT\">NIT</option>");
			respuesta.println("                                    		<option value=\"NIT extrajenro\">NIT extranejro</option>");
			respuesta.println("                                    	</select>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"nombre\">Nombre: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                        <input name=\"nombre\" type=\"text\" id=\"nombre\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"nacionalidad\">Nacionalidad: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input name=\"nacionalidad\" id=\"nacionalidad\" type=\"text\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"direccionElectronica\">Correo: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input name=\"direccionElectronica\" id=\"direccionElectronica\" type=\"email\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"ciudad\">Ciudad: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input name=\"ciudad\" id=\"ciudad\" type=\"text\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"departamento\">Departamento: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input name=\"departamento\" id=\"departamento\" type=\"text\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"direccionFisica\">Direcci&oacute;n: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input name=\"direccionFisica\" id=\"direccionFisica\" type=\"text\" class=\"form-control\" required><br>");
			respuesta.println("                                   		<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"telefono\">Tel&eacute;fono: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"tel\" name=\"telefono\" id=\"telefono\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"codPostal\">C&oacute;digo Postal: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                        <input type=\"text\" name=\"codPostal\" id=\"codPostal\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("				<!-- aqui es para cada tipo de usuario-->");
			respuesta.println("                                    <label for=\"sinv\">Registro SINV: </label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"sinv\" id=\"sinv\" name=\"sinv\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"nombrelegal\">Representante legal:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"text\" id=\"nombrelegal\" name=\"nombrelegal\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"idlegal\">Id de representante legal:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<input type=\"text\" id=\"idlegal\" name=\"idlegal\" class=\"form-control\" required><br>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                    <label for=\"tipoIdLegal\">Tipo Id del representate legal:</label>");
			respuesta.println("                                    <div class=\"input-group\">");
			respuesta.println("                                    	<select name=\"selTipoIdLegal\" id=\"selTipoId\" class=\"form-control\" required><br>");
			respuesta.println("                                            <option value=\"cedula\">Cedula</option>");
			respuesta.println("                                            <option value=\"c. extranjeria\">Cedula de extranjeria</option>");
			respuesta.println("                                            <option value=\"NIT\">NIT</option>");
			respuesta.println("                                            <option value=\"NIT extrajenro\">NIT extranejro</option>");
			respuesta.println("	                                    </select>");
			respuesta.println("                                    	<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
			respuesta.println("                   					</div>");
			respuesta.println("                                      <br>");
			respuesta.println("                                    <input type=\"submit\" value=\"Submit\">");
			respuesta.println("                             ");
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
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			String idcli = request.getParameter("idcli");
			String selTipoId = request.getParameter("selTipoId");
			String nombre = request.getParameter("nombre");
			String nacionalidad = request.getParameter("nacionalidad");
			String direccionElectronica = request.getParameter("direccionElectronica");
			String ciudad = request.getParameter("ciudad");
			String departamento = request.getParameter("departamento");
			String direccionFisica = request.getParameter("direccionFisica");
			String telefno = request.getParameter("telefono");
			String codPostal = request.getParameter("codPostal");
			
			String nombrelegal = request.getParameter("nombreLegal");
			String id = request.getParameter("idLegal");
			String sinv = request.getParameter("sinv");
			String tipoIdLegal = request.getParameter("selTipoIdLegal");
			try {
				fachada.registrarCliente(login,pass,idcli,selTipoId,nombre,nacionalidad,direccionElectronica,ciudad,departamento,direccionFisica,telefno,codPostal,nombrelegal,id,sinv,tipoIdLegal);
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
	
	private void escribirUsuarioRegistrado(){
		
	}
}

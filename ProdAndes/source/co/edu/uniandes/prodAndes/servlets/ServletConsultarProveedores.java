package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.vos.EstadoPedidoValue;
import co.edu.uniandes.prodAndes.vos.Material;
import co.edu.uniandes.prodAndes.vos.Operario;
import co.edu.uniandes.prodAndes.vos.Producto;
import co.edu.uniandes.prodAndes.vos.Proveedor;
import co.edu.uniandes.prodAndes.vos.ProveedorValue;

public class ServletConsultarProveedores extends ServletTemplate{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				respuesta.println("                                <form  action=\"proveedor.html\">");
				respuesta.println("                                		<input type=\"hidden\" name=\"registrado\" value=\"registrado\"><br>");
				respuesta.println("										<label for=\"selproveedor\">Clientes:</label>");
				respuesta.println("										<div class=\"input-group\">");
				respuesta.println("                                   		<select id=\"selproveedor\"  name=\"selproveedor\" class=\"form-control\" >");
				respuesta.println(escribirProveedores());
				respuesta.println("											</select>");        
				respuesta.println("											<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span></div>");
				respuesta.println("										<label for=\"selproducto\">Producto:</label>           <div class=\"input-group\">");
				respuesta.println("                                   	<select id=\"selproducto\"  name=\"selproducto\" class=\"form-control\" >");
				respuesta.println(escribirProductos());
				respuesta.println("										</select><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("										<label for=\"selmateriales\">Materiales:</label>           <div class=\"input-group\">");
				respuesta.println("                                   	<select id=\"selmateriales\"  name=\"selmateriales\" class=\"form-control\" >");
				respuesta.println(escribirMateriales());
				respuesta.println("										</select><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span> </div>");
				respuesta.println("<label for=\"cantprodmin\">Cantidad m&iacute;nima de producto:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"1\"  id=\"cantprodmin\" name=\"cantprodmin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"cantpromax\">Cantidad m&aacute;xima de producto:</label><div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"1\"  id=\"cantprodmax\" name=\"cantprodmax\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"costomin\">Costo m&iacute;nimo:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"0.01\"  id=\"costomin\" name=\"costomin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"costomax\">Costo m&aacute;ximo:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"number\" min=\"0\" step=\"0.01\"  id=\"cotomax\" name=\"costomax\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"datemin\">Fecha m&iacute;nima:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"date\" min=\"2015-01-01\"  id=\"datemin\" name=\"datemin\">");
				respuesta.println("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>");
				respuesta.println("</div>");
				respuesta.println("<label for=\"datemax\">Fecha m&aacute;xima:</label>           <div class=\"input-group\">");
				respuesta.println("<input type=\"date\" min=\"2015-01-01\"  id=\"datemax\" name=\"datemax\">");
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

				String codCliente = request.getParameter("selcliente");
				String codProducto = request.getParameter("selproducto");
				String codmaterial = request.getParameter("selmateriales");
				String cantProdMin = request.getParameter("cantprodmin");
				String cantProdMax = request.getParameter("cantprodmax");
				String costoMin = request.getParameter("cantprodmin");
				String CostoMax = request.getParameter("cantprodmin");
				String datemin = request.getParameter("datemin");
				String datemax = request.getParameter("datemax");
				try {
					ArrayList<Proveedor> pedidos = fachada.consultarProveedores("", "", "", "", "", "", "", "", "", "");
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
					respuesta.println("<!-- Start Post -->");
					respuesta.println("                        <table>");
					respuesta.println(escribirTablaEstadoPedidos(pedidos));
					respuesta.println("                        </table>");
					respuesta.println("						");
					respuesta.println("						<!-- End Post -->");
					respuesta.println("						");
//					respuesta.println("						<!-- Start Pagination -->");
//					respuesta.println("						<div id=\"pagination\">");
//					respuesta.println("							<span class=\"all-pages\">Page 1 of 3</span>");
//					respuesta.println("							<span class=\"current page-num\">1</span>");
//					respuesta.println("							<a class=\"page-num\" href=\"#\">2</a>");
//					respuesta.println("							<a class=\"page-num\" href=\"#\">3</a>");
//					respuesta.println("							<a class=\"next-page\" href=\"#\">Next</a>");
//					respuesta.println("						</div>");
//					respuesta.println("						<!-- End Pagination -->");
					respuesta.println("                 <a href=\"menu.html\">Volver</a>");
					respuesta.println("					</div>");
					respuesta.println("					<!-- End Blog Posts -->");
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
				} catch (Exception e) {
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

	private String escribirTablaEstadoPedidos(ArrayList<Proveedor> pedidos){
		String tabla = "";
		tabla += "<tr><th>C&oacute;digo Pedido</th>"
				+ "<th>Cliente</th>"
				+ "<th>C&oacute;digo Cliente</th>"
				+ "<th>Nombre Producto</>"
				+ "<th>Estado</th>"
				+ "<th>Cantidad</th>"
				+ "<th>Costo del pedido</th>"
				+ "<th>Fecha pedido</th>"
				+ "<th>Fecha esperada</th>"
				+ "<th>Fecha entrega</th></tr>";
		for (int i = 0; i < pedidos.size(); i++) {
			tabla += "<tr>";
			Proveedor actual = pedidos.get(i);
			tabla += "<td>"+actual.getCodigo()+"</td>";
			tabla += "</tr>";
		}
		return tabla;
	}

	private String escribirProveedores(){
		String options = "<option></option>\n";
		try {
			ArrayList<Proveedor> clientes = fachada.darProveedores();
			for (int i = 0; i < clientes.size(); i++) {
				Proveedor act = clientes.get(i);
				options = "<option value=\""+act.getCodigo()+"\">"+act.getNombreLegal()+"</option>\n";				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}

	private String escribirProductos(){
		String options = "<option></option>\n";
		try {
			ArrayList<Producto> clientes = fachada.darTodosProductosCodigoNombre();
			for (int i = 0; i < clientes.size(); i++) {
				Producto act = clientes.get(i);
				options += "<option value=\""+act.getCodigo()+"\">"+act.getNombre()+"</option>\n";				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}

	private String escribirMateriales(){
		String options = "<option value=\"\"></option>\n";
		try {
			ArrayList<Material> clientes = fachada.darTodosMaterialesCodigoNombreTipo();
			for (int i = 0; i < clientes.size(); i++) {
				Material act = clientes.get(i);
				options += "<option value=\""+act.getCodigo()+"\">"+act.getNombre()+"</option>\n";				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}
	
}

package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.prodAndes.vos.EtapaProduccion;
import co.edu.uniandes.prodAndes.vos.Pedido;

public class ConsultarMaterialPedidoV2 extends ServletTemplate{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6782687164907176534L;
	
	@Override
	public String darTituloPagina(HttpServletRequest request) {
		return "Consultar Etapas";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter respuesta = response.getWriter();
		
		String consultado = request.getParameter("registrado");
		if(fachada.getTipoUsuario()==1 || (fachada.getTipoUsuario()==3 && fachada.getOperarioVal().isGerente())){
			if(consultado == null){
				respuesta.println("<!-- Start Content -->");
				respuesta.println("		<div id=\"content\">");
				respuesta.println("			<div class=\"container\">");
				respuesta.println("				<div class=\"page-content\">");
				respuesta.println("					");
				respuesta.println("					<div class=\"row\">");
				respuesta.println("						");
				respuesta.println("						<div class=\"col-md-7\">");
				respuesta.println("							");
				respuesta.println("							<!-- Classic Heading -->");
				respuesta.println("							<h4 class=\"classic-title\">");
				respuesta.println("								<span>Consultar material:</span>");
				respuesta.println("							</h4>");
				respuesta.println("							");
				respuesta.println("                            <form  action=\"pedidosV2.html\">");
				respuesta.println("	                            <input type=\"hidden\" name=\"registrado\" value=\"registrado\"><br>");
				respuesta.println("								<label for=\"material\">Materiales:</label>  ");
				respuesta.println("								<div class=\"input-group\">");
				respuesta.println("									<input type=\"text\" id=\"material\"  name=\"material\" class=\"form-control\">");
				respuesta.println("									<span class=\"input-group-addon\">");
				respuesta.println("										<span class=\"glyphicon glyphicon-asterisk\">");
				respuesta.println("										</span>");
				respuesta.println("									</span>");
				respuesta.println("								</div>");
				respuesta.println("								<br>");
				respuesta.println("								<input type=\"submit\" value=\"Consultar\">");
				respuesta.println("                            </form>				");
				respuesta.println("						</div>");
				respuesta.println("					");
				respuesta.println("						<div class=\"col-md-5\"> 	");
				respuesta.println("						</div>");
				respuesta.println("					</div>");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\">");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<div class=\"row\">	");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\">");
				respuesta.println("					</div>");
				respuesta.println("					");
				respuesta.println("					<!-- Divider -->");
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\">");
				respuesta.println("					</div>					");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("		</div>");
				respuesta.println("<!-- End content -->");
			}
			else{
				try {

				String idMaterial = request.getParameter("material");
				int pagina = Integer.parseInt(request.getParameter("pagina"));
				ArrayList<Pedido> etapas = fachada.darMaterialesParaPedidosV2(idMaterial, pagina);
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
				respuesta.println(imprimirTabla(etapas));
				respuesta.println("							<p>                             ");
				respuesta.println("						</div>");
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
				respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
				respuesta.println("				</div>");
				respuesta.println("			</div>");
				respuesta.println("						<!-- Start Pagination -->");
				respuesta.println("						<div id=\"pagination\">");
				respuesta.println("							<span class=\"current page-num\">"+pagina+"</span>");
				respuesta.println("<form id=\"formpagina\" method=\"post\" action=\"pedidosV2.html\">");
				respuesta.println("	<input type=\"hidden\" name=\"tipo\" value=\""+idMaterial+"\">");
				respuesta.println("	<input type=\"hidden\" name=\"pagina\" value=\""+(pagina+1)+"\">");
				respuesta.println(" <input type=\"hidden\" name=\"registrado\" value=\"registrado\">");
				respuesta.println("	<div class=\"text-center\">");
				respuesta.println("		<input type=\"submit\" class=\"btn-system btn-small\" value=\"Siguiente\">");
				respuesta.println("	</div>");
				respuesta.println("</form>");
				respuesta.println("						<!-- End Pagination -->");
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
		else if(fachada.getTipoUsuario()==0){
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
			respuesta.println("				</div>");
			respuesta.println("			</div>");
			respuesta.println("		</div>");
			respuesta.println("		<!-- End Content -->");
		}
		else {
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
			respuesta.println("				</div>");
			respuesta.println("			</div>");
			respuesta.println("		</div>");
			respuesta.println("		<!-- End Content -->");
		}
	}
	
	private String imprimirTabla(ArrayList<Pedido> etapas){
		String tabla = "<table>";
		tabla += "<tr><td>Codigo</td><td>Estado</td><td>Cantidad</td><td>Fecha Pedido</td><td>Fecha esperada</td><td>Fecha entrega</td><td>Producto</td><td>Cliente</td></tr>";
		for (int i = 0; i < etapas.size(); i++) {
			Pedido act = etapas.get(i);
			tabla += "<tr>";
			tabla += "<td>"+act.getCodigo()+"</td>";
			tabla += "<td>"+act.getEstado()+"</td>";
			tabla += "<td>"+act.getCantidad()+"</td>";
			tabla += "<td>"+act.getFechaPedido()+"</td>";
			tabla += "<td>"+act.getFechaEsperada()+"</td>";
			tabla += "<td>"+act.getFechaEntrega()+"</td>";
			tabla += "<td>"+act.getProducto().getCodigo()+"</td>";
			tabla += "<td>"+act.getCliente().getCodigo()+"</td>";
			tabla += "</tr>";
		}
		tabla += "</table>";
		return tabla;
	}
}


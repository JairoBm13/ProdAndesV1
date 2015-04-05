package co.edu.uniandes.prodAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletMenu extends ServletTemplate{

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
		respuesta.println("		<!-- Start Page Banner -->");
		respuesta.println("		<div class=\"page-banner\" style=\"padding:40px 0; background: url(images/slide-02-bg.jpg) center #f9f9f9;\">");
		respuesta.println("			<div class=\"container\">");
		respuesta.println("				<div class=\"row\">");
		respuesta.println("					<div class=\"col-md-6\">");
		respuesta.println("						<h2>Men&uacute;</h2>");
		//TODO agregar el tipo de usuario ahi.
		respuesta.println("						<p>Opciones disponibles para "+"</p>");
		respuesta.println("					</div>");
		respuesta.println("					<div class=\"col-md-6\">");
		respuesta.println("						<ul class=\"breadcrumbs\">");
		respuesta.println("							<li><a href=\"index.html\">Inicio</a></li>");
		respuesta.println("							<li>Menu</li>");
		respuesta.println("						</ul>");
		respuesta.println("					</div>");
		respuesta.println("				</div>");
		respuesta.println("			</div>");
		respuesta.println("		</div>");
		respuesta.println("		<!-- End Page Banner -->");
		respuesta.println("		");
		respuesta.println("		");
		respuesta.println("		");
		respuesta.println("");
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
		respuesta.println("							<h4 class=\"classic-title\"><span>Registro</span></h4>");
		respuesta.println("							");
		respuesta.println("							<!-- Some Text -->");
		respuesta.println("							");
		respuesta.println("							<p><ul>");
		if(fachada.getTipoUsuario()==1){
			respuesta.println("                            		   <li><a href=\"registroCliente.html\">Registrar un cliente</a></li>");
			respuesta.println("                                    <li><a href=\"registroProveedor.html\">Registrar un proveedor</a></li>");
			respuesta.println("                                    <li><a href=\"registroOperario.html\">Registrar un operario</a></li>");
			respuesta.println("                                    <li><a href=\"registroUsuario.html\">Registrar un usuario</a></li>");
		}
		if(fachada.getTipoUsuario()==4 && fachada.getOperarioVal().getCargo().equals("Gerente") || fachada.getTipoUsuario()==1){
			respuesta.println("                                    <li><a href=\"registrarMateria.html\">Registrar materia prima</a></li>");
			respuesta.println("                                    <li><a href=\"registroComponente.html\">Registrar componente</a></li>");
			respuesta.println("                                    <li><a href=\"registrarEstacion.html\">Registrar una estaci&oacute;n de producci&oacute;n</a></li>");
			respuesta.println("                                    <li><a href=\"registrarEtapa.html\">Registrar una etapa de producci&oacute;</a></li>");
			respuesta.println("                                    <li><a href=\"registrarProducto.html\">Registrar un producto</a></li>");
			respuesta.println("                                    <li><a href=\"registrarLlegada\">Registrar llegada de material</a></li>");
			respuesta.println("                                    <li><a href=\"registrarPedidoEntregado.html>\">Registrar entrega de pedido</a></li>");
			respuesta.println("                                    <li><a href=\"registrarPedidoMaterial\">Registrar pedido material</a></li>");

		}
		if((fachada.getTipoUsuario()==4 && !fachada.getOperarioVal().getCargo().equals("Gerente")) || fachada.getTipoUsuario()==1){
			respuesta.println("                                    <li><a href=\"registarEjecucion.html\">Registrar ejecuci&oacute;n de etapa</a></li>");
		}
		if(fachada.getTipoUsuario()==3 ){
			respuesta.println("                                    <li><a href=\"registrarPedidoProducto.html\">Registrar pedido material</a></li>");
			respuesta.println("                                    <li><a href=\"cancelarPedido\">Cancelar pedido</a></li>");
		}
		respuesta.println("                            	</ul>");
		respuesta.println("                            </p>");
		respuesta.println("                            ");
		respuesta.println("                            <!-- Classic Heading -->");
		respuesta.println("							<h4 class=\"classic-title\"><span>Consultas</span></h4>");
		respuesta.println("                            ");
		respuesta.println("                            <p>");
		respuesta.println("                            	<ul>");
		respuesta.println("                                	<li><a href=\"consultarExistenciaMaterial.html\">Consultar existencia de material</a></li>");
		respuesta.println("                                    <li><a href=\"consultarMaterial.html\">Consultar informaci&oacute;n de un Material</a></li>");
		respuesta.println("                                </ul>");
		respuesta.println("                            </p>");
		respuesta.println("							");
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
		respuesta.println("					<div class=\"hr1\" style=\"margin-bottom:50px;\"></div>");
		respuesta.println("				</div>");
		respuesta.println("			</div>");
		respuesta.println("		</div>");
		respuesta.println("		<!-- End content -->");
	}

}

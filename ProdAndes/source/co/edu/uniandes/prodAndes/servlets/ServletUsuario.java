package co.edu.uniandes.prodAndes.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.*;
import java.util.Iterator;

import javax.servlet.*;
import javax.servlet.http.*;


public class ServletUsuario extends ServletTemplate
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Calculadora para realizar las operaciones
     */
	
	private char dobleComilla = '"';
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicialización del Servlet
     */

    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarSolicitud( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    	if(request.getParameter("IdUsuR")!=null&&request.getParameter("IdContraR")!=null)
    	{
    		
    		String usu = request.getParameter("IdUsuR");
    		String contra = request.getParameter("IdContraR");
    		
    		try {
				
				// Envía a la respuesta el encabezado del template
				imprimirEncabezado( response );

				//Trabaja y envia el resultado
				imprimirResultadoRegistro(response);

				// Envía a la respuesta la parte inferior del template
				imprimirFooter( response);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				this.imprimirMensajeError(response);
			}
    		
    		
    		
    	}
    	
    	else if(request.getParameter("IdUsuI")!=null&&request.getParameter("IdContraI")!=null)
    	{
    		
    		String usu = request.getParameter("IdUsuI");
    		String contra = request.getParameter("IdContraI");
    		
    		try {
				
				// Envía a la respuesta el encabezado del template
				imprimirEncabezado( response );

				//Trabaja y envia el resultado
				imprimirResultadoIngreso(response);

				// Envía a la respuesta la parte inferior del template
				imprimirFooter( response);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				this.imprimirMensajeError(response);
			}
    		
    		
    		
    	}
    	
    	else if(request.getParameter("InfoUsu")!=null)
    	{
    		


    		try {
				
				// Envía a la respuesta el encabezado del template
				imprimirEncabezado( response );

				//Trabaja y envia el resultado
				imprimirResultadoIngreso(response);

				// Envía a la respuesta la parte inferior del template
				imprimirFooter( response);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				this.imprimirMensajeError(response);
			}
    		
    		
    		
    	}
    	
    	else if(request.getParameter("idAerolinea")!=null)
    	{
    		try {
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.imprimirMensajeError(response);
			}
    		
    		// Envía a la respuesta el encabezado del template
			imprimirEncabezado( response );

			//Trabaja y envia el resultado
			imprimirResultadoIngreso(response);

			// Envía a la respuesta la parte inferior del template
			imprimirFooter( response);
    	}
    	
    	else if(request.getParameter("idAeropuerto")!=null)
    	{
    		try {
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.imprimirMensajeError(response);
			}
    		
    		// Envía a la respuesta el encabezado del template
			imprimirEncabezado( response );

			//Trabaja y envia el resultado
			imprimirResultadoIngreso(response);

			// Envía a la respuesta la parte inferior del template
			imprimirFooter( response);
    	}
    	
    	    	
    	else
    	{
    		this.imprimirMensajeError(response);
    	}

    }

    /**
     * Imprime el resultado de la operación
     * @param operacion Operación realizada
     * @param v1 Valor 1 de la operación
     * @param v2 Valor 2 de la operación
     * @param resultado Resultado de la operación
     * @param response Respuesta al cliente
     * @throws IOException Excepción al obtener el flujo de escritura para escribir al cliente
     */
    private void imprimirResultadoIngreso( HttpServletResponse response) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime el resultado
        
        
        {
        	
        	            
            respuesta.println("                  <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonInfo"+dobleComilla+" id="+dobleComilla+"botonInfo"+dobleComilla+" value="+dobleComilla+"Informacion Aerolinea"+dobleComilla+" />");
            respuesta.println("     </form></p>");
            respuesta.println("      </tr>");
            
        }
        
        
        
        
        
        
    }

    
    /**
     * Imprime el resultado de la operación
     * @param operacion Operación realizada
     * @param v1 Valor 1 de la operación
     * @param v2 Valor 2 de la operación
     * @param resultado Resultado de la operación
     * @param response Respuesta al cliente
     * @throws IOException Excepción al obtener el flujo de escritura para escribir al cliente
     */
    private void imprimirResultadoRegistro( HttpServletResponse response) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime el resultado
        
        
        {
        	
        	        
            respuesta.println("                  <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonInfo"+dobleComilla+" id="+dobleComilla+"botonInfo"+dobleComilla+" value="+dobleComilla+"Informacion Aerolinea"+dobleComilla+" />");
            respuesta.println("     </form></p>");
            respuesta.println("      </tr>");
            
        }
        
        
        
        
        
        
    }
    
    
    /**
     * Imprime el encabezado con el diseño de la página
     * @param response Respuesta
     * @throws IOException Excepción al imprimir en el resultado
     */
    private void imprimirEncabezado( HttpServletResponse response ) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime el encabezado
        
        respuesta.println( "<!DOCTYPE html PUBLIC "+dobleComilla+"-//W3C//DTD XHTML 1.0 Transitional//EN"+dobleComilla+" "+ dobleComilla +"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"+dobleComilla+">" );
        respuesta.println( "<html xmlns="+dobleComilla+"http://www.w3.org/1999/xhtml"+dobleComilla+">" );
        respuesta.println( "<head>" );
        respuesta.println( "<meta http-equiv="+dobleComilla+"Content-Type"+dobleComilla+" content="+dobleComilla+"text/html; charset=utf-8"+dobleComilla+" />" );
        respuesta.println("<title>CupiFlights - Usuario</title>");
        respuesta.println("<style type="+dobleComilla+"text/css"+dobleComilla+">");
        respuesta.println("body {");
        respuesta.println("	background-image: url(imagenes/16cupi02.png);");
        respuesta.println("}");
        respuesta.println("</style>");
        respuesta.println("</head>");
        respuesta.println("<body topmargin="+dobleComilla+"+dobleComilla+"+dobleComilla+"0"+dobleComilla+">");
        respuesta.println("<table width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("<tr>");
        respuesta.println(" <td height="+dobleComilla+"20%"+dobleComilla+"><img src="+dobleComilla+"imagenes/16cupi01.png"+dobleComilla+" width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"268"+dobleComilla+" /></td>");
        respuesta.println("</tr>");
        
        respuesta.println("<tr>");
        respuesta.println("<td height="+dobleComilla+"80%"+dobleComilla+"><table width="+dobleComilla+"100%"+dobleComilla+" height="+dobleComilla+"513%"+dobleComilla+" border="+dobleComilla+"2"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("      <tr>");
        respuesta.println("     <td><table width="+dobleComilla+"100%"+dobleComilla+" height="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        
        
        
        
        
    }

    /**
     * Imprime la parte inferior del diseño de la página
     * @param response Respuesta
     * @param tieneResultado Especifica si debe incluir el resultado
     * @param resultado Resultado de la operación anterior
     * @throws IOException Excepción al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime la parte inferior del diseño de la página
        

        respuesta.println("        </table></td>");
        respuesta.println("      </tr>");
        respuesta.println("      <tr>");
        respuesta.println("  <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        
        
        ///////////////////////////////////////////////
        
        respuesta.println("      <tr>");
        
        
        respuesta.println("  <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("   <tr>");
        
        respuesta.println("  "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"usuario.htm"+dobleComilla+">"+"        ");
        
        
        
        respuesta.println(" <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        respuesta.println("      <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonGuardar"+dobleComilla+" id="+dobleComilla+"botonGuardar"+dobleComilla+" value="+dobleComilla+"Guardar Registro de Usuario"+dobleComilla+" />");
        //respuesta.println("             </div></td>");
        respuesta.println("             </div></td></form>");
        
        
        respuesta.println("     </tr>");
        respuesta.println("      </table></td>");
        respuesta.println("     </tr>");
        
/////////////////////////////////////////////////////
        
        
        
        
        respuesta.println(" <tr>");
        respuesta.println("     <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        
        
        
        
        respuesta.println("   <tr>");
        
        
        
        
        
        
        
        
        //respuesta.println("   <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        //respuesta.println("  "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"Vuelos.htm"+dobleComilla+">"+"        <td width="+dobleComilla+"25%"+dobleComilla+"><input type="+dobleComilla+"text"+dobleComilla+"  name=\"idAero\" value = \""+aeropuerto.darId()+"\" readonly=\"readonly\"  /></td>"+" <td width="+dobleComilla+"80%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        respuesta.println("  "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"usuario.htm"+dobleComilla+">"+"        ");
        
        
        respuesta.println("<td width=\"30%\"> <label>Nuevo Minimo:</label>   <input type=\"text\" name=\"min\" id=\"min\" /></td>");
        
        respuesta.println("<td width=\"30%\"> <label>Nuevo Maximo:</label>   <input type=\"text\" name=\"max\" id=\"max\" /></td>");
        
        
        
        
        respuesta.println(" <td width="+dobleComilla+"40%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        respuesta.println("      <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonValor"+dobleComilla+" id="+dobleComilla+"botonValor"+dobleComilla+" value="+dobleComilla+"Reajustar minimo y maximo"+dobleComilla+" />");
        //respuesta.println("             </div></td>");
        respuesta.println("             </div></td></form>");
        
        
        
        respuesta.println("        </tr>");
        
        
        ///////////////////////////////////////////////////////////
        
        
        
        respuesta.println(" <tr>");
        respuesta.println("     <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("   <tr>");
        //respuesta.println("   <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        
        	respuesta.println(" "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"indexUsuario.htm"+dobleComilla+">"+"  <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        
        
        respuesta.println("      <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonVolver"+dobleComilla+" id="+dobleComilla+"botonVolver"+dobleComilla+" value="+dobleComilla+"Pagina Principal"+dobleComilla+" />");
        //respuesta.println("             </div></td>");
        respuesta.println("             </div></td></form>");
        respuesta.println("        </tr>");
        respuesta.println("      </table></td>");
        respuesta.println("     </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("      </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("  </tr>");
        respuesta.println("</table>");
        respuesta.println("</body>");
        respuesta.println("</html>");
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param mensaje Mensaje del error
     */
    private void imprimirMensajeError( HttpServletResponse response ) throws IOException
    {
    	
    	// Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );
    	
        respuesta.println( "<!DOCTYPE html PUBLIC "+dobleComilla+"-//W3C//DTD XHTML 1.0 Transitional//EN"+dobleComilla+" "+ dobleComilla +"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"+dobleComilla+">" );
        respuesta.println( "<html xmlns="+dobleComilla+"http://www.w3.org/1999/xhtml"+dobleComilla+">" );
        respuesta.println( "<head>" );
        respuesta.println( "<meta http-equiv="+dobleComilla+"Content-Type"+dobleComilla+" content="+dobleComilla+"text/html; charset=utf-8"+dobleComilla+" />" );
        respuesta.println("<title>CupiFlights - Error</title>");
        respuesta.println("<style type="+dobleComilla+"text/css"+dobleComilla+">");
        respuesta.println("body {");
        respuesta.println("	background-image: url(imagenes/16cupi02.png);");
        respuesta.println("}");
        respuesta.println("</style>");
        respuesta.println("</head>");
        respuesta.println("<body topmargin="+dobleComilla+"+dobleComilla+"+dobleComilla+"0"+dobleComilla+">");
        respuesta.println("<table width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("<tr>");
        respuesta.println(" <td height="+dobleComilla+"20%"+dobleComilla+"><img src="+dobleComilla+"imagenes/16cupi01.png"+dobleComilla+" width="+dobleComilla+"1290"+dobleComilla+" height="+dobleComilla+"268"+dobleComilla+" /></td>");
        respuesta.println("</tr>");
        respuesta.println("<tr>");
        respuesta.println("<td height="+dobleComilla+"80%"+dobleComilla+"><table width="+dobleComilla+"100%"+dobleComilla+" height="+dobleComilla+"513%"+dobleComilla+" border="+dobleComilla+"2"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("      <tr>");
        respuesta.println("     <td>Error al ingresar los datos, por favor vuelva a la pagina principal :(</td>");
        respuesta.println("      </tr>");
        respuesta.println("      <tr>");
        respuesta.println("  <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println(" <tr>");
        respuesta.println("     <td><table width="+dobleComilla+"100%"+dobleComilla+" border="+dobleComilla+"0"+dobleComilla+" cellspacing="+dobleComilla+"5"+dobleComilla+">");
        respuesta.println("   <tr>");
        //respuesta.println("   <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        	respuesta.println(" "+"<form method="+dobleComilla+"post"+dobleComilla+" action="+dobleComilla+"indexUsuario.htm"+dobleComilla+">"+"  <td width="+dobleComilla+"100%"+dobleComilla+"><div align="+dobleComilla+"right"+dobleComilla+">");
        respuesta.println("      <input type="+dobleComilla+"submit"+dobleComilla+" name="+dobleComilla+"botonVolver"+dobleComilla+" id="+dobleComilla+"botonVolver"+dobleComilla+" value="+dobleComilla+"Pagina Principal"+dobleComilla+" />");
        //respuesta.println("             </div></td>");
        respuesta.println("             </div></td></form>");
        respuesta.println("        </tr>");
        respuesta.println("      </table></td>");
        respuesta.println("     </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("      </tr>");
        respuesta.println("    </table></td>");
        respuesta.println("  </tr>");
        respuesta.println("</table>");
        respuesta.println("</body>");
        respuesta.println("</html>");
    	
    }

    /**
     * Formatea un valor numérico para presentar en la interfaz <br>
     * @param valor - El valor numérico a ser formateado
     * @return Retorna una cadena de caracteres con el valor formateado con puntos y signos.
     */
    private String formatearValor( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

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
		// TODO Auto-generated method stub
		
	}
}

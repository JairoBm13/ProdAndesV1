package co.edu.uniandes.prodAndes.dao;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TransactSender {

	//-------------------------------------------------
	// Atributos
	//-------------------------------------------------
	
	private InitialContext contexto;
	
	private ConnectionFactory fabricaConect;
	
	private javax.jms.Connection conectMensa;
	
	private Queue cola;
	
	private Session sesion;
	
	private MessageProducer productor;
	
	//----------------------------------------------
	// Constructor
	//----------------------------------------------
	
	public TransactSender(){
		try {
			contexto = new InitialContext();
			fabricaConect = (ConnectionFactory) contexto.lookup("/ConnectionFactory");
			conectMensa = fabricaConect.createConnection();
			sesion = conectMensa.createSession(false, Session.AUTO_ACKNOWLEDGE);
			cola = (Queue) contexto.lookup("queue/Test");
			productor = sesion.createProducer(cola);
			conectMensa.start();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String mensaje) throws Exception{
		try {
			TextMessage tm = sesion.createTextMessage(mensaje);
			productor.send(tm);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			conectMensa.close();
		}
	}
}

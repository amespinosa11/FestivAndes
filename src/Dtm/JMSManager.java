package Dtm;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.jms.DeliveryMode;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.codehaus.jackson.map.ObjectMapper;

import Fachada.Master;
import Vos.ListaFunciones;

public class JMSManager implements MessageListener, ExceptionListener
{
	/**
	 * Atributo tipo JMSManager para manejar la �nica instancia del patron singleton
	 */
	private static JMSManager instancia;
	
	/**
	 * Atributo tipo ListaVideos que va guardando todas las funciones que llegan como respuesta de las otra aplicaciones 
	 */
	private ListaFunciones respuesta;
	
	/**
	 * Atributo tipo TopicSession que se usa para la conexi�n a los topics 
	 */
	private TopicSession topicSession;
	
	/**
	 * Atributo tipo Topic que maneja la conexi�n al topic de dar todas las funciones 
	 */
	private Topic topic;
	
	/**
	 * ArrayList que guarda todos los recursos que se usan para la conexi�n a las colas y topics
	 */
	private ArrayList<Object> recursos;

	/**
	 * boolean que dice si la aplicaci�n esta a la espera de respuestas de las otras aplicaciones
	 */
	private boolean waiting;

	/**
	 * Numero de aplicaciones que hay en todo el sistema aparte de la propia. 
	 * Esto se hace para saber cuantas respuestas se esperan 
	 */
	private int numberAppsTotal;

	/**
	 * Numero de aplicaciones/respuestas que han llegado
	 */
	private int numberApps;

	/**
	 * Referencia a la clase principal Master para su uso.
	 * Se usa para responder a requerimientos que llegan de otras aplicaciones
	 */
	private Master master;

	/////Queues:
	
	/**
	 * Atributo que representa la cola personal de esta aplicaci�n
	 */
	private String myQueue;

	/////Topics:

	/**
	 * Atributo que representa el topic: topicAllFunctiones
	 */
	private String topicAllFunctions;

	/////Protocol

	/**
	 * Atributo que representa el time out de el requerimiento de dar todas las funciones
	 * 10 segundos
	 */
	public final static int TIME_OUT = 10;

	/**
	 * Ruta para la conexi�n al Remote Connection Factory
	 */
	public final static String REMOTE_CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";	

	/**
	 * Atributo que representa, dentro del mensaje, la solicitud de todas las funciones de manera distribuida
	 */
	public final static String GET_ALL_FUNCTIONS_ASK = "GETALLFUNCTIONS";

	/**
	 * Atributo que representa, dentro del mensaje, la respuesta del requerimiento dar todas las funciones.
	 */
	public final static String GET_ALL_FUNCTIONS_REPLY = "GETALLFUNCTIONSRES";

	/**
	 * Atributo que representa, dentro del mensaje, el conector para el formateo de todos los mensajes
	 */
	public final static String CONNECTOR = "@@@";


	/**
	 * M�todo que inicializa el atributo de master
	 * @param videoAndesMaster - la instancia de VideoAndesMaster. videoAndesMaster !=  null
	 * <b> post: </b> se ha inicializado el atributo  master
	 */
	public void setUpMaster(Master pMaster){
		this.master =  pMaster;
	}

	/**
	 * M�todo que retorna la instancia �nica de la clase
	 * @param videoAndesMaster - instancia que hace referencia a la clase principal VideoAndesMaster
	 * @return JMSManager - instancia �nica de la clase
	 */
	public static JMSManager darInstacia(Master pMaster){
		instancia = instancia == null? new JMSManager() : instancia;
		instancia.setUpMaster(pMaster);
		return instancia;
	}

	
	/**
	 * M�todo que cierra todos los recursos que est�n en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	/*
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof FuncionDao)
				try {
					((FuncionDao) ob).cerrarRecursos();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}*/


	/**
	 * M�todo que inicializa la clase JMSManager 
	 * @param numerApps - numero de aplicaciones totales 
	 * @param myQueue - ruta de la cola personal de la aplicaci�n
	 * @param topicAllFunctions - ruta del topic topicAllFunctions
	 * <b>post: </b> se han inicializado los atributos de la clase y 
	 * se han generado la suscripciones y publicaciones a las colas y topics
	 */
	public void setUpJMSManager(int numerApps, String myQueue, String topicAllFuncs){
		try {
			this.numberAppsTotal = numerApps - 1;
			this.myQueue = myQueue;
			this.topicAllFunctions = topicAllFuncs;
			setupMyQueue();
			setupSubscriptions();
			waiting = false;
			respuesta = new ListaFunciones();
			this.recursos = new ArrayList<Object>();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que retorna la respuesta a requerimiento de dar todas las funciones.
	 * @return ListaFunciones - Objeto ListaFunciones con las funciones de la respuesta del requerimiento
	 * @throws IncompleteReplyException - Caso de IncompleteReplyException
	 * @throws NonReplyException - Caso de NonReplyException
	 * @throws JMSException - Caso de JMSException
	 */
	public ListaFunciones getResponse() throws IncompleteReplyException, JMSException, NamingException, InterruptedException, NonReplyException {
		sendMessage(); // manda el mensaje de solicitud del requerimiento al topic
		waiting = true; // Lo hace para< indicar que si esta esperando respuestas
		this.numberApps = 0; // Pone en 0 el numero de respuestas que han llegado

		int count = 0;
		while(TIME_OUT != count && this.numberApps != this.numberAppsTotal){
			TimeUnit.SECONDS.sleep(1); // espera activa que termina cuando se ha cumplido el time out o cuando han llegado todas las respuestas esperadas
			count++;
		}
		
		if(count == TIME_OUT){ // Verifica si se cumpli� el time out 
			if(this.respuesta.getFunciones().isEmpty()){
				waiting = false;
				this.numberApps = 0;
				throw new NonReplyException("Time Out - No Reply"); // Exception que indica que se cumplido el time out y nadie respondido 
			}
			waiting = false;
			this.numberApps = 0;
			throw new IncompleteReplyException("Time out ",respuesta); // Exception que indica que se cumplido el time out pero algunos respondieron
		}
		waiting = false;
		this.numberApps = 0;
		if(respuesta.getFunciones().isEmpty())
			throw new NonReplyException("Got all responses but no videos were detected"); // Exception que indica que todos respondieron pero no llegaron videos
		ListaFunciones res = respuesta;
		respuesta = new ListaFunciones();
		return res; // Retorna con la respuesta completa de todas las aplicaciones
	}

	/**
	 * M�todo que inicializa todas las suscripciones a los topics.
	 * En este caso solo al topic de topicAllVideos
	 * <b>post: </b> se han suscito al topic de dar todas las funciones
	 */
	public void setupSubscriptions()
	{
		// init Topic para consumir donde llegan las peticiones
		try {
			InitialContext ctx = new InitialContext();
			this.topic = (Topic) ctx.lookup(topicAllFunctions);
			TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup(REMOTE_CONNECTION_FACTORY);
			TopicConnection topicConn = connFactory.createTopicConnection();
			this.topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
			topicSubscriber.setMessageListener(this);
			topicConn.setExceptionListener(this);
			topicConn.start();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}                                                                      
	}

	/**
	 * M�todo que manda el mensaje para solicitar el requerimiento de las todas las funciones
	 * <b>post: </b> se han mandado todos los mensaje
	 * @throws JMSException - Caso de JMSException
	 * @throws NamingException - Caso de NamingException
	 */
	public void sendMessage() throws JMSException, NamingException{
		// conecta al Topic para mandar la petici�n
		TopicPublisher topicPublisher = this.topicSession.createPublisher(this.topic);
		topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		TextMessage message = topicSession.createTextMessage();
		message.setText(GET_ALL_FUNCTIONS_ASK + CONNECTOR + this.myQueue);
		topicPublisher.publish(message);
		System.out.println("published: " + message.getText());
	}

	/**
	 * M�todo que publica y suscribe la cola personal de la aplicaci�n
	 * <b>post: </b> se han publicado y suscrito a la cola personal
	 */
	public void setupMyQueue() throws NamingException, JMSException{
		// conecta a la cola para respuestas propias.
		InitialContext ctx = new InitialContext();
		Queue queue = (Queue) ctx.lookup(this.myQueue);
		QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx. lookup(REMOTE_CONNECTION_FACTORY);
		QueueConnection queueConn = connFactory.createQueueConnection();
		QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver queueReceiver = queueSession.createReceiver(queue);
		queueReceiver.setMessageListener(this);
		queueConn.setExceptionListener(this);
		queueConn.start();
	}

	/**
	 * M�todo que manda el mensaje que llega como par�metro a la cola que llega como par�metro 
	 * @param queueName - ruta de la cola 
	 * @param response - mensaje a mandar 
	 * <b>post: </b> se ha mandado el mensaje a la cola
	 */
	public void doResponseToQueue(String queueName , String response) throws NamingException, JMSException{
		// conecta a la cola de respuestas del que pidio
		InitialContext ctx = new InitialContext();
		Queue queue = (Queue) ctx.lookup(queueName);
		QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup(REMOTE_CONNECTION_FACTORY);
		QueueConnection queueConn = connFactory.createQueueConnection();
		QueueSession queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);
		QueueSender queueSender = queueSession.createSender(queue);
		queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		TextMessage message = queueSession.createTextMessage(response);
		queueSender.send(message);
		System.out.println("sent: " + message.getText());
		queueConn.close();
	}

	/**
	 * M�todo listener que recibe los mensajes, lo formatea y  hace lo que corresponde en cada caso: 
	 * - Caso 1: a[0].equals(GET_ALL_FUNCTIONS_ASK): llega la petici�n del requerimiento dar todas las funciones por lo 
	 * que pide todos los videos a la clase principal y manda un mensaje con la respuesta 
	 * - Caso 2: a[0].equals(GET_ALL_FUNCTIONS_REPLY): llega la respuesta de la petici�n del requerimiento por lo tanto
	 * Coge la respuesta y la guarda en respuesta 
	 * @param message - mensaje que llego 
	 * <b>post: </b> se ha mandado el mensaje a la cola
	 */
	public void onMessage(Message message)
	{
		TextMessage msg = (TextMessage) message;
		try 
		{
			String mes = msg.getText();
			System.out.println("received: " + mes);
			String[] a = mes.split(CONNECTOR);
			if(a[0].equals(GET_ALL_FUNCTIONS_ASK) && !a[1].equals(this.myQueue)){
				ListaFunciones videos = this.master.darFuncionesLocal(1);
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(videos);
				doResponseToQueue(a[1], GET_ALL_FUNCTIONS_REPLY + CONNECTOR + jsonString);
			}
			else if(a[0].equals(GET_ALL_FUNCTIONS_REPLY)){
				ObjectMapper mapper = new ObjectMapper();
				if(waiting){
					ListaFunciones obj = mapper.readValue(a[1], ListaFunciones.class);
					this.respuesta.addFuncion(obj);
					this.numberApps++;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * M�todo que se llama con el error cuando hay un error de JMS
	 */
	public void onException(JMSException exception)
	{
		System.err.println("something bad happended: " + exception);
	}

}

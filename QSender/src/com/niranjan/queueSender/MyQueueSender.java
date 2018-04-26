package com.niranjan.queueSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MyQueueSender {
	public static final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public static final String PROVIDER_URL = "t3://localhost:7001";
	public static final String JMS_FACTORY = "jms/TestConnectionFactory";
	
	public static void main(String[] args) throws JMSException, IOException {
		//context initialization
		QueueConnectionFactory  qConFact = null;
		QueueConnection qCon = null;
		QueueSession qSession = null;
		Queue q = null;
		QueueSender qSender = null;
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		p.put(Context.PROVIDER_URL, PROVIDER_URL);
		try {
			InitialContext ctx = new InitialContext(p);			

			//initializing necessary objects for sending message
			qConFact = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);			
			qCon = qConFact.createQueueConnection();			
			qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			q = (Queue) ctx.lookup("jms/TestJMSQueue");
			qSender = (QueueSender) qSession.createSender(q);
			TextMessage msg = qSession.createTextMessage();
			
			qCon.start();
			//write the message
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("please enter the message, enter end to terminate:");
				String s = br.readLine();
				if(s.equals("end")) {
					break;
				}
				msg.setText(s);
				qSender.send(msg);
				System.out.println("message sent successfully");
			}
					
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			qSender.close();
			qSession.close();
			qCon.close();
			
		}
		

	}

}

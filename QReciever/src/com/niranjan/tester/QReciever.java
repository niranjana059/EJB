package com.niranjan.tester;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QReciever implements MessageListener{
    
    
	
	private static final Object JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	private static final Object PROVIDER_URL = "t3://localhost:7001";
	private static final String JMS_FACTORY = "jms/TestConnectionFactory";
	private static final String QUEUE_JNDI_NAME = "jms/TestJMSQueue";
	private static boolean read = true;

	public static void main(String[] args) {
		QReciever q = new QReciever();
		q.reciever(q);
	}
	
	
	public void reciever(QReciever q) {
		 System.out.println("started recieving the message");
		 QueueConnectionFactory qConFact  = null;
		 QueueConnection qCon = null;
		 QueueSession qSession = null;
		 Queue queue = null;
		 QueueReceiver qRec = null;
		 Properties p = new Properties();
		 p.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		 p.put(Context.PROVIDER_URL, PROVIDER_URL);
		 try {
			InitialContext ctx = new InitialContext(p);
			qConFact = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
			qCon = qConFact.createQueueConnection();
			qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(QUEUE_JNDI_NAME);
			qRec = qSession.createReceiver(queue);
			qRec.setMessageListener(this);
			qCon.start();
			
			System.out.println("JMS Ready To Receive Messages. send \"end\" to quite recieving");
		    // Wait until a "end" message has been received.
			synchronized(q) {
				while(read){
					try {
						q.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				qRec.close();
				qSession.close();
				qCon.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} 

		
	}

	@Override
	public void onMessage(Message msg) {
		System.out.println("entered on message");
		String msgStr = null ; 
		if(msg instanceof TextMessage) {
			try {
				msgStr =((TextMessage) msg).getText();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msgStr =msg.toString() ;
		}		   
		System.out.println("Message Recieved: "+msgStr);
		if(msgStr.equals("end")) {
			read = false;
			this.notifyAll();
		}
		
	}

	
}

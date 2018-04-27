package com.niranjan.queueSender;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
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


public class MyQueueSenderFile {
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
			System.out.println("sending the message ...");
			InitialContext ctx = new InitialContext(p);
			//initializing necessary objects for sending message
			qConFact = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);			
			qCon = qConFact.createQueueConnection();			
			qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			q = (Queue) ctx.lookup("jms/TestJMSQueue");
			qSender = (QueueSender) qSession.createSender(q);
			String filepath = "file/launchConfigurationHistory.xml";
			File file = new File(filepath);
			
			MyFile myFile = new MyFile();
			myFile.setFilename(file.getName());
			myFile.setMIMEType(new MimetypesFileTypeMap().getContentType(file));
			myFile.setBytes(Files.readAllBytes(file.toPath()));
			
			BytesMessage msg = qSession.createBytesMessage();
			msg.setStringProperty("filename", myFile.getFilename());
			msg.setStringProperty("mimeType", myFile.getMIMEType());
			msg.writeBytes(myFile.getBytes());
			qSender.send(msg);
			System.out.println("message sent successfully");
		}catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			qSender.close();
			qSession.close();
			qCon.close();

		}


	}

}

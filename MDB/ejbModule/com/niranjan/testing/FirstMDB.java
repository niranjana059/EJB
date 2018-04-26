package com.niranjan.testing;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: FirstMDB
 */
@MessageDriven(mappedName = "jms/TestJMSQueue" )
public class FirstMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public FirstMDB() {
        System.out.println(this.getClass().getSimpleName()+" created...");
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        System.out.println("*****Message Arrived*****");
        String messageStr = null;
        if(message instanceof TextMessage) {
        	try {
				messageStr = ((TextMessage) message).getText();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else {
        	messageStr = message.toString();
        }
        System.out.println("Recieved message :");
        System.out.println(messageStr);
        System.out.println("*****done*****");
    }

}

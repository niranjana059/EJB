package com.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Testing {

	public static void main(String[] args) {
		try {
			System.out.println("new one");
			 Properties props = new Properties();
	          props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	          props.put(Context.PROVIDER_URL, "t3://localhost:7001"); 
	         // props.put(Context.SECURITY_PRINCIPAL, "");
	        //  props.put(Context.SECURITY_CREDENTIALS, "@");
			Context ctx =  new InitialContext(props);		
			System.out.println("\n\t[WebLogicEJBClient] Got initial Context ch: "+ctx);  
			com.niranjan.stateless.VistiBeanRemote remote = (com.niranjan.stateless.VistiBeanRemote) ctx.lookup("visit#com.niranjan.stateless.VistiBeanRemote");
			//VistiBeanRemote remote = ctx.lookup("");
			System.out.print(remote.sayHi());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

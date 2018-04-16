package com.niranjan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MessageController
 */
@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		try {
			System.out.println("new one");
			 Properties props = new Properties();
	          props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	          props.put(Context.PROVIDER_URL, "t3://localhost:7001"); 
	         // props.put(Context.SECURITY_PRINCIPAL, "weblogic");
	        //  props.put(Context.SECURITY_CREDENTIALS, "9164hp517690@");
			Context ctx =  new InitialContext(props);		
			System.out.println("\n\t[WebLogicEJBClient] Got initial Context: "+ctx);  
			com.niranjan.stateless.VistiBeanRemote remote = (com.niranjan.stateless.VistiBeanRemote) ctx.lookup("visitBean#com.niranjan.stateless.VistiBeanRemote");
			//VistiBeanRemote remote = ctx.lookup("");
			out.print(remote.sayHi());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

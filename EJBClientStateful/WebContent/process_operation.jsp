<%@page import="com.niranjana.stateful.BankRemote"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.niranjan.*" %>
<%
BankRemote bank = (BankRemote) session.getAttribute("bank");
String operation = request.getParameter("operation");
Integer amount = Integer.parseInt(request.getParameter("amount")); 

if(operation.equals("deposit")){
	bank.deposite(amount);
	out.println("deposited successfully");
}else if(operation.equals("withdraw")){
	boolean flag = bank.withdraw(amount);
	if(flag){
		out.println("withdrawn successfully");
	}else{
		out.println("please enter lesser amount than amount in the account");
	}
}else{
	out.println("balance is "+ bank.getBalance());
}

 

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="operation.jsp"></jsp:include>
</body>
</html>
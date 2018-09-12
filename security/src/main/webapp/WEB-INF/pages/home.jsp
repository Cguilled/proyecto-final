<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Mensaje : ${mensaje}</h1>

	<sec:authorize access="hasAuthority('USER')"> <!-- para determinado rol -->
	
	Bienvenido <sec:authentication property="principal.username" /> esto lo ves porque eres user
	
	</sec:authorize>
	
	<sec:authorize access="hasAuthority('PROUSER')"> <!-- para determinado rol -->
	
	Y esto NO lo ves porque NO eres prouser
	
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()"> <!-- para determinado rol -->
	
	<form id="logout" action="${pageContext.request.contextPath}/doLogout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
	<br/><a href="#" onclick="document.getElementById('logout').submit()" >Salir</a>
	
	</sec:authorize>
	
	<br/><a href="${pageContext.request.contextPath}/user/accion">Accion prohibida para probar</a>
	
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>

	<h1>Logout</h1>

	<form action="<%=request.getContextPath()%>/logout" method="POST">
		<input type="submit" value="Logout" />
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</body>
</html>
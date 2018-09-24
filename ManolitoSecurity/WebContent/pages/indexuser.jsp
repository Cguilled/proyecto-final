<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina segura</title>
</head>
<body>
  <div class="container">
    <h1>Index ROL_USER</h1>
    <p>
      Bienvenido,  <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
  </div>
</body>
</html>
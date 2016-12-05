<%--
  Created by IntelliJ IDEA.
  User: x110277
  Date: 11/09/2016
  Time: 02:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <p><form method="GET" action="${pageContext.request.contextPath}/reportrun"><input type="submit" value="Force A Report Run"/></form></p>
</body>
</html>

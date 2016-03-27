<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/login" var="loginUrl"/>
<c:url value="/logout" var="logoutUrl"/>
<!doctype html>
<html>
<head>
  <title>Hello, Jetty!</title>
</head>
<body>
  <h1>Hello World</h1>
  <c:choose>
    <c:when test="${empty pageContext.request.userPrincipal}">
      <div>Please <a href="${loginUrl}">log in</a></div>
    </c:when>
    <c:otherwise>
      <div>Welcome again, <c:out value="${pageContext.request.userPrincipal.name}"/></div>
      <form method="post" action="${logoutUrl}">
  	    <button type="submit">Log out</button>
      </form>
    </c:otherwise>
  </c:choose>
  <div>This page was rendered at <span id="date">${date}</span></div>
</body>
</html>
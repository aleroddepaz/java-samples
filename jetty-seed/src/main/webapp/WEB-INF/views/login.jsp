<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
  <title>Hello, Jetty!</title>
</head>
<body>
  <form method="post" action="${pageContext.request.contextPath}/j_security_check">
    <c:if test="${loginError}">
      <div id="login-error">Incorrect username or password</div>
    </c:if>
    <div>
      <label for="username">Username:</label>
      <input id="username" name="j_username" type="text"/>
    </div>
    <div>
      <label for="password">Password:</label>
      <input id="password" name="j_password" type="password"/>
    </div>
    <button type="submit">Log in</button>
  </form>
</body>
</html>
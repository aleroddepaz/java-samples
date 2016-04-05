<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>JAX-WS client webapp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<style type="text/css">
body {
	padding: 10px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">

      <!-- Form -->
      <div class="col-md-4">
        <form action="" method="post" class="form-horizontal well">
          <div class="form-group">
            <label for="linea" class="col-md-4 control-label">L&iacute;nea:</label>
            <div class="col-md-8">
              <input id="linea" name="linea" type="text" value="${linea}" class="form-control" />
            </div>
          </div>

          <div class="form-group">
            <label for="parada" class="col-md-4 control-label">Parada:</label>
            <div class="col-md-8">
              <input id="parada" name="parada" type="text" value="${parada}" class="form-control" />
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
              <button class="btn btn-success" type="submit">Consultar</button>
            </div>
          </div>
        </form>
      </div>

      <!-- Results -->
      <div class="col-md-8">
        <c:if test="${not empty pasosParada}">
          <table class="table">
            <thead>
              <tr>
                <th>L&iacute;nea</th>
                <th>Ruta</th>
                <th class="text-center" colspan="2">Estimaciones</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${pasosParada}" var="paso">
                <tr>
                  <td><c:out value="${paso.linea}" /></td>
                  <td><c:out value="${paso.ruta}" /></td>
                  <td><c:out value="${paso.e1.minutos}" /> min (<c:out
                      value="${paso.e1.metros}" /> metros)</td>
                  <td><c:out value="${paso.e2.minutos}" /> min (<c:out
                      value="${paso.e2.metros}" /> metros)</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
    </div>
    <hr />
    <jsp:useBean id="now" class="java.util.Date" />
    <em>Petici&oacute;n realizada a las <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
  </div>
</body>
</html>
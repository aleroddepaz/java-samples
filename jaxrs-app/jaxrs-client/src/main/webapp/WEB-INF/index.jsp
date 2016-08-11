<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Movie webapp</title>
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
            <label for="title" class="col-md-4 control-label">Title:</label>
            <div class="col-md-8">
              <input id="title" name="title" class="form-control" />
            </div>
          </div>

          <div class="form-group">
            <label for="description" class="col-md-4 control-label">Description:</label>
            <div class="col-md-8">
              <textarea id="description" name="description" class="form-control"></textarea>
            </div>
          </div>

          <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
              <button class="btn btn-success" type="submit">Submit</button>
            </div>
          </div>
        </form>
      </div>

      <!-- Movies -->
      <div class="col-md-8">
        <c:if test="${not empty movies}">
          <table class="table">
            <thead>
              <tr>
                <th>#</th>
                <th>Title</th>
                <th>Description</th>
                <th>Year</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${movies}" var="movie">
                <tr>
                  <td><c:out value="${movie.id}"/></td>
                  <td><c:out value="${movie.title}"/></td>
                  <td><c:out value="${movie.description}"/></td>
                  <td><c:out value="${movie.year}"/></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
    </div>
    <hr />
    <jsp:useBean id="now" class="java.util.Date" />
    <em>Page requested at <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
  </div>
</body>
</html>
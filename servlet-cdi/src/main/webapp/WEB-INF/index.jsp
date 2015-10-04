<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>My web app</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
body { padding: 10px; }
</style>
</head>
<body class="container">
	<div class="row">
		<div class="well col-md-4">
			<form method="post" action="${pageContext.servletContext.contextPath}/index">
			
				<c:if test="${not empty errors}">
					<div class="alert alert-danger">
						<c:choose>
							<c:when test="${fn:length(errors) eq 1}">
								<c:out value="${errors[0]}"/>
							</c:when>
							<c:otherwise>
								<ul>
									<c:forEach items="${errors}" var="error">
										<li><c:out value="${error}"/></li>
									</c:forEach>
								</ul>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				
				<div class="form-group">
					<label for="username">Username:</label>
					<input id="username" name="username" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">Send</button>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Username</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
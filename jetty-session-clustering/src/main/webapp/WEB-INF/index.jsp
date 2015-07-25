<%@ page session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Jetty Clustering Example</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
.top20 {
	margin-top: 20px;
}
</style>
</head>
<body>
<div class="container">
	<h1>Jetty Clustering Example</h1>
	<hr/>
	
	<div class="row top20">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Attribute</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty sessionScope}">
						<tr><td colspan="2" class="text-center">( There are not any attributes in the session yet )</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${sessionScope}" var="attr">
							<tr>
								<td><c:out value="${attr.key}"></c:out>
								<td><c:out value="${attr.value}"></c:out>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<div class="row top20">
		<div class="well">
			<form class="form-inline" action="/" method="post">
				<div class="form-group">
					<label for="sessionAttributeInput">Session attribute</label>
					<input id="sessionAttributeInput" name="sessionAttribute" class="form-control" placeholder="Attribute" required>
				</div>
				
				<div class="form-group">
					<label for="sessionValueInput">Session value</label>
					<input id="sessionValueInput" name="sessionValue" class="form-control" placeholder="Value" required>
				</div>
				
				<button type="submit" class="btn btn-success">Submit</button>
			</form>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-6">
			<em>Current time is <fmt:formatDate value="${time}" pattern="HH:mm:ss dd/MM/yyyy" /></em>
		</div>
		<div class="col-md-6 text-right"><a href="/logout">Logout</a></div>
	</div>
</div>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
</body>
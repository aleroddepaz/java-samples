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
		<div class="col-md-12">
			<h2>404 - Page not found!</h2>
			<div class="alert alert-warning">
				<span>The requested page cannot be found.</span>
				<a href="${pageContext.servletContext.contextPath}/index">Back to index &rarr;</a>
			</div>
		</div>
	</div>
</body>
</html>
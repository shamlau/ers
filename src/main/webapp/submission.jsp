<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="styles.css" type="text/css" />
<style type="text/css">
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Submit Your Submissions</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<h1 class="middlePage">Fill out Submission:</h1>
	<div class="container">

    <form role="form" style="width:400px; margin: 0 auto;">
        <h1>Put in Your Submission (Still in progress)</h1>
        
<form class="form-horizontal" role="form" method="post" action="index.php">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Amount</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="name" name="name" placeholder="Reimbursement Amount" value="">
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Reimbursement Type</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="email" name="email" placeholder="Make this a dropdown" value="">
		</div>
	</div>
	<div class="form-group">
		<label for="message" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="message"></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<input id="submit" name="submit" type="submit" value="Send" class="btn btn-primary">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<! Will be used to display an alert to the user>
		</div>
	</div>
</form>
    </form>
</div>
</body>
</html>
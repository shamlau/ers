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
        
        <div class="required-field-block">
            <input type="text" placeholder="Amount" class="form-control">
            <div class="required-icon">
                <div class="text">*</div>
            </div>
        </div>
        
        <div class="required-field-block">
            <input type="text" placeholder="Type" class="form-control">
            <div class="required-icon">
                <div class="text">*</div>
            </div>
        </div>
        
        <button class="btn btn-primary">Send</button>
    </form>
</div>
</body>
</html>
<!-- This is the page that displays all your personal tables -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>

<title>Reimbursements</title>
<!-- Latest compiled and minified CSS -->
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.css">

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
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.js">
	
</script>
<link rel="stylesheet" href="styles.css" type="text/css" />

</head>
<%@ include file="navbar.jsp"%>

<body>
	<h1 class="middlePage">Welcome to Your Personal Reimbursements, <%= request.getSession().getAttribute("username") %></h1>
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>#</th>
				<th>Amount</th>
				<th>Description</th>
				<th>Submitted</th>
				<th>Resolved</th>
				<th>Status</th>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reimb" items="${reimbursements}">
				<c:choose>
					<c:when test="${reimb.author.username == uname}">
						<tr>
							<td><c:out value="${reimb.reimbId }" /></td>
							<td><fmt:formatNumber type="currency"
									value="${reimb.reimbAmount }" /></td>
							<td><c:out value="${reimb.description }" /></td>
							<td><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${reimb.submitted }" /></td>
							<td><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${reimb.resolved }" /></td>
							<td><c:out value="${reimb.status.reimbStatus }" /></td>
							<td><c:out value="${reimb.type.reimbType }"></c:out>
						</tr>
					</c:when>

				</c:choose>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<br />
	
	<h3 class="middlePage">Submit New Reimbursement</h3>
	<br />
	<div class="container">
		<form action="submit.do" method="post">
			<div class="form-group">
				<label>Reimbursement Amount</label> <input
					type="number" min="0" step="0.01" class="form-control" id="reimbAmount" name="reimbAmount"
					aria-describedby="emailHelp" placeholder="Enter the Amount for Reimbursement">
			</div>
			<div class="form-group">
				<label for="exampleSelect1">Reimbursement Type</label> <select
					class="form-control" id="exampleSelect1" name="type" placeholder="Select Reimbursement Type">
					<option>Business</option>
					<option>Lodging</option>
					<option>Travel</option>
					<option>Food</option>
					<option>Other</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleTextarea">Enter Description of the Reimbursement. Please Enter Serious Comments</label>
				<textarea class="form-control" id="exampleTextarea" rows="3" name="description"></textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputFile">File input</label> <input type="file"
					class="form-control-file" id="exampleInputFile"
					aria-describedby="fileHelp"> <small id="fileHelp"
					class="form-text text-muted">This is a placeholder. Not functional yet. Please don't actually insert Blobs yet.</small>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>

</html>
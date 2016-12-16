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

<title>Login Page</title>
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
<title>Reimbursements</title>
</head>
<%@ include file="navbar.jsp"%>

<body>
	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>Reimbursement Number</th>
				<th>Username</th>
				<th>Amount</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reimb" items="${reimbursements}">
				<tr>
					<td><c:out value="${reimbursements.reimbId } }"/></td>
					<td><c:out value="${reimbursements.author.userId } }"/></td>
					<td><c:out value="${reimbursements.reimbAmount } }"/></td>
					<td><c:out value="${reimbursements.reimbId } }"/></td>
					<td><c:out value="${reimbursements.reimbId } }"/></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
<script>
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>

</html>
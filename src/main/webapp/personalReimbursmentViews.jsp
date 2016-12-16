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
<title>PersonalReimbursements</title>
</head>
<%@ include file="navbar.jsp"%>

<body>
	<div class="container">
		<h3 class="middlePage">Here Are Your Reimbursements</h3>
		<hr>
		<div class="row">
			<div class="panel panel-primary filterable">
				<div class="panel-heading">
					<h3 class="panel-title">Reimbursements</h3>
					<div class="pull-right">
						<button class="btn btn-default btn-xs btn-filter">
							<span class="glyphicon glyphicon-filter"></span> Filter
						</button>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr class="filters">
							<th><input type="text" class="form-control"
								placeholder="Reimbursement ID" disabled></th>
							<th><input type="text" class="form-control"
								placeholder="Amount" disabled></th>
							<th><input type="text" class="form-control"
								placeholder="Date Submitted" disabled></th>
							<th><input type="text" class="form-control"
								placeholder="Status ID" disabled></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>100.00</td>
							<td>11-12-13</td>
							<td>1</td>
						</tr>
						<tr>
							<td>31</td>
							<td>1099898.00</td>
							<td>11-23-14</td>
							<td>1</td>
						</tr>
						<tr>
							<td>5</td>
							<td>123.00</td>
							<td>08-12-12</td>
							<td>2</td>
						</tr>
						<tr>
							<td>12</td>
							<td>1223.00</td>
							<td>01-12-12</td>
							<td>3</td>
						</tr>
						<tr>
							<td>35</td>
							<td>11223.00</td>
							<td>08-14-92</td>
							<td>3</td>
						</tr>
						<tr>
							<td>55</td>
							<td>1213.00</td>
							<td>08-02-12</td>
							<td>1</td>
						</tr>

						<tr>
							<td>135</td>
							<td>12.00</td>
							<td>01-11-12</td>
							<td>2</td>
						</tr>

						<tr>
							<td>9</td>
							<td>123.10</td>
							<td>02-12-12</td>
							<td>1</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
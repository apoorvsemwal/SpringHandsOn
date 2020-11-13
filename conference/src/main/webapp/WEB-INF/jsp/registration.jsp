<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<style>
	.error {
		color: #ff0000;
	}
	
	.errorblock {
		color: #000;
		background-color: #ffEEEE;
		border: 3ex solid #ff0000;
		padding: 8px;
		margin: 16px;
	}
</style>
</head>
<body>
	<h1>Registration</h1>
	<form:form modelAttribute="registration">
	<form:errors path="*" cssClass="errorBlock" element="div"></form:errors>
		<table>
			<tr>
				<td>Name:</td>
				<td>
					<form:input path="name" />
				</td>
				<td>
					<form:errors path="name" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Add Registration">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
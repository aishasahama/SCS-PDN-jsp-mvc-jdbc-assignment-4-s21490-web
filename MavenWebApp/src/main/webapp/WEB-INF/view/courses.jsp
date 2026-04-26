<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>courses page</title>
</head>
<body>
	<h2>Available courses</h2>
	
	<table>
		
		<tr>
			<th>Course Id</th>
			<th>Course Name</th>
			<th>Instructor</th>
			<th>Credits</th>
		</tr>
		
		<c:if test="${not empty courses}">
		<c:forEach var="c" items="${courses}">
		<tr>
			<td>${c.courseId}</td>
			<td>${c.name}</td>
			<td>${c.instructor}</td>
			<td>${c.credits}</td>
		</tr>
		</c:forEach>
		</c:if>
		
		
		
	</table>
</body>
</html>
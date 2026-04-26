<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
	<h2>Login</h2>
	
	<p style="color:red;"> ${error}</p>
	
	<form action="login" method="post">
		
		Email     : <input type="text" name="email"/><br><br>
		Password  : <input type="password" name="password"/><br><br>
		
		<input type="submit" value="login"/>
			
	</form>

</body>
</html>
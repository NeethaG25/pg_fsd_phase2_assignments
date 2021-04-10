<html>
<body>
<%
/* Check whether the user is already logged in */
if(session.getAttribute("userName") != null)
{
	/* Redirect to dashboard */
	response.sendRedirect("dashboard.jsp");
}
%>
<h2>Login</h2>
<hr>
<form action="authenticate">
<input type="text" name = "userName" placeholder="Your Name"><br>
<input type="submit" value = "LOGIN">
</form>
</body>
</html>

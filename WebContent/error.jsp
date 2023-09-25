<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>  
<body>

<script type="text/javascript">
//scriptlet tag -in this we can write complete java code 
<%
try
{
	String ms = request.getParameter("msg"); 
	if (ms.equals("abc"))
	{%>
		alert("All fields are mandatory");
		window.history.back();
	<%}
	else if (ms.equals("man"))
	{%>
		alert("All fields are mandatory");
		window.history.back();
	<%}
	else if (ms.equals("failed"))
	{%>
		alert("Please enter valid credentials");
		window.history.back();
	<%}
	else if (ms.equals("phone"))
	{%>
		alert("Please enter valid phone number");
		window.history.back();
	<%}
	else if (ms.equals("date"))
	{%>
		alert("Please enter valid date! You should be 18 to give vote");
		window.history.back();
	<%}
	
	else if (ms.equals("otp"))
	{%>
		alert("Otp Not Matched. Please enter correct one.");
		window.history.back();
	<%}
	else if (ms.equals("PasswordSendToMail"))
	{%>
		alert("Your Website Password has sent to your mail id.");
		window.location.href="login.jsp";
	<%}
	else if (ms.equals("email"))
	{%>
		alert("enter existing,deliverable and valid email id");
		window.history.back();
	<%}
	else if (ms.equals("exists"))
	{%>
		alert("User already exists");
		window.location.href="login.jsp";
	<%}
	else if (ms.equals("newpassword"))
	{%>
		alert("Choose Unique password , password must not like old password");
		window.location.href="changepassword.jsp";
	<%}
	else if (ms.equals("password"))
	{%>
		alert("enter valid password:At least one uppercase letter,one lowercase letter,one digit,one special character (e.g., @, #, $, %, ^, &, +, =, !),Minimum length of 8 characters");
		window.history.back();
	<%}
}
catch(Exception e)
{
	e.printStackTrace();
}	
%>
</script>
Error stat
</body>
</html>

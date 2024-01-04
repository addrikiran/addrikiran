<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
        
        HttpSession session1 = request.getSession(false);
        if (session1 != null && session1.getAttribute("username") != null) {
            String username = (String) session1.getAttribute("username");
    %>

      <div class="container">
        <h1>Welcome, <%= username %>!</h1>
        <p>I am delighted to have you on my platform.</p>
        <p>Feel free to stay as long as you like, and when you're ready,</p> 
        you can <a href="logout.jsp">LOGOUT</a> securely.
    </div>

    <%
        } else {
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
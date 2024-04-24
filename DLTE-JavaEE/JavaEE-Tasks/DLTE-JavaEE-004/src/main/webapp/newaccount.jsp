<%--
  Created by IntelliJ IDEA.
  User: xxshetur
  Date: 4/22/2024
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("username") != null) {
%>

<%
    String info = (String) request.getAttribute("info");
%>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color:green;">
    <div class="container-fluid justify-content-between">
        <a class="navbar-brand" href="#">MYBANK</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <p class="text-light display-6">Hi, <%=session.getAttribute("username")%></p>
                </li>
                <li class="nav-item active">
                    <a class="btn btn-outline-light rounded-5 me-2" href="#">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="btn btn-outline-light rounded-5 me-2" href="newaccount.jsp">Create Account</a>
                </li>
                <li class="nav-item active">
                    <a href="logout" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-door-open"></span> Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <% if (info != null && info != "") { %>
    <h1 class="text-center text-success"><%= info %></h1>
    <% } %>
    <h1>Create Account</h1>
    <form action="create" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">User Name</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="text" id="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" id="address" name="address" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="contact" class="form-label">Contact</label>
            <input type="number" id="contact" name="contact" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="balance" class="form-label">Balance</label>
            <input type="number" id="balance" name="balance" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<% } else {
    response.sendRedirect("index.jsp");
} %>

</body>
</html>

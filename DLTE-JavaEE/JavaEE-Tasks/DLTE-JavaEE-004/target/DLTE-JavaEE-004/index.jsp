
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-button {
            background-color: #6c757d;
            color: #fff;
            border: none;
            border-radius: 5px;
        }

        body {
            background-color:cadetblue;
            background-size: cover;
            background-attachment: fixed;
        }
        .login-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5);
        }
    </style>
</head>
<body>
<form method="post" action="login">
    <div class="container login-container">
        <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 col-sm-8 col-10">
                <div class="login-box"><h1>Login</h1>
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                    </div>
                    <div class="row justify-content-around">
                        <button id="loginbutton" type="submit" class="col-4 btn login-button bi bi-box-arrow-in-right"> Login</button>
<%--                        <button id="cancel" class="col-4 btn btn-secondary bi bi-x-circle-fill"> Cancel</button>--%>
                        <a href="index.jsp" id="cancel" class="col-4 btn btn-secondary bi bi-x-circle-fill"> Cancel</a>
                    </div>
                </div>
            </div>
        </div>
    </div>



</form>
</body>
<script>
    document.getElementById("cancel").addEventListener("click", function() {
        window.location.href = "index.jsp";
    });
</script>
</html>
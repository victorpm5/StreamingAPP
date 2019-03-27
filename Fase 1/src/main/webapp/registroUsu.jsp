<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
    <%--<link href="css/fontawesome.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <%--<link href="css/all.css" rel="stylesheet">--%>
</head>

<body>

    <%  HttpSession sessioUsuari= (HttpSession) request.getSession();
        String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

        //Si entrem aquí és pq no hi ha sessió
        if(StringUtils.isNotBlank(usuariLogged)){
            response.sendRedirect("listadoVid");
        };
    %>

    <div class="container login-container">

        <div class="login-form">

            <h2>Crea una compta</h2>

            <form action="login" method="POST">

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" placeholder="Introdueix el teu nom" type="text">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="surname" class="form-control" placeholder="Introdueix el teu cognom" type="text">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="Introdueix el teu email" type="email">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="username" class="form-control" placeholder="Introdueix un nom d'usuari" type="text">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="Introdueix una contrasenya" type="password">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password2" class="form-control" placeholder="Repeteix la contrasenya" type="password">
                </div>

                <div class="form-group">
                    <button name="action" value="REGISTER" type="submit" class="btn btn-primary btn-block"> Registrar-se</button>
                </div>

                <p class="text-center">Ja tens una compta? <a href="./login.jsp">Inicia sessió</a> </p>

            </form>

            <%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

        </div>

    </div>

</body>

</html>

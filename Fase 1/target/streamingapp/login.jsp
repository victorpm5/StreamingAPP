<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/custom.css" rel="stylesheet">
    </head>

    <body class="text-center">

        <%  HttpSession sessioUsuari= (HttpSession) request.getSession();
            String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

            //Si entrem aquí és pq no hi ha sessió
            if(StringUtils.isNotBlank(usuariLogged)){
                response.sendRedirect("listadoVid");
            };
        %>

        <div class="container login-container">
            <div class="login-form">

                <h2>Inicia sessió</h2>

                <form action="login" method="POST">

                    <input name="username" type="text" id="inputEmail" class="form-control" placeholder="Nom d'usuari" required autofocus>
                    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Contrasenya" required>

                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="LOGIN">Continuar</button>
                </form>

                <a href="./registroUsu.jsp">Registra't</a>

                <%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

            </div>
        </div>

    </body>

</html>

<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body class="text-center">

        <%  HttpSession sessioUsuari= (HttpSession) request.getSession();
            String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

            //Si entrem aquí és pq no hi ha sessió
            if(StringUtils.isNotBlank(usuariLogged)){
                response.sendRedirect("listadoVid");
            };
        %>

        <form class="form-signin" action="login" method="POST">

            <h1 class="h3 mb-3 font-weight-normal">Inicia sessió</h1>

            <label for="inputEmail" class="sr-only"> Introdueix el teu nom d’usuari</label>
            <input name="username" type="text" id="inputEmail" class="form-control" placeholder="Nom d'usuari" required autofocus>
            <label for="inputPassword" class="sr-only">Introdueix la teva contrasenya</label>
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Contrasenya" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="LOGIN">Sign in</button>
        </form>

        <a href="./registroUsu.jsp">Register</a>

        <%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

    </body>

</html>

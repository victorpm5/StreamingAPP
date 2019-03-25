<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
    <head>
    </head>

    <body>

        <%  HttpSession sessioUsuari= (HttpSession) request.getSession();
            String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

            //Si entrem aquí és pq no hi ha sessió
            if(StringUtils.isNotBlank(usuariLogged)){
                response.sendRedirect("listadoVid");
            };
        %>

        <h2>Pàgina bàsica de Login</h2>
        <form action="login" method="POST">

            Introdueix el teu nom d’usuari
            <input type="text" name="username"/><br>

            Introdueix la teva contrasenya
            <input type="password" name="password"/>

            <input type="submit" name="action" value="LOGIN">
        </form>

        <a href="./registroUsu.jsp">Register</a>

        <%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

    </body>

</html>

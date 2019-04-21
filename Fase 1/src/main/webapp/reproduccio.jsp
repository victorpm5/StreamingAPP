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
            if(StringUtils.isBlank(usuariLogged)){
                response.sendRedirect("login.jsp");
            };
        %>

        <div class="container login-container">
            <div class="login-form">

                <h2><%=request.getAttribute("titol")%></h2>

                <iframe width="420" height="315"
                        src="<%=request.getAttribute("url")%>">
                </iframe>

                <a href="./listadoVid">Llista de vídeos</a>

            </div>
        </div>

    </body>

</html>

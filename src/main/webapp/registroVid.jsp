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
    if(StringUtils.isBlank(usuariLogged)){
        response.sendRedirect("login.jsp");
    };
%>

<div class="container login-container">
    <div class="login-form">
        <h2>Registra un vídeo</h2>

        <form action="newvideo" method="POST">

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-heading"></i> </span>
                </div>
                <input name="title" class="form-control" placeholder="Introdueix el títol del vídeo" type="text" required>
            </div>

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input name="autor" class="form-control" placeholder="Introdueix el seu autor" type="text" required>
            </div>

            Introdueix la data de publicació:
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
                </div>
                <input name="fechaCreacion" class="form-control" placeholder="Introdueix la data de creació" type="date" required>
            </div>

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-clock"></i> </span>
                </div>
                <input name="duracion" class="form-control" placeholder="Introdueix la seva duració" type="number" step=".01" min="0" required>
            </div>

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-align-justify"></i> </span>
                </div>
                <textarea name="descripcion" class="form-control" placeholder="Introdueix la descripció del vídeo" type="text"></textarea>
            </div>

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-file-video"></i> </span>
                </div>
                <input name="formato" class="form-control" placeholder="Introdueix el fornmat del vídeo" type="text" required>
            </div>

            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-link"></i> </span>
                </div>
                <input name="url" class="form-control" placeholder="Introdueix l'enllaç al vídeo" type="text" required>
            </div>

            <div class="form-group">
                <button name="register" type="submit" class="btn btn-primary btn-block">Registrar vídeo</button>
            </div>

        </form>

        <a href="./listadoVid">Llista de vídeos</a>

    </div>
</div>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>

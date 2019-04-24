<%@ page import="com.isdcm.streamingapp.services.soap.Video" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="container video-list-container">
        <div class="login-form">

            <h2>Llistat de vídeos</h2>

            <div>
                <h4>Filtres:</h4>
                <form action="listadoVid" method="POST">
                    <div class="filterContainer">
                        <div class="form-group input-group margin-group">
                            <input name="valuetitle" class="form-control" placeholder="Introdueix el títol del vídeo" type="text">
                            <button name="title" type="submit" class="btn btn-primary btn-block">Filtrar per títol</button>
                        </div>

                        <div class="form-group input-group margin-group">
                            <input name="valueautor" class="form-control" placeholder="Introdueix l'autor del vídeo" type="text">
                            <button name="autor" type="submit" class="btn btn-primary btn-block">Filtar per autor</button>
                        </div>

                        <div class="form-group input-group margin-group">
                            <input name="valueyear" class="form-control" placeholder="Introdueix l'any creació" type="number">
                            <button name="year" type="submit" class="btn btn-primary btn-block">Filtrar per any</button>
                        </div>
                    </div>
                    <button name="all" type="submit" class="btn btn-primary btn-block margin-group">Borrar Filtres</button>
                </form>
            </div>

            <table class="table video-table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Títol</th>
                    <th scope="col">Autor</th>
                    <th scope="col">Data de Creació</th>
                    <th scope="col">Duració</th>
                    <th scope="col">Visualitzacions</th>
                    <th scope="col">Descripció</th>
                    <th scope="col">Format</th>
                    <th scope="col">Url</th>
                </tr>
                </thead>
                <% List<Video> videos = (List<Video>)request.getAttribute("videos"); %>
                <% SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy"); %>
                <% for(int i = 0; i < videos.size(); i+=1) { %>
                <tr>
                    <td><%=videos.get(i).getTitle()%></td>
                    <td><%=videos.get(i).getAutor()%></td>
                    <td><%=dt.format(videos.get(i).getFechaCreacion().toGregorianCalendar().getTime())%></td>
                    <td><%=videos.get(i).getDuracion()%> min</td>
                    <td><%=videos.get(i).getNumReproducciones()%></td>
                    <td><%=videos.get(i).getDescripcion()%></td>
                    <td><%=videos.get(i).getFormato()%></td>
                    <td>
                        <form action="reproduccio" method="POST">
                            <input type="hidden" name="id" value="<%=videos.get(i).getId()%>">
                            <input type="hidden" name="url" value="<%=videos.get(i).getUrl()%>">
                            <input type="hidden" name="titol" value="<%=videos.get(i).getTitle()%>">
                            <button type="submit">Visualitzar</button>
                            <%--<a target="_blank" href="/reproduccio?id=<%=videos.get(i).getId()%>">Visualitzar!</a>--%>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>

            <div style="display:flex; justify-content: space-between;">
                <a href="./registroVid.jsp">Afegeix un vídeo!</a>
            </div>

        </div>
    </div>

</html>
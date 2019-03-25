<%@ page import="com.isdcm.streamingapp.models.Video" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre</h2>
</body>

<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Publish Date</th>
        <th>Duration</th>
        <th>Visualizations</th>
        <th>Description</th>
        <th>Format</th>
        <th>Url</th>
    </tr>
    <% List<Video> videos = (List<Video>)request.getAttribute("videos"); %>
    <% SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy"); %>
    <% for(int i = 0; i < videos.size(); i+=1) { %>
        <tr>
            <td><%=videos.get(i).getTitle()%></td>
            <td><%=videos.get(i).getAutor()%></td>
            <td><%=dt.format(videos.get(i).getFechaCreacion())%></td>
            <td><%=videos.get(i).getDuracion()%></td>
            <td><%=videos.get(i).getNumReproducciones()%></td>
            <td><%=videos.get(i).getDescripcion()%></td>
            <td><%=videos.get(i).getFormato()%></td>
            <td><%=videos.get(i).getUrl()%></td>
        </tr>
    <% } %>
</table>

<a href="./registroVid.jsp">Add Video</a>

</html>
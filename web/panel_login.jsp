<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.datos.DatosMinSalud"%>
<%@page import="java.util.List"%>
<%
    if (session.getAttribute("logeado") != "ok") {
            response.sendRedirect("login.jsp");
        }
%>
<%
    List<DatosMinSalud> lista = (List<DatosMinSalud>)request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <body>
         <h1>listado de blog </h1>
         <p>
             <a href="NuevaEntrada?op=nuevo">Nuevo blog</a>
         </p>
         <table border="1">
             <tr>
                 <th>ID</th>
                 <th>fecha</th>
                 <th>titulo</th>
                 <th>comentarip</th>
                 <th></th>
             </tr>
             <c:forEach var="item" items="${lista}">
             <tr>
                <th>${item.id}</th>
                 <th>${item.fecha}</th>
                 <th>${item.titulo}</th>
                 <th>${item.comentario}</th>
                 <td><a href="NuevaEntrada?op=editar&id=${item.id}">Editar</a></td>
                 <td><a href="NuevaEntrada?op=eliminar&id=${item.id}">Eliminar</a></td>
             </tr>
             </c:forEach>
         </table>
    </body>
</html>

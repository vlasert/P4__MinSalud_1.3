<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.datos.DatosMinSalud"%>
<%
    DatosMinSalud libro =(DatosMinSalud)request.getAttribute("libro");
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>tabla para llenar </h1>
        <form action="NuevaEntrada" method="post">
            <h1><c:if test="${item.id == 0}">Nuevo libro</c:if>
                <c:if test="${item.id != 0}">Editar libro</c:if>
            </h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>  entrada</th>
                    </tr>
                </thead>
                <tbody>
                <input type="hidden" name="id" value="${item.id}">
                    <tr>
                        <td>fecha</td>
                        <td><input type="date" name="fecha" value="${item.fecha}"></td>
                    </tr>
                    <tr>
                        <td>titulo</td>
                        <td><input type="text" name="titulo" value="${item.titulo}"></td>
                    </tr>
                    <tr>
                        <td>contenido</td>
                        <td><input type="text" name="comentario" value="${item.contenido}"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="enviar" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>

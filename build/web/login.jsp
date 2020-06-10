<%-- 
    Document   : login
    Created on : 07-jun-2020, 9:56:38
    Author     : vlady
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>iniciar sesion</h1>
        <!-- ya que es un espacio para llenar datos, hay que crear un formulario para el controlador-->
        <form action="LoginControler" method="post">
            <label>Usuario</label>
            <input type="text" name="usuario">
            <br>
            <label>contrasena</label>
            <input type="password" name="password">
            <br>
            <input type="submit" value="ingresar">
        </form>
    </body>
</html>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controladores;

import com.emergentes.dao.Conexion_BD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginControler", urlPatterns = {"/LoginControler"})
public class LoginControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //solicitar un dato para autenticar el login e implementar una accion de condicional 
        String action = (request.getParameter("action") == null ) ? "view" : request.getParameter("action");
            //equals (igual a ) var action == view?
        if (action.equals("view") ) {
            
            //redireccionar a login
            response.sendRedirect("login.jsp");
        }
        if (action.equals("logout")) {
            //se compara un parametro para cerrar la secion 
            //CERRAR SECION
            HttpSession ses = request.getSession();
            
            //eliminar todas las seciones
            ses.invalidate();
            
            //redireccionar a login.jsp
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            
            //crear un canal para ver si estan los datos
            Conexion_BD canal = new Conexion_BD();
            
            //llamar a la libreria connection y ponerlo enuna variable CN  para el canal
            Connection cn = canal.conectar();
            
            //crear una variable para generar la consulta
            String sql = "select * from usuarios where usuario = ? and password = ? limit 1";
            
            //preparar una declaracion (preparedStatement)
            PreparedStatement dec = cn.prepareStatement(sql);
            
            //prepara para obtener los parametros de usuario y password
            dec.setString(1, usuario);
            dec.setString(2, password);
            
            //ejecucion de la consulta
            ResultSet res ;
            
            res = dec.executeQuery();
            
            //comprobar si coinciden el usuario y contrasena
            if (res.next()) {
                HttpSession ses = request.getSession();
                ses.setAttribute("logeado", "ok");
                ses.setAttribute("usuario", usuario);
                
                //enviar el resultado optenido
                response.sendRedirect("panel_login.jsp");
            }
            else{
                response.sendRedirect("login.jsp");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controladores;

import com.emergentes.dao.Conexion_BD;
import com.emergentes.datos.DatosMinSalud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vlady
 */
@WebServlet(name = "NuevaEntrada", urlPatterns = {"/NuevaEntrada"})
public class NuevaEntrada extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";

        ArrayList<DatosMinSalud> lista = new ArrayList<DatosMinSalud>();

        Conexion_BD canal = new Conexion_BD();
        Connection conn = canal.conectar();

        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")) {
            try {       
                String sql = "select * from Datos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    DatosMinSalud dat = new DatosMinSalud();
                    dat.setId(rs.getInt("id"));
                    dat.setFecha(rs.getString("fecha"));
                    dat.setTitulo(rs.getString("titulo"));
                    dat.setContenido(rs.getString("comentario"));

                    lista.add(dat);
                }
                //enviar 
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("panel_login.jsp").forward(request, response);

            } catch (SQLException ex) {
                System.out.println("error en sql" + ex.getMessage());
            } finally {
                canal.desconectar();
            }
        }

        if (op.equals("nuevo")) {
            DatosMinSalud l = new DatosMinSalud();
            request.setAttribute("libro", l); //ojo tal ves sea DATOS de mysql
            request.getRequestDispatcher("llenarDatos.jsp").forward(request, response);
        }
        if (op.equals("editar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from Datos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                rs = ps.executeQuery();
                
                DatosMinSalud li = new DatosMinSalud();
                
                while(rs.next()){
                    li.setId(rs.getInt("id"));
                    li.setFecha(rs.getString("fecha"));
                    li.setTitulo(rs.getString("titulo"));
                    li.setContenido(rs.getString("contenido"));
                }
                request.setAttribute("libro", li);
                request.getRequestDispatcher("llenarDatos.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                System.out.print("error de sql " + ex.getMessage());
            }

        }
        if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from Datos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.print("error de sql " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("NuevaEntrada");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //creacion de las variables 
        int id = Integer.parseInt(request.getParameter("id"));
        String fecha = request.getParameter("fecha");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("comentario");

        DatosMinSalud dat = new DatosMinSalud();

        dat.setId(id);
        dat.setFecha(fecha);
        dat.setTitulo(titulo);
        dat.setContenido(contenido);

        Conexion_BD canal = new Conexion_BD();

        Connection conn = canal.conectar();
        PreparedStatement ps;

        ResultSet rs;

        if (id == 0) {
            try {
                String sql = "insert into Datos(fecha,titulo,comentario) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dat.getFecha());
                ps.setString(2, dat.getTitulo());
                ps.setString(3, dat.getContenido());
                
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.print("error de sql"+ex.getMessage());
            } finally{
                canal.desconectar();
            }
            response.sendRedirect("NuevaEntrada");
        }
        //si el registro ya existe
        else{
            try {
                String sql = "update Datos set fecha=?,titulo=?,comentario=? where id=?";
                ps = conn.prepareStatement(sql);
                
                ps.setString(1, dat.getFecha());
                ps.setString(2, dat.getTitulo());
                ps.setString(3, dat.getContenido());
                ps.setInt(4, dat.getId());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error al actualizaar"+ex.getMessage());
            }
        }
        response.sendRedirect("NuevaEntrada");
    }
}

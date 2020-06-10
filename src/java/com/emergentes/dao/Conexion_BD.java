
package com.emergentes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion_BD {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_Min_salud";
    static String usuario = "root";
    static String password = "user";
    
    public Connection conn = null;
    
    public Conexion_BD(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            
            if (conn != null) {
                System.out.println("Conexion OK");
            }
        } catch (ClassNotFoundException e) {
                System.out.println("falta especificar driver"+e.getMessage());
        } catch (SQLException e){
            System.out.print("error al abrir la base de datos "+e.getMessage());
        }
    }
    
    public Connection conectar (){
        return conn;
    }
    
    public void desconectar (){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar la base de datos "+ex.getMessage());
        }
    }
}

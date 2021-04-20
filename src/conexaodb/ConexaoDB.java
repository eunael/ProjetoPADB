/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author natan
 */
public class ConexaoDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/projetopadb";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection conectar(){
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: " + ex);
        }
    }
    
    public static void desconectar(Connection con){
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("A conexão não foi aberta ainda", ex);
        }
    }
    public static void desconectar(Connection con, PreparedStatement estado){
        desconectar(con);
        try {
            if(estado != null){
                estado.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("A conexão não foi aberta ainda", ex);
        }
    }
    public static void desconectar(Connection con, PreparedStatement estado, ResultSet rs){
        desconectar(con, estado);
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("A conexão não foi aberta ainda", ex);
        }
    }
}

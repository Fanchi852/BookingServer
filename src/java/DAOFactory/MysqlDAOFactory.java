/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fanchi
 */
public class MysqlDAOFactory {
    
    private Connection conn;
    
    private static final String USER="root";
    private static final String PASS="root";
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String URL
            = "jdbc:mysql://localhost:3306/booking?"
            + "useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&"
            + "useSSL=false"
            ;

    public MysqlDAOFactory() {
    }
    
    public Connection connect() {
        try {
            
            //¿Qué controlador necesito?
            Class.forName(DRIVER);
            // ¿Dónde está la BD, user y pass?
            conn = DriverManager.getConnection(URL, USER, PASS);
            
            // Con el objeto conexión me creo un Statement
            System.out.println("conectado");
        }catch (Exception ex) {
            System.out.println(ex.getStackTrace().toString());
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return conn;
    }
    
}

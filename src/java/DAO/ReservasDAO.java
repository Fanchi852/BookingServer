/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


 *
 * @author Fanchi
 
public class ReservasDAO implements interfaces.IDAO<Reserva, Map<String, List<String>>>{
    
    private final String DB_TABLE = "reservas";
    private final String ID_OBJECT = "id_reservas";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (id_hotel, id_cliente, habitaciones, fecha_entrada, fecha_salida) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE + " WHERE ";
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET id_hotel = ?, id_cliente = ?, habitaciones = ?, fecha_entrada = ?, fecha_salida = ?, WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public ReservasDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Boolean add (Reserva reserva){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD);
            
            query.setInt(1, reserva.getId_hotel());
            query.setInt(2, reserva.getId_cliente());
            query.setInt(3, reserva.getHabitaciones());
            query.setDate(4, reserva.getFecha_entrada());
            query.setDate(5, reserva.getFecha_salida());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Reserva reserva){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, reserva.getId_reserva());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Reserva> findAll(){
        List<Reserva> reservas = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reserva reserva = new Reserva();
                
                reserva.setId_reserva(res.getInt("id_reserva"));
                reserva.setId_hotel(res.getInt("id_hotel"));
                reserva.setId_cliente(res.getInt("id_cliente"));
                reserva.setHabitaciones(res.getInt("numero_habitaciones"));
                reserva.setFecha_entrada(res.getDate("fecha_entrada"));
                reserva.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reserva);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    public List<Reserva> find(Reserva reserva){
        List<Reserva> reservas = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, reserva.getId_reserva());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reserva reservanew = new Reserva();
                
                reservanew.setId_reserva(res.getInt("id_reserva"));
                reservanew.setId_hotel(res.getInt("id_hotel"));
                reservanew.setId_cliente(res.getInt("id_cliente"));
                reservanew.setHabitaciones(res.getInt("numero_habitaciones"));
                reservanew.setFecha_entrada(res.getDate("fecha_entrada"));
                reservanew.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reservanew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    
    public List<Reserva> findBy(Map<String, List<String>> filter){
        List<Reserva> reservas = new ArrayList<>();
        
        String where = "";
        Boolean hasAnd = false;
        
        for (String key : filter.keySet()){
            List<String> values = filter.get(key);
            
            if(hasAnd){
                where += " AND ";
            }
            hasAnd = true;
            
            where += " " + key + " IN " + values;
            
        }
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_BY + where);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reserva reservanew = new Reserva();
                
                reservanew.setId_reserva(res.getInt("id_reserva"));
                reservanew.setId_hotel(res.getInt("id_hotel"));
                reservanew.setId_cliente(res.getInt("id_cliente"));
                reservanew.setHabitaciones(res.getInt("numero_habitaciones"));
                reservanew.setFecha_entrada(res.getDate("fecha_entrada"));
                reservanew.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reservanew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    public Boolean update (Reserva reserva){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setInt(1, reserva.getId_hotel());
            query.setInt(2, reserva.getId_cliente());
            query.setInt(3, reserva.getHabitaciones());
            query.setDate(4, reserva.getFecha_entrada());
            query.setDate(5, reserva.getFecha_salida());
            query.setInt(6, reserva.getId_reserva());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}
*/
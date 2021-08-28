/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanchi
 */
public class ReservaDAO implements interfaces.IDAO<Reserva, Map<String, String[]>>{
    
    private final String DB_TABLE = "reservas";
    private final String ID_OBJECT = "id_reservas";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (id_cliente, id_hotel, habitaciones, fecha_entrada, fecha_salida) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET id_cliente = ?, id_hotel = ?, habitaciones = ?, fecha_entrada = ?, fecha_salida = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public ReservaDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Reserva reserva){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setInt(1, reserva.getId_cliente());
            query.setInt(2, reserva.getId_hotel());
            query.setInt(3, reserva.getHabitaciones());
            query.setDate(4, reserva.getFecha_entrada());
            query.setDate(5, reserva.getFecha_salida());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                reserva.setId_cliente(res.getInt("id_cliente"));
                reserva.setId_hotel(res.getInt("id_hotel"));
                reserva.setHabitaciones(res.getInt("habitaciones"));
                reserva.setFecha_entrada(res.getDate("fecha_entrada"));
                reserva.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reserva);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                reservanew.setId_cliente(res.getInt("id_cliente"));
                reservanew.setId_hotel(res.getInt("id_hotel"));
                reservanew.setHabitaciones(res.getInt("habitaciones"));
                reservanew.setFecha_entrada(res.getDate("fecha_entrada"));
                reservanew.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reservanew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    
    public List<Reserva> findBy(Map<String, String[]> filter){
        List<Reserva> reservas = new ArrayList<>();
        
        String where = " WHERE ";
        Boolean hasAnd = false;
        Boolean haswhere = false;
        
        for (String key : filter.keySet()){
            if(!key.equals("ACTION")){
                haswhere = true;
                String[] values = filter.get(key);
            
                if(hasAnd){
                    where += " AND ";
                }
                hasAnd = true;

                where += " " + key + " IN (";
                
                Integer count = 0;
                
                for(String value: values){
                    where += count != 0 ? ", ": "";
                    where += "\"" + value + "\"";
                    count ++;
                }
                where += ")";
            }
        }
        
        System.out.println("SLQ SENCENCE: " + FIND_BY + where);
        
        try {
            PreparedStatement query;
            if(haswhere){
                query = conn.prepareStatement(FIND_BY + where);
            }else{
                query = conn.prepareStatement(FIND_BY);
            }
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reserva reservanew = new Reserva();
                
                reservanew.setId_reserva(res.getInt("id_reserva"));
                reservanew.setId_cliente(res.getInt("id_cliente"));
                reservanew.setId_hotel(res.getInt("id_hotel"));
                reservanew.setHabitaciones(res.getInt("habitaciones"));
                reservanew.setFecha_entrada(res.getDate("fecha_entrada"));
                reservanew.setFecha_salida(res.getDate("fecha_salida"));
                
                reservas.add(reservanew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    public Boolean update (Reserva reserva){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setInt(1, reserva.getId_cliente());
            query.setInt(2, reserva.getId_hotel());
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

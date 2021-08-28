/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Reservahabitacion;
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
public class ReservahabitacionDAO implements interfaces.IDAO<Reservahabitacion, Map<String, String[]>>{
    
    private final String DB_TABLE = "reservahabitaciones";
    private final String ID_OBJECT = "id_reservahabitacione";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (id_reserva, id_habitacion) VALUES (?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET id_reserva = ?, id_habitacion = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public ReservahabitacionDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Reservahabitacion reservahabitacion){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setInt(1, reservahabitacion.getId_reserva());
            query.setInt(2, reservahabitacion.getId_habitacion());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Reservahabitacion reservahabitacion){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, reservahabitacion.getId_reservahabitacion());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Reservahabitacion> findAll(){
        List<Reservahabitacion> reservahabitaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reservahabitacion reservahabitacion = new Reservahabitacion();
                
                reservahabitacion.setId_reservahabitacion(res.getInt("id_reservahabitacion"));
                reservahabitacion.setId_reserva(res.getInt("id_reserva"));
                reservahabitacion.setId_habitacion(res.getInt("id_habitacion"));
                
                reservahabitaciones.add(reservahabitacion);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservahabitaciones;
    }
    
    public List<Reservahabitacion> find(Reservahabitacion reservahabitacion){
        List<Reservahabitacion> reservahabitaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, reservahabitacion.getId_reservahabitacion());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Reservahabitacion reservahabitacionnew = new Reservahabitacion();
                
                reservahabitacionnew.setId_reservahabitacion(res.getInt("id_reservahabitacion"));
                reservahabitacionnew.setId_reserva(res.getInt("id_reserva"));
                reservahabitacionnew.setId_habitacion(res.getInt("id_habitacion"));
                
                reservahabitaciones.add(reservahabitacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservahabitaciones;
    }
    
    
    public List<Reservahabitacion> findBy(Map<String, String[]> filter){
        List<Reservahabitacion> reservahabitaciones = new ArrayList<>();
        
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
                Reservahabitacion reservahabitacionnew = new Reservahabitacion();
                
                reservahabitacionnew.setId_reservahabitacion(res.getInt("id_reservahabitacion"));
                reservahabitacionnew.setId_reserva(res.getInt("id_reserva"));
                reservahabitacionnew.setId_habitacion(res.getInt("id_habitacion"));
                
                reservahabitaciones.add(reservahabitacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservahabitaciones;
    }
    
    public Boolean update (Reservahabitacion reservahabitacion){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setInt(1, reservahabitacion.getId_reserva());
            query.setInt(2, reservahabitacion.getId_habitacion());
            query.setInt(3, reservahabitacion.getId_reservahabitacion());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ReservahabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}

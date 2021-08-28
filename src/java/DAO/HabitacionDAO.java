/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Habitacion;
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
public class HabitacionDAO implements interfaces.IDAO<Habitacion, Map<String, String[]>>{
    
    private final String DB_TABLE = "habitaciones";
    private final String ID_OBJECT = "id_habitaciones";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (decripcion, nombre, precio, id_hotel,) VALUES (?, ?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " decripcion = ?, nombre = ?, precio = ?, id_hotel = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public HabitacionDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Habitacion habitacion){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setString(1, habitacion.getDescripcion());
            query.setString(2, habitacion.getNombre());
            query.setInt(3, habitacion.getPrecio());
            query.setInt(4, habitacion.getId_hotel());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Habitacion habitacion){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, habitacion.getId_habitacion());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Habitacion> findAll(){
        List<Habitacion> habitaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Habitacion habitacion = new Habitacion();
                
                habitacion.setId_habitacion(res.getInt("id_habitacion"));
                habitacion.setDescripcion(res.getString("descripcion"));
                habitacion.setNombre(res.getString("nombre"));
                habitacion.setPrecio(res.getInt("precio"));
                habitacion.setId_hotel(res.getInt("id_hotel"));
                
                habitaciones.add(habitacion);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return habitaciones;
    }
    
    public List<Habitacion> find(Habitacion habitacion){
        List<Habitacion> habitaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, habitacion.getId_habitacion());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Habitacion habitacionnew = new Habitacion();
                
                habitacionnew.setId_habitacion(res.getInt("id_categoria"));
                habitacionnew.setDescripcion(res.getString("descripcion"));
                habitacionnew.setNombre(res.getString("nombre"));
                habitacionnew.setPrecio(res.getInt("precio"));
                habitacionnew.setId_hotel(res.getInt("id_hotel"));
                
                habitaciones.add(habitacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return habitaciones;
    }
    
    
    public List<Habitacion> findBy(Map<String, String[]> filter){
        List<Habitacion> habitaciones = new ArrayList<>();
        
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
                Habitacion habitacionnew = new Habitacion();
                
                habitacionnew.setId_habitacion(res.getInt("id_categoria"));
                habitacionnew.setDescripcion(res.getString("descripcion"));
                habitacionnew.setNombre(res.getString("nombre"));
                habitacionnew.setPrecio(res.getInt("precio"));
                habitacionnew.setId_hotel(res.getInt("id_hotel"));
                
                habitaciones.add(habitacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return habitaciones;
    }
    
    public Boolean update (Habitacion habitacion){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setString(1, habitacion.getDescripcion());
            query.setString(2, habitacion.getNombre());
            query.setInt(3, habitacion.getPrecio());
            query.setInt(4, habitacion.getId_hotel());
            query.setInt(5, habitacion.getId_habitacion());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}

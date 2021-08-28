/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Puntuacion;
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
public class PuntuacionDAO implements interfaces.IDAO<Puntuacion, Map<String, String[]>>{
    
    private final String DB_TABLE = "Puntuaciones";
    private final String ID_OBJECT = "id_puntuacion";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (id_cliente, id_hotel, puntuacion) VALUES (?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET id_cliente = ?, id_hotel = ?, puntuacion = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public PuntuacionDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Puntuacion puntuacion){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setInt(1, puntuacion.getId_cliente());
            query.setInt(2, puntuacion.getId_hotel());
            query.setInt(3, puntuacion.getPuntuacion());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Puntuacion puntuacion){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, puntuacion.getId_puntuacion());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Puntuacion> findAll(){
        List<Puntuacion> Puntuaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Puntuacion puntuacion = new Puntuacion();
                
                puntuacion.setId_puntuacion(res.getInt("id_puntuacion"));
                puntuacion.setId_cliente(res.getInt("id_cliente"));
                puntuacion.setId_hotel(res.getInt("id_hotel"));
                puntuacion.setPuntuacion(res.getInt("puntuacion"));
                
                Puntuaciones.add(puntuacion);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Puntuaciones;
    }
    
    public List<Puntuacion> find(Puntuacion puntuacion){
        List<Puntuacion> Puntuaciones = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, puntuacion.getId_puntuacion());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Puntuacion puntuacionnew = new Puntuacion();
                
                puntuacionnew.setId_puntuacion(res.getInt("id_puntuacion"));
                puntuacionnew.setId_cliente(res.getInt("id_cliente"));
                puntuacionnew.setId_hotel(res.getInt("id_hotel"));
                puntuacionnew.setPuntuacion(res.getInt("puntuacion"));
                
                Puntuaciones.add(puntuacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Puntuaciones;
    }
    
    
    public List<Puntuacion> findBy(Map<String, String[]> filter){
        List<Puntuacion> Puntuaciones = new ArrayList<>();
        
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
                Puntuacion puntuacionnew = new Puntuacion();
                
                puntuacionnew.setId_puntuacion(res.getInt("id_puntuacion"));
                puntuacionnew.setId_cliente(res.getInt("id_cliente"));
                puntuacionnew.setId_hotel(res.getInt("id_hotel"));
                puntuacionnew.setPuntuacion(res.getInt("puntuacion"));
                
                Puntuaciones.add(puntuacionnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Puntuaciones;
    }
    
    public Boolean update (Puntuacion puntuacion){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setInt(1, puntuacion.getId_cliente());
            query.setInt(2, puntuacion.getId_hotel());
            query.setInt(3, puntuacion.getPuntuacion());
            query.setInt(4, puntuacion.getId_puntuacion());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(PuntuacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}

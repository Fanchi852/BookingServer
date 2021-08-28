/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import POJOs.Hotel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanchi
 */
public class HotelDAO implements interfaces.IDAO<Hotel, Map<String, String[]>>{
    
    
    private final String DB_TABLE = "hoteles";
    private final String ID_OBJECT = "id_hotel";
    
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (nombre, direccion, estrellas, puntuacion, votos, habitaciones, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET nombre = ?, direccion = ?, estrellas = ?, puntuacion = ?, votos = ?, habitaciones = ?, id_categoria = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public HotelDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Hotel hotel){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setString(1, hotel.getNombre());
            query.setString(2, hotel.getDireccion());
            query.setInt(3, hotel.getEstrellas());
            query.setInt(4, hotel.getPuntuacion());
            query.setInt(5, hotel.getVotos());
            query.setInt(6, hotel.getHabitaciones());
            query.setInt(7, hotel.getId_categoria());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Hotel hotel){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, hotel.getId_hotel());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Hotel> findAll(){
        List<Hotel> hoteles = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Hotel hotel = new Hotel();
                
                hotel.setId_hotel(res.getInt("id_hotel"));
                hotel.setNombre(res.getString("nombre"));
                hotel.setDireccion(res.getString("direccion"));
                hotel.setEstrellas(res.getInt("estrellas"));
                hotel.setPuntuacion(res.getInt("puntuacion"));
                hotel.setVotos(res.getInt("votos"));
                hotel.setHabitaciones(res.getInt("habitaciones"));
                hotel.setId_categoria(res.getInt("id_categoria"));
                
                hoteles.add(hotel);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hoteles;
    }
    
    public List<Hotel> find(Hotel hotel){
        List<Hotel> hoteles = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, hotel.getId_hotel());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Hotel hotelnew = new Hotel();
                
                hotel.setId_hotel(res.getInt("id_hotel"));
                hotel.setNombre(res.getString("nombre"));
                hotel.setDireccion(res.getString("direccion"));
                hotel.setEstrellas(res.getInt("estrellas"));
                hotel.setPuntuacion(res.getInt("puntuacion"));
                hotel.setVotos(res.getInt("votos"));
                hotel.setHabitaciones(res.getInt("habitaciones"));
                hotel.setId_categoria(res.getInt("id_categoria"));
                
                hoteles.add(hotelnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hoteles;
    }
    
    
    
    public List<Hotel> findBy(Map<String, String[]> filter){
        List<Hotel> hoteles = new ArrayList<>();
        
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
                Hotel hotelnew = new Hotel();
                
                hotelnew.setId_hotel(res.getInt("id_hotel"));
                hotelnew.setNombre(res.getString("nombre"));
                hotelnew.setDireccion(res.getString("direccion"));
                hotelnew.setEstrellas(res.getInt("estrellas"));
                hotelnew.setPuntuacion(res.getInt("puntuacion"));
                hotelnew.setVotos(res.getInt("votos"));
                hotelnew.setHabitaciones(res.getInt("habitaciones"));
                hotelnew.setId_categoria(res.getInt("id_categoria"));
                
                hoteles.add(hotelnew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hoteles;
    }
    
    public Boolean update (Hotel hotel){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            
            query.setString(1, hotel.getNombre());
            query.setString(2, hotel.getDireccion());
            query.setInt(3, hotel.getEstrellas());
            query.setInt(4, hotel.getId_categoria());
            query.setInt(5, hotel.getPuntuacion());
            query.setInt(6, hotel.getVotos());
            query.setInt(7, hotel.getHabitaciones());
            query.setInt(8, hotel.getId_hotel());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
    /*
    private final String findby_SQL = "SELECT * FROM hoteles WHERE = ";
    
    private MotorSQL motorSql;

    public HotelDAO() {
        motorSql = ConnectionFactory.selectDb();
    }
    
    public ArrayList<Hotel> findby (Object dato, String tipo, String ascendente){
        
        ArrayList<Hotel> hoteles = new ArrayList<>();
        String sql = findby_SQL;
        String cadena = "";
        motorSql.connect();
        switch (tipo){
            case "localidad":
                cadena = sql + "localidad = " + dato;
            
    }
        ResultSet rs = motorSql.executeQuery(cadena);
        return hoteles;
    }
    */
    
}

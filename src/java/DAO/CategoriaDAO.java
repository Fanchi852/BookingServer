/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Categoria;
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
public class CategoriaDAO implements interfaces.IDAO<Categoria, Map<String, String[]>>{
    
    
    private final String DB_TABLE = "categorias";
    private final String ID_OBJECT = "id_categoria";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (nombre) VALUES (?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET nombre = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public CategoriaDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Categoria categoria){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setString(1, categoria.getNombre());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Categoria categoria){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, categoria.getId_categoria());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Categoria> findAll(){
        List<Categoria> categorias = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Categoria categoria = new Categoria();
                
                categoria.setId_categoria(res.getInt("id_categoria"));
                categoria.setNombre(res.getString("nombre"));
                
                categorias.add(categoria);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
    
    public List<Categoria> find(Categoria categoria){
        List<Categoria> categorias = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, categoria.getId_categoria());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Categoria categorianew = new Categoria();
                
                categorianew.setId_categoria(res.getInt("id_categoria"));
                categorianew.setNombre(res.getString("nombre"));
                
                categorias.add(categorianew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
    
    
    public List<Categoria> findBy(Map<String, String[]> filter){
        List<Categoria> categorias = new ArrayList<>();
        
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
                Categoria categorianew = new Categoria();
                
                categorianew.setId_categoria(res.getInt("id_categoria"));
                categorianew.setNombre(res.getString("nombre"));
                
                categorias.add(categorianew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
    
    public Boolean update (Categoria categoria){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setString(1, categoria.getNombre());
            query.setInt(2, categoria.getId_categoria());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}

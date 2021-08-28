/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOFactory.MysqlDAOFactory;
import POJOs.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ClienteDAO implements interfaces.IDAO<Cliente, Map<String, String[]>>{
    
    private final String DB_TABLE = "clientes";
    private final String ID_OBJECT = "id_cliente";
    
    private final String ADD = "INSERT INTO " + DB_TABLE + " (nombre, apellido, dni, telefono, contrasena, email, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_ALL = "DELETE FROM " + DB_TABLE;
    private final String DELETE = "DELETE FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND = "SELECT * FROM " + DB_TABLE + " WHERE " + ID_OBJECT + " = ?";
    private final String FIND_ALL = "SELECT * FROM " + DB_TABLE;
    private final String FIND_BY = "SELECT * FROM " + DB_TABLE;
    private final String UPDATE = "UPDATE " + DB_TABLE + " SET nombre = ?, apellido = ?, dni = ?, telefono = ?, contrasena = ?, email = ?, direccion = ? WHERE " + ID_OBJECT + " = ?";
    
    private Connection conn;

    public ClienteDAO() {
        MysqlDAOFactory daofactory = new MysqlDAOFactory();
        conn = daofactory.connect();
    }
    
    public Integer add (Cliente cliente){
        Integer res = -1;
        
        try {
            PreparedStatement query = conn.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getDni());
            query.setInt(4, cliente.getTelefono());
            query.setString(5, cliente.getContrasena());
            query.setString(6, cliente.getEmail());
            query.setString(7, cliente.getDireccion());
            
            Integer filas = query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                res = (int) generatedKeys.getLong(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean deleteAll (){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE_ALL);
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public Boolean delete (Cliente cliente){
        Boolean res = false;
        
        try {
            PreparedStatement query = conn.prepareStatement(DELETE);
            
            query.setInt(1, cliente.getId_cliente());
            
            res = query.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public List<Cliente> findAll(){
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND_ALL);
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId_cliente(res.getInt("id_cliente"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setApellido(res.getString("apellido"));
                cliente.setDni(res.getString("dni"));
                cliente.setTelefono(res.getInt("telefono"));
                cliente.setContrasena(res.getString("contrasena"));
                cliente.setEmail(res.getString("email"));
                cliente.setDireccion(res.getString("direccion"));
                
                clientes.add(cliente);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientes;
    }
    
    public List<Cliente> find(Cliente cliente){
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            PreparedStatement query = conn.prepareStatement(FIND);
            
            query.setInt(1, cliente.getId_cliente());
            
            ResultSet res = query.executeQuery();
            
            while (res.next()){
                Cliente clientenew = new Cliente();
                
                clientenew.setId_cliente(res.getInt("id_cliente"));
                clientenew.setNombre(res.getString("nombre"));
                clientenew.setApellido(res.getString("apellido"));
                clientenew.setDni(res.getString("dni"));
                clientenew.setTelefono(res.getInt("telefono"));
                clientenew.setContrasena(res.getString("contrasena"));
                clientenew.setEmail(res.getString("email"));
                clientenew.setDireccion(res.getString("direccion"));
                
                clientes.add(clientenew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientes;
    }
    
    
    public List<Cliente> findBy(Map<String, String[]> filter){
        List<Cliente> clientes = new ArrayList<>();
        
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
                Cliente clientenew = new Cliente();
                
                clientenew.setId_cliente(res.getInt("id_cliente"));
                clientenew.setNombre(res.getString("nombre"));
                clientenew.setApellido(res.getString("apellido"));
                clientenew.setDni(res.getString("dni"));
                clientenew.setTelefono(res.getInt("telefono"));
                clientenew.setContrasena(res.getString("contrasena"));
                clientenew.setEmail(res.getString("email"));
                clientenew.setDireccion(res.getString("direccion"));
                
                clientes.add(clientenew);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientes;
    }

    public Boolean update (Cliente cliente){
        
        Boolean res = false;
        
        try{
            PreparedStatement query = conn.prepareStatement(UPDATE);
            
            query.setString(1, cliente.getNombre());
            query.setString(2, cliente.getApellido());
            query.setString(3, cliente.getDni());
            query.setInt(4, cliente.getTelefono());
            query.setString(5, cliente.getContrasena());
            query.setString(6, cliente.getEmail());
            query.setString(7, cliente.getDireccion());
            query.setInt(8, cliente.getId_cliente());
            
            res = query.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
        
    }
    
}

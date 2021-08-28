/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import DAO.HabitacionDAO;
import POJOs.Habitacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fanchi
 */
public class HabitacionAction implements interfaces.Action{
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {

            case "ADD":
                cadDestino = add(request, response);
                break;
            case "DELETE_ALL":
                cadDestino = deleteAll(request, response);
                break;
            case "DELETE":
                cadDestino = dalete(request, response);
                break;
            case "FIND":
                cadDestino = find(request, response);
                break;
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND_BY":
                cadDestino = findBy(request, response);
                break;
            case "UPDATE":
                cadDestino = update(request, response);
                break;
        }
        return cadDestino;
    }
    
    public String add (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Habitacion habitacion = new Habitacion();
        
        String descripcion = request.getParameter("DESCRIPCION");
        String nombre = request.getParameter("NOMBRE");
        Integer precio = Integer.parseInt(request.getParameter("PRECIO"));
        Integer id = Integer.parseInt(request.getParameter("ID_HOTEL"));
        
        habitacion.setDescripcion(descripcion);
        habitacion.setNombre(nombre);
        habitacion.setPrecio(precio);
        habitacion.setId_hotel(id);
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        Integer resp = habitacionDAO.add(habitacion);
        
        if (resp > 0){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
    public String deleteAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        Boolean resp = habitacionDAO.deleteAll();
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
    public String dalete (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Habitacion habitacion = new Habitacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_HABITACION"));
        habitacion.setId_habitacion(id);
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        Boolean resp = habitacionDAO.delete(habitacion);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }
        
        return res;
    }
    
    public String find (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Habitacion habitacion = new Habitacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_HABITACION"));
        habitacion.setId_habitacion(id);
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<Habitacion> resp = habitacionDAO.find(habitacion);
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<Habitacion> resp = habitacionDAO.findAll();
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findBy (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        Map<String, String[]> map = new HashMap<String, String[]>();
        map = request.getParameterMap();
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<Habitacion> resp = habitacionDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Habitacion habitacion = new Habitacion();
        
        String descripcion = request.getParameter("DESCRIPCION");
        String nombre = request.getParameter("NOMBRE");
        Integer precio = Integer.parseInt(request.getParameter("PRECIO"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTEL"));
        Integer id_habitacion = Integer.parseInt(request.getParameter("ID_HABITACION"));
        
        habitacion.setDescripcion(descripcion);
        habitacion.setNombre(nombre);
        habitacion.setPrecio(precio);
        habitacion.setId_hotel(id_hotel);
        habitacion.setId_habitacion(id_habitacion);
        
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        Boolean resp = habitacionDAO.update(habitacion);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

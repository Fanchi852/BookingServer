/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import DAO.PuntuacionDAO;
import POJOs.Puntuacion;
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
public class PuntuacionAction implements interfaces.Action{
    
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
        Puntuacion puntuacion = new Puntuacion();
        
        
        Integer id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTELA"));
        Integer valor = Integer.parseInt(request.getParameter("PUNTUACION"));
        
        puntuacion.setId_cliente(id_cliente);
        puntuacion.setId_hotel(id_hotel);
        puntuacion.setPuntuacion(valor);
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        Integer resp = puntuacionDAO.add(puntuacion);
        
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
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        Boolean resp = puntuacionDAO.deleteAll();
        
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
        Puntuacion puntuacion = new Puntuacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_PUNTUACION"));
        puntuacion.setId_hotel(id);
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        Boolean resp = puntuacionDAO.delete(puntuacion);
        
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
        Puntuacion puntuacion = new Puntuacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_PUNTUACION"));
        puntuacion.setId_hotel(id);
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        List<Puntuacion> resp = puntuacionDAO.find(puntuacion);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        List<Puntuacion> resp = puntuacionDAO.findAll();
        
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
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        List<Puntuacion> resp = puntuacionDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Puntuacion puntuacion = new Puntuacion();
        
        Integer id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTELA"));
        Integer valor = Integer.parseInt(request.getParameter("PUNTUACION"));
        Integer id_puntuacion = Integer.parseInt(request.getParameter("ID_PUNTUACION"));
        
        puntuacion.setId_cliente(id_cliente);
        puntuacion.setId_hotel(id_hotel);
        puntuacion.setPuntuacion(valor);
        puntuacion.setId_puntuacion(id_puntuacion);
        
        PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
        Boolean resp = puntuacionDAO.update(puntuacion);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

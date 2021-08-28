/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import DAO.ReservahabitacionDAO;
import POJOs.Reservahabitacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fanchi
 */
public class ReservahabitacionAction implements interfaces.Action{
    
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
        Reservahabitacion reservahabitacion = new Reservahabitacion();
        
        Integer id_reserva = Integer.parseInt(request.getParameter("ID_RESERVA"));
        Integer id_habitacion = Integer.parseInt(request.getParameter("ID_HABITACION"));
        
        reservahabitacion.setId_reserva(id_reserva);
        reservahabitacion.setId_habitacion(id_habitacion);
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        Integer resp = reservahabitacionDAO.add(reservahabitacion);
        
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
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        Boolean resp = reservahabitacionDAO.deleteAll();
        
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
        Reservahabitacion reservahabitacion = new Reservahabitacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_RESERVAHABITACION"));
        reservahabitacion.setId_reserva(id);
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        Boolean resp = reservahabitacionDAO.delete(reservahabitacion);
        
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
        Reservahabitacion reservahabitacion = new Reservahabitacion();
        
        Integer id = Integer.parseInt(request.getParameter("ID_RESERVAHABITACION"));
        reservahabitacion.setId_reserva(id);
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        List<Reservahabitacion> resp = reservahabitacionDAO.find(reservahabitacion);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        List<Reservahabitacion> resp = reservahabitacionDAO.findAll();
        
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
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        List<Reservahabitacion> resp = reservahabitacionDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Reservahabitacion reservahabitacion = new Reservahabitacion();
        
        Integer id_reserva = Integer.parseInt(request.getParameter("ID_RESERVA"));
        Integer id_habitacion = Integer.parseInt(request.getParameter("ID_HABITACION"));
        
        reservahabitacion.setId_reserva(id_reserva);
        reservahabitacion.setId_habitacion(id_habitacion);
        
        ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
        Boolean resp = reservahabitacionDAO.update(reservahabitacion);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

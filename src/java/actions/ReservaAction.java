/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import DAO.ReservaDAO;
import DAO.ReservahabitacionDAO;
import POJOs.Reserva;
import POJOs.Reservahabitacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fanchi
 */
public class ReservaAction implements interfaces.Action{
    
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
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        
        Integer id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTELA"));
        Integer habitaciones = Integer.parseInt(request.getParameter("HABITACIONES"));
        Date fecha_entrada = null;
        Date fecha_salida = null;
        try{
            fecha_entrada = format.parse(request.getParameter("FECHA_ENTRADA"));
            fecha_salida = format.parse(request.getParameter("FECHA_SALIDA"));
        }catch(Exception error){
        }
        
        Reserva reserva = new Reserva();
        reserva.setId_cliente(id_cliente);
        reserva.setId_hotel(id_hotel);
        reserva.setHabitaciones(habitaciones);
        reserva.setFecha_entrada(fecha_entrada);
        reserva.setFecha_salida(fecha_salida);
        
        ReservaDAO reservaDAO = new ReservaDAO();
        Integer resp = reservaDAO.add(reserva);
        
        if (resp > 0){
            String[] lines = request.getParameterValues("RESERVAS_HABITACIONES");
            for(String line: lines){
                String reservaHabitacionString = line.replace("ReservaHabitacion", "");
                
                Reservahabitacion reservahabitacion = gson.fromJson(reservaHabitacionString, Reservahabitacion.class);
                reservahabitacion.setId_reserva(resp);
                
                ReservahabitacionDAO reservahabitacionDAO = new ReservahabitacionDAO();
                Integer respReserva = reservahabitacionDAO.add(reservahabitacion);
            }
            
        }
        
        res = gson.toJson(true);
        
        return res;
    }
    
    public String deleteAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        ReservaDAO reservaDAO = new ReservaDAO();
        Boolean resp = reservaDAO.deleteAll();
        
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
        Reserva reserva = new Reserva();
        
        Integer id = Integer.parseInt(request.getParameter("ID_RESERVA"));
        reserva.setId_reserva(id);
        
        ReservaDAO reservaDAO = new ReservaDAO();
        Boolean resp = reservaDAO.delete(reserva);
        
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
        Reserva reserva = new Reserva();
        
        Integer id = Integer.parseInt(request.getParameter("ID_RESERVA"));
        reserva.setId_reserva(id);
        
        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> resp = reservaDAO.find(reserva);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> resp = reservaDAO.findAll();
        
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
        
        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> resp = reservaDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        Reserva reserva = new Reserva();
        
        Integer id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTELA"));
        Integer habitaciones = Integer.parseInt(request.getParameter("HABITACIONES"));
        Integer id_reserva = Integer.parseInt(request.getParameter("ID_RESERVA"));
        
        Date fecha_entrada = null;
        Date fecha_salida = null;
        try{
            fecha_entrada = format.parse(request.getParameter("FECHA_ENTRADA"));
            fecha_salida = format.parse(request.getParameter("FECHA_SALIDA"));
        }catch(Exception error){
        }
        
        reserva.setId_cliente(id_cliente);
        reserva.setId_hotel(id_hotel);
        reserva.setHabitaciones(habitaciones);
        reserva.setFecha_entrada(fecha_entrada);
        reserva.setFecha_salida(fecha_salida);
        reserva.setId_reserva(id_reserva);
        
        ReservaDAO reservaDAO = new ReservaDAO();
        Boolean resp = reservaDAO.update(reserva);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import DAO.HotelDAO;
import POJOs.Hotel;
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
public class HotelAction implements interfaces.Action{
    
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
        Hotel hotel = new Hotel();
        
        String direccion = request.getParameter("DIRECCION");
        String nombre = request.getParameter("NOMBRE");
        Integer estrellas = Integer.parseInt(request.getParameter("ESTRELLAS"));
        Integer puntuacion = Integer.parseInt(request.getParameter("PUNTUACION"));
        Integer votos = Integer.parseInt(request.getParameter("VOTOS"));
        Integer habitaciones = Integer.parseInt(request.getParameter("HABITACIONES"));
        Integer id_categoria = Integer.parseInt(request.getParameter("ID_CATEGORIA"));
        
        hotel.setDireccion(direccion);
        hotel.setNombre(nombre);
        hotel.setEstrellas(estrellas);
        hotel.setPuntuacion(puntuacion);
        hotel.setVotos(votos);
        hotel.setHabitaciones(habitaciones);
        hotel.setId_categoria(id_categoria);
        
        HotelDAO hotelDAO = new HotelDAO();
        Integer resp = hotelDAO.add(hotel);
        
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
        
        HotelDAO hotelDAO = new HotelDAO();
        Boolean resp = hotelDAO.deleteAll();
        
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
        Hotel hotel = new Hotel();
        
        Integer id = Integer.parseInt(request.getParameter("ID_HOTEL"));
        hotel.setId_hotel(id);
        
        HotelDAO hotelDAO = new HotelDAO();
        Boolean resp = hotelDAO.delete(hotel);
        
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
        Hotel hotel = new Hotel();
        
        Integer id = Integer.parseInt(request.getParameter("ID_HOTEL"));
        hotel.setId_hotel(id);
        
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> resp = hotelDAO.find(hotel);
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> resp = hotelDAO.findAll();
        
        
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
        
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> resp = hotelDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Hotel hotel = new Hotel();
        
        String direccion = request.getParameter("DIRECCION");
        String nombre = request.getParameter("NOMBRE");
        Integer estrellas = Integer.parseInt(request.getParameter("ESTRELLAS"));
        Integer puntuacion = Integer.parseInt(request.getParameter("PUNTUACION"));
        Integer votos = Integer.parseInt(request.getParameter("VOTOS"));
        Integer habitaciones = Integer.parseInt(request.getParameter("HABITACIONES"));
        Integer id_categoria = Integer.parseInt(request.getParameter("ID_CATEGORIA"));
        Integer id_hotel = Integer.parseInt(request.getParameter("ID_HOTEL"));
        
        hotel.setDireccion(direccion);
        hotel.setNombre(nombre);
        hotel.setEstrellas(estrellas);
        hotel.setPuntuacion(puntuacion);
        hotel.setVotos(votos);
        hotel.setHabitaciones(habitaciones);
        hotel.setId_categoria(id_categoria);
        hotel.setId_hotel(id_hotel);
        
        HotelDAO hotelDAO = new HotelDAO();
        Boolean resp = hotelDAO.update(hotel);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

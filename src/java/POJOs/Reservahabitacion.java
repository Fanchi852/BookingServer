/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author Fanchi
 */
public class Reservahabitacion{
    
    private Integer id_reservahabitacion, id_reserva, id_habitacion;

    public Reservahabitacion(Integer id_reservahabitacion, Integer id_reserva, Integer id_habitacion) {
        this.id_reservahabitacion = id_reservahabitacion;
        this.id_reserva = id_reserva;
        this.id_habitacion = id_habitacion;
    }

    public Reservahabitacion() {
    }

    public Integer getId_reservahabitacion() {
        return id_reservahabitacion;
    }

    public void setId_reservahabitacion(Integer id_reservahabitacion) {
        this.id_reservahabitacion = id_reservahabitacion;
    }

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Integer getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(Integer id_habitacion) {
        this.id_habitacion = id_habitacion;
    }
    
    public static String toArrayJSon(ArrayList<Reservahabitacion> reservahabitacion) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(reservahabitacion);

        return resp;
    }
    public static String toObjectJson(Reservahabitacion reservahabitacion){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        
        Gson gson = builder.create();
        String resp = gson.toJson(reservahabitacion);
        
        return resp;
    }    

    @Override
    public String toString() {
        return "Reservahabitacion{" + "id_reservahabitacion=" + id_reservahabitacion + ", id_reserva=" + id_reserva + ", id_habitacion=" + id_habitacion + '}';
    }
    
}

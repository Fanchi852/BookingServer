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
public class Hotel {
    
    private Integer id_hotel, estrellas, puntuacion, id_categoria, votos, habitaciones;
    private String direccion, nombre;

    public Hotel(Integer id_hotel, Integer estrellas, Integer puntuacion, Integer id_categoria, Integer votos, Integer habitaciones, String direccion, String nombre) {
        this.id_hotel = id_hotel;
        this.estrellas = estrellas;
        this.puntuacion = puntuacion;
        this.id_categoria = id_categoria;
        this.votos = votos;
        this.habitaciones = habitaciones;
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Hotel() {
    }

    public Integer getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static String toArrayJSon(ArrayList<Hotel> hoteles) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(hoteles);

        return resp;
    }
    public static String toObjectJson(Hotel hotel){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        
        Gson gson = builder.create();
        String resp = gson.toJson(hotel);
        
        return resp;
    } 
    
}

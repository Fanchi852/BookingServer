/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;



/**
 *
 * @author Fanchi
 */
public class Reserva {
    
    private Integer id_cliente, id_hotel, habitaciones, id_reserva;
    private String fecha_entrada, fecha_salida;

    public Reserva(Integer id_cliente, Integer id_hotel, Integer habitaciones, Integer id_reserva, Date fecha_entrada, Date fecha_salida) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        this.id_cliente = id_cliente;
        this.id_hotel = id_hotel;
        this.habitaciones = habitaciones;
        this.id_reserva = id_reserva;
        this.fecha_entrada = format.format(fecha_entrada);
        this.fecha_salida = format.format(fecha_salida);
    }

    public Reserva() {
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

   public java.sql.Date getFecha_entrada() {
        Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            result = format.parse(fecha_entrada);
        }catch(Exception error){
        }
        java.sql.Date sql = new java.sql.Date(result.getTime());
        return sql;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.fecha_entrada = format.format(fecha_entrada);
    }

    public java.sql.Date getFecha_salida() {
        Date result = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            result = format.parse(fecha_salida);
        }catch(Exception error){
        }
        java.sql.Date sql = new java.sql.Date(result.getTime());
        return sql;
    }

    public void setFecha_salida(Date fecha_salida) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.fecha_salida = format.format(fecha_salida);
    }

    

    public static String toArrayJSon(ArrayList<Reserva> reservas) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(reservas);

        return resp;
    }
    public static String toObjectJson(Reserva reserva){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        
        Gson gson = builder.create();
        String resp = gson.toJson(reserva);
        
        return resp;
    }    
    
}

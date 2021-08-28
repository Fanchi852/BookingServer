/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import actions.CategoriaAction;
import actions.ClienteAction;
import actions.HabitacionAction;
import actions.HotelAction;
import actions.PuntuacionAction;
import actions.ReservaAction;
import actions.ReservahabitacionAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fanchi
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occursn
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        System.out.println("WS INIT");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");
        String arrayAction[] = action.split("\\.");
        System.out.println("ACTION: " + arrayAction[0]);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (arrayAction[0]) {
            
            case "CATEGORIA":
                System.out.println("dentro de categoria");
                out.print(new CategoriaAction().
                        execute(request, response));
                break;
            case "CLIENTE":
                System.out.println("dentro de CLIENTE");
                
                    Map<String, String[]> map = request.getParameterMap();
                    for(String key : map.keySet()){
                        String[] value = map.get(key);
                        System.out.println(key + ": ");
                        for(String v :value){
                            System.out.print(v + ", ");
                        }
                    }
                
                System.out.println("entrada: " + request.getParameter("NOMBRE"));
                out.print(new ClienteAction().
                        execute(request, response));
                break;
            case "HABITACION":
                System.out.println("dentro de HABITACION");
                System.out.println("entrada: " + request.toString());
                out.print(new HabitacionAction().
                        execute(request, response));
                break;
            case "HOTEL":
                System.out.println("dentro de HOTEL");
                System.out.println("entrada: " + request.toString());
                out.print(new HotelAction().
                        execute(request, response));
                break;
            case "RESERVA":
                System.out.println("dentro de RESERVA");
                System.out.println("entrada: " + request.toString());
                out.print(new ReservaAction().
                        execute(request, response));
                break;
            case "RESERVAHABITACION":
                System.out.println("dentro de RESERVAHABITACION");
                System.out.println("entrada: " + request.toString());
                out.print(new ReservahabitacionAction().
                        execute(request, response));
                break;
            case "PUNTUACION":
                System.out.println("dentro de PUNTUACION");
                System.out.println("entrada: " + request.toString());
                out.print(new PuntuacionAction().
                        execute(request, response));
                break;
            
            
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

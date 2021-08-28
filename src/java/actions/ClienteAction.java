/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import DAO.ClienteDAO;
import POJOs.Cliente;
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
public class ClienteAction implements interfaces.Action{
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        System.out.println("SUBACTION: " + arrayAction[1]);
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
            /*case "LOGIN":
                cadDestino = login(request, response);
                break;*/
        }
        return cadDestino;
    }
    
    public String add (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Cliente cliente = new Cliente();
        
        System.out.println("x2: " +request.getParameter("NOMBRE"));
        
        String nombre =  request.getParameter("NOMBRE");
        String apellidos = request.getParameter("APELLIDO");
        String dni = request.getParameter("DNI");
        Integer telefono = Integer.parseInt(request.getParameter("TELEFONO"));
        String contrasena = request.getParameter("CONTRASENA");
        String email = request.getParameter("EMAIL");
        String direccion = request.getParameter("DIRECCION");
        
        cliente.setNombre(nombre);
        cliente.setApellido(apellidos);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setContrasena(contrasena);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        
        System.out.println("x1: " + cliente.getNombre());
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Integer resp = clienteDAO.add(cliente);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (resp > 0){
            res = gson.toJson(true);
 
        }else{
            res = gson.toJson(false);
        }     
        
        return res;
    }
    
    public String deleteAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Boolean resp = clienteDAO.deleteAll();
        
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
        Cliente cliente = new Cliente();
        
        Integer id = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        cliente.setId_cliente(id);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Boolean resp = clienteDAO.delete(cliente);
        
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
        Cliente cliente = new Cliente();
        
        Integer id = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        cliente.setId_cliente(id);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> resp = clienteDAO.find(cliente);
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> resp = clienteDAO.findAll();
        
        
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
        
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> resp = clienteDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        System.out.println(res);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Cliente cliente = new Cliente();
        
        String nombre =  request.getParameter("NOMBRE");
        String apellidos = request.getParameter("APELLIDO");
        String dni = request.getParameter("DNI");
        Integer telefono = Integer.parseInt(request.getParameter("TELEFONO"));
        String contrasena = request.getParameter("CONTRASENA");
        String email = request.getParameter("EMAIL");
        String direccion = request.getParameter("DIRECCION");
        Integer id = Integer.parseInt(request.getParameter("ID_CLIENTE"));
        
        cliente.setNombre(nombre);
        cliente.setApellido(apellidos);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setContrasena(contrasena);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        cliente.setId_cliente(id);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Boolean resp = clienteDAO.update(cliente);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
    public String login (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Cliente cliente = new Cliente();
        
        Integer id = Integer.parseInt(request.getParameter(""));
        cliente.setId_cliente(id);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> resp = clienteDAO.find(cliente);
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        
        return res;
    }
    
}

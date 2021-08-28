/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import DAO.CategoriaDAO;
import POJOs.Categoria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fanchi
 */
public class CategoriaAction implements interfaces.Action{
    
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
        Categoria categoria = new Categoria();
        
        String nombre =  request.getParameter("NOMBRE");
        
        categoria.setNombre(nombre);
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Integer resp = categoriaDAO.add(categoria);
        
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
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Boolean resp = categoriaDAO.deleteAll();
        
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
        Categoria categoria = new Categoria();
        
        Integer id =  Integer.parseInt(request.getParameter("ID_CATEGORIA"));
        
        categoria.setId_categoria(id);
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Boolean resp = categoriaDAO.delete(categoria);
        
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
        Categoria categoria = new Categoria();
        
        Integer id = Integer.parseInt(request.getParameter("ID_CATEGORIA"));
        
        categoria.setId_categoria(id);
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> resp = categoriaDAO.find(categoria);
        
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
 
        
        return res;
    }
    
    public String findAll (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> resp = categoriaDAO.findAll();
        
        
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
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> resp = categoriaDAO.findBy(map);
        
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        res = gson.toJson(resp);
        return res;
    }
    
    public String update (HttpServletRequest request, HttpServletResponse response){
        String res = "";
        Categoria categoria = new Categoria();
        
        String nombre =  request.getParameter("NOMBRE");
        Integer id = Integer.parseInt(request.getParameter("ID_CATEGORIA"));
        
        categoria.setId_categoria(id);
        categoria.setNombre(nombre);
        
        System.out.println(categoria.toString());
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Boolean resp = categoriaDAO.update(categoria);
        
        if (resp){
            
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            res = gson.toJson(resp);
 
        }        
        
        return res;
    }
    
}

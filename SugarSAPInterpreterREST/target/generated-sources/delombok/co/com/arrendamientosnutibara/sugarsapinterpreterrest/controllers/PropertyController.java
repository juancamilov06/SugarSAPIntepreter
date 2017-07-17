/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.arrendamientosnutibara.sugarsapinterpreterrest.controllers;

import co.com.arrendamientosnutibara.sugarinterpreterrest.entites.Property;
import co.com.arrendamientosnutibara.sugarsapinterpreterrest.interfaces.IResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import spark.Response;

/**
 *
 * @author Juan Camilo Villa Amaya
 */
//TODO: Verificar campos que llegan, insertar a BD
public class PropertyController implements IResponse{
        
    public PropertyController(){
    }
    
    public Response create(String req, Response res){
        Property property = mapProperty(req);
        if(property != null){
            return okResponse(res);
        }
        return internalErrorResponse(res);
    }
    
    public Response update(String req, Response res){
        Property property = mapProperty(req);
        if(property != null){
            return okResponse(res);
        }
        return internalErrorResponse(res);
    }
    
    private Property mapProperty(String req){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Property property = mapper.readValue(req, Property.class);
            return property;
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Response okResponse(Response res) {
        res.body(new JSONObject().put("success", true).put("code", 200).toString());
        return res;
    }

    @Override
    public Response internalErrorResponse(Response res) {
        res.body(new JSONObject().put("success", false).put("code", 500).toString());
        return res;
    }

    @Override
    public Response badRequestResponse(Response res) {
        res.body(new JSONObject().put("success", false).put("code", 400).toString());
        return res;
    }
   
}

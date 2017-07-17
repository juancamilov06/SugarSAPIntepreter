/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.arrendamientosnutibara.sugarsapinterpreterrest.controllers;

import co.com.arrendamientosnutibara.sugarsapinterpreterrest.interfaces.IResponse;
import org.codehaus.jackson.map.ObjectMapper;
import co.com.arrendamientosnutibara.sugarinterpreterrest.entites.Client;
import java.io.IOException;
import org.json.JSONObject;
import spark.Response;

/**
 *
 * @author Juan Camilo Villa Amaya
 */

//TODO: Verificar campos que llegan, insertar a BD
public class ClientController implements IResponse{
    
    public Response create(String req, Response res){
        Client client = mapClient(req);
        if(client != null){
            System.out.println("cliente: " + client.getId());
            return okResponse(res);
        }
        return internalErrorResponse(res);
    }
    
    public Response update(String req, Response res){
        Client client = mapClient(req);
        if(client != null){
            System.out.println("cliente: " + client.getId());
            return okResponse(res);
        }
        return internalErrorResponse(res);
    }
    
    private Client mapClient(String req){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Client client = mapper.readValue(req, Client.class);
            return client;
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

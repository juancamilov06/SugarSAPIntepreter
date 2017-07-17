/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.arrendamientosnutibara.sugarsapinterpreterrest.interfaces;

import spark.Response;

/**
 *
 * @author jva807
 */
public interface IResponse {
    
    public Response okResponse(Response res);
    public Response internalErrorResponse(Response res);
    public Response badRequestResponse(Response res);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.arrendamientosnutibara.sugarinterpreterrest.entites;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Juan Camilo Villa Amaya
 */

@Data
public class Property {
    
    //Clave primaria 
    private String id;
    //Codigo interno del inmueble
    private String code;
    //Direccion del inmueble
    private String address;
    //Datos extra del inmueble. EJ. Nombre de la unidad residencial, cerca a.
    private String assignment;
    //Valor actual que paga el arrendatario
    private int canon;
    //Numero de contrato que se celebra con EPM
    private String epmcontract;
    //Barrio donde se encuentra el inmueble
    private String neighborhood;
    //Sector de ubicacion del inmueble: EJ. CENTRO, NORTE.
    private String sector;
    //Estrato del inmueble
    private String stratum;
    //Numero de llaves que deja el propietario
    private String keynumber;
    //Numero de ubicacion de las llaves
    private String keylocation;
    //Latitud del inmueble
    private double latitude;
    //Longitud del inmueble
    private double longitude;
    //Situacion actual del inmueble, Pendiente, Normal, Cons. SantaFe.
    private String situation;
    //Causa del retiro de la propiedad
    private String retirementcause;
    //Municipio donde se encuentra el inmueble
    private String city;
    //Observaciones del inmueble
    private String observations;
    //Observaciones del estado o condiciones del inmueble. EJ. Puertas 
    //sin pintar, no mascotas.
    private String extraobservations;
    //Se refiere a si el inmueble es de Nutibara o de Santa Fe
    private String propertyof;   
    
    public static String extractProperty(String body) throws JSONException{
        JSONObject object = new JSONObject(body);
        System.out.println(object.getJSONObject("property"));
        return "";
    }
    
}

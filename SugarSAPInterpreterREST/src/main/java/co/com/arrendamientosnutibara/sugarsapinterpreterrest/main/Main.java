/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.arrendamientosnutibara.sugarsapinterpreterrest.main;
import co.com.arrendamientosnutibara.sugarinterpreterrest.entites.Property;
import co.com.arrendamientosnutibara.sugarsapinterpreterrest.controllers.ClientController;
import co.com.arrendamientosnutibara.sugarsapinterpreterrest.controllers.PropertyController;
import org.codehaus.jackson.map.ObjectMapper;
import static spark.Spark.*;
/**
 *
 * @author Juan Camilo Villa Amaya
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        path("/api", () -> {
           before("/*", (req, res) -> {
               System.out.println("Api called");
           });
           path("/inmueble", () -> {
               post("", (req, res) -> {
                   PropertyController controller = new PropertyController();
                   return controller.create(req.body(), res);
               });
               put("", (req, res) -> {
                   PropertyController controller = new PropertyController();
                   return controller.update(req.body(), res);
               });
           });
           path("/tercero", () -> {
               post("", (req, res) -> {
                   ClientController controller = new ClientController();
                   return controller.create(req.body(), res);
               });
               put("", (req, res) -> {
                   ClientController controller = new ClientController();
                   return controller.update(req.body(), res);
               });
           });
        });
        
        
        
        get("/test", (req, res) -> {
            
            return true;
        });
        
    }
    
}

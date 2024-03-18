/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.conexion.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.controller.ControllerArticle;
import org.utl.model.Articulo;
/**
 *
 * @author josue
 */
@Path("article")
public class ArticleRest {

    @Path("getAllArt")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        ControllerArticle objCE = new ControllerArticle();
        List<Articulo> listArt = objCE.read();
        Gson objGS = new Gson();
        String out = objGS.toJson(listArt);
        return Response.ok(out).build();
    }
}

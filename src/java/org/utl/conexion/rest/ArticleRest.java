/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.conexion.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.utl.controller.ControllerArticle;
import org.utl.model.Articulo;
import org.utl.model.Usuario;

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

    @Path("insert")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response inser(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            String id = objCE.insr(document);
            // Devolver solo el ID como parte del objeto JSON de respuesta
            out = "{\"id\": \"" + id + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("updateCom")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCom(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            String id = objCE.updateComen(document);
            // Devolver solo el ID como parte del objeto JSON de respuesta
            out = "{\"id\": \"" + id + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }
    @Path("palabra")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response palabra(@FormParam("title") @DefaultValue("") String title) {
        System.out.println("palabra:"+title);
        System.out.println(title.length());
        String out = "";
        try {
            ControllerArticle objCE = new ControllerArticle();
            String id = objCE.palabraMasRepetidaEnComentarios(title);
            // Devolver solo el ID como parte del objeto JSON de respuesta
            out = "{\"id\": \"" + id + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }
    @Path("reacciones")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response reacciones(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            String id = objCE.reaccionv2(document);
            // Devolver solo el ID como parte del objeto JSON de respuesta
            out = "{\"id\": \"" + id + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("logIn")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            Boolean res = objCE.logIn(document);
            // Devolver solo el ID como parte del objeto JSON de respuesta
            if (res == true) {
                out = "{\"success\": " + res + "}";
            } else {
                out = "{\"error\": " + res + "}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("logInv2")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logInv2(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            Usuario u = objCE.logInv2(document);

            // Convertir el objeto Usuario a JSON usando Gson
            Gson gson = new Gson();
            String jsonString = gson.toJson(u);

            // Construir la respuesta JSON
            out = "{\"success\": " + jsonString + "}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("singIn")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response singIn(String documentString) {
        String out = "";
        try {
            Document document = Document.parse(documentString);
            ControllerArticle objCE = new ControllerArticle();
            Boolean res = objCE.singIn(document);
            System.out.println(res);
            if (res == true) {
                out = "{\"success\": " + res + "}";
            } else if (res == false) {
                //out = "{\"err\": \"username ocupado " + res + "}";
                return Response.status(Response.Status.CONFLICT).entity("{\"err\": \"username ocupado " + res + "\"}").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

}

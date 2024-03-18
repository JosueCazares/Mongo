/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.utl.conexion.CConexion;
import org.utl.model.Articulo;
import org.utl.model.Comments;
import org.utl.model.Reaccion;
import org.utl.model.Usuario;

/**
 *
 * @author josue
 */
public class ControllerArticle {

    public List<Articulo> readnulll() {
        List<Articulo> listaArticulo = new ArrayList<>();
        List<Reaccion> listaReaccion = new ArrayList<>();
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            FindIterable<org.bson.Document> documents = db.getCollection("article").find();
            for (org.bson.Document document : documents) {
                Articulo articulo = new Articulo();
                List<Reaccion> listareacciones = new ArrayList<>();
                org.bson.Document reaccionDoc = (org.bson.Document) document.get("reaccion");
                Reaccion reaccion = new Reaccion(reaccionDoc.getInteger("like"), reaccionDoc.getInteger("dislike"));
                listareacciones.add(reaccion);
                articulo.setTitle(document.getString("title")); // Suponiendo que "nombre" es un campo en tu documento
                articulo.setDatePost(document.getString("datePost"));
                articulo.setDescription(document.getString("description"));
                articulo.setImage(document.getString("image"));
                articulo.setReaccion(listaReaccion);
                listaArticulo.add(articulo);
                List<Comments> listcomentarios = new ArrayList<>();
                List<org.bson.Document> commentsDocList = (List<org.bson.Document>) document.get("comments");
                for (org.bson.Document commentDoc : commentsDocList) {
                    Comments comentario = new Comments();
                    comentario.setMensaje(commentDoc.getString("mensaje"));
                    listcomentarios.add(comentario);
                }
                articulo.setComments(listcomentarios);
                listaArticulo.add(articulo);
            }
        }
        return listaArticulo;
    }

    public List<Articulo> read() {
        List<Articulo> listaArticulo = new ArrayList<>();
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            FindIterable<org.bson.Document> documents = db.getCollection("article").find();
            for (org.bson.Document document : documents) {
                Articulo articulo = new Articulo();
                articulo.setTitle(document.getString("title"));
                articulo.setDatePost(document.getString("datePost"));
                articulo.setDescription(document.getString("description"));
                articulo.setImage(document.getString("image"));

                List<Reaccion> reacciones = new ArrayList<>();
                List<org.bson.Document> reaccionesDoc = (List<org.bson.Document>) document.get("reaccion");
                for (org.bson.Document reaccionDoc : reaccionesDoc) {
                    Reaccion reaccion = new Reaccion();
                    reaccion.setLike(reaccionDoc.getInteger("like"));
                    reaccion.setDislake(reaccionDoc.getInteger("dislike"));
                    reacciones.add(reaccion);
                }
                articulo.setReaccion(reacciones);

                List<Comments> comentarios = new ArrayList<>();
                List<org.bson.Document> commentsDocList = (List<org.bson.Document>) document.get("comments");
                for (org.bson.Document commentDoc : commentsDocList) {
                    Comments comentario = new Comments();
                    comentario.setMensaje(commentDoc.getString("mensaje"));
                    // Aquí debes crear un objeto Usuario y establecerlo en el comentario si es necesario
                    Usuario usercom = new Usuario();
                    usercom.setUsername(commentDoc.getString("user"));
                    // comentario.setUsuario(usuario);
                    comentario.setUsuario(usercom);
                    comentarios.add(comentario);
                }
                Usuario user = new Usuario();
                user.setUsername(document.getString("user"));
                articulo.setUsuario(user);
                articulo.setComments(comentarios);

                // Aquí debes crear un objeto Usuario y establecerlo en el artículo si es necesario
                // articulo.setUsuario(usuario);
                listaArticulo.add(articulo);
            }
        }
        return listaArticulo;
    }
}

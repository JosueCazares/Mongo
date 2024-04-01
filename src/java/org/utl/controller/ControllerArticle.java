/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.controller;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
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
                articulo.setLike(document.getInteger("like"));
                articulo.setDislike(document.getInteger("dislike"));
                List<Comments> comentarios = new ArrayList<>();
                List<org.bson.Document> commentsDocList = (List<org.bson.Document>) document.get("comments");
                for (org.bson.Document commentDoc : commentsDocList) {
                    Comments comentario = new Comments();
                    comentario.setMensaje(commentDoc.getString("mensaje"));
                    // Aquí debes crear un objeto Usuario y establecerlo en el comentario si es necesario
                    Usuario usercom = new Usuario();
                    usercom.setUsername(commentDoc.getString("usuarioComenta"));
                    // comentario.setUsuario(usuario);
                    comentario.setUsuario(usercom);
                    comentarios.add(comentario);
                }
                List<Usuario> listausuario = new ArrayList<>();
                List<org.bson.Document> userDocList = (List<org.bson.Document>) document.get("usuario");
                for (org.bson.Document userDoc : userDocList) {
                    Usuario user = new Usuario();
                    user.setUsername(userDoc.getString("name"));
                    listausuario.add(user);
                }
                articulo.setUsuarios(listausuario);
                articulo.setComments(comentarios);

                listaArticulo.add(articulo);
            }
        }
        //System.out.println("hoola");
        return listaArticulo;
    }

    public String insr(Document document) {
        System.out.println(document.get("title"));
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("article");
            try {
                InsertOneResult result = collection.insertOne(document);
                System.out.println("Exito! id del documento: " + result.getInsertedId());
                return "Exito! id del documento: " + result.getInsertedId();
            } catch (MongoException me) {
                //System.err.println("Unable to insert due to an error: " + me);
                return "Unable to insert due to an error: " + me;
            }
        }
    }

    public String comentarios(Document document) {
        String id = (String) document.get("id");
        String title = (String) document.get("title");
        String date = (String) document.get("datePost");
        String email = (String) document.get("email");
        List<Document> listcomments = (List<Document>) document.get("comments");

        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("article");
            Document query = new Document().append("title", title);
            Bson updates = Updates.combine(
                    Updates.set("comments", listcomments),
                    Updates.set("datePost", date));
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(query, updates, options);
                System.out.println("Modified document count: " + result.getModifiedCount());
                System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
                return "Modified document count: " + result.getModifiedCount();
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
                return "Unable to update due to an error: " + me;
            }
        }
    }

    public String updateComen(Document document) {
        String title = (String) document.get("title");
        String date = (String) document.get("datePost");
        List<Document> listcomments = (List<Document>) document.get("comments");

        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("article");
            Document query = new Document().append("title", title);
            Document existingArticle = collection.find(query).first();

            if (existingArticle != null) {
                List<Document> existingComments = (List<Document>) existingArticle.get("comments");
                existingComments.addAll(listcomments);

                Bson updates = Updates.combine(
                        Updates.set("comments", existingComments),
                        Updates.set("datePost", date));
                UpdateOptions options = new UpdateOptions().upsert(true);

                try {
                    UpdateResult result = collection.updateOne(query, updates, options);
                    System.out.println("Modified document count: " + result.getModifiedCount());
                    System.out.println("Upserted id: " + result.getUpsertedId());
                    return "Modified document count: " + result.getModifiedCount();
                } catch (MongoException me) {
                    System.err.println("Unable to update due to an error: " + me);
                    return "Unable to update due to an error: " + me;
                }
            } else {
                System.err.println("Article not found: " + title);
                return "Article not found: " + title;
            }
        }
    }

    public Boolean insrUser(Document document) {
        String username = (String) document.get("username");
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("users");
            //hacer una busqueda yb luego la insercion
            Document existingUser = collection.find(Filters.eq("username", username)).first();
            if (existingUser != null) {
                // Si ya existe un usuario con el mismo nombre de usuario
                //System.out.println("El usuario con el nombre '" + username + "' ya existe en la base de datos.");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // En caso de error, devolver false
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public Boolean logIn(Document document) {
        String username = (String) document.get("username");
        String pass = (String) document.get("password");
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("users");
            Document query = new Document("username", username).append("password", pass);
            Document usuario = collection.find(query).first();
            if (usuario != null) {
                System.out.println("a");
                return true;
            } else {
                System.out.println("b");
                return false;
            }
        }
    }

    public Usuario logInv2(Document document) {
        Usuario u = new Usuario();
        String username = (String) document.get("username");
        String email = (String) document.get("email");
        //System.out.println("user " + username);
        String pass = (String) document.get("password");
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("users");
            Document query = new Document("username", username).append("email", email).append("password", pass);
            Document usuario = collection.find(query).first();
            if (usuario != null) {
                u.setUsername(username);
                u.setEmail(email);
                return u;
            }
        }
        return u;
    }

    public Boolean singIn(Document document) {
        String username = (String) document.get("username");
        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("users");
            Document query = new Document("username", username);
            Document usuario = collection.find(query).first();
            if (usuario != null) {
                System.out.println("Username existente");
                return false;
            } else {
                System.out.println("Redistrate");
                InsertOneResult result = collection.insertOne(document);
                System.out.println("Exito! id del documento: " + result.getInsertedId());
                return true;
            }
        }
    }

   

    public String reaccionv2(Document document) {
        String title = (String) document.get("title");
        int like = (int) document.get("like");
        int dislike = (int) document.get("dislike");

        try (MongoClient client = CConexion.getConnection()) {
            MongoDatabase db = client.getDatabase("blogPage");
            MongoCollection<Document> collection = db.getCollection("article");
            Document query = new Document().append("title", title);
            Document existingArticle = collection.find(query).first();

            if (existingArticle != null) {
                // Definir los incrementos para los likes y dislikes
                Bson updates = null;
                if (like != 0 && dislike != 0) {
                    updates = Updates.combine(
                            Updates.inc("like", like),
                            Updates.inc("dislike", dislike)
                    );
                } else if (like != 0) {
                    updates = Updates.inc("like", like);
                } else if (dislike != 0) {
                    updates = Updates.inc("dislike", dislike);
                }

                if (updates != null) {
                    UpdateOptions options = new UpdateOptions().upsert(true);
                    try {
                        // Ejecutar la actualización utilizando el operador de incremento
                        UpdateResult result = collection.updateOne(query, updates, options);
                        System.out.println("Modified document count: " + result.getModifiedCount());
                        System.out.println("Upserted id: " + result.getUpsertedId());
                        return "Modified document count: " + result.getModifiedCount();
                    } catch (MongoException me) {
                        System.err.println("Unable to update due to an error: " + me);
                        return "Unable to update due to an error: " + me;
                    }
                } else {
                    System.err.println("No valid values provided for likes or dislikes.");
                    return "No valid values provided for likes or dislikes.";
                }
            } else {
                System.err.println("Article not found: " + title);
                return "Article not found: " + title;
            }
        }
    }

}

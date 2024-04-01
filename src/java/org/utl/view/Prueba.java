/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.view;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.utl.controller.ControllerArticle;
import org.utl.model.Articulo;
import org.utl.model.Comments;
import org.utl.model.Reaccion;
import org.utl.model.Usuario;

/**
 *
 * @author josue
 */
public class Prueba {

    public static void main(String[] args) {
        ControllerArticle ca = new ControllerArticle();
        //pr1();
        //ca.read();
        //System.out.println(ca.read());
        //pruebaUpdateArt();
        //pruebaUpdate();
        //prbComv2();
        //prubeLogv2();
        prbReacv2();
    }

    public static void pr1() {
        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();
        u1.setUsername("matiaslol");
        u2.setUsername("josue");
        Comments c1 = new Comments();
        Comments c2 = new Comments();
        Comments c3 = new Comments();
        Comments c4 = new Comments();
        Comments c5 = new Comments();
        Comments c6 = new Comments();
        Comments c7 = new Comments();
        c1.setMensaje("bad");
        c2.setMensaje("good");
        c3.setMensaje("good");
        c4.setMensaje("good");
        c5.setMensaje("good");
        c6.setMensaje("good");
        c7.setMensaje("good");
        c1.setUsuario(u1);
        c2.setUsuario(u2);
        c3.setUsuario(u2);
        c4.setUsuario(u2);
        c5.setUsuario(u2);
        c6.setUsuario(u2);
        c7.setUsuario(u2);
        List<Comments> comments = new ArrayList<>();
        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        comments.add(c4);
        comments.add(c5);
        comments.add(c6);
        comments.add(c7);
        List<Usuario> listaUsuarioCreadores = new ArrayList<>();
        Usuario usp = new Usuario();
        usp.setUsername("matias10");
        listaUsuarioCreadores.add(usp);
        Articulo a = new Articulo();
        a.setTitle("Batman");
        a.setDescription("Good book");
        a.setImage("urltextsrc");
        a.setDatePost("22/03/2024");
        a.setLike(5);
        a.setDislike(6);
        a.setComments(comments);
        a.setUsuarios(listaUsuarioCreadores);

        // Crear un nuevo documento y asignar los campos del objeto 'a'
        Document document = new Document();
        document.append("title", a.getTitle());
        document.append("datePost", a.getDatePost());
        document.append("description", a.getDescription());
        document.append("image", a.getImage());
        document.append("like", a.getLike());
        document.append("dislike", a.getDislike());
        // Mapear la lista de comentarios
        List<Document> commentsDocs = new ArrayList<>();
        for (Comments comment : a.getComments()) {
            Document commentDoc = new Document();
            commentDoc.append("mensaje", comment.getMensaje());
            commentDoc.append("usuarioComenta", comment.getUsuario().getUsername());
            // Puedes mapear más campos si es necesario
            commentsDocs.add(commentDoc);
        }
        document.append("comments", commentsDocs);

        // Mapear el lista de usuarios
        List<Document> usuariosCreadoresDoc = new ArrayList<>();
        for (Usuario us : a.getUsuarios()) {
            Document usuariop = new Document();
            usuariop.append("name", us.getUsername());
            usuariosCreadoresDoc.add(usuariop);
        }
        //usuarioDoc.append("username", a.getUsuarios().get(0).getUsername());
        // Puedes mapear más campos si es necesario
        document.append("usuario", usuariosCreadoresDoc);

        // Llamar al método 'insr' con el documento creado
        ControllerArticle ca = new ControllerArticle();
        ca.insr(document);
    }

    public static void prubeLog() {
        Usuario u = new Usuario();
        u.setUsername("jssry");
        u.setEmail("matias@gmail.com");
        u.setPassword("password");
        ControllerArticle ca = new ControllerArticle();
        Document document = new Document();
        document.append("username", u.getUsername());
        document.append("email", u.getEmail());
        document.append("password", u.getPassword());
        System.out.println(ca.logIn(document));
    }

    public static void prubeLogv2() {
        Usuario u = new Usuario();
        u.setUsername("jssry");
        u.setEmail("matias@gmail.com");
        u.setPassword("password");
        ControllerArticle ca = new ControllerArticle();
        Document document = new Document();
        document.append("username", u.getUsername());
        document.append("email", u.getEmail());
        document.append("password", u.getPassword());
        System.out.println(ca.logInv2(document));
    }

    public static void preubaSing() {
        Usuario u = new Usuario();
        u.setUsername("jssry");
        u.setEmail("matias@gmail.com");
        u.setPassword("password");
        ControllerArticle ca = new ControllerArticle();
        Document document = new Document();
        document.append("username", u.getUsername());
        document.append("email", u.getEmail());
        document.append("password", u.getPassword());
        System.out.println(ca.singIn(document));
    }

    public static void pruebaUpdate() {
        Usuario u = new Usuario();
        u.setUsername("a13");
        u.setEmail("m@gmail.com");
        u.setPassword("password");
        ControllerArticle ca = new ControllerArticle();
        Document document = new Document();
        document.append("username", u.getUsername());
        document.append("email", u.getEmail());
        document.append("password", u.getPassword());
//        System.out.println(ca.update(document));
    }
//
//    public static void pruebaUpdateArt() {
//        Usuario u1 = new Usuario();
//        Usuario u2 = new Usuario();
//        u1.setUsername("matiaslol");
//        u2.setUsername("josue");
//        Comments c1 = new Comments();
//        Comments c2 = new Comments();
//        List<Comments> comments = new ArrayList<>();
//        comments.add(c1);
//        comments.add(c2);
//        c1.setMensaje("bad");
//        c2.setMensaje("good");
//        c1.setUsuario(u1);
//        c2.setUsuario(u2);
//        Articulo a = new Articulo();
//        a.setId("6603899bf1623d556e3ca208");
//        a.setTitle("hola");
//        a.setComments(comments);
//        ControllerArticle ca = new ControllerArticle();
//        Document document = new Document();
//        //document.append("comments", a.getComments());
//        document.append("title", a.getTitle());
//        ca.update(document);
//    }
//    public static void pruebaUpdate2() {
//        Usuario u1 = new Usuario();
//        Usuario u2 = new Usuario();
//        u1.setUsername("matiaslol");
//        u2.setUsername("josue");
//        Comments c1 = new Comments();
//        Comments c2 = new Comments();
//        List<Comments> comments = new ArrayList<>();
//        comments.add(c1);
//        comments.add(c2);
//        c1.setMensaje("bad");
//        c2.setMensaje("good");
//        c1.setUsuario(u1);
//        c2.setUsuario(u2);
//        Articulo a = new Articulo();
//        a.setId("66038a14f1623d556e3ca20f");
//        a.setComments(comments);
//        ControllerArticle ca = new ControllerArticle();
//        Document document = new Document();
//        document.append("comments", a.getComments());
//        String id="66038a14f1623d556e3ca20f";
//        //ca.update2(id,document);
//    }

    public static void prbCom() {
        Usuario u = new Usuario();
        u.setUsername("test");
        Comments c = new Comments();
        c.setMensaje("test");
        c.setUsuario(u);
        List<Comments> listCom = new ArrayList<>();
        listCom.add(c);
        Articulo a = new Articulo();
        a.setTitle("Jay");
        a.setDatePost("18");
        a.setComments(listCom);
        Document document = new Document();
        List<Document> commentsDocs = new ArrayList<>();
        for (Comments comment : a.getComments()) {
            Document commentDoc = new Document();
            commentDoc.append("mensaje", comment.getMensaje());
            commentDoc.append("usuarioComenta", comment.getUsuario().getUsername());
            commentsDocs.add(commentDoc);
        }
        document.append("comments", commentsDocs);
        document.append("title", a.getTitle());
        document.append("datePost", a.getDatePost());
        ControllerArticle ca = new ControllerArticle();
        System.out.println(ca.comentarios(document));
    }

    public static void prbComv2() {
        Usuario u = new Usuario();
        u.setUsername("test");
        Comments c = new Comments();
        c.setMensaje("test");
        c.setUsuario(u);
        List<Comments> listCom = new ArrayList<>();
        listCom.add(c);
        Articulo a = new Articulo();
        a.setTitle("Jay");
        a.setDatePost("18");
        a.setComments(listCom);
        Document document = new Document();
        List<Document> commentsDocs = new ArrayList<>();
        for (Comments comment : a.getComments()) {
            Document commentDoc = new Document();
            commentDoc.append("mensaje", comment.getMensaje());
            commentDoc.append("usuarioComenta", comment.getUsuario().getUsername());
            commentsDocs.add(commentDoc);
        }
        document.append("comments", commentsDocs);
        document.append("title", a.getTitle());
        document.append("datePost", a.getDatePost());
        ControllerArticle ca = new ControllerArticle();
        System.out.println(ca.updateComen(document));
    }
    public static void prbReac() {
        Articulo a = new Articulo();
        a.setTitle("Test");
        a.setLike(1);
        a.setDislike(1);
        Document document = new Document();
        document.append("like",a.getLike());
        document.append("dislike",a.getDislike());
        document.append("title", a.getTitle());
        ControllerArticle ca = new ControllerArticle();
        //System.out.println(ca.reacion(document));
    }
    public static void prbReacv2() {
        Articulo a = new Articulo();
        a.setTitle("Test");
        a.setLike(4);
        //a.setDislike(0);
        Document document = new Document();
        document.append("like",a.getLike());
        document.append("dislike",a.getDislike());
        document.append("title", a.getTitle());
        ControllerArticle ca = new ControllerArticle();
        System.out.println(ca.reaccionv2(document));
    }
}

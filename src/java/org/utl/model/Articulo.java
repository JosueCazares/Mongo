/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.model;

import java.util.List;

/**
 *
 * @author josue
 */
public class Articulo {
    private String title;
    private String datePost;
    private String description;
    private String image;
    private List<Reaccion> reaccion;
    private List<Comments> comments;
    private Usuario usuario;

    public Articulo() {
    }

    public Articulo(String title, String datePost, String description, String image, List<Reaccion> reaccion, List<Comments> comments, Usuario usuario) {
        this.title = title;
        this.datePost = datePost;
        this.description = description;
        this.image = image;
        this.reaccion = reaccion;
        this.comments = comments;
        this.usuario = usuario;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Reaccion> getReaccion() {
        return reaccion;
    }

    public void setReaccion(List<Reaccion> reaccion) {
        this.reaccion = reaccion;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Articulo{");
        sb.append("title=").append(title);
        sb.append(", datePost=").append(datePost);
        sb.append(", description=").append(description);
        sb.append(", image=").append(image);
        sb.append(", reaccion=").append(reaccion);
        sb.append(", comments=").append(comments);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }

    

    
    
}

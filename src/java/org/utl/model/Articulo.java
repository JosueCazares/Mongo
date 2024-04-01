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
    private String id;
    private String title;
    private String datePost;
    private String description;
    private String image;
    private int like;
    private int dislike;
    private List<Comments> comments;
    private List<Usuario> usuarios;

    public Articulo() {
    }

    public Articulo(String id, String title, String datePost, String description, String image, int like, int dislike, List<Comments> comments, List<Usuario> usuarios) {
        this.id = id;
        this.title = title;
        this.datePost = datePost;
        this.description = description;
        this.image = image;
        this.like = like;
        this.dislike = dislike;
        this.comments = comments;
        this.usuarios = usuarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Articulo{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", datePost=").append(datePost);
        sb.append(", description=").append(description);
        sb.append(", image=").append(image);
        sb.append(", like=").append(like);
        sb.append(", dislike=").append(dislike);
        sb.append(", comments=").append(comments);
        sb.append(", usuarios=").append(usuarios);
        sb.append('}');
        return sb.toString();
    }

        
}

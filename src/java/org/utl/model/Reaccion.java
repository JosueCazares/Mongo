/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.model;

/**
 *
 * @author josue
 */
public class Reaccion {
    private int like;
    private int dislake;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislake() {
        return dislake;
    }

    public void setDislake(int dislake) {
        this.dislake = dislake;
    }

    public Reaccion() {
    }

    public Reaccion(int like, int dislake) {
        this.like = like;
        this.dislake = dislake;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reaccion{");
        sb.append("like=").append(like);
        sb.append(", dislake=").append(dislake);
        sb.append('}');
        return sb.toString();
    }
    
}

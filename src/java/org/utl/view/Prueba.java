/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.view;

import java.util.List;
import org.utl.controller.ControllerArticle;
import org.utl.model.Articulo;

/**
 *
 * @author josue
 */
public class Prueba {
    public static void main(String[] args) {
        ControllerArticle ca = new ControllerArticle();
        List<Articulo> articulos = ca.read();
            for (Articulo articulo : articulos) {
                System.out.println(articulo);
            }
    }
}

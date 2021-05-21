/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author anclenius
 */
public class BuildingNotFound extends RuntimeException{
    
    public BuildingNotFound(String id) {
        super("El edificio con id " + id + " no existe en el sistema.");
    }
}

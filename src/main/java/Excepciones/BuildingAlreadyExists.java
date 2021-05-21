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
public class BuildingAlreadyExists extends RuntimeException {
    
    public BuildingAlreadyExists(String id) {
        super("El edificio con id "+ id + " ya existe en el sistema");
    }
}

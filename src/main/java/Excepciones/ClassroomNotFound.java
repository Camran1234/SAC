/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Anclenius
 */
public class ClassroomNotFound extends RuntimeException{
    
    public ClassroomNotFound(int clase,String edificio){
        super("La clase " + clase + " no existe en el edificio " + edificio);
    }
}

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
public class SubjectNotFound extends RuntimeException{
    
    public SubjectNotFound(int clave){
        super("El curso con clave " + clave + " no existe en la base de datos");
    }
}

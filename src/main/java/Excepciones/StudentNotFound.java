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
public class StudentNotFound extends RuntimeException{
    
    public StudentNotFound(long carnet) {
        super("El estudiante " + carnet + " no existe en la base de datos");
    }
}

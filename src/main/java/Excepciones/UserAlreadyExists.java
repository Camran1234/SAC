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
public class UserAlreadyExists extends RuntimeException{
    
    public UserAlreadyExists(long id) {
        super("El Usuario " + id + " ya existe en la tabla");
    }
}

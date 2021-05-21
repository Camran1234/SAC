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
public class TeacherNotFound extends RuntimeException{
    
    public TeacherNotFound(int codigo) {
        super("El catedratico con codigo " + codigo + " no existe en el sistema");
    }
    
}

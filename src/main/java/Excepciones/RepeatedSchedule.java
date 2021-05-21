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
public class RepeatedSchedule extends RuntimeException {
    
    public RepeatedSchedule(int codigo) {
        super("El horario de codigo " + codigo + " ya existe en el sistema");
    }
}

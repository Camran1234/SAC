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
public class ScheduleNotFound extends RuntimeException{
    
    public ScheduleNotFound(int codigoHorario) {
        super("El horario con codigo " + codigoHorario + " no existe en el sistema");   
    }
}

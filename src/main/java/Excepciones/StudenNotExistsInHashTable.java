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
public class StudenNotExistsInHashTable extends RuntimeException{
    
    public StudenNotExistsInHashTable(long id) {
        super("El estudiante " + id + " no existe en la tabla hash");
    }
}

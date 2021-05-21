/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Excepciones.StudentNotFound;
import Excepciones.SubjectNotFound;

/**
 *
 * @author Anclenius
 */
public class Asignacion {
    
    private final int codigoAsignacion;
    private int zona;
    private int examenFinal;
    private int carne;
    private int curso;
    private Horario horario;
    
    public Asignacion(int carne, int curso, int zona, int examenFinal){
        this.codigoAsignacion = carne;
        this.carne=carne;
        this.curso = curso;
        this.zona = zona;
        this.examenFinal = examenFinal;
    }
    
    public Asignacion(int asignacion,Horario horario) throws SubjectNotFound,StudentNotFound {
        codigoAsignacion = asignacion;
        zona = 0;
        examenFinal = 0;
        this.horario = horario;
    }
    
    public Asignacion(int asignacion,int zona, int examenFinal,Horario horario) throws SubjectNotFound,StudentNotFound {
        codigoAsignacion = asignacion;
        this.zona = zona;
        this.examenFinal = examenFinal;
        this.horario = horario;
    }
    
    public int getCodigoAsignacion(){
        return codigoAsignacion;
    }
    
    public Horario getHorario(){
        return horario;
    }
    
    public void setZona(int zona) {
        this.zona = zona;
    }
    
    public int getZona() {
        return zona;
    }
    
    public void setFinal(int notaFinal) {
        examenFinal = notaFinal;
    } 
    
    public int getFinal(){
        return examenFinal;
    }
    
    
    
    
    
}

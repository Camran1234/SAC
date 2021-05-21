/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Estructuras.ListaSimple;
import Nodos.NodoSimple;

/**
 *
 * @author Anclenius
 */
public class Estudiante extends Usuario implements Cloneable{
    
    private ListaSimple asignaciones;
    private String nombre;
    public Estudiante(long id, String nombre, String direccion){
        super(id,nombre,direccion,"",false,true);
        this.nombre = nombre;
        asignaciones = new ListaSimple("Cursos asignados del estudiante " + id);
    }
    
    public void agregarAsignacion(Asignacion asignacion) {
        asignaciones.insertarAlFinal(new NodoSimple(asignaciones.size(),asignacion));
        
    }
    
    public ListaSimple getAsignaciones(){
        return this.asignaciones;
    }

    @Override
    public Estudiante clone() throws CloneNotSupportedException {
        return (Estudiante)super.clone(); 
    }
    
    
    
    
    
}

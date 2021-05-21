/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Estructuras.ListaSimple;
import Excepciones.ClassroomNotFound;
import Nodos.NodoSimple;

/**
 *
 * @author Anclenius
 */
public class Edificio {
    private String id;
    private ListaSimple salones;
    
    public Edificio(String id) {
        this.id = id;
        this.salones = new ListaSimple("Salones del edificio " + id);
    }
    
    public String getId(){
        return id;
    }
    
    public void agregarSalon(Salon salon){
        salones.insertarAlFinal(new NodoSimple(salon.getId(),salon));
    }
    
    public Salon buscarSalon(int salon) throws ClassroomNotFound {
        NodoSimple busqueda = (NodoSimple)salones.buscar(salon);
        if(busqueda != null){
            return (Salon)busqueda.getInfo();
        } else 
        throw new ClassroomNotFound(salon,id);
    }
    
    public String printSalones(){
        return salones.printSalones();
    }
}

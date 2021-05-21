/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author Anclenius
 */
public class NodoSimple {
    int id;
    Object info;
    NodoSimple siguiente;
    
    public NodoSimple(int id, Object info){
        this.info = info;
        this.id = id;
        this.siguiente = null;
    }
    
    public int getId(){
        return this.id;
    }
    
    public NodoSimple getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
    
    public Object getInfo(){
        return this.info;
    }
    
}

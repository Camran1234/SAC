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
public class NodoCircular {
    String id;
    Object info;
    NodoCircular siguiente;
    NodoCircular anterior;
    
    public NodoCircular(String id, Object info){
        this.info = info;
        this.id = id;
        this.anterior = this.siguiente = null;
    }
    
    public String getId(){
        return this.id;
    }
    
    public NodoCircular getAnterior(){
        return this.anterior;
    }
    
    public void setAnterior(NodoCircular anterior) {
        this.anterior = anterior;
    }
    
    public NodoCircular getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(NodoCircular siguiente) {
        this.siguiente = siguiente;
    }
    
    public Object getInfo(){
        return this.info;
    }
}

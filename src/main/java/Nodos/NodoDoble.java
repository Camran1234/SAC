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
public class NodoDoble {
    int id;
    Object info;
    NodoDoble siguiente;
    NodoDoble anterior;
    
    public NodoDoble(int id, Object info){
        this.info = info;
        this.id = id;
        this.anterior = this.siguiente = null;
    }
    
    public int getId(){
        return this.id;
    }
    
    public NodoDoble getAnterior(){
        return this.anterior;
    }
    
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
    
    public NodoDoble getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    
    public Object getInfo(){
        return this.info;
    }
}

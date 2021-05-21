/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Excepciones.BuildingAlreadyExists;
import Modelos.Edificio;
import Nodos.NodoCircular;
import java.io.IOException;

/**
 *
 * @author Anclenius
 */
public class ListaCircular {
    public NodoCircular inicio;
    public NodoCircular fin;
    String nombre;
    int size;
    
    public ListaCircular(String nombre) {
        this.inicio = this.fin = null;
        this.nombre = nombre;
        this.size = 0;
    }
    
    public int getSize() {
        return size;
    }
    public void insertarElemento(NodoCircular nodo) throws BuildingAlreadyExists{
        if(estaVacia()){
            inicio = fin = nodo;
            inicio.setSiguiente(fin);
            fin.setAnterior(inicio);
            size++;
        } else {
            NodoCircular nodito = buscar(nodo.getId());
            if (nodito == null) {
                fin.setSiguiente(nodo);
                nodo.setAnterior(fin);
                nodo.setSiguiente(inicio);
                inicio.setAnterior(nodo);
                fin = nodo;
                size++;
            } else {
                throw new BuildingAlreadyExists(((Edificio)nodo.getInfo()).getId());
            }
        }
    }
    
    public NodoCircular buscar(String id) {
        if(estaVacia()) return null;
        NodoCircular aux = inicio;
        if(aux.getId().equalsIgnoreCase(id)) return aux;
        aux = aux.getSiguiente();
        while(aux != inicio) {
            if(aux.getId().equalsIgnoreCase(id)) return aux;
            aux = aux.getSiguiente();
        }
        if(aux.getId() == id) return aux;
        return null;
    }
    
    public boolean estaVacia(){
        if(inicio == null && fin == null) return true;
        return false;
    }
    
    public void eliminar(String id) {
        if(estaVacia()) return;
        NodoCircular eliminacion = buscar(id);
        if(eliminacion != null){
            if(inicio == fin && inicio.getId().equalsIgnoreCase(id)) {
                inicio = fin = null;
            } else if(inicio.getId().equalsIgnoreCase(id)) {
                fin.setSiguiente(inicio.getSiguiente());
                inicio.getSiguiente().setAnterior(fin);
                inicio = inicio.getSiguiente();
            } else if(fin.getId().equalsIgnoreCase(id)) {
                fin.getAnterior().setSiguiente(inicio);
                inicio.setAnterior(fin.getAnterior());
                fin = fin.getAnterior();
            } else {
                NodoCircular aux = inicio;
                while(aux != fin) {
                    if(eliminacion == aux) break;
                    aux = aux.getSiguiente();
                }
                if(eliminacion == aux) {
                    aux.getAnterior().setSiguiente(aux.getSiguiente());
                    aux.getSiguiente().setAnterior(aux.getAnterior());
                }
            }
            size--;
        } else {
            //No existe el elemento
            System.out.println("El elemento no existe en la lista circular " + this.nombre);
        }
    }
    
    public String printEdificios(){
        if(estaVacia()) {
            System.out.println("La lista " + this.nombre + " esta vacia");
            return null;
        }
        String salida = "digraph G {\n"
                + "rankdir=LR;\n"
                + "node [shape = record, style=filled, fillcolor=\"#CB97FC\"];\n";
        NodoCircular aux = inicio;
        Edificio edificio = (Edificio) aux.getInfo();
        salida += "nodo"+aux.getId()+" [ label = \"ID: "+edificio.getId()+"\"];\n";
        aux = aux.getSiguiente();
        while(aux != fin) {
            edificio = (Edificio) aux.getInfo();
            salida += "nodo"+aux.getId()+" [ label = \"ID: "+edificio.getId()+"\"];\n";
            aux = aux.getSiguiente();
        }
        
        salida+= "\n";
        aux = inicio;
        edificio = (Edificio)aux.getInfo();
        salida+= "nodo"+edificio.getId();
        aux = aux.getSiguiente();
        while(aux != inicio){
            edificio = (Edificio)aux.getInfo();
            salida+= "-> nodo"+edificio.getId();
            aux = aux.getSiguiente();
        }
        salida += ";\n";
        
        
        salida+= "\n";
        aux = fin;
        edificio = (Edificio)aux.getInfo();
        salida+= "nodo"+edificio.getId();
        aux = aux.getAnterior();
        while(aux != fin){
            edificio = (Edificio)aux.getInfo();
            salida+= "-> nodo"+edificio.getId();
            aux = aux.getAnterior();
        }
        salida += ";\n";
        Edificio a1 = (Edificio)inicio.getInfo();
        Edificio a2 = (Edificio)fin.getInfo();
        
        salida += "nodo"+a1.getId() + " -> nodo"+a2.getId()+";\n";
        salida += "nodo"+a2.getId() + " -> nodo"+a1.getId()+";\n";
        salida += "\n}";
        
        try {
            Controlador.Estructuras.run("Lista_de_Edificios", salida);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return salida;
    }
    
    public String printSalones(){
        if(estaVacia()) {
            System.out.println("La lista salones de " + this.nombre + " esta vacia");
            return null;
        }
        String salida = "digraph G { \nrankdir=TB; \nnode [shape=record,width=.1,height=.1, style=filled, fillcolor=\"#10CD00\"]; \n";
        salida += "node0 [label = \"";
        NodoCircular aux = inicio;
        Edificio edificio = (Edificio)aux.getInfo();
        salida += "<f"+edificio.getId()+">"+edificio.getId();
        aux = aux.getSiguiente();
        while(aux != inicio) {
            edificio = (Edificio)aux.getInfo();
            salida += "|<f"+edificio.getId()+">"+edificio.getId();
            aux = aux.getSiguiente();
        }
        salida += "\",height = .5, width = 3];\n";
        
        aux = inicio;
        edificio = (Edificio)aux.getInfo();
        salida += "node0:f"+edificio.getId()+edificio.printSalones()+";\n";
        aux = aux.getSiguiente();
        while(aux != inicio) {
            edificio = (Edificio)aux.getInfo();
            salida += "node0:f"+edificio.getId()+edificio.printSalones()+";\n";
            aux = aux.getSiguiente();
        }
        
        salida += "}";
        try {
            Controlador.Estructuras.run("Lista_de_Salones", salida);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return salida;
    }
}

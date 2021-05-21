/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Excepciones.SubjectAlreadyExists;
import Modelos.Curso;
import Modelos.Salon;
import Nodos.NodoSimple;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anclenius
 */
public class ListaSimple {

    public NodoSimple inicio;
    public NodoSimple fin;
    String nombre;
    int size;
    
    public ListaSimple(String nombre){
        inicio = fin = null;
        this.nombre = nombre;
        this.size = 0;
    }
    
    public int size(){
        return size;
    }
    
    public void insertarAlInicio(NodoSimple nodo){
        if(estaVacia()) {
            inicio = fin = nodo;
        } else {
            nodo.setSiguiente(inicio);
            inicio = nodo;
            size++;
        }
    }
    
    public boolean estaVacia(){
        if(inicio == null && fin == null) return true;
        return false;
    }
    
    public void insertarAlFinal(NodoSimple nodo){
        if(estaVacia()) {
            inicio = fin = nodo;
            size++;
        } else {
            NodoSimple a = buscar(nodo.getId());
            if(a == null){
                fin.setSiguiente(nodo);
                fin = nodo;
                size++;
            } else {
                try {
                    Curso curso = (Curso)nodo.getInfo();
                    throw new SubjectAlreadyExists(curso.getCodigo());
                } catch(ClassCastException ex){
                    JOptionPane.showMessageDialog(null, "El ID "+nodo.getId()+" ya existe en la lista " + this.nombre);
                }
                
            }
        }
    }
    
    public NodoSimple buscar(int id) {
        NodoSimple aux = inicio;
        while(aux != null) {
            if(aux.getId() == id) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public void eliminar(int id) {
        NodoSimple eliminacion = buscar(id);
        if(estaVacia()) return;
        
        if(eliminacion != null) {
            if(inicio.getId() == id && inicio == fin) {
                inicio = fin = null;
            } else {
                NodoSimple aux = inicio;
                NodoSimple anterior = null;
                while(aux.getSiguiente() != null) {
                    if(aux == eliminacion) break;
                    anterior = aux;
                    aux = aux.getSiguiente();
                }
                if(aux == eliminacion) {
                    anterior.setSiguiente(aux.getSiguiente());
                }
            }
            size++;
        } else {
            //El elemento no existe
            System.out.println("El elemento de la lista simple "+this.nombre+" que se quiere eliminar no existe");
        }
    }
    
    public String printCursos(){
        if(estaVacia()) {
            System.out.println("La lista " + this.nombre + " esta vacia");
            return null;
        }
        String salida = "digraph G {\n"
                + "rankdir=LR;\n"
                + "node [shape = record, style=filled, fillcolor=\"#FFF4AF\"];\n";
        NodoSimple aux = inicio;
        Curso curso = (Curso) aux.getInfo();
        salida += "nodo"+aux.getId()+" [ label = \"ID: "+curso.getCodigo()+"\\nNombre: "+curso.getNombre()+"\\nSemestre: "+curso.getSemestre()+"\\nCreditos: "+curso.getCreditos()+"\"];\n";
        aux = aux.getSiguiente();
        while(aux != null) {
            curso = (Curso) aux.getInfo();
            salida += "nodo"+aux.getId()+" [ label = \"ID: "+curso.getCodigo()+"\\nNombre: "+curso.getNombre()+"\\nSemestre: "+curso.getSemestre()+"\\nCreditos: "+curso.getCreditos()+"\"];\n";
            aux = aux.getSiguiente();
        }
        salida+= "\n";
        aux = inicio;
        curso = (Curso)aux.getInfo();
        salida+= "nodo"+curso.getCodigo();
        aux = aux.getSiguiente();
        while(aux != null){
            curso = (Curso)aux.getInfo();
            salida+= "-> nodo"+curso.getCodigo();
            aux = aux.getSiguiente();
        }
        salida += ";\n}";
        try {
            Controlador.Estructuras.run("Lista_de_Cursos", salida);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return salida;
    }
    
    public String printSalones() {
        if(estaVacia()) return "";
        String salida = "";
        NodoSimple aux = inicio;
        Salon salon;
        while(aux != null) {
            salon = (Salon) aux.getInfo();
            salida += " -> "+ salon.getId();
            aux = aux.getSiguiente();
        }
        
        return salida;
    }
}

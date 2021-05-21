/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Excepciones.UserAlreadyExists;
import Modelos.Usuario;
import Nodos.NodoDoble;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anclenius
 */
public class ListaDoble {

    public NodoDoble inicio;
    public NodoDoble fin;
    private final String nombre;
    private int size;

    public ListaDoble(String nombre) {
        this.nombre = nombre;
        this.inicio = this.fin = null;
        size = 0;
    }

    public void insertar(NodoDoble nodo) throws UserAlreadyExists {

        if (inicio == null && fin == null) {
            inicio = fin = nodo;
            size++;
        } else {
            NodoDoble nodito = buscar(nodo.getId());
            if (nodito == null) {
                fin.setSiguiente(nodo);
                nodo.setAnterior(fin);
                fin = nodo;
                size++;
            } else {
                throw new UserAlreadyExists(((Usuario)nodo.getInfo()).getId());
            }
        }
    }

    public NodoDoble buscar(int id) {
        NodoDoble aux = inicio;
        while (aux != null) {
            if (aux.getId() == id) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        
        return null;
    }

    public void eliminar(int id) {
        if (estaVacia()) {
            return;
        }
        NodoDoble eliminacion = buscar(id);
        if (eliminacion != null) {
            NodoDoble aux = inicio;
            if (inicio == fin && inicio.getId() == id) {
                inicio = fin = null;
            } else if (inicio.getId() == id) {
                inicio = inicio.getSiguiente();
            } else if (fin.getId() == id) {
                fin = fin.getAnterior();
            } else {
                while (aux != null) {
                    if (aux.getId() == id) {
                        break;
                    }
                    aux = aux.getSiguiente();
                }
                if (aux.getId() == id) {
                    aux.getAnterior().setSiguiente(aux.getSiguiente());
                    aux.getSiguiente().setAnterior(aux.getAnterior());
                }
            }
            size--;
        } else {
            //El elemento no existe
            JOptionPane.showMessageDialog(null, "El elemento de la lista doble " + this.nombre + " no existe");
        }
    }

    public boolean estaVacia() {
        if (inicio == null && fin == null) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }
    
    public String printUsuarios(){
        if(estaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista " + this.nombre + " esta vacia");
            return null;
        }
        String salida = "digraph G {\n"
                + "rankdir=LR;\n"
                + "node [shape = record, style=filled, fillcolor=\"#AFFFCA\"];\n";
        NodoDoble aux = inicio;
        Usuario usuario = (Usuario) aux.getInfo();
        salida += "nodo"+aux.getId()+" [ label = \"ID: "+usuario.getId()+"\\nNombre: "+usuario.getNombre()+"\\nDireccion: "+usuario.getDireccion()+"\\nContraseña: "+usuario.getPass()+"\"];\n";
        aux = aux.getSiguiente();
        while(aux != null) {
            usuario = (Usuario) aux.getInfo();
            salida += "nodo"+aux.getId()+" [ label = \"ID: "+usuario.getId()+"\\nNombre: "+usuario.getNombre()+"\\nDireccion: "+usuario.getDireccion()+"\\nContraseña: "+usuario.getPass()+"\"];\n";
            aux = aux.getSiguiente();
        }
        
        salida+= "\n";
        aux = inicio;
        usuario = (Usuario)aux.getInfo();
        salida+= "nodo"+usuario.getId();
        aux = aux.getSiguiente();
        while(aux != null){
            usuario = (Usuario)aux.getInfo();
            salida+= "-> nodo"+usuario.getId();
            aux = aux.getSiguiente();
        }
        salida += ";\n";
        
        
        salida+= "\n";
        aux = fin;
        usuario = (Usuario)aux.getInfo();
        salida+= "nodo"+usuario.getId();
        aux = aux.getAnterior();
        while(aux != null){
            usuario = (Usuario)aux.getInfo();
            salida+= "-> nodo"+usuario.getId();
            aux = aux.getAnterior();
        }
        salida += ";\n}";
        
        
        try {
            Controlador.Estructuras.run("Lista_de_Usuarios", salida);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return salida;
    }

}

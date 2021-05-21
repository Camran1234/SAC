/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Excepciones.TeacherAlreadyExists;
import Nodos.NodoAVL;
import java.io.IOException;

/**
 *
 * @author Anclenius
 */
 public class ArbolAVL {

    private NodoAVL raiz;
    private String salida;

    public ArbolAVL() {
        salida = "";
        raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void vaciarArbol() {
        raiz = null;
    }

    public void insertar(int data, Object info) throws TeacherAlreadyExists{
        raiz = this.insertar(data, raiz, info);
    }

    private int altura(NodoAVL t) {
        return t == null ? -1 : t.height;
    }

    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    private NodoAVL insertar(int x, NodoAVL t, Object info) throws TeacherAlreadyExists{
        if (t == null) {
            t = new NodoAVL(x, info);
        } else if (x < t.data) {
            t.left = ArbolAVL.this.insertar(x, t.left, info);
            if (altura(t.left) - altura(t.right) == 2) {
                if (x < t.left.data) {
                    t = rotacionIzquierda(t);
                } else {
                    t = rotacionDobleIzquierda(t);
                }
            }
        } else if (x > t.data) {
            t.right = ArbolAVL.this.insertar(x, t.right, info);
            if (altura(t.right) - altura(t.left) == 2) {
                if (x > t.right.data) {
                    t = rotacionDerecha(t);
                } else {
                    t = rotacionDobleDerecha(t);
                }
            }
        } else {
            System.out.println("ID repetido en Arbol AVL");
            throw new TeacherAlreadyExists(x);
        }
        t.height = max(altura(t.left), altura(t.right)) + 1;
        return t;
    }

    private NodoAVL rotacionIzquierda(NodoAVL k2) {
        NodoAVL k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(altura(k2.left), altura(k2.right)) + 1;
        k1.height = max(altura(k1.left), k2.height) + 1;
        return k1;
    }

    private NodoAVL rotacionDerecha(NodoAVL k1) {
        NodoAVL k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(altura(k1.left), altura(k1.right)) + 1;
        k2.height = max(altura(k2.right), k1.height) + 1;
        return k2;
    }

    private NodoAVL rotacionDobleIzquierda(NodoAVL k3) {
        k3.left = rotacionDerecha(k3.left);
        return rotacionIzquierda(k3);
    }

    private NodoAVL rotacionDobleDerecha(NodoAVL k1) {
        k1.right = rotacionIzquierda(k1.right);
        return rotacionDerecha(k1);
    }

    public int contarNodos() {
        return ArbolAVL.this.contarNodos(raiz);
    }

    private int contarNodos(NodoAVL r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l += ArbolAVL.this.contarNodos(r.left);
            l += ArbolAVL.this.contarNodos(r.right);
            return l;
        }
    }

    
    public NodoAVL buscar(int val) {
        return ArbolAVL.this.buscar(raiz, val);
    }

    private NodoAVL buscar(NodoAVL r, int val) {
        while ((r != null)) {
            int rval = r.data;
            if (val < rval) {
                r = r.left;
                return this.buscar(r,val);
            } else if (val > rval) {
                r = r.right;
                return this.buscar(r,val);
            } else {
                return r;
            }
        }
        return null;
    }

    
    public void inOrden() {
        ArbolAVL.this.inOrden(raiz);
    }

    private void inOrden(NodoAVL r) {
        if (r != null) {
            ArbolAVL.this.inOrden(r.left);
            System.out.print(r.data + " ");
            ArbolAVL.this.inOrden(r.right);
        }
    }

    
    public String preOrden() {
        if(esVacio()) return null;
        salida = "";
        ArbolAVL.this.preOrden(raiz);
        int a = salida.lastIndexOf("->");
        salida = salida.substring(0, a);
        return salida;
    }

    private void preOrden(NodoAVL r) {
        if (r != null) {
            salida += r.data + " -> ";
            ArbolAVL.this.preOrden(r.left);
            salida += r.data + " -> ";
            ArbolAVL.this.preOrden(r.right);
            salida += r.data + " -> ";
        }
    }

    public void postOrden() {
        ArbolAVL.this.postOrden(raiz);
    }

    private void postOrden(NodoAVL r) {
        if (r != null) {
            ArbolAVL.this.postOrden(r.left);
            ArbolAVL.this.postOrden(r.right);
            System.out.print(r.data + " ");
        }
    }
    
    public String print(){
        String sal= "digraph G { nodesep=.05;\n" +
                    "rankdir=TB;\n" +
                    "node [shape=folder,width=.1,height=.1, style=filled, fillcolor=\"#FFFF0C\"];\n";
        sal += preOrden();
        sal += ";\n}";
        try {
            Controlador.Estructuras.run("Arbol_de_Catedraticos", sal);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return sal;
    }
    
    
}

package Nodos;

import Estructuras.ListaSimple;
import Modelos.Asignacion;
import Modelos.Horario;

public class NodoB {

    public int nodosActuales;
    public Horario[] claves;
    public NodoB[] punteros; 
    public boolean esHoja;    
    
    
    //Crea el Nodo en tiempo de ejecucion
    public NodoB(Horario horario) {
        claves = new Horario[3];
        punteros = new NodoB[4];
        esHoja = true;       
        
    }
    //Crea el nodo raiz
    public NodoB() {
        claves = new Horario[3];
        punteros = new NodoB[4];
        esHoja = true;
    }
    
    
    
    
}

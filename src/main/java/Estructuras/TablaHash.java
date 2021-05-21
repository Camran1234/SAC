/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Excepciones.StudentAlreadyExists;
import Modelos.Estudiante;
import Nodos.NodoSimple;
import java.io.IOException;

/**
 *
 * @author Anclenius
 */
public class TablaHash {

    private Estudiante[] array;
    private static final int CAPACIDAD = 37;
    private int capacidadActual;
    private int datosActuales;

    public TablaHash() {
        array = new Estudiante[37];
        capacidadActual = CAPACIDAD;
        datosActuales = 0;
    }

    public TablaHash(int capacidad) {
        if (esPrimo(capacidad)) {
            array = new Estudiante[capacidad];
            capacidadActual = capacidad;
        } else {
            capacidadActual = siguientePrimo(capacidad);
            array = new Estudiante[capacidadActual];
        }
        datosActuales = 0;
    }

    public TablaHash reHash() {
        Estudiante[] nuevosEstudiantes;
        TablaHash newHash = new TablaHash(capacidadActual + 1);
        for (Estudiante estudiante : array) {

        }
        return newHash;
    }

    /*
    *Funcion de insercion utilizada h(llv) = llv mod M
     */
    public boolean insertar(Estudiante estudiante) throws StudentAlreadyExists {
        int llave = (int) (estudiante.getId() % capacidadActual);
        if(buscar(estudiante.getId()) == null){
            if (array[llave] == null) {
                array[llave] = estudiante;
            } else {
                array[solucionarColision(estudiante.getId())] = estudiante;
            }
            datosActuales++;
            if (estaAl55()) {
                return true;
            }
            return false;
        } else {
            throw new StudentAlreadyExists(estudiante.getId());
        }
    }

    private int solucionarColision(long llave, int intento) {
        int clave = (((int) (llave % 7)) + 1) * intento;
        if (array[clave] == null) {
            return clave;
        } else {
            return solucionarColision(llave, intento + 1);
        }
    }

    public int solucionarColision(long llave) {
        return solucionarColision(llave, 1);
    }

    public boolean estaAl55() {
        float a = (float) datosActuales / capacidadActual;
        if (a >= 0.25) {
            return true;
        }
        return false;
    }

    public Estudiante buscar(long registro) {
        Estudiante estudiante;
        for (int i = 0; i < capacidadActual; i++) {
            estudiante = array[i];
            if (estudiante != null) {
                if (estudiante.getId() == registro) {
                    return estudiante;
                }
            }
        }
        return null;
    }

    public void eliminar(long registro) {
        for (int i = 0; i < capacidadActual; i++) {
            if (array[i].getId() == registro) {
                array[i] = null;
            }
        }
    }

    public ListaSimple exportarEstudiantes() {
        ListaSimple lista = new ListaSimple("Listado completo de estudiantes");
        for (int i = 0; i < capacidadActual; i++) {
            if (array[i] != null) {
                lista.insertarAlFinal(new NodoSimple((int) array[i].getId(), array[i]));
            }
        }
        return lista;
    }

    public static boolean esPrimo(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n == 1 || n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int siguientePrimo(int n) {
        if (n % 2 == 0) {
            n++;
        }
        for (; !esPrimo(n); n += 2);

        return n;
    }

    public String print() {
        if(esVacio()) return null;
        String salida = "digraph G {\n"
                + "nodesep=.05;\n"
                + "rankdir=LR;\n"
                + "node [shape=record,width=.1,height=.1];\n"
                + "node0 [label = \"";
        for(int i = 0; i < capacidadActual; i++) {
            if(array[i] != null) {
                salida += "<f"+i+">"+i+"|";  
            }
        }
        int a = salida.lastIndexOf("|");
        salida = salida.substring(0,a);
        salida += "\",height = 1];\n";
        for(int i = 0; i<capacidadActual;i++) {
            if(array[i] != null) {
                salida += "node"+i+" [label = \"" + array[i].getId() +"\"];\n";
            }
        }
        salida += "node [width = 1.5];\n";
        for (int i = 0; i < capacidadActual; i++) {
            if(array[i] != null) {
                salida += "node0:f"+i+" -> node"+i+"\n";
            }
        }
        
        salida += "}\n";
        try {
            Controlador.Estructuras.run("Tabla_de_estudiantes", salida);
        } catch (IOException ex) {
            System.out.println("Error inesperado");
        }
        return salida;
    }
    
    public boolean esVacio(){
        for (int i = 0; i < capacidadActual; i++) {
            if(array[i] != null){
                return false;
            }
        }
        return true;
    }
}

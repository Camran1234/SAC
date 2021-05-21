package Estructuras;

import Excepciones.ScheduleNotFound;
import Modelos.Horario;
import Modelos.Salon;
import Nodos.NodoB;
import Nodos.NodoSimple;

public class ArbolB {
    
    public NodoB raiz;
    private ListaSimple horarios;
    private ListaSimple aprobados;
    
    public ArbolB() {
        horarios = new ListaSimple("Lista de horarios para reporte 3");
        aprobados = new ListaSimple("Lista de horarios para ese semestre");
        raiz = new NodoB();
        
        raiz.esHoja = true;
        raiz.nodosActuales = 0;
        
        raiz.claves[0] = null;
    }

    
    public void insertar(Horario horario)
    {       
        NodoB r = raiz;  
        if (r.nodosActuales == 3) {         
            NodoB s = new NodoB(horario);  
            raiz = s;
            s.nodosActuales = 0;
            s.esHoja = false;
            s.punteros[0] = r;
            split(s, 1, r); 
            insertarNodoNoLleno(s, horario);
        } 
        else {
            insertarNodoNoLleno(r, horario);
        }
    }

    public void insertarNodoNoLleno(NodoB nodo, Horario valor) {
        int i = nodo.nodosActuales;
        if (nodo.esHoja) {
            while (i >= 1 && valor.getCodigo() < nodo.claves[i - 1].getCodigo()) {
                nodo.claves[i] = nodo.claves[i - 1];
                i--;
            }
            nodo.claves[i] = valor;
            nodo.nodosActuales++;
        } else {
            while (i >= 1 && valor.getCodigo() < nodo.claves[i - 1].getCodigo()) {
                i--;
            }
            i++;
            if (nodo.punteros[i - 1].nodosActuales == 3) {
                split(nodo, i, nodo.punteros[i - 1]);
                if (valor.getCodigo() > nodo.claves[i - 1].getCodigo()) {
                    i++;
                }
            }
            insertarNodoNoLleno(nodo.punteros[i - 1], valor);
        }
    }

    public void split(NodoB nodoPadre, int indexHijo, NodoB nuevoHijo) {
        NodoB z = new NodoB();
        z.esHoja = nuevoHijo.esHoja;
        z.nodosActuales = 1;
        z.claves[0] = nuevoHijo.claves[2];

        if (!nuevoHijo.esHoja) {
            z.punteros[1] = nuevoHijo.punteros[3];
            z.punteros[0] = nuevoHijo.punteros[2];
        }
        nuevoHijo.nodosActuales = 1; 

        for (int j = nodoPadre.nodosActuales + 1; j >= indexHijo + 1; j--) {
            nodoPadre.punteros[j] = nodoPadre.punteros[j - 1];
            nodoPadre.claves[j - 1] = nodoPadre.claves[j - 2];
        }
        nodoPadre.punteros[indexHijo] = z;
        nodoPadre.claves[indexHijo - 1] = nuevoHijo.claves[1];
        nodoPadre.nodosActuales++;
    }

    public Horario buscar(NodoB nodo, int valor) throws ScheduleNotFound {
        int i = 1;
        while (i <= nodo.nodosActuales && valor > nodo.claves[i - 1].getCodigo()) {
            i++;
        }
        if (i <= nodo.nodosActuales && valor == nodo.claves[i - 1].getCodigo()) {
            System.out.println(nodo.claves[i-1].getCodigo());
            return nodo.claves[i-1];
        }
        if (!nodo.esHoja) {
            return ArbolB.this.buscar(nodo.punteros[i - 1], valor);
        }
        throw new ScheduleNotFound(valor);
    }

    public Horario buscar(int k) throws ScheduleNotFound { 
        NodoB x = raiz;
        return ArbolB.this.buscar(x, k);
    }
    
    public void imprimirArbol() {
        
        imprimirArbol(raiz, "");
    }
    
    
    public void imprimirArbol(NodoB nodo, String indentacion) {
        
        if (nodo == null) {
            System.out.println(indentacion + "El arbol B esta vacio");
        } else {
            System.out.println(indentacion + " ");

            
            String indentacionHijo = indentacion + "\t";

            
            for (int i = nodo.nodosActuales-1; i >= 0; i--) {
                if (!nodo.esHoja)
                {
                    imprimirArbol(nodo.punteros[i], indentacionHijo);
                }
                
                if(nodo.claves[i].getCodigo() > 0)
                    System.out.println(indentacionHijo + nodo.claves[i].getCodigo());
            }
            if (!nodo.esHoja)
            {
                imprimirArbol(nodo.punteros[nodo.nodosActuales], indentacionHijo);
            }
        }
    }
    
    public ListaSimple getHorarios(){
        horarios = new ListaSimple("Lista de horarios");
        getHorarios(raiz);
        return horarios;
    }
    
    public void getHorarios(NodoB nodo) {
        if (nodo == null) {
            System.out.println("El arbol B esta vacio");
        } else {
            
            for (int i = nodo.nodosActuales-1; i >= 0; i--) {
                if (!nodo.esHoja)
                {
                    getHorarios(nodo.punteros[i]);
                }
                    horarios.insertarAlFinal(new NodoSimple(horarios.size()+1,nodo.claves[i]));
            }
            if (!nodo.esHoja)
            {
                getHorarios(nodo.punteros[nodo.nodosActuales]);
            }
        }
    }
    
    public ListaSimple getCursosDeSalones(Salon salon) {
        horarios = new ListaSimple("Lista de horarios para reporte 3");
        getCursosDeSalones(raiz,salon);
        return horarios;
    }
    
    private void getCursosDeSalones(NodoB nodo,Salon salon) {
        if (nodo == null) {
            System.out.println("El arbol B esta vacio");
        } else {
            
            for (int i = nodo.nodosActuales-1; i >= 0; i--) {
                if (!nodo.esHoja)
                {
                    getCursosDeSalones(nodo.punteros[i], salon);
                }
                
                if(nodo.claves[i].getSalon() == salon)
                    horarios.insertarAlFinal(new NodoSimple(horarios.size()+1,nodo.claves[i]));
            }
            if (!nodo.esHoja)
            {
                getCursosDeSalones(nodo.punteros[nodo.nodosActuales], salon);
            }
        }
    }
    
    public ListaSimple getCursosDeSalonesSemestre(int semestre) {
        aprobados = new ListaSimple("Lista de horarios para reporte 4");
        getCursosDeSalonesSemestre(raiz,semestre);
        return aprobados;
    }
    
    private void getCursosDeSalonesSemestre(NodoB nodo,int semestre) {
        if (nodo == null) {
            System.out.println("El arbol B esta vacio");
        } else {
            
            for (int i = nodo.nodosActuales-1; i >= 0; i--) {
                if (!nodo.esHoja)
                {
                    getCursosDeSalonesSemestre(nodo.punteros[i],semestre);
                }
                
                if(nodo.claves[i].getCurso().getSemestre() == semestre)
                    aprobados.insertarAlFinal(new NodoSimple(horarios.size()+1,nodo.claves[i]));
            }
            if (!nodo.esHoja)
            {
                getCursosDeSalonesSemestre(nodo.punteros[nodo.nodosActuales], semestre);
            }
        }
    }
}

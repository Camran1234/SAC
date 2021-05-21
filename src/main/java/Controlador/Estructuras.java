/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Estructuras.ArbolAVL;
import Estructuras.ArbolB;
import Estructuras.ListaCircular;
import Estructuras.ListaDoble;
import Estructuras.ListaSimple;
import Estructuras.TablaHash;
import Excepciones.BuildingAlreadyExists;
import Excepciones.BuildingNotFound;
import Excepciones.RepeatedSchedule;
import Excepciones.ScheduleNotFound;
import Excepciones.StudenNotExistsInHashTable;
import Excepciones.StudentAlreadyExists;
import Excepciones.StudentNotFound;
import Excepciones.SubjectAlreadyExists;
import Excepciones.SubjectNotFound;
import Excepciones.TeacherAlreadyExists;
import Excepciones.TeacherNotFound;
import Excepciones.UserAlreadyExists;
import Modelos.Asignacion;
import Modelos.Catedratico;
import Modelos.Curso;
import Modelos.Edificio;
import Modelos.Estudiante;
import Modelos.Horario;
import Modelos.Salon;
import Modelos.Usuario;
import Nodos.NodoCircular;
import Nodos.NodoDoble;
import Nodos.NodoSimple;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Anclenius
 */
public class Estructuras {

    public static ArbolAVL catedraticos = new ArbolAVL();
    public static ArbolB horarios = new ArbolB();
    public static TablaHash estudiantes = new TablaHash();
    public static ListaDoble usuarios = new ListaDoble("Lista Usuarios");
    public static ListaCircular edificios = new ListaCircular("Lista de Edificios");

    public static ListaSimple cursos = new ListaSimple("Lista de Cursos");

    /*
    *Constructores de cada modelo:
    *   Usuario: id, nombre, direccion, contraseña, puedeLogearse
    *   Catedratico: id,nombre,direccion,contraseña.
    *   Estudiante: id,nombre,direccion.
    *   Catedratico: id,nombre,direccion.
    *   Edificio: id.
    *   Salon: id,capacidad.
    *   Curso: codigo,nombre,semestre,creditos.
     */

    /**
     * ***********************************************Usuarios**************************************************************
     */
    public static Usuario buscarUsuario(String name, String pass) {
        if (usuarios.size() != 0) {
            NodoDoble aux = usuarios.inicio;
            Usuario usuario;
            while (aux != null) {
                usuario = (Usuario) aux.getInfo();
                if (usuario.getNombre().equalsIgnoreCase(name) && usuario.getPass().equalsIgnoreCase(pass)) {
                    return usuario;
                }
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null,"El usuario o contraseña no son validos.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay usuarios en el sistema");
        }
        return null;
    }

    public static void agregarUsuario(Usuario usuario) throws CloneNotSupportedException,UserAlreadyExists,StudentAlreadyExists,StudenNotExistsInHashTable {
        if (usuario.esEstudiante()) {
            agregarUsuarioEstudiante(usuario);
        } else {
            agregarUser(usuario);
        }
        usuarios.printUsuarios();
    }

    private static void agregarUser(Usuario usuario) throws UserAlreadyExists{
        NodoDoble nodo = new NodoDoble((int) usuario.getId(), usuario);
        usuarios.insertar(nodo);
    }

    public static void agregarEstudiante(Estudiante estudiante) throws StudentAlreadyExists {
        boolean flag = estudiantes.insertar(estudiante);
        if (flag) {
            estudiantes = estudiantes.reHash();
        }
        estudiantes.print();
    }

    public static void agregarUsuarioEstudiante(Usuario usuario) throws CloneNotSupportedException,StudenNotExistsInHashTable {
        Estudiante estudianta = estudiantes.buscar(usuario.getId());
        if (estudianta != null) {
            Estudiante estudiante = estudianta.clone();
            System.out.println("Estudiante encontrado en la tabla hash");
            estudiante.setLog(true);
            estudiante.setPass(usuario.getPass());
            estudiante.setNombre(usuario.getNombre());
            agregarUser(estudiante);
        } else {
            throw new StudenNotExistsInHashTable(usuario.getId());
        }
    }

    private static Estudiante buscarEstudiante(long carnet) throws StudentNotFound {
        Estudiante estudiante = Controlador.Estructuras.estudiantes.buscar(carnet);
        if (estudiante != null) {
            return estudiante;
        } else {
            throw new StudentNotFound(carnet);
        }
    }

    /**
     * ***********************************************Edificios y Salones**************************************************************
     */
    public static void agregarEdificio(Edificio edificio) throws BuildingAlreadyExists{
        NodoCircular nodo = new NodoCircular(edificio.getId(), edificio);
        edificios.insertarElemento(nodo);
        edificios.printEdificios();
    }

    public static void agregarSalon(String nombreEdificio, Salon salon) throws BuildingNotFound{
        Edificio edificio = buscarEdificio(nombreEdificio);
        if (edificio != null) {
            edificio.agregarSalon(salon);
        } else {
            System.out.println("El edificio no se encontro");
            throw new BuildingNotFound(nombreEdificio);
            
        }
        edificios.printSalones();
    }

    public static Edificio buscarEdificio(String nombre) {
        NodoCircular aux = edificios.inicio;
        NodoCircular inicio = edificios.inicio;
        NodoCircular fin = edificios.fin;
        if (inicio.getId().equalsIgnoreCase(nombre)) {
            return (Edificio) inicio.getInfo();
        }
        if (fin.getId().equalsIgnoreCase(nombre)) {
            return (Edificio) fin.getInfo();
        }
        aux = aux.getSiguiente();
        while (aux != inicio) {
            if (aux.getId().equalsIgnoreCase(nombre)) {
                return (Edificio) aux.getInfo();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    /**
     * ***********************************************Catedraticos**************************************************************
     */
    public static void insertarCatedratico(Catedratico catedratico) throws TeacherAlreadyExists{
        catedraticos.insertar((int) catedratico.getId(), catedratico);
        catedraticos.print();
    }

    public static Catedratico buscarCatedratico(int codigoDocente) throws TeacherNotFound {
        try {
        Catedratico catedratico = (Catedratico) catedraticos.buscar(codigoDocente).getInfo();
        return catedratico;
        
        } catch (NullPointerException ex) {
            throw new TeacherNotFound(codigoDocente);
        }
        
    }

    /**
     * ***************************************************Cursos*****************************************************************
     */
    public static void agregarCurso(Curso curso) throws SubjectAlreadyExists{
        
        cursos.insertarAlFinal(new NodoSimple(curso.getCodigo(), curso));
        cursos.printCursos();
    }

    public static Curso buscarCurso(int clave) throws SubjectNotFound {
        NodoSimple nodo = cursos.buscar(clave);
        if (nodo != null) {
            return (Curso) cursos.buscar(clave).getInfo();
        }
        throw new SubjectNotFound(clave);
    }
    
    public static void asd() {
        horarios.imprimirArbol();
    }

    /**
     * **********************************************Horario y asignacion********************************************************
     */
    
    public static void agregarHorario(Horario horario) throws RepeatedSchedule {
        try {
            Horario schedule = horarios.buscar(horario.getCodigo());
            throw new RepeatedSchedule(horario.getCodigo());
        } catch (ScheduleNotFound ex) {
            horarios.insertar(horario);
            System.out.println("\n\n\n\n\n");
            horarios.imprimirArbol();
        }
    }

    public static Horario buscarHorario(int codigoHorario) throws ScheduleNotFound {
        return horarios.buscar(codigoHorario);
    }

    private static Estudiante crearAsignacion(int id, long carnetEstudiante,Horario horario) throws StudentNotFound {
        Estudiante estudiante = Controlador.Estructuras.buscarEstudiante(carnetEstudiante);
        estudiante.agregarAsignacion(new Asignacion(id,horario));
        return estudiante;
    }

    public static void asignarCurso(long carnetEstudiante, int codigoHorario) throws ScheduleNotFound, StudentNotFound {
        Horario horario = Controlador.Estructuras.buscarHorario(codigoHorario);
        Estudiante estudiante = crearAsignacion(horario.getListSize(), carnetEstudiante, horario);
        horario.agregarEstudiante(estudiante);
    }
    
    private static Estudiante crearAsignacion(int id, long carnetEstudiante,int zona,int eFinal,Horario horario) throws StudentNotFound {
        Estudiante estudiante = Controlador.Estructuras.buscarEstudiante(carnetEstudiante);
        estudiante.agregarAsignacion(new Asignacion(id,zona,eFinal,horario));
        return estudiante;
    }

    public static void asignarCurso(long carnetEstudiante, int codigoHorario,int zona,int eFinal) throws ScheduleNotFound, StudentNotFound {
        Horario horario = Controlador.Estructuras.buscarHorario(codigoHorario);
        Estudiante estudiante = crearAsignacion(horario.getListSize(), carnetEstudiante,zona,eFinal,horario);
        horario.agregarEstudiante(estudiante);
    }
    
    public static ListaSimple obtenerHorarios(Salon salon) {
        return horarios.getCursosDeSalones(salon);
    }
    
    public static ListaSimple obtenerPorSemestre(int semestre) {
        return horarios.getCursosDeSalonesSemestre(semestre);
    }
    
    public static void run(String fileName,String salida) throws IOException {
        File file = new File(fileName+".dot");
        if(!file.exists()) file.createNewFile();
        try {
            PrintWriter out = new PrintWriter(file);
            out.print(salida);
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("error");
        }
        
        String command = "dot -Tpng "+fileName+".dot -o "+fileName+".png"; 
	Runtime.getRuntime().exec(command);
        
    }
    
    

}

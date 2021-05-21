/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Estructuras.ListaSimple;
import Excepciones.ClassroomNotFound;
import Excepciones.StudentNotFound;
import Excepciones.SubjectNotFound;
import Excepciones.TeacherNotFound;
import Nodos.NodoSimple;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Anclenius
 */
public class Horario {
    private int codigo;
    private Date horaInicial;
    private String dimensionalInicial;
    private Date horaFinal;
    private String dimensionalFinal;
    private String dia;
    private Salon salon;
    private Edificio edificio;
    private Curso curso;
    private Catedratico catedratico;
    private ListaSimple asignaciones;
    private static SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    
    public Horario (int codigo,String inicio,String fin,String dia,String edificio,int salon,int curso,int idCatedratico) throws SubjectNotFound,ClassroomNotFound, ParseException,TeacherNotFound{
        this.codigo = codigo;
        this.dia = dia;
        if(inicio.contains("am")){
            //am
            dimensionalInicial = "am";
            inicio.replace("am","");
        }else{
            //pm
            dimensionalInicial = "pm";
            inicio.replace("pm","");
        }
        
        if(fin.contains("am")){
            dimensionalFinal = "am";
            fin.replace("am","");
        }else{
            fin.replace("pm","");
            dimensionalFinal = "pm";
        }
        this.horaInicial = sdf.parse(inicio);
        this.horaFinal = sdf.parse(fin);
        this.edificio = (Edificio)Controlador.Estructuras.edificios.buscar(edificio).getInfo();
        this.salon = this.edificio.buscarSalon(salon);
        this.curso = buscarCurso(curso);
        this.catedratico = Controlador.Estructuras.buscarCatedratico(idCatedratico);
        asignaciones = new ListaSimple("Lista de estudiantes del horario " + codigo);
    }
    
    private static Curso buscarCurso(int codigo) throws SubjectNotFound {
        Curso curso = Controlador.Estructuras.buscarCurso(codigo);
        if(curso != null) return curso;
        throw new SubjectNotFound(codigo);
    }
    
    public Catedratico getCatedratico(){
        return catedratico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        Horario.sdf = sdf;
    }
    
    public int getListSize(){
        return asignaciones.size();
    }
    
    public void agregarEstudiante(Estudiante estudiante) {
        asignaciones.insertarAlFinal(new NodoSimple(asignaciones.size() + 1,estudiante));
        
    }
    
    public ListaSimple getAsignaciones(){
        return this.asignaciones;
    }
    
    
}

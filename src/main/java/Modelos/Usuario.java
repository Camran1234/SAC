/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Anclenius
 */
public class Usuario {
    private long id;
    private String nombre;
    private String password;
    private String direccion;
    private boolean canLog;
    private boolean esEstudiante;
    
    public Usuario (long id, String nombre, String direccion, String password, boolean canLog,boolean esEstudiante) {
        this.esEstudiante = esEstudiante;
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.direccion = direccion;
        this.canLog = canLog;
    }
    
    public boolean esEstudiante(){
        return esEstudiante;
    }

    public void setLog(boolean flag) {
        this.canLog = flag;
    }
    
    public boolean canLog(){
        return canLog;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getPass(){
        return password;
    }
    
    public void setPass(String pass){
        this.password = pass;
    }
}

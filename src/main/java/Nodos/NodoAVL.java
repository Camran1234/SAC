/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author Anclenius
 */
public class NodoAVL {

    public NodoAVL left, right;
    public int data;
    public int height;
    public Object info;
    /* Constructor */
    public NodoAVL(int n,Object info) {
        left = null;
        right = null;
        data = n;
        height = 0;
        this.info = info;
    }
    
    public Object getInfo(){
        return info;
    }
}

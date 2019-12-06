/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author mounir
 */
public class NoeudAvl {
    int value; 
    NoeudAvl fg;
    NoeudAvl fd;
    int profendeur;
    int balence;
    public NoeudAvl(int x){
        value=x;
        fg=null;
        fd=null;
        profendeur=0;
        balence=0;
        
    }
    
    
    
    
}


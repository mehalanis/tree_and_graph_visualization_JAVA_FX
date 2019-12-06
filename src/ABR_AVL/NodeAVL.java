/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABR_AVL;

/**
 *
 * @author mounir
 */
public class NodeAVL extends Node {
    private int balence;
    private int profendeur;
    public NodeAVL(int x){
        super(x);
        balence=0;
        
    }

    public int getProfendeur() {
        return profendeur;
    }

    public void setProfendeur(int profendeur) {
        this.profendeur = profendeur;
    }
    
    public int getBalence() {
        return balence;
    }

    public void setBalence(int balence) {
        this.balence = balence;
    }
    
}


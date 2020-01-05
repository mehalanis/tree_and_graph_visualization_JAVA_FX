
package Arbre.AMR;

// creation de notre structure de noeud

import Arbre.Formes.rectangle;



public class Noeud {

    int[] data;		//tableau des valeurs.
    Noeud[] children;	//tableau des noeuds de fils.
    Parent parent;
    int size;		    //taille de noeud qui doit �tre < ordre-1 .
    boolean leaf;		//true si feuille.
    rectangle rectangle[];
    //le constructeur.. 
    public Noeud(int t) { //t est l'ordre de notre AMR
        this.data = new int[t - 1];
        this.children = new Noeud[t];
        this.rectangle=new rectangle[t-1];
        this.parent = new Parent(null, 0);
        this.leaf = true;
    }

    public rectangle getRectangle(int i) {
        return rectangle[i];
    }

    public void setRectangle(rectangle rectangle,int i) {
        this.rectangle[i] = rectangle;
    }
    
    
}

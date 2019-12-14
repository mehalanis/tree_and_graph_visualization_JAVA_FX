/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_arbre;

/**
 *
 * @author Abdou
 */
public class Pair {

    private B_Arbre monNoued;
    private int Level;
    private int x;
    private int y;
    public Pair(B_Arbre monNoued, int Level) {
        this.monNoued = monNoued;
        this.Level = Level;
    }
    
    public Pair(B_Arbre monNoued, int Level, int x,int y) {
        this.Level = Level;
        this.monNoued = monNoued;
        this.x = x;
        this.y=y;
    }

    public B_Arbre getMonNoued() {
        return monNoued;
    }

    public int getLevel() {
        return Level;
    }

    public void AfficherPair() {
        System.out.print("Level :" + this.Level + "  les Valeur de Noued : ");

        this.monNoued.afficher();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}

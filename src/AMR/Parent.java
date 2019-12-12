/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMR;

public class Parent {

    int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Noeud getPapa() {
        return papa;
    }

    public void setPapa(Noeud papa) {
        this.papa = papa;
    }
    Noeud papa;

    public Parent(Noeud pap, int po) {
        this.papa = pap;
        this.pos = pos;
    }

}

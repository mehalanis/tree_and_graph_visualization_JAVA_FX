package ABR_AVL;

import Formes.CercleAVL;

public class NodeAVL extends Node {
    private int balence;
    private int profendeur;
    public NodeAVL(int x){
        super(x);
        balence=0;
        super.setC(new CercleAVL(x,balence));
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
    public NodeAVL getFG() {
        return (NodeAVL)super.getFG();
    }

    public NodeAVL getFD() {
        return (NodeAVL)super.getFD();
    }
}


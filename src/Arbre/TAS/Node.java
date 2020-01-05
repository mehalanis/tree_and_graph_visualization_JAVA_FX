package Arbre.TAS;

import Arbre.Formes.Cercle;
import javafx.scene.shape.Line;


public class Node {
    private int val;
    private Cercle c; 
    private Line LFG,LFD;
    public Node(int val) {
        this.val = val;
        this.c = new Cercle(val);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Cercle getC(int x, int y) {
        c.setLayout(x, y);
        return c;
    }

    public Cercle getC() {
        return c;
    }
    
    public void setC(Cercle c) {
        this.c = c;
    }

    public Line getLFG() {
        return LFG;
    }

    public void setLFG(Line LFG) {
        this.LFG = LFG;
    }

    public Line getLFD() {
        return LFD;
    }

    public void setLFD(Line LFD) {
        this.LFD = LFD;
    }
    
    
}

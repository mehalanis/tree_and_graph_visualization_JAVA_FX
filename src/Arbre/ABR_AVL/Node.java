package Arbre.ABR_AVL;

import Arbre.Formes.Cercle;
import java.util.*;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Node {
   private int val;
   private Node FG=null,FD=null;
   private Line LFG,LFD;
   private Cercle c;
    public Node(int val) {
        this.val = val;
        c=new Cercle(val);
    }

    public void setVal(int val) {
        this.val = val;
        c.setVal(val);
    }

    public void setFG(Node FG) {
        this.FG = FG;
    }

    public void setFD(Node FD) {
        this.FD = FD;
    }

    public int getVal() {
        return val;
    }

    public Node getFG() {
        return FG;
    }

    public Node getFD() {
        return FD;
    }

    public void setC(Cercle c) {
        this.c = c;
    }
    
    public Cercle getC() {
        return c;
    }    
    public Cercle getCircle(int x,int y){
        c.setLayout(x, y);
        return c;
    }
    public void setCircleColor(Color color){
         c.setStroke(color);
    }

    public void setLFG(Line LFG) {
        this.LFG = LFG;
    }

    public void setLFD(Line LFD) {
        this.LFD = LFD;
    }

    public Line getLFG() {
        return LFG;
    }

    public Line getLFD() {
        return LFD;
    }
    public void setColorLFG(Color color){
        LFG.setStroke(color);
    }
    public void setColorLFD(Color color){
        LFD.setStroke(color);
    }
    
}
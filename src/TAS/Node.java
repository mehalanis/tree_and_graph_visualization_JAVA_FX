/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import Formes.Cercle;

/**
 *
 * @author meder
 */
public class Node {
    private int val;
    private Cercle c; 

    public Node(int val, Cercle c) {
        this.val = val;
        this.c = c;
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

    public void setC(Cercle c) {
        this.c = c;
    }
    
    
}

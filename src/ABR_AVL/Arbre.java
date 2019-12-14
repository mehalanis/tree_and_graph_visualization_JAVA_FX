package ABR_AVL;

import Formes.Cercle;
import Formes.CercleAVL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public abstract class Arbre {

    public Node root;
    public AnchorPane group;
    
    public Arbre(AnchorPane group) {
        this.group = group;
    }
    
    public abstract void insertion(int n);

    public abstract void suppression(int val,char r);

    /*public void Afficher() {
        Afficher(this.root);
    }

    private void Afficher(Node root) {
        if (root != null) {
            Afficher(root.getFG());
            System.out.println(root.getVal());
            Afficher(root.getFD());
        }
    } */   

    public int depth(Node t) {
        if (t == null) {
            return -1;
        }
        return 1 + Math.max(depth(t.getFG()), depth(t.getFD()));
    }
    public void Afficher(){
        group.getChildren().clear();
        int gap = this.depth(this.root);
        gap = gap * 2 *24;
        this.Afficher(this.root,null, group ,(int)group.getWidth()/ 2, 0,(int)group.getWidth()/ 2, 0,0,gap);
    }
    public void Afficher(Node t, Node root, AnchorPane g, int x, int y, int prevx, int prevy, int lev, int gap) {        
        
        if (t == null) {
            return;
        }
        if(t instanceof NodeAVL){
           t.setC(new CercleAVL(t.getVal(),((NodeAVL)t).getBalence()));
        }else{
          t.setC(new Cercle(t.getVal()));              
        }
        g.getChildren().add(t.getCircle(x, y));
        
        if ((++lev) != 1) {
            Line line = new Line(prevx + 23, prevy + 46, x + 23, y);
            line.setStrokeWidth(2);
            if (root.getFD() == t) {
                root.setLFD(line);
            } else {
                root.setLFG(line);
            }
            g.getChildren().add(line);
            gap = (gap) / 2;
        }
        Afficher(t.getFG(), t, g, x - gap, y + 50, x, y, lev, gap);
        Afficher(t.getFD(), t, g, x + gap, y + 50, x, y, lev, gap);
    }

    public void rechercher(Label label, String val) {
        rechercher(label, Integer.parseInt(val));
    }

    public void rechercher(Label label, int val) {
        Find t = new Find(root, label, val);
        t.start();
    }
    public Node[] Successeur(Node root){
        Node[] tab=new Node[2]; //[0]=PS , [1]=S
        tab[0]=root;
        tab[1]=root.getFD();
        if(tab[1]!=null){
            while(tab[1].getFG()!=null){
                tab[0]=tab[1];
                tab[1]=tab[1].getFG();
            }
        }
        return tab;
    }
    public Node[] Predecesseur(Node root){
        Node[] tab=new Node[2]; //[0]=PP , [1]=P
        tab[0]=root;
        tab[1]=root.getFG();
        if(tab[1]!=null){
            while(tab[1].getFD()!=null){
                tab[0]=tab[1];
                tab[1]=tab[1].getFD();
            }
        }
        return tab;
    }
}

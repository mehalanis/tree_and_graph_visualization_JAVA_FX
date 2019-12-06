package ABR_AVL;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public abstract boolean suppression(int val);

    public void Afficher() {
        Afficher(this.root);
    }

    private void Afficher(Node root) {
        if (root != null) {
            Afficher(root.getFG());
            System.out.println(root.getVal());
            Afficher(root.getFD());
        }
    }    

    public int depth(Node t) {
        if (t == null) {
            return 0;
        }
        return 1 + Math.max(depth(t.getFG()), depth(t.getFD()));
    }

    public void Afficher(Node t, Node root, AnchorPane g, int x, int y, int prevx, int prevy, int lev, int gap) {        
        
        if (t == null) {
            return;
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
        System.out.println("node = " + t.getVal() + " x = " + x + " y= " + y + " gap=" + gap);
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
}

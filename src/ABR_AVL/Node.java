package ABR_AVL;
import Formes.Cercle;
import java.util.*;
import javafx.scene.paint.Color;

public class Node {
   private int val;
   private Node FG=null,FD=null;
   Cercle c;
    public Node(int val) {
        this.val = val;
        c=new Cercle(this);
    }

    public void setVal(int val) {
        this.val = val;
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
   
}
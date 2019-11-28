package ABR_AVL;
import java.util.*;

public class Node {
   private int val;
   private Node FG=null,FD=null;

    public Node(int val) {
        this.val = val;
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
   
   
}
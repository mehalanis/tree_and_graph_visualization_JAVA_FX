package ABR_AVL;
import java.util.*;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/** @pdOid 67546721-5ccc-454c-88f2-aafdac22489a */
public class ABR extends Arbre {
    private Group group;

    public ABR(Group group) {
        this.group = group;
    }

    public void insertion(String s){
        insertion(Integer.parseInt(s)); 
    }
    @Override
    public void insertion(int n) {
        Node node =new Node(n);
        this.root=insertion(this.root,node);
    }
    private Node insertion(Node root,Node n) {
        if(root==null) return n;
        else{
            if(n.getVal()<root.getVal()){
                root.setFG(insertion(root.getFG(),n));
            }else{
                root.setFD(insertion(root.getFD(),n));
            }
            return root;
        }
    }

    @Override
    public boolean suppression(int val) {
        return true;
    }

}
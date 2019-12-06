package ABR_AVL;

import Formes.Cercle;
import static java.lang.Thread.sleep;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class ABR extends Arbre {

    public ABR(AnchorPane group) {
        super(group);
    }

    @Override
    public void insertion(int n) {
        this.root = insertion(this.root, new Node(n));
    }

    public void insertion(String s) {
        insertion(Integer.parseInt(s));
    }

    public Node insertion(Node root, Node n) {
        if (root == null) {
            return n;
        } else {
            if (n.getVal() < root.getVal()) {
                root.setFG(insertion(root.getFG(), n));
            } else {
                root.setFD(insertion(root.getFD(), n));
            }
            return root;
        }
    }

    public void insertionAnimation(String n) {
        insertionAnimation(Integer.parseInt(n));
    }

    public void insertionAnimation(int n) {
        Node node = new Node(n);
        int gap = this.depth(this.root);
        this.group.getChildren().add(node.getCircle(0, 0));
        gap = gap * gap * 10;
        new insertionAnimation(this, node, group).start();
    }

    @Override
    public boolean suppression(int val) {
        return true;
    }
}

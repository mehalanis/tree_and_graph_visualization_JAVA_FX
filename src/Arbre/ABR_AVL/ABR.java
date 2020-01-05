package Arbre.ABR_AVL;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        this.group.getChildren().add(node.getCircle(0, 0));
        new insertionAnimation(this, node, group).start();
    }

    public void suppression(String val, char r) {
        suppression( Integer.parseInt(val), r);
    }

    @Override
    public void suppression(int val, char r) {
        this.root = suppression(this.root, val, r);
    }

    public Node suppression(Node root, int val, char r) {
        if (root == null) {
            return null;
        } else {
            if (root.getVal() > val) {
                root.setFG(suppression(root.getFG(), val, r));
                return root;
            } else {
                if (root.getVal() < val) {
                    root.setFD(suppression(root.getFD(), val, r));
                    return root;
                } else {
                   /* Label x=new Label("X");
                    x.setTextFill(Color.RED);
                    x.setFont(new Font(76));
                    x.setLayoutX(root.getC().getLayoutX());
                    x.setLayoutY(root.getC().getLayoutY()-30);
                    group.getChildren().add(x);*/
                    return suppressionRacine(root, r);
                }
            }
        }
    }

    public Node suppressionRacine(Node root, char r) {
        if (root.getFG() == null) {
            if (root.getFD() == null) {
                return null;
            } else {
                return root.getFD();
            }
        } else {
            if (root.getFD() == null) {
                return root.getFG();
            } else {
                Node[] tab;
                if (r == 'S') {
                    System.out.println("Successeur");
                    tab = this.Successeur(root);
                    root.setVal(tab[1].getVal());
                    root.setFD(suppression(root.getFD(), tab[1].getVal(), r));
                }
                if(r=='P'){
                    System.out.println("Predecesseur");
                    tab = this.Predecesseur(root);
                    root.setVal(tab[1].getVal());
                    root.setFG(suppression(root.getFG(), tab[1].getVal(), r));
                }
                
                return root;
            }
        }
    }
}

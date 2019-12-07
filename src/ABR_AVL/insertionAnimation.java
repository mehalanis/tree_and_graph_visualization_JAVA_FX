/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABR_AVL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class insertionAnimation extends Thread {

    public Arbre abr;
    public Node n;
    public AnchorPane group;

    public insertionAnimation(Arbre abr, Node n, AnchorPane group) {
        this.abr = abr;
        this.n = n;
        this.group = group;
    }

    @Override
    public void run() {
        int gap = abr.depth(abr.root) ;
        if (abr instanceof ABR) {
            ABR r = (ABR) abr;
            abr.root = r.insertion(abr.root, n);
        } else {
            AVL r=(AVL)abr;
            abr.root=r.insertion(abr.root,(NodeAVL)n);
        }
        gap = gap * gap * 24;
        insertionAnimation(abr.root, n, (int)group.getWidth() / 2, 0, (int)group.getWidth()  / 2, 0, 0, gap);
       
    }

    private void insertionAnimation(Node root, Node n, int x, int y, int prevx, int prevy, int lev, int gap) {
        if (root == n) {
            TranslateTransition t1 = new TranslateTransition(Duration.millis(500), n.getC());
            t1.setToX(x);
            t1.setToY(y);
            t1.setOnFinished(new OnFinishedInsertionAnimation(group, n, abr));
            t1.play();
        } else {
            lev++;
            gap = (gap) / 2;
            root.setCircleColor(Color.YELLOW);
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            }
            root.setCircleColor(Color.BLACK);
            if (n.getVal() < root.getVal()) {
                if (root.getLFG() != null) {
                    root.setColorLFG(Color.YELLOW);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root.setColorLFG(Color.BLACK);
                }
                insertionAnimation(root.getFG(), n, x - gap, y + 50, x, y, lev, gap);
            } else {
                if (root.getLFD() != null) {
                    root.setColorLFD(Color.YELLOW);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root.setColorLFD(Color.BLACK);
                }
                insertionAnimation(root.getFD(), n, x + gap, y + 50, x, y, lev, gap);
            }
        }
    }

}

package ABR_AVL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Find extends Thread {

    private Node node;
    private Label label;
    private int val;

    public Find(Node node, Label label, int val) {
        this.node = node;
        this.label = label;
        this.val = val;
    }

    @Override
    public void run() {
        Node n = find(node, val);
        Runnable updater = new Runnable() {

            @Override
            public void run() {
                label.setText(((n != null) ? "found : " + val : "not found : " + val));
            }
        };
        Platform.runLater(updater);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        n.setCircleColor(Color.BLACK);
    }

    public Node find(Node t, int find) {
        if (t == null) {
            return null;
        }
        t.setCircleColor(Color.YELLOW);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        if (t.getVal() == find) {
            t.setCircleColor(Color.GREEN);
            return t;
        } else {
            t.setCircleColor(Color.BLACK);
            if (find < t.getVal()) {
                if (t.getLFG() != null) {
                    t.setColorLFG(Color.YELLOW);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    t.setColorLFG(Color.BLACK);
                }
                return find(t.getFG(), find);
            } else {
                if (t.getLFD() != null) {
                    t.setColorLFD(Color.YELLOW);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    t.setColorLFD(Color.BLACK);
                }
                return find(t.getFD(), find);
            }
        }
    }

}

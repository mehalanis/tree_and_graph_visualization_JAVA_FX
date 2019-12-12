/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMR;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RechercherAnimation extends Thread {

    public amr amr;
    private int val;
    public Label label;

    public RechercherAnimation(amr amr, int val, Label label) {
        this.amr = amr;
        this.val = val;
        this.label = label;
    }

    @Override
    public void run() {
        boolean result = recurech(this.amr.getRoot(), val);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(((result == true) ? "found : " + val : "not found : " + val));
            }

        });

    }

    public boolean recurech(Noeud Noeud, int data) {

        for (int i = 0; i <= Noeud.size - 1; i++) {
            Noeud.getRectangle(i).setStroke(Color.YELLOW);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            Noeud.getRectangle(i).setStroke(Color.BLACK);
            if (data == Noeud.data[i]) {
                Noeud.getRectangle(i).setStroke(Color.GREEN);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setText("found : " + val);
                    }

                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }

                Noeud.getRectangle(i).setStroke(Color.BLACK);
                return true;
            }
            if (data < Noeud.data[i]) {
                if (Noeud.children[i] == null) {
                    return false;
                } else {
                    return recurech(Noeud.children[i], data);
                }
            }
            if (data > Noeud.data[Noeud.size - 1]) {
                if (Noeud.children[Noeud.size] == null) {
                    return false;
                } else {
                    return recurech(Noeud.children[Noeud.size], data);
                }

            }
        }

        return false;
    }

}

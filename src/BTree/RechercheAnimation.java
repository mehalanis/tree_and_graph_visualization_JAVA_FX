/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTree;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RechercheAnimation extends Thread {

    public BTree BTree;
    private int val;
    public Label label;

    public RechercheAnimation(BTree BTree, int val, Label label) {
        this.BTree = BTree;
        this.val = val;
        this.label = label;
    }

    @Override
    public void run() {

        BTNode result = recurech(this.BTree.getRoot(), val);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(((!result.isNull()) ? "found : " + val : "not found : " + val));
            }

        });

    }

    public BTNode recurech(BTNode Noeud, int key) {

        if (Noeud.isNull()) {
            return this.BTree.getNullNode();
        }
        BTNode currentNode = this.BTree.getRoot();

        while (!currentNode.equals(BTree.getNullNode())) {

            int i = 0;
            while (i < currentNode.getSize()) {

                currentNode.getRectangle(i).setStroke(Color.YELLOW);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RechercheAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Integer.parseInt(currentNode.getKey(i).toString()) == key) {
                    currentNode.getRectangle(i).setStroke(Color.GREEN);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            label.setText("found : " + val);
                        }

                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RechercheAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currentNode.getRectangle(i).setStroke(Color.BLACK);
                    int index = i;
                    return currentNode;
                } else {
                    currentNode.getRectangle(i).setStroke(Color.BLACK);
                    if (Integer.parseInt(currentNode.getKey(i).toString()) > val) {
                        currentNode = currentNode.getChild(i);
                        i = 0;
                    } else {
                        i++;
                    }
                }

            }
            if (!currentNode.isNull()) {
                currentNode = currentNode.getChild(currentNode.getSize());
            }
        }
        return this.BTree.getNullNode();

    }
}

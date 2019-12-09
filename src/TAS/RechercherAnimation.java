/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author root
 */
public class RechercherAnimation extends Thread {

    public TASmax tas;
    public int val;

    public RechercherAnimation(TASmax tas, int val) {
        this.tas = tas;
        this.val = val;
    }

    @Override
    public void run() {
        Rechercher(val);
        tas.Afficher();

    }

    public void Rechercher(int valeur) {
        int pos = 0, removepos, trouve = 0;

        // Création d'une file
        Queue<Integer> q = new LinkedList<>();
        if (tas.taille != 0) {
            pos = 1;
            q.add(pos);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            tas.Tas[1].getC().setStroke(Color.YELLOW);
            while (q.peek() != null) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RechercherAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = q.remove();
                if (tas.Tas[pos].getVal() == valeur) {
                    System.out.println("la valeur \"" + (Math.abs(valeur)) + "\" éxiste à la position \"" + pos + "\" ");
                    while (q.peek() != null) {
                        removepos = q.remove();
                        tas.Tas[removepos].getC().setStroke(Color.BLACK);
                    }
                    tas.Tas[pos].getC().setStroke(Color.GREEN);
                    break;
                } else {
                    tas.Tas[pos].getC().setStroke(Color.BLACK);
                }
                if (tas.filsGauche(pos) <= tas.taille) {
                    if (tas.Tas[tas.filsGauche(pos)].getVal() >= valeur) {
                        tas.Tas[tas.filsGauche(pos)].getC().setStroke(Color.YELLOW);
                        q.add(tas.filsGauche(pos));
                    }
                }
                if (tas.filsDroit(pos) <= tas.taille) {
                    if (tas.Tas[tas.filsDroit(pos)].getVal() >= valeur) {
                        tas.Tas[tas.filsDroit(pos)].getC().setStroke(Color.YELLOW);
                        q.add(tas.filsDroit(pos));
                    }
                }
            }

        }
    }
}

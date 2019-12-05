/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *
 * @author meder
 */

public class TASmin extends TASmax {

    public TASmin(int cpct) {
        super(cpct);
    }

    public int inserer(int element) {
      int pos=super.inserer(-element);
      Node n=Tas[pos];
      n.getC().setVal(element);
      return pos;
   }
     public int Rechercher(int valeur) {
         return super.Rechercher(-valeur);
    }

    @Override
    public Node Supprimer(int val){
        super.Supprimer(-val);

        return null;
        }
    @Override
     public void Afficher(){
      for (int i = 1; i <= taille; i++){
            System.out.print(-Tas[i].getVal() + " ");
      }
      System.out.println();
     }
}

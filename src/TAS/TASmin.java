/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 *
 * @author meder
 */

public class TASmin extends TASmax {

    public TASmin(int cpct,AnchorPane group) {
        super(cpct,group);
        Tas[0] = new Node(Integer.MIN_VALUE);
    }

    public void inserer(int element) {
      super.inserer(-element);
     // Node n=Tas[this.Rechercher(element)];
     // n.getC().setVal(element);
   }
     public int Rechercher(int valeur) {
         return super.Rechercher(-valeur);
    }

    @Override
    public Node Supprimer(int val){
        return super.Supprimer(-val);
    }
  /*  @Override
     public void Afficher(){
      for (int i = 1; i <= taille; i++){
            System.out.print(-Tas[i].getVal() + " ");
      }
      System.out.println();
     }*/
}

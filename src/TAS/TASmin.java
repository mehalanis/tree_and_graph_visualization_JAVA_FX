/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 *
 * @author meder
 */

public class TASmin extends TASmax {

    public TASmin(int cpct,AnchorPane group,Label result) {
        super(cpct,group,result);
    }

    public void inserer(int element) {
      super.inserer(-element);
      
   }
     public int Rechercher(int valeur) {
         return super.Rechercher(-valeur);
    }

    @Override
    public void Supprimer(int val){
        new SupprimerAnimation(this,-val).start();
        // super.Supprimer(val);
    }
  /*  @Override
     public void Afficher(){
      for (int i = 1; i <= taille; i++){
            System.out.print(-Tas[i].getVal() + " ");
      }
      System.out.println();
     }*/
}

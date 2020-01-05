package Arbre.TAS;

import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

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
    public void RechercherAnimation(int val){
        new  RechercherAnimation(this,this.result,-val).start();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasmax;

/**
 *
 * @author meder
*/

import java.util.*;

public class TASmax {
   private int[] Tas;//le Tas
   private int taille;//La taille Du Tas
   private int cpct;//capacité maximum du Tas

   public TASmax(int cpct) {
     this.cpct=cpct;
     this.taille=0;
     Tas=new int[this.cpct + 1];
     Tas[0]=Integer.MAX_VALUE;
     //l'indice 0 ne peut pas étre utilisée dans les prochaine fonctions
   }

   private int parent(int pos)
   {
     return pos / 2;
   }

   private int filsGauche(int pos)
   {
     return (2 * pos);
   }

   private int filsDroit(int pos)
   {
     return (2 * pos) + 1;
   }

   private boolean feuille(int pos)
   {
     return pos >= (taille / 2) && pos <= taille;
   }

   public void Permuter(int pos1, int pos2) {
     // TODO: implement
     int tmp;
     tmp = Tas[pos1];
     Tas[pos1] = Tas[pos2];
     Tas[pos2] = tmp;
   }

   private void maxTas(int pos)
   {
     if (feuille(pos))
       return;

     if (Tas[pos] < Tas[filsGauche(pos)] ||
       Tas[pos] < Tas[filsDroit(pos)]) {

       if (Tas[filsGauche(pos)] > Tas[filsDroit(pos)]) {
         Permuter(pos, filsGauche(pos));
         maxTas(filsGauche(pos));
       }
       else {
         Permuter(pos, filsDroit(pos));
         maxTas(filsDroit(pos));
       }
     }
   }

   public void inserer(int element) {
      // TODO: implement
      Tas[++taille] = element;
      // Maintenance des lois du Tas
      int current = taille;
      while (Tas[current] > Tas[parent(current)]) {
        Permuter(current, parent(current));
        current = parent(current);
      }
   }
   public int Rechercher(int valeur) {
      // TODO: implement
      int pos = 0;
      int found= 0;

      for (int i = 1; i <= taille; i++){
        if (Tas[i]==valeur) {
          pos = i;
          found=1;
        }
      }

      if(found == 1){
          System.out.println("la valeur \""+valeur+ "\" éxiste à la position \""+pos+"\" ");
          return pos;
      }
      System.out.println("la valeur \""+valeur+ "\" n\'éxiste pas.");
      return 0;
    }

   /** *  @param val
     * @return

    * @pdOid 87ad828d-4b45-488d-81d4-8fa55ee77fbc */
    public int Supprimer(int val){
        int pos = 0;
        pos = Rechercher(val);
       /* int pos = 0;
        int found= 0;
              for (int i = 1; i <= taille; i++){
               if (Tas[i]==val) {
                pos = i;
                found=1;
               }
               }*/
              if(pos != 0){

                  int popped = Tas[pos];
                  Tas[pos] = Tas[taille--];
                  maxTas(pos);
                  return popped;
              }
              return 0;
          }

/*
   public boolean Supprimer(int val) {
      // TODO: implement
      int valSup = val
      return false;
   }*/

   /*
   public void PermuterPereFilsGauche(int posPere) {
     // TODO: implement
     Permuter(posPere, filsGauche(posPere));
   }

   public void PermuterPereFilsDroit(int posPere) {
      // TODO: implement
      Permuter(posPere, filsDroit(posPere));
   }

   public void PermuterFilsGauchePere(int posFilsGauche) {
      // TODO: implement
      Permuter(posFilsGauche, parent(posFilsGauche));
   }

   public void PermuterFilsDroitPere(int posFilsDroit) {
      // TODO: implement
      Permuter(posFilsDroit, parent(posFilsDroit));
   }
   */

   public void Afficher(){
      for (int i = 1; i <= taille; i++){
            System.out.print(Tas[i] + " ");
      }
      System.out.println();
   }

    public static void main(String[] args) {
        // TODO code application logic here

   int a;

                 System.out.println("Notre TAS : ");
 		TASmax Tas = new TASmax(15);
             /*    Scanner sc = new Scanner(System.in);
                 a = sc.nextInt();
 		Tas.inserer(a);
                 Tas.print();*/
 		Tas.inserer(3);
 		Tas.inserer(17);
 		Tas.inserer(10);
 		Tas.inserer(84);
 		Tas.inserer(19);
 		Tas.inserer(6);
 		Tas.inserer(22);
 		Tas.inserer(9);
                 Tas.inserer(11);

 		//Tas.print();
 		//System.out.println("The max val is " + Tas.extractMax());
                 Tas.Afficher();
                 Tas.Supprimer(6);
                 Tas.Afficher();
                 Tas.Supprimer(19);
                 Tas.Afficher();
                 Tas.Supprimer(194);
                 Tas.Supprimer(11);
                 Tas.Afficher();

    }

}

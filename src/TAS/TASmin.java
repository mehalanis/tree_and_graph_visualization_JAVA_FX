/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TAS;

import java.util.Scanner;

/**
 *
 * @author meder
 */
public class TASmin extends TASmax {



    public TASmin(int cpct) {
        super(cpct);
    }

    public void inserer(int element) {
      // TODO: implement
      Tas[++taille] = -element;
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
          System.out.println("la valeur \""+(-valeur)+ "\" éxiste à la position \""+pos+"\" ");
          return pos;
      }
      System.out.println("la valeur \""+(-valeur)+ "\" n\'éxiste pas.");
      return 0;
    }

   /** *  @param val
     * @return

    * @pdOid 87ad828d-4b45-488d-81d4-8fa55ee77fbc */
    @Override
    public int Supprimer(int val){
        int pos = 0;
        pos = Rechercher(-val);
            if(pos != 0){

                int popped = Tas[pos];
                Tas[pos] = Tas[taille--];
                maxTas(pos);
                return popped;
            }
        return 0;
        }
    @Override
     public void Afficher(){
      for (int i = 1; i <= taille; i++){
            System.out.print(-Tas[i] + " ");
      }
      System.out.println();
   }
    public static void main(String[] args) {
        // TODO code application logic here

                int a;

                System.out.println("Notre TAS : ");

                TASmin Tas = new TASmin(15);

                Scanner sc = new Scanner(System.in);

                a = sc.nextInt();

                Tas.inserer(a);
 		Tas.inserer(3);
 		Tas.inserer(17);
 		Tas.inserer(10);
 		Tas.inserer(84);
 		Tas.inserer(19);
 		Tas.inserer(6);
 		Tas.inserer(22);
 		Tas.inserer(9);
                Tas.inserer(11);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_arbre;

/**
 *
 * @author Abdou
 */
import Formes.rectangle;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

/**
 *
 * @author Abdou
 */
public class B_Arbre<E extends Comparable> {

    public B_Arbre<E> pere = null;
    public ArrayList<Integer> node = new ArrayList<Integer>();
    public ArrayList<B_Arbre<E>> fils = new ArrayList<B_Arbre<E>>();
    public int Ordre = 3;

    // private boolean estRacine=false;
    public void setOrdre(int Ordre) {
        if (Ordre % 2 == 1 && Ordre > 2) {
            this.Ordre = Ordre;
        } else {
            System.out.println("L'ordre doit être Impaire");
        }
    }


    public B_Arbre(int val) {
        this.node.add(val);
    }

    public B_Arbre() {
    }

    public void sort() {
        Collections.sort(this.node);

    }

    public boolean estFeuille() {
        return this.fils.isEmpty();
    }

    public void setNode(ArrayList<Integer> newNode) {
        this.node = newNode;
    }

    public void setPere(B_Arbre<E> pere) {
        this.pere = pere;

        // this.pere.fils.add(this);
    }

    public void setFils(B_Arbre<E> fils, int index) {
        this.fils.add(index, fils);

    }

    public B_Arbre<E> getFils(int indexFils) {
        return this.fils.get(indexFils);
    }

    public B_Arbre<E> getPere() {
        return this.pere;
    }

    public boolean isFull() {
        return this.node.size() == Ordre - 1;
    }

    public B_Arbre getRacine() {
        if (this.pere != null) {
            return this.pere.getRacine();
        } else {
            return this;
        }
    }

    public B_Arbre getPos(int val) // chercher la meilleur  pos d'insertion 
    //ps: on doit commencer par la racine 
    {

        for (int i = 0; i < this.node.size(); i++) {
            if (val < this.node.get(i)) {
                if (this.estFeuille()) {
                    return this;
                } else {

                    return this.getFils(i).getPos(val);

                }

            } else {
                if (i == this.node.size() - 1) // last val in node 
                {//System.err.println("hello " +i);

                    if (this.estFeuille()) {
                        return this;
                    } else {

                        try {
                            return this.getFils(i + 1).getPos(val);
                        } catch (Exception e) {
                        }

                    }
                }
            }

        }

        return null;
    }

    public B_Arbre Recherche(int val) // chercher la meilleur  pos d'insertion 
    //ps: on doit commencer par la racine 
    {

        for (int i = 0; i < this.node.size(); i++) {
            if (val < this.node.get(i)) {

                return this.getFils(i).Recherche(val);

            } else {
                if (val == this.node.get(i)) {
                    return this;
                } else {
                    if (i == this.node.size() - 1) // last val in node 
                    {//System.err.println("hello " +i);

                        return this.getFils(i + 1).Recherche(val);

                    }
                }
            }

        }

        return null;
    }

    /*  public void setFatherForChildNodes()
     {
         for (B_Arbre Child : this.fils)
             Child.setPere(this);
     }*/
    public void AfficherT() // depuis la racine  on chercherche depuis la racine
    {
        int level = 0;
        ArrayList<Pair> Queue = new ArrayList<Pair>();
        Pair monCouple = new Pair(this, level);
        level++;
        Queue.add(monCouple);
        while (!Queue.isEmpty()) {

            Queue.get(0).AfficherPair();

            if (!Queue.get(0).getMonNoued().fils.isEmpty()) {
                ArrayList<B_Arbre> fils2 = Queue.get(0).getMonNoued().fils;
                int nbr = Queue.get(0).getMonNoued().node.size();
                //System.out.println("fils queuesize "+fils2.size());
                for (int i = 0; i <= nbr; i++) {
                    try {
                        Queue.add(new Pair(fils2.get(i), Queue.get(0).getLevel() + 1));
                    } catch (Exception e) {
                    }
                }

                /*    for(B_Arbre MonFils : fils2)
                {
                    Queue.add(new Pair(MonFils,level));
                }*/
            }
            Queue.remove(0);

        }

    }

   /* public void Afficher() {
        arbre.getChildren().clear();
        int gap = 3;
        gap = gap * this.Ordre * 40;
        this.Afficher(this.getRacine(), null, 1200 / 2, 0, 1200 / 2, 0, 0, gap);

    }

    public void Afficher(B_Arbre t, B_Arbre root, int x, int y, int prevx, int prevy, int lev, int gap) {

        if (t == null) {
            return;
        }
        HBox hbox = new HBox();
        rectangle r;
        for (int i = 0; i < t.node.size(); i++) {
            int k = (int) t.node.get(i);
            r = new rectangle(k);
            hbox.getChildren().add(r);

        }
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        arbre.getChildren().add(hbox);
        if ((++lev) != 1) {
            //  Line line = new Line(prevx, prevy + 36, x + (35 * t.node.size()) / 2, y);
            //  line.setStrokeWidth(2);
            //   arbre.getChildren().add(line);
            gap = (gap) / this.Ordre;
        }
        if (t.estFeuille()) {
            return;
        }
        int xpere = x;

        for (int i = 0; i < t.fils.size(); i++) {

            Afficher(t.getFils(i), t, x - gap, y + 58, xpere, y, lev, gap);
            x += gap;

            xpere += 35;
        }
    }*/
    public void insert(int val) // on doit l 'utiliser en feuille
    {
        // System.out.println("it s me");
        //this.afficher();
        //System.out.println("nbr de mes fils"+this.fils.size());

        if (this.node.size() < (Ordre - 1)) {
            this.node.add(val);
            sort();

            // int index=node.indexOf(val);
        } else {

            this.node.add(val);
            System.out.println(val);
            sort();
            this.afficher();
            int k = this.node.size() / 2;
            int ValMil = this.node.get(k);
            System.out.println("val mil" + ValMil);

            try {
                System.out.println("filssss3");
                this.getFils(0);
                this.pere.getFils(1);
            } catch (Exception e) {
            }

            if (this.pere != null) {
                int indexDeFilsDansPere = this.pere.fils.indexOf(this);
                System.out.println("indx pere this" + indexDeFilsDansPere);

                pere.setOrdre(this.Ordre);
                ArrayList<Integer> ValsMin = new ArrayList<Integer>();
                ArrayList<Integer> ValsMax = new ArrayList<Integer>();
                int length = this.node.size();

                for (int i = length - 1; i >= 0; i--) //chercher les val inf à la valDeMil et les vals sup 
                {
                    if (this.node.get(i) > ValMil) {

                        ValsMax.add(this.node.get(i));
                        this.node.remove(i);
                        System.out.println("cherche val max ");
                        System.out.println("vl max" + ValsMax.get(0));

                    }

                }
                this.sort();
                if (!this.pere.isFull()) //si le pere n'est pas full on fait permutation de fils on on ajout les valeurs max dans un nouveau fils(indexDansPere+1)
                {
                    //  try
                    //{
                    if (indexDeFilsDansPere + 1 >= this.pere.fils.size())// dernier fils dans un pere non complet 
                    {
                        System.out.println("rnai dkhlt les hommes");
                        try {
                            this.pere.fils.remove(indexDeFilsDansPere + 1);
                            this.pere.setFils(new B_Arbre<E>(), indexDeFilsDansPere + 1);
                            this.pere.getFils(indexDeFilsDansPere + 1).setNode(ValsMax);
                            this.pere.getFils(indexDeFilsDansPere + 1).setPere(this.pere);
                        } catch (Exception e) {
                        }
                    } else {
                        System.out.println("rnai dkhlt lel else");
                        int cc = this.pere.fils.size() - 1;
                        System.out.println(cc);
                        while (cc > indexDeFilsDansPere) {
                            this.pere.setFils(this.pere.getFils(cc), cc + 1);
                            //this.pere.fils.remove(cc);
                            System.out.println("7titha");
                            // this.pere.getFils(cc+1).setPere(this.pere);
                            //this.pere.getFils(cc+1).afficher();
                            //this.pere.getFils(cc+1).pere.afficher();
                            cc--;
                        }//permutation dans pere fils[cc+1]=fils[cc]
                        //this.pere.getFils(cc+1).afficher();
                        //this.pere.getFils(cc+2).afficher();
                        //this.pere.getFils(cc+1).pere.afficher();

                        this.pere.fils.remove(indexDeFilsDansPere + 1);
                        this.pere.setFils(new B_Arbre<E>(), indexDeFilsDansPere + 1);

                        this.pere.getFils(indexDeFilsDansPere + 1).setNode(ValsMax);
                        this.pere.getFils(indexDeFilsDansPere + 1).setPere(this.pere);
                        try {
                            int j = 0;
                            for (int i = this.node.size(); i <= Ordre; i++) // deplacer les fils sup à le noued suivant
                            {
                                this.pere.getFils(indexDeFilsDansPere + 1).setFils(this.getFils(i), j);
                                j++;
                            }
                        } catch (Exception e) {
                        }
                        // this.pere.getFils(cc+2).afficher();

                    }
                    //}catch(Exception e){}
                    pere.node.add(ValMil);
                    pere.sort();
                } else // le pere est complet -full-
                {
                    System.out.println("rani hna ");
                    this.pere.afficher();
                    // this.pere.getFils(0).afficher();
                    //this.pere.getFils(1).afficher();
                    //this.pere.getFils(2).afficher();
                    //this.pere.getFils(3).afficher();
                    //this.pere.getFils(4).afficher();

                    //-----------
                    System.out.println("Hna size" + this.pere.fils.size());
                    int cc = this.pere.node.size();
                    System.out.println(cc);
                    while (cc > indexDeFilsDansPere) {
                        try {
                            this.pere.setFils(this.pere.getFils(cc), cc + 1);
                            this.pere.getFils(cc + 1).setPere(this.pere);
                            //this.pere.fils.remove(cc);
                            System.out.println("7titha");
                            System.out.println("cc" + cc + "  cc+1  " + (cc + 1));
                            this.pere.getFils(cc + 1).setPere(this.pere);
                            this.pere.getFils(cc + 1).afficher();
                            this.pere.getFils(cc + 1).pere.afficher();
                            cc--;
                        } catch (Exception e) {
                        }
                    }
                    /* this.pere.fils.remove(indexDeFilsDansPere+1);
                              this.pere.setFils(new B_Arbre<E>(),indexDeFilsDansPere+1 );
                               this.pere.getFils(indexDeFilsDansPere+1).setNode(ValsMax);
                               this.pere.getFils(indexDeFilsDansPere+1).setPere(this.pere);*/
                    System.out.println("indx pere" + indexDeFilsDansPere);

                    this.pere.setFils(new B_Arbre<E>(), indexDeFilsDansPere + 1);
                    this.pere.getFils(indexDeFilsDansPere + 1).setNode(ValsMax);
                    this.pere.getFils(indexDeFilsDansPere + 1).afficher();
                    //   System.out.println("Fils"+(indexDeFilsDansPere+1));

                    //this.pere.afficher();
                    //this.pere.getFils(indexDeFilsDansPere).afficher();
                    //this.pere.getFils(indexDeFilsDansPere+1).setPere(this.pere);
                    //this.pere.getFils(indexDeFilsDansPere+1).afficher();
                    //System.out.println("pr rc");
                    this.pere.insert(ValMil);

                    //-----------------------------
                }

                // pere.insert(ValMil);
            } else // pere is null => creer une nouvelle racine qui a deux fils ^^ 
            {
                //  B_Arbre FilsG Le filsG sera notre noued et contient les vals min à val de mil
                //System.out.println("hna pere null"+this.fils.size());
                //this.afficher();

                //System.out.println(this.fils.isEmpty());
                /*try{//this.fils.get(0).afficher();
                    this.getFils(3).afficher();
                    //this.getFils(2).afficher();
                    //this.getFils(3).afficher();
                   }catch(Exception e){}*/
                B_Arbre FilsD = new B_Arbre(); // FilsD contient les vals max de vals de mil
                // System.err.println("size"+this.node.size());
                int TmpLength = this.node.size();
                //System.out.println("yaw tem legnth"+TmpLength+" nbrdFils"+this.fils.size());

                int indexvalDeMilSupp = -1;
                try {
                    for (int i = TmpLength - 1; i >= 0; i--) {  //this.afficher();
                        //System.err.println(this.node.get(2));

                        if (i == TmpLength - 1) {
                            try {

                                //          System.out.println("tmp ilfs :"+this.fils.size());
                                //        System.out.println("done "+TmpLength);
                                int IndexDern = Ordre / 2;
                                //          System.out.println("indx"+IndexDern);
                                //        this.fils.get(TmpLength).afficher();
                                FilsD.fils.add(this.fils.get(TmpLength));
                                FilsD.fils.get(0);
                                //   System.out.println("Doneeessssss");

                                FilsD.getFils(0).setPere(FilsD);

                                //   System.out.println("Doneee");
                            } catch (Exception e) {
                            }
                        }
                        if (this.node.get(i) > ValMil) {
                            FilsD.node.add(this.node.get(i));
                            //System.out.println(this.node.get(i));

                            this.node.remove(i);
                            try {
                                if (this.fils.get(i) != null)//cas de racine : la racine n a pas de fils 
                                {
                                    FilsD.fils.add(this.fils.get(i));
                                    FilsD.getFils(i).setPere(FilsD);
                                    this.fils.remove(i);
                                }

                            } catch (IndexOutOfBoundsException e) {
                            }
                        } else {
                            if (this.node.get(i) == ValMil) {
                                indexvalDeMilSupp = i;

                            }
                            FilsD.getFils(i).setPere(FilsD);

                        }

                    }
                } catch (Exception e) {
                }

                /*  try
                   {
                    
                    System.out.println("tmp ilfs :"+this.fils.size());
                    
                    System.out.println("done "+TmpLength);
                    FilsD.fils.add(this.fils.get(TmpLength));
                       System.out.println("Doneee");
                    
                        
                    
                   }catch(Exception e)
                   {}*/
                // System.out.println("hna pere jdide");
                //this.afficher();
                this.setPere(new B_Arbre<E>(ValMil));
                try {
                    FilsD.fils.add(FilsD.fils.get(0));
                    FilsD.fils.remove(0);
                } catch (Exception e) {
                }
                this.pere.setFils(this, 0);
                this.pere.setFils(FilsD, 1);
                FilsD.setPere(this.pere);

                //this.pere.setFils(new B_Arbre(), 1);
            }

            this.node.remove(k);
        }
        sort();
    }

    public B_Arbre Insertion(int val) {
        B_Arbre monArbre = this.getRacine();
        monArbre = monArbre.getPos(val);
        monArbre.insert(val);
        return monArbre;

    }

    public void afficher() {

        for (Integer vals : this.node) {
            System.out.print(vals + " , ");
        }
        System.out.println("\n");
    }

    /*  public static void main(String[] args) {
        B_Arbre racine4 = new B_Arbre(25);
        racine4.setOrdre(3); // pour les ordre pair on prends tjr ordre 3 
        racine4 = racine4.Insertion(60);
        racine4 = racine4.Insertion(35);
        racine4 = racine4.Insertion(10);
        racine4 = racine4.Insertion(5);
        racine4 = racine4.Insertion(18);
        racine4 = racine4.Insertion(22);
        racine4 = racine4.Insertion(44);
        racine4 = racine4.Insertion(13);
        racine4 = racine4.getRacine();
        racine4.AfficherT();

    }*/
}

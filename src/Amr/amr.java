package AMR;

import Formes.rectangle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class amr {

    // pour le test 
    private Noeud root;		//la racine de notre amr 
    public int t = 3;		//l'ordre de l'amr a recupur� de notre interface
    private boolean sucesseur = false;
    private boolean predecesseur = true; // pour la supression a recup de l'interface
    public Group arbre;
    
    public amr(int t, Group arbre) {
        this.t = t;
        this.arbre = arbre;
    }
    
    public Noeud getRoot() {
        return root;
    }
    public void SwitchSucPred(char c){
        if(c=='S'){
            sucesseur=true;
            predecesseur=false;
        }else{
            sucesseur=false;
            predecesseur=true;
        }
    }
    boolean isFull(Noeud noeud) {
        return noeud.size == t - 1;
    }

    boolean isNull(Noeud noeud) {
        return noeud.size == 0;
    }

    // insertion 
    public void insertData(Noeud node, int data) { // en param�tre on a le noeud et la valeur a ajout�
        // creation de fonction de l'ajout dans le m�me noeud
        int index = node.size;
        for (int i = node.size - 1; i > -1; i--) { // on lis les valeur par odres d�croissant
            if (data <= node.data[i]) {
                node.data[i + 1] = node.data[i];
                index = i;
            } else {
                break;
            }
        }
        node.data[index] = data;
        node.size++;
    }

    public void recu(Noeud Noeud, int data) {
        // fonction recursive pour l'ajout dans d'autre niveau)
        for (int i = 0; i <= Noeud.size - 1; i++) {
            if (data < Noeud.data[i]) {
                if (Noeud.leaf == true && Noeud.size < t - 1) {
                    insertData(Noeud, data);
                    return;
                } //feuille non pleine j'insert
                if (Noeud.children[i] == null && Noeud.size >= t - 1) {
                    Noeud node = new Noeud(t);
                    node.parent.setPapa(Noeud);
                    node.parent.setPos(i);
                    insertData(node, data);
                    Noeud.children[i] = node;
                    Noeud.leaf = false;
                    return;
                } // feuille pleine, je cr�e un nvx noeud 
                if (Noeud.leaf == false) {
                    recu(Noeud.children[i], data);
                } // je refais le traitement pour les pr�decesseur de root.data[i]
                return;
            }

        }
        int i = Noeud.size - 1;
        if (data >= Noeud.data[i]) {
            if (Noeud.leaf == true && Noeud.size < t - 1) {
                insertData(Noeud, data);
                return;
            } //feuille non pleine j'insert
            if (Noeud.children[i + 1] == null && Noeud.size >= t - 1) {
                Noeud node = new Noeud(t);
                node.parent.setPapa(Noeud);
                node.parent.setPos(i + 1);
                insertData(node, data);
                Noeud.children[i + 1] = node;
                Noeud.leaf = false;
                return;
            } // feuille pleine, je cr�e un nvx noeud 
            if (Noeud.leaf == false) {
                recu(Noeud.children[i + 1], data);
            } // je refais le traitement pour les successeur de root.data[i]
            return;
        }

    }

    public void insertion(int data) {

        if (root == null) { //amr vide
            Noeud node = new Noeud(t);
            node.parent = null;
            insertData(node, data);
            root = node;
            return;
        }

        recu(root, data); //amr non vide, recherche et insertion 

    } //fin insertion 

    //Recherche ... 
    public boolean recurech(Noeud Noeud, int data) {
        for (int i = 0; i <= Noeud.size - 1; i++) {
            if (data == Noeud.data[i]) {
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

    public boolean rech(int data) {
        return recurech(root, data);
    }

    public void RechercherAnimation(int val,Label l) {
        new RechercherAnimation(this, val, l).start();
    }

    // supression 	
    public Noeud supdnoeud(Noeud noeud, int pos) {
        int[] datas = new int[t - 1];
        Noeud[] childrens = new Noeud[t];
        for (int i = 0; i <= noeud.size - 1; i++) {
            if (i < pos) {
                datas[i] = noeud.data[i];
            }
            if (i > pos) {
                datas[i - 1] = noeud.data[i];
            }
        }

        if (noeud.children[pos + 1] != null) {
            for (int i = 0; i <= noeud.size; i++) {
                if (i < pos) {
                    childrens[i] = noeud.children[i];
                }
                if (i > pos) {
                    childrens[i - 1] = noeud.children[i];
                }

            }
            noeud.children[pos + 1].parent.pos = pos;
        }

        if (noeud.children[pos + 1] == null) {
            for (int i = 0; i <= noeud.size; i++) {
                if (i <= pos) {
                    childrens[i] = noeud.children[i];
                }
                if (i > pos + 1) {
                    childrens[i - 1] = noeud.children[i];
                }

            }
        }

        noeud.data = datas;
        noeud.children = childrens;
        noeud.size = noeud.size - 1;
        return noeud;
    }

    public void recusup(Noeud Noeud, int data) {
        for (int i = 0; i <= Noeud.size - 1; i++) {
            if (data == Noeud.data[i]) { // if found 

                if (Noeud.children[i] == null && Noeud.children[i + 1] == null) { // s'il n'as pas de fils
                    if (Noeud.size == 1) {
                        int poss = Noeud.parent.getPos();
                        Noeud parents = Noeud.parent.getPapa();
                        parents.children[poss] = null;
                        return;
                    }
                    Noeud temp = supdnoeud(Noeud, i);
                    Noeud.data = temp.data;
                    Noeud.children = temp.children;
                    Noeud.parent = temp.parent;
                    Noeud.size = temp.size;
                    Noeud.leaf = temp.leaf;
                    return;
                } else if (Noeud.children[i] != null && Noeud.children[i + 1] != null) { // il a deux fils a traiter
                    if (sucesseur == true) {
                        Noeud.data[i] = Noeud.children[i + 1].data[0];
                        recusup(Noeud.children[i + 1], Noeud.children[i + 1].data[0]);
                        return;
                    }
                    if (predecesseur == true) {
                        Noeud.data[i] = Noeud.children[i].data[Noeud.children[i].size - 1];
                        recusup(Noeud.children[i], Noeud.children[i].data[Noeud.children[i].size - 1]);
                        return;
                    }

                } else { // il a un seul fils
                    if (Noeud.size == 1) {
                        if (Noeud.children[i] == null) {
                            Noeud.data = Noeud.children[i + 1].data;
                            Noeud.children = Noeud.children[i + 1].children;
                            Noeud.size = Noeud.children[i + 1].size;
                            Noeud.leaf = Noeud.children[i + 1].leaf;
                            return;
                        }

                        if (Noeud.children[i + 1] == null) {
                            Noeud.data = Noeud.children[i].data;
                            Noeud.children = Noeud.children[i].children;
                            Noeud.size = Noeud.children[i].size;
                            Noeud.leaf = Noeud.children[i].leaf;
                            return;
                        }
                    }
                    Noeud temp = supdnoeud(Noeud, i);
                    Noeud.data = temp.data;
                    Noeud.children = temp.children;
                    Noeud.parent = temp.parent;
                    Noeud.size = temp.size;
                    Noeud.leaf = temp.leaf;;
                    return;
                }
            }

            // not in this lvl need to moove 
            if (data < Noeud.data[i]) {
                recusup(Noeud.children[i], data);
                return;
            }

            if (data > Noeud.data[Noeud.size - 1]) {
                recusup(Noeud.children[Noeud.size], data);
                return;
            }

        }// end for

    }

    public void suppression(int data) {
        //rech
        if (rech(data) == false) {
            return;
        }
        recusup(root, data);

    }

    //Fonction pour affichage sur console
    public void display(Noeud node, int level) {

        if (node == null) {
            return;
        }

        System.out.print("Level : " + level + " " + "Data : ");
        for (int i = 0; i < node.size; i++) {
            System.out.print(node.data[i] + " ");
        }

        System.out.println();

        if (node.leaf) {
            return;
        }
        for (int i = 0; i < node.size + 1; i++) {
            display(node.children[i], level + 1);
        }
        System.out.println();
    }

    public static int recupro(Noeud noeud, int profendeur) {
        int tpro = 0;
        int tprofendeur = profendeur;
        if (noeud.leaf == true) {
            return tprofendeur;
        }
        for (int i = 0; i < noeud.size + 1; i++) {
            if (noeud.children[i] != null) {
                tpro = recupro(noeud.children[i], profendeur + 1);
                if (tprofendeur < tpro) {
                    tprofendeur = tpro;
                }
            }
        }

        return tprofendeur;
    }

    public static int profendeur(Noeud noeud) {
        int pro;
        if (noeud != null) {
            pro = 0;
        } else {
            return pro = -1;
        }
        if (noeud.leaf == true) {
            return pro;
        }
        pro = pro + 1;
        int ttpro = 0;
        for (int i = 0; i < noeud.size + 1; i++) {
            if (noeud.children[i] != null) {
                int tpro = recupro(noeud.children[i], pro);
                if (ttpro < tpro) {
                    ttpro = tpro;
                }
            }
        }
        return ttpro;
    }

    public void Afficher() {
        arbre.getChildren().clear();
        int gap = profendeur(root);
        gap = gap * this.t * 40;
        this.Afficher(this.getRoot(), null, 1200 / 2, 0, 1200 / 2, 0, 0, gap);

    }

    public void Afficher(Noeud t, Noeud root, int x, int y, int prevx, int prevy, int lev, int gap) {

        if (t == null) {
            return;
        }
        HBox hbox = new HBox();
        rectangle r;
        for (int i = 0; i < t.size; i++) {
            t.setRectangle(new rectangle(t.data[i]), i);
            hbox.getChildren().add(t.getRectangle(i).getRectangle(0, 0));

        }
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        arbre.getChildren().add(hbox);
        if ((++lev) != 1) {
            Line line = new Line(prevx, prevy + 36, x + (35*t.size)/2, y);
            line.setStrokeWidth(2);
            arbre.getChildren().add(line);
            gap = (gap) / this.t;
        }
        if (t.leaf) {
            return;
        }
        int xpere = x;

        for (int i = 0; i < t.size + 1; i++) {

            Afficher(t.children[i], t, x - gap, y + 58, xpere, y, lev, gap);
            if (this.t % 2 == 0) {
                x += gap - (this.t / 2) * 15;
            } else {
                x += gap;
            }
            xpere += 35;
        }
    }
    /*
    //HOPE IT WORK... AMR ORDRE 5 DE NOTRE SERIE D'EXO
    public static void main(String[] args) {
        insertion(25);
        insertion(60);
        insertion(35);
        insertion(10);
        insertion(5);
        insertion(20);
        insertion(65);
        insertion(45);
        insertion(70);
        insertion(40);
        insertion(50);
        insertion(55);
        insertion(30);
        insertion(15);
        insertion(22);
        insertion(62);
        insertion(64);
        insertion(4);
        insertion(8);
        display(root, 1);

        System.out.println("la vleur 30 existe :" + rech(30) + "\n");

        suppression(35);
        display(root, 1);
    }
     */
}

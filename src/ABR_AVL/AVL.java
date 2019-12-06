/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABR_AVL;

import static java.lang.Integer.max;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mounir
 */
public class AVL extends Arbre {

    public AVL(AnchorPane group) {
        super(group);
    }

    public void insertion(int x) {
        root = insertion(root, x);
    }

    private Node insertion(Node n, int x) {
        if (n == null) {
            n = new NodeAVL(x);
        } else {
            if (x < n.getVal()) {
                n.setFG(insertion(n.getFG(), x));
            } else if (x > n.getVal()) {
                n.setFD(insertion(n.getFD(), x));
            }
        }
        NodeAVL v=(NodeAVL)n;
        v.setBalence(calculeBalance(n));
        v = (NodeAVL) rotation(v);
        v.setProfendeur(calculeProfondeur(n));
        n=v;
        return n;
    }

    private int calculeProfondeur(Node n) {
        return (n == null) ? -1 : max(n.getFD().getProfendeur(),n.getFG().getProfendeur())+1;
    }
    private int calculeBalance(Node n){
        return (calculeProfondeur(n.getFG()) - calculeProfondeur(n.getFD()))
    }

    private Node rotation(Node r) {
        NodeAVL R=(NodeAVL)r;
        if (R != null) {
            if ((R.getBalence() == -2) && (((NodeAVL)R.getFD()).getBalence() == -1)) {
                NodeAVL P = R.fd;
                R.fd = P.fg;
                P.fg = R;
                R.profendeur = calculeProfondeur(R);
                P.profendeur = calculeProfondeur(P);
                R.balence = calculeBalance(R);
                P.balence = calculeBalance(P);
                return P;

            } else if ((R.balence == -2) && (R.fd.balence == 1)) {
                NodeAVL P = R.fd;
                NodeAVL Q = P.fg;
                R.fd = Q.fg;
                P.fg = Q.fd;
                Q.fd = P;
                Q.fg = R;
                R.profendeur = calculeProfondeur(R);
                P.profendeur = calculeProfondeur(P);
                Q.profendeur = calculeProfondeur(Q);
                R.balence = calculeBalance(R);
                P.balence = calculeBalance(P);
                Q.balence = calculeBalance(Q);
                return Q;

            } else if ((R.balence == 2) && (R.fg.balence == 1)) {
                NodeAVL P = R.fg;
                R.fg = P.fd;
                P.fd = R;
                R.profendeur = calculeProfondeur(R);
                P.profendeur = calculeProfondeur(P);
                R.balence = calculeBalance(R);
                P.balence = calculeBalance(P);
                return P;

            } else if ((R.balence == 2) && (R.fg.balence == -1)) {
                NodeAVL P = R.fg;
                NodeAVL Q = P.fd;
                R.fg = Q.fd;
                P.fd = Q.fg;
                Q.fd = R;
                Q.fg = P;
                R.profendeur = calculeProfondeur(R);
                P.profendeur = calculeProfondeur(P);
                Q.profendeur = calculeProfondeur(Q);
                R.balence = calculeBalance(R);
                P.balence = calculeBalance(P);
                Q.balence = calculeBalance(Q);
                return Q;
            }

        }
        return R;

    }

    public void delete(int x) {
        root = delete(root, x);
    }

    private NodeAVL delete(NodeAVL n, int x) {
        if (n != null) {
            if (x < n.value) {
                n.fg = delete(n.fg, x);
            } else if (x > n.value) {
                n.fd = delete(n.fd, x);
            } else {
                if ((n.fg == null) || (n.fd == null)) {
                    if (n.fg == null) {
                        n = n.fd;
                    } else {
                        n = n.fg;
                    }

                    return n;
                }

                //si on a choisit le min prédéssusseur:
                NodeAVL t1 = minVal(n.fd);
                n.value = t1.value;
                n.fd = delete(n.fd, t1.value);
                // si on a choisit le max succésseur :
                /*NoeudAvl t2=maxVal(n.fg);
              n.value=t2.value;
              n.fg=delete(n.fg,t2.value);*/
            }
            n.balence = (calculeProfondeur(n.fg) - calculeProfondeur(n.fd));
            n.profendeur = max(calculeProfondeur(n.fg), calculeProfondeur(n.fd)) + 1;
            n = rotation(n);
            return n;
        } else {
            return n;
        }

    }

    public void affich() {
        affichage(root);
    }

    private void affichage(NodeAVL n) {
        if (n == null) {
        } else {
            affichage(n.fg);
            if (n.fg != null) {
                System.out.println("\n fils gauche = " + n.fg.value);
            }
            System.out.println("value = " + n.value + "\n profendeur = " + n.profendeur + "\n balence = " + n.balence);
            if (n.fd != null) {
                System.out.println(" fils droit = " + n.fd.value);
            }
            affichage(n.fd);
        }

    }

    private NodeAVL minVal(NodeAVL n) {
        return (n.fg == null) ? n : minVal(n.fg);
    }

    private NodeAVL maxVal(NodeAVL n) {
        return (n.fd == null) ? n : minVal(n.fd);
    }

    @Override
    public void suppression(int val, char r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

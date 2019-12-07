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

    public void insertionAnimation(String n) {
        insertionAnimation(Integer.parseInt(n));
    }

    public void insertionAnimation(int n) {
        NodeAVL node = new NodeAVL(n);
        this.group.getChildren().add(node.getCircle(0, 0));
        new insertionAnimation(this, node, group).start();
    }

    @Override
    public void insertion(int n) {
        root = insertion(root, new NodeAVL(n));
    }

    public void insertion(String x) {
        insertion(Integer.parseInt(x));
    }

    public NodeAVL insertion(Node n, NodeAVL x) {
        if (n == null) {
            return x;
        } else {
            if (x.getVal() < n.getVal()) {
                n.setFG(insertion(n.getFG(), x));
            } else{
                n.setFD(insertion(n.getFD(), x));
            }
        }
        NodeAVL v = (NodeAVL) n;
        v.setBalence(calculeBalance(v));
        v = rotation(v);
        v.setProfendeur(calculeProfondeur(v));
        return v;
    }

    private int calculeProfondeur(NodeAVL n) {
        return ((n == null) ? -1 : (Math.max(calculeProfondeur(n.getFD()), calculeProfondeur(n.getFG())) + 1));
    }

    private int calculeBalance(NodeAVL n) {
        return (calculeProfondeur(n.getFG()) - calculeProfondeur(n.getFD()));
    }

    private NodeAVL rotation(Node r) {
        NodeAVL R = (NodeAVL) r;
        if (R != null) {
            if ((R.getBalence() == -2) && (R.getFD().getBalence() == -1)) {
                NodeAVL P = R.getFD();
                R.setFD(P.getFG());
                P.setFG(R);
                R.setProfendeur(calculeProfondeur(R));
                P.setProfendeur(calculeProfondeur(P));
                R.setBalence(calculeBalance(R));
                P.setBalence(calculeBalance(P));
                return P;

            } else if ((R.getBalence() == -2) && (R.getFD().getBalence() == 1)) {
                NodeAVL P = R.getFD();
                NodeAVL Q = P.getFG();
                R.setFD(Q.getFG());
                P.setFG(Q.getFD());
                Q.setFD(P);
                Q.setFG(R);
                R.setProfendeur(calculeProfondeur(R));
                P.setProfendeur(calculeProfondeur(P));
                Q.setProfendeur(calculeProfondeur(Q));
                R.setBalence(calculeBalance(R));
                P.setBalence(calculeBalance(P));
                Q.setBalence(calculeBalance(Q));
                return Q;

            } else if ((R.getBalence() == 2) && (R.getFG().getBalence() == 1)) {
                NodeAVL P = R.getFG();
                R.setFG(P.getFD());
                P.setFD(R);
                R.setProfendeur(calculeProfondeur(R));
                P.setProfendeur(calculeProfondeur(P));
                R.setBalence(calculeBalance(R));
                P.setBalence(calculeBalance(P));
                return P;

            } else if ((R.getBalence() == 2) && (R.getFG().getBalence() == -1)) {
                NodeAVL P = R.getFG();
                NodeAVL Q = P.getFD();
                R.setFG(Q.getFD());
                P.setFD(Q.getFG());
                Q.setFD(R);
                Q.setFG(P);
                R.setProfendeur(calculeProfondeur(R));
                P.setProfendeur(calculeProfondeur(P));
                Q.setProfendeur(calculeProfondeur(Q));
                R.setBalence(calculeBalance(R));
                P.setBalence(calculeBalance(P));
                Q.setBalence(calculeBalance(Q));
                return Q;
            }

        }
        return R;

    }
    public void delete(String x, char r) {
        delete(Integer.parseInt(x),r);
    }
    public void delete(int x, char r) {
        root = delete(root, x, r);
    }

    private NodeAVL delete(Node R, int x, char r) {
        NodeAVL n = (NodeAVL) R;
        if (n != null) {
            if (x < n.getVal()) {
                n.setFG(delete(n.getFG(), x, r));
            } else if (x > n.getVal()) {
                n.setFD(delete(n.getFD(), x, r));
            } else {
                if ((n.getFG() == null) || (n.getFD() == null)) {
                    if (n.getFG() == null) {
                        n = n.getFD();
                    } else {
                        n = n.getFG();
                    }

                    return n;
                }

                //si on a choisit le min prédéssusseur
                if (r == 'S') {
                    NodeAVL t1 = minVal(n.getFD());
                    n.setVal(t1.getVal());
                    n.setFD(delete(n.getFD(), t1.getVal(), r));
                } else {
                    NodeAVL t2 = maxVal(n.getFG());
                    n.setVal(t2.getVal());
                    n.setFG(delete(n.getFG(), t2.getVal(),r));
                }
                // si on a choisit le max succésseur :
                /**/
            }
            n.setBalence(calculeBalance(n));
            n.setProfendeur(calculeProfondeur(n));
            n = rotation(n);
            return n;
        } else {
            return n;
        }

    }

    public void affich() {
        affichage((NodeAVL) root);
    }

    private void affichage(NodeAVL n) {
        if (n == null) {
        } else {
            affichage(n.getFG());
            /* if (n.getFG() != null) {
                System.out.println("\n fils gauche = " + n.fg.value);
            }*/
            System.out.println("value = " + n.getVal() /*+ "\n profendeur = " + n.profendeur */ + "\n balence = " + n.getBalence());
            /*if (n.fd != null) {
                System.out.println(" fils droit = " + n.fd.value);
            }*/
            affichage(n.getFD());
        }

    }

    private NodeAVL minVal(NodeAVL n) {
        return (n.getFG() == null) ? n : minVal(n.getFG());
    }

    private NodeAVL maxVal(NodeAVL n) {
        return (n.getFD() == null) ? n : minVal(n.getFD());
    }

    @Override
    public void suppression(int val, char r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

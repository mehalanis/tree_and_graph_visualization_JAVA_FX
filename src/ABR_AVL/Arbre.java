package ABR_AVL;
import java.util.*;

public abstract class Arbre {
   public Node root;
   
   public abstract void insertion(int n);
   public abstract boolean suppression(int val);
   public abstract Node rechercher(int val);

}
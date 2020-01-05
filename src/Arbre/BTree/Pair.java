package Arbre.BTree;


public class Pair<A extends Comparable<A>> implements Comparable<Pair<A>> {

    A first;
    Pair(A a) {
        first = a;
    }

    public String toString() {
        if (first == null )
            return "null";
        return first.toString();
    }

    @Override
    public int compareTo(Pair<A> o) {
        return first.compareTo(o.first);
    }
}

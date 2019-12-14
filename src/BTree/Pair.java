package BTree;

import java.io.Serializable;


public class Pair<A extends Comparable<A>> implements Comparable<Pair<A>>, Serializable {
    private static final long serialVersionUID = -8914647164831651005L;

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

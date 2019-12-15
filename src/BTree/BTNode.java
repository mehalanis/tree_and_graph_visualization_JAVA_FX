package BTree;

import java.io.Serializable;
import java.util.ArrayList;
import Formes.rectangle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

/**
 * DataStructure Created by Blaise Wang on 16/6/5.
 */
public class BTNode<E extends Comparable<E>>  {

    private int fullNumber;
    private BTNode<E> father;
    ArrayList<BTNode<E>> children = new ArrayList<BTNode<E>>();
    ArrayList<E> keys = new ArrayList<>();
    ArrayList< rectangle> rectangle=new ArrayList< rectangle> ();

    BTNode() {
    }

    BTNode(int order) {
        fullNumber = order - 1;
    }

    
    boolean isLastInternalNode() {
        if (keys.size() == 0) {
            return false;
        }
        for (BTNode<E> node : children) {
            if (node.keys.size() != 0) {
                return false;
            }
        }
        return true;
    }

    BTNode<E> getFather() {
        return father;
    }

    public rectangle getRectangle(int pos){
       return  rectangle.get(pos);
    }
    public void addRectangle(rectangle rectangle) {
        this.rectangle.add(rectangle);
    }

    void setFather(BTNode<E> father) {
        this.father = father;
    }

    BTNode<E> getChild(int index) {
        return children.get(index);
    }

    void addChild(int index, BTNode<E> node) {
        children.add(index, node);
    }

    void removeChild(int index) {
        children.remove(index);
    }

    E getKey(int index) {
        return keys.get(index);
    }

    void addKey(int index, E element) {
        keys.add(index, element);
    }

    void removeKey(int index) {
        keys.remove(index);
    }

    boolean isFull() {
        return fullNumber == keys.size();
    }

    boolean isOverflow() {
        return fullNumber < keys.size();
    }

    boolean isNull() {
        return keys.isEmpty();
    }
    boolean ischildrenNull() {
        return children.isEmpty();
    }

    int getSize() {
        return keys.size();
    }

    public String toString() {
        if (keys.size() == 0) {
            return "NullNode";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[Numbers: ").append(keys.size()).append("] [values: ");
        for (E e : keys) {
            sb.append(e).append(", ");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("] [father: ");
        if (father.keys.size() == 0) {
            sb.append("NullNode");
        } else {
            for (E e : father.keys) {
                sb.append(e).append(", ");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("] [children: ");
        for (BTNode<E> node : children) {
            if (node.getSize() == 0) {
                sb.append(node).append(", ");
            } else {
                sb.append("NotNullNode" + ", ");
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("] [childrenSize: ").append(children.size()).append("]");
        return sb.toString();
    }
}

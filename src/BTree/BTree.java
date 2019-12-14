package BTree;

import Formes.rectangle;
import java.io.Serializable;
import java.util.LinkedList;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class BTree<K extends Comparable<K>> implements Serializable {

    private BTNode<Pair<K>> root = null;
    private int order, index, treeSize;
    private final int halfNumber;
    private final BTNode<Pair<K>> nullBTNode = new BTNode<Pair<K>>();
    private Group arbre;

    /**
     *
     * @param order of B-tree
     */
    public BTree(int order, Group arbre) {
        if (order < 3) {
            try {
                throw new Exception("B-tree's order can not lower than 3");
            } catch (Exception e) {
                e.printStackTrace();
            }
            order = 3;

        }
        this.arbre = arbre;
        this.order = order;
        halfNumber = (order - 1) / 2;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BTNode<Pair<K>> getRoot() {
        return root;
    }

    public int getTreeSize() {
        return treeSize;
    }

    public int getProfondeur() {
        if (isEmpty()) {
            return 0;
        } else {
            return getProfondeur(root);
        }
    }

    private int getProfondeur(BTNode<Pair<K>> node) {
        int height = 0;
        BTNode<Pair<K>> currentNode = node;
        while (!currentNode.equals(nullBTNode)) {
            currentNode = currentNode.getChild(0);
            height++;
        }
        return height;
    }

    public Pair<K> get(K key) {
        BTNode<Pair<K>> node = getNode(key);
        if (node != nullBTNode) {
            return node.getKey(index);
        } else {
            return null;
        }
    }

    public BTNode<Pair<K>> getNode(K key) {
        if (isEmpty()) {
            return nullBTNode;
        }
        BTNode<Pair<K>> currentNode = root;
        while (!currentNode.equals(nullBTNode)) {
            int i = 0;
            while (i < currentNode.getSize()) {
                if (currentNode.getKey(i).first.equals(key)) {
                    index = i;
                    return currentNode;
                } else if (currentNode.getKey(i).first.compareTo(key) > 0) {
                    currentNode = currentNode.getChild(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            if (!currentNode.isNull()) {
                currentNode = currentNode.getChild(currentNode.getSize());
            }
        }
        return nullBTNode;
    }

    public void replace(K key) {
        Pair<K> pair = new Pair<K>(key);
        BTNode<Pair<K>> currentNode = root;

        if (get(pair.first) == null) {
            return;
        }
        while (!currentNode.equals(nullBTNode)) {
            int i = 0;
            while (i < currentNode.getSize()) {
                if (currentNode.getKey(i).first.equals(pair.first)) {

                    return;
                } else if (currentNode.getKey(i).first.compareTo(pair.first) > 0) {
                    currentNode = currentNode.getChild(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            if (!currentNode.isNull()) {
                currentNode = currentNode.getChild(currentNode.getSize());
            }
        }
    }

    private BTNode<Pair<K>> getHalfKeys(Pair<K> pair, BTNode<Pair<K>> fullNode) {
        int fullNodeSize = fullNode.getSize();

        for (int i = 0; i < fullNodeSize; i++) {
            if (fullNode.getKey(i).first.compareTo(pair.first) > 0) {
                fullNode.addKey(i, pair);
                break;
            }
        }
        if (fullNodeSize == fullNode.getSize()) {
            fullNode.addKey(fullNodeSize, pair);
        }

        return getHalfKeys(fullNode);
    }

    private BTNode<Pair<K>> getHalfKeys(BTNode<Pair<K>> fullNode) {
        BTNode<Pair<K>> newNode = new BTNode<Pair<K>>(order);
        for (int i = 0; i < halfNumber; i++) {
            newNode.addKey(i, fullNode.getKey(0));
            fullNode.removeKey(0);
        }
        return newNode;
    }

    private BTNode<Pair<K>> getRestOfHalfKeys(BTNode<Pair<K>> halfNode) {
        BTNode<Pair<K>> newNode = new BTNode<Pair<K>>(order);
        int halfNodeSize = halfNode.getSize();
        for (int i = 0; i < halfNodeSize; i++) {
            if (i != 0) {
                newNode.addKey(i - 1, halfNode.getKey(1));
                halfNode.removeKey(1);
            }
            newNode.addChild(i, halfNode.getChild(0));
            halfNode.removeChild(0);
        }
        return newNode;
    }

    private void mergeWithFatherNode(BTNode<Pair<K>> childNode, int index) {
        childNode.getFather().addKey(index, childNode.getKey(0));
        childNode.getFather().removeChild(index);
        childNode.getFather().addChild(index, childNode.getChild(0));
        childNode.getFather().addChild(index + 1, childNode.getChild(1));
    }


    private void mergeWithFatherNode(BTNode<Pair<K>> childNode) {
        int fatherNodeSize = childNode.getFather().getSize();
        for (int i = 0; i < fatherNodeSize; i++) {
            if (childNode.getFather().getKey(i).compareTo(childNode.getKey(0)) > 0) {
                mergeWithFatherNode(childNode, i);
                break;
            }
        }
        if (fatherNodeSize == childNode.getFather().getSize()) {
            mergeWithFatherNode(childNode, fatherNodeSize);
        }
        for (int i = 0; i <= childNode.getFather().getSize(); i++) {
            childNode.getFather().getChild(i).setFather(childNode.getFather());
        }
    }

    private void setSplitFatherNode(BTNode<Pair<K>> node) {
        for (int i = 0; i <= node.getSize(); i++) {
            node.getChild(i).setFather(node);
        }
    }

    private void processOverflow(BTNode<Pair<K>> currentNode) {
        BTNode<Pair<K>> newNode = getHalfKeys(currentNode);
        for (int i = 0; i <= newNode.getSize(); i++) {
            newNode.addChild(i, currentNode.getChild(0));
            currentNode.removeChild(0);
        }
        BTNode<Pair<K>> originalNode = getRestOfHalfKeys(currentNode);
        currentNode.addChild(0, newNode);
        currentNode.addChild(1, originalNode);
        originalNode.setFather(currentNode);
        newNode.setFather(currentNode);
        setSplitFatherNode(originalNode);
        setSplitFatherNode(newNode);
    }

    public void insert(K key) {
        Pair<K> pair = new Pair<K>(key);
        if (isEmpty()) {
            root = new BTNode<Pair<K>>(order);
            root.addKey(0, pair);
            treeSize++;
            root.setFather(nullBTNode);
            root.addChild(0, nullBTNode);
            root.addChild(1, nullBTNode);
            return;
        }

        BTNode<Pair<K>> currentNode = root;

        if (get(pair.first) != null) {
            replace(key);
            return;
        }

        while (!currentNode.isLastInternalNode()) {
            int i = 0;
            while (i < currentNode.getSize()) {
                if (currentNode.isLastInternalNode()) {
                    i = currentNode.getSize();
                } else if (currentNode.getKey(i).first.compareTo(pair.first) > 0) {
                    currentNode = currentNode.getChild(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            if (!currentNode.isLastInternalNode()) {
                currentNode = currentNode.getChild(currentNode.getSize());
            }
        }

        if (!currentNode.isFull()) {
            int i = 0;
            while (i < currentNode.getSize()) {
                if (currentNode.getKey(i).first.compareTo(pair.first) > 0) {
                    currentNode.addKey(i, pair);
                    currentNode.addChild(currentNode.getSize(), nullBTNode);
                    treeSize++;
                    return;
                } else {
                    i++;
                }
            }
            currentNode.addKey(currentNode.getSize(), pair);
            currentNode.addChild(currentNode.getSize(), nullBTNode);
            treeSize++;
        } else {
            BTNode<Pair<K>> newChildNode = getHalfKeys(pair, currentNode);
            for (int i = 0; i < halfNumber; i++) {
                newChildNode.addChild(i, currentNode.getChild(0));
                currentNode.removeChild(0);
            }
            newChildNode.addChild(halfNumber, nullBTNode);
            BTNode<Pair<K>> originalFatherNode = getRestOfHalfKeys(currentNode);
            currentNode.addChild(0, newChildNode);
            currentNode.addChild(1, originalFatherNode);
            originalFatherNode.setFather(currentNode);
            newChildNode.setFather(currentNode);
            treeSize++;

            if (!currentNode.getFather().equals(nullBTNode)) {
                while (!currentNode.getFather().isOverflow() && !currentNode.getFather().equals(nullBTNode)) {
                    boolean flag = currentNode.getSize() == 1 && !currentNode.getFather().isOverflow();
                    if (currentNode.isOverflow() || flag) {
                        mergeWithFatherNode(currentNode);
                        currentNode = currentNode.getFather();
                        if (currentNode.isOverflow()) {
                            processOverflow(currentNode);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

 
    private int findChild(BTNode<Pair<K>> node) {
        if (!node.equals(root)) {
            BTNode<Pair<K>> fatherNode = node.getFather();

            for (int i = 0; i <= fatherNode.getSize(); i++) {
                if (fatherNode.getChild(i).equals(node)) {
                    return i;
                }
            }
        }
        return -1;
    }

 
    private BTNode<Pair<K>> balanceDeletedNode(BTNode<Pair<K>> node) {
        boolean flag;
        int nodeIndex = findChild(node);
        Pair<K> pair;
        BTNode<Pair<K>> fatherNode = node.getFather();
        BTNode<Pair<K>> currentNode;
        if (nodeIndex == 0) {
            currentNode = fatherNode.getChild(1);
            flag = true;
        } else {
            currentNode = fatherNode.getChild(nodeIndex - 1);
            flag = false;
        }

        int currentSize = currentNode.getSize();
        if (currentSize > halfNumber) {
            if (flag) {
                pair = fatherNode.getKey(0);
                node.addKey(node.getSize(), pair);
                fatherNode.removeKey(0);
                pair = currentNode.getKey(0);
                currentNode.removeKey(0);
                node.addChild(node.getSize(), currentNode.getChild(0));
                currentNode.removeChild(0);
                fatherNode.addKey(0, pair);
                if (node.isLastInternalNode()) {
                    node.removeChild(0);
                }
            } else {
                pair = fatherNode.getKey(nodeIndex - 1);
                node.addKey(0, pair);
                fatherNode.removeKey(nodeIndex - 1);
                pair = currentNode.getKey(currentSize - 1);
                currentNode.removeKey(currentSize - 1);
                node.addChild(0, currentNode.getChild(currentSize));
                currentNode.removeChild(currentSize);
                fatherNode.addKey(nodeIndex - 1, pair);
                if (node.isLastInternalNode()) {
                    node.removeChild(0);
                }
            }
            return node;
        } else {
            if (flag) {
                currentNode.addKey(0, fatherNode.getKey(0));
                fatherNode.removeKey(0);
                fatherNode.removeChild(0);
                if (root.getSize() == 0) {
                    root = currentNode;
                    currentNode.setFather(nullBTNode);
                }
                if (node.getSize() == 0) {
                    currentNode.addChild(0, node.getChild(0));
                    currentNode.getChild(0).setFather(currentNode);
                }
                for (int i = 0; i < node.getSize(); i++) {
                    currentNode.addKey(i, node.getKey(i));
                    currentNode.addChild(i, node.getChild(i));
                    currentNode.getChild(i).setFather(currentNode);
                }
            } else {
                currentNode.addKey(currentNode.getSize(), fatherNode.getKey(nodeIndex - 1));
                fatherNode.removeKey(nodeIndex - 1);
                fatherNode.removeChild(nodeIndex);
                if (root.getSize() == 0) {
                    root = currentNode;
                    currentNode.setFather(nullBTNode);
                }
                int currentNodeSize = currentNode.getSize();
                if (node.getSize() == 0) {
                    currentNode.addChild(currentNodeSize, node.getChild(0));
                    currentNode.getChild(currentNodeSize).setFather(currentNode);
                }
                for (int i = 0; i < node.getSize(); i++) {
                    currentNode.addKey(currentNodeSize + i, node.getKey(i));
                    currentNode.addChild(currentNodeSize + i, node.getChild(i));
                    currentNode.getChild(currentNodeSize + i).setFather(currentNode);
                }
            }
            return fatherNode;
        }
    }

 
    private BTNode<Pair<K>> replaceNode(BTNode<Pair<K>> node) {
        BTNode<Pair<K>> currentNode = node.getChild(index + 1);
        while (!currentNode.isLastInternalNode()) {
            currentNode = currentNode.getChild(0);
        }
        if (currentNode.getSize() - 1 < halfNumber) {
            currentNode = node.getChild(index);
            int currentNodeSize = currentNode.getSize();
            while (!currentNode.isLastInternalNode()) {
                currentNode = currentNode.getChild(currentNodeSize);
            }
            node.addKey(index, currentNode.getKey(currentNodeSize - 1));
            currentNode.removeKey(currentNodeSize - 1);
            currentNode.addKey(currentNodeSize - 1, node.getKey(index + 1));
            node.removeKey(index + 1);
            index = currentNode.getSize() - 1;
        } else {
            node.addKey(index + 1, currentNode.getKey(0));
            currentNode.removeKey(0);
            currentNode.addKey(0, node.getKey(index));
            node.removeKey(index);
            index = 0;
        }
        return currentNode;
    }

    public void delete(K key) {
        BTNode<Pair<K>> node = getNode(key);
        BTNode<Pair<K>> deleteNode = null;
        if (node.equals(nullBTNode)) {
            return;
        }

        if (node.equals(root) && node.getSize() == 1 && node.isLastInternalNode()) {
            root = null;
            treeSize--;
        } else {
            boolean flag = true;
            boolean isReplaced = false;
            if (!node.isLastInternalNode()) {
                node = replaceNode(node);
                deleteNode = node;
                isReplaced = true;
            }

            if (node.getSize() - 1 < halfNumber) {
                node = balanceDeletedNode(node);
                if (isReplaced) {
                    for (int i = 0; i <= node.getSize(); i++) {
                        for (int j = 0; i < node.getChild(i).getSize(); j++) {
                            if (node.getChild(i).getKey(j).first.equals(key)) {
                                deleteNode = node.getChild(i);
                                break;
                            }
                        }
                    }
                }
            } else if (node.isLastInternalNode()) {
                node.removeChild(0);
            }

            while (!node.getChild(0).equals(root) && node.getSize() < halfNumber && flag) {
                if (node.equals(root)) {
                    for (int i = 0; i <= root.getSize(); i++) {
                        if (root.getChild(i).getSize() == 0) {
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    node = balanceDeletedNode(node);
                }
            }

            if (deleteNode == null) {
                node = getNode(key);
            } else {
                node = deleteNode;
            }

            if (!node.equals(nullBTNode)) {
                for (int i = 0; i < node.getSize(); i++) {
                    if (node.getKey(i).first == key) {
                        node.removeKey(i);
                    }
                }
                treeSize--;
            }
        }
    }

    public void Afficher() {
        arbre.getChildren().clear();
        int gap = this.getProfondeur();
        gap = gap * this.order * 40;
        this.Afficher(root, null, 1200 / 2, 0, 1200 / 2, 0, 0, gap);

    }

    public void Afficher(BTNode t, BTNode root, int x, int y, int prevx, int prevy, int lev, int gap) {

        if (t == null) {
            return;
        }
        HBox hbox = new HBox();
        rectangle r;
        for (int i = 0; i < t.keys.size(); i++) {
            r = new rectangle(Integer.parseInt(t.getKey(i).toString()));
            hbox.getChildren().add(r);
        }
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        arbre.getChildren().add(hbox);
        if ((++lev) != 1) {

             Line line = new Line(prevx, prevy + 36, x + (35 * t.getSize()) / 2, y);
            line.setStrokeWidth(2);
            arbre.getChildren().add(line);
            gap = (gap) / this.order;
        }
        if (t.isLastInternalNode()) {
            return;
        }
        int xpere = x;

        for (int i = 0; i < t.getSize()+1; i++) {
            if ((t.getSize()+1 == 2) && (i == 1)) {
                x += gap;
            }
            Afficher(t.getChild(i), t, x - gap, y + 58, xpere, y, lev, gap);
            x += gap;

            xpere += 35;
        }
    }
}

package bstmap;


import jh61b.junit.In;

import java.util.*;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private Node root;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    private Node find(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);      // if key > curr.key   >0
            if (cmp > 0) {    //key > curr.key
                curr = curr.right;
            }
            else if (cmp < 0) {
                curr = curr.left;
            }
            else {
                return curr;
            }
        }
        return null;
    }

    private void insert(K key, V value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
            size += 1;
            return;
        }
        Node curr = this.root;
        Node prev = curr;
        while (curr != null) {
            prev = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp > 0) {          //   key > curr.key
                curr = curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        int cmp = key.compareTo(prev.key);
        if (cmp > 0) {
            prev.right = newNode;
        }
        else {
            prev.left = newNode;
        }
        size += 1;

    }

    @Override
    public boolean containsKey(K key) {
        if (find(key) != null ) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        Node ret = find(key);
        if (ret != null) {
            return ret.value;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        Node fNode = find(key);
        if (fNode == null) {
            insert(key, value);
        }
        else {
            fNode.value = value;
        }
    }

    private void inOrderHelper(Node root) {    // L D R
        if (root == null) {
            return;
        }
        inOrderHelper(root.left);
        System.out.print(root.key + " ");
        inOrderHelper(root.right);
    }
    public void printInOrder() {
        inOrderHelper(this.root);
        System.out.println();
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for (K ele : this) {
            ret.add(ele);
        }
        return ret;
    }

    public Node minOfRight(Node root) {
        Node curr = root;
        while (curr.left != null && curr.right != null) {      // to make curr a leaf Node
            curr = curr.left;
        }
        return curr;
    }

    private int numOfChild(Node root) {
        int count = 0;
        if (root.left != null ) {
            count += 1;
        }
        if (root.right != null) {
            count += 1;
        }
        return count;
    }

    public Node findParent(Node node) {
        Node curr = root;
        Node prev = curr;
        if (node.key.equals(root.key)) {
            return null;
        }
        while ( !(curr.key.equals(node.key)) ) {
            prev = curr;
            int cmp = node.key.compareTo(curr.key);
            if (cmp > 0 ) {        // node > curr
                curr = curr.right;
            }
            else if (cmp < 0) {
                curr = curr.left;
            }
        }
        return prev;
    }

    public Node delete(Node node) {
        int numChild = numOfChild(node);
        if (numChild == 0) {
            if (size == 1) {        // 这是root
                size = 0;
                root = null;
                return node;
            }
            else {                  // leaf node
                Node parent = findParent(node);
                if (parent.left != null && parent.left.key.equals(node.key)) {
                    parent.left = null;
                    size -= 1;
                    return node;
                }
                else {
                    parent.right = null;
                    size -= 1;
                    return node;
                }
            }
        }
        else if (numChild == 1) {
            Node parent = findParent(node);
            if (parent == null) {    // node 是 root
                if (node.left != null) {
                    root = root.left;
                }
                else {
                    root = root.right;
                }
                size -= 1;
                return node;
            }
            if (parent.left != null && parent.left.key.equals(node.key)) {
                if (node.left != null) {    // node只有.left
                    parent.left = node.left;
                    size -= 1;
                    return node;
                }
                else {
                    parent.left = node.right;
                    size -= 1;
                    return node;
                }
            }
            else {
                if (node.left != null) {    // node只有.left
                    parent.right = node.left;
                    size -= 1;
                    return node;
                }
                else {
                    parent.right = node.right;
                    size -= 1;
                    return node;
                }
            }
        }
        else {
            Node successor = minOfRight(node);
            delete(successor);
            node.key = successor.key;
            node.value = successor.value;
//            size -= 1;
            return node;
        }
    }



    @Override
    public V remove(K key) {
        Node findN = find(key);
        if (findN == null) {
            return null;
        }
        else {
            V val = findN.value;
            delete(findN);
            return val;
        }
    }

    @Override
    public V remove(K key, V value) {
//        throw new UnsupportedOperationException();
//        return null;
        Node findN = find(key);
        if (findN == null || !(findN.value.equals(value)) ) {
            return null;
        }
        else {
            V val = findN.value;
            delete(findN);
            return val;
        }
    }

    public class treeInterator implements Iterator<K> {
        private List<K> data;
        private int index;

        private void helper(List<K> lst, Node root) {
            if (root == null) {
                return;
            }
            helper(lst, root.left);
            lst.add(root.key);
            helper(lst, root.right);
        }
        public List<K> inorderTraversal(Node root) {
            List<K> lst = new ArrayList<>();
            helper(lst, root);
            return lst;
        }
        public treeInterator() {
            data = inorderTraversal(root);
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < data.size();
        }

        @Override
        public K next() {
            K ret = data.get(index);
            index += 1;
            return ret;
        }
    }
    @Override
    public Iterator<K> iterator() {
//        throw new UnsupportedOperationException();
        return new treeInterator();

    }

    @Override
    public void forEach(Consumer<? super K> action) {
        Map61B.super.forEach(action);
    }

    @Override
    public Spliterator<K> spliterator() {
        return Map61B.super.spliterator();
    }
}

package bstmap;


import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
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

    public void printInOrder() {
        return;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
//        return null;
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

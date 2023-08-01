package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private double maxLoadFactor;
    private int numItems;
    private int numBuckets;
    private double currLoadFactor;

    /** Constructors */
    public MyHashMap() {
        createTable(16);
        numItems = 0;
        numBuckets = 16;
        currLoadFactor = 0;
        maxLoadFactor = 0.75;
    }

    public MyHashMap(int initialSize) {
        createTable(initialSize);
        numItems = 0;
        numBuckets = initialSize;
        currLoadFactor = 0;
        maxLoadFactor = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        createTable(initialSize);
        numItems = 0;
        numBuckets = initialSize;
        currLoadFactor = 0;
        maxLoadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        buckets = new Collection[tableSize];
        for (int i = 0; i < tableSize; ++i) {
            buckets[i] = createBucket();
        }
//        numItems = 0;
        numBuckets = tableSize;
//        currLoadFactor = computeLF(numItems, numBuckets);
//        maxLoadFactor = 0.75;
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private double computeLF(int numItems, int numBuckets) {
        return (double) numItems / (double) numBuckets ;
    }

    @Override
    public void clear() {
        buckets = null;
        numBuckets = 0;
        numItems = 0;
        currLoadFactor = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (numItems == 0) {
            return false;
        }
        int hash = key.hashCode();
        int index = Math.floorMod(hash, numBuckets);
        Iterator<Node> iter = buckets[index].iterator();
        while (iter.hasNext()) {
            if (iter.next().key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private Node find(K key) {
        if (numItems == 0) {
            return null;
        }
        int hash = key.hashCode();
        int index = Math.floorMod(hash, numBuckets);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node item = find(key);
        if (item != null) {
            return item.value;
        }
        return null;
    }

    @Override
    public int size() {
        return numItems;
    }

    private void resize(int tableSize) {
        Collection<Node>[] oldBucket = buckets;
        numBuckets = tableSize;
        numItems = 0;
        currLoadFactor = computeLF(numItems,numBuckets);
        createTable(tableSize);
        for (int i = 0; i < oldBucket.length; ++i) {
            for (Node ele : oldBucket[i]) {
                this.put(ele.key, ele.value);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        Node item = find(key);
        if (item != null) {
            item.value = value;
        }
        else {
            int hash = key.hashCode();
            int index = Math.floorMod(hash, numBuckets);
            buckets[index].add(createNode(key, value));
            numItems += 1;
            currLoadFactor = computeLF(numItems, numBuckets);
        }
        if (currLoadFactor > maxLoadFactor) {
            resize(numBuckets * 2);
        }

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
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }


}

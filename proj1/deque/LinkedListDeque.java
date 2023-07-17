package deque;
import java.util.Iterator;
import java.util.Objects;
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public Node next;
        public T item;

        public Node(T data) {
            item = data;
            prev = null;
            next = null;
        }
    }

    // first = sentinel.next
    // last = sentinel.prev
    // sentinel = first.prev || last.next
    public LinkedListDeque() {
        sentinel = new Node(null);
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
//    @Override
//    public boolean isEmpty() {
//        return size() == 0;
//    }
    @Override
    public void addFirst(T item) {
        if (size == 0) {              //  第一个既是first 也是last
            Node newNode = new Node(item);
            sentinel.next = newNode;
            sentinel.prev = newNode;
            newNode.next = sentinel;
            newNode.prev = sentinel;
        }
        else {                // 连接sentinel和newNode, 连接newNode和oldFirst
            Node newNode = new Node(item);
            Node oldFirst = sentinel.next;
            sentinel.next = newNode;
            newNode.prev = sentinel;
            newNode.next = oldFirst;
            oldFirst.prev = newNode;
        }
        size += 1;
    }
    @Override
    public void addLast(T item) {
        if (size == 0) {
            Node newNode = new Node(item);
            sentinel.next = newNode;
            sentinel.prev = newNode;
            newNode.next = sentinel;
            newNode.prev = sentinel;
        }
        else {                             // 连接oldLast和newLast, 连接newLast和sentinel
            Node newNode = new Node(item);
            Node oldLast = sentinel.prev;
            oldLast.next = newNode;
            newNode.prev = oldLast;
            sentinel.prev = newNode;
            newNode.next = sentinel;
        }
        size += 1;
    }
    @Override
    public T get(int index) {        // first = index 0
        if (size == 0) {
            return null;
        }
        int count = 0;
        Node curr = sentinel.next;
        while (count < index) {
            curr = curr.next;
            count += 1;
        }
        return curr.item;
    }

    private T helper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return helper(n.next,index-1);
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        return helper(sentinel.next, index);          // 把first node 和 index传进去
    }
    @Override
    public T removeFirst() {          //别忘更新size
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            Node removeN = sentinel.next;
            removeN.prev = null;
            removeN.next = null;
            sentinel.next = null;
            sentinel.prev = null;
            size -= 1;
            return removeN.item;
        }
        else {
            // 连接sentinel和newFirst，切断 oldFirst 和 newFirst
            Node newFirst = sentinel.next.next;
            Node oldFirst = sentinel.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            oldFirst.next = null;
            oldFirst.prev = null;
            size -= 1;
            return oldFirst.item;
        }


    }

    @Override
    public T removeLast() {         // 别忘update size
        if (size == 0) {
            return null;
        }
        Node oldLast = sentinel.prev;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
            oldLast.next = null;
            oldLast.prev = null;
            size -= 1;
            return oldLast.item;
        }
        else {     // 连接sentinel 和 newLast, 切断 newLast和 newLast
            Node newLast = sentinel.prev.prev;
            sentinel.prev = newLast;
            newLast.next = sentinel;
            oldLast.next = null;
            oldLast.prev = null;
            size -= 1;
            return oldLast.item;
        }

    }

    public void printDequeBad() {       //  这样极其不efficient， 因为get是O(n)
        if (size != 0) {
            for (int i = 0; i < size; ++i) {
                T data = get(i);
                System.out.print(data + " ");
            }
            System.out.println();
        }

    }

    @Override
    public void printDeque() {
        if (size != 0) {
            for (Node curr = sentinel.next; curr != sentinel; curr = curr.next ) {
                System.out.print(curr.item + " ");
            }
            System.out.println();
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node curr;
        public LinkedListDequeIterator() {
            curr = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return (curr != null && curr != sentinel);           // 如果curr是null或者curr.next是sentinel说明到头了
        }

        @Override
        public T next() {
            T data = curr.item;
            curr = curr.next;
            return data;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque other) {
            if (this.size != other.size) {
                return false;
            }
            Node curr = this.sentinel.next;
            Node otherCurr = other.sentinel.next;
            int count = 0;
            while (count < size) {
                if ( !(curr.item.equals(otherCurr.item)) ) {
                    return false;
                }
                count += 1;
            }
            return true;
        }
        return false;
    }

}

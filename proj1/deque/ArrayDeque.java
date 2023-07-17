package deque;

public class ArrayDeque<T> {

    private T[] data;
    private int size;
    private int frontIndex;
    private int backIndex;      // current backIndex = (frontIndex + size -1) % data.length

    // 当addFirst: frontIndex = (frontIndex -1 + data.length) % data.length   // 要加的位置
    //             backIndex = 不变
    // 当addLast: backIndex = (frontIndex + size) % data.length;   // 要加的位置
    //            frontIndex = 不变
    // 当removeFirst: frontIndex = (frontIndex +1 + data.length) % data.length  // 新的frontIndex
    //                backIndex = 不变
    // 当removeLast: backIndex = (backIndex -1 + data.length) % data.length
    //              frontIndex = 不变

    public ArrayDeque() {            // when queue is empty, frontIndex and backIndex is -1
        data = (T[]) new Object[8];
        size = 0;
        frontIndex = -1;
        backIndex = -1;
    }

    public void updateFrontIndex() {
        frontIndex = frontIndex - 1 + size;
    }
    public void updateBackIndex() {
        backIndex = (frontIndex + size) % data.length;
    }
    public void resize(int newSize) {        // resize完之后 frontIndex = 0;
        T[] a = (T[]) new Object[newSize];
        int start = frontIndex;
        int count = 0;
        while (start != backIndex) {
            a[count] = data[start];
            start = (start + data.length + 1) % data.length;
            count += 1;
        }
        a[count] = data[start];
        data = a;

//        for (int start = frontIndex; start <= backIndex; start = (start + data.length + 1)% data.length)  {
//            a[count] = data[start];
//            count += 1;
//        }
        frontIndex = 0;
        backIndex = frontIndex + size -1;

    }
    public void addFirst(T item) {
        if (data.length == size) {     // array 满了
            resize(size * 2);
            frontIndex = (0 - 1 + data.length) % data.length;
            data[frontIndex] = item;
            size += 1;
            backIndex = (frontIndex + size -1) % data.length;
        }
        else if (size == 0) {
            data[0] = item;
            frontIndex = 0;
            backIndex = 0;
            size = 1;
        }
        else {
            frontIndex = (frontIndex -1 + data.length) % data.length;
            data[frontIndex] = item;
            size += 1;
            backIndex = (frontIndex + size -1) % data.length;
        }
    }
    public void addLast(T item) {
        if (data.length == size) {
            resize(size * 2);
            frontIndex = 0;
            backIndex = (frontIndex + size) % data.length;
            data[backIndex] = item;
            size += 1;
        }
        else if (size == 0) {     // 空 queue
            data[0] = item;
            frontIndex = 0;
            backIndex = 0;
            size += 1;
        }
        else {
            backIndex = (frontIndex + size) % data.length;
            data[backIndex] = item;
            size += 1;
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int start = frontIndex;
        while (start != backIndex) {
            System.out.print(data[start] + " ");
            start = (start + data.length + 1)% data.length;
        }
        System.out.print(data[start] + " ");
        System.out.println();
//        for (int start = frontIndex; start <= backIndex; start = (start + data.length + 1)% data.length) {
//            System.out.print(data[start] + " ");
//        }
        System.out.println();
    }
    public T removeFirst() {
//        return data[0];
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T item = data[frontIndex];
            data[frontIndex] = null;
            size -= 1;
            return item;
        }
        if ( (size < data.length / 4) && (size > 16) ) {    //  需要resize
            resize(data.length / 4);
            T item = data[0];
            data[0] = null;
            frontIndex = (frontIndex +1 + data.length) % data.length;   // 1
            size -= 1;
//            backIndex = (frontIndex + size -1) % data.length;
            return item;
        }
        else {
            T item = data[frontIndex];
            data[frontIndex] = null;
            frontIndex = (frontIndex +1 + data.length) % data.length;
            size -= 1;
            return item;
        }


    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (size == 1) {
            T item = data[backIndex];
            data[backIndex] = null;
            size -= 1;
            return item;
        }

        if ( (size < data.length / 4) && (size > 16) ) {           // resize
            resize(data.length / 4);
            T item = data[backIndex];
            data[backIndex] = null;
            size -= 1;
            backIndex = (frontIndex + size -1) % data.length;
            return item;
        }
        else {
            T item = data[backIndex];
            data[backIndex] = null;
            size -= 1;
            backIndex = (frontIndex + size -1) % data.length;
            return item;
        }
    }
    public T get(int index) {
        int realIndex = (frontIndex + index) % data.length;
        return data[realIndex];
    }



}

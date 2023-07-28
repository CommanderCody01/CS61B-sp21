package bstmap;

import static org.junit.Assert.assertTrue;

public class Test {

    public static void main(String[] args) {
//        BSTMap<Integer, Integer> b = new BSTMap<Integer, Integer>();
//        for (int i = 0; i < 11; i++){
//            b.put(i, 1 + i);
//        }
//
//        b.printInOrder();
//
//        BSTMap<String,String> q = new BSTMap<String,String>();
//        q.put("c","a");
//        q.put("b","a");
//        q.put("a","a");
//        q.put("d","a");
//        q.put("e","a");
//        q.printInOrder();
//        q.remove("e");
//        q.printInOrder();
        BSTMap rightChild = new BSTMap();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        Integer result = (Integer) rightChild.remove('A');
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C'+i), 3+i);
        }
        rightChild.put('A', 100);
        rightChild.printInOrder();

    }

}

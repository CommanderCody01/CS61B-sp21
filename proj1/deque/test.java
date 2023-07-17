package deque;

public class test {
    public static void main(String[] args) {
        LinkedListDeque<Integer> lst = new LinkedListDeque<Integer>();
        lst.addFirst(10);
        lst.addFirst(5);
        lst.addFirst(0);
        lst.addLast(100);
//        System.out.println(lst.get(0));
//        System.out.println(lst.getRecursive(0));
//        lst.printDeque();

        for (int x : lst) {
            System.out.println(x);
        }


        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 50; i++) {
            lld1.addLast(i);
//            lld1.printDeque();
        }
//        lld1.printDeque();
//
//        for (double i = 0; i < 50; i++) {
//            System.out.println((double) lld1.removeFirst());
//        }

//        for (int x : lld1) {
//            System.out.println(x);
//        }



    }
}

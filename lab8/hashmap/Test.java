package hashmap;

import static org.junit.Assert.assertTrue;

public class Test {
    public static void main(String[] args) {
//        System.out.println( (double) 3 / (double) 4);

        MyHashMap<String, Integer> b = new MyHashMap<>();

        for (int i = 0; i < 13; i++) {
            b.put("hi" + i, 1);
            //make sure put is working via containsKey and get
            System.out.println(b.size());
        }




    }
}













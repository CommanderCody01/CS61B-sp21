package bstmap;

import static org.junit.Assert.assertTrue;

public class Test {

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 11; i++){
            String x = "hi" + i;
//            System.out.println(x);
            b.put("hi" + i, 1 + i);
        }

    }

}

package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testAddLast() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                broken.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = broken.size();
                assertEquals(size, size2);
//                System.out.println("size: " + size);
            }else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                int last = L.getLast();
                int last2 = broken.getLast();
                assertEquals(last, last2);
//                System.out.println("last: " + last);
            }else if (operationNumber == 3 && L.size() > 0) {
                // removeLast
                int last = L.removeLast();
                int last2 = broken.removeLast();
                assertEquals(last, last2);
//                System.out.println("last: " + last);
            }
        }
    }
}

package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

//    public T max() {
//        if (this.isEmpty()) {
//            return null;
//        }
//        int maxIndex = this.getFrontIndex();
//        int curr = this.getFrontIndex();
//        int count = 0;
//        while (count < this.size()) {
//            int cmp = com.compare(this.get(curr), this.get(maxIndex));
//            if (cmp > 0) {
//                maxIndex = curr;
//            }
//            curr = nextIndex(curr);
//            count += 1;
//        }
//        return this.get(maxIndex);
//    }
//
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        int curr = 0;
//        int count = 0;
        while (curr < this.size()) {
            int cmp = c.compare(this.get(curr), this.get(maxIndex));
            if (cmp > 0) {
                maxIndex = curr;
            }
            curr += 1;
//            count += 1;
        }
        return this.get(maxIndex);
    }

//    public T max(Comparator<T> c) {
//        if (isEmpty()) {
//            return null;
//        }
//        int maxIndex = 0;
//        for (int i = 1; i < size(); i++) {
//            if (c.compare(get(i), get(maxIndex)) > 0) {
//                maxIndex = i;
//            }
//        }
//        return get(maxIndex);
//    }

    public T max() {
        return max(comparator);
    }

}

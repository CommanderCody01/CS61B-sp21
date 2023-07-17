package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> com;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        com = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int maxIndex = this.getFrontIndex();
        int curr = this.getFrontIndex();
        int count = 0;
        while (count < this.size()) {
            int cmp = com.compare(this.get(curr), this.get(maxIndex));
            if (cmp > 0) {
                maxIndex = curr;
            }
            curr = nextIndex(curr);
            count -= 1;
        }
        return this.get(maxIndex);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxIndex = this.getFrontIndex();
        int curr = this.getFrontIndex();
        int count = 0;
        while (count < this.size()) {
            int cmp = c.compare(this.get(curr), this.get(maxIndex));
            if (cmp > 0) {
                maxIndex = curr;
            }
            curr = nextIndex(curr);
            count -= 1;
        }
        return this.get(maxIndex);
    }

}

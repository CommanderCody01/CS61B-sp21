package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> lst = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int count = 0;
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        Ns.addLast(128000);
        opCounts.addLast(1000);
        opCounts.addLast(2000);
        opCounts.addLast(4000);
        opCounts.addLast(8000);
        opCounts.addLast(16000);
        opCounts.addLast(32000);
        opCounts.addLast(64000);
        opCounts.addLast(128000);

        Stopwatch sw = new Stopwatch();
        while (count < 128001) {       // 128000
            lst.addLast(0);
            if (count == 1000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 2000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 4000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 8000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 16000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 32000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 64000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            if (count == 128000 ) {
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
            count = count + 1;
        }

        printTimingTable(Ns, times, opCounts);
        return;


    }
}
